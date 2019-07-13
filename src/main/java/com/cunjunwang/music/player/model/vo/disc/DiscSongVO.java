package com.cunjunwang.music.player.model.vo.disc;

import com.cunjunwang.music.player.model.dto.api.DiscSingerApiDTO;
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
@ApiModel(value = "歌单歌曲展示对象")
public class DiscSongVO implements Serializable {

    private static final long serialVersionUID = -1L;

    @ApiModelProperty(value = "专辑Id", name = "albumId")
    private Long albumId;

    @ApiModelProperty(value = "专辑唯一识别Id", name = "albumMid")
    private String albumMid;

    @ApiModelProperty(value = "专辑名称", name = "albumName")
    private String albumName;

    @ApiModelProperty(value = "歌曲Id", name = "songId")
    private Long songId;

    @ApiModelProperty(value = "歌曲唯一识别Id", name = "songMid")
    private String songMid;

    @ApiModelProperty(value = "歌曲名称", name = "songName")
    private String songName;

    @ApiModelProperty(value = "歌曲长度(秒)", name = "duration")
    private Long duration;

    @ApiModelProperty(value = "128Kbps大小(KB)", name = "size128")
    private Long size128;

    @ApiModelProperty(value = "320Kbps大小(KB)", name = "size320")
    private Long size320;

    @ApiModelProperty(value = "歌手列表", name = "singerList")
    private List<DiscSingerApiDTO> singerList;

}
