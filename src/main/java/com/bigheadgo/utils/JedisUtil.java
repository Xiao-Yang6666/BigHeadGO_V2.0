package com.bigheadgo.utils;


import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;

/**
 * Redis 工具类
 * <p>
 * author: xiaoYang
 * time: 2021/11/19 18:42
 */
@Data
@Slf4j
@Component()
@ConfigurationProperties(prefix = "jedis-util")
public class JedisUtil {
    // 连接地址
    private String url;
    // 接口
    private int port;

    /**
     * redis 连接
     *
     * @return redis连接
     */
    public Jedis connect() {
        return new Jedis(url, port);
    }

    /**
     * 通过key获取 value
     *
     * @param key 键
     * @return 值
     */
    public String getKey(String key) {
        String value = "";
        // 带资源的try写法 会在退出时自动关闭资源
        try (Jedis jedis = connect()) {
            value = jedis.get(key);
        } catch (Exception e) {
            log.error("redis异常");
            return "";
        }
        return value;
    }

    /**
     * 添加不带过期时间的数据
     *
     * @param key   键
     * @param value 值
     * @return 是否成功
     */
    public boolean putKey(String key, String value) {
        try (Jedis jedis = connect()) {
            jedis.set(key, value);
            return true;
        } catch (Exception e) {
            log.error("redis异常");
            return false;
        }
    }

    /**
     * 添加带过期时间的数据
     *
     * @param key    键
     * @param value  值
     * @param minute 过期时间 分钟
     * @return 是否成功
     */
    public boolean putKey(String key, String value, String minute) {
        try (Jedis jedis = connect()) {
            jedis.setex(key, Integer.parseInt(minute) * 60, value);
            return true;
        } catch (Exception e) {
            log.error("redis异常");
            return false;
        }
    }

    /**
     * 向队列的左边添加数据
     *
     * @param key   key
     * @param value 值
     * @return 集合中的值
     */
    public long lpush(String key, String value) {
        try (Jedis jedis = connect()) {
            return jedis.lpush(key, value);
        } catch (Exception e) {
            log.error("redis异常");
            return -1;
        }
    }

    /**
     * 向队列的左边添加数据
     * 存byte[]
     *
     * @param key   key
     * @param value 值
     * @return 集合中的值
     */
    public long lpush(String key, byte[] value) {
        try (Jedis jedis = connect()) {
            return jedis.lpush(key.getBytes(), value);
        } catch (Exception e) {
            log.error("redis异常");
            return -1;
        }
    }

    /**
     * 向队列的右边添加数据
     *
     * @param key   key
     * @param value 值
     * @return 集合中的值
     */
    public long rpush(String key, String value) {
        try (Jedis jedis = connect()) {
            return jedis.rpush(key, value);
        } catch (Exception e) {
            log.error("redis异常");
            return -1;
        }
    }

    /**
     * 向队列的右边添加数据
     * 存byte[]
     *
     * @param key   key
     * @param value 值
     * @return 集合中的值
     */
    public long rpush(String key, byte[] value) {
        try (Jedis jedis = connect()) {
            return jedis.rpush(key.getBytes(), value);
        } catch (Exception e) {
            log.error("redis异常");
            return -1;
        }
    }


    /**
     * 从队列中右边获取一个值
     *
     * @param key key
     * @return value
     */
    public String rpop(String key) {
        try (Jedis jedis = connect()) {
            return jedis.rpop(key);
        } catch (Exception e) {
            log.error("redis异常");
            return "";
        }
    }

    /**
     * 从队列中左边获取一个值
     *
     * @param key key
     * @return value
     */
    public String lpop(String key) {
        try (Jedis jedis = connect()) {
            return jedis.lpop(key);
        } catch (Exception e) {
            log.error("redis异常");
            return "";
        }
    }

    /**
     * 获取key的过期剩余时间,以分钟的形式返回
     *
     * @param key 键
     * @return 剩余分钟数
     */
    public long ttlKeyMinute(String key) {
        try (Jedis jedis = connect()) {
            Long ttl = jedis.ttl(key);
            return ttl / 60;
        } catch (Exception e) {
            log.error("redis异常");
        }
        return -1;
    }

    /**
     * 获取key的过期剩余时间,以秒的形式返回
     *
     * @param key 键
     * @return 剩余秒数
     */
    public long ttlKeySecond(String key) {
        try (Jedis jedis = connect()) {
            return jedis.ttl(key);
        } catch (Exception e) {
            log.error("redis异常");
        }
        return -1;
    }
}
