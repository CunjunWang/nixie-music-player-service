package com.cunjunwang.music.player.dao;

import com.cunjunwang.music.player.model.po.TmSingerTags;
import org.springframework.stereotype.Repository;

@Repository
public interface TmSingerTagsMapper {
    int insert(TmSingerTags record);

    int insertSelective(TmSingerTags record);

    TmSingerTags selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(TmSingerTags record);

    int updateByPrimaryKey(TmSingerTags record);
}