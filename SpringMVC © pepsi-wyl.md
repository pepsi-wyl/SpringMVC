# 简介
### 什么是MVC

MVC是一种软件架构的思想，将软件按照模型、视图、控制器来划分

M：Model，模型层，指工程中的JavaBean，作用是处理数据
JavaBean分为两类：

- 一类称为实体类Bean：专门存储业务数据的，如 Student、User 等
- 一类称为业务处理 Bean：指 Service 或 Dao 对象，专门用于处理业务逻辑和数据访问。

V：View，视图层，指工程中的html或jsp等页面，作用是与用户进行交互，展示数据
C：Controller，控制层，指工程中的servlet，作用是接收请求和响应浏览器

MVC的工作流程：
    用户通过视图层发送请求到服务器，在服务器中请求被Controller接收，Controller调用相应的Model层处理请求，处理完毕将结果返回到Controller，Controller再根据请求处理的结果找到相应的View视图，渲染数据后最终响应给浏览器
### 什么是SpringMVC
SpringMVC是Spring的一个后续产品，是Spring的一个子项目
SpringMVC 是 Spring 为表述层开发提供的一整套完备的解决方案。在表述层框架历经 Strust、WebWork、Strust2 等诸多产品的历代更迭之后，目前业界普遍选择了 SpringMVC 作为 Java EE 项目表述层开发的**首选方案**。
> 注：三层架构分为表述层（或表示层）、业务逻辑层、数据访问层，表述层表示前台页面和后台servlet

### SpringMVC的特点

- **Spring 家族原生产品**，与 IOC 容器等基础设施无缝对接
- **基于原生的Servlet**，通过了功能强大的**前端控制器DispatcherServlet**，对请求和响应进行统一处理
- 表述层各细分领域需要解决的问题**全方位覆盖**，提供**全面解决方案**
- **代码清新简洁**，大幅度提升开发效率
- 内部组件化程度高，可插拔式组件**即插即用**，想要什么功能配置相应组件即可
- **性能卓著**，尤其适合现代大型、超大型互联网项目要求
# 入门
### 准备工作
#### 创建maven工程
#### 添加web模块
![image.png](https://cdn.nlark.com/yuque/0/2022/png/23219042/1643199930360-ac9ded1f-d62d-4fe5-b969-101696ab7b12.png#clientId=u03158935-6000-4&crop=0&crop=0&crop=1&crop=1&from=paste&height=207&id=u4a0f082e&margin=%5Bobject%20Object%5D&name=image.png&originHeight=207&originWidth=227&originalType=binary&ratio=1&rotation=0&showTitle=false&size=5885&status=done&style=none&taskId=u97e21f3f-1632-4806-98f1-5c3fb504c6c&title=&width=227)
#### 打包方式：war
![image.png](https://cdn.nlark.com/yuque/0/2022/png/23219042/1643200109143-86239539-2338-4e3b-9c6a-53825fe9ba50.png#clientId=u03158935-6000-4&crop=0&crop=0&crop=1&crop=1&from=paste&height=29&id=u07c3e19d&margin=%5Bobject%20Object%5D&name=image.png&originHeight=29&originWidth=276&originalType=binary&ratio=1&rotation=0&showTitle=false&size=10985&status=done&style=none&taskId=u817b7d71-48f0-4db9-af2d-313e930539a&title=&width=276)
#### 配置Tomcat服务器
#### 引入依赖
```xml
<dependencies>
    <!--junit-jar包-->
        <dependency>
            <groupId>junit</groupId>
            <artifactId>junit</artifactId>
            <version>4.13.2</version>
            <scope>test</scope>
        </dependency>

        <!--logback日志-->
        <dependency>
            <groupId>ch.qos.logback</groupId>
            <artifactId>logback-classic</artifactId>
            <version>1.2.10</version>
        </dependency>

        <!--Lombok-->
        <dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
            <version>1.18.22</version>
        </dependency>

        <!--SpringMVC-->
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-webmvc</artifactId>
            <version>5.3.15</version>
        </dependency>

        <!-- Servlet-api-->
        <dependency>
            <groupId>javax.servlet</groupId>
            <artifactId>javax.servlet-api</artifactId>
            <version>4.0.1</version>
        </dependency>

        <!--thymeleaf模板引擎 替代jsp开发-->
        <dependency>
            <groupId>org.thymeleaf</groupId>
            <artifactId>thymeleaf-spring5</artifactId>
            <version>3.0.14.RELEASE</version>
        </dependency>
</dependencies>
```

注：由于 Maven 的传递性，我们不必将所有需要的包全部配置依赖，而是配置最顶端的依赖，其他靠传递性导入。
依赖添加失败 需要手动添加
![image.png](https://cdn.nlark.com/yuque/0/2022/png/23219042/1646132568996-b7e668af-4312-4cf7-b234-7f85b5e2c7dc.png#clientId=uf0da2c38-1ba4-4&crop=0&crop=0&crop=1&crop=1&from=paste&height=476&id=ud6d3dc65&margin=%5Bobject%20Object%5D&name=image.png&originHeight=476&originWidth=285&originalType=binary&ratio=1&rotation=0&showTitle=false&size=19735&status=done&style=none&taskId=u29fc70e0-b4c7-4191-87e1-fb618f5e968&title=&width=285)
### 配置web.xml
注册SpringMVC的前端控制器DispatcherServlet
#### 默认配置方式
此配置作用下，SpringMVC的配置文件默认位于WEB-INF下，默认名称为<servlet-name>-servlet.xml
例如，以下配置所对应SpringMVC的配置文件位于WEB-INF下，文件名为springMVC-servlet.xml
```xml
<!-- 配置SpringMVC的前端控制器，对浏览器发送的请求统一进行处理 -->
<servlet>
    <servlet-name>springMVC</servlet-name>
    <servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
</servlet>
<servlet-mapping>
    <servlet-name>springMVC</servlet-name>
    <!--
        设置springMVC的核心控制器所能处理的请求的请求路径
        /所匹配的请求可以是/login或.html或.js或.css方式的请求路径
        但是/不能匹配.jsp请求路径的请求
    -->
    <url-pattern>/</url-pattern>
</servlet-mapping>
```
#### 扩展配置方式
通过init-param标签设置SpringMVC配置文件的位置和名称
通过load-on-startup标签设置SpringMVC前端控制器DispatcherServlet的初始化时间

```xml
<!--配置SpringMVC的前端控制器，对浏览器发送的请求统一进行处理-->
<servlet>
    <servlet-name>springMVC</servlet-name>
    <servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
    
    <!-- 通过初始化参数指定SpringMVC配置文件的位置和名称 -->
    <init-param>
        <!-- contextConfigLocation为固定值 -->
        <param-name>contextConfigLocation</param-name>
        <!-- 使用classpath:表示从类路径查找配置文件，例如maven工程中的src/main/resources -->
        <param-value>classpath:springMVC.xml</param-value>
    </init-param>
  
    <!-- 
 		作为框架的核心组件，在启动过程中有大量的初始化操作要做
		而这些操作放在第一次请求时才执行会严重影响访问速度
		因此需要通过此标签将启动控制DispatcherServlet的初始化时间提前到服务器启动时
	  -->
    <load-on-startup>1</load-on-startup>
  
</servlet>
<servlet-mapping>
    <servlet-name>springMVC</servlet-name>
  
    <!--
        设置springMVC的核心控制器所能处理的请求的请求路径
        /所匹配的请求可以是/login或.html或.js或.css方式的请求路径
        但是/不能匹配.jsp请求路径的请求
        /匹配所有 不包括jsp  /*匹配所有 
    -->
    <url-pattern>/</url-pattern>
</servlet-mapping>




<!--配置springMVC的编码过滤器-->
<filter>
    <filter-name>CharacterEncodingFilter</filter-name>
    <filter-class>org.springframework.web.filter.CharacterEncodingFilter</filter-class>
    <init-param>
        <param-name>encoding</param-name>
        <param-value>UTF-8</param-value>
    </init-param>
    <init-param>
        <param-name>forceResponseEncoding</param-name>
        <param-value>true</param-value>
    </init-param>
</filter>
<filter-mapping>
    <filter-name>CharacterEncodingFilter</filter-name>
    <url-pattern>/*</url-pattern>
</filter-mapping>




<!--配置HiddenHttpMethodFilter 请求过滤器-->
<filter>
    <filter-name>HiddenHttpMethodFilter</filter-name>
    <filter-class>org.springframework.web.filter.HiddenHttpMethodFilter</filter-class>
</filter>
<filter-mapping>
    <filter-name>HiddenHttpMethodFilter</filter-name>
    <url-pattern>/*</url-pattern>
</filter-mapping>
```

> 注： <url-pattern>标签中使用  /和/*  的区别：
> - /所匹配的请求可以是/login或.html或.js或.css方式的请求路径，但是/不能匹配.jsp请求路径的请求
> 
          因此就可以避免在访问jsp页面时，该请求被DispatcherServlet处理，从而找不到相应的页面
> - /*则能够匹配所有请求，例如在使用过滤器时，若需要对所有请求进行过滤，就需要使用/*的写法



  注：  
         classpath 路径在每个[J2ee](https://so.csdn.net/so/search?q=J2ee&spm=1001.2101.3001.7020)项目中都会用到，即WEB-INF下面的classes目录，所有src目录下面的java、xml、properties等文件编译后都会在此，在开发时常将相应的xml配置文件放于src或其子目录下；
    引用classpath路径下的文件，只需在文件名前加classpath:(需保证该文件确实位于classpath路径下);
![image.png](https://cdn.nlark.com/yuque/0/2022/png/23219042/1645582904575-b9b37779-8a15-469f-8d96-0a395fef4d03.png#clientId=uacfad287-749d-4&crop=0&crop=0&crop=1&crop=1&from=paste&height=286&id=u91c882cd&margin=%5Bobject%20Object%5D&name=image.png&originHeight=286&originWidth=258&originalType=binary&ratio=1&rotation=0&showTitle=false&size=78923&status=done&style=none&taskId=uc32bbdb2-5910-4e3b-bf77-ab3286747df&title=&width=258)
### 配置springMVC.xml
```xml
<!-- 自动扫描包 -->
<context:component-scan base-package="com.atguigu.mvc.controller"/>

<!-- 配置Thymeleaf视图解析器 -->
<bean id="viewResolver" class="org.thymeleaf.spring5.view.ThymeleafViewResolver">
    <property name="order" value="1"/>  // 视图解析器 优先级
    <property name="characterEncoding" value="UTF-8"/> // 解析编码
  
    <property name="templateEngine">
        <bean class="org.thymeleaf.spring5.SpringTemplateEngine">
            <property name="templateResolver">
                <bean class="org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver">
    
                    <!-- 视图前缀 -->
                    <property name="prefix" value="/WEB-INF/templates/"/>
    
                    <!-- 视图后缀 -->
                    <property name="suffix" value=".html"/>
                  
                    <property name="templateMode" value="HTML5"/>        // 模板模型
                    <property name="characterEncoding" value="UTF-8" />  // 页面编码
                </bean>
            </property>
        </bean>
    </property>
  
</bean>

<!-- 
  处理静态资源，例如html、js、css、jpg
  若只设置该标签，则只能访问静态资源，其他请求则无法访问
  此时必须设置<mvc:annotation-driven/>解决问题
 -->
<mvc:default-servlet-handler/>

<!-- 开启mvc注解驱动 -->
<mvc:annotation-driven>
    <mvc:message-converters>
        <!-- 处理响应中文内容乱码 -->
        <bean class="org.springframework.http.converter.StringHttpMessageConverter">
            <property name="defaultCharset" value="UTF-8" />
            <property name="supportedMediaTypes">
                <list>
                    <value>text/html</value>
                    <value>application/json</value>
                </list>
            </property>
        </bean>
    </mvc:message-converters>
</mvc:annotation-driven>
```
### 创建请求控制器
由于前端控制器对浏览器发送的请求进行了统一的处理，但是具体的请求有不同的处理过程，因此需要创建处理具体请求的类，    请求控制器
请求控制器中每一个处理请求的方法成为控制器方法
因为SpringMVC的控制器由一个POJO（普通的Java类）担任，因此需要通过@Controller注解将其标识为一个控制层组件，交给Spring的IoC容器管理，此时SpringMVC才能够识别控制器的存在
```java
@Controller
public class HelloController {
    
}
```
### 测试
#### 实现对首页的访问
在请求控制器中创建处理请求的方法
```java
// @RequestMapping注解：处理请求和控制器方法之间的映射关系
// @RequestMapping注解的value属性可以通过请求地址匹配请求，/表示的当前工程的上下文路径
// localhost:8080/springMVC/
@RequestMapping("/")
public String index() {
    //设置视图名称
    return "index";
}
```
#### 通过超链接跳转到指定页面
在主页index.html中设置超链接
```html
<!DOCTYPE html>
<html lang="en" xmlns:th="http://www.thymeleaf.org">  <!--thymeleaf的命名空间-->

<head>
    <meta charset="UTF-8">
    <title>首页</title>
</head>
  
<body>
    <h1>首页</h1>
    <a th:href="@{/hello}">HelloWorld</a><br/>
</body>

</html>
```
在请求控制器中创建处理请求的方法
```java
@RequestMapping("/hello")
public String HelloWorld() {
    return "target";
}
```
### 总结
浏览器发送请求，若请求地址符合前端控制器的url-pattern，该请求就会被前端控制器DispatcherServlet处理。
前端控制器会读取SpringMVC的核心配置文件，通过扫描组件找到控制器，将请求地址和控制@RequestMapping注解的value属性值进行匹配，若匹配成功，该注解所标识的控制器方法就是处理请求的方法。
处理请求的方法需要返回一个字符串类型的视图名称，该视图名称会被视图解析器解析，加上前缀和后缀组成视图的路径，通过Thymeleaf对视图进行渲染，最终转发到视图所对应页面
# 路由
### 源码
RequestMapping GetMapping PostMapping PutMapping DeleteMapping
```java
@Target({ElementType.METHOD, ElementType.TYPE})   // 可以在方法和类的声明中使用
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Mapping
public @interface RequestMapping {

    String name() default "";     // 指定映射的名称

    @AliasFor("path")
    String[] value() default {};  // 指定请求路径的地址  别名为path 单独使用可以省略value

    @AliasFor("value")
    String[] path() default {};   // 指定请求路径的地址  别名为value

	// 指定请求的方式，是一个RequsetMethod数组，可以配置多个方法
    RequestMethod[] method() default {};
	
	// 指定参数的类型
    String[] params() default {};
	
	// 指定请求头内容
    String[] headers() default {};
	
	// 指定数据请求的格式
    String[] consumes() default {};
	
	// 指定返回的内容类型
    String[] produces() default {};
}

/**
 * 指定 http 请求的类型如GET, POST, HEAD, OPTIONS, PUT, PATCH, DELETE, TRACE. 
 */
public enum RequestMethod {
    GET,
    HEAD,
    POST,
    PUT,
    PATCH,
    DELETE,
    OPTIONS,
    TRACE;

    private RequestMethod() {
    }
}
```
### 功能

- 标识 http 请求地址与 Controller 类的方法之间的映射

### 位置
#### 源码
```java
@Target({ElementType.TYPE, ElementType.METHOD})  // 可以在方法和类的声明中使用
```
#### 使用

- ElementType.TYPE         类：   设置映射请求请求路径的初始信息
- ElementType.METHOD  方法：设置映射请求请求路径的具体信息

```java
/**
 * @author by pepsi-wyl
 * @date 2022-01-28 13:23
 */

@Controller(value = "userController")

@RequestMapping("/user")                 // 类上    value省略
public class UserController {

    // http://localhost/MVC_02/user/getUserList
    @ResponseBody  // 返回JSON字符串
    @RequestMapping("/getUserList")      // 方法上  value省略
    public String getUserList() {
        return "userList";
    }

}
```
### name属性
#### 源码
```java
String name() default "";     // 指定映射的名称
```
#### 使用
相当于方法体的注释，使得方法更易于理解
```java
@RequestMapping("/user")
public class UserController {

    // http://localhost/MVC_02/user/login
    @ResponseBody       // 返回JSON字符串
    @RequestMapping(name = "用户登录", value = "/login")
    public String login() {
        return "success";
    }

}
```
### value和path属性
#### 源码
```java
@AliasFor("path")
String[] value() default {};  // 指定请求路径的地址  value可以省略

@AliasFor("value")
String[] path() default {};   // 指定请求路径的地址
```
#### 使用

- @RequestMapping注解至少通过请求地址匹配请求映射 （至少得有请求路径）
- value是默认属性，只有value属性时可以省略属性名称
- path 和 value 互相引用，使用一致，两者都是通过请求的请求地址匹配请求映射，使用一个即可（使用重复会报错）
- 都是字符串类型数组，表示该请求映射能够匹配多个请求地址所对应的请求
- 支持通配符匹配 "XXX/*"

```java
    @RequestMapping("/login")
    @RequestMapping({"/login", "/userLogin"})
    @RequestMapping("login/*")        // 通配符
 
    //////////////////////省略时候是value属性////////////

    @RequestMapping(value = "/login" )
    @RequestMapping(value = {"/login", "/userLogin"})
    @RequestMapping(value="login/*")   // 通配符

    @ResponseBody       // 返回JSON字符串
    @RequestMapping(path = "/login" )
    @RequestMapping(path = {"/login", "/userLogin"})
    @RequestMapping(path = "/login/*" ) // 通配符

    // Different @AliasFor mirror values for annotation [org.springframework.web.bind.annotation.RequestMapping] declared on controller.UserController.login(); attribute 'path' and its alias 'value' are declared with values of [{/login, /userLogin}] and [{Login, UserLogin}].
    @RequestMapping(path = {"/login", "/userLogin"},value = {"Login","UserLogin"})
```

### method属性
#### 源码
```java
// 指定请求的方式，是一个RequsetMethod数组，可以配置多个方法
RequestMethod[] method() default {};

public enum RequestMethod {
    GET,
    HEAD,
    POST,
    PUT,
    PATCH,
    DELETE,
    OPTIONS,
    TRACE;
    private RequestMethod() {
    }
}
```
#### 使用

- 如果没有method属性，支持所有HTTP请求
- 通过请求的请求方式匹配请求映射 
- 路径映射成功，method请求方式不满足浏览器报错 405'XXX' not supported
- 数组类型，表示该请求映射能够匹配多种请求方式的请求

```java
@RequestMapping(name = "用户登陆", value = "login")                            // 支持全部的HTTP请求
@RequestMapping(name = "用户登陆", value = "login", method = RequestMethod.GET) // 支持GET HTTP请求
@RequestMapping(name = "用户登陆", value = "login", method = {RequestMethod.GET,RequestMethod.POST}) // 支持GET POST HTTP请求
```
> 注：
> 对于处理指定请求方式的控制器方法，SpringMVC中提供了@RequestMapping的派生注解
>   处理get请求的映射-->      @GetMapping  
>   处理post请求的映射-->    @PostMapping  
>   处理put请求的映射-->      @PutMapping  
>   处理delete请求的映射--> @DeleteMapping  
>  
> 
> - 常用的请求方式有get，post，put，delete
> - 目前浏览器只支持get和post，若在form表单提交时，为method设置了其他请求方式的字符串（put或delete），则按照默认的请求方式get处理

### params属性（了解）
#### 源码
```java
// 指定参数的类型
String[] params() default {};
```
#### 使用

- 通过请求的请求参数匹配请求映射
- 该属性指定，请求中必须包含params属性规定的参数时，才能执行该请求
- 字符串类型的数组，可以通过四种表达式设置请求参数和请求映射的匹配关系
- params属性匹配失败，页面报错400：Parameter conditions XXXXXX......
```java
"flag"：要求请求映射所匹配的请求必须携带flag请求参数
"!flag"：要求请求映射所匹配的请求必须不能携带flag请求参数
"flag=true"：要求请求映射所匹配的请求必须携带flag请求参数且flag=true
"flag!=true"：要求请求映射所匹配的请求必须携带flag请求参数但是flag!=true

@RequestMapping(value = "/login",params = "flag") // 必须包含flag参数才能执行该请求，flag参数值不做要求
@ResponseBody
public String login() {
	return "success";
}

@RequestMapping(value = "/login",params = "flag=true")// 说明请求中必须包含flag参数，而且参数值必须为true才能执行该请求
@ResponseBody
public String login() {
	return "success";
}
```
```java
? 传参themlef会报错 但是不影响正常使用 采用括号则不会报错
<a th:href="@{/login(username='admin',password=123456)">params属性</a><br>
```
### headers属性（了解）
#### 源码
```java
// 指定请求头内容
String[] headers() default {};
```
#### 使用

- 用于HTTP协义交互的信息被称为HTTP报文，客户端发送的HTTP报文被称为请求报文，服务器发回给客户端的HTTP报文称为响应报文，报文由报文头部和报文体组成。
- 请求头部（Request Headers）：请求头包含许多有关客户端环境和请求正文的信息，例如浏览器支持的语言、请求的服务器地址、客户端的操作系统等。
- 响应头部（Rsponse Headers）：响应头也包含许多有用的信息，包括服务器类型、日期、响应内容的类型及编码，响应内容的长度等等。

- 通过请求的请求头信息匹配请求映射
- 该属性指定，请求中必须包含某些指定的header值，才能够让该方法处理请求
- 字符串类型的数组，可以通过四种表达式设置请求头信息和请求映射的匹配关系
- 不满足headers属性，页面显示404错误，即资源未找到
```java
"header"：要求请求映射所匹配的请求必须携带header请求头信息
"!header"：要求请求映射所匹配的请求必须不能携带header请求头信息
"header=value"：要求请求映射所匹配的请求必须携带header请求头信息且header=value
"header!=value"：要求请求映射所匹配的请求必须携带header请求头信息且header!=value

// 表示只接收本机发来的请求
@RequestMapping(path = "/login", headers="Referer=http://localhost:8080")
```
### consumes属性（了解）
#### 源码
```java
// 指定数据请求的格式
String[] consumes() default {};
```
#### 使用

- 处理请求的提交内容类型（Content-Type），例如：application/json、text/html时，才能够让该方法处理请求
```java
@RequestMapping(value = "login",consumes = "application/json")
```
### produces属性（了解）
#### 源码
```java
// 指定返回的内容类型
String[] produces() default {};
```
#### 使用

- 指定返回的内容类型，返回的内容类型必须是request请求头（Accept）中所包含的类型
```java
@RequestMapping(value = "login",produces = "application/json")
@RequestMapping(value = "login",produces = "application/json，charset=utf-8")
```
### 支持ant风格的路径
```java
？：表示任意的单个字符
*：表示任意的0个或多个字符
**：表示任意的一层或多层目录

@RequestMapping(name = "ant", path = "/ant?")    // 任意的单个字符       / : 除外
@RequestMapping(name = "ant", path = "/ant*")    // 任意的0个或多个字符  / : 除外
@RequestMapping(name = "ant", path = "/**/ant")  // 表示任意的一层或多层目录  
                                         注意：在使用**时，只能使用 /**/xxx的方式
```
### 支持路径中的占位符（rest方式重点）
原始方式：/deleteUser?id=1
rest方式：/deleteUser/1

SpringMVC路径中的占位符常用于RESTful风格中，当请求路径中将某些数据通过路径的方式传输到服务器中，就可以在相应的@RequestMapping注解的value属性中通过占位符{xxx}表示传输的数据，在通过@PathVariable注解，将占位符所表示的数据赋值给控制器方法的形参
```html
<a th:href="@{/user/1/admin}">测试路径中的占位符</a><br>
```
```java
@RequestMapping("/user/{id}/{username}")
public String user(@PathVariable("id") String id, @PathVariable("username") String username){
    System.out.println("id:"+id+",username:"+username);
    return "success";
}
// 最终输出的内容为-->id:1,username:admin
```
# 获取请求参数
### 通过ServletAPI获取
将HttpServletRequest作为控制器方法的形参，此时HttpServletRequest类型的参数表示封装了当前请求的请求报文的对象
```java
@ResponseBody       // 返回JSON字符串
@RequestMapping(name = "用户登陆", path = {"/login", "/login/*", "/userLogin"}, method = RequestMethod.GET)
public String login(HttpServletRequest request) {
    String username = request.getParameter("username");
    String password = request.getParameter("password");
    System.out.println("username:"+username+",password:"+password);
    return "success";
}
```
### 通过控制器方法的形参获取请求参数
在控制器方法的形参位置，设置和请求参数同名的形参，当浏览器发送请求，匹配到请求映射时，在DispatcherServlet中就会将请求参数赋值给相应的形参
```java
// http://localhost/MVC_02/user/login?userName=zhazha&password=888888&hobby=a&hobby=b

@ResponseBody       // 返回JSON字符串    
@RequestMapping(name = "用户登陆", path = {"/login", "/login/*", "/userLogin"}, method = RequestMethod.GET)
public String login(String userName, String password, String[] hobby) {
    System.out.println(userName + password + Arrays.toString(hobby));
    return "success";
}
```
> 注：
>  请求参数中有多个同名的请求参数
> - 若使用字符串数组类型的形参，此参数的数组中包含了每一个数据
> - 若使用字符串类型的形参，此参数的值为每个数据中间使用逗号拼接的结果

### @RequestParam  
@RequestParam是将请求参数和控制器方法的形参创建映射关系 （名字不一致）
```java
@AliasFor("name")
String value() default "";     // 形参赋值的请求参数的参数名

@AliasFor("value")
String name() default "";      // 形参赋值的请求参数的参数名

boolean required() default true;  // 设置是否必须传输此请求参数，默认值为true

String defaultValue() default "\n\t\t\n\t\t\n\ue000\ue001\ue002\n\t\t\t\t\n"; // 形参默认值
```
注意：
required为true当前请求必须传输value所指定的请求参数，若没有传输该请求参数，有默认参数值则使用默认参数值，没有则页面报错400：Required String parameter 'xxx'isnot present；
required为false当前请求不是必须传输value所指定的请求参数，若没有传输该请求参数，有默认参数值则使用默认参数值，没有则值为null
defaultValue：只要指定该值，且前端请求参数没有传输或传输的值为""时，即使用默认值为形参赋值
```java
// http://localhost/MVC_02/user/login?userName=zhazha&password=888888&hobby=a&hobby=b
@ResponseBody       // 返回JSON字符串
@RequestMapping(name = "用户登陆", path = {"/login", "/login/*", "/userLogin"}, method = RequestMethod.GET)
public String login(@RequestParam(value = "userName", required = false, defaultValue = "获取值失败") String userName,
                    String password,
                    String[] hobby) {
    System.out.println(userName + password + Arrays.toString(hobby));
    return "success";
}
```
### @RequestHeader  
@RequestHeader是将请求头信息和控制器方法的形参创建映射关系
```java
@AliasFor("name")
String value() default "";    // 形参赋值的请求头参数的参数名

@AliasFor("value")
String name() default "";     // 形参赋值的请求头参数的参数名

boolean required() default true;  // 设置是否必须传输此请求头参数，默认值为true

String defaultValue() default "\n\t\t\n\t\t\n\ue000\ue001\ue002\n\t\t\t\t\n";  //形参默认值
```
```java
@ResponseBody       // 返回JSON字符串
@RequestMapping(name = "Header", path = {"/header"}, method = RequestMethod.GET)
public String login(@RequestHeader(value = "Host", required = false, defaultValue = "获取值失败") String host) {
    System.out.println(host);
    return "success";
}
```
使用注意点 同 @RequestParam  
### @CookieValue  
@CookieValue是将cookie数据和控制器方法的形参创建映射关系
```java
@AliasFor("name")
String value() default "";        // 形参赋值的Cookie参数的参数名

@AliasFor("value")
String name() default "";         // 形参赋值的Cookie参数的参数名

boolean required() default true;  // 设置是否必须传输此Cookie参数，默认值为true

String defaultValue() default "\n\t\t\n\t\t\n\ue000\ue001\ue002\n\t\t\t\t\n";  //形参默认值
```
```java
@ResponseBody       // 返回JSON字符串
@RequestMapping(name = "cookie", path = {"/cookie"}, method = RequestMethod.GET)
public String login(@CookieValue(value = "cookieValue", required = false, defaultValue = "获取值失败") String cookie) {
   System.out.println(cookie);
   return "success";
}
```
使用注意点 同 @RequestParam  
### @PathVariable
@PathVariable 将 URL 中[占位符](https://so.csdn.net/so/search?q=%E5%8D%A0%E4%BD%8D%E7%AC%A6&spm=1001.2101.3001.7020)参数绑定到控制器处理方法的参数中
```java
@Target({ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface PathVariable {
    @AliasFor("name")
    String value() default "";

    @AliasFor("value")
    String name() default "";

    boolean required() default true;
}
```
```java
@ResponseBody       // 返回JSON字符串
@RequestMapping(name = "url", path = {"/url/{userName}/{password}"}, method = RequestMethod.GET)
public String login(@PathVariable(value = "userName") String userName, @PathVariable(value = "password") String password) {
    System.out.println(userName+" "+password);
    return "success";
}
```
### POJO获取请求参数
可以在控制器方法的形参位置设置一个实体类类型的形参，此时若浏览器传输的请求参数的参数名和实体类中的属性名一致，那么请求参数就会为此属性赋值
```html
<form th:action="@{/user/pojo}" method="post">
    用户名：<input type="text" name="userName"><br>
    密码：<input type="password" name="password"><br>
    性别：<input type="radio" name="sex" value="男">男<input type="radio" name="sex" value="女">女<br>
    年龄：<input type="text" name="age"><br>
    邮箱：<input type="text" name="email"><br>
    <input type="submit">
</form>
```
```java
import lombok.*;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * @author by pepsi-wyl
 * @date 2022-02-05 16:30
 */

@Component(value = "user")  // 向IOC容器中注册组件
@Scope(value = "singleton") // 周期为单例

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString

public class User {
    private String userName;
    private String password;
    private String sex;
    private Integer age;
    private String email;
}
```
```java
@Controller(value = "userController")
@RequestMapping("/user")
public class UserController {
    @ResponseBody
    @RequestMapping(name = "pojo传递参数", path = {"/pojo"}, method = {RequestMethod.GET, RequestMethod.POST})
    public String pojo(User user) {
        System.out.println(user);
        return "success";
    }
}
```
### 解决获取请求参数的乱码问题
#### 使用SpringMVC提供的编码过滤器CharacterEncodingFilter
GET
需要配置Tomcat的service.xml 配置文件 (utf-8) 
```xml
<Connector port="8080" protocol="HTTP/1.1"
            connectionTimeout="20000"
            redirectPort="8443"
            URIEncoding="utf-8"
/>
```
POST
web.xml中配置   加载顺序为listener filter servlet   与配置顺序文无关 
```xml
<!--配置springMVC的编码过滤器-->
<filter>
    <filter-name>CharacterEncodingFilter</filter-name>
    <filter-class>org.springframework.web.filter.CharacterEncodingFilter</filter-class>
    <init-param>
        <param-name>encoding</param-name>
        <param-value>UTF-8</param-value>
    </init-param>
    <init-param>
        <param-name>forceResponseEncoding</param-name>
        <param-value>true</param-value>
    </init-param>
</filter>
<filter-mapping>
    <filter-name>CharacterEncodingFilter</filter-name>
    <url-pattern>/*</url-pattern>
</filter-mapping>
```
注意:
> SpringMVC中处理编码的过滤器一定要配置到其他过滤器之前，否则无效

源码分析
```java
@Nullable
private String encoding;
private boolean forceRequestEncoding;
private boolean forceResponseEncoding;

protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
   String encoding = this.getEncoding();
   if (encoding != null) {
        if (this.isForceRequestEncoding() || request.getCharacterEncoding() == null) {
            request.setCharacterEncoding(encoding);
        }

        if (this.isForceResponseEncoding()) {
            response.setCharacterEncoding(encoding);
        }
   }

   filterChain.doFilter(request, response);      // 过滤器链
}
```
#### 自定义过滤器
```java
package filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Map;

/**
 * @author by wyl
 * @date 2021/10/5.16点54分
 */

@SuppressWarnings("all")

public class GenericEncodingFilter implements Filter {

    @Override
    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        //处理response的字符编码
        HttpServletResponse myResponse = (HttpServletResponse) response;
        myResponse.setContentType("text/html;charset=UTF-8");

        // 转型为与协议相关对象
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        // 对request包装增强
        HttpServletRequest myrequest = new MyRequest(httpServletRequest);
        chain.doFilter(myrequest, response);
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

}

//自定义request对象，HttpServletRequest的包装类
class MyRequest extends HttpServletRequestWrapper {

    private HttpServletRequest request;
    //是否编码的标记
    private boolean hasEncode;

    //定义一个可以传入HttpServletRequest对象的构造函数，以便对其进行装饰
    public MyRequest(HttpServletRequest request) {
        super(request);// super必须写
        this.request = request;
    }

    // 对需要增强方法 进行覆盖
    @Override
    public Map getParameterMap() {
        // 先获得请求方式
        String method = request.getMethod();
        if (method.equalsIgnoreCase("post")) {
            // post请求
            try {
                // 处理post乱码
                request.setCharacterEncoding("utf-8");
                return request.getParameterMap();
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        } else if (method.equalsIgnoreCase("get")) {
            // get请求
            Map<String, String[]> parameterMap = request.getParameterMap();
            if (!hasEncode) { // 确保get手动编码逻辑只运行一次
                for (String parameterName : parameterMap.keySet()) {
                    String[] values = parameterMap.get(parameterName);
                    if (values != null) {
                        for (int i = 0; i < values.length; i++) {
                            try {
                                // 处理get乱码
                                values[i] = new String(values[i]
                                        .getBytes("ISO-8859-1"), "utf-8");
                            } catch (UnsupportedEncodingException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
                hasEncode = true;
            }
            return parameterMap;
        }
        return super.getParameterMap();
    }

    //取一个值
    @Override
    public String getParameter(String name) {
        Map<String, String[]> parameterMap = getParameterMap();
        String[] values = parameterMap.get(name);
        if (values == null) {
            return null;
        }
        return values[0]; // 取回参数的第一个值
    }

    //取所有值
    @Override
    public String[] getParameterValues(String name) {
        Map<String, String[]> parameterMap = getParameterMap();
        String[] values = parameterMap.get(name);
        return values;
    }
}

```
```xml
<!--配置自定义的乱码过滤器-->
<filter>
    <filter-name>encoding</filter-name>
    <filter-class>filter.GenericEncodingFilter</filter-class>
</filter>
<filter-mapping>
    <filter-name>encoding</filter-name>
    <url-pattern>/*</url-pattern>
</filter-mapping>
```

# 域对象共享数据
## 四大域对象
   根据有作用范围由小到大：

- page(jsp有效)------》page域指的是pageContext.
- request(一次请求)---》request域request HttpServletContext
- session(一次会话)---》session域session HttpSession
- application(当前web应用)---》application域指的是application  ServletContext；

                之所以他们是域对象，原因是他们都内置了map集合，都有setAttribute和getAttribute方法。
### PageContext域
```java
一、PageContext域
1.生命周期：当对JSP的请求开始，当响应结束时销毁。
           jsp页面被执行，声明周期开始；
           jsp页面执行完毕，声明周期结束；
2.作用范围：整个JSP页面，是四大作用域中最小的一个。
3.作用：
　　　（1）获取其它八大隐式对象，可以认为是一个入口对象。 
　　　（2）获取其所有域中的数据    
pageContext　　操作所有域中属性的方法 #   
public java.lang.Object getAttribute(java.lang.String name,int scope)      　　　　　　　　　　　　　
public void setAttribute(java.lang.String name, java.lang.Object value,int scope)    
public void removeAttribute(java.lang.String name,int scope)          
pageContext 中代表域的常量    
　　　PageContext.APPLICATION_SCOPE    
　　　PageContext.SESSION_SCOPE    
　　　PageContext.REQUEST_SCOPE    
　　　PageContext.PAGE_SCOPE          
findAttribute方法,在四大域中搜寻属性，搜寻的顺序是page域、request域、session域、application域，从小域到大域开始搜索，如果搜索到就直接获取该值，如果所有域中都找不到，返回一个null(与el表达式不同，此处返回null，对网页是不友好的)       
　　　（3）跳转到其他资源      其身上提供了forward和include方法，简化重定向和转发的操作
```
### Request域
```java
二、Request域
1.生命周期：
    在Service方法调用前由服务器创建，传入service方法。整个请求结束，request生命结束。
    用户发送一个请求，开始，服务器返回响应，请求结束，生命周期结束；
2.作用范围：整个请求链（请求转发也存在）
3.作用：在整个请求链中共享数据，经常用到：在servlet中处理好的数据交给JSP显示，此时参数就可以放在Request域中。
```
### HttpSession域
```java
三、HttpSession域
1.生命周期：
    在第一次调用request.getSession()方法时，服务器会检查是否已经有对应的session，如果没有就在内存中创建一个session并返回。
（1）当一段时间内session没有被使用（默认为30分钟），则服务器会销毁该session。
（2）如果服务器非正常关闭，没有到期的session也会跟着销毁。
（3）如果调用session提供的invalidate()，可以立即销毁session。
用户打开浏览器访问，创建session（开始），session超时或者被声明失效，该对象生命周期结束；
2.作用范围：一次会话。
HttpSession 在服务器中，为浏览器创建独一无二的内存空间，在其中保存会话相关的信息
注意：服务器正常关闭，再启动，Session对象会进行钝化和活化操作。同时如果服务器钝化的时间在session 默认销毁时间之内， 则活化后session还是存在的。否则Session不存在。  
     如果JavaBean 数据在session钝化时，没有实现Serializable 则当Session活化时，会消失。
```
### ServletContext
```java
四、ServletContext
1.生命周期：
    当WEB应用被加载进容器创建代表整个WEB应用的ServletContext对象；
    当服务器关闭或WEB应用被移除时，ServletContext对象跟着被销毁。
2.作用范围：整个WEB应用。
3.作用： 
a)在不同Servlet 之间转发  
this.getServletContext().getRequestDispatcher("/servlet/Demo10Servlet").forward(request,response); 
方法执行结束，service就会返回到服务器，再有服务器去调用目标servlet，其中request会重新创建，并将之前的request的数据拷贝进去。    
b)读取资源文件。
1、由于相对路径默认相对的是java虚拟机启动的目录，所以我们直接写相对路径将会是相对于tomcat/bin目录，所以是拿不到资源的。如果写成绝对路径，当项目发布到其他环境时，绝对路径就错了。  
为了解决这个问题ServletContext提供了：
this.getServletContext().getRealPath("/1.properties")，给进一个资源的虚拟路径，将会返回该资源在当前环境下的真实路径。　　　　　　　　
this.getServletContext().getResourceAsStream("/1.properties")，给一个资源的虚拟路径返回到该资源真实路径的流。  
2、当在非servlet下获取资源文件时，就没有ServletContext对象用了，此时只能用类加载器   
classLoader.getResourceAsStream("../../1.properties")，此方法利用类加载器直接将资源加载到内存中，有更新延迟的问题，以及如果文件太大，占用内存过大。   
classLoader.getResource("../1.properties").getPath()，直接返回资源的真实路径，没有更新延迟的问题。
```
## 向request域对象共享数据
### ServletAPI (HttpServletRequest request)
```html
## 获得跳转页面
<a th:href="@{/scope/servletAPI}">ServletAPI</a>
```
```java
/**
 * @author by pepsi-wyl
 * @date 2022-02-24 9:22
 */

@Controller(value = "scopeController")
@RequestMapping(name = "域对象共享数据测试", value = "scope")
public class ScopeController {
    @RequestMapping(name = "ServletAPI共享数据", value = {"/servletAPI"}, method = {RequestMethod.GET})
    public String ServletAPI_T(HttpServletRequest request) {
        request.setAttribute("key", "value");
        return "success";
    }
}
```
```html
<!DOCTYPE html>
<html lang="en" xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="UTF-8">
    <title>success</title>
</head>
<body>

<!--获取域对象-->
<!--/*@thymesVar id="key" type="java.lang.String"*/-->
<p th:text="${key}"></p>

</body>
</html>
```
### ModelAndView
```html
## 获得跳转页面
<a th:href="@{/scope/modelAndView}">ModelAndersonView</a>
```
```java
/**
 * @author by pepsi-wyl
 * @date 2022-02-24 9:22
 */

@Controller(value = "scopeController")
@RequestMapping(name = "域对象共享数据测试", value = "scope")
public class ScopeController {
    @RequestMapping(name = "ModelAndView共享数据", value = {"/modelAndView"}, method = {RequestMethod.GET})
    public ModelAndView ModelAndView_T() {
        /**
         * ModelAndView有Model和View的功能
         * Model主要用于向请求域共享数据
         * View主要用于设置视图，实现页面跳转
         */
        ModelAndView view = new ModelAndView();
        view.setViewName("success");                               //设置视图，实现页面跳转
        view.addObject("key", "value");    //向请求域共享数据
        return view;
    }
}
```
```html
<!DOCTYPE html>
<html lang="en" xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="UTF-8">
    <title>success</title>
</head>
<body>

<!--获取域对象-->
<!--/*@thymesVar id="key" type="java.lang.String"*/-->
<p th:text="${key}"></p>

</body>
</html>
```
### Model
```html
## 获得跳转页面
<a th:href="@{/scope/model}">Model</a>
```
```java
/**
 * @author by pepsi-wyl
 * @date 2022-02-24 9:22
 */

@Controller(value = "scopeController")
@RequestMapping(name = "域对象共享数据测试", value = "scope")
public class ScopeController {
    @RequestMapping(name = "Model共享数据", value = {"/model"}, method = {RequestMethod.GET})
    public String Model_T(Model model) {
        model.addAttribute("key", "value");
        return "success";
    }
}
```
```html
<!DOCTYPE html>
<html lang="en" xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="UTF-8">
    <title>success</title>
</head>
<body>

<!--获取域对象-->
<!--/*@thymesVar id="key" type="java.lang.String"*/-->
<p th:text="${key}"></p>

</body>
</html>
```
### map
```html
## 获得跳转页面
<a th:href="@{/scope/map}">Map</a>
```
```java
/**
 * @author by pepsi-wyl
 * @date 2022-02-24 9:22
 */

@Controller(value = "scopeController")
@RequestMapping(name = "域对象共享数据测试", value = "scope")
public class ScopeController {
    @RequestMapping(name = "Map共享数据", value = {"/map"}, method = {RequestMethod.GET})
    public String Map_T(Map<String, Object> map) {
        map.put("key", "value");
        return "success";
    }
}
```
```html
<!DOCTYPE html>
<html lang="en" xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="UTF-8">
    <title>success</title>
</head>
<body>

<!--获取域对象-->
<!--/*@thymesVar id="key" type="java.lang.String"*/-->
<p th:text="${key}"></p>

</body>
</html>
```
### ModelMap
```html
## 获得跳转页面
<a th:href="@{/scope/modelMap}">ModelMap</a>
```
```java
/**
 * @author by pepsi-wyl
 * @date 2022-02-24 9:22
 */

@Controller(value = "scopeController")
@RequestMapping(name = "域对象共享数据测试", value = "scope")
public class ScopeController {
    @RequestMapping(name = "ModelMap共享数据", value = {"/modelMap"}, method = {RequestMethod.GET})
    public String ModelMap_T(ModelMap map) {
        map.addAttribute("key", "value");
        return "success";
    }
}
```
```html
<!DOCTYPE html>
<html lang="en" xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="UTF-8">
    <title>success</title>
</head>
<body>

<!--获取域对象-->
<!--/*@thymesVar id="key" type="java.lang.String"*/-->
<p th:text="${key}"></p>

</body>
</html>
```
### Model、ModelMap、Map的关系
Model、ModelMap、Map类型的参数其实本质上都是 BindingAwareModelMap 类型的

```
public interface Model

public class LinkedHashMap<K,V> extends HashMap<K,V> implements Map<K,V>
public class ModelMap extends LinkedHashMap<String, Object>

public class BindingAwareModelMap extends ExtendedModelMap 
```
![image.png](https://cdn.nlark.com/yuque/0/2022/png/23219042/1645770212719-2ae268cd-a31c-4a1d-97c4-14e62cb13ba4.png#clientId=u66425716-75da-4&crop=0&crop=0&crop=1&crop=1&from=paste&id=u88144309&margin=%5Bobject%20Object%5D&name=image.png&originHeight=640&originWidth=1176&originalType=url&ratio=1&rotation=0&showTitle=false&size=88037&status=done&style=none&taskId=ua3bf77f7-a38b-4169-952a-33ad5c58997&title=)
### ModelAndView
```java
ServletAPI (HttpServletRequest request) Model ModelMap Map 
全部将数据封装成ModelAndView对象进行数据传输
```
```java
mv = ha.handle(processedRequest, response, mappedHandler.getHandler());
```

## 向session域共享数据
### ServletAPI (HttpSession session)
```html
<a th:href="@{/scope/session}">Session</a>
```

```java
/**
 * @author by pepsi-wyl
 * @date 2022-02-24 9:22
 */

@Controller(value = "scopeController")
@RequestMapping(name = "域对象共享数据测试", value = "scope")
public class ScopeController {
    @RequestMapping(name = "Session共享数据", value = {"/session"}, method = {RequestMethod.GET})
    public String Session_T(HttpSession session) {
        session.setAttribute("key","value");
        return "success";
    }
}
```
```html
<!DOCTYPE html>
<html lang="en" xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="UTF-8">
    <title>success</title>
</head>
<body>

<!--获取域对象-->
<p th:text="${session.key}"></p>

</body>
</html>
```
## 向application域共享数据
### ServletAPI (HttpSession session)
```html
<a th:href="@{/scope/application}">Application</a>
```
```java
/**
 * @author by pepsi-wyl
 * @date 2022-02-24 9:22
 */

@Controller(value = "scopeController")
@RequestMapping(name = "域对象共享数据测试", value = "scope")
public class ScopeController {
    @RequestMapping(name = "Application共享数据",value = {"/application"},method = {RequestMethod.GET})
    public String Application_T(HttpSession session){
        ServletContext application = session.getServletContext();
        application.setAttribute("key", "value");
        return "success";
    }
}
```
```html
<!DOCTYPE html>
<html lang="en" xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="UTF-8">
    <title>success</title>
</head>
<body>

<p th:text="${application.key}"></p>

</body>
</html>
```
# 视图
## 视图简介
SpringMVC中的视图是View接口，视图的作用渲染数据，将模型Model中的数据展示给用户
SpringMVC视图的种类很多，默认有转发视图和重定向视图

- 当工程引入jstl的依赖，转发视图会自动转换为JstlView
- 若使用的视图技术为Thymeleaf，在SpringMVC的配置文件中配置了Thymeleaf的视图解析器，由此视图解析器解析之后所得到的是ThymeleafView
## ThymeleafView
使用的视图技术为Thymeleaf，在SpringMVC的配置文件中配置了Thymeleaf的视图解析器，解析之后所得到的是ThymeleafView

1. 控制器方法中设置的视图名称无任何前缀时，视图名称会被SpringMVC配置文件中所配置的视图解析器解析
1. 视图名称拼接视图前缀和视图后缀所得到的最终路径，会通过转发的方式实现跳转
```html
<a th:href="@{/view/thymeleaf}">Thymeleaf</a>
```
```java
/**
 * @author by pepsi-wyl
 * @date 2022-02-25 18:44
 */
@Controller("viewController")
@RequestMapping(name = "视图",path = {"/view"})
public class ViewController {
    @RequestMapping(name = "Thymeleaf视图",path = {"/thymeleaf"},method = {RequestMethod.GET})
    public String Thymeleaf_T(){
        return "success";
    }
}
```
![image.png](https://cdn.nlark.com/yuque/0/2022/png/23219042/1645789377829-4e4f966e-fac9-483f-8135-eac8a21b3715.png#clientId=u66425716-75da-4&crop=0&crop=0&crop=1&crop=1&from=paste&height=390&id=u1ae7f4f7&margin=%5Bobject%20Object%5D&name=image.png&originHeight=390&originWidth=1562&originalType=binary&ratio=1&rotation=0&showTitle=false&size=759012&status=done&style=none&taskId=u0950742c-9838-4a6f-8e8a-d0d1c7aee7c&title=&width=1562)
## 转发视图InternalResourceView
SpringMVC中默认的转发视图是InternalResourceView

1. 控制器方法中设置的视图名称以"forward:"为前缀时，创建InternalResourceView视图，不会被SpringMVC配置文件中所配置的视图解析器解析
1. 将前缀"forward:"去掉，剩余部分作为最终路径通过转发的方式实现跳转

```html
<a th:href="@{/view/forward}">Forward</a>
```
```java
/**
 * @author by pepsi-wyl
 * @date 2022-02-25 18:44
 */
@Controller("viewController")
@RequestMapping(name = "视图",path = {"/view"})
public class ViewController {
    @RequestMapping(name = "Forward视图",path = {"/forward"},method = {RequestMethod.GET})
    public String Forward_T(){
        return "forward:/view/thymeleaf";
    }
}
```
![image.png](https://cdn.nlark.com/yuque/0/2022/png/23219042/1645789638034-21edcad2-5a2d-4dde-8c9b-f99dfc91e0cb.png#clientId=u66425716-75da-4&crop=0&crop=0&crop=1&crop=1&from=paste&height=401&id=u699959ab&margin=%5Bobject%20Object%5D&name=image.png&originHeight=401&originWidth=1765&originalType=binary&ratio=1&rotation=0&showTitle=false&size=885974&status=done&style=none&taskId=uf854a2e4-7606-4ef6-a131-c58b7a34220&title=&width=1765)
## 重定向视图RedirectView
SpringMVC中默认的重定向视图是RedirectView

1. 控制器方法中设置的视图名称以"redirect:"为前缀时，创建RedirectView视图，不会被SpringMVC配置文件中所配置的视图解析器解析
1. 将前缀"redirect:"去掉，剩余部分作为最终路径通过重定向的方式实现跳转
```html
<a th:href="@{/view/redirect}">Redirect</a>
```
```java
/**
 * @author by pepsi-wyl
 * @date 2022-02-25 18:44
 */
@Controller("viewController")
@RequestMapping(name = "视图",path = {"/view"})
public class ViewController {
    @RequestMapping(name = "Redirect视图",path = {"/redirect"},method = {RequestMethod.GET})
    public String Redirect_T(){
        return "redirect:/view/thymeleaf";      // 上下文路径拼接
        //return "redirect:view/thymeleaf";     // 直接拼接
    }
}
```
![image.png](https://cdn.nlark.com/yuque/0/2022/png/23219042/1645789705466-08186330-3db2-48ea-ab8d-0008601802c4.png#clientId=u66425716-75da-4&crop=0&crop=0&crop=1&crop=1&from=paste&height=320&id=uf97489dd&margin=%5Bobject%20Object%5D&name=image.png&originHeight=320&originWidth=1742&originalType=binary&ratio=1&rotation=0&showTitle=false&size=681186&status=done&style=none&taskId=ua8d5e2ba-b437-4ed2-b03f-2e9b9f5ed78&title=&width=1742)

> 注：
> 重定向视图在解析时，会先将redirect:前缀去掉，判断剩余部分是否以/开头，若是则会自动拼接上下文路径

## 视图控制器view-controller
当控制器方法中，仅仅用来实现页面跳转，可以将处理器方法使用view-controller标签进行表示
```xml
<!-- path：设置处理的请求地址 view-name：设置请求地址所对应的视图名称-->
<mvc:view-controller path="/" view-name="index"></mvc:view-controller>

<!-- SpringMVC中设置任何一个view-controller时，其他控制器中的请求映射将全部失效-->
<!-- 需要在SpringMVC的核心配置文件中设置 开启mvc注解驱动的标签-->
<mvc:annotation-driven/>
```
```java

/**
 * @author by pepsi-wyl
 * @date 2022-01-28 14:05
 */

@Controller(value = "indexController")   // 自定义Controller组件名称
public class IndexController {
    @RequestMapping(
            name = "首页",
            value = {"/", "index", "index.html", "main", "main.html"},
            method = RequestMethod.GET)
    public String index() {
        return "index";
    } // 返回index首页页面
}
```
# RESTful
## 简介
REST：**Re**presentational **S**tate **T**ransfer，表现层资源状态转移。
### 资源
资源是一种看待服务器的方式，即，将服务器看作是由很多离散的资源组成。每个资源是服务器上一个可命名的抽象概念。因为资源是一个抽象的概念，所以它不仅仅能代表服务器文件系统中的一个文件、数据库中的一张表等等具体的东西，可以将资源设计的要多抽象有多抽象，只要想象力允许而且客户端应用开发者能够理解。与面向对象设计类似，资源是以名词为核心来组织的，首先关注的是名词。一个资源可以由一个或多个URI来标识。URI既是资源的名称，也是资源在Web上的地址。对某个资源感兴趣的客户端应用，可以通过资源的URI与其进行交互。
### 资源的表述
资源的表述是一段对于资源在某个特定时刻的状态的描述。可以在客户端-服务器端之间转移（交换）。资源的表述可以有多种格式，例如HTML/XML/JSON/纯文本/图片/视频/音频等等。资源的表述格式可以通过协商机制来确定。请求-响应方向的表述通常使用不同的格式。
### 状态转移
状态转移说的是：在客户端和服务器端之间转移（transfer）代表资源状态的表述。通过转移和操作资源的表述，来间接实现操作资源的目的。
## 实现
### 简述
REST 风格提倡 URL 地址使用统一的风格设计，从前到后各个单词使用斜杠分开，不使用问号键值对方式携带请求参数，而是将要发送给服务器的数据作为 URL 地址的一部分，以保证整体风格的一致性。

具体说，就是 HTTP 协议里面，四个表示操作方式的动词：GET、POST、PUT、DELETE
它们分别对应四种基本操作：

- GET 用来获取资源
- POST 用来新建资源
- PUT 用来更新资源
- DELETE 用来删除资源
### GET POST
#### GET  查询
```html
<a th:href="@{/user}">得到所有用户信息</a>      <br/>
<a th:href="@{/user/1}">根据ID查询用户信息</a>  <br/>  // 传统方式 /user?id=1
```
```java
/**
 * @author by pepsi-wyl
 * @date 2022-02-26 9:47
 */
@Controller(value = "RESTfulController")
public class RESTfulController {
    @ResponseBody
    @RequestMapping(name = "查找所有user信息", value = {"/user"}, method = {RequestMethod.GET})
    public String getAllUser() {
        System.out.println("查询所有用户信息");
        return "success";
    }

    @ResponseBody
    @RequestMapping(name = "根据ID查找user信息", value = {"/user/{id}"}, method = {RequestMethod.GET})
    public String getUserByID(@PathVariable(value = "id") Integer id) {
        System.out.println("查询ID为" + id + "的用户信息");
        return "success";
    }
}
```
#### POST 添加
```html
<form th:action="@{/user}" method="post">
    userName:<input type="text" name="userName"> <br/>
    password:<input type="password" name="password"> <br/>
    <input type="submit" value="添加">
</form>
```
```java
/**
 * @author by pepsi-wyl
 * @date 2022-02-26 9:47
 */

@Controller(value = "RESTfulController")
public class RESTfulController {
    @ResponseBody
    @RequestMapping(name = "添加用户信息", value = "/user", method = {RequestMethod.POST})
    public String saveUser(String userName, String password) {
        System.out.println("添加用户信息 " + userName + " " + password);
        return "success";
    }
}
```
### PUT DELETE
#### HiddenHttpMethodFilter
##### 起因
```java
1. 浏览器只支持发送get和post方式的请求
2. SpringMVC 提供了 HiddenHttpMethodFilter 帮助我们将 POST 请求转换为 DELETE 或 PUT 请求
```
##### 请求条件
```java
处理put和delete请求的条件

请求方式必须为post
必须传输请求参数_method  (请求参数_method的值才是最终的请求方式)
    
满足以上条件，HiddenHttpMethodFilter 过滤器就会将当前请求的请求方式转换为请求参数_method的值
```
##### 配置 过滤器
```xml
## 在web.xml中注册 HiddenHttpMethodFilter
<filter>
    <filter-name>HiddenHttpMethodFilter</filter-name>
    <filter-class>org.springframework.web.filter.HiddenHttpMethodFilter</filter-class>
</filter>
<filter-mapping>
    <filter-name>HiddenHttpMethodFilter</filter-name>
    <url-pattern>/*</url-pattern>
</filter-mapping>
```
注意： 必须先注册CharacterEncodingFilter，再注册HiddenHttpMethodFilter

- 在 CharacterEncodingFilter 中通过 request.setCharacterEncoding(encoding) 方法设置字符集
- request.setCharacterEncoding(encoding) 方法要求前面不能有任何获取请求参数的操作
- 而 HiddenHttpMethodFilter 恰恰有一个获取请求方式的操作：

      String paramValue = request.getParameter(this.methodParam);
##### 源码分析
```java
private String methodParam = "_method"; //  属性常量

protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
    
    HttpServletRequest requestToUse = request; // 创建request中间变量
   
    // POST请求方式 
    if ("POST".equals(request.getMethod()) && request.getAttribute("javax.servlet.error.exception") == null) {
        
        // private String methodParam = "_method";  获取 _method 的值
        String paramValue = request.getParameter(this.methodParam);
        
        if (StringUtils.hasLength(paramValue)) { // 长度不为0
            
            // 将method 转化为大写
            String method = paramValue.toUpperCase(Locale.ENGLISH);
          
          
            /**
             *   static {
             *      ALLOWED_METHODS = Collections.unmodifiableList(Arrays.asList(HttpMethod.PUT.name(), HttpMethod.DELETE.name(), HttpMethod.PATCH.name()));
             *   }
             */
            
            if (ALLOWED_METHODS.contains(method)) { // 转化method
                requestToUse = new HiddenHttpMethodFilter.HttpMethodRequestWrapper(request, method);
            }
          
        }
    }

    filterChain.doFilter((ServletRequest)requestToUse, response); // 过滤器放行
}
```
#### PUT 修改
```html
<form th:action="@{/user}" method="post">
    <!-- HiddenHttpMethodFilter要求：必须传输_method请求参数，并且值为最终的请求方式 -->
    <input type="hidden" name="_method" value="put">
    userName:<input type="text" name="userName"> <br/>
    password:<input type="password" name="password"> <br/>
    <input type="submit" value="修改">
</form>
```
```java
@Controller(value = "RESTfulController")
public class RESTfulController {
    @ResponseBody
    @RequestMapping(name = "修改用户信息", value = "/user", method = {RequestMethod.PUT})
    public String updateUser(String userName, String password) {
        System.out.println("修改用户信息 " + userName + " " + password);
        return "success";
    }
}

```
#### DELETE 删除
```html
<form th:action="@{/user/1}" method="post">
    <!-- HiddenHttpMethodFilter要求：必须传输_method请求参数，并且值为最终的请求方式 -->
    <input type="hidden" name="_method" value="delete"/>
    <input type="submit" value="删除">
</form>
```
```java
@Controller(value = "RESTfulController")
public class RESTfulController {
    @ResponseBody
    @RequestMapping(name = "删除用户信息", value = {"/user/{id}"}, method = {RequestMethod.DELETE})
    public String delUser(@PathVariable(value = "id") Integer id) {
        System.out.println("删除" + id + "的用户信息");
        return "success";
    }
}
```
## 案例
### 准备工作
#### 准备实体类  
```java
/**
 * @author by pepsi-wyl
 * @date 2022-02-26 14:02
 */

@Component(value = "employee")
@Scope(value = "singleton") // 周期为单例

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class Employee {
    private Integer id;
    private String lastName;
    private String email;
    //1 male, 0 female
    private Integer gender;
}
```
#### 准备Mapper数据
```java
/**
 * @author by pepsi-wyl
 * @date 2022-02-26 14:05
 */

@Repository
public class EmployeeMapper {
    private static Map<Integer, Employee> employees = null;

    static {
        employees = new HashMap<Integer, Employee>();

        employees.put(1001, new Employee(1001, "E-AA", "aa@163.com", 1));
        employees.put(1002, new Employee(1002, "E-BB", "bb@163.com", 1));
        employees.put(1003, new Employee(1003, "E-CC", "cc@163.com", 0));
        employees.put(1004, new Employee(1004, "E-DD", "dd@163.com", 0));
        employees.put(1005, new Employee(1005, "E-EE", "ee@163.com", 1));
    }

    private static Integer initId = 1006;

    public void saveEmployee(Employee employee) {
        if (employee.getId() == null) {
            employee.setId(initId++);
        }
        employees.put(employee.getId(), employee);
    }

    public Collection<Employee> getAllEmployee() {
        return employees.values();
    }

    public Employee getEmployeeByID(Integer id) {
        return employees.get(id);
    }

    public void deleteEmployeeByID(Integer id) {
        employees.remove(id);
    }
}
```
### 查询
跳转查询所有员工Controller请求
```html
<a th:href="@{/employee}">查询所有员工信息</a>
```
查询所有员工Controller   并返回数据
```java
@Controller(value = "employeeController")
public class EmployeeController {

    /**
     * 持久层数据
     */
    private final EmployeeMapper employeeMapper;

    // Spring 注入
    public EmployeeController(@Qualifier(value = "employeeMapper") EmployeeMapper employeeMapper) {
        this.employeeMapper = employeeMapper;
    }

    @RequestMapping(name = "查询所有Employee信息", value = "/employee", method = RequestMethod.GET)
    public ModelAndView getEmployeeList(ModelAndView modelAndView) {
        Collection<Employee> employeeList = employeeMapper.getAllEmployee();
        modelAndView.addObject("employeeList", employeeList);
        modelAndView.setViewName("employeeList");           // 视图名称
        return modelAndView;
    }
}
```
数据展示
```html
<!DOCTYPE html>
<html lang="en" xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="UTF-8">
    <title>Employee Info</title>
</head>
<body>

<table id="dataTable" border="1" cellpadding="0" cellspacing="0" style="text-align: center;">
    <tr>
        <th colspan="5">Employee Info</th>
    </tr>
    <tr>
        <th>Id</th>
        <th>LastName</th>
        <th>Email</th>
        <th>Gender</th>
    </tr>
    <tr th:each="employee : ${employeeList}">
        <td th:text="${employee.id}"></td>
        <td th:text="${employee.lastName}"></td>
        <td th:text="${employee.email}"></td>
        <td th:text="${employee.gender}"></td>
    </tr>
</table>

</body>
</html>
```
### 添加
添加跳转页面
```html
<th><a th:href="@{/toAddEmployee}">添加员工</a></th>
```
配置 添加页面 view-controller
```xml
<mvc:view-controller path="/toAddEmployee" view-name="employeeAdd"/>
<mvc:annotation-driven/>
```
添加页面
```html
<!DOCTYPE html>
<html lang="en" xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="UTF-8">
    <title>Add Employee</title>
</head>
<body>

<form th:action="@{/employee}" method="post">
    lastName<input type="text" name="lastName"><br/>
    email<input type="text" name="email"><br/>
    gender<input type="radio" name="gender" value="1"> male <input type="radio" name="gender" value="0">female<br/>
    <input type="submit" value="添加">
</form>

</body>
</html>
```
添加 Controller
```java
@Controller(value = "employeeController")
public class EmployeeController {

    /**
     * 持久层数据
     */
    private final EmployeeMapper employeeMapper;

    // Spring 注入
    public EmployeeController(@Qualifier(value = "employeeMapper") EmployeeMapper employeeMapper) {
        this.employeeMapper = employeeMapper;
    }

    @RequestMapping(name = "添加Employee信息", value = "/employee", method = RequestMethod.POST)
    public String addEmployee(Employee employee) { // pojo类型数据 传递
        employeeMapper.saveEmployee(employee);
        return "redirect:/employee";  // 重定向
    }
}

```
### 删除
删除请求 
```html
<!--删除 DELETE请求-->
<form th:action="@{'/employee/'+${employee.id}}" method="post">
     <input type="hidden" name="_method" value="delete"/>
     <input type="submit" value="删除"/>
</form>
```
删除 Controller
```java
@Controller(value = "employeeController")
public class EmployeeController {

    /**
     * 持久层数据
     */
    private final EmployeeMapper employeeMapper;

    // Spring 注入
    public EmployeeController(@Qualifier(value = "employeeMapper") EmployeeMapper employeeMapper) {
        this.employeeMapper = employeeMapper;
    }
  
    @RequestMapping(name = "删除Employee信息", value = "/employee/{id}", method = RequestMethod.DELETE)
    public String delEmployeeByID(@PathVariable(value = "id") Integer id) {
        employeeMapper.deleteEmployeeByID(id);
        return "redirect:/employee";  // 重定向
    }
}

```
### 修改
修改 数据回显
```html
<a th:href="@{'/employee/'+${employee.id}}"> update </a>
```
数据回显 Controller
```java
@Controller(value = "employeeController")
public class EmployeeController {

    /**
     * 持久层数据
     */
    private final EmployeeMapper employeeMapper;

    // Spring 注入
    public EmployeeController(@Qualifier(value = "employeeMapper") EmployeeMapper employeeMapper) {
        this.employeeMapper = employeeMapper;
    }

    @RequestMapping(name = "根据ID查找Employee信息", value = "/employee/{id}", method = RequestMethod.GET)
    public ModelAndView getEmployeeByID(@PathVariable(value = "id") Integer id, ModelAndView modelAndView) {
        Employee employeeByID = employeeMapper.getEmployeeByID(id);
        modelAndView.addObject("employee", employeeByID);
        modelAndView.setViewName("employeeUpdate");           // 视图名称
        return modelAndView;
    }
}
```
修改页面
```html
<!DOCTYPE html>
<html lang="en" xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="UTF-8">
    <title>Update Employee</title>
</head>
<body>

<form th:action="@{/employee}" method="post">
    <!--PUT 请求方式修改-->
    <input type="hidden" name="_method" value="put">

    <input type="hidden" name="id" th:value="${employee.id}">
    lastName<input type="text" name="lastName" th:value="${employee.lastName}"><br/>
    email<input type="text" name="email" th:value="${employee.email}"><br/>
    gender<input type="radio" name="gender" value="1" th:field="${employee.gender}">male
    <input type="radio" name="gender" value="0" th:field="${employee.gender}">female<br/>
    <input type="submit" value="修改">
</form>

</body>
</html>
```
修改 Controller
```java
@Controller(value = "employeeController")
public class EmployeeController {

    /**
     * 持久层数据
     */
    private final EmployeeMapper employeeMapper;

    // Spring 注入
    public EmployeeController(@Qualifier(value = "employeeMapper") EmployeeMapper employeeMapper) {
        this.employeeMapper = employeeMapper;
    }
    
    @RequestMapping(name = "修改Employee信息", value = "/employee", method = RequestMethod.PUT)
    public String updateEmployee(Employee employee) {
        employeeMapper.saveEmployee(employee);
        return "redirect:/employee";  // 重定向
    }
}
```

# HttpMessageConverter
HttpMessageConverter，报文信息转换器，将请求报文转换为Java对象，或将Java对象转换为响应报文
两个注解

- @RequestBody       ------->@RestController
- @ResponseBody

两个类型

- RequestEntity
- ResponseEntity
## @RequestBody  
@RequestBody获取请求体，在控制器方法设置一个形参，使用@RequestBody进行标识，
当前请求的请求体就会为当前注解所标识的形参赋值
```html
<form th:action="@{/requestBody}" method="post">
    用户名：<input type="text" name="username"><br>
    密码：<input type="password" name="password"><br>
    <input type="submit" value="@RequestBody获取请求体">
</form>
```
```java
@Controller(value = "httpController")
public class HttpController {
    @ResponseBody
    @RequestMapping(name = "@RequestBody获取请求体", value = "/requestBody", method = RequestMethod.POST)
    public String T_RequestBody(@RequestBody String requestBody) {
        System.out.println(requestBody);
        return "success";
    }
}

```
![image.png](https://cdn.nlark.com/yuque/0/2022/png/23219042/1645945282716-bf3e10ee-9c25-45d5-aa13-2e34bda7c673.png#clientId=u91f0cead-9f56-4&crop=0&crop=0&crop=1&crop=1&from=paste&height=35&id=u2b89c1e9&margin=%5Bobject%20Object%5D&name=image.png&originHeight=35&originWidth=398&originalType=binary&ratio=1&rotation=0&showTitle=false&size=22671&status=done&style=none&taskId=ua9e9461b-533f-4a1a-9656-3f9f0611b68&title=&width=398)
## @ResponseBody  
@ResponseBody用于标识一个控制器方法，可以将该方法的返回值直接作为响应报文的响应体响应到浏览器
```html
<a th:href="@{/responseBody}">@ResponseBody返回JSON类型数据</a>
```
```java
@Controller(value = "httpController")
public class HttpController {
ResponseBody
    @ResponseBody
    @RequestMapping(name = "@ResponseBody返回JSON类型数据", value = "/responseBody", method = RequestMethod.GET)
    public String T_ResponseBody(){
        return "success";
    }
}
```

![image.png](https://cdn.nlark.com/yuque/0/2022/png/23219042/1645945391308-d2be55bf-5aa2-4c89-b7c6-8c9028c176c4.png#clientId=u91f0cead-9f56-4&crop=0&crop=0&crop=1&crop=1&from=paste&height=54&id=SMgzo&margin=%5Bobject%20Object%5D&name=image.png&originHeight=54&originWidth=510&originalType=binary&ratio=1&rotation=0&showTitle=false&size=37877&status=done&style=none&taskId=u16e8bb52-de07-40ff-8e22-76e929c2d8c&title=&width=510)
## @RestController注解
@RestController注解是springMVC提供的一个复合注解，标识在控制器的类上，
为类添加了@Controller注解，并且为其中的每个方法添加了@ResponseBody注解
```html
<a th:href="@{/restController}">@RestController 衍生注解</a>
```
```java
@RestController(value = "httpController")  // 衍生注解
public class HttpController {
    @RequestMapping(name = "RestController衍生注解", value = "/restController", method = RequestMethod.GET)
    public String T_RestController(){
        return "success";
    }
}
```
![image.png](https://cdn.nlark.com/yuque/0/2022/png/23219042/1645945704263-ee3d6fa4-8cd4-439b-8c40-3ce293665aed.png#clientId=u91f0cead-9f56-4&crop=0&crop=0&crop=1&crop=1&from=paste&height=58&id=ua10baaf7&margin=%5Bobject%20Object%5D&name=image.png&originHeight=58&originWidth=547&originalType=binary&ratio=1&rotation=0&showTitle=false&size=43105&status=done&style=none&taskId=u4c29aa1d-f731-465c-9402-5e6e0bd7afd&title=&width=547)
## RequestEntity
RequestEntity封装请求报文的一种类型
在控制器方法的形参中设置该类型的形参，当前请求的请求报文就会赋值给该形参
通过getHeaders()获取请求头信息，通过getBody()获取请求体信息
```html
<a th:href="@{/requestEntity}">RequestEntity类型</a>
```
```java
@Controller(value = "httpController")
public class HttpController {
ResponseBody
    @RequestMapping(name = "RequestEntity类型", value = "/requestEntity", method = RequestMethod.GET)
    public String T_RequestEntity(RequestEntity<String> requestEntity){
        System.out.println("requestHeader:"+requestEntity.getHeaders());
        System.out.println("requestBody:"+requestEntity.getBody());
        return "success";
    }
}
```
![image.png](https://cdn.nlark.com/yuque/0/2022/png/23219042/1645945325775-8a593f4f-5ea7-43a6-80af-080436891d3e.png#clientId=u91f0cead-9f56-4&crop=0&crop=0&crop=1&crop=1&from=paste&height=26&id=u7ee88c6d&margin=%5Bobject%20Object%5D&name=image.png&originHeight=26&originWidth=1206&originalType=binary&ratio=1&rotation=0&showTitle=false&size=43905&status=done&style=none&taskId=ud7080039-c806-4c3a-a49e-db7a50c8117&title=&width=1206)
## ResponseEntity
ResponseEntity用于控制器方法的返回值类型，该控制器方法的返回值就是响应到浏览器的响应报文
## json
### 配置web.xml 解决JSON乱码
```xml
<!--解决json乱码配置-->
<mvc:annotation-driven>
    <mvc:message-converters register-defaults="true">
        <bean class="org.springframework.http.converter.StringHttpMessageConverter">
            <constructor-arg value="UTF-8"/>
        </bean>
        <bean class="org.springframework.http.converter.json.MappingJackson2HttpMessageConverter">
            <property name="objectMapper">
                <bean class="org.springframework.http.converter.json.Jackson2ObjectMapperFactoryBean">
                    <property name="failOnEmptyBeans" value="false"/>
                </bean>
            </property>
        </bean>
    </mvc:message-converters>
</mvc:annotation-driven>
```
### jackson
#### 导入依赖
```xml
<!-- https://mvnrepository.com/artifact/com.fasterxml.jackson.core/jackson-databind -->
<dependency>
        <groupId>com.fasterxml.jackson.core</groupId>
        <artifactId>jackson-databind</artifactId>
        <version>2.13.1</version>
</dependency>
<!-- https://mvnrepository.com/artifact/com.fasterxml.jackson.core/jackson-core -->
<dependency>
        <groupId>com.fasterxml.jackson.core</groupId>
        <artifactId>jackson-core</artifactId>
        <version>2.13.1</version>
</dependency>
<!-- https://mvnrepository.com/artifact/com.fasterxml.jackson.core/jackson-annotations -->
<dependency>
        <groupId>com.fasterxml.jackson.core</groupId>
        <artifactId>jackson-annotations</artifactId>
       <version>2.13.1</version>
</dependency>
```
#### 实体类
```java
@Data
@NoArgsConstructor
@AllArgsConstructor

@Component
@Scope("singleton")

public class User {
    private int id; 
    private String name;
    private int age;
}

```
#### JacksonUtils
```java
public class JacksonUtils {

    private static final String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";    //解析时间格式
    private static ObjectMapper mapper = null;

    //无参的私有构造方法
    private JacksonUtils() {

    }

    //初始化mapper变量
    static {
        if (mapper == null) {
            mapper = new ObjectMapper();
            mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);    //关闭时间戳的方式
            mapper.setDateFormat(new SimpleDateFormat(DATE_FORMAT));                          //设置时间解析方式
        }
    }

    /**
     * 得到json字符串
     */
    @SneakyThrows
    public static String toJson(Object obj) {
        return mapper.writeValueAsString(obj);
    }

    /**
     * json转化为Obj
     */
    @SneakyThrows
    public static <T> T jsonToObj(String jsonString, Class<T> clazz) {
        return (T) mapper.readValue(jsonString, clazz);
    }

    /**
     * json转化为List
     */
    @SneakyThrows
    public static <T> List<T> jsonToList(String jsonString, Class<T> clazz) {
        return mapper.readValue(jsonString, mapper.getTypeFactory().constructParametricType(List.class, clazz));
    }

    /**
     * json转化为Map
     */
    @SneakyThrows
    public static <K, V> Map<K, V> jsonToMap(String jsonString, Class<K> keyType, Class<V> valueType) {
        return mapper.readValue(jsonString, mapper.getTypeFactory().constructMapType(Map.class, keyType, valueType));
    }
}

```
#### 测试1  不需要使用JacksonUtils 
需要在SpringMVC的核心配置文件中开启mvc的注解驱动
此时在HandlerAdaptor中会自动装配一个消息转换器：MappingJackson2HttpMessageConverter
可以将响应到浏览器的Java对象转换为Json格式的字符串
```
<mvc:annotation-driven />
```
```html
<a th:href="@{/JSON_Gson/test1}">/JSON_Gson/test1</a>
<a th:href="@{/JSON_Gson/test2}">/JSON_Gson/test2</a>
<a th:href="@{/JSON_Gson/test3}">/JSON_Gson/test3</a>
<a th:href="@{/JSON_Gson/test4}">/JSON_Gson/test4</a>
```
```java
@RestController(value = "JSON_Jackson")
@RequestMapping("/JSON_Jackson")
public class JSON_Jackson {

    /**
     * 解析对象
     */
    @RequestMapping(value = "/test1", method = RequestMethod.GET)
    @SneakyThrows
    public User test1() {
        return new User(1, "武扬岚", 20);
    }

    /**
     * 解析List
     */
    @RequestMapping(value = "/test2", method = RequestMethod.GET)
    @SneakyThrows            //lombok注解抛异常
    public List test2() {
        List<User> list = new ArrayList<User>();
        list.add(new User(1, "武扬岚", 20));
        list.add(new User(2, "武扬岚", 20));
        list.add(new User(3, "武扬岚", 20));
        list.add(new User(4, "武扬岚", 20));
        return list;
    }

    /**
     * 解析Map
     */
    @RequestMapping(value = "/test3", method = RequestMethod.GET)
    @SneakyThrows              //lombok注解抛异常
    public HashMap test3() {
        HashMap<Integer, User> personMap = new HashMap<>();
        personMap.put(1, new User(1, "武扬岚", 20));
        personMap.put(2, new User(1, "武扬岚", 20));
        return personMap;
    }

    /**
     * 解析时间
     */
    @RequestMapping(value = "/test4", method = RequestMethod.GET)
    @SneakyThrows              //lombok注解抛异常
    public String test4() {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) ;
    }
}
```
#### 测试2  需要使用JacksonUtils
```xml
<a th:href="@{/JSON_Gson/test5}">/JSON_Gson/test5</a>
<a th:href="@{/JSON_Gson/test6}">/JSON_Gson/test6</a>
<a th:href="@{/JSON_Gson/test7}">/JSON_Gson/test7</a>
<a th:href="@{/JSON_Gson/test8}">/JSON_Gson/test8</a>
```
```java
@RestController(value = "JSON_Jackson")
@RequestMapping("/JSON_Jackson")
public class JSON_Jackson {

    /**
     * 解析对象
     */
    @RequestMapping(value = "/test5", method = RequestMethod.GET)
    @SneakyThrows            //lombok注解抛异常
    public String test5() {
        User user = new User(1, "武扬岚", 20);
        String str = JacksonUtils.toJson(user);
        User user1 = JacksonUtils.jsonToObj(str, User.class);
        System.out.println(user1);
        return JacksonUtils.toJson(user);
    }

    /**
     * 解析List
     */
    @RequestMapping(value = "/test6", method = RequestMethod.GET)
    @SneakyThrows            //lombok注解抛异常
    public String test6() {
        List<User> list = new ArrayList<User>();
        list.add(new User(1, "武扬岚", 20));
        list.add(new User(2, "武扬岚", 20));
        list.add(new User(3, "武扬岚", 20));
        list.add(new User(4, "武扬岚", 20));
        String str = JacksonUtils.toJson(list);
        List<User> users = JacksonUtils.jsonToList(str, User.class);
        users.forEach((v) -> System.out.println(v));
        return JacksonUtils.toJson(list);
    }

    /**
     * 解析Map
     */
    @RequestMapping(value = "/test7", method = RequestMethod.GET)
    @SneakyThrows              //lombok注解抛异常
    public String test7() {
        HashMap<Integer, User> personMap = new HashMap<>();
        personMap.put(1, new User(1, "武扬岚", 20));
        personMap.put(2, new User(1, "武扬岚", 20));
        String str = JacksonUtils.toJson(personMap);
        System.out.println(str);
        Map<Integer, User> map = JacksonUtils.jsonToMap(str, Integer.class, User.class);
        map.forEach((k, v) -> System.out.println(k + " " + v));
        return JacksonUtils.toJson(personMap);
    }

    /**
     * 解析时间
     */
    @RequestMapping(value = "/test8", method = RequestMethod.GET)
    @SneakyThrows              //lombok注解抛异常
    public String test8() {
        String str = JacksonUtils.toJson(new Date());
        System.out.println(str);
        Date date = JacksonUtils.jsonToObj(str, Date.class);
        System.out.println(date);
        return JacksonUtils.toJson(new Date());
    }
}

```
### Gson
#### 导入依赖
```xml
<!-- https://mvnrepository.com/artifact/com.google.code.gson/gson -->
<dependency>
       <groupId>com.google.code.gson</groupId>
       <artifactId>gson</artifactId>
       <version>2.9.0</version>
</dependency>
```
#### 实体类
```java
@Data
@NoArgsConstructor
@AllArgsConstructor

@Component
@Scope("singleton")

public class User {
    // Gson  serialize  序列化  deserialize 反序列化
    @Expose(serialize = true, deserialize = true)
    private int id;
    // SerializedName  value 表示默认名称    alternate 表示备用名称
    @SerializedName(value = "name", alternate = {"username", "user"})
    private String name;
    private int age;
}
```
#### GsonUtils
```java
public class GsonUtils {

    private static final String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";    // 解析时间格式
    private static Gson gson = null;                                    // 私有化Gosn对象

    // 无参的私有构造方法
    private GsonUtils() {

    }

    static {
        if (gson == null) {
            gson = new GsonBuilder()
                    .setLenient()                          //json宽松
                    .enableComplexMapKeySerialization()    //支持Map的key为复杂对象的形式
                    .serializeNulls()                      //智能null
                    .setPrettyPrinting()                   //调教格式
                    .disableHtmlEscaping()                 //默认是GSON把HTML 转义的
                    .setDateFormat(DATE_FORMAT)
                    .create();
        }
    }
    
    /**
     * 得到Gson对象
     */
    public static Gson getGosn() {
        return gson;
    }

    /**
     * 将对象转成json格式
     */
    @SneakyThrows
    public static String toJson(Object object) {
        return gson.toJson(object);
    }

    /**
     * 将json转成特定的class的对象
     */
    @SneakyThrows
    public static <T> T jsonToObj(String jsonString, Class<T> clazz) {
        return gson.fromJson(jsonString, clazz);
    }

    /**
     * json字符串转成list
     */
    @SneakyThrows
    public static <T> List<T> jsonToList(String jsonString, TypeToken typeToken) {
        return gson.fromJson(jsonString, typeToken.getType());
    }

    @SneakyThrows
    public static <T> List<T> jsonToList(String jsonString, TypeToken typeToken, Class<T> clazz) {
        return gson.fromJson(jsonString, typeToken.getType());
    }

    /**
     * json字符串转成Map
     */
    @SneakyThrows
    public static <K, T> Map<K, T> jsonToMap(String jsonString, TypeToken typeToken) {
        return gson.fromJson(jsonString, typeToken.getType());
    }

    @SneakyThrows
    public static <K, T> Map<K, T> jsonToMap(String jsonString, TypeToken typeToken, Class<K> k, Class<T> t) {
        return gson.fromJson(jsonString, typeToken.getType());
    }
}
```
#### 测试1  不需要使用JacksonUtils 
需要在SpringMVC的核心配置文件中开启mvc的注解驱动
此时在HandlerAdaptor中会自动装配一个消息转换器：MappingJackson2HttpMessageConverter
可以将响应到浏览器的Java对象转换为Json格式的字符串
```html
<a th:href="@{/JSON_Gson/test1}">/JSON_Gson/test1</a>
<a th:href="@{/JSON_Gson/test2}">/JSON_Gson/test2</a>
<a th:href="@{/JSON_Gson/test3}">/JSON_Gson/test3</a>
<a th:href="@{/JSON_Gson/test4}">/JSON_Gson/test4</a>
```
```java
@RestController(value = "JSON_Gson")  // 组合注解
@RequestMapping("/JSON_Gson")
public class JSON_Gson {

    /**
     * 解析对象
     */
    @RequestMapping(value = "/test1", method = RequestMethod.GET)
    @SneakyThrows
    public User test1() {
        return new User(1, "武扬岚", 20);
    }

    /**
     * 解析集合
     */
    @RequestMapping(value = "/test2", method = RequestMethod.GET)
    @SneakyThrows            //lombok注解抛异常
    public List test2() {
        List<User> list = new ArrayList<User>();
        list.add(new User(1, "武扬岚", 20));
        list.add(new User(2, "武扬岚", 20));
        list.add(new User(3, "武扬岚", 20));
        list.add(new User(4, "武扬岚", 20));
        return list;
    }

    /**
     * 解析Map
     */
    @RequestMapping(value = "/test3", method = RequestMethod.GET)
    @SneakyThrows              //lombok注解抛异常
    public HashMap test3() {
        HashMap<Integer, User> personMap = new HashMap<>();
        personMap.put(1, new User(1, "武扬岚", 20));
        personMap.put(2, new User(1, "武扬岚", 20));
        return personMap;
    }

    /**
     * 解析时间
     */
    @RequestMapping(value = "/test4", method = RequestMethod.GET)
    @SneakyThrows              //lombok注解抛异常
    public String test4() {
        return new SimpleDateFormat().format(new Date());
    } 
}

```
#### 测试2 需要使用JacksonUtils 
```html
<a th:href="@{/JSON_Gson/test5}">/JSON_Gson/test5</a>
<a th:href="@{/JSON_Gson/test6}">/JSON_Gson/test6</a>
<a th:href="@{/JSON_Gson/test7}">/JSON_Gson/test7</a>
<a th:href="@{/JSON_Gson/test8}">/JSON_Gson/test8</a>
```
```java
@RestController(value = "JSON_Gson")  // 组合注解
@RequestMapping("/JSON_Gson")
public class JSON_Gson {
    /**
     * 解析对象
     */
    @RequestMapping(value = "/test5", method = RequestMethod.GET)
    @SneakyThrows
    public String test5() {
        // toJson
        String str = GsonUtils.toJson(new User(1, "武扬岚", 20));
        System.out.println(str);
        // fromJson
        User user = GsonUtils.jsonToObj(str, User.class);
        System.out.println(user);
        return GsonUtils.toJson(new User(1, "武扬岚", 20));
    }


    /**
     * 解析集合
     */
    @RequestMapping(value = "/test6", method = RequestMethod.GET)
    @SneakyThrows            //lombok注解抛异常
    public String test6() {
        List<User> list = new ArrayList<User>();
        list.add(new User(1, "武扬岚", 20));
        list.add(new User(2, "武扬岚", 20));
        list.add(new User(3, "武扬岚", 20));
        list.add(new User(4, "武扬岚", 20));
        //toJson
        String str = GsonUtils.toJson(list);
        System.out.println(str);
        //fromJson
        List<User> lists = GsonUtils.jsonToList(str, new TypeToken<ArrayList<User>>() {
        }, User.class);
        lists.forEach((v) -> System.out.println(v));
        return GsonUtils.toJson(list);
    }

    /**
     * 解析Map
     */
    @RequestMapping(value = "/test7", method = RequestMethod.GET)
    @SneakyThrows              //lombok注解抛异常
    public String test7() {
        HashMap<Integer, User> personMap = new HashMap<>();
        personMap.put(1, new User(1, "武扬岚", 20));
        personMap.put(2, new User(1, "武扬岚", 20));
        //toJson
        String str = GsonUtils.toJson(personMap);
        System.out.println(str);
        //fromJson
        Map<Integer, User> map = GsonUtils.jsonToMap(str, new TypeToken<HashMap<Integer, User>>() {
        }, Integer.class, User.class);
        map.forEach((k, v) -> System.out.println(k + " " + v));
        return GsonUtils.toJson(personMap);
    }

    /**
     * 解析时间
     */
    @RequestMapping(value = "/test8", method = RequestMethod.GET)
    @SneakyThrows              //lombok注解抛异常
    public String test8() {
        //toJson
        String str = GsonUtils.toJson(new Date());
        System.out.println(str);
        //fromJson
        Date date = GsonUtils.jsonToObj(str, Date.class);
        System.out.println(date);
        return GsonUtils.toJson(new Date());
    }
}

```
## ajax
### axios
```html
<div id="axios">
    <a th:href="@{/axios}" @click="ToAxios">ToAxios</a>
</div>

<!-- 官网提供的 axios 在线地址 -->
<script src="https://unpkg.com/axios/dist/axios.min.js"></script>
<!-- 开发环境版本，包含了有帮助的命令行警告 -->
<script src="https://cdn.jsdelivr.net/npm/vue/dist/vue.js"></script>

<script type="text/javascript">
    const axios = new Vue({
        el: '#axios',
        methods: {
            ToAxios: function (event) {
                event.preventDefault();  // 取消链接的默认事件
                axios({
                    method: "post",      // 发送请求
                    url: event.target.href,
                    params: {
                        username: "武扬岚",
                        password: "999999",
                    }
                }).then(function (response) { // 响应成功
                    alert(response.data);
                });
            }
        }
    });
</script>
```
```java
@Controller(value = "ajaxController")
public class AjaxController {
    @ResponseBody
    @RequestMapping(name = "axios请求", value = "/axios", method = RequestMethod.POST)
    public String Axios_T(String username, String password) {
        System.out.println("username:"+username+",password:"+password);
        return "axios";
    }
}
```
# 文件上传和下载
### 文件下载
#### 方式一:
```html
<!--文件下载-->
<a th:href="@{/file/fileDown}">文件下载1</a> <br/>
```
```java
# 使用ResponseEntity实现下载文件的功能
@SneakyThrows
@RequestMapping(name = "下载文件", value= "/fileDown")
public ResponseEntity<byte[]> fileDown(HttpSession session) {

    String path = session.getServletContext().getRealPath("/static/upload");
    String fileName = "1.jpg";
        
    InputStream input = new FileInputStream(new File(path, fileName)); //创建输入流
    byte[] bytes = new byte[input.available()];//创建字节数组
    input.read(bytes); //将流读到字节数组中
        
    MultiValueMap<String, String> headers = new HttpHeaders(); //创建HttpHeaders对象设置响应头信息
    headers.add("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8")); //设置要下载方式以及下载文件的名字
    ResponseEntity<byte[]> responseEntity = new ResponseEntity<>(bytes, headers, HttpStatus.OK); //创建ResponseEntity对象
    input.close(); //关闭输入流
    return responseEntity;
}
```
#### 方式二:
```html
<!--文件下载-->
<a th:href="@{/file/down}">文件下载2</a> <br/>
```
```java
@SneakyThrows
@RequestMapping(name = "下载文件", value = "/down")
public String down(HttpServletRequest request, HttpServletResponse response) {

    String path = request.getServletContext().getRealPath("/static/upload");
    String fileName = "1.jpg";

    //设置response 响应头
    response.reset();    //设置页面不缓存,清空buffer
    response.setCharacterEncoding("UTF-8");            //字符编码
    response.setContentType("multipart/form-data");    //二进制传输数据
    response.setHeader("Content-Disposition",
            "attachment;fileName=" + URLEncoder.encode(fileName, "UTF-8"));

    InputStream input = new FileInputStream(new File(path, fileName));
    OutputStream out = response.getOutputStream();

    byte[] buff = new byte[1024];
    int index = 0;
    //执行 写出操作
    while ((index = input.read(buff)) != -1) {
        out.write(buff, 0, index);
        out.flush();
    }

    out.close();
    input.close();
    return null;
}
```
### 文件上传
#### 编写前端代码
请求方式必须为post
添加属性enctype="multipart/form-data"
```html
<!--文件上传-->
<form th:action="@{/file/fileUp}" method="post" enctype="multipart/form-data">
    <input type="file" name="file">
    <input type="submit" value="上传">
</form>
```
#### 添加依赖
```xml
<!--文件上传依赖-->
<!-- https://mvnrepository.com/artifact/commons-fileupload/commons-fileupload -->
<dependency>
       <groupId>commons-fileupload</groupId>
       <artifactId>commons-fileupload</artifactId>
       <version>1.4</version>
</dependency>
```
#### 配置文件解析器
SpringMVC的配置文件中添加配置：
```xml
<!--必须通过文件解析器的解析才能将文件转换为MultipartFile对象-->
<!--文件上传配置-->
<bean id="multipartResolver" class="org.springframework.web.multipart.commons.CommonsMultipartResolver">
   <!--设置请求编码，碧血和jsp的pageEncoding属性一致，以便于正确读取表单的内容，默认为IOS-1-->
   <property name="defaultEncoding" value="utf-8"/>
   <!--上传文件大小上限，单位为字节 -->
   <property name="maxUploadSize" value="1073741824"/>
   <property name="maxInMemorySize" value="1073741824"/>
   <!--resolveLazily属性启用是为了推迟文件解析，以便在UploadController 中捕获文件大小异常-->
   <property name="resolveLazily" value="true"/>
</bean>
```
#### 控制器方法
```java
@ResponseBody
@SneakyThrows
@RequestMapping(name = "上传文件", value = "/fileUp", method = RequestMethod.POST)
public String fileDown(@RequestParam("file") MultipartFile file, HttpSession session) {
    File realPath = new File(session.getServletContext().getRealPath("/static/file")); // 获取服务器上传文件的路径
    if (!realPath.exists()) realPath.mkdir();     // 文件夹不存在则创建
    String filename = file.getOriginalFilename(); // 获取文件名称
    file.transferTo(new File(realPath + File.separator + UUID.randomUUID().toString() + filename.substring(filename.lastIndexOf(".")))); // 上传文件
    return "success";
}
```
# 拦截器
## 三个抽象方法

- preHandle：控制器方法执行之前执行preHandle()

       其boolean类型的返回值表示是否拦截或放行
       返回true为放行，即调用控制器方法；返回false表示拦截，即不调用控制器方法

- postHandle：控制器方法执行之后执行postHandle()
- afterComplation：处理完视图和模型数据，渲染视图完毕之后执行afterComplation()
## 拦截器使用
用于拦截控制器方法的执行
### 编写拦截器
需要实现HandlerInterceptor接口 
```java
// 拦截器
@Component
public class FirstInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("preHandle");
        return true;  // true 放行  false拦截
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("postHandle");
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        System.out.println("afterCompletion");
    }
}
```
```java
// 拦截器
@Component(value = "secondInterceptor")
public class SecondInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("Second--->preHandle");
        return true;  // true 放行  false拦截
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("Second--->postHandle");
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        System.out.println("Second--->afterCompletion");
    }
}
```
### 配置拦截器
```xml
<!--配置拦截器-->
<mvc:interceptors>
    <!--两种方式对DispatcherServlet所处理的所有的请求进行拦截-->
    <bean class="interceptors.FirstInterceptor"></bean>
    <ref bean="secondInterceptor"></ref>   
</mvc:interceptors>
```
```xml
<!--配置拦截器-->
<mvc:interceptors>
    
    <!--对需要拦截的路径进行拦截-->
    <mvc:interceptor>
        <!--拦截-->
        <mvc:mapping path="/**"/>
        <!--排除-->
        <mvc:exclude-mapping path="/"/>
        <!--拦截规则-->
        <ref bean="firstInterceptor"></ref>
    </mvc:interceptor>
    
    <mvc:interceptor>
        <!--拦截-->
        <mvc:mapping path="/**"/>
        <!--排除-->
        <mvc:exclude-mapping path="/"/>
        <!--拦截规则-->
        <ref bean="secondInterceptor"></ref>
    </mvc:interceptor>
        
</mvc:interceptors>
```
## 源码分析
```java
    protected void doDispatch(HttpServletRequest request, HttpServletResponse response) throws Exception {
        HttpServletRequest processedRequest = request;
        HandlerExecutionChain mappedHandler = null;
        boolean multipartRequestParsed = false;
        WebAsyncManager asyncManager = WebAsyncUtils.getAsyncManager(request);

        try {
            try {
                ModelAndView mv = null;
                Object dispatchException = null;

                try {
                    processedRequest = this.checkMultipart(request);
                    multipartRequestParsed = processedRequest != request;
                    mappedHandler = this.getHandler(processedRequest);
                    if (mappedHandler == null) {
                        this.noHandlerFound(processedRequest, response);
                        return;
                    }

                    HandlerAdapter ha = this.getHandlerAdapter(mappedHandler.getHandler());
                    String method = request.getMethod();
                    boolean isGet = HttpMethod.GET.matches(method);
                    if (isGet || HttpMethod.HEAD.matches(method)) {
                        long lastModified = ha.getLastModified(request, mappedHandler.getHandler());
                        if ((new ServletWebRequest(request, response)).checkNotModified(lastModified) && isGet) {
                            return;
                        }
                    }
                    
// preHandle
if (!mappedHandler.applyPreHandle(processedRequest, response)) {  
    return;
}

// 视图解析                    
mv = ha.handle(processedRequest, response, mappedHandler.getHandler());  
                    
                    if (asyncManager.isConcurrentHandlingStarted()) {
                        return;
                    }

                    this.applyDefaultViewName(processedRequest, mv);   
// postHandle                 
mappedHandler.applyPostHandle(processedRequest, response, mv);  
                } catch (Exception var20) {
                    dispatchException = var20;
                } catch (Throwable var21) {
                    dispatchException = new NestedServletException("Handler dispatch failed", var21);
                }
// 渲染视图                
this.processDispatchResult(processedRequest, response, mappedHandler, mv, (Exception)dispatchException);
            } catch (Exception var22) {
                this.triggerAfterCompletion(processedRequest, response, mappedHandler, var22);
            } catch (Throwable var23) {
                this.triggerAfterCompletion(processedRequest, response, mappedHandler, new NestedServletException("Handler processing failed", var23));
            }
        } finally {
            if (asyncManager.isConcurrentHandlingStarted()) {
                if (mappedHandler != null) {
                    mappedHandler.applyAfterConcurrentHandlingStarted(processedRequest, response);
                }
            } else if (multipartRequestParsed) {
                this.cleanupMultipart(processedRequest);
            }

        }
    }

// 渲染视图     
    private void processDispatchResult(HttpServletRequest request, HttpServletResponse response, @Nullable HandlerExecutionChain mappedHandler, @Nullable ModelAndView mv, @Nullable Exception exception) throws Exception {
        boolean errorView = false;
        if (exception != null) {
            if (exception instanceof ModelAndViewDefiningException) {
                this.logger.debug("ModelAndViewDefiningException encountered", exception);
                mv = ((ModelAndViewDefiningException)exception).getModelAndView();
            } else {
                Object handler = mappedHandler != null ? mappedHandler.getHandler() : null;
                mv = this.processHandlerException(request, response, handler, exception);
                errorView = mv != null;
            }
        }

        if (mv != null && !mv.wasCleared()) {
            this.render(mv, request, response);
            if (errorView) {
                WebUtils.clearErrorRequestAttributes(request);
            }
        } else if (this.logger.isTraceEnabled()) {
            this.logger.trace("No view rendering, null ModelAndView returned.");
        }

        if (!WebAsyncUtils.getAsyncManager(request).isConcurrentHandlingStarted()) {
            
if (mappedHandler != null) {
// afterComplation                
mappedHandler.triggerAfterCompletion(request, response, (Exception)null);
}

        }
    }
```
```java
boolean applyPreHandle(HttpServletRequest request, HttpServletResponse response) throws Exception {
    // 拦截器List正序执行
    for(int i = 0; i < this.interceptorList.size(); this.interceptorIndex = i++) { 
        HandlerInterceptor interceptor = (HandlerInterceptor)this.interceptorList.get(i);
        // 拦截链 返回值
        if (!interceptor.preHandle(request, response, this.handler)) { 
            // 拦截返回false  拦截返回 执行该拦截器之前的triggerAfterCompletion
            this.triggerAfterCompletion(request, response, (Exception)null);
            return false;
        }
    }
    return true;
}

void applyPostHandle(HttpServletRequest request, HttpServletResponse response, @Nullable ModelAndView mv) throws Exception {
    // 拦截器List倒序执行
    for(int i = this.interceptorList.size() - 1; i >= 0; --i) {
        HandlerInterceptor interceptor = (HandlerInterceptor)this.interceptorList.get(i);
        interceptor.postHandle(request, response, this.handler, mv);
    }
}

void triggerAfterCompletion(HttpServletRequest request, HttpServletResponse response, @Nullable Exception ex) {
    // 拦截器List倒序执行
    for(int i = this.interceptorIndex; i >= 0; --i) {
        HandlerInterceptor interceptor = (HandlerInterceptor)this.interceptorList.get(i);
         try {
            interceptor.afterCompletion(request, response, this.handler, ex);
        } catch (Throwable var7) {
            logger.error("HandlerInterceptor.afterCompletion threw exception", var7);
        }
    }
}
```
## 多个拦截器的执行顺序
### 全为TRUE

- 多个拦截器的执行顺序和拦截器在SpringMVC的配置文件的配置顺序有关
- preHandle()会按照配置的顺序执行，而postHandle()和afterComplation()会按照配置的反序执行
### 存在FALSE

- 若某个拦截器的preHandle()返回了false
- preHandle()的返回值为false的拦截器  它和之前的拦截器的preHandle()都会执行

       postHandle()都不执行
       返回false的拦截器之前的拦截器的afterComplation()会执行
### 存在异常
直接执行afterComplation链 倒叙执行
# 异常处理器
## 基于配置的异常处理
SpringMVC提供了一个处理控制器方法执行过程中所出现的异常的接口：HandlerExceptionResolver
接口的实现类有：
       DefaultHandlerExceptionResolver 和 SimpleMappingExceptionResolver
SpringMVC提供了自定义的异常处理器SimpleMappingExceptionResolver，使用方式：
### 制造异常
```java
@ResponseBody
@RequestMapping("/exception")
public String Exception_T() {
    System.out.println(1 / 0);
    return "success";
}
```
### 配置异常处理器
```xml
<!--配置异常处器-->
<bean class="org.springframework.web.servlet.handler.SimpleMappingExceptionResolver">
    <property name="exceptionMappings">
        <props>
            <!--
            properties的键表示处理器方法执行过程中出现的异常
            properties的值表示若出现指定异常时，设置一个新的视图名称，跳转到指定页面
            -->
            <prop key="java.lang.ArithmeticException">error</prop>
        </props>
    </property>
    <!--exceptionAttribute属性设置一个属性名，将出现的异常信息在请求域中进行共享-->
    <property name="exceptionAttribute" value="exception"></property>
</bean>
```
### error页面
```html
<!DOCTYPE html>
<html lang="en" xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="UTF-8">
    <title>error</title>
</head>
<body>

error<br/>
<!--异常信息-->
<p th:text="${exception}"></p>

</body>
</html>
```
## 基于注解的异常处理
### 制造异常
```java
// 模拟异常
@ResponseBody
@RequestMapping("/exception")
public String Exception_T() {
    System.out.println(1 / 0);
    return "success";
}
```
### 配置异常处理器
```java
@ControllerAdvice
public class ExceptionController {
    // @ExceptionHandler用于设置所标识方法处理的异常
    @ExceptionHandler(ArithmeticException.class)
    public String handleArithmeticException(Exception exception, Model model){
        model.addAttribute("exception", exception);
        return "error";
    }
}
```
### error页面
```html
<!DOCTYPE html>
<html lang="en" xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="UTF-8">
    <title>error</title>
</head>
<body>

error<br/>
<!--异常信息-->
<p th:text="${exception}"></p>

</body>
</html>
```
# 注解配置
使用配置类和注解代替web.xml和SpringMVC配置文件的功能
## 创建 WebInit  代替web.xml

- 在Servlet3.0环境中，容器会在类路径中查找实现javax.servlet.ServletContainerInitializer接口的类，如果找到的话就用它来配置Servlet容器。
- Spring提供了这个接口的实现，名为SpringServletContainerInitializer，这个类反过来又会查找实现WebApplicationInitializer的类并将配置的任务交给它们来完成。
- Spring3.2引入了一个便利的WebApplicationInitializer基础实现，名为AbstractAnnotationConfigDispatcherServletInitializer，当我们的类扩展了AbstractAnnotationConfigDispatcherServletInitializer并将其部署到Servlet3.0容器的时候，容器会自动发现它，并用它来配置Servlet上下文。
```xml
<?xml version="1.0" encoding="UTF-8"?>
<web-app xmlns="http://xmlns.jcp.org/xml/ns/javaee"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://xmlns.jcp.org/xml/ns/javaee http://xmlns.jcp.org/xml/ns/javaee/web-app_4_0.xsd"
         version="4.0">

    <!--配置前端控制器 DispatcherServlet-->
    <servlet>
        <servlet-name>DispatcherServlet</servlet-name>
        <servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
        <!--载入SpringMVC配置文件-->
        <init-param>
            <param-name>contextConfigLocation</param-name>
            <param-value>classpath:SpringMVC.xml</param-value>
        </init-param>
        <!--启动等级1 tomcat实例启动的时候启动Servlet-->
        <load-on-startup>1</load-on-startup>
    </servlet>
    <servlet-mapping>
        <servlet-name>DispatcherServlet</servlet-name>
        <!--DispatcherServlet拦截路径-->
        <url-pattern>/</url-pattern>
    </servlet-mapping>

    <!--配置springMVC的编码过滤器-->
    <filter>
        <filter-name>CharacterEncodingFilter</filter-name>
        <filter-class>org.springframework.web.filter.CharacterEncodingFilter</filter-class>
        <init-param>
            <param-name>encoding</param-name>
            <param-value>UTF-8</param-value>
        </init-param>
        <init-param>
            <param-name>forceResponseEncoding</param-name>
            <param-value>true</param-value>
        </init-param>
    </filter>
    <filter-mapping>
        <filter-name>CharacterEncodingFilter</filter-name>
        <url-pattern>/*</url-pattern>
    </filter-mapping>

    <!--配置HiddenHttpMethodFilter 请求过滤器-->
    <filter>
        <filter-name>HiddenHttpMethodFilter</filter-name>
        <filter-class>org.springframework.web.filter.HiddenHttpMethodFilter</filter-class>
    </filter>
    <filter-mapping>
        <filter-name>HiddenHttpMethodFilter</filter-name>
        <url-pattern>/*</url-pattern>
    </filter-mapping>
    
</web-app>
```
```java
// 代替WEB.XML
public class WebInit extends AbstractAnnotationConfigDispatcherServletInitializer {

    // 指定Spring的配置类
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[]{SpringConfig.class};
    }

    // 指定SpringMVC的配置类
    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[]{WebConfig.class};
    }

    // 指定DispatcherServlet的映射规则，即url-pattern
    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};  // 路径
    }

    //添加过滤器
    @Override
    protected Filter[] getServletFilters() {

        // 配置springMVC的           编码过滤器
        CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
        characterEncodingFilter.setEncoding("utf-8");
        characterEncodingFilter.setForceResponseEncoding(true);

        // 配置HiddenHttpMethodFilter 请求过滤器
        HiddenHttpMethodFilter hiddenHttpMethodFilter = new HiddenHttpMethodFilter();

        return new Filter[]{characterEncodingFilter, hiddenHttpMethodFilter};
    }
}
```
## 创建SpringConfig配置类 代替spring的配置文件
```java
// Spring配置类
@Configuration
public class SpringConfig {
     // spring配置
}
```
## 创建WebConfig配置类 代替SpringMVC的配置文件
```xml
<?xml version="1.0" encoding="UTF8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:context="http://www.springframework.org/schema/context"
       xmlns:mvc="http://www.springframework.org/schema/mvc"
       xsi:schemaLocation="
       http://www.springframework.org/schema/beans
       http://www.springframework.org/schema/beans/spring-beans.xsd
       http://www.springframework.org/schema/context
       https://www.springframework.org/schema/context/spring-context.xsd
       http://www.springframework.org/schema/mvc
       https://www.springframework.org/schema/mvc/spring-mvc.xsd">

    <!-- 配置Thymeleaf视图解析器 -->
    <bean id="viewResolver" class="org.thymeleaf.spring5.view.ThymeleafViewResolver">
        <property name="order" value="1"/>
        <property name="characterEncoding" value="UTF-8"/>
        <property name="templateEngine">
            <bean class="org.thymeleaf.spring5.SpringTemplateEngine">
                <property name="templateResolver">
                    <bean class="org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver">

                        <!-- 视图前缀 -->
                        <property name="prefix" value="/WEB-INF/templates/"/>

                        <!-- 视图后缀 -->
                        <property name="suffix" value=".html"/>
                        <property name="templateMode" value="HTML5"/>
                        <property name="characterEncoding" value="UTF-8"/>
                    </bean>
                </property>
            </bean>
        </property>
    </bean>

    <!--必须通过文件解析器的解析才能将文件转换为MultipartFile对象-->
    <bean id="multipartResolver" class="org.springframework.web.multipart.commons.CommonsMultipartResolver"></bean>

    <!-- 自动扫描包 -->
    <context:component-scan base-package="controller pojo mapper interceptors"/>

    <!--
    path：设置处理的请求地址 view-name：设置请求地址所对应的视图名称
    SpringMVC中设置任何一个view-controller时，其他控制器中的请求映射将全部失效
    需要在SpringMVC的核心配置文件中设置 开启mvc注解驱动的标签
    -->
    <mvc:view-controller path="/" view-name="index"/>

    <!--开放对静态资源的访问 一定配置在mvc注解驱动的标签之前-->
    <mvc:default-servlet-handler/>

    <!--开启mvc注解驱动的标签-->
    <mvc:annotation-driven/>

    <!--解决json乱码配置 mvc注解驱动的标签-->
    <mvc:annotation-driven>
        <mvc:message-converters register-defaults="true">
            <bean class="org.springframework.http.converter.StringHttpMessageConverter">
                <constructor-arg value="UTF-8"/>
            </bean>
            <bean class="org.springframework.http.converter.json.MappingJackson2HttpMessageConverter">
                <property name="objectMapper">
                    <bean class="org.springframework.http.converter.json.Jackson2ObjectMapperFactoryBean">
                        <property name="failOnEmptyBeans" value="false"/>
                    </bean>
                </property>
            </bean>
        </mvc:message-converters>
    </mvc:annotation-driven>

    <!--配置拦截器-->
    <mvc:interceptors>

        <!--对DispatcherServlet所处理的所有的请求进行拦截-->
        <!--<bean class="interceptors.FirstInterceptor"></bean>-->
        <!--<ref bean="firstInterceptor"></ref>-->

        <!--设置需要拦截的路径-->
        <mvc:interceptor>
            <!--拦截-->
            <mvc:mapping path="/**"/>
            <!--排除-->
            <mvc:exclude-mapping path="/"/>
            <!--拦截规则-->
            <ref bean="firstInterceptor"></ref>
        </mvc:interceptor>

        <!--设置需要拦截的路径-->
        <mvc:interceptor>
            <!--拦截-->
            <mvc:mapping path="/**"/>
            <!--排除-->
            <mvc:exclude-mapping path="/"/>
            <!--拦截规则-->
            <ref bean="secondInterceptor"></ref>
        </mvc:interceptor>

    </mvc:interceptors>

    <!--配置异常处器-->
    <bean class="org.springframework.web.servlet.handler.SimpleMappingExceptionResolver">
        <property name="exceptionMappings">
            <props>
                <!--
                properties的键表示处理器方法执行过程中出现的异常
                properties的值表示若出现指定异常时，设置一个新的视图名称，跳转到指定页面
                -->
                <prop key="java.lang.ArithmeticException">error</prop>
            </props>
        </property>
        <!--exceptionAttribute属性设置一个属性名，将出现的异常信息在请求域中进行共享-->
        <property name="exceptionAttribute" value="exception"></property>
    </bean>

</beans>
```
```java
@Configuration  // 标识为一个配置类
@ComponentScan({"controller", "service", "mapper", "pojo"})   // 扫描组件
@EnableWebMvc                                                 // mvc注解驱动
public class WebConfig implements WebMvcConfigurer {

    //配置Thymeleaf视图解析器++++++++++++++++++++++++++++++++++++++++++++++++
    // 配置生成模板解析器
    @Bean
    public ITemplateResolver templateResolver() {
        WebApplicationContext webApplicationContext = ContextLoader.getCurrentWebApplicationContext();
        // ServletContextTemplateResolver需要一个ServletContext作为构造参数，可通过WebApplicationContext 的方法获得
        ServletContextTemplateResolver templateResolver = new ServletContextTemplateResolver(
                webApplicationContext.getServletContext());
        templateResolver.setPrefix("/WEB-INF/templates/");   // 前缀
        templateResolver.setSuffix(".html");                 // 后缀
        templateResolver.setCharacterEncoding("UTF-8");      // 编码格式
        templateResolver.setTemplateMode(TemplateMode.HTML); // 模板类型HTML文件
        return templateResolver;
    }

    // 生成模板引擎并为模板引擎注入模板解析器
    @Bean
    public SpringTemplateEngine templateEngine(ITemplateResolver templateResolver) {
        SpringTemplateEngine templateEngine = new SpringTemplateEngine();
        templateEngine.setTemplateResolver(templateResolver); // 设置templateResolver
        return templateEngine;
    }

    // 生成视图解析器并未解析器注入模板引擎
    @Bean
    public ViewResolver viewResolver(SpringTemplateEngine templateEngine) {
        ThymeleafViewResolver viewResolver = new ThymeleafViewResolver();
        viewResolver.setCharacterEncoding("UTF-8");           // 编码格式
        viewResolver.setTemplateEngine(templateEngine);       // 设置templateEngine
        return viewResolver;
    }
    //配置Thymeleaf视图解析器++++++++++++++++++++++++++++++++++++++++++++++++


    // 配置文件上传解析器 CommonsMultipartResolver
    @Bean
    public CommonsMultipartResolver multipartResolver() {
        return new CommonsMultipartResolver();
    }

    // 使用默认的servlet处理静态资源 DefaultServletHandling
    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        WebMvcConfigurer.super.configureDefaultServletHandling(configurer);
    }

    // 视图控制 ViewControllers
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        // 设置处理路径     设置跳转视图
        registry.addViewController("/").setViewName("index");
    }

    // 拦截器 Interceptors
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 设置拦截路径 排除路径
        registry.addInterceptor(new MyInterceptor()).addPathPatterns("/**").excludePathPatterns("/admin");
    }

    // 配置异常映射
    @Override
    public void configureHandlerExceptionResolvers(List<HandlerExceptionResolver> resolvers) {
        SimpleMappingExceptionResolver exceptionResolver = new SimpleMappingExceptionResolver();
        Properties prop = new Properties();
        prop.setProperty("java.lang.ArithmeticException", "error");  // 设置异常类型和跳转页面
        //设置异常映射
        exceptionResolver.setExceptionMappings(prop);
        //ExceptionAttribute属性设置一个属性名，将出现的异常信息在请求域中进行共享
        exceptionResolver.setExceptionAttribute("exception");
        resolvers.add(exceptionResolver);
    }
}
```
# 执行流程
## 常用组件

- DispatcherServlet：**前端控制器**，不需要工程师开发，由框架提供

       统一处理请求和响应，整个流程控制的中心，由它调用其它组件处理用户的请求

- HandlerMapping：**处理器映射器**，不需要工程师开发，由框架提供

       根据请求的url、method等信息查找Handler，即控制器方法

- Handler：**处理器**，需要工程师开发(controller)

       在DispatcherServlet的控制下Handler对具体的用户请求进行处理

- HandlerAdapter：**处理器适配器**，不需要工程师开发，由框架提供

       通过HandlerAdapter对处理器（控制器方法）进行执行

- ViewResolver：**视图解析器**，不需要工程师开发，由框架提供

       进行视图解析，得到相应的视图，例如：ThymeleafView、InternalResourceView、RedirectView

- View：**视图**

       将模型数据通过页面展示给用户
## DispatcherServlet初始化过程
DispatcherServlet 本质上是一个 Servlet，所以天然的遵循 Servlet 的生命周期。所以宏观上是 Servlet 生命周期来进行调度。
![image.png](https://cdn.nlark.com/yuque/0/2022/png/23219042/1646137176251-da462689-bf8e-47fe-8130-810fe02afa57.png#clientId=uf0da2c38-1ba4-4&crop=0&crop=0&crop=1&crop=1&from=paste&height=26&id=u425203dd&margin=%5Bobject%20Object%5D&name=image.png&originHeight=26&originWidth=878&originalType=binary&ratio=1&rotation=0&showTitle=false&size=21198&status=done&style=none&taskId=uaae70a73-21fe-4e86-9720-a6718cd30ff&title=&width=878)
```java
public interface Servlet {
    void init(ServletConfig var1) throws ServletException;
}
```
```java
public abstract class GenericServlet implements Servlet, ServletConfig, Serializable {
    public void init() throws ServletException {
    } 
}
```
```java
public abstract class HttpServletBean extends HttpServlet implements EnvironmentCapable, EnvironmentAware {
    public final void init() throws ServletException {
        PropertyValues pvs = new HttpServletBean.ServletConfigPropertyValues(this.getServletConfig(), this.requiredProperties);
        if (!pvs.isEmpty()) {
            try {
                BeanWrapper bw = PropertyAccessorFactory.forBeanPropertyAccess(this);
                ResourceLoader resourceLoader = new ServletContextResourceLoader(this.getServletContext());
                bw.registerCustomEditor(Resource.class, new ResourceEditor(resourceLoader, this.getEnvironment()));
                this.initBeanWrapper(bw);
                bw.setPropertyValues(pvs, true);
            } catch (BeansException var4) {
                if (this.logger.isErrorEnabled()) {
                    this.logger.error("Failed to set bean properties on servlet '" + this.getServletName() + "'", var4);
                }

                throw var4;
            }
        }

        this.initServletBean();
    }

    protected void initServletBean() throws ServletException {
    }
}
```
```java
public abstract class FrameworkServlet extends HttpServletBean implements ApplicationContextAware {
    
    protected final void initServletBean() throws ServletException {
        this.getServletContext().log("Initializing Spring " + this.getClass().getSimpleName() + " '" + this.getServletName() + "'");
        if (this.logger.isInfoEnabled()) {
            this.logger.info("Initializing Servlet '" + this.getServletName() + "'");
        }

        long startTime = System.currentTimeMillis();

        try {
            this.webApplicationContext = this.initWebApplicationContext();  // 初始化WebApplicationContext
            this.initFrameworkServlet();
        } catch (RuntimeException | ServletException var4) {
            this.logger.error("Context initialization failed", var4);
            throw var4;
        }

        if (this.logger.isDebugEnabled()) {
            String value = this.enableLoggingRequestDetails ? "shown which may lead to unsafe logging of potentially sensitive data" : "masked to prevent unsafe logging of potentially sensitive data";
            this.logger.debug("enableLoggingRequestDetails='" + this.enableLoggingRequestDetails + "': request parameters and headers will be " + value);
        }

        if (this.logger.isInfoEnabled()) {
            this.logger.info("Completed initialization in " + (System.currentTimeMillis() - startTime) + " ms");
        }

    }
    
}

```
```java
protected WebApplicationContext initWebApplicationContext() {
        WebApplicationContext rootContext = WebApplicationContextUtils.getWebApplicationContext(this.getServletContext());
        WebApplicationContext wac = null;
        if (this.webApplicationContext != null) {
            wac = this.webApplicationContext;
            if (wac instanceof ConfigurableWebApplicationContext) {
                ConfigurableWebApplicationContext cwac = (ConfigurableWebApplicationContext)wac;
                if (!cwac.isActive()) {
                    if (cwac.getParent() == null) {
                        cwac.setParent(rootContext);
                    }

                    this.configureAndRefreshWebApplicationContext(cwac);
                }
            }
        }

        if (wac == null) {
            wac = this.findWebApplicationContext();
        }

        if (wac == null) { 
            wac = this.createWebApplicationContext(rootContext);  // 创建WebApplicationContext
        }

        if (!this.refreshEventReceived) {
            synchronized(this.onRefreshMonitor) {
                this.onRefresh(wac);  // 刷新
            }
        }

        if (this.publishContext) {
            String attrName = this.getServletContextAttributeName();
            this.getServletContext().setAttribute(attrName, wac);
        }

        return wac;
    
        protected void onRefresh(ApplicationContext context) {    // 刷新
        }
    
    }
```
```java
protected WebApplicationContext createWebApplicationContext(@Nullable ApplicationContext parent) {
    Class<?> contextClass = getContextClass();
    if (!ConfigurableWebApplicationContext.class.isAssignableFrom(contextClass)) {
        throw new ApplicationContextException(
            "Fatal initialization error in servlet with name '" + getServletName() +
            "': custom WebApplicationContext class [" + contextClass.getName() +
            "] is not of type ConfigurableWebApplicationContext");
    }
    // 通过反射创建 IOC 容器对象
    ConfigurableWebApplicationContext wac =
        (ConfigurableWebApplicationContext) BeanUtils.instantiateClass(contextClass);

    wac.setEnvironment(getEnvironment());
    // 设置父容器
    wac.setParent(parent);
    String configLocation = getContextConfigLocation();
    if (configLocation != null) {
        wac.setConfigLocation(configLocation);
    }
    configureAndRefreshWebApplicationContext(wac);

    return wac;
}
```

```java
# FrameworkServlet创建WebApplicationContext后，刷新容器，调用onRefresh(wac)
# 此方法在DispatcherServlet中进行了重写，调用了initStrategies(context)方法，初始化策略 
# 即初始化DispatcherServlet的各个组件
protected void onRefresh(ApplicationContext context) {
        this.initStrategies(context);
}

# 初始化策略
protected void initStrategies(ApplicationContext context) {
   initMultipartResolver(context);
   initLocaleResolver(context);
   initThemeResolver(context);
   initHandlerMappings(context);
   initHandlerAdapters(context);
   initHandlerExceptionResolvers(context);
   initRequestToViewNameTranslator(context);
   initViewResolvers(context);
   initFlashMapManager(context);
}
```

## DispatcherServlet调用组件处理请求
![image.png](https://cdn.nlark.com/yuque/0/2022/png/23219042/1646139728386-0e6d715b-010f-4d40-915a-8ee5efdbc5bb.png#clientId=uf0da2c38-1ba4-4&crop=0&crop=0&crop=1&crop=1&from=paste&height=25&id=u22121cb3&margin=%5Bobject%20Object%5D&name=image.png&originHeight=25&originWidth=876&originalType=binary&ratio=1&rotation=0&showTitle=false&size=19227&status=done&style=none&taskId=u14e571b9-3c80-4527-a771-969707d8fd8&title=&width=876)
```java
public interface Servlet {
    void service(ServletRequest var1, ServletResponse var2) throws ServletException, IOException;
}
```
```java
public abstract class GenericServlet implements Servlet, ServletConfig, Serializable {
   public abstract void service(ServletRequest var1, ServletResponse var2) throws ServletException, IOException;
}
```
```java
public abstract class HttpServlet extends GenericServlet {
 
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String method = req.getMethod();
        long lastModified;
        if (method.equals("GET")) {
            lastModified = this.getLastModified(req);
            if (lastModified == -1L) {
                this.doGet(req, resp);
            } else {
                long ifModifiedSince = req.getDateHeader("If-Modified-Since");
                if (ifModifiedSince < lastModified) {
                    this.maybeSetLastModified(resp, lastModified);
                    this.doGet(req, resp);
                } else {
                    resp.setStatus(304);
                }
            }
        } else if (method.equals("HEAD")) {
            lastModified = this.getLastModified(req);
            this.maybeSetLastModified(resp, lastModified);
            this.doHead(req, resp);
        } else if (method.equals("POST")) {
            this.doPost(req, resp);
        } else if (method.equals("PUT")) {
            this.doPut(req, resp);
        } else if (method.equals("DELETE")) {
            this.doDelete(req, resp);
        } else if (method.equals("OPTIONS")) {
            this.doOptions(req, resp);
        } else if (method.equals("TRACE")) {
            this.doTrace(req, resp);
        } else {
            String errMsg = lStrings.getString("http.method_not_implemented");
            Object[] errArgs = new Object[]{method};
            errMsg = MessageFormat.format(errMsg, errArgs);
            resp.sendError(501, errMsg);
        }
    }
    
    public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
        if (req instanceof HttpServletRequest && res instanceof HttpServletResponse) {
            HttpServletRequest request = (HttpServletRequest)req;
            HttpServletResponse response = (HttpServletResponse)res;
            this.service(request, response);                                  // 类型转化
        } else {
            throw new ServletException("non-HTTP request or response");
        }
    }
    
}

```
```java
public abstract class FrameworkServlet extends HttpServletBean implements ApplicationContextAware {

    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpMethod httpMethod = HttpMethod.resolve(request.getMethod());
        if (httpMethod != HttpMethod.PATCH && httpMethod != null) {
            super.service(request, response);        // 父类service
        } else {
            this.processRequest(request, response);  // 重写servcie
        }
    }

    protected final void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        long startTime = System.currentTimeMillis();
        Throwable failureCause = null;
        LocaleContext previousLocaleContext = LocaleContextHolder.getLocaleContext();
        LocaleContext localeContext = this.buildLocaleContext(request);
        RequestAttributes previousAttributes = RequestContextHolder.getRequestAttributes();
        ServletRequestAttributes requestAttributes = this.buildRequestAttributes(request, response, previousAttributes);
        WebAsyncManager asyncManager = WebAsyncUtils.getAsyncManager(request);
        asyncManager.registerCallableInterceptor(FrameworkServlet.class.getName(), new FrameworkServlet.RequestBindingInterceptor());
        this.initContextHolders(request, localeContext, requestAttributes);

        try {
            
            // 执行服务，doService()是一个抽象方法，在DispatcherServlet中进行了重写
            this.doService(request, response);    
            
        } catch (IOException | ServletException var16) {
            failureCause = var16;
            throw var16;
        } catch (Throwable var17) {
            failureCause = var17;
            throw new NestedServletException("Request processing failed", var17);
        } finally {
            this.resetContextHolders(request, previousLocaleContext, previousAttributes);
            if (requestAttributes != null) {
                requestAttributes.requestCompleted();
            }

            this.logResult(request, response, (Throwable)failureCause, asyncManager);
            this.publishRequestHandledEvent(request, response, startTime, (Throwable)failureCause);
        }
    }
    
    // doService()抽象方法
    protected abstract void doService(HttpServletRequest request, HttpServletResponse response) throws Exception;
}
```
```java
public class DispatcherServlet extends FrameworkServlet {
    protected void doService(HttpServletRequest request, HttpServletResponse response) throws Exception {
        this.logRequest(request);
        Map<String, Object> attributesSnapshot = null;
        if (WebUtils.isIncludeRequest(request)) {
            attributesSnapshot = new HashMap();
            Enumeration attrNames = request.getAttributeNames();

            label116:
            while(true) {
                String attrName;
                do {
                    if (!attrNames.hasMoreElements()) {
                        break label116;
                    }

                    attrName = (String)attrNames.nextElement();
                } while(!this.cleanupAfterInclude && !attrName.startsWith("org.springframework.web.servlet"));

                attributesSnapshot.put(attrName, request.getAttribute(attrName));
            }
        }

        request.setAttribute(WEB_APPLICATION_CONTEXT_ATTRIBUTE, this.getWebApplicationContext());
        request.setAttribute(LOCALE_RESOLVER_ATTRIBUTE, this.localeResolver);
        request.setAttribute(THEME_RESOLVER_ATTRIBUTE, this.themeResolver);
        request.setAttribute(THEME_SOURCE_ATTRIBUTE, this.getThemeSource());
        if (this.flashMapManager != null) {
            FlashMap inputFlashMap = this.flashMapManager.retrieveAndUpdate(request, response);
            if (inputFlashMap != null) {
                request.setAttribute(INPUT_FLASH_MAP_ATTRIBUTE, Collections.unmodifiableMap(inputFlashMap));
            }

            request.setAttribute(OUTPUT_FLASH_MAP_ATTRIBUTE, new FlashMap());
            request.setAttribute(FLASH_MAP_MANAGER_ATTRIBUTE, this.flashMapManager);
        }

        RequestPath previousRequestPath = null;
        if (this.parseRequestPath) {
            previousRequestPath = (RequestPath)request.getAttribute(ServletRequestPathUtils.PATH_ATTRIBUTE);
            ServletRequestPathUtils.parseAndCache(request);
        }

        try {
            
            // 处理请求和响应  doDispatch(request, response);
            this.doDispatch(request, response);
            
        } finally {
            if (!WebAsyncUtils.getAsyncManager(request).isConcurrentHandlingStarted() && attributesSnapshot != null) {
                this.restoreAttributesAfterInclude(request, attributesSnapshot);
            }

            if (this.parseRequestPath) {
                ServletRequestPathUtils.setParsedRequestPath(previousRequestPath, request);
            }

        }

    }

}

```
```java
protected void doDispatch(HttpServletRequest request, HttpServletResponse response) throws Exception {
    HttpServletRequest processedRequest = request;
    HandlerExecutionChain mappedHandler = null;
    boolean multipartRequestParsed = false;

    WebAsyncManager asyncManager = WebAsyncUtils.getAsyncManager(request);

    try {
        ModelAndView mv = null;
        Exception dispatchException = null;

        try {
            processedRequest = checkMultipart(request);
            multipartRequestParsed = (processedRequest != request);

            // Determine handler for the current request.
            /*
            	mappedHandler：调用链
                包含handler、interceptorList、interceptorIndex
            	handler：浏览器发送的请求所匹配的控制器方法
            	interceptorList：处理控制器方法的所有拦截器集合
            	interceptorIndex：拦截器索引，控制拦截器afterCompletion()的执行
            */
            mappedHandler = getHandler(processedRequest);
            if (mappedHandler == null) {
                noHandlerFound(processedRequest, response);
                return;
            }

            // Determine handler adapter for the current request.
           	// 通过控制器方法创建相应的处理器适配器，调用所对应的控制器方法
            HandlerAdapter ha = getHandlerAdapter(mappedHandler.getHandler());

            // Process last-modified header, if supported by the handler.
            String method = request.getMethod();
            boolean isGet = "GET".equals(method);
            if (isGet || "HEAD".equals(method)) {
                long lastModified = ha.getLastModified(request, mappedHandler.getHandler());
                if (new ServletWebRequest(request, response).checkNotModified(lastModified) && isGet) {
                    return;
                }
            }
			
            // 调用拦截器的preHandle()
            if (!mappedHandler.applyPreHandle(processedRequest, response)) {
                return;
            }

            // Actually invoke the handler.
            // 由处理器适配器 调用具体的控制器方法 ，最终获得ModelAndView对象
            mv = ha.handle(processedRequest, response, mappedHandler.getHandler());

            if (asyncManager.isConcurrentHandlingStarted()) {
                return;
            }

            applyDefaultViewName(processedRequest, mv);
            // 调用拦截器的postHandle()
            mappedHandler.applyPostHandle(processedRequest, response, mv);
        }
        catch (Exception ex) {
            dispatchException = ex;
        }
        catch (Throwable err) {
            // As of 4.3, we're processing Errors thrown from handler methods as well,
            // making them available for @ExceptionHandler methods and other scenarios.
            dispatchException = new NestedServletException("Handler dispatch failed", err);
        }
// 后续处理：处理模型数据和渲染视图
        processDispatchResult(processedRequest, response, mappedHandler, mv, dispatchException);
    }
    catch (Exception ex) {
        triggerAfterCompletion(processedRequest, response, mappedHandler, ex);
    }
    catch (Throwable err) {
        triggerAfterCompletion(processedRequest, response, mappedHandler,
                               new NestedServletException("Handler processing failed", err));
    }
    finally {
        if (asyncManager.isConcurrentHandlingStarted()) {
            // Instead of postHandle and afterCompletion
            if (mappedHandler != null) {
                mappedHandler.applyAfterConcurrentHandlingStarted(processedRequest, response);
            }
        }
        else {
            // Clean up any resources used by a multipart request.
            if (multipartRequestParsed) {
                cleanupMultipart(processedRequest);
            }
        }
    }
}
```

```java
private void processDispatchResult(HttpServletRequest request, HttpServletResponse response,
                                   @Nullable HandlerExecutionChain mappedHandler, @Nullable ModelAndView mv,
                                   @Nullable Exception exception) throws Exception {

    boolean errorView = false;

    if (exception != null) {
        if (exception instanceof ModelAndViewDefiningException) {
            logger.debug("ModelAndViewDefiningException encountered", exception);
            mv = ((ModelAndViewDefiningException) exception).getModelAndView();
        }
        else {
            Object handler = (mappedHandler != null ? mappedHandler.getHandler() : null);
            mv = processHandlerException(request, response, handler, exception);
            errorView = (mv != null);
        }
    }

    // Did the handler return a view to render?
    if (mv != null && !mv.wasCleared()) {
        // 处理模型数据和渲染视图
        render(mv, request, response);
        if (errorView) {
            WebUtils.clearErrorRequestAttributes(request);
        }
    }
    else {
        if (logger.isTraceEnabled()) {
            logger.trace("No view rendering, null ModelAndView returned.");
        }
    }

    if (WebAsyncUtils.getAsyncManager(request).isConcurrentHandlingStarted()) {
        // Concurrent handling started during a forward
        return;
    }

    if (mappedHandler != null) {
        // Exception (if any) is already handled..
        // 调用拦截器的afterCompletion()
        mappedHandler.triggerAfterCompletion(request, response, null);
    }
}
```

## 执行流程


![image.png](https://cdn.nlark.com/yuque/0/2022/png/23219042/1646184910954-adfd6e86-dfd8-49cd-86c0-3d2411851202.png#clientId=u42f2b64b-a41d-4&crop=0&crop=0&crop=1&crop=1&from=paste&height=281&id=u36c3e9c0&margin=%5Bobject%20Object%5D&name=image.png&originHeight=281&originWidth=609&originalType=binary&ratio=1&rotation=0&showTitle=false&size=100544&status=done&style=none&taskId=uc0684726-f44c-4b36-aaf3-44609a2efe2&title=&width=609)
![image.png](https://cdn.nlark.com/yuque/0/2022/png/23219042/1646184936666-c305a1b8-12ee-448e-a34c-6857bab26d5e.png#clientId=u42f2b64b-a41d-4&crop=0&crop=0&crop=1&crop=1&from=paste&height=320&id=u54f9ee81&margin=%5Bobject%20Object%5D&name=image.png&originHeight=320&originWidth=630&originalType=binary&ratio=1&rotation=0&showTitle=false&size=155469&status=done&style=none&taskId=uff3b08df-6e29-40e7-9e11-b8a725777b0&title=&width=630)

1. 用户向服务器发送请求，请求被SpringMVC 前端控制器 DispatcherServlet捕获。 
2. DispatcherServlet对请求URL进行解析，得到请求资源标识符（URI），判断请求URI对应的映射： 
### 映射不存在

---

是否配置了mvc:default-servlet-handler   ( 开放对静态资源的访问 一定配置在mvc注解驱动的标签之前 )

- 如果没配置，则控制台报映射查找不到，客户端展示404错误

![image.png](https://cdn.nlark.com/yuque/0/2022/png/23219042/1646183103358-a86dc254-667f-418f-aa0d-90422a1d6284.png#clientId=u42f2b64b-a41d-4&crop=0&crop=0&crop=1&crop=1&from=paste&height=251&id=ua05e7bd4&margin=%5Bobject%20Object%5D&name=image.png&originHeight=251&originWidth=746&originalType=binary&ratio=1&rotation=0&showTitle=false&size=121023&status=done&style=none&taskId=u1c4fe3c9-5767-48cf-a911-7bb105d93fc&title=&width=746)

- 如果有配置，则访问目标资源（一般为静态资源，如：JS,CSS,HTML），找不到客户端也会展示404错误

![image.png](https://cdn.nlark.com/yuque/0/2022/png/23219042/1646183175207-f2503c1b-69e0-4a8c-8da5-1a6c0e7d1f0f.png#clientId=u42f2b64b-a41d-4&crop=0&crop=0&crop=1&crop=1&from=paste&height=249&id=u99f5464a&margin=%5Bobject%20Object%5D&name=image.png&originHeight=249&originWidth=755&originalType=binary&ratio=1&rotation=0&showTitle=false&size=110333&status=done&style=none&taskId=u011e8c16-f392-4bce-9f87-cb5eb461df7&title=&width=755)
### 映射不存在

---

1. 根据该URI，调用HandlerMapping获得该Handler配置的所有相关的对象（包括Handler对象以及Handler对象对应的拦截器），最后以HandlerExecutionChain执行链对象的形式返回。 
1. DispatcherServlet 根据获得的Handler，选择一个合适的HandlerAdapter。 
1. 如果成功获得HandlerAdapter，此时将开始执行拦截器的preHandler方法【正向】 
1. 提取Request中的模型数据，填充Handler入参，开始执行Handler（Controller)方法，处理请求。在填充Handler的入参过程中，根据你的配置，Spring将帮你做一些额外的工作：

 

- HttpMessageConveter： 将请求消息（如Json、xml等数据）转换成一个对象，将对象转换为指定的响应信息
- 数据转换：对请求消息进行数据转换。如String转换成Integer、Double等
- 数据格式化：对请求消息进行数据格式化。 如将字符串转换成格式化数字或格式化日期等
- 数据验证： 验证数据的有效性（长度、格式等），验证结果存储到BindingResult或Error中

5. Handler执行完成后，向DispatcherServlet 返回一个ModelAndView对象。 
5. 此时将开始执行拦截器的postHandle方法【逆向】。 
5. 根据返回的ModelAndView（此时会判断是否存在异常：如果存在异常，则执行HandlerExceptionResolver进行异常处理）选择一个适合的ViewResolver进行视图解析，根据Model和View，来渲染视图。 
5. 渲染视图完毕执行拦截器的afterCompletion方法【逆向】。 
5. 将渲染结果返回给客户端。 

