package com.cunjunwang.music.player.model.vo;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

/**
 * Created by CunjunWang on 2019-05-11.
 */
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "歌手列表页展示对象")
public class SingerInfoVO implements Serializable {

    private static final long serialVersionUID = -1L;

    private String country;

    private Long singer_id;

    private String singer_mid;

    private String singer_name;

    private String singer_pic;

}
