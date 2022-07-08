package com.liuyong.mybatisplus.myclass;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.util.Collections;

public class daimasehngcheng {
    public static void main(String[] args) {
        FastAutoGenerator.create("jdbc:mysql://43.138.55.22:3306/mybatis_plus? characterEncoding=utf-8&userSSL=false", "root", "123456") .globalConfig(
                builder -> {
            builder.author("liuyong")// 设置作者
                    // .enableSwagger() // 开启 swagger 模式
                    .fileOverride() // 覆盖已生成文件
                    .outputDir("D://mybatis_plus"); // 指定输出目录
                    //这样设置是指自动生成的文件都在com.atguigu.mybatisplus目录下
                   }).packageConfig(builder -> { builder.parent("com.liuyong") // 设置父包名
                   .moduleName("mybatisplus") // 设置父包模块名
                  .pathInfo(Collections.singletonMap(OutputFile.mapperXml, "D:\\javaproject\\MyBatis-Plus\\mybatisplus\\src\\main\\resources\\mapper")); // 设置mapperXml生成路径
                  }).strategyConfig(builder -> { builder.addInclude("user") // 设置需要生成的表名
                  .addTablePrefix(""); // 设置过滤表前缀
                   }).templateEngine(new FreemarkerTemplateEngine()) // 使用Freemarker 引擎模板，默认的是Velocity引擎模板
               .execute();
    }
}
