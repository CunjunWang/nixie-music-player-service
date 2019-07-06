package com.cunjunwang.music.player.service.impl.api;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.cunjunwang.music.player.constant.Constant;
import com.cunjunwang.music.player.constant.ErrConstant;
import com.cunjunwang.music.player.exception.MusicPlayerException;
import com.cunjunwang.music.player.model.dto.api.RecommendDiscApiDTO;
import com.cunjunwang.music.player.service.inf.api.IRecommendApiService;
import com.cunjunwang.music.player.util.RequestUrlUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by CunjunWang on 2019-06-29.
 */
@Service
public class RecommendApiService implements IRecommendApiService {

    private static final Logger logger = LoggerFactory.getLogger(Constant.LOGGER);

    private static final String RESPONSE_CODE_KEY = "code";

    private static final Integer RESPONSE_CODE_OK = 0;

    private static final String RESPONSE_DATA_KEY = "data";

    private static final String RESPONSE_DISC_ARRAY_KEY = "list";

    @Value("${com.cunjunwang.music.player.getDiscListUrl}")
    private String discListUrl;

    @Value("${com.cunjunwang.music.player.discSongListUrl}")
    private String discSongListUrl;

    @Autowired
    private RestTemplate restTemplate;

    /**
     * 获取推荐歌单列表
     *
     * @param params 接口url参数
     * @return 推荐歌单列表
     */
    @Override
    public List<RecommendDiscApiDTO> getRecommendDiscList(Map<String, String> params) {
        List<RecommendDiscApiDTO> result = new ArrayList<>();
        try {
            logger.info("调用QQ音乐接口, 获取首页推荐歌单列表");
            HttpHeaders headers = new HttpHeaders();
            headers.add(HttpHeaders.REFERER, Constant.COMMON_API_REFERER);
            headers.add(HttpHeaders.HOST, Constant.COMMON_API_HOST);
            HttpEntity entity = new HttpEntity(headers);
            String targetUrl = RequestUrlUtil.concatUrl(discListUrl, params);
            String responseString = restTemplate.exchange(targetUrl, HttpMethod.GET, entity, String.class).getBody();
            JSONObject responseObject = JSONObject.parseObject(responseString);
            if (responseObject == null) {
                throw new MusicPlayerException(ErrConstant.UNKNOWN_ERR, "获取首页推荐歌单列表失败");
            }
            logger.debug("QQ音乐响应[{}]", responseObject);
            if (RESPONSE_CODE_OK.equals(responseObject.getInteger(RESPONSE_CODE_KEY))) {
                logger.info("调用QQ音乐接口成功");
                JSONObject dataObject = responseObject.getJSONObject(RESPONSE_DATA_KEY);
                if (dataObject == null) {
                    throw new MusicPlayerException(ErrConstant.UNKNOWN_ERR, "获取首页推荐歌单列表失败");
                }
                JSONArray discArray = dataObject.getJSONArray(RESPONSE_DISC_ARRAY_KEY);
                if (discArray != null) {
                    String discArrayString = discArray.toJSONString();
                    result = JSONArray.parseArray(discArrayString, RecommendDiscApiDTO.class);
                }
            }
            return result;
        } catch (Exception e) {
            logger.error("获取首页推荐歌单列表失败", e);
            throw new MusicPlayerException(ErrConstant.UNKNOWN_ERR, "获取首页推荐歌单列表失败");
        }
    }

}
