# MCloud - OAuth2 认证中心
[![Build Status](https://www.travis-ci.org/heyuxian/mcloud-oauth2-server.svg?branch=master)](https://www.travis-ci.org/heyuxian/mcloud-oauth2-server)
[![Coverage Status](https://coveralls.io/repos/github/heyuxian/mcloud-oauth2-server/badge.svg?branch=master)](https://coveralls.io/github/heyuxian/mcloud-oauth2-server?branch=master)
## 简介

`mcloud-oauth-server` 基于**Spring OAuth2**,实现了**OAuth2**认证服务器以及资源服务器，并以 **Restful API** 的方式提供了**OAuth** 客户端以及用户的管理功能。项目中主要使用了以下技术：

- **Java8**
- **Spring 相关** Spring, Spring Mvc, Spring Cloud, Spring data jpa, Spring Boot,Hibernate
- **mapstruct** 主要用于DTO 与 Entity 之间的转换
- **flywaydb** 以版本化的方式管理数据库脚本
- **thymeleaf** 模板框架，主要用于登陆界面以及OAuth2认证页面
- **Redis** 主要用作缓存实现 (暂未实现)
- **lombok** 主要用于简化 **Javabean** 开发，只需使用注解即可生成 **Javabean** 的 get/set/equals 等方法

## 认证流程

**OAuth2**认证流程可参考博客 [理解OAuth 2.0](http://www.ruanyifeng.com/blog/2014/05/oauth_2_0.html) ，此处不再赘述。

## 具体实现

系统实现了OAuth2 **认证服务器** 以及 **资源管理器**

- **认证服务器** 主要进行用户权限的鉴定以及 token的发放，token是使用JWT Token的形式进行签名及验证，并且由于使用的是非对称方式进行 **access_token** 的签名，故 **资源服务器** 只需知道所用的公钥即可验证**access_token** ，减少了 **资源服务器** 与 **认证服务器** 之间的通信。
- **资源服务器** 主要提供了用于管理用户以及OAuth客户端的**Rest API** ，具体请查看 swagger 文档：`http://localhost:8043/uaa/swagger-ui.html`

## 快速使用

> 本项目属于[ MCloud](https://github.com/heyuxian/mcloud) 系列中的OAuth2认证服务，故项目中添加了Spring Cloud的依赖，项目启动时会自动在 [mcloud-eureka](https://github.com/heyuxian/mcloud-eureka) 服务发现与注册中心注册，所以在启动此项目之前，需要先启动 [mcloud-eureka](https://github.com/heyuxian/mcloud-eureka) 服务，**若不希望项目在注册中心注册，请将以下代码注释或是直接删除：**

**pom.xml**

```xml
<!--这个依赖用于注册Eureka，如不需要，请删除或注释-->
<dependency>
  <groupId>org.springframework.cloud</groupId>
  <artifactId>spring-cloud-starter-eureka</artifactId>
</dependency>
```

**OAuthServerApplication**

```
//此注解是用于添加 EurekaClient 支持，如不需要Eureka支持，则请删除
@EnableEurekaClient
```

**创建数据库**

请使用 mysql 客户端或是其他你喜欢的工具创建数据库，默认的数据库名称为 `db_oauth`

**执行初始化脚本**

因为项目使用了**flywaydb** 进行数据库脚本的管理，所以还需修改 `pom.xml`中 **flywaydb** 插件的数据库名，用户名以及密码：

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
mvn flyway:migrate
```

至此，数据库已成功创建，最后还需修改项目的数据库配置：

**application.yml**

```yaml
spring:
  datasource:
    #TODO 根据每个项目自行设置
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

首先自然是启动服务，然后我们在Postman中请求 `http://localhost:8043/uaa/api/v1/users/` ，此时因为还未进行认证，所以服务器返回的是 `401` 的状态码：

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

![qq 20171022012531](https://user-images.githubusercontent.com/30259465/31854246-34a722bc-b6c8-11e7-9815-8e85822f2352.png)

默认用户：

- **用户名** user
- **密码** 123456

![qq 20171022012701](https://user-images.githubusercontent.com/30259465/31854253-51b0a55e-b6c8-11e7-8415-d0ff4a242a85.png)



当我们授权之后，就可以得到 **access_token**，此时选中并点击 Use Token，然后我们再次访问之前无权限的 API

![qq 20171022013209](https://user-images.githubusercontent.com/30259465/31854294-d78b8fae-b6c8-11e7-8c77-46878e9159ef.png)

服务器已成功返回 `200` 的状态码，并返回了用户列表。

## 其他

1. 因本项目使用了 **lombok** 进行开发，在开发之前需要安装插件，若使用的是 **IDEA**  进行开发，请自行搜索并安装 **lombok** 插件，否则会导致编译错误
2. 若是进行二次开发，请替换 `keystore.jks` **（非常重要）** ,否则会导致安全风险，`keystore.jks` 可使用jdk 工具生成，也可自行使用其他工具生成非对称密钥对。

## 意见及建议

如果你有任何好的意见以及建议，请提Issue给我，如果使用的是码云，可直接发表评论或是私信给我。

最后，如果你觉得此项目对你有所帮助，欢迎点赞支持，若是你愿意参与到项目中,请fork以及提PR给我。

## License

```
Copyright 2017 http://www.javaroad.me

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```