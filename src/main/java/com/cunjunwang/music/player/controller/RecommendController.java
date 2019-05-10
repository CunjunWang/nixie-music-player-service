package com.cunjunwang.music.player.controller;

import com.cunjunwang.music.player.entity.ResultData;
import com.cunjunwang.music.player.model.vo.CarouselVO;
import com.cunjunwang.music.player.model.vo.DiscVO;
import com.cunjunwang.music.player.service.impl.RecommendService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by CunjunWang on 2019-05-09.
 */

@RestController
@RequestMapping(value = "/recommend")
@Api(value = "推荐页面API", tags = {"推荐页面API"})
public class RecommendController {

    @Autowired
    private RecommendService recommendService;

    @ApiOperation(value = "首页轮播图API", notes = "首页轮播图API")
    @RequestMapping(value = "/carousel", method = RequestMethod.GET)
    public ResultData<List<CarouselVO>> getCarouselList() {
        return new ResultData<>(ResultData.SUCCESS, "", "获取首页轮播图列表成功",
                recommendService.getCarouselList());
    }

    @ApiOperation(value = "歌单列表API", notes = "歌单列表API")
    @RequestMapping(value = "/discList", method = RequestMethod.GET)
    public ResultData<List<DiscVO>> getDiscList() {
        return new ResultData<>(ResultData.SUCCESS, "", "获取首页歌单列表成功",
                recommendService.getDiscList());
    }

}
