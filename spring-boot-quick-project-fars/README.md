
## 简介
[spring-boot-quick-project-fars](https://github.com/chenhaoxiang/spring-boot-quick-project-all/tree/master/spring-boot-quick-project-fars)全名为spring-boot-quick-project-front-and-rear-separation    
是一基于SpringBoot,MyBatis等开源框架快速构建CSD（Controller-Service-DAO）层的项目，用于快速搭建中小型的API、RESTful API项目。使用简单，运行稳定快捷，摆脱重复的劳动，专注业务代码的编写。  
下面是一个简单的视频Demo，基于本项目可以在短短几分钟内实现一套简单的API，0代码编写实现单表的任意增删改查操作。         

### 演示视频
[![YouTube](http://blogimg.chenhaoxiang.cn/18-9-3/22718315.jpg)](https://youtu.be/_I4PSA4obPs)
点击图片打开演示视频，打不开的需要翻墙~~  

## 特征
- 最佳实践的项目结构、配置文件、精简的POM 
- 统一响应结果封装及生成工具 
- 统一异常处理
- 简单的接口签名认证
- 常用基础方法抽象封装
- 使用Druid Spring Boot Starter 集成Druid数据库连接池与监控
- 使用FastJsonHttpMessageConverter，提高JSON序列化速度
- 集成MyBatis、通用Mapper插件、PageHelper分页插件，实现单表业务零SQL
- 提供代码生成器根据表名生成对应的Entity、Mapper、MapperXML、Service、ServiceImpl、Controller等基础代码
- Controller模板默认提供POST和RESTful两套，根据需求在```application-init.properties```配置文件中自己配置`project.controller.ftl.model`，默认使用POST方式的模板。
- 代码模板可根据实际项目的需求来扩展，由于每个公司业务都不太一样，所以只提供了一些比较基础、通用的模板，**主要是提供一个思路**来减少重复代码的编写。
- 在实际项目的使用中，可以根据公司业务的抽象编写大量的模板。另外，使用模板也有助于保持团队代码风格的统一 

 
## 快速开始
1. 克隆项目到本地
2. 对```src/resoucres```下的```application-dev.properties，application-init.properties```进行配置，主要是JDBC，项目包名等配置
3. 对```src/test```包内的代码生成器```CodeAuthGenerator```中的main测试方法进行配置，主要是配置需要生成的表名称，根据表名来生成代码
4. 如果只是想测试的话，可以使用```src/test/resources```目录下的```sq-user.sql```，否则忽略该步
5. 输入表名，运行```CodeAuthGenerator.main()```方法，生成基础代码（可能需要刷新项目目录才会出来）
6. 根据业务在基础代码上进行扩展
7. 对开发环境配置文件```application-dev.properties```进行配置，启动项目即可。

## 开发建议
- 表名，建议使用小写，多个单词使用下划线拼接
- Entity内成员变量与表字段数量对应，如需扩展成员变量（比如连表查询）建议创建DTO，否则需在扩展的成员变量上加```@Transient```注解，详情见[通用Mapper插件文档说明](https://mapperhelper.github.io/docs/2.use/)
- 建议业务失败直接使用```ServiceException("message")```抛出，由统一异常处理器来封装业务失败的响应结果，比如```throw new ServiceException("该手机号已被注册")```，会直接被封装为```{"code":400,"message":"该手机号已被注册"}```返回，无需自己处理，尽情抛出
- 需要工具类的话建议先从```apache-commons-*```和```guava```中找，实在没有再造轮子或引入类库，尽量精简项目
- 开发规范建议遵循（[阿里巴巴Java开发手册](https://github.com/alibaba/p3c))
- 建议在公司内部使用[ShowDoc](https://github.com/star7th/showdoc)、[SpringFox-Swagger2](https://github.com/springfox/springfox) 、[RAP](https://github.com/thx/RAP)等开源项目来编写、管理API文档
 
## 项目中依赖的部分开源项目
- Spring Boot（[https://github.com/spring-projects/spring-boot](https://github.com/spring-projects/spring-boot)）
- MyBatis（[http://www.mybatis.org/mybatis-3/zh/index.html](http://www.mybatis.org/mybatis-3/zh/index.html)）
- MyBatisb通用Mapper插件（[https://mapperhelper.github.io/docs/](https://mapperhelper.github.io/docs/)）
- MyBatis PageHelper分页插件（[https://pagehelper.github.io/](https://pagehelper.github.io/)）
- Druid（[https://github.com/alibaba/druid](https://github.com/alibaba/druid)）
- Fastjson（[https://github.com/alibaba/fastjson](https://github.com/alibaba/fastjson)）
- Lombok([https://github.com/rzwitserloot/lombok](https://github.com/rzwitserloot/lombok))
- XDoc ([https://gitee.com/treeleaf/xDoc](https://gitee.com/treeleaf/xDoc))
- 其他略

### XDoc 快速文档构建框架
#### 功能
基于java注释生成接口文档
注释支持扩展
接口框架支持扩展
默认支持markdown和离线/在线html等格式的文档
默认支持spring mvc规范
默认支持spring-boot直接内嵌启动

#### 注意事项
在Controller层的@RequestMapping注解中的值，不能以"/"开头，否则生成的自动文档无法进行在线测试  
例如：@RequestMapping("/user")需要写成@RequestMapping("user")  
另外，不能在配置文件中配置spring.resources.add-mappings=false  
生成环境下开启了XDoc，可能会造成jar包稍微大一些  
如Controller中方法参数为POJO对象（例如User对象），则使用XDoc传值测试比较麻烦  
如果需要测试，则进行传JSON字符串，并且在方法的对象前添加RequestBody注解  
```
    @PostMapping("add")
    @ResponseBody
    public ResultModel add(@RequestBody User user) {
        userService.insert(user);
        return ResultModel.success();
    }
```
经过测试发现，该种方法无法实现使用XDoc传输对象的JSON字符串测试。  
XDoc前端的测试会将对象当做String进行传输    
例如：
在测试中 接口地址: user/add，在XDoc接口进行传值
```
{
    "username": "123456",
    "password": "123456",
    "salt": "12555",
    "age": 21,
    "status": 1
}
```
![例如该图](http://blogimg.chenhaoxiang.cn/18-9-11/27273170.jpg)  

在XDoc的测试中，会进行一个赋值，并且将参数名称带上，作为key=value传递到Controller层的方法上。  
那么在fastJson进行解析的时候得到的字符串是：
```
user={    "username": "123456",    "password": "123456",    "salt": "12555",    "age": 21,    "status": 1}
```
所以会出现下面的异常  
```
nested exception is com.alibaba.fastjson.JSONException: syntax error, expect {, actual ident, pos 0, fastjson-version 1.2.49
```

暂时未进行XDoc框架的其他Bug测试，如果后期有时间，可能会对于XDoc进行一些改造  

## 访问
本地运行项目后，直接在浏览器中输入[http://localhost:8080/xdoc/index.html](http://localhost:8080/xdoc/index.html)访问即可  
![演示](http://blogimg.chenhaoxiang.cn/18-9-11/60930327.jpg)  
**点击最右边的测试**  
![演示](http://blogimg.chenhaoxiang.cn/18-9-11/36345891.jpg)   
**可输入参数进行测试**


## 参考项目

[spring-boot-api-project-seed](https://github.com/lihengming/spring-boot-api-project-seed)

## 其他
如果需要新功能，欢迎大家提Issues，会考虑进行加入。
感谢大家 [Star](https://github.com/chenhaoxiang/spring-boot-quick-project-all/stargazers) & [Fork](https://github.com/chenhaoxiang/spring-boot-quick-project-all/network/members) 的支持。

