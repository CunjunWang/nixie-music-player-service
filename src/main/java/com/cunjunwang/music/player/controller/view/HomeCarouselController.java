package com.cunjunwang.music.player.controller.view;

import com.cunjunwang.music.player.constant.ErrConstant;
import com.cunjunwang.music.player.entity.ResultData;
import com.cunjunwang.music.player.model.vo.CarouselVO;
import com.cunjunwang.music.player.service.impl.CarouselService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by CunjunWang on 2019-06-29.
 */

@RestController
@RequestMapping(value = "/carousel")
@Api(value = "[首页轮播]首页轮播图相关API", tags = {"[首页轮播]首页轮播图相关API"})
public class HomeCarouselController {

    @Autowired
    private CarouselService carouselService;

    @ApiOperation(value = "获取首页轮播图", notes = "获取首页轮播图")
    @RequestMapping(value = "/getCarouselList", method = RequestMethod.GET)
    public ResultData<List<CarouselVO>> getCarouselList() {
        return new ResultData<>(ResultData.SUCCESS, ErrConstant.SUCCESS, "获取首页轮播图列表成功",
                carouselService.getHomeCarouselList());
    }

}
