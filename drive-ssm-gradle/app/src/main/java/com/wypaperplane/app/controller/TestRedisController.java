package com.wypaperplane.app.controller;

import com.wypaperplane.syscore.ResponseCode;
import com.wypaperplane.syscore.ResponseResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping(path="/app")
public class TestRedisController {

    private final Logger logger = LoggerFactory.getLogger(TestRedisController.class);

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @RequestMapping(path="/redisTest", method = RequestMethod.GET)
    public ResponseResult redisTest() {
        redisTemplate.opsForValue().set("smoke", "Hello Redis", 60, TimeUnit.SECONDS);
        return ResponseResult.success(ResponseCode.SUCCESS, "redis test");
    }

    @RequestMapping(path="/redisTestb", method = RequestMethod.GET)
    public ResponseResult redisTestb() {
        String smoke = (String) redisTemplate.opsForValue().get("smoke");

        // 获取过期时间
        RedisOperations<String, Object> redisOperations = redisTemplate.opsForValue().getOperations();
        Long nmnong = redisOperations.getExpire("smoke", TimeUnit.SECONDS);

        logger.info("----- {}", nmnong);

        return ResponseResult.success(ResponseCode.SUCCESS, smoke);
    }

    public void redisStringTest() {
        // Smoke smoke = new Smoke("hongtashan", (short) 12, "wuyan");

        // add
        redisTemplate.opsForValue().set("smoke", "Hello Redis");
        // redisTemplate.opsForValue().set("smoke", smoke);

        // get
        // Smoke getsmoke = (Smoke) redisTemplate.opsForValue().get("smoke");
    }

    public void redisListTest() {
        List<String> lists = new ArrayList<>();
        lists.add("liuyi");
        lists.add("chener");

        // add
        redisTemplate.opsForList().rightPushAll("listkey", lists);
        redisTemplate.opsForList().rightPush("listkey", "zhangsan");

        // get
        redisTemplate.opsForList().index("listkey", 0);
        redisTemplate.opsForList().range("listkey", 0, -1);

        // length
        redisTemplate.opsForList().size("listkey");

        // delete
        redisTemplate.opsForList().remove("listkey", 1, "item");
    }

    public void redisSetTest() {
        Set<String> sets = new HashSet<>();
        sets.add("liuyi");
        sets.add("chener");

        // add
        redisTemplate.opsForSet().add("setkey", sets);

        // get
        redisTemplate.opsForSet().members("setkey");

        // delete
        redisTemplate.opsForSet().remove("setkey", "zhangsan");

        // 是否存在
        redisTemplate.opsForSet().isMember("setkey", "lisi");
        redisTemplate.opsForSet().size("setkey");
    }

    public void redisHashTest() {
        Map<String, String> maps = new HashMap<>();
        maps.put("name", "wangwu");
        maps.put("age", "30");

        // add
        redisTemplate.opsForHash().put("mapkey", "gender", "man");
        redisTemplate.opsForHash().putAll("mapkey", maps);

        // get
        redisTemplate.opsForHash().entries("mapkey");
        redisTemplate.opsForHash().get("mapkey", "name");

        // delete
        redisTemplate.opsForHash().delete("mapkey", "name");

        redisTemplate.opsForHash().hasKey("mapkey", "name");
    }
}
