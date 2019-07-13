package com.cunjunwang.music.player.service.inf;

import com.alibaba.fastjson.JSONObject;
import com.cunjunwang.music.player.model.dto.singer.SingerQueryParam;
import com.cunjunwang.music.player.model.dto.singer.SingerTagsCollectDTO;
import com.cunjunwang.music.player.model.vo.SingerInfoVO;

import java.util.List;

/**
 * Created by CunjunWang on 2019-05-11.
 */
public interface ISingerService {

    /**
     * 获取歌手列表
     *
     * @return 歌手信息列表
     */
    List<SingerInfoVO> getSingerList();

    /**
     * 获取歌手标签
     *
     * @return 标签列表对象
     */
    SingerTagsCollectDTO getTags();

    /**
     * 解析并存储标签
     *
     * @param tagsObject 标签对象
     * @return 标签列表对象
     */
    SingerTagsCollectDTO parseAndSaveTags(JSONObject tagsObject);

    /**
     * 按条件查询歌手
     *
     * @param queryParam 查询参数
     * @return 歌手列表
     */
    List<SingerInfoVO> querySingerByCondition(SingerQueryParam queryParam);
}
