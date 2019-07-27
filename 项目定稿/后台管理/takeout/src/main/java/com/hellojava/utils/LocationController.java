package com.hellojava.utils;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


@Service
public class LocationController {
    public static List<String> Convert(String path) {
        String url = "https://restapi.amap.com/v3/geocode/geo?address=" + path + "&output=JSON&key=811b5c6f567552f778712a64eb2fc0b3";
        CloseableHttpClient httpClient = HttpClientBuilder.create ().build ();
        HttpGet httpGet = new HttpGet (url);
        CloseableHttpResponse response = null;
        List<String> list = new ArrayList<> ();
        String a = null;
        String b = null;
        try {
            response = httpClient.execute (httpGet);
            HttpEntity httpEntity = response.getEntity ();
            if (httpEntity != null) {
                String sc = EntityUtils.toString (httpEntity);
                JSONObject jsonObject = JSONObject.parseObject (sc);
                JSONArray jsonArray = JSONArray.parseArray (jsonObject.get ("geocodes").toString ());
                if (jsonArray.size()>0) {
                    JSONObject json = JSONObject.parseObject (jsonArray.get (0).toString ());
                    a = json.get ("formatted_address").toString ();
                    //获取地址
                    list.add (a);
                    //获取经纬度
                    b = json.get ("location").toString ();
                    list.add (b);
                }
            }
        } catch (IOException e) {
            e.printStackTrace ();
        } finally {
            try {
                //释放资源
                if (httpClient != null) {
                    httpClient.close ();
                }
                if (response != null) {
                    response.close ();
                }
            } catch (IOException e) {
                e.printStackTrace ();
            }
        }
        return list;

    }
}
