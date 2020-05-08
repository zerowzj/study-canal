package study.canal.kafka.common;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;

import java.util.HashMap;
import java.util.Map;

public class JsonUtils {

    public static String toJson(Object obj) {
        String text = "";
        if (obj != null) {
            text = JSON.toJSONString(obj, true);
        }
        return text;
    }

    public static <T> T fromJson(String text, Class<T> clazz) {
        T t = JSON.parseObject(text, clazz);
        return t;
    }

    public static void main(String[] args) {
        Map<String, String> data = new HashMap<>();
        data.put("name", "wzhj");
        data.put("age", "33");
        System.out.println(JsonUtils.toJson(data));
    }
}
