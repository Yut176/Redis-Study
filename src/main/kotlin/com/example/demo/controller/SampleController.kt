package com.example.demo.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.boot.autoconfigure.data.redis.RedisProperties
import org.springframework.data.redis.core.RedisHash
import org.springframework.data.redis.core.StringRedisTemplate
import org.springframework.stereotype.Controller
import org.springframework.transaction.annotation.Transactional
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.ResponseBody
import redis.clients.jedis.Jedis


@Controller
@EnableAutoConfiguration
class SampleController {
    @Autowired
    private val redisTemplate : StringRedisTemplate? = null

    @RequestMapping("/test/{id}")
    @ResponseBody
    fun test(@PathVariable("id") id : String) : String {
        redisTemplate!!.opsForValue().set(id, id)
        val str = redisTemplate.opsForValue().get(id)
        return str!!
    }


}