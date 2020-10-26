package com.tony.product.sky.rest;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

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

        String result = callBees("name");
        log.info("sky scene");

        return ResponseEntity.ok(result);
    }

    @GetMapping("/request/slowly/response/{time}")
    public ResponseEntity<String> sleep(@PathVariable("time") Long time) {
        String result = callBees("/request/sleep/"+time);
        log.info("sky /request/low/response/");
        return ResponseEntity.ok(result);
    }

    @GetMapping("/request/slowly/run/{time}")
    public ResponseEntity<String> running(@PathVariable("time") Long time) {
        String result = callBees("/request/running/"+time);
        log.info("sky /request/low/run");
        return ResponseEntity.ok(result);
    }

    private String callBees(String url) {
        String result = null;
        try {
            CloseableHttpClient httpclient = HttpClients.createDefault();
            CloseableHttpResponse response = httpclient.execute(new HttpGet("http://bees:8080/" + url));
            try {
                HttpEntity entity = response.getEntity();
                log.info(JSON.toJSONString(response));
                log.info(JSON.toJSONString(entity));
                result = EntityUtils.toString(entity, StandardCharsets.UTF_8);

            } finally {
                response.close();
                httpclient.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }


}
