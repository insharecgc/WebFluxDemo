package com.inshare.fluxdemo.service;

import com.inshare.fluxdemo.domain.User;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class UserService {
    private final Map<String, User> data = new ConcurrentHashMap<>();

    public UserService() {
        User user = new User("U001","Cai蔡",27);
        data.put(user.getId(),user);
    }

    public Flux<User> list() {
        return Flux.fromIterable(data.values());
    }

    public Flux<User> getById(final Flux<String> ids) {
        return ids.flatMap(id -> Mono.justOrEmpty(data.get(id)));
    }

    public Mono<User> getById(final String id) {
        return Mono.justOrEmpty(data.get(id)).switchIfEmpty(Mono.error(new Exception()));
    }

    public Mono<User> createOrUpdate(final User user) {
        data.put(user.getId(), user);
        return Mono.just(user);
    }

    public Mono<User> delete(final String id) {
        return Mono.justOrEmpty(data.remove(id));
    }
}
