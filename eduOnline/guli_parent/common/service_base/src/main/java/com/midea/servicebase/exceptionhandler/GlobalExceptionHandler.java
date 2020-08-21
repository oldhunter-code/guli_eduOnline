package com.midea.servicebase.exceptionhandler;


import com.midea.commonutils.R;
import com.midea.servicebase.exception.MyException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    //指定出现什么异常执行这个方法
    @ExceptionHandler(Exception.class)
    @ResponseBody //为了返回数据
    public R error(Exception e) {
        e.printStackTrace();
        log.error(e.getMessage());
        return R.error().message("执行了全局异常处理..");
    }
    //特定异常执行这个方法
    @ExceptionHandler(ArithmeticException.class)
    @ResponseBody //为了返回数据
    public R error(ArithmeticException e) {//（重载）
        e.printStackTrace();
        return R.error().message("执行了ArithmeticException异常处理..");
    }

    //自定义异常
    @ExceptionHandler(MyException.class)
    @ResponseBody //为了返回数据
    public R error(MyException e) {//（重载）
        e.printStackTrace();
        log.error(e.getMessage());
        return R.error().code(e.getCode()).message(e.getMessage());
    }
}
