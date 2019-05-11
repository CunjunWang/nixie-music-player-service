package com.cunjunwang.music.player.service.inf;

import com.cunjunwang.music.player.model.vo.SingerInfoVO;

import java.util.List;

/**
 * Created by CunjunWang on 2019-05-11.
 */
public interface ISingerService {

    /**
     * 获取歌手列表
     *
     * @return 歌手信息列表
     */
    List<SingerInfoVO> getSingerList();
}
