package com.example.testlink.utils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Create by lxx
 * Date : 2020/9/27 11:13
 * Use by
 */
public class OtherUtil {

    public static String formatJson(String json) {
        StringBuilder sb = new StringBuilder();
        String message;
        // 格式化json字符串
        try {
            if (json.startsWith("{")) {
                // 最重要的方法，就一行，返回格式化的json字符串，其中的数字4是缩进字符数
                message = new JSONObject(json).toString(4);
            } else if (json.startsWith("[")) {
                message = new JSONArray(json).toString(4);
            } else {
                message = json;
            }
        } catch (JSONException e) {
            message = json;
        }

        // 添加换行并输出字符串
        String[] lines = message.split("\n");
        for (String line : lines) {
            line = line.replace("\\", "");
            sb.append(line).append("\n");
        }
        return sb.toString();
    }
}
