package com.cunjunwang.music.player.model.vo.disc;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

/**
 * Created by CunjunWang on 2019-06-29.
 */
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "推荐歌单数据展示对象")
public class RecommendDiscGeneralVO implements Serializable {

    private static final long serialVersionUID = -1L;

    @ApiModelProperty(value = "discId", name = "歌单Id")
    private String discId;

    @ApiModelProperty(value = "discName", name = "歌单名称")
    private String discName;

    @ApiModelProperty(value = "createDate", name = "创建时间")
    private String createDate;

    @ApiModelProperty(value = "imageUrl", name = "封面图片地址")
    private String imageUrl;

    @ApiModelProperty(value = "description", name = "描述")
    private String description;

    @ApiModelProperty(value = "listenNumber", name = "播放量")
    private Long listenNumber;

    @ApiModelProperty(value = "creatorQQ", name = "创建人qq号")
    private Long creatorQQ;

    @ApiModelProperty(value = "creatorName", name = "创建人昵称")
    private String creatorName;

}
