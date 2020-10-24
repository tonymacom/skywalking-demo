package com.tony.product.sky.rest;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

/**
 * @author ittony.ma@gmail.com
 * @desc :
 * @date 2020/10/22
 */
@Slf4j
@RestController
public class SkyRest {

    @RequestMapping("scene")
    public ResponseEntity<String> getScene() {

        try {
            CloseableHttpClient httpclient = HttpClients.createDefault();
            HttpGet httpget = new HttpGet("http://bees:8080/getBees");
            CloseableHttpResponse response = httpclient.execute(httpget);
            try {
                HttpEntity entity = response.getEntity();
                log.info(JSON.toJSONString(entity));

            } finally {
                response.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return ResponseEntity.ok("SUCCESS");
    }


}
