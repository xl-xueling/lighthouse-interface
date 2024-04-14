package com.dtstep.lighthouse.api.test;

import com.dtstep.lighthouse.api.util.OkHttpUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

public class TestHttpApi {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    public void testHttpApi() throws Exception {
        long t = System.currentTimeMillis();
        for(int i=0;i<100;i++){
            String token = "TlZ:test_stat";
            String secretKey = "mgasBFCD8iFcIgKNkpmBwfMm51M0CE2DZsKWwB5z";
            Map<String,Object> paramMap = new HashMap<>();
            paramMap.put("province", ThreadLocalRandom.current().nextInt(10));
            paramMap.put("score",ThreadLocalRandom.current().nextDouble(1000));
            paramMap.put("uuid","test_"+ThreadLocalRandom.current().nextInt(300));
            ObjectNode objectNode = objectMapper.createObjectNode();
            objectNode.put("token",token);
            objectNode.put("secretKey",secretKey);
            objectNode.put("timestamp",t);
            objectNode.put("repeat",1);
            objectNode.putPOJO("params",paramMap);
            System.out.println(objectNode.toPrettyString());
            String response = OkHttpUtil.post("http://10.206.6.5:9085/api/v1/stat",objectNode.toString());
            System.out.println("response:" + response);
        }
        Thread.sleep(20 * 1000);
    }
}
