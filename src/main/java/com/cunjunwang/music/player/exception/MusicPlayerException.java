package com.cunjunwang.music.player.exception;

import lombok.*;

/**
 * Created by CunjunWang on 2018-12-22.
 */

@Setter
@Getter
@ToString
@NoArgsConstructor
@EqualsAndHashCode
public class MusicPlayerException extends RuntimeException {

    private String errCode;

    private String errMsg;

    public static String ERR_PREFIX = "MUSIC-";

    public MusicPlayerException(String errCode, String errMsg){
        this.errCode = ERR_PREFIX + errCode;
        this.errMsg = errMsg;
    }

}
