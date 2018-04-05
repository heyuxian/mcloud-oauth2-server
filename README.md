# MCloud - OAuth2 认证中心
[![Build Status](https://www.travis-ci.org/heyuxian/mcloud-oauth2-server.svg?branch=master)](https://www.travis-ci.org/heyuxian/mcloud-oauth2-server)
[![Coverage Status](https://coveralls.io/repos/github/heyuxian/mcloud-oauth2-server/badge.svg?branch=master)](https://coveralls.io/github/heyuxian/mcloud-oauth2-server?branch=master)
[![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](https://opensource.org/licenses/Apache-2.0)


# Deprecated
> 认证模块使用  [keycloak](http://www.keycloak.org/) 替代

## 简介

`mcloud-oauth-server` 基于**Spring OAuth2**,实现了**OAuth2**认证服务器以及资源服务器，并以 **Restful API** 的方式提供了**OAuth** 客户端以及用户的管理功能。

项目中主要使用了以下技术：

- **Java8**
- **Spring 相关** Spring, Spring Mvc, Spring Cloud, Spring data jpa, Spring Boot,Hibernate
- **mapstruct** 主要用于DTO 与 Entity 之间的转换
- **flywaydb** 以版本化的方式管理数据库脚本
- **thymeleaf** 模板框架，用于实现后台管理界面
- **Redis** 主要用作缓存实现 (暂未实现)
- **lombok** 

## 认证流程

**OAuth2**认证流程可参考博客 [理解OAuth 2.0](http://www.ruanyifeng.com/blog/2014/05/oauth_2_0.html) ，此处不再赘述。

## Features

- **认证服务器** 使用Spring Security 结合 JWT Token 实现认证服务，提供了功能完整的 OAuth2 认证服务器。 

- **资源服务器** 实现了以下两种方式进行OAuth2 相关资源的管理

  - 以 RestAPI 形式提供服务来管理资源：`http://localhost:8043/uaa/swagger-ui.html`
  - 以 Thymeleaf + [AdminBSBMaterialDesign](https://github.com/gurayyarar/AdminBSBMaterialDesign) 实现的管理端:

  ​

  **用户端**

  ![image](https://user-images.githubusercontent.com/30259465/33719089-a021befc-db9a-11e7-9a59-179f531dcfe4.png)


**管理员**


  ![image](https://user-images.githubusercontent.com/30259465/33719145-c7c0af54-db9a-11e7-8c52-f92df2cdd7a1.png)  

## 快速使用

**创建数据库**

请使用 mysql 客户端或是其他你喜欢的工具创建数据库，默认的数据库名称为 `db_oauth`

**使用flywaydb初始化数据库**

修改 `pom.xml`中 **flywaydb** 插件的数据库名，用户名以及密码：

**pom.xml**

```xml
<plugin>
  <groupId>org.flywaydb</groupId>
  <artifactId>flyway-maven-plugin</artifactId>
  <version>4.2.0</version>
  <configuration>
    <user>root</user>
    <password>你的数据库密码</password>
    <driver>com.mysql.jdbc.Driver</driver>
    <url>jdbc:mysql://localhost:3306/数据库名称</url>
  </configuration>
</plugin>
```

修改相关配置之后，请在项目根目录下执行maven命令:

```
mvn flyway:clean flyway:migrate
```

修改项目的数据库配置：

**application.yml**

```yaml
spring:
  datasource:
    url: jdbc:mysql://localhost/数据库名称
    username: 用户
    password: 密码
    driver-class-name: com.mysql.jdbc.Driver
```

**启动OAuth2认证服务**

所有准备工作已经完成，现在，让我们启动服务,在项目根目录下运行maven命令：

```
mvn clean install
mvn spring-boot:run
```

如果你使用的是 Idea 或 eclipse 开发，请直接运行 **OAuthServerApplication** 这个方法即可启动服务。

**访问地址**

```
http://localhost:8043/uaa/swagger-ui.html
```

![uaa](https://user-images.githubusercontent.com/30259465/31441550-16f2053e-aec6-11e7-9568-93cd35dbc1dd.png)

## 使用 Postman 调试API

**前提：**

了解Postman并且安装了 **Chrome** 的 **Postman** 插件

首先自然是启动服务，然后我们在Postman中请求 `http://localhost:8043/uaa/api/v1/users/me` ，此时因为还未进行认证，所以服务器返回的是 `401` 的状态码：

![OAuth fail](https://user-images.githubusercontent.com/30259465/31854106-e2270cf6-b6c6-11e7-91e3-f66ec6fef9fd.png)

现在我们通过Postman 自带的OAuth认证功能进行认证：

![qq 20171022011928](https://user-images.githubusercontent.com/30259465/31854237-11da1e38-b6c8-11e7-90b0-40dc54325b67.png)

![qq 20171022012054](https://user-images.githubusercontent.com/30259465/31854178-c1ad282e-b6c7-11e7-95a8-e2b5b006fcab.png)

相关项：

- **Token Name** access_token 
- **Auth URL**: http://localhost:8043/uaa/oauth/authorize
- **Access Token URL**: http://localhost:8043/uaa/oauth/token
- **Client ID**: mcloud-blog
- **Client Secret**: 123456
- **Scope**:  可以为空，此处不填
- **Grant Type**: Authorization code

![qq 20171126221612](https://user-images.githubusercontent.com/30259465/33240822-6c0d75ee-d2f7-11e7-810c-5dd523714c86.jpg)

默认用户：

- **用户名** mcloud-user
- **密码** 123456

![qq 20171022012701](https://user-images.githubusercontent.com/30259465/31854253-51b0a55e-b6c8-11e7-8415-d0ff4a242a85.png)



当我们授权之后，就可以得到 **access_token**，此时选中并点击 Use Token，然后我们再次访问之前无权限的 API

![qq 20171022013209](https://user-images.githubusercontent.com/30259465/31854294-d78b8fae-b6c8-11e7-8c77-46878e9159ef.png)

服务器已成功返回 `200` 的状态码，并返回了用户列表。

## 其他

1. 因本项目使用了 **lombok** 进行开发，在开发之前需要安装插件，若使用的是 **IDEA**  进行开发，请自行搜索并安装 **lombok** 插件，否则会导致编译错误
2. 若是进行二次开发，请替换 `keystore.jks` **（非常重要）** ,否则会导致安全风险，`keystore.jks` 可使用jdk 工具生成，也可自行使用其他工具生成非对称密钥对。

## 问题及建议

如果你有任何好的意见以及建议，请提 [Issue](https://github.com/heyuxian/mcloud-oauth2-server/issues/new)。

如果你觉得此项目对你有所帮助，欢迎点赞支持。
