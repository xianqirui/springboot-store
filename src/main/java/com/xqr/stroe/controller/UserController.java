package com.xqr.stroe.controller;

import com.xqr.stroe.controller.exception.*;
import com.xqr.stroe.entity.User;
import com.xqr.stroe.service.IUserService;
import com.xqr.stroe.service.exception.InsertException;
import com.xqr.stroe.service.exception.UserNameException;
import com.xqr.stroe.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

//@Controller
@RestController
@RequestMapping("/users")
public class UserController extends BaseController{

    @Autowired
    private IUserService userService;

    @RequestMapping("/reg")
//    @ResponseBody //响应json
    public JsonResult<Void> reg(User user){
        //创建响应结果对象
      userService.reg(user);

        return new JsonResult<>(OK);
    }

   /* @RequestMapping("/reg")
//    @ResponseBody //响应json
    public JsonResult<Void> reg(User user){
        //创建响应结果对象
        JsonResult<Void> result = new JsonResult<>();
        try {
            userService.reg(user);
            result.setState(200);
            result.setMessage("用户注册成功");
        } catch (UserNameException e) {
            result.setState(4000);
            result.setMessage("用户名被占用");
        }catch (InsertException e){
            result.setState(1000);
            result.setMessage("注册时产生未知错误");
        }
        return result;
    }*/
    /*约定大于配置：省略大量的配置注解的编写
    * 2。接收数据方式：请求处理方法的参数列表设置为非pojo类型，
    * soringboot直接将参数名和方法的参数名进行比较
    * 相同则自动注入
    * */
    /*用户登录*/
    @RequestMapping("/login")
    public JsonResult<User> login(String username, String password, HttpSession session){
        //登陆前清除session
        session.removeAttribute("uid");
        session.removeAttribute("username");
        User data = userService.login(username, password);
        //向session对象中绑定数据
        session.setAttribute("uid",data.getUid());
        session.setAttribute("username",data.getUsername());
        //获取session中绑定的数据
        System.out.println(getUsernameFromSession(session));
        return new JsonResult<User>(OK,data);
    }
    /*密码修改*/
    @RequestMapping("/change_password")
    public JsonResult<Void> changePassword(String oldPassword,
                                           String newPassword,
                                           HttpSession session){
        Integer uid = getuidFromSession(session);
        String username = getUsernameFromSession(session);
        userService.changePasswword(uid,username,oldPassword,newPassword);
        return new JsonResult<>(OK);
    }

    /*查询用户数据*/
    @RequestMapping("/get_by_uid")
    public JsonResult<User> getByUid(HttpSession session){
        User data = userService.getByUid(getuidFromSession(session));

        return new JsonResult<>(OK,data);
    }

    /*更新用户数据*/
    @RequestMapping("/change_info")
    public JsonResult<Void> changeInfo(User user,HttpSession session){
        //user对象中有：username,phone,email,gender
        Integer uid = getuidFromSession(session);
        String username = getUsernameFromSession(session);
        userService.changeInfo(uid,username,user);
        return new JsonResult<>(OK);
    }
    //接受用户头像

    /**
     * MultipartFile接口时SpringMvc提供的接口
     * 可以获取任何类型的文件
     * @param session
     * @param file
     * @return
     */
    //设置上传文件最大值
    public static final int AVATAR_MAX_SIZE=10*1024*1024;
    //限制文件类型
    public static final List<String> AVATAR_TYPE=new ArrayList<>();
    static {
        AVATAR_TYPE.add("image/jpeg");
        AVATAR_TYPE.add("image/png");
        AVATAR_TYPE.add("image/bmp");
        AVATAR_TYPE.add("image/gif");
    }
    @RequestMapping("/change_avatar")
    public JsonResult<String> changeAvatar(HttpSession session,
                                           @RequestParam("file") MultipartFile file){
        System.out.println("上传头像开始");
            //判断文件是否为空
        if (file.isEmpty()){
            throw new FileEmptyException("文件为空");
        }
        if (file.getSize()>AVATAR_MAX_SIZE){
            throw new FileSizeException("文件超出限制");
        }
        //判断文件类型
        String contentyep=file.getContentType();
        System.out.println(contentyep);
        //如果集合包含某个元素返回true
        if(!AVATAR_TYPE.contains(contentyep)){
            throw new FileTypeException("文件类型不支持");
        }
        //上传的文件... /upload/文件.png
        //String path = session.getServletContext().getRealPath("/upload");
        //因为每次运行项目都会生成一个tomcat文件，所以我直接把存储路径定死
        //File对象执行这个路径，File是否存在
        File dir=new File("E:\\IDEwork\\SpringBoot实战\\stroe\\src\\main\\resources\\static\\upload\\");
        if (!dir.exists()){//检测目录是否存在
            dir.mkdirs();//创建目录
        }
        System.out.println(dir);
        //获取文件名称，
        String filename = file.getOriginalFilename();
        System.out.println("filename"+filename);
        int index = filename.lastIndexOf(".");
        //文件后缀
        String suffix=filename.substring(index);
        //UUID生成新的字符串作为文件名
        filename = UUID.randomUUID().toString().toUpperCase()+suffix;
        //空文件
        File dest=new File(dir,filename);
        //将参数中的文件数据写入空文件中
        try {
            file.transferTo(dest);//将file文件中的数据写入到dest文件中
        } catch (IOException e) {
            throw new FileUploadIOException("文件读写异常");
        }catch (FileStateException e) {
            throw new FileStateException("文件状态异常");
        }
        //
        Integer uid=getuidFromSession(session);
        String username=getUsernameFromSession(session);
        //返回头像路径/upload/test.png
        String avatar="/upload/"+filename;
        userService.updateAvatarByUid(uid,avatar,username);
        //返回路径给前端，将来用来展示头像
        return new JsonResult<String>(OK,avatar);
    }
}
