package com.huijava;

import com.google.common.base.CaseFormat;
import com.google.common.base.Charsets;
import com.google.common.io.Files;
import com.huijava.util.FileUtils;
import freemarker.template.TemplateExceptionHandler;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.Context;
import org.mybatis.generator.config.GeneratedKey;
import org.mybatis.generator.config.JDBCConnectionConfiguration;
import org.mybatis.generator.config.JavaClientGeneratorConfiguration;
import org.mybatis.generator.config.JavaModelGeneratorConfiguration;
import org.mybatis.generator.config.ModelType;
import org.mybatis.generator.config.PluginConfiguration;
import org.mybatis.generator.config.PropertyRegistry;
import org.mybatis.generator.config.SqlMapGeneratorConfiguration;
import org.mybatis.generator.config.TableConfiguration;
import org.mybatis.generator.internal.DefaultShellCallback;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 代码生成器，根据数据表名称生成对应的Model、Mapper、Service、Controller简化开发。
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
@TestConfiguration("classpath:aplication.properties")
@Data
public class CodeAuthGenerator {
    private static final Logger LOGGER = LoggerFactory.getLogger(CodeAuthGenerator.class);
    @Test
    public void main(){
        String[] tablesNames = jdbcTableNames.split(";");
        //生成代码
        authCreateCode(tablesNames);
        //重命名包名
        renamePackage(getJavaPath());
        renamePackage(getTestPath());
    }

    /**
     * 项目的开始基础包名称。请不要修改该包
     */
    @Value("${project.old-base-package}")
    private String oldBasePackage="com.huijava";
    /**
     * 生成代码所在的基础包名称，可根据自己公司的项目修改
     * 可以理解为将OLD_BASE_PACKAGE包名替换为BASE_PACKAGE配置Id包名
     */
    @Value("${project.new-base-package}")
    private String newBasePackage="com.huijava";

    /**
     * 生成的数据库实体所在包
     */
    @Value("${project.entity-package}")
    private String entityPackage=newBasePackage+".entity";
    /**
     * 生成的Mapper所在包
     */
    @Value("${project.mapper-package}")
    private String mapperPackage=newBasePackage+".mapper";
    /**
     * 生成的Service所在包
     */
    @Value("${project.service-package}")
    private String servicePackage=newBasePackage+".service";
    /**
     * 生成的ServiceImpl所在包
     */
    @Value("${project.service-impl-package}")
    private String serviceImplPackage=newBasePackage+".service.impl";
    /**
     * 生成的Controller所在包
     */
    @Value("${project.controller-package}")
    private String controllerPackage=newBasePackage+".controller";

    /**
     * Mapper插件基础接口的完全限定名
     */
    @Value("${project.base-mapper-class-name}")
    private String baseMapperClassName=newBasePackage+".common.base.Mapper";
    /**
     * 选择Controller模板，controller，controller-restful
     */
    @Value("${project.controller.ftl.model}")
    public String controllerFtlModel = "controller.ftl";

    /**
     * JDBC配置
     */
    @Value("${spring.datasource.url}")
    private String jdbcUrl;
    @Value("${spring.datasource.username}")
    private String jdbcUsername;
    @Value("${spring.datasource.password}")
    private String jdbcPassword;
    @Value("${spring.datasource.driver-class-name}")
    private String jdbcDiverClassName;

    /**
     * 项目在硬盘上的根路径
     */
    private static final String PROJECT_PATH = System.getProperty("user.dir");

    /**
     * 类的模板位置
     */
    private static final String TEMPLATE_FILE_PATH = PROJECT_PATH + "/src/test/resources/generator/template";

    /**
     * 资源文件路径
     */
    @Value("${project.resources.path}")
    private String resourcesPath="/src/main/resources";

    /**
     * java文件路径
     */
    @Value("${project.java.path}")
    private String javaPath="/src/main/java";
    /**
     * @author 作者
     */
    @Value("${project.author}")
    private String author="chenhaoxiang";

    /**
     * test文件路径
     */
    @Value("${project.test.path}")
    private String testPath="/src/main/test";
    /**
     * @date
     */
    private static final String DATE = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
    /**
     * 表名生成Model需要省略的前缀位数
     * 例如sp_user,如配置IGNORE_PREFIX=3，则生成Model为User，否则为SpUser
     *
     */
    @Value("${project.ignore.prefix}")
    private Integer ignorePrefix=0;
    /**
     * 配置数据库表名，多个数据库之间用英文分号隔开
     */
    @Value("${project.jdbc.table.names}")
    public String jdbcTableNames;

    /**
     * 重名名目录代码中的项目包名
     */
    private void renamePackage(String packageName) {
        String oldBasePackage = getOldBasePackage();
        String newBasePackage = getNewBasePackage();
        if(oldBasePackage.equals(newBasePackage)){
            return;
        }
        //如果包名被修改，遍历默认包名下的文件，将文件类默认包名修改为配置的包名
        String oldBaseName = oldBasePackage.replace(".",File.separator);
        String oldBasePath = PROJECT_PATH+packageName+File.separator+oldBaseName;
        String newFileName = newBasePackage.replace(".",File.separator);
        List<String> fileNames = FileUtils.getFilesName(oldBasePath);
        for(String fileName:fileNames) {
            //将原来文件中的老包名修改为最新的包名
            try {
                String newFilePath = fileName.replace(oldBaseName,newFileName);
                List<String> fileContents=Files.readLines(new File(fileName),Charsets.UTF_8);
                StringBuffer newFileContent = new StringBuffer();
                for(int i=0;i<fileContents.size();i++){
                    newFileContent.append(fileContents.get(i).replace(oldBasePackage,newBasePackage)).append("\r\n");
                }
                FileUtils.createDir(newFilePath.substring(0,newFilePath.lastIndexOf(File.separator)));
                //将内容存入文件
                Files.write(newFileContent.toString().getBytes(Charsets.UTF_8),new File(newFilePath));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        //删除原来的老目录
        String[] oldPaths = oldBasePackage.split("\\.");
        String[] newPaths = newBasePackage.split("\\.");
        String oldPath = PROJECT_PATH+packageName+File.separator;
        for(int i=0;i<oldPaths.length;i++){
            if(newPaths.length<=i){
                oldPath=oldPath+oldPaths[i]+File.separator;
                break;
            }
            oldPath=oldPath+oldPaths[i]+File.separator;
            if(!oldPaths[i].equals(newPaths[i])){
                break;
            }
        }
        //如果新包名包含了老包名，则不删除
        if(!getNewBasePackage().contains(getOldBasePackage())) {
            FileUtils.removeFiles(oldPath);
        }
    }

    /**
     * 通过数据表名称生成代码，Model 名称通过解析数据表名称获得，下划线转大驼峰的形式。
     * 如输入表名称 "sq_user" 将生成 SqUser、SqUserMapper、SqUserService、SqUserController
     * @param tableNames 数据表名称...
     */
    public void authCreateCode(String... tableNames) {
        for (String tableName : tableNames) {
            if(getIgnorePrefix().equals(0)){
                genCodeByCustomModelName(tableName, null);
            }else {
                String modelName=tableName.substring(getIgnorePrefix(),tableName.length());
                //表名转驼峰  tp_user -> TpUser
                modelName = CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, modelName);
                genCodeByCustomModelName(tableName, modelName);
            }
        }
    }

    /**
     * 通过数据表名称，和自定义的 Model 名称生成代码
     * 如输入表名称 "sq_user" 和自定义的 Model 名称 "User" 将生成 User、UserMapper、UserService ...
     * @param tableName 数据表名称
     * @param modelName 自定义的 Model 名称
     */
    public void genCodeByCustomModelName(String tableName, String modelName) {
        genModelAndMapper(tableName, modelName);
        genService(tableName, modelName);
        genController(tableName, modelName);
    }


    public void genModelAndMapper(String tableName, String modelName) {
        Context context = new Context(ModelType.FLAT);
        context.setId("spring-boot-quick-project");
        context.setTargetRuntime("MyBatis3Simple");
        context.addProperty(PropertyRegistry.CONTEXT_BEGINNING_DELIMITER, "`");
        context.addProperty(PropertyRegistry.CONTEXT_ENDING_DELIMITER, "`");

        JDBCConnectionConfiguration jdbcConnectionConfiguration = new JDBCConnectionConfiguration();
        jdbcConnectionConfiguration.setConnectionURL(getJdbcUrl());
        jdbcConnectionConfiguration.setUserId(getJdbcUsername());
        jdbcConnectionConfiguration.setPassword(getJdbcPassword());
        jdbcConnectionConfiguration.setDriverClass(getJdbcDiverClassName());
        context.setJdbcConnectionConfiguration(jdbcConnectionConfiguration);

        PluginConfiguration pluginConfiguration = new PluginConfiguration();
        pluginConfiguration.setConfigurationType("tk.mybatis.mapper.generator.MapperPlugin");
        pluginConfiguration.addProperty("mappers", getBaseMapperClassName());
        context.addPluginConfiguration(pluginConfiguration);

        JavaModelGeneratorConfiguration javaModelGeneratorConfiguration = new JavaModelGeneratorConfiguration();
        javaModelGeneratorConfiguration.setTargetProject(PROJECT_PATH + getJavaPath());
        javaModelGeneratorConfiguration.setTargetPackage( getEntityPackage());
        context.setJavaModelGeneratorConfiguration(javaModelGeneratorConfiguration);

        SqlMapGeneratorConfiguration sqlMapGeneratorConfiguration = new SqlMapGeneratorConfiguration();
        sqlMapGeneratorConfiguration.setTargetProject(PROJECT_PATH + getResourcesPath());
        sqlMapGeneratorConfiguration.setTargetPackage("mapper");
        context.setSqlMapGeneratorConfiguration(sqlMapGeneratorConfiguration);

        JavaClientGeneratorConfiguration javaClientGeneratorConfiguration = new JavaClientGeneratorConfiguration();
        javaClientGeneratorConfiguration.setTargetProject(PROJECT_PATH + getJavaPath());
        javaClientGeneratorConfiguration.setTargetPackage( getMapperPackage());
        javaClientGeneratorConfiguration.setConfigurationType("XMLMAPPER");
        context.setJavaClientGeneratorConfiguration(javaClientGeneratorConfiguration);

        TableConfiguration tableConfiguration = new TableConfiguration(context);
        tableConfiguration.setTableName(tableName);
        if (StringUtils.isNotEmpty(modelName)){
            tableConfiguration.setDomainObjectName(modelName);
        }
        tableConfiguration.setGeneratedKey(new GeneratedKey("id", "Mysql", true, null));
        context.addTableConfiguration(tableConfiguration);

        List<String> warnings;
        MyBatisGenerator generator;
        try {
            Configuration config = new Configuration();
            config.addContext(context);
            config.validate();

            DefaultShellCallback callback = new DefaultShellCallback(true);
            warnings = new ArrayList<>();
            generator = new MyBatisGenerator(config, callback, warnings);
            generator.generate(null);
        } catch (Exception e) {
            throw new RuntimeException("生成Model和Mapper失败", e);
        }

        if (generator.getGeneratedJavaFiles().isEmpty() || generator.getGeneratedXmlFiles().isEmpty()) {
            throw new RuntimeException("生成Model和Mapper失败：" + warnings);
        }
        if (StringUtils.isEmpty(modelName)) modelName = tableNameConvertUpperCamel(tableName);
        LOGGER.info("====={}.java 生成成功",modelName);
        LOGGER.info("====={}Mapper.java 生成成功",modelName);
        LOGGER.info("====={}Mapper.xml 生成成功",modelName);
    }

    public void genService(String tableName, String modelName) {
        try {
            freemarker.template.Configuration cfg = getConfiguration();

            Map<String, Object> data = new HashMap<>();
            data.put("date", DATE);
            data.put("author", getAuthor());
            String modelNameUpperCamel = StringUtils.isEmpty(modelName) ? tableNameConvertUpperCamel(tableName) : modelName;
            data.put("modelNameUpperCamel", modelNameUpperCamel);
            data.put("modelNameLowerCamel", tableNameConvertLowerCamel(tableName));
            data.put("basePackage", getNewBasePackage());
            data.put("serviceImplPackage", getServiceImplPackage());
            data.put("servicePackage", getServicePackage());
            data.put("entityPackage", getEntityPackage());
            data.put("mapperPackage", getMapperPackage());

            File file = new File(PROJECT_PATH + getJavaPath() + packageConvertPath(getServicePackage()) + modelNameUpperCamel + "Service.java");
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }
            cfg.getTemplate("service.ftl").process(data,
                    new FileWriter(file));
            LOGGER.info("====={}Service.java 生成成功",modelNameUpperCamel);

            File file1 = new File(PROJECT_PATH + getJavaPath() +  packageConvertPath(packageConvertPath(getServiceImplPackage())) + modelNameUpperCamel + "ServiceImpl.java");
            if (!file1.getParentFile().exists()) {
                file1.getParentFile().mkdirs();
            }
            cfg.getTemplate("service-impl.ftl").process(data,
                    new FileWriter(file1));

            LOGGER.info("====={}ServiceImpl.java 生成成功",modelNameUpperCamel);
        } catch (Exception e) {
            throw new RuntimeException("生成Service失败", e);
        }
    }

    public void genController(String tableName, String modelName) {
        try {
            freemarker.template.Configuration cfg = getConfiguration();

            Map<String, Object> data = new HashMap<>();
            data.put("date", DATE);
            data.put("author", getAuthor());
            String modelNameUpperCamel = StringUtils.isEmpty(modelName) ? tableNameConvertUpperCamel(tableName) : modelName;
            data.put("baseRequestMapping", modelNameConvertMappingPath(modelNameUpperCamel));
            data.put("modelNameUpperCamel", modelNameUpperCamel);
            data.put("modelNameLowerCamel", CaseFormat.UPPER_CAMEL.to(CaseFormat.LOWER_CAMEL, modelNameUpperCamel));
            data.put("basePackage", getNewBasePackage());
            data.put("servicePackage", getServicePackage());
            data.put("entityPackage", getEntityPackage());
            data.put("controllerPackage", getControllerPackage());

            File file = new File(PROJECT_PATH + getJavaPath() + packageConvertPath(getControllerPackage()) + modelNameUpperCamel + "Controller.java");
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }
            cfg.getTemplate(controllerFtlModel).process(data, new FileWriter(file));

            LOGGER.info("====={}Controller.java 生成成功",modelNameUpperCamel);
        } catch (Exception e) {
            throw new RuntimeException("生成Controller失败", e);
        }

    }

    private freemarker.template.Configuration getConfiguration() throws IOException {
        freemarker.template.Configuration cfg = new freemarker.template.Configuration(freemarker.template.Configuration.VERSION_2_3_23);
        cfg.setDirectoryForTemplateLoading(new File(TEMPLATE_FILE_PATH));
        cfg.setDefaultEncoding("UTF-8");
        cfg.setTemplateExceptionHandler(TemplateExceptionHandler.IGNORE_HANDLER);
        return cfg;
    }

    private String tableNameConvertLowerCamel(String tableName) {
        return CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, tableName.toLowerCase());
    }

    private String tableNameConvertUpperCamel(String tableName) {
        return CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, tableName.toLowerCase());

    }

    private String tableNameConvertMappingPath(String tableName) {
        //兼容使用大写的表名
        tableName = tableName.toLowerCase();
        return "/" + (tableName.contains("_") ? tableName.replaceAll("_", "/") : tableName);
    }

    private String modelNameConvertMappingPath(String modelName) {
        String tableName = CaseFormat.UPPER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, modelName);
        return tableNameConvertMappingPath(tableName);
    }

    private String packageConvertPath(String packageName) {
        return String.format("/%s/", packageName.contains(".") ? packageName.replaceAll("\\.", "/") : packageName);
    }

}
