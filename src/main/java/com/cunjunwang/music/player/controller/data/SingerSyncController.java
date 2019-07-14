package com.cunjunwang.music.player.controller.data;

import com.cunjunwang.music.player.entity.ResultData;
import com.cunjunwang.music.player.model.vo.SingerInfoVO;
import com.cunjunwang.music.player.service.impl.SingerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by CunjunWang on 2019-07-14.
 */
@RestController
@RequestMapping(value = "/data/singer")
@Api(value = "[主数据]歌手数据维护与同步", tags = {"[主数据]歌手数据维护与同步"})
public class SingerSyncController {

    @Autowired
    private SingerService singerService;

    @ApiOperation(value = "获取歌手列表", notes = "获取歌手列表")
    @RequestMapping(value = "/getSingerList", method = RequestMethod.GET)
    public ResultData<List<SingerInfoVO>> getSingerList() {
        return new ResultData<>(ResultData.SUCCESS, "", "获取歌手列表完成",
                singerService.getSingerList());
    }

}
