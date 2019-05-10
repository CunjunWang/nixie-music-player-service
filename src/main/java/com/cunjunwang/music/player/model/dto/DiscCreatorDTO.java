package com.cunjunwang.music.player.model.dto;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

/**
 * Created by CunjunWang on 2019-05-10.
 */
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "歌单创建人对象")
public class DiscCreatorDTO implements Serializable {

    private static final long serialVersionUID = -1L;

    private Integer type;

    private Long qq;

    private String encrypt_uin;

    private String name;

    private Integer isVip;

    private String avatarUrl;

    private Integer followflag;

}
