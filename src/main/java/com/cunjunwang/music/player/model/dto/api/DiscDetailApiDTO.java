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
@ApiModel(value = "QQ音乐返回歌单详情对象")
public class DiscDetailApiDTO implements Serializable {

    private static final long serialVersionUID = -1L;

    private String disstid;

    private Long dissid;

    private String dissname;

    private String logo;

    private String desc;

    private String headurl;

    private String ifpicurl;

    private String nickname;

    private Long visitnum;

    private Integer songnum;

    private List<DiscTagApiDTO> tags;

}
