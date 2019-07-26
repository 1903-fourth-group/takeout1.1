package com.hellojava.controller;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;


@Controller
@Api(tags = "定位操作")
@RequestMapping("/location")
@ResponseBody
public class LocationController {
    @RequestMapping(value = "/convert", method = RequestMethod.GET)
    @ApiOperation(value = "定位解析")
    public String Convert(String path) {
        String url = "https://restapi.amap.com/v3/geocode/geo?address=" + path + "&output=JSON&key=811b5c6f567552f778712a64eb2fc0b3";
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        HttpGet httpGet = new HttpGet(url);
        CloseableHttpResponse response = null;
        String a = null;
        String b = null;
        try {
            response = httpClient.execute(httpGet);
            HttpEntity httpEntity = response.getEntity();
            if (httpEntity != null) {
                String sc = EntityUtils.toString(httpEntity);
                JSONObject jsonObject = JSONObject.parseObject(sc);

                JSONArray jsonArray = JSONArray.parseArray(jsonObject.get("geocodes").toString());
                JSONObject json = JSONObject.parseObject(jsonArray.get(0).toString());
                //获取地址
                a = "地址为："+json.get("formatted_address").toString();
                System.out.println(json.get("formatted_address"));
                //获取经纬度
                b = "经纬度为："+json.get("location").toString();
                System.out.println(json.get("location"));
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                //释放资源
                if (httpClient != null) {
                    httpClient.close();
                }
                if (response != null) {
                    response.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return a + b;

    }
}
