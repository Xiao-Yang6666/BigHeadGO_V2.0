package com.bigheadgo.utils;

import com.bigheadgo.exception.SendMessException;
import com.github.qcloudsms.SmsSingleSender;
import com.github.qcloudsms.SmsSingleSenderResult;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Random;

/**
 * 验证码短信工具类
 * <p>
 * author: xiaoYang
 * time: 2021/11/19 0:17
 */
@Data
@Slf4j
@Component
@ConfigurationProperties("messages-util")
public class MessagesUtil {
    // 短信应用id
    private int sdkAppId;
    private String sdkAppKey;
    // 短信签名
    private String signName;
    // 登录模板id
    private int loginTemplateId;
    // 忘记密码模板id
    private int forgetPwdTemplateId;

    /**
     * 调用腾讯云 api 发送短信
     *
     * @param type   发送类型 0为登录， 1为找回密码
     * @param phone  手机号
     * @param params 模板的参数 ['验证码','过期时间']
     * @return 是否发送成功
     */
    public boolean sendMess(int type, String phone, String... params) {
        try {
            // 通过应用秘钥创建 发送一条信息的对象
            SmsSingleSender singleSender = new SmsSingleSender(sdkAppId, sdkAppKey);
            // 登录
            if (type == 0) {
                SmsSingleSenderResult result = singleSender.sendWithParam("86", phone, loginTemplateId, params, signName, "", "");
                if (result.result != 0)
                    throw new SendMessException("验证码发送失败");
                return true;
            }
            // 忘记密码
            if (type == 1) {
                SmsSingleSenderResult result = singleSender.sendWithParam("86", phone, forgetPwdTemplateId, params, signName, "", "");
                if (result.result != 0)
                    throw new SendMessException("验证码发送失败");
                return true;
            }
            // 输入类型有误直接提示异常
            throw new SendMessException("发送类型不存在！");
        } catch (Exception e) {
            log.error("" + e);
            return false;
        }
    }

    /**
     * 生成需要的验证码
     *
     * @param length 生成验证码的长度
     * @return 验证码
     */
    public String getVerificationCode(int length) {
        String str = "0123456789";
        Random random = new Random();
        // 动态string接收 随机值
        StringBuilder buffer = new StringBuilder();
        // 根据需要的长度获取
        for (int i = 0; i < length; i++) {
            // 生成随机数
            int number = random.nextInt(str.length());
            // 添加进stringBuffer中
            buffer.append(str.charAt(number));
        }
        return buffer.toString();
    }

}
