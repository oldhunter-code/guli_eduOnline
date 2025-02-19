package com.midea.eduManager.controller;

import com.midea.commonutils.R;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/eduservice/user")
@CrossOrigin  //解决跨域问题
public class EduLoginController {

    //login
    @PostMapping("login")
    public R login(){

        return R.ok().data("token","admin");
    }
    //info获取用户信息
    @GetMapping("info")
    public R info(){

        return  R.ok().data("roles","admin").data("name","admin").data("avatar","https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
    }
}
