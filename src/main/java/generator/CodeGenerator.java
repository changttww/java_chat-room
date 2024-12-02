package generator;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
//
//public class CodeGenerator {
//
//    public static void main(String[] args) {
//        // 代码生成器
//        AutoGenerator mpg = new AutoGenerator();
//
//        // 全局配置
//        GlobalConfig gc = new GlobalConfig();
//        String projectPath = System.getProperty("user.dir");
//        gc.setOutputDir(projectPath + "/src/main/java");
//        gc.setAuthor("nipengcheng");
//        gc.setOpen(false);
//        gc.setFileOverride(true); // 是否覆盖已有文件
//        mpg.setGlobalConfig(gc);
//
//        // 数据源配置
//        DataSourceConfig dsc = new DataSourceConfig();
//        dsc.setUrl("jdbc:mysql://localhost:3306/db_chat_room?useSSL=false&serverTimezone=UTC");
//        dsc.setDriverName("com.mysql.cj.jdbc.Driver");
//        dsc.setUsername("root");
//        dsc.setPassword("nipengcheng");
//        dsc.setDbType(DbType.MYSQL);
//        mpg.setDataSource(dsc);
//
//        // 包配置
//        PackageConfig pc = new PackageConfig();
//        pc.setModuleName("demo"); // 模块名
//        pc.setParent("com.example");
//        mpg.setPackageInfo(pc);
//
//        // 策略配置
//        StrategyConfig strategy = new StrategyConfig();
//        strategy.setNaming(NamingStrategy.underline_to_camel);
//        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
////        strategy.setEntityLombokModel(true); // lombok 模型 @Accessors(chain = true) setter链式操作
////        strategy.setRestControllerStyle(false); // restful api风格控制器
//        strategy.setControllerMappingHyphenStyle(true); // url中驼峰转连字符
//        strategy.setEntityTableFieldAnnotationEnable(true); // 是否生成实体时，生成字段注解
//        strategy.setEntityLombokModel(true); // 实体是否为lombok模型
//        strategy.setRestControllerStyle(false); // 生成@RestController 控制器
//        strategy.setInclude("chat_user"); // 需要生成的表
////         strategy.setTablePrefix(pc.getModuleName() + "_"); // 表前缀
//        mpg.setStrategy(strategy);
//        mpg.setTemplateEngine(new FreemarkerTemplateEngine());
//
//        // 模板配置，禁用不需要生成的模板
//        TemplateConfig templateConfig = new TemplateConfig();
//        templateConfig.setController(null);
//        templateConfig.setService(null);
//        templateConfig.setServiceImpl(null);
//        templateConfig.setXml(null);
//
//        mpg.setTemplate(templateConfig);
//        mpg.setTemplateEngine(new FreemarkerTemplateEngine());
//        // 执行生成
//        mpg.execute();
//    }
//}
