package com.cunjunwang.music.player.service.impl;

import com.cunjunwang.music.player.constant.Constant;
import com.cunjunwang.music.player.constant.QQApiConstant;
import com.cunjunwang.music.player.model.vo.RecommendDiscVO;
import com.cunjunwang.music.player.service.impl.api.RecommendApiService;
import com.cunjunwang.music.player.service.inf.IRecommendService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;


/**
 * Created by CunjunWang on 2019-05-09.
 */
@Service
public class RecommendService implements IRecommendService {

    private static final Logger logger = LoggerFactory.getLogger(Constant.LOGGER);

    @Autowired
    private RecommendApiService recommendApiService;

    /**
     * 获取首页推荐歌单列表
     *
     * @return 首页推荐歌单列表
     */
    @Cacheable(value = "music:player:discList", key = "'music:player:discList'")
    public List<RecommendDiscVO> getDiscList() {
        logger.info("获取首页推荐歌单列表");
        Map<String, String> params = new HashMap<>();
        params.put(QQApiConstant.QQ_API_KEY_G_TK, QQApiConstant.QQ_API_VALUE_G_TK);
        params.put(QQApiConstant.QQ_API_KEY_IN_CHAR_SET, StandardCharsets.UTF_8.displayName());
        params.put(QQApiConstant.QQ_API_KEY_OUT_CHAR_SET, StandardCharsets.UTF_8.displayName());
        params.put(QQApiConstant.QQ_API_KEY_NOTICE, "0");
        params.put(QQApiConstant.QQ_API_KEY_PLATFORM, QQApiConstant.QQ_API_VALUE_PLATFORM);
        params.put(QQApiConstant.QQ_API_KEY_HOST_UIN, "0");
        params.put(QQApiConstant.QQ_API_KEY_SIN, "0");
        params.put(QQApiConstant.QQ_API_KEY_EIN, "29");
        params.put(QQApiConstant.QQ_API_KEY_SORT_ID, "5");
        params.put(QQApiConstant.QQ_API_KEY_NEED_NEW_CODE, "0");
        params.put(QQApiConstant.QQ_API_KEY_CATEGORY_ID, "10000000");
        params.put(QQApiConstant.QQ_API_KEY_FORMAT, QQApiConstant.QQ_API_VALUE_FORMAT_JSON);
        params.put(QQApiConstant.QQ_API_KEY_RND, String.valueOf(new Random().nextInt()));
        return recommendApiService.getRecommendDiscList(params);
    }

}
