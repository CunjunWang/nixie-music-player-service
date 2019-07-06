package com.cunjunwang.music.player.service.inf.api;

import com.cunjunwang.music.player.model.dto.api.RecommendDiscApiDTO;

import java.util.List;
import java.util.Map;

/**
 * Created by CunjunWang on 2019-06-29.
 */
public interface IRecommendApiService {

    /**
     * 获取推荐歌单列表
     *
     * @param params 接口url参数
     * @return 推荐歌单列表
     */
    List<RecommendDiscApiDTO> getRecommendDiscList(Map<String, String> params);

}
