package com.inshare.fluxdemo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

/**
 * 最基础注解式 Reactor 反应式请求（异步）
 */
@RestController
public class BasicController {

    @GetMapping("/hello")
    public Mono<String> hello() {
        return Mono.just("Hello WebFlux");
    }
}
