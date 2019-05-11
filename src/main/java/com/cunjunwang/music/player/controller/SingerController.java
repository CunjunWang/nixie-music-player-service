package com.cunjunwang.music.player.controller;

import com.cunjunwang.music.player.entity.ResultData;
import com.cunjunwang.music.player.model.vo.SingerInfoVO;
import com.cunjunwang.music.player.service.impl.SingerService;
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
    public ResultData<String> querySingerByCondition() {
        return new ResultData<>(ResultData.SUCCESS, "", "按条件筛选歌手完成", null);
    }

    @ApiOperation(value = "测试歌手数据API", notes = "测试歌手数据API")
    @RequestMapping(value = "/testSinger", method = RequestMethod.GET)
    public ResultData<String> test(
            @ApiParam(name = "testParam", value = "测试参数", required = true)
            @RequestParam String testParam) {
        return new ResultData<>(ResultData.SUCCESS, "", "测试接口调用成功", testParam);
    }
}
