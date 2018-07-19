package com.inshare.fluxdemo.controller;

import com.inshare.fluxdemo.domain.User;
import com.inshare.fluxdemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Objects;

/**
 * 通过注解方式实现Reactor反应式编程
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Resource not found")
    @ExceptionHandler(Exception.class)
    public void notFound() {
    }

    /**
     * Flux和Mono说明：
     * Flux可以触发零到多个事件，并根据实际情况结束处理或触发错误
     * Mono最多只触发一个事件
     *
     */

    @GetMapping("/list")
    public Flux<User> list() {
        return userService.list();
    }

    @GetMapping("/{id}")
    public Mono<User> getById(@PathVariable("id") String id) {
        return userService.getById(id);
    }

    /**
     *
     * @param user 通过post提交参数，不用任何注解修饰，会自动将json转变为对象
     * @return
     */
    @PostMapping("/add")
    public Mono<User> create(User user) {
        return userService.createOrUpdate(user);
    }

    @PutMapping("/{id}")
    public Mono<User> update(@PathVariable("id") String id, User user) {
        Objects.requireNonNull(user);
        user.setId(id);
        return userService.createOrUpdate(user);
    }

    @DeleteMapping("/{id}")
    public Mono<User> delete(@PathVariable("id") String id) {
        return userService.delete(id);
    }
}
