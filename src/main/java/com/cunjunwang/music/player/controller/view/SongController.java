package com.cunjunwang.music.player.controller.view;

import com.cunjunwang.music.player.entity.ResultData;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by CunjunWang on 2019-05-04.
 */
@RestController
@RequestMapping(value = "/song")
@Api(value = "歌曲数据API", tags = {"歌曲数据API"})
public class SongController {

    @ApiOperation(value = "测试歌曲数据API", notes = "测试歌曲数据API")
    @RequestMapping(value = "/testSong", method = RequestMethod.GET)
    public ResultData<String> test(
            @ApiParam(name = "testParam", value = "测试参数", required = true)
            @RequestParam String testParam) {
        return new ResultData<>(ResultData.SUCCESS, "", "测试接口调用成功", testParam);
    }

}
