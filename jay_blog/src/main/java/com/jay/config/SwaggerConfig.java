package com.jay.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Created by xiang.wei on 2018/10/15
 *
 * @author xiang.wei
 */
@Configuration  //让Spring来加载该类配置
@EnableSwagger2  //注解来启用Swagger2
public class SwaggerConfig {
    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                //扫描该包下所有Controller定义的API,并产生文档内容（除了被@ApiIgnore指定的请求）。
                .apis(RequestHandlerSelectors.basePackage("com.jay.web"))
                .paths(PathSelectors.any())
                .build()
                ;
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("个人博客项目")
                .description("第一个项目要认真对待")
                .termsOfServiceUrl("http://example.org/api/")
                .version("1.0")
                .build();
    }
}
