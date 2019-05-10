package com.cunjunwang.music.player.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.cunjunwang.music.player.constant.Constant;
import com.cunjunwang.music.player.constant.ErrConstant;
import com.cunjunwang.music.player.exception.MusicPlayerException;
import com.cunjunwang.music.player.model.vo.CarouselVO;
import com.cunjunwang.music.player.model.vo.DiscVO;
import com.cunjunwang.music.player.service.inf.IRecommendService;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by CunjunWang on 2019-05-09.
 */
@Service
public class RecommendService implements IRecommendService {

    private static final Logger logger = LoggerFactory.getLogger(Constant.LOGGER);

    private static final String RESPONSE_CODE_KEY = "code";

    private static final Integer RESPONSE_CODE_OK = 0;

    private static final String RESPONSE_DATA_KEY = "data";

    private static final String RESPONSE_SLIDER_ARRAY_KEY = "slider";

    private static final String RESPONSE_DISC_ARRAY_KEY = "list";

    @Value("${com.cunjunwang.music.player.getHomepageDataUrl}")
    private String homepageDataUrl;

    @Value("${com.cunjunwang.music.player.getDiscListUrl}")
    private String discListUrl;

    @Autowired
    private RestTemplate restTemplate;

    /**
     * 获取首页轮播图列表
     *
     * @return 轮播图列表
     */
    @Override
    public List<CarouselVO> getCarouselList() {
        List<CarouselVO> result = new ArrayList<>();
        try {
            logger.info("开始获取首页轮播图列表");
            String responseString = restTemplate.getForObject(homepageDataUrl, String.class);
            JSONObject responseObject = JSONObject.parseObject(responseString);
            if (responseObject == null) {
                throw new MusicPlayerException(ErrConstant.UNKNOWN_ERR, "获取首页轮播图列表失败");
            }
            logger.info("QQ音乐响应[{}]", responseObject);
            if (RESPONSE_CODE_OK.equals(responseObject.getInteger(RESPONSE_CODE_KEY))) {
                logger.info("调用QQ音乐接口成功");
                JSONObject dataObject = responseObject.getJSONObject(RESPONSE_DATA_KEY);
                if (dataObject == null) {
                    throw new MusicPlayerException(ErrConstant.UNKNOWN_ERR, "获取首页轮播图列表失败");
                }
                JSONArray sliderArray = dataObject.getJSONArray(RESPONSE_SLIDER_ARRAY_KEY);
                if (sliderArray != null) {
                    String sliderArrayString = sliderArray.toJSONString();
                    result = JSONArray.parseArray(sliderArrayString, CarouselVO.class);
                }
            }
            return result;
        } catch (Exception e) {
            logger.error("获取首页轮播图列表失败", e);
            throw new MusicPlayerException(ErrConstant.UNKNOWN_ERR, "获取首页轮播图列表失败");
        }
    }

    /**
     * 获取歌单列表
     *
     * @return 歌单列表
     */
    @Override
    public List<DiscVO> getDiscList() {
        List<DiscVO> result = new ArrayList<>();
        try {
            logger.info("开始获取首页推荐歌单列表");
            HttpHeaders headers = new HttpHeaders();
            headers.add("referer", "https://c.y.qq.com/");
            headers.add("host", "c.y.qq.com");
            HttpEntity entity = new HttpEntity(headers);
            Map<String, Object> params = new HashMap<>();
            params.put("platform", "yqq");
            params.put("hostUin", 0);
            params.put("sin", 0);
            params.put("ein", 29);
            params.put("sortId", 5);
            params.put("needNewCode", 0);
            params.put("categoryId", 10000000);
            params.put("format", "json");
            String responseString = restTemplate.exchange(discListUrl, HttpMethod.GET, entity, String.class, params).getBody();
            JSONObject responseObject = JSONObject.parseObject(responseString);
            if (responseObject == null) {
                throw new MusicPlayerException(ErrConstant.UNKNOWN_ERR, "获取首页推荐歌单列表失败");
            }
            logger.info("QQ音乐响应[{}]", responseObject);
            if (RESPONSE_CODE_OK.equals(responseObject.getInteger(RESPONSE_CODE_KEY))) {
                logger.info("调用QQ音乐接口成功");
                JSONObject dataObject = responseObject.getJSONObject(RESPONSE_DATA_KEY);
                if (dataObject == null) {
                    throw new MusicPlayerException(ErrConstant.UNKNOWN_ERR, "获取首页推荐歌单列表失败");
                }
                JSONArray discArray = dataObject.getJSONArray(RESPONSE_DISC_ARRAY_KEY);
                if (discArray != null) {
                    String discArrayString = discArray.toJSONString();
                    result = JSONArray.parseArray(discArrayString, DiscVO.class);
                }
            }
            return result;
        } catch (Exception e) {
            logger.error("获取首页推荐歌单列表失败", e);
            throw new MusicPlayerException(ErrConstant.UNKNOWN_ERR, "获取首页推荐歌单列表失败");
        }
    }
}
