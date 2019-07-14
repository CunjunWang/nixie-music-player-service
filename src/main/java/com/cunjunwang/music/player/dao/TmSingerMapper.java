package com.cunjunwang.music.player.dao;

import com.cunjunwang.music.player.model.po.TmSinger;

public interface TmSingerMapper {
    int insert(TmSinger record);

    int insertSelective(TmSinger record);

    TmSinger selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(TmSinger record);

    int updateByPrimaryKey(TmSinger record);
}