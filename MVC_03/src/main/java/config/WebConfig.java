package config;


import interceptor.MyInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ITemplateResolver;
import org.thymeleaf.templateresolver.ServletContextTemplateResolver;

import java.util.List;
import java.util.Properties;

/**
 * @author by pepsi-wyl
 * @date 2022-03-01 17:13
 */

// 代替SpringMVC配置文件
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
//    @Bean
//    public CommonsMultipartResolver multipartResolver() {
//        return new CommonsMultipartResolver();
//    }

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
//        registry.addInterceptor(new MyInterceptor()).addPathPatterns("/**").excludePathPatterns("/admin");
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
