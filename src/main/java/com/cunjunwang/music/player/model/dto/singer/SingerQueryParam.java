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
@ApiModel(value = "歌手查询参数")
public class SingerQueryParam implements Serializable {

    private static final long serialVersionUID = -1L;

    @ApiModelProperty(value = "首字母", name = "index")
    private Long index;

    @ApiModelProperty(value = "流派", name = "genre")
    private Long genre;

    @ApiModelProperty(value = "性别", name = "gender")
    private Long gender;

    @ApiModelProperty(value = "区域", name = "area")
    private Long area;

    @ApiModelProperty(value = "当前页", name = "currentPage")
    private Long currentPage;

    @ApiModelProperty(value = "每页条数", name = "pageSize")
    private Long pageSize;

}
