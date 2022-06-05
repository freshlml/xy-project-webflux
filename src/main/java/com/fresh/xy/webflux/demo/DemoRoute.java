package com.fresh.xy.webflux.demo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

/**
 * @Author yangmingzi    <br>
 * @CreateDate 2020/4/16   <br>
 * @Descrption TODO      <br>
 */
@Configuration
public class DemoRoute {

    @Bean
    public RouterFunction<ServerResponse> ddRoute(DdHandler ddHandler) {
        return RouterFunctions.route(RequestPredicates.GET("/demo").and(RequestPredicates.accept(MediaType.APPLICATION_JSON)),
                ddHandler::demo);
    }

}
