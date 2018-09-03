
## 简介
spring-boot-quick-project-fars全名为spring-boot-quick-project-front-and-rear-separation  
是一基于SpringBoot,MyBatis等开源框架快速构建CSD（Controller-Service-DAO）层的项目，用于快速搭建中小型的API、RESTful API项目。使用简单，运行稳定快捷，让我们摆脱重复的劳动，专注业务代码的编写。  
下面是一个简单的视频Demo，基于本项目可以在短短几分钟内实现一套简单的API，并运行提供增删改查服务。    

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
- Controller模板默认提供POST和RESTful两套，根据需求在```application-dev.properties```配置文件中自己配置`project.controller.ftl.model`，默认使用POST方式的模板。
- 代码模板可根据实际项目的需求来扩展，由于每个公司业务都不太一样，所以只提供了一些比较基础、通用的模板，**主要是提供一个思路**来减少重复代码的编写。
- 在实际项目的使用中，可以根据公司业务的抽象编写大量的模板。另外，使用模板也有助于保持团队代码风格的统一 

 
## 快速开始
1. 克隆项目到本地
2. 对```src/resoucres```下的```application-dev.properties```进行配置，主要是JDBC，项目包名等配置
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
- Spring Boot（[项目地址](https://github.com/spring-projects/spring-boot)）
- MyBatis（[文档地址](http://www.mybatis.org/mybatis-3/zh/index.html)）
- MyBatisb通用Mapper插件（[文档地址](https://mapperhelper.github.io/docs/)）
- MyBatis PageHelper分页插件（[文档地址](https://pagehelper.github.io/)）
- Druid（[项目地址](https://github.com/alibaba/druid)）
- Fastjson（[项目地址](https://github.com/alibaba/fastjson)）
- Lombok([项目地址](https://github.com/rzwitserloot/lombok))
- 其他略

## 其他
如果需要新功能，欢迎大家提Issues，会考虑进行加入。
感谢大家 [Star](https://github.com/chenhaoxiang/spring-boot-quick-project-all/stargazers) & [Fork](https://github.com/chenhaoxiang/spring-boot-quick-project-all/network/members) 的支持。

