package com.cunjunwang.music.player.controller;

import com.cunjunwang.music.player.entity.ResultData;
import com.cunjunwang.music.player.model.dto.singer.SingerQueryParam;
import com.cunjunwang.music.player.model.dto.singer.SingerTagsCollectDTO;
import com.cunjunwang.music.player.model.vo.SingerInfoVO;
import com.cunjunwang.music.player.service.impl.SingerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by CunjunWang on 2019-05-04.
 */
@RestController
@RequestMapping(value = "/singer")
@Api(value = "歌手数据API", tags = {"歌手数据API"})
public class SingerController {

    @Autowired
    private SingerService singerService;

    @ApiOperation(value = "获取歌手列表", notes = "获取歌手列表")
    @RequestMapping(value = "/getSingerList", method = RequestMethod.GET)
    public ResultData<List<SingerInfoVO>> getSingerList() {
        return new ResultData<>(ResultData.SUCCESS, "", "获取歌手列表完成",
                singerService.getSingerList());
    }

    @ApiOperation(value = "按条件筛选歌手", notes = "按条件筛选歌手")
    @RequestMapping(value = "/getSingerList", method = RequestMethod.POST)
    public ResultData<List<SingerInfoVO>> querySingerByCondition(
            @ApiParam(name = "queryParam", value = "查询参数", required = true)
            @RequestBody(required = true) SingerQueryParam queryParam) {
        return new ResultData<>(ResultData.SUCCESS, "", "按条件筛选歌手完成",
                singerService.querySingerByCondition(queryParam));
    }

    @ApiOperation(value = "获取歌手标签", notes = "获取歌手标签")
    @RequestMapping(value = "/getSingerTags", method = RequestMethod.GET)
    public ResultData<SingerTagsCollectDTO> getSingerTags() {
        return new ResultData<>(ResultData.SUCCESS, "", "获取歌手标签完成",
                singerService.getTags());
    }

    @ApiOperation(value = "测试歌手数据API", notes = "测试歌手数据API")
    @RequestMapping(value = "/testSinger", method = RequestMethod.GET)
    public ResultData<String> test(
            @ApiParam(name = "testParam", value = "测试参数", required = true)
            @RequestParam String testParam) {
        return new ResultData<>(ResultData.SUCCESS, "", "测试接口调用成功", testParam);
    }
}
