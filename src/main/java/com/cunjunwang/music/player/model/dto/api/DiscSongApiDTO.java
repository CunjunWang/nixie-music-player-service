package com.cunjunwang.music.player.model.dto.api;

import io.swagger.annotations.ApiModel;
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
@ApiModel(value = "QQ音乐歌单歌曲数据对象")
public class DiscSongApiDTO implements Serializable {

    private static final long serialVersionUID = -1L;

    private Long albumid;

    private String albummid;

    private String albumname;

    private Long interval;

    private Long songid;

    private String songmid;

    private String songname;

    private Long size128;

    private Long size320;

    private PaymentInfoApiDTO pay;

    private List<DiscSingerApiDTO> singer;

}
