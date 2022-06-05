package com.fresh.xy.webflux.demo.repository;

import com.fresh.xy.webflux.demo.entity.Demo;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @Author yangmingzi    <br>
 * @CreateDate 2020/4/16   <br>
 * @Descrption TODO      <br>
 */
@Repository
public class DemoRepository {

    private ConcurrentMap<Long, Demo> repository = new ConcurrentHashMap<>();

    private static final AtomicLong idGenerator = new AtomicLong(0);

    public Long insert(Demo demo) {
        Long id = idGenerator.incrementAndGet();
        demo.setId(id);
        demo.setCreateTime(LocalDateTime.now());
        demo.setModifyTime(LocalDateTime.now());
        repository.put(id, demo);
        return id;
    }

    public List<Demo> selectAll() {
        System.out.println("Repository执行线程: " + Thread.currentThread().getId());
        /*try {
            Thread.sleep(100000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
        Collection<Demo> list = repository.values();
        List<Demo> result = new ArrayList<>();
        result.addAll(list);
        return result;
    }


    public Demo selectById(Long id) {
        System.out.println("Repository执行线程: " + Thread.currentThread().getName());
        return repository.get(id);
    }

    public Long update(Demo demo) {
        repository.put(demo.getId(), demo);
        demo.setModifyTime(LocalDateTime.now());
        return demo.getId();
    }

    public Long delete(Long id) {
        repository.remove(id);
        return id;
    }

}
