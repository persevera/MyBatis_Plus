package com.liuyong.mybatisplus;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.liuyong.mybatisplus.Daointerface.UserMapper;
import com.liuyong.mybatisplus.myclass.SexEnum;
import com.liuyong.mybatisplus.myclass.user;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;

@SpringBootTest
class MybatisplusApplicationTests {

    @Autowired
    private  UserMapper  myUserMapper;
    @Test
    void contextLoads() {
    }

 //测试查询全部
    @Test
    void  testselectall(){
        List<user> users = myUserMapper.selectList(null);
        for (user user : users) {
            System.out.println(user);
        }
    }
    //测试新增数据
    @Test
    void   testinsert(){
        //创建实体类对象
        user  user=new user();
        //给实体类赋值
        user.setAge(12);
        user.setEmail("2234215028@qq.com");
        user.setName("李四");
        int insert = myUserMapper.insert(user);
      if(insert==1){
          System.out.println("数据插入成功");
          System.out.println("主键id为" + user.getId());
      }
    }
      //测试删除数据
     @Test
    void  testdelete(){
        //删除指定数据的id
         int i = myUserMapper.deleteById(0);
         if(i==1){
             System.out.println("删除成功");
         }
         //通过Map集合删除,map存储条件
         Map<String,Object>  map=new HashMap<>();
         map.put("name","李四");
         map.put("age",12);
         int i1 = myUserMapper.deleteByMap(map);
         if(i1==1){
             System.out.println("name为李四，年龄为12的数据已经被删除");
         }
         //通过多个id进行批量的删除
         List<Integer> longs = Arrays.asList(1, 2, 3);
         int i2 = myUserMapper.deleteBatchIds(longs);
         if(i2==1){
             System.out.println("数据删除成功");
         }
     }
     @Test
    void  testupdate(){
        user  user=new user();
        user.setId(0);
        //需要修改什么就给哪个属性重新赋值
        user.setName("张三");
        user.setEmail("2815721758@qq.com");
         int i = myUserMapper.updateById(user);
         if(i==1){
             System.out.println("修改成功");
         }
     }
     @Test
    void  testselect(){
        //查询指定的id的信息
         user user = myUserMapper.selectById(0);
         System.out.println(user);
         //查询指定的多个id的数据信息
         List<Integer> integers = Arrays.asList(0, 1, 2);
         List<user> users = myUserMapper.selectBatchIds(integers);
        users.forEach(System.out::println);
        //map集合存储查询条件进行查询
         Map<String, Object> map=new HashMap<>();
         map.put("name","张三");
         map.put("id",0);
         List<user> users1 = myUserMapper.selectByMap(map);
         users1.forEach(System.out::println);
         //查询所有数据
         List<user> users2 = myUserMapper.selectList(null);
         users2.forEach(System.out::println);
     }
     //wrapper组装查询条件
    @Test
    public   void  testwarpperselect(){
        //初始化QueryWrapper对象
        QueryWrapper<user>  queryWrapper=new QueryWrapper<>();
        //查询用户名包含a,年龄在20到30之间，邮箱信息不为null的信息
        queryWrapper.like("name","a").between("age",20,30).isNotNull("email");
        List<user> list=myUserMapper.selectList(queryWrapper);
        list.forEach(System.out::println);
    }
    //wrapper排序查询
    @Test
    public   void  testwarpperorderby(){
        //初始化QueryWrapper对象
        QueryWrapper<user>  queryWrapper=new QueryWrapper<>();
        //按年龄的降序和uid的升序进行排序
        queryWrapper.orderByDesc("age").orderByAsc("uid");
        List<user> list = myUserMapper.selectList(queryWrapper);
        list.forEach(System.out::println);
    }
    //wrapper删除条件
    @Test
    public  void  testwarpperdelete(){
        QueryWrapper<user> objectQueryWrapper = new QueryWrapper<>();
        //删除email为空的数据
        objectQueryWrapper.isNull("email");
        List<user> list = myUserMapper.selectList(objectQueryWrapper);
        list.forEach(System.out::println);
    }
    //wrappers实现修改功能
    @Test
    public  void   testupdateq(){
        QueryWrapper<user> myquerywrapper = new QueryWrapper<>();
        //条件为年龄大于20并且name中有a,或者email为空的数据
        myquerywrapper.gt("age",20).like("name","a").or().isNull("email");
        user u=new user();
        u.setAge(12);
        u.setName("小明");
        myUserMapper.update(u,myquerywrapper);
    }
    //wrappers条件优先级
    @Test
    public  void  test05(){
        QueryWrapper<user> objectQueryWrapper = new QueryWrapper<>();
        //将用户名中包含a并且(年龄大于20或邮箱为null)的用户信息删除
        objectQueryWrapper.like("name","a")
                //lambada表达式中的条件优先执行
                //年龄大于20或邮箱为null是作为一个整体的条件，用and方法和lambada表
                .and(i->i.gt("age",20).isNull("email"));
        myUserMapper.delete(objectQueryWrapper);
    }
    //组装select语句
    @Test
    public  void   test06() {
        QueryWrapper<user> objectQueryWrapper = new QueryWrapper<>();
        //查询name,age,email这些字段的数据
        objectQueryWrapper.select("name", "age", "email");
        List<Map<String, Object>> maps = myUserMapper.selectMaps(objectQueryWrapper);
        maps.forEach(System.out::println);
    }
    //子查询
    @Test
    public  void  test07(){
        QueryWrapper<user> objectQueryWrapper = new QueryWrapper<>();
        //子查询实现查询id小于100的数据
        //相当于select *from user where  uid  in(select *from user where id<100);
        //以下就是子查询嵌套
        objectQueryWrapper.inSql("uid","select uid from t_user");
        List<user> list = myUserMapper.selectList(objectQueryWrapper);
        list.forEach(System.out::println);
    }
    //updateWapper实现修改功能
    @Test
    public  void  test08(){
        UpdateWrapper<user>  hu=new UpdateWrapper<>(  );
        //设置条件用户名包含a并且(年龄大于20或邮箱为null)的用户信息
        hu.like("name","a").and(i->i.gt("age",20).or().isNotNull("email"));
        //设置修改信息
        hu.set("name","小黑");
        //执行修改操作,第二个参数是updatewrapper对象
        int update = myUserMapper.update(null, hu);
        if(update==1){
            System.out.println("修改成功");
        }
    }
    @Test
    public  void  test09(){
        user  u=new user();
        QueryWrapper<Object> objectQueryWrapper = new QueryWrapper<>();
        //第一个参数是符合这个条件时添加这个查询条件
        objectQueryWrapper.like(StringUtils.isNotBlank(u.getName()),"name",u.getName())
                //ge是大于等于
                .ge(u.getAge()!=null,"age",20)
                //le是小于等于
                .le(u.getAge()!=null,"age",30);
    }
    //LambadaWrapper的使用
    @Test
    public  void  test10(){
        user u=new user();
        LambdaQueryWrapper<user> queryWrapper=new LambdaQueryWrapper<>();
        //第一个参数是条件，条件满足时才会取添加这个查询的条件
        queryWrapper.like(StringUtils.isNotBlank(u.getName()),user::getName,u.getName())
                //大于等于10
                .ge(u.getAge()!=null,user::getAge,10)
                //小于等于20de1数据
                .le(u.getAge()!=null,user::getAge,20);
        myUserMapper.selectList(queryWrapper);
    }

    @Test
    public  void  test11(){
        //创建Page对象,泛型是实体类一下表示第1页，每页有3条数据
        Page<user> p1=new Page<>(1,3);
        //通过queryWrapper对象设置查询条件
        QueryWrapper<user>  queryWrapper=new QueryWrapper<>();
        queryWrapper.like("name","liuyong");
        /*通过Dao接口执行分页查询
        第一个参数是Page对象,queryWrapper条件对象
         */
       myUserMapper.selectPage(p1, queryWrapper);
       //p1就是返回的三条数据的json格数组
        System.out.println(p1);
        //获取记录
        System.out.println(p1.getRecords());
        //获取总页码
        System.out.println(p1.getPages());
        //获取总记录数
        System.out.println(p1.getTotal());
        //是否有下一页
        System.out.println(p1.hasNext());
        //是否有上一页
        System.out.println(p1.hasPrevious());
    }


    //自定义分页功能
    @Test
    public  void  test12(){
        //查询第一页,每一页3条数据
        Page<user>  p2=new Page<>(1,3);
        //这里调用的是Dao接口中自定义的连接数据库的方法
        myUserMapper.selectsPagevo(p2,20);
        //分页的数据
        System.out.println(p2);
    }
    //测试枚举
    @Test
    public   void  test13(){
        user us=new user();
        us.setName("liuyogn");
        us.setEmail("2234215028@qq.com");
        us.setId(1);
        us.setSex(SexEnum.MALE);//
    }
}
