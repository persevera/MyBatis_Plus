package com.liuyong.mybatisplus.myclass;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

@Data//实体类简易操作注解
@TableName("User")//指定实体类对应的数据库表名
public class user {
        //设置主键自增
    @TableId(value = "uid",type = IdType.AUTO)
    private  long  id;
    private  String  name;
    private  Integer  age;
    private  String   email;
    private SexEnum sex;
    @TableLogic//设置该主键为逻辑删除的字段
    private   Integer  if_delete;


}
