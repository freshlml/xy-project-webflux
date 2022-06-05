package com.fresh.xy.webflux.demo.handler;

import com.fresh.xy.webflux.demo.entity.Demo;
import com.fresh.xy.webflux.demo.repository.DemoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @Author yangmingzi    <br>
 * @CreateDate 2020/4/16   <br>
 * @Descrption TODO      <br>
 */
@Component
public class DemoHandler {

    @Autowired
    private DemoRepository demoRepository;

    public Mono<Long> save(Demo demo) {
        Mono<Long> mono = Mono.create(sink -> {
            sink.success(demoRepository.insert(demo));
        });
        return mono;
    }

    public Flux<Demo> findAll() {
        System.out.println("Handler执行线程: " + Thread.currentThread().getName());
        return Flux.fromIterable(demoRepository.selectAll());
    }

    public Flux<Demo> otherList() {
        System.out.println("Handler执行线程: " + Thread.currentThread().getName());
        return Flux.create(sink -> {
            System.out.println("Flux create执行线程: " + Thread.currentThread().getName());
            Demo demo = demoRepository.selectById(1L);
            sink.next(demo);
        });
    }

    public Mono<Demo> findById(Long id) {
        System.out.println("Handler执行线程: " + Thread.currentThread().getName());
        Mono<Demo> mono = Mono.create(sink -> {
            sink.success(demoRepository.selectById(id));
        });
        return mono;
    }

    public Mono<Long> update(Demo demo) {
        return Mono.create(sink -> {
            sink.success(demoRepository.update(demo));
        });
    }

    public Mono<Long> delete(Long id) {
        return Mono.create(sink -> {
            sink.success(demoRepository.delete(id));
        });
    }

}
