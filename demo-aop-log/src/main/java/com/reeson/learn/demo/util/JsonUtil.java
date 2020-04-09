package com.reeson.learn.demo.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;

public class JsonUtil {
    public static String toJsonString(Object object){
        return JSON.toJSONString(object, SerializerFeature.DisableCircularReferenceDetect);
    }
}
