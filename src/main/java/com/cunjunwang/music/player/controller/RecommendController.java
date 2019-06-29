package com.cunjunwang.music.player.controller;

import com.cunjunwang.music.player.entity.ResultData;
import com.cunjunwang.music.player.model.vo.RecommendDiscVO;
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
@Api(value = "[首页推荐]首页推荐页面API", tags = {"[首页推荐]首页推荐页面API"})
public class RecommendController {

    @Autowired
    private RecommendService recommendService;

    @ApiOperation(value = "获取推荐歌单列表", notes = "获取推荐歌单列表")
    @RequestMapping(value = "/getDiscList", method = RequestMethod.GET)
    public ResultData<List<RecommendDiscVO>> getDiscList() {
        return new ResultData<>(ResultData.SUCCESS, "", "获取首页推荐歌单列表成功",
                recommendService.getDiscList());
    }

//    @ApiOperation(value = "获取推荐歌单歌曲列表", notes = "获取推荐歌单歌曲列表")
//    @RequestMapping(value = "/discSongList", method = RequestMethod.GET)
//    public ResultData<Boolean> getDiscSongList() {
//        return new ResultData<>(ResultData.SUCCESS, "", "获取推荐歌单歌曲列表成功",
//                recommendService.getDiscList());
//    }

}
