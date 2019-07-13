package com.cunjunwang.music.player.service.impl;

import com.cunjunwang.music.player.constant.Constant;
import com.cunjunwang.music.player.model.vo.CarouselVO;
import com.cunjunwang.music.player.service.impl.api.CarouselApiService;
import com.cunjunwang.music.player.service.inf.ICarouselService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by CunjunWang on 2019-06-29.
 */
@Service
public class CarouselService implements ICarouselService {

    private static final Logger logger = LoggerFactory.getLogger(Constant.LOGGER);

    @Autowired
    private CarouselApiService carouselApiService;

    /**
     * 获取首页轮播图列表
     *
     * @return 轮播图列表
     */
    @Override
    public List<CarouselVO> getHomeCarouselList() {
        logger.info("获取首页轮播图列表数据");
        return carouselApiService.getCarouselList();
    }
}
