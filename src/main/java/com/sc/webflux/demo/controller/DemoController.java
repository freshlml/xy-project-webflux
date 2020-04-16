package com.sc.webflux.demo.controller;

import com.sc.webflux.demo.entity.Demo;
import com.sc.webflux.demo.handler.DemoHandler;
import com.sc.webflux.demo.repository.DemoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.util.Collection;
import java.util.List;

/**
 * @Author yangmingzi    <br>
 * @CreateDate 2020/4/16   <br>
 * @Descrption TODO      <br>
 */
@RestController
@RequestMapping("/demo")
public class DemoController {

    @Autowired
    private DemoHandler demoHandler;
    @Autowired
    private DemoRepository demoRepository;

    @PostMapping("/save")
    public Mono<Long> saveDemo(@RequestBody Demo demo) {
        Mono<Long> mono = demoHandler.save(demo);
        return mono;
    }

    @GetMapping("/list")
    public Flux<Demo> listAll() {
        System.out.println("Controller 执行线程: " + Thread.currentThread().getName());
        Flux<Demo> flux = demoHandler.findAll();
        return flux;
    }

    @GetMapping("/directList")
    public Collection<Demo> directListAll() {
        System.out.println("Controller 执行线程: " + Thread.currentThread().getName());
        return demoRepository.selectAll();
    }

    @GetMapping("/otherList")
    public Flux<Integer> otherList() {
        //TODO can not run success, and do not know why?
        System.out.println("1-执行线程: " + Thread.currentThread().getName());
        Flux<Integer> flux = Flux.create(sink -> {
            System.out.println("2-执行线程: " + Thread.currentThread().getName());
            sink.next(1);
            sink.next(2);
            sink.next(3);
        });
        return flux;
    }

    @GetMapping("/otherList2")
    public Mono<List<Demo>> otherList2() {
        //TODO 两处线程ID一样
        System.out.println("1-执行线程: " + Thread.currentThread().getId());
        Mono<List<Demo>> mono = Mono.create(sink -> {
            System.out.println("2-执行线程: " + Thread.currentThread().getId());
            List<Demo> demoList = demoRepository.selectAll();
            sink.success(demoList);
        });
        //mono.subscribeOn(Schedulers.newParallel("parallel-scheduler", 4));  //无效

        return mono;
    }


    @GetMapping("/getById")
    public Mono<Demo> getById(@RequestParam("id") Long id) {
        System.out.println("Controller 执行线程: " + Thread.currentThread().getName());
        Mono<Demo> mono = demoHandler.findById(id);
        return mono;
    }

    @PostMapping("/update")
    public Mono<Long> updateDemo(@RequestBody Demo demo) {
        return demoHandler.update(demo);
    }

    @PostMapping("/delete")
    public Mono<Long> deleteDemo(@RequestBody Demo demo) {
        return demoHandler.delete(demo.getId());
    }

}
