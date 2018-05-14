package com.example.demo

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.data.redis.core.StringRedisTemplate
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody


@Controller
@EnableAutoConfiguration
class SampleController {
    @Autowired
    private val redisTemplate : StringRedisTemplate? = null

    @RequestMapping("/")
    @ResponseBody
    fun hello() : String {
        redisTemplate!!.opsForValue().set("test", "foo")
        val str = redisTemplate.opsForValue().get("test")

        return str!!
    }

    @RequestMapping("/test/{id}")
    @ResponseBody
    fun test(@PathVariable("id") id : String) : String {
        redisTemplate!!.opsForValue().set(id, id)
        val str = redisTemplate.opsForValue().get(id)
        return str!!
    }


}