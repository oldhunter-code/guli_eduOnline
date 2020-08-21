package com.midea.servicebase.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//自定义异常测试
@Data
@AllArgsConstructor //生成有参构造函数方法
@NoArgsConstructor //无参构造函数
public class MyException extends RuntimeException {
    private Integer code; //状态码
    private String message;//异常信息


}
