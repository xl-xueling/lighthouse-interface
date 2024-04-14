package com.dtstep.lighthouse.api.test;

import com.dtstep.lighthouse.client.LightHouse;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

public class HelloWorld {

    static {
        try{
            //修改rpc服务地址,一主一从，默认为部署集群的前两个节点
            LightHouse.init("10.206.6.5:4061,10.206.6.13:4061");
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    @Test
    public void helloWorld() throws Exception {
        long t = System.currentTimeMillis();
        for(int i = 0;i<100;i++){
            //修改统计组参数值、Token和秘钥
            Map<String,Object> map = new HashMap<>();
            map.put("province", ThreadLocalRandom.current().nextInt(10));
            map.put("score",ThreadLocalRandom.current().nextDouble(1000));
            map.put("uuid","test_"+ThreadLocalRandom.current().nextInt(300));
            LightHouse.stat("TlZ:test_stat","mgasBFCD8iFcIgKNkpmBwfMm51M0CE2DZsKWwB5z",map,t);
        }
        System.out.println("send ok.");
        Thread.sleep(100000);
    }
}
