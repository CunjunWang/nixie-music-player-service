package com.cunjunwang.music.player.entity;

import lombok.*;

import java.io.Serializable;

/**
 * Created by CunjunWang on 2019-05-04.
 */
@Data
@Getter
@Setter
@ToString
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class ResultData<T> implements Serializable {
    private static final long serialVersionUID = -1L;

    private int status;

    private String errCode;

    private String errMsg;

    private T data;

    public static final int SUCCESS = 1;

    public static final int FAILURE = 0;
}
