package com.liuyong.mybatisplus.Service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.liuyong.mybatisplus.myclass.user;
import org.apache.ibatis.annotations.Param;

public interface mydaoservice extends IService<user> {
    Page<user> selectsPagevo(@Param("page")Page<user> page, @Param("age") Integer age);
}
