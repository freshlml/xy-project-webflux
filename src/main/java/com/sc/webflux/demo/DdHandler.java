package com.sc.webflux.demo;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;



/**
 * @Author yangmingzi    <br>
 * @CreateDate 2020/4/16   <br>
 * @Descrption TODO      <br>
 */

@Component
public class DdHandler {

    public Mono<ServerResponse> demo(ServerRequest request) {
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(BodyInserters.fromObject("hello 中"));
    }





}
