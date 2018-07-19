package com.inshare.fluxdemo.config;

import com.inshare.fluxdemo.domain.User;
import com.inshare.fluxdemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;

/**
 * 路由器函数配置
 */
@Configuration
public class RouterConfig {

    /**
     * Servlet
     *  请求接口： ServletRequest 或者 HttpServletRequest
     *  响应接口： ServletResponse 或者 HttpServletResponse
     *
     * Spring 5.0 重新定义了服务请求和响应接口
     *  请求接口： ServerRequest
     *  响应接口： ServerResponse
     *
     * 本例，定义GET请求，并返回用户对象 URI: /user/findAll
     * Reactive 中的 Flux 或 Mono 它是异步处理（非阻塞）
     * 集合对象基本上是同步处理（阻塞
     *
     */
    @Bean
    @Autowired
    public RouterFunction<ServerResponse> findAllUser(UserService userService) {
        return RouterFunctions.route(RequestPredicates.GET("/user/findAll"),
                request -> {
                    //返回所有用户对象
                    Flux<User> userFlux = userService.list();
                    return ServerResponse.ok().body(userFlux, User.class);
                });
    }
}
