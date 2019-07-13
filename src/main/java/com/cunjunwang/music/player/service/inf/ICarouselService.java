package com.cunjunwang.music.player.service.inf;

import com.cunjunwang.music.player.model.vo.CarouselVO;

import java.util.List;

/**
 * Created by CunjunWang on 2019-06-29.
 */
public interface ICarouselService {

    /**
     * 获取首页轮播图列表
     *
     * @return 轮播图列表
     */
    List<CarouselVO> getHomeCarouselList();

}
