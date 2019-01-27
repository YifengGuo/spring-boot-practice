package com.yifeng.spring_boot_practice.chapter7_redis.spring_boot_redis.controller;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.RedisZSetCommands;
import org.springframework.data.redis.core.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.ScanResult;
import redis.clients.jedis.Tuple;

import java.util.*;

import static redis.clients.jedis.ScanParams.SCAN_POINTER_START;

/**
 * @author yifengguo
 */
@RestController
@RequestMapping("/redis")
public class RedisController {

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @RequestMapping("/stringAndHash")
    public List<Map.Entry<String, String>> testStringAndHash() {
        redisTemplate.opsForValue().set("test_key1", "test_value1");

        // JDK serializer will not store integer. So value cannot be calculated
        redisTemplate.opsForValue().set("int_key", "1");
        stringRedisTemplate.opsForValue().set("int", "1");

        // do the computation
        stringRedisTemplate.opsForValue().increment("int", 1);

        // obtain jedis connection
        Jedis jedis = (Jedis) stringRedisTemplate.getConnectionFactory().getConnection().getNativeConnection();

        jedis.decr("int");

        Map<String, String> hash = new HashMap<>();
        hash.put("test_hkey_1", "test_hvalue_1");
        hash.put("test_hkey_2", "test_hvalue_2");

        // insert hash key-value pairs to redis
        stringRedisTemplate.opsForHash().putAll("hash", hash);

        stringRedisTemplate.opsForHash().put("hash", "test_hkey_3", "test_hvalue_3");

        // get the bound of a map in the redis which make sequential operations on this map possible
        BoundHashOperations hashOps = stringRedisTemplate.boundHashOps("hash");
        hashOps.delete("test_hkey_1", "test_hkey_2");
        hashOps.put("test_hkey_4", "test_hvalue_4");

        // iterate on all k-v pairs under "hash"
        ScanResult<Map.Entry<String, String>> scanResult = jedis.hscan("hash", SCAN_POINTER_START);
        return scanResult.getResult();
    }

    @RequestMapping("/list")
    public JSONObject testList() {
        stringRedisTemplate.opsForList().leftPushAll("list1", Arrays.asList("v1", "v2", "v3", "v4", "v5", "v6"));
        stringRedisTemplate.opsForList().rightPushAll("list2", Arrays.asList("v1", "v2", "v3", "v4", "v5", "v6"));

        BoundListOperations rightListOps = stringRedisTemplate.boundListOps("list2");

        Object o1 = rightListOps.rightPop();
        Object o2 = rightListOps.index(1);
        rightListOps.leftPush("v0");
        long size = rightListOps.size();

        List elements = rightListOps.range(0, size - 1);

        JSONObject res = new JSONObject();
        res.put("right_pop", o1);
        res.put("right_index1", o2);
        res.put("right_list", elements);
        return res;
    }

    @RequestMapping("/set")
    public JSONObject testSet() {
        stringRedisTemplate.opsForSet().add("unsorted_set1", "v1", "v2", "v3", "v4", "v1", "v2");
        stringRedisTemplate.opsForSet().add("unsorted_set2",  "v2", "v3", "v4", "v6", "v5");

        BoundSetOperations setOperations = stringRedisTemplate.boundSetOps("unsorted_set1");

        // add elements
        setOperations.add("v6", "v7");

        // remove element
        setOperations.remove("v1", "v6");

        // return all elements
        Set total = setOperations.members();

        // get size
        long size = setOperations.size();

        // get intersection
        Set intersect = setOperations.intersect("unsorted_set2");

        // get intersection and store it in a new set
        setOperations.intersectAndStore("unsorted_set2", "intersect");
        Set intersect2 = stringRedisTemplate.opsForSet().members("intersect");

        // get diff
        Set diff = setOperations.diff("unsorted_set2");

        // get diff and store it in a new set
        setOperations.diffAndStore("unsorted_set2", "diff");
        Set diff2 = stringRedisTemplate.opsForSet().members("diff");

        // get union
        Set union = setOperations.union("unsorted_set2");

        // get union and store it in a new set
        setOperations.unionAndStore("unsorted_set2", "union");
        Set union2 = stringRedisTemplate.opsForSet().members("union");

        JSONObject res = new JSONObject();

        res.put("elements", total);
        res.put("size", size);
        res.put("inter1", intersect);
        res.put("inter2", intersect2);
        res.put("diff1", diff);
        res.put("diff2", diff2);
        res.put("union1", union);
        res.put("union2", union2);
        return res;
    }

    @RequestMapping("/zset")
    public JSONObject testZset() {
        Set<ZSetOperations.TypedTuple<String>> typedTupleSet = new HashSet<>();

        for (int i = 0; i < 10; i++) {
            double score = i * 0.1;
            ZSetOperations.TypedTuple<String> typedTuple = new DefaultTypedTuple<>("value" + i, score);
            typedTupleSet.add(typedTuple);
        }

        // insert elements to the redis
        stringRedisTemplate.opsForZSet().add("zset1", typedTupleSet);

        BoundZSetOperations boundZSetOperations = stringRedisTemplate.boundZSetOps("zset1");

        boundZSetOperations.add("value10", 0.26);

        Set<String> rangeSet = boundZSetOperations.range(1, 6);  // get elements given range of index
        Set<String> scoreSet = boundZSetOperations.rangeByScore(0.2, 0.6);  // get elements given range of score

        RedisZSetCommands.Range range = new RedisZSetCommands.Range();
        range.gt("value3");
        range.lte("value8");
        // Get all elements with lexicographical ordering with a value within the range
        Set<String> setLex = boundZSetOperations.rangeByLex(range);

        boundZSetOperations.remove("value2", "value9");

        double score = boundZSetOperations.score("value8");  // get element's score

        Set<ZSetOperations.TypedTuple<String>> rangeSet2 = boundZSetOperations.rangeWithScores(1, 6);  // return value given range of scores
        Set<ZSetOperations.TypedTuple<String>> scoreSet2 = boundZSetOperations.rangeByScoreWithScores(1, 6);  // return value and score gienn range of scores

        Set<String> reverseSet = boundZSetOperations.reverseRange(2, 8);

        Set<String> totoal = boundZSetOperations.range(0, boundZSetOperations.size());

        JSONObject res = new JSONObject();
        res.put("total", totoal);
        res.put("rangeSet", rangeSet);
        res.put("scoreSet", scoreSet);
        res.put("rangeSet2", rangeSet2);
        res.put("scoreSet2", rangeSet2);
        res.put("size", boundZSetOperations.size());
        return res;
    }
}
