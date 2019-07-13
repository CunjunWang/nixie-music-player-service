package com.cunjunwang.music.player.model.dto;

import com.cunjunwang.music.player.model.vo.disc.DiscSongVO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

/**
 * Created by CunjunWang on 2019-07-13.
 */
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "歌单歌曲数据返回对象")
public class DiscSongDataDTO implements Serializable {

    private static final long serialVersionUID = -1L;

    @ApiModelProperty(value = "歌单Id", name = "discId")
    private String discId;

    @ApiModelProperty(value = "歌曲数量", name = "songCount")
    private Integer songCount;

    @ApiModelProperty(value = "歌曲列表", name = "songList")
    private List<DiscSongVO> songList;

}
