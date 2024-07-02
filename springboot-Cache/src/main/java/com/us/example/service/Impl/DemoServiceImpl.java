package com.us.example.service.Impl;

import com.us.example.bean.Person;
import com.us.example.dao.PersonRepository;
import com.us.example.service.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * Created by yangyibo on 17/1/13.
 */
@Service
public class DemoServiceImpl implements DemoService {

    @Autowired
    private PersonRepository personRepository;

    @Override
    //@CachePut缓存新增的或更新的数据到缓存,其中缓存名字是 people 。数据的key是person的id
    @CachePut(value = "people", key = "#person.id")
    public Person save(Person person) {
        Person p = personRepository.save(person);
        System.out.println("为id、key为:"+p.getId()+"数据做了缓存");
        return p;
    }

    @Override
    //@CacheEvict 从缓存people中删除key为id 的数据
    @CacheEvict(value = "people")
    public void remove(Long id) {
        System.out.println("删除了id、key为"+id+"的数据缓存");
        //这里不做实际删除操作
    }

    @Override
    //@Cacheable缓存key为person 的id 数据到缓存people 中,如果没有指定key则方法参数作为key保存到缓存中。
    //【自查解释】：在Spring Boot缓存注解中，"key"是用于唯一标识缓存数据的键，这个键的取值由表达式"#person.id"指定。
    //           这里的表达式指的是从方法参数person对象中获取id属性的值作为缓存的键。
    //           简单来说，当使用缓存注解对一个方法进行缓存时，相同参数传入的数据将被缓存起来并使用相同的键进行存储和取值。在这种情况下，只有当传入参数的id属性值相等时，才会获取到相同的缓存数据。
    @Cacheable(value = "people", key = "#person.id") //就是个map
    public Person findOne(Person person) {
        Person p = personRepository.findOne(person.getId());
        System.out.println("为id、key为:"+p.getId()+"数据做了缓存");
        return p;
    }

}