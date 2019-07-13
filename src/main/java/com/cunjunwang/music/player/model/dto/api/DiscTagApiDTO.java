package com.cunjunwang.music.player.model.dto.api;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

/**
 * Created by CunjunWang on 2019-07-13.
 */
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "QQ音乐歌单标签对象")
public class DiscTagApiDTO implements Serializable {

    private static final long serialVersionUID = -1L;

    private Long id;

    private String name;

    private Long pid;

}
