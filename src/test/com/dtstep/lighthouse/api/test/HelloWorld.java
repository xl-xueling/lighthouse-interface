package com.dtstep.lighthouse.api.test;

import com.dtstep.lighthouse.client.LightHouse;
import com.dtstep.lighthouse.common.util.JsonUtil;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

public class HelloWorld {

    static {
        try{
            //修改rpc服务注册中心地址,集群模式为一主一从，默认为部署集群的前两个节点IP,使用逗号分割，单机模式为当前节点IP
            //LightHouse.init("10.206.6.11:4061,10.206.6.12:4061");//集群模式初始化
            LightHouse.init("10.206.6.10:4061");//单机模式初始化
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    @Test
    public void helloWorld() throws Exception {
        System.out.println("----test---");
        long t = System.currentTimeMillis();
        for(int i = 0;i<100831;i++){
            //修改统计组参数值、Token和秘钥
            Map<String,Object> map = new HashMap<>();
            map.put("product","abc");
            map.put("thread","abc");
            map.put("scene","abc");
            Double d = ThreadLocalRandom.current().nextDouble(1000);
            map.put("tps",String.format("%.3f", d));//防止上面随机数出现科学计数法
            System.out.println("send info:" + JsonUtil.toJSONString(map));
            LightHouse.stat("_demo_feed_behavior_stat","tTHlVXiYh0KXorWqSWc73QmaWyzc2zdsNyGibo11",map,t);
            if(i / 1000 == 0){
                System.out.println("----test");
            }
        }
        System.out.println("send ok.");
        Thread.sleep(3000000);//client为异步发送，防止进程结束时内存中部分消息没有发送出去
    }
}
