package com.bigheadgo.utils;

import com.bigheadgo.entity.RespJson;

import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * author: xiaoYang
 * time: 2021/12/1 22:12
 */
public class MyUtils {
    /**
     * 封装普通Json
     * 已经使用了JSON.toJSONString(),无须再做额外操作 直接丢给前台就好
     *
     * @param msg 备注信息
     * @param obj 要返回到页面的data
     * @return 响应前台的Json, 以Json格式规约RespJson封装
     */
    public static RespJson isCode(String msg, Object obj) {
        // 如果数据库返回数据为空 返回错误状态给前台
        if (obj == null || "".equals(obj) || "[]".equals(obj))
            return new RespJson(505, msg, "");

        return new RespJson(200, msg, obj);
    }

    /**
     * 封装分页Json
     * 已经使用了JSON.toJSONString(),无须再做额外操作 直接丢给前台就好
     *
     * @param msg   备注信息
     * @param count 总行数
     * @param obj   要返回到页面的data
     * @return 响应前台的Json, 以Json格式规约RespJson封装
     */
    public static RespJson isCode(String msg, Integer count, Object obj) {
        // 如果数据库返回数据为空 返回错误状态给前台
        if (obj == null || "".equals(obj) || "[]".equals(obj))
            return new RespJson(505, msg, null, null);

        return new RespJson(200, msg, count, obj);
    }

    /**
     * 获取当前时间
     * 已经使用了SimpleDateFormat格式化,无须再做额外操作 直接使用就好
     *
     * @return 当前时间
     */
    public static String getTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        return sdf.format(new Date());
    }

    /**
     * 处理科学计数法结果显示
     *
     * @param d 需要处理的Double
     * @return 保留两位小数
     */
    public static String dealingScientificNotation(Double d) {
        DecimalFormat df = new DecimalFormat();
        // 保留两位小数
        df.setMaximumFractionDigits(2);
        return df.format(d);
    }

    /**
     * 获取真实ip地址
     * 不处理的话通过Nginx后就会一直获取 Nginx的ip,而获取不到用户真实ip
     *
     * @param request HttpServletRequest
     * @return 真实ip地址
     */
    public static String getIpAddr(HttpServletRequest request) {
        String ipAddress = request.getHeader("x-forwarded-for");
        if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("Proxy-Client-IP");
        }
        if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getRemoteAddr();
            String localIp = "127.0.0.1";
            String localIpv6 = "0:0:0:0:0:0:0:1";
            if (ipAddress != null && (ipAddress.equals(localIp) || ipAddress.equals(localIpv6))) {
                // 根据网卡取本机配置的IP
                InetAddress inet = null;
                try {
                    inet = InetAddress.getLocalHost();
                    ipAddress = inet.getHostAddress();
                } catch (UnknownHostException e) {
                    e.printStackTrace();
                }
            }
        }
        // 对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割
        String ipSeparate = ",";
        int ipLength = 15;
        if (ipAddress != null && ipAddress.length() > ipLength) {
            if (ipAddress.indexOf(ipSeparate) > 0) {
                ipAddress = ipAddress.substring(0, ipAddress.indexOf(ipSeparate));
            }
        }
        return ipAddress;
    }
}
