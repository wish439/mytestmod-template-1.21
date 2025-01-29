package com.wishtoday.ts.Test;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

public class TranTest {

    private static final String APP_KEY = "7c70cce0adee3a67";
    private static final String SECRET_KEY = "MSd1nSYjPL1yTaiLJT0pxI3sOqmwqJY9";
    private static final String API_URL = "http://openapi.youdao.com/api";

    public static void main(String[] args) {
        String query = "Hello, world!";
        String from = "en";
        String to = "zh-CHS";

        try {
            String result = translate(query, from, to);
            System.out.println(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String translate(String query, String from, String to) throws Exception {
        // 生成随机数
        String salt = String.valueOf(new Random().nextInt(100000));
        // 获取当前时间戳
        String curtime = String.valueOf(System.currentTimeMillis() / 1000);
        // 生成签名
        String sign = generateSign(APP_KEY, query, salt, curtime, SECRET_KEY);

        // 构建请求参数
        String params = "q=" + java.net.URLEncoder.encode(query, StandardCharsets.UTF_8) +
                "&from=" + from +
                "&to=" + to +
                "&appKey=" + APP_KEY +
                "&salt=" + salt +
                "&sign=" + sign +
                "&signType=v3" +
                "&curtime=" + curtime;

        // 发送HTTP请求
        return sendPostRequest(API_URL, params);
    }

    private static String generateSign(String appKey, String query, String salt, String curtime, String secretKey) throws NoSuchAlgorithmException {
        String signStr = appKey + truncate(query) + salt + curtime + secretKey;
        return sha256(signStr);
    }

    private static String truncate(String q) {
        if (q == null) {
            return null;
        }
        int len = q.length();
        return len <= 20 ? q : (q.substring(0, 10) + len + q.substring(len - 10, len));
    }

    private static String sha256(String str) throws NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] hash = digest.digest(str.getBytes(StandardCharsets.UTF_8));
        StringBuilder hexString = new StringBuilder(2 * hash.length);
        for (byte b : hash) {
            String hex = Integer.toHexString(0xff & b);
            if (hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }
        return hexString.toString();
    }

    private static String sendPostRequest(String urlStr, String params) throws Exception {
        URL url = new URL(urlStr);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("POST");
        connection.setDoOutput(true);
        connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

        try (OutputStream os = connection.getOutputStream()) {
            os.write(params.getBytes(StandardCharsets.UTF_8));
            os.flush();
        }

        StringBuilder response = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream(), StandardCharsets.UTF_8))) {
            String line;
            while ((line = br.readLine()) != null) {
                response.append(line).append("\n");
            }
        }
        return response.toString();
    }
}