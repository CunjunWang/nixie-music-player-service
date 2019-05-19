package com.cunjunwang.music.player.model.dto.singer;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

/**
 * Created by CunjunWang on 2019-05-19.
 */
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "歌手标签对象")
public class SingerTagDTO implements Serializable {

    private static final long serialVersionUID = -1L;

    @ApiModelProperty(value = "id", name = "id")
    private Long id;

    @ApiModelProperty(value = "名称", name = "name")
    private String name;
}
