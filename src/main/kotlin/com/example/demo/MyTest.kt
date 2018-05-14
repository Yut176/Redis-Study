package com.example.demo

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.redis.core.ListOperations
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.data.redis.core.StringRedisTemplate
import java.net.URL
import javax.annotation.Resource

@Autowired
private val redisTemplate : StringRedisTemplate? = null

class MyTest {
    fun basic(){
        redisTemplate!!.opsForValue().set("myKey", "val1")
        print(redisTemplate.opsForValue().get("myKey"))
    }
}