package com.cunjunwang.music.player.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

/**
 * Created by CunjunWang on 2019-05-09.
 */
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "缩略图展示对象")
public class CarouselVO implements Serializable {

    private static final long serialVersionUID = -1L;

    @ApiModelProperty(value = "id", name = "id")
    private Long id;

    @ApiModelProperty(value = "链接地址", name = "linkUrl")
    private String linkUrl;

    @ApiModelProperty(value = "图片地址", name = "picUrl")
    private String picUrl;

}
