package com.atguigu.springcloud.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @ClassName Payment
 * @Description TODO
 * @Author acui
 * @Date 2021/1/10 23:46
 * @Version 1.0
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Payment implements Serializable {
    //数据库中为bigint，对应Long
    private Long id;
    private String serial;
}