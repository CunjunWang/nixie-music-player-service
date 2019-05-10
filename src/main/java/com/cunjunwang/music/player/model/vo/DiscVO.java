package com.cunjunwang.music.player.model.vo;

import com.cunjunwang.music.player.model.dto.DiscCreatorDTO;
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
@ApiModel(value = "歌单展示对象")
public class DiscVO implements Serializable {

    private static final long serialVersionUID = -1L;

    private String dissid;

    private String createtime;

    private String commit_time;

    private String dissname;

    private String imgurl;

    private String introduction;

    private Long listennum;

    private Integer score;

    private Integer version;

    private DiscCreatorDTO creator;

}
