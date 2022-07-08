package com.liuyong.mybatisplus.Service;



import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.liuyong.mybatisplus.Daointerface.UserMapper;
import com.liuyong.mybatisplus.myclass.user;

import javax.annotation.Resource;

public class mydaoserviceImpl extends ServiceImpl<UserMapper, user> implements mydaoservice {
    @Resource
    private   UserMapper  userMapper;
    @Override
    public Page<user> selectsPagevo(Page<user> page, Integer age) {
        return userMapper.selectsPagevo(page,age);
    }
}
