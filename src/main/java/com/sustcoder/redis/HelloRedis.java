package com.sustcoder.blog.redis;

import redis.clients.jedis.Jedis;

/**
 * <p>Description: 连接redis服务
 * <p>Version:v1.0
 * <p>Copyright ©2015 madadai.com All Rights Reserved. </p>
 * <p>Company:caxins</p>
 * <p>Author:liyanzhao
 * <p>Date: 16:36 2018/1/15
 */
public class HelloRedis {
    public static void main(String[] args) {

        final  String IP="10.10.8.10";
        final  int PORT=6379;

        Jedis jedis=new Jedis(IP,PORT);
        System.out.println("连接成功");

        // 配置密码登陆： config set requirepass admin1234
        // 设置登陆密码
        jedis.auth("admin1234");


        // 查看服务是否正常
        System.out.println("查看服务是否正常： ping-->"+jedis.ping());

        jedis.close();
    }
}
