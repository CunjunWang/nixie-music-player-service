package com.cunjunwang.music.player.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.cunjunwang.music.player.constant.Constant;
import com.cunjunwang.music.player.constant.ErrConstant;
import com.cunjunwang.music.player.exception.MusicPlayerException;
import com.cunjunwang.music.player.model.vo.SingerInfoVO;
import com.cunjunwang.music.player.service.inf.ISingerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by CunjunWang on 2019-05-11.
 */
@Service
public class SingerService implements ISingerService {

    private static final Logger logger = LoggerFactory.getLogger(Constant.LOGGER);

    private static final String RESPONSE_CODE_KEY = "code";

    private static final Integer RESPONSE_CODE_OK = 0;

    private static final String RESPONSE_SINGER_LIST_KEY = "singerList";

    private static final String RESPONSE_DATA_KEY = "data";

    private static final String SINGER_LIST_KEY = "singerlist";

    @Value("${com.cunjunwang.music.player.getSingerListUrl}")
    private String getSingerListUrl;

    @Autowired
    private RestTemplate restTemplate;

    /**
     * 获取歌手列表
     *
     * @return 歌手信息列表
     */
    @Override
    public List<SingerInfoVO> getSingerList() {
        List<SingerInfoVO> result = new ArrayList<>();
        try {
            logger.info("开始获取歌手列表");
            String responseString = restTemplate.getForObject(getSingerListUrl, String.class);
            JSONObject responseObject = JSONObject.parseObject(responseString);
            if (responseObject == null) {
                throw new MusicPlayerException(ErrConstant.UNKNOWN_ERR, "获取歌手列表失败");
            }
            if (RESPONSE_CODE_OK.equals(responseObject.getInteger(RESPONSE_CODE_KEY))) {
                logger.info("调用QQ音乐接口成功");
                JSONObject singerListObject = responseObject.getJSONObject(RESPONSE_SINGER_LIST_KEY);
                if (singerListObject == null) {
                    throw new MusicPlayerException(ErrConstant.UNKNOWN_ERR, "获取歌手列表失败");
                }
                JSONObject dataObject = singerListObject.getJSONObject(RESPONSE_DATA_KEY);
                if (dataObject == null) {
                    throw new MusicPlayerException(ErrConstant.UNKNOWN_ERR, "获取歌手列表失败");
                }
                JSONArray singerListArray = dataObject.getJSONArray(SINGER_LIST_KEY);
                if (singerListArray != null) {
                    String singerListArrayString = singerListArray.toJSONString();
                    result = JSONArray.parseArray(singerListArrayString, SingerInfoVO.class);
                }
            }
            return result;
        } catch (Exception e) {
            logger.error("获取歌手列表失败", e);
            throw new MusicPlayerException(ErrConstant.UNKNOWN_ERR, "获取歌手列表失败");
        }
    }
}
