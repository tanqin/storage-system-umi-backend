package com.tanqin.common;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.engine.VelocityTemplateEngine;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * 代码生成器（新版）
 */
public class CodeGenerator {
    // 获取表名
    protected static List<String> getTables(String tables) {
        return "all".equals(tables) ? Collections.emptyList() : Arrays.asList(tables.split(","));
    }

    public static void main(String[] args) {
        String projectPath = System.getProperty("user.dir");
        System.out.println("projectPath ===> " + projectPath);
        // 创建一个代码生成器
        FastAutoGenerator.create("jdbc:mysql:///storage_system?useUnicode=true&characterEncoding=UTF8&useSSL=false", "root", "root")
                // 全局配置
                .globalConfig(builder -> {
                    builder.author("tanqin") // 设置作者
                            .enableSwagger() // 开启 swagger 模式
                            .dateType(DateType.TIME_PACK) // 时间策略
                            .commentDate("yyyy-MM-dd") // 注释日期
                            .outputDir(projectPath + "\\src\\main\\java"); // 指定输出目录
                })
                // 包配置
                .packageConfig((builder) -> {
                    builder.parent("com.tanqin") // 设置父包名
                            .moduleName("") // 设置父包模块名，这里一般不设置
                            .pathInfo(Collections.singletonMap(OutputFile.xml, projectPath + "\\src\\main\\resources\\mapper")); //  // 设置 mapperXml 生成路径，这里是 Mapper 配置文件的路径，建议使用绝对路径
                })
                // 策略配置
                .strategyConfig((scanner, builder) -> {
                    builder.entityBuilder().enableFileOverride().enableLombok(); // 覆盖已生成文件、启用 lombok
                    builder.addInclude(getTables(scanner.apply("请输入表名，多个英文逗号分隔？所有输入 all"))) // 设置需要生成的表名
                            .addTablePrefix("t_"); // 设置过滤表前缀
                    builder.serviceBuilder().formatServiceFileName("%sService") //设置 service 的命名策略,没有这个配置的话，生成的 service 和 serviceImpl 类前面会有一个I，比如 IUserService 和 IUserServiceImpl
                            .formatServiceImplFileName("%sServiceImpl"); //设置serviceImpl的命名策略
                    builder.controllerBuilder().enableRestStyle(); // 开启生成 @RestController 控制器，不配置这个默认是 Controller 注解，RestController 是返回 Json 字符串的，多用于前后端分离项目
                    builder.mapperBuilder().enableMapperAnnotation();//开启 @Mapper 注解，也就是在 dao 接口上添加一个 @Mapper 注解，这个注解的作用是开启注解模式，就可以在接口的抽象方法上面直接使用 @Select 和 @Insert 和 @Update 和 @Delete 注解
                })
//                .templateEngine(new FreemarkerTemplateEngine()) // 使用Freemarker引擎模板，默认的是Velocity引擎模板
                .templateEngine(new VelocityTemplateEngine()).execute(); //执行以上配置

    }
}
