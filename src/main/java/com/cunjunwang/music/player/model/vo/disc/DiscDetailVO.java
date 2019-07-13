package com.cunjunwang.music.player.model.vo.disc;

import com.cunjunwang.music.player.model.dto.api.DiscTagApiDTO;
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
@ApiModel(value = "歌单详情展示对象")
public class DiscDetailVO implements Serializable {

    private static final long serialVersionUID = -1L;

    @ApiModelProperty(value = "歌单Id (disstid)", name = "discId")
    private String discId;

    @ApiModelProperty(value = "歌单QQ音乐主键Id (dissid)", name = "discKeyId")
    private Long discKeyId;

    @ApiModelProperty(value = "歌单名称", name = "discName")
    private String discName;

    @ApiModelProperty(value = "图片地址", name = "logo")
    private String logo;

    @ApiModelProperty(value = "歌单描述", name = "description")
    private String description;

    @ApiModelProperty(value = "创建人昵称", name = "nickname")
    private String nickname;

    @ApiModelProperty(value = "访问量", name = "visitNumber")
    private Long visitNumber;

    @ApiModelProperty(value = "歌曲数量", name = "songNumber")
    private Integer songNumber;

    @ApiModelProperty(value = "标签列表", name = "tags")
    private List<DiscTagApiDTO> tags;

}
