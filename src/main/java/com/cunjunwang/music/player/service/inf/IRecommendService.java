package com.cunjunwang.music.player.service.inf;

import com.cunjunwang.music.player.model.vo.RecommendDiscVO;

import java.util.List;

/**
 * Created by CunjunWang on 2019-05-09.
 */
public interface IRecommendService {

    /**
     * 获取歌单列表
     *
     * @return 歌单列表
     */
    List<RecommendDiscVO> getDiscList();

}
