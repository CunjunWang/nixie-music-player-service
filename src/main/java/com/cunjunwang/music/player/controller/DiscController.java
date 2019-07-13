package com.cunjunwang.music.player.controller;

import com.cunjunwang.music.player.constant.ErrConstant;
import com.cunjunwang.music.player.entity.ResultData;
import com.cunjunwang.music.player.model.dto.DiscSongDataDTO;
import com.cunjunwang.music.player.model.vo.disc.DiscDetailVO;
import com.cunjunwang.music.player.model.vo.disc.RecommendDiscGeneralVO;
import com.cunjunwang.music.player.service.impl.DiscService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by CunjunWang on 2019-05-09.
 */

@RestController
@RequestMapping(value = "/disc")
@Api(value = "[歌单]歌单相关接口", tags = {"[歌单]歌单相关接口"})
public class DiscController {

    @Autowired
    private DiscService discService;

    @ApiOperation(value = "获取推荐歌单列表", notes = "获取推荐歌单列表")
    @RequestMapping(value = "/getRecommendDiscList", method = RequestMethod.GET)
    public ResultData<List<RecommendDiscGeneralVO>> getDiscList() {
        return new ResultData<>(ResultData.SUCCESS, ErrConstant.SUCCESS, "获取首页推荐歌单列表成功",
                discService.getDiscList());
    }

    @ApiOperation(value = "获取歌单详情", notes = "获取歌单详情")
    @RequestMapping(value = "/getDetailByDiscId", method = RequestMethod.GET)
    public ResultData<DiscDetailVO> getDetailByDiscId(
            @ApiParam(name = "discId", value = "歌单Id", required = true)
            @RequestParam(value = "discId", required = true) String discId) {
        return new ResultData<>(ResultData.SUCCESS, ErrConstant.SUCCESS, "获取歌单详情成功",
                discService.getDiscDetailById(discId));
    }

    @ApiOperation(value = "获取推荐歌单歌曲列表", notes = "获取推荐歌单歌曲列表")
    @RequestMapping(value = "/getSongListByDiscId", method = RequestMethod.GET)
    public ResultData<DiscSongDataDTO> getDiscSongList(
            @ApiParam(name = "discId", value = "歌单Id", required = true)
            @RequestParam(value = "discId", required = true) String discId) {
        return new ResultData<>(ResultData.SUCCESS, ErrConstant.SUCCESS, "获取推荐歌单歌曲列表成功",
                discService.getDiscSongList(discId));
    }

}
