package com.cunjunwang.music.player.service.inf.api;

import com.cunjunwang.music.player.model.dto.api.DiscDetailApiDTO;
import com.cunjunwang.music.player.model.dto.api.DiscSongApiDTO;
import com.cunjunwang.music.player.model.dto.api.RecommendDiscApiDTO;

import java.util.List;
import java.util.Map;

/**
 * Created by CunjunWang on 2019-06-29.
 */
public interface IDiscApiService {

    /**
     * 获取推荐歌单列表
     *
     * @param params 接口url参数
     * @return 推荐歌单列表
     */
    List<RecommendDiscApiDTO> getRecommendDiscList(Map<String, String> params);

    /**
     * 获取歌单详情
     *
     * @param params 接口url参数
     * @return 歌单详情
     */
    DiscDetailApiDTO getDiscDetail(Map<String, String> params);

    /**
     * 获取歌单列表
     *
     * @param params 接口url参数
     * @return 歌单列表
     */
    List<DiscSongApiDTO> getDiscSongList(Map<String, String> params);

}
