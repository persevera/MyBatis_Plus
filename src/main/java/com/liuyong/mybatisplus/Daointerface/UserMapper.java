package com.liuyong.mybatisplus.Daointerface;
import java.util.List;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.liuyong.mybatisplus.myclass.user;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper extends BaseMapper<user> {


    Page<user>  selectsPagevo(@Param("page")Page<user> page,@Param("age") Integer age);

    int insertSelective(user user);

    List<user> selectPage();

}
