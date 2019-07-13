package com.cunjunwang.music.player.service.inf.api;

import com.cunjunwang.music.player.model.vo.CarouselVO;

import java.util.List;

/**
 * 封装qq音乐首页轮播图接口
 * Created by CunjunWang on 2019-06-29.
 */
public interface ICarouselApiService {

    /**
     * 获取首页轮播图列表
     *
     * @return 轮播图列表
     */
    List<CarouselVO> getCarouselList();

}
