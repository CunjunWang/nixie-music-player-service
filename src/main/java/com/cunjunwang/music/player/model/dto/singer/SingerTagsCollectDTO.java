package com.cunjunwang.music.player.model.dto.singer;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

/**
 * Created by CunjunWang on 2019-05-19.
 */
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "歌手标签组合对象")
public class SingerTagsCollectDTO implements Serializable {

    private static final long serialVersionUID = -1L;

    @ApiModelProperty(value = "区域标签", name = "areaTags")
    private List<SingerTagDTO> areaTags;

    @ApiModelProperty(value = "流派标签", name = "genreTags")
    private List<SingerTagDTO> genreTags;

    @ApiModelProperty(value = "下标标签", name = "indexTags")
    private List<SingerTagDTO> indexTags;

    @ApiModelProperty(value = "性别标签", name = "genderTags")
    private List<SingerTagDTO> genderTags;

}
