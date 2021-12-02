package com.bigheadgo.utils;

import lombok.extern.slf4j.Slf4j;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * java对象序列化工具类
 * <p>
 * author: xiaoYang
 * time: 2021/12/2 0:00
 */
@Slf4j
public class SerializeUtil {
    /**
     * java对象序列化
     *
     * @param object java对象
     * @return ByteArray
     */
    public static byte[] serialize(Object object) {
        ObjectOutputStream oos = null;
        ByteArrayOutputStream baos = null;
        try {
            //序列化
            baos = new ByteArrayOutputStream();
            oos = new ObjectOutputStream(baos);
            oos.writeObject(object);
            return baos.toByteArray();
        } catch (Exception e) {
            log.error("序列化失败");
        }
        return null;
    }

    /**
     * 反序列化
     *
     * @param bytes ByteArray
     * @return Object
     */
    public static Object unserialize(byte[] bytes) {
        ByteArrayInputStream bais = null;
        try {
            //反序列化
            bais = new ByteArrayInputStream(bytes);
            ObjectInputStream ois = new ObjectInputStream(bais);
            return ois.readObject();
        } catch (Exception e) {
            log.error("反序列化失败");
        }
        return null;
    }
}
