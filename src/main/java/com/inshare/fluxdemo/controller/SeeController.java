package com.inshare.fluxdemo.controller;

import org.springframework.http.codec.ServerSentEvent;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.util.function.Tuples;

import java.time.Duration;
import java.util.concurrent.ThreadLocalRandom;

/**
 * 反应式服务器推送事件示例
 * 启动后，用curl命令测试 curl http://localhost:8088/sse/random
 */
@RestController
@RequestMapping("/see")
public class SeeController {

    @GetMapping("/random")
    public Flux<ServerSentEvent<Integer>> randomNumbers() {
        return Flux.interval(Duration.ofSeconds(2))
                .map(seq -> Tuples.of(seq, ThreadLocalRandom.current().nextInt()))
                .map(data -> ServerSentEvent.<Integer>builder()
                    .event("random")
                    .id(Long.toString(data.getT1()))
                    .data(data.getT2())
                    .build());
    }
}
