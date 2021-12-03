package com.bigheadgo.utils;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.StandardCharsets;

/**
 * 微信小程序工具类
 * <p>
 * author: xiaoYang
 * time: 2021/12/3 11:21
 */
@Data
@Slf4j
@Component
@ConfigurationProperties("wechat-util")
public class WeChatUtil {
    // 小程序id和秘钥
    private String appId;
    private String appSecret;

    /**
     * 访问微信服务器 换取用户的 openid
     * AppID(小程序ID) wxc947fc0c1ec2e285
     * AppSecret(小程序密钥) dc53875e6e31e7594b4ec7910d84e4cd
     *
     * @param code 临时code
     * @return openid
     */
    public String getOpenid(String code) {
        // 拼接微信服务 请求的地址和参数 用于获取用户的 openid
        String params = "https://api.weixin.qq.com/sns/jscode2session?appid=" +
                appId +
                "&secret=" +
                appSecret +
                "&js_code=" + code +
                "&grant_type=authorization_code";//网址;

        // 通过url类读取返回值
        URL url;
        String openid = null;
        try {
            url = new URL(params);
            InputStream in = url.openStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(in, StandardCharsets.UTF_8));

            byte[] b = new byte[1024];
            String line;
            //截取openid
            while ((line = br.readLine()) != null) {
                int star = line.indexOf("\"openid\":");
                int end = line.indexOf("}");
                if (star > 0 && end > 0) {
                    openid = line.substring(star + 10, end - 1);
                }
            }
        } catch (Exception e) {
            log.error("微信登录: " + e);
        }

        return openid;
    }
}
