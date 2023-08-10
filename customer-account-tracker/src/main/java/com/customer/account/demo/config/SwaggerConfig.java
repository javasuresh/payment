package com.customer.account.demo.config;

import com.customer.account.demo.constant.ApiHeaderConstants;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.RequestParameterBuilder;
import springfox.documentation.service.ParameterType;
import springfox.documentation.service.RequestParameter;
import springfox.documentation.service.Tag;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket produceApi() {
        return new Docket(DocumentationType.OAS_30)
            .useDefaultResponseMessages(false)
            .apiInfo(new ApiInfoBuilder()
                .title("CUSTOMER ACCOUNT TRACKER")
                .description("SURESH PAY Wallet Service")
                .version("1.0")
                .build())
               .globalRequestParameters(getGlobalParameterList())
            .tags(new Tag(ServiceTags.WALLET_SERVICE, "Wallet service"))
            .select().apis(RequestHandlerSelectors.withClassAnnotation(RestController.class))
            .build();
    }

    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static class ServiceTags {
        public static final String WALLET_SERVICE = "Wallet Service";
        public static final String ACCOUNT_SERVICE = "Account Service";
        public static final String CUSTOMER_SERVICE = "Customer Service";

    }

    private List<RequestParameter> getGlobalParameterList() {
        return Stream.concat(
                        ApiHeaderConstants.getOptionalApiHeaders().stream()
                                .map(header -> new RequestParameterBuilder()
                                        .name(header)
                                        .in(ParameterType.HEADER)
                                        .required(false)
                                        .build()),
                        ApiHeaderConstants.getMandatoryApiHeaders().stream()
                                .map(header -> new RequestParameterBuilder()
                                        .name(header)
                                        .in(ParameterType.HEADER)
                                        .required(true)
                                        .build()))
                .collect(Collectors.toList());
    }
}