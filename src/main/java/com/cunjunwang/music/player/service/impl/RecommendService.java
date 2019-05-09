package com.cunjunwang.music.player.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.cunjunwang.music.player.constant.Constant;
import com.cunjunwang.music.player.constant.ErrConstant;
import com.cunjunwang.music.player.exception.MusicPlayerException;
import com.cunjunwang.music.player.model.CarouselVO;
import com.cunjunwang.music.player.service.inf.IRecommendService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

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

    @Value("${com.cunjunwang.music.player.getHomepageDataUrl}")
    private String homepageDataUrl;

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
                    for (int i = 0; i < sliderArray.size(); i++) {
                        JSONObject object = sliderArray.getJSONObject(i);
                        String objectString = object.toJSONString();
                        CarouselVO carouselVO = JSON.parseObject(objectString,
                                new TypeReference<CarouselVO>(){});
                        result.add(carouselVO);
                    }
                }
            }
            return result;
        } catch (Exception e) {
            logger.error("获取首页轮播图列表失败", e);
            throw new MusicPlayerException(ErrConstant.UNKNOWN_ERR, "获取首页轮播图列表失败");
        }
    }
}
