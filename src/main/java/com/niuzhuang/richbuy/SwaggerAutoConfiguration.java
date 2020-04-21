package com.niuzhuang.richbuy;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

/**
 * Created on 2017/6/22.
 */
@Configuration
@EnableSwagger2
@ConditionalOnProperty(prefix = "site-web", name = "swagger-open", havingValue = "true", matchIfMissing = true)
public class SwaggerAutoConfiguration {
    @Value(value = "${spring.application.name:Swagger API list}")
    private String title;

    @Value(value = "${spring.application.description:This is to show all swagger controller API description}")
    private String description;

    @Value(value = "${spring.application.version:1.0.0}")
    private String version;

    @Value(value = "${spring.application.contact:huimeng.li@mobilemd.cn}")
    private String contact;

    ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title(title)
                .description(description)
                .license("Apache 2.0")
                .licenseUrl("http://www.apache.org/licenses/LICENSE-2.0.html")
                .termsOfServiceUrl("")
                .version(version)
                .contact(new Contact("", "", contact))
                .build();
    }

    @Bean
    public Docket createRestApi() {
        List<Parameter> headerParams = new ArrayList<>();

        headerParams.add(createCommonParam("TM-Header-TenantId", "e4bf26830e814f5992dda82c0097b713"));
        headerParams.add(createCommonParam("TM-Header-UserId", "297e05005c2ecce1015c2ed24eaf0000"));
        headerParams.add(createCommonParam("TM-Header-UserName", ""));
        headerParams.add(createCommonParam("TM-Header-AccountId"));
        headerParams.add(createCommonParam("TM-Header-UserAgent"));
        headerParams.add(createCommonParam("TM-Header-UserIp"));
        headerParams.add(createCommonParam("TM-Header-Locale", "zh_CN"));
        headerParams.add(createCommonParam("TM-Header-Token", ""));

        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .useDefaultResponseMessages(false)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.niuzhuang.richbuy"))
                .paths(PathSelectors.any())
                .build()
                .globalOperationParameters(headerParams);
    }

    private Parameter createCommonParam(String name, String defaultValue) {
        ParameterBuilder param = new ParameterBuilder();

        ParameterBuilder pb = param.name(name).description("通用参数").modelRef(new ModelRef("string")).parameterType("header").required(false);
        Parameter newParam = null != defaultValue ? pb.build() : pb.defaultValue(defaultValue).build();
        return newParam;

    }

    private Parameter createCommonParam(String name) {
        return createCommonParam(name, null);
    }
}