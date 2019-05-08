package com.cunjunwang.music.player.service.inf;

import com.cunjunwang.music.player.model.CarouselVO;

import java.util.List;

/**
 * Created by CunjunWang on 2019-05-09.
 */
public interface IRecommendService {

    /**
     * 获取首页轮播图列表
     *
     * @return 轮播图列表
     */
    List<CarouselVO> getCarouselList();

}
