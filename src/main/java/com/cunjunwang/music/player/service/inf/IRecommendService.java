package com.cunjunwang.music.player.service.inf;

import com.cunjunwang.music.player.model.vo.disc.RecommendDiscGeneralVO;

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
    List<RecommendDiscGeneralVO> getDiscList();

    /**
     * 根据歌单Id查询歌曲列表
     *
     * @param discId 歌单Id
     * @return 歌曲列表
     */
    Boolean getDiscSongList(String discId);
}
