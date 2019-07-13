package com.cunjunwang.music.player.util;

import java.util.Map;

/**
 * 请求Url工具类
 * Created by CunjunWang on 2019-06-29.
 */
public class RequestUrlUtil {

    /**
     * 将params中的参数拼接进url
     *
     * @param originUrl host
     * @param urlParams 参数
     * @return 拼接后的url
     */
    public static String concatUrl(String originUrl, Map<String, String> urlParams) {
        StringBuilder builder = new StringBuilder(originUrl);
        builder.append("?");
        urlParams.keySet().forEach(key -> {
            String value = urlParams.get(key);
            builder.append(key).append("=").append(value).append("&");
        });
        return builder.toString();
    }

}
