package com.cunjunwang.music.player.util;


import org.springframework.util.StringUtils;

/**
 * Created by CunjunWang on 2019-07-13.
 */
public class ResponseUtil {

    /**
     * 把jsonp返回字符串解析为json格式字符串
     *
     * @param jsonpString jsonp字符串
     * @return json字符串
     */
    public static String parseJsonpResponseToJsonResponse(String jsonpString) {
        if (StringUtils.isEmpty(jsonpString)) {
            return null;
        }
        int firstIndexOfLeftParenthesis = jsonpString.indexOf("(");
        int lastIndexOfRightParenthesis = jsonpString.lastIndexOf(")");
        return jsonpString.substring(firstIndexOfLeftParenthesis + 1, lastIndexOfRightParenthesis);
    }

}
