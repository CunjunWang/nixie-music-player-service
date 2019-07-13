package com.cunjunwang.music.player.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.cunjunwang.music.player.constant.Constant;
import com.cunjunwang.music.player.constant.ErrConstant;
import com.cunjunwang.music.player.exception.MusicPlayerException;
import com.cunjunwang.music.player.model.dto.singer.SingerQueryParam;
import com.cunjunwang.music.player.model.dto.singer.SingerTagDTO;
import com.cunjunwang.music.player.model.dto.singer.SingerTagsCollectDTO;
import com.cunjunwang.music.player.model.vo.SingerInfoVO;
import com.cunjunwang.music.player.service.inf.ISingerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
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

    private static final String RESPONSE_TAGS_KEY = "tags";

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
        try {
            logger.info("开始获取歌手列表");
            return this.helper(getSingerListUrl);
        } catch (Exception e) {
            logger.error("获取歌手列表失败", e);
            throw new MusicPlayerException(ErrConstant.UNKNOWN_ERR, "获取歌手列表失败");
        }
    }

    /**
     * 获取歌手标签
     *
     * @return 标签列表对象
     */
    @Override
    public SingerTagsCollectDTO getTags() {
        try {
            logger.info("获取歌手标签");
            String responseString = restTemplate.getForObject(getSingerListUrl, String.class);
            JSONObject responseObject = JSONObject.parseObject(responseString);
            if (responseObject == null) {
                throw new MusicPlayerException(ErrConstant.UNKNOWN_ERR, "获取歌手标签失败");
            }
            if (RESPONSE_CODE_OK.equals(responseObject.getInteger(RESPONSE_CODE_KEY))) {
                logger.info("调用QQ音乐接口成功");
                JSONObject singerListObject = responseObject.getJSONObject(RESPONSE_SINGER_LIST_KEY);
                if (singerListObject == null) {
                    throw new MusicPlayerException(ErrConstant.UNKNOWN_ERR, "获取歌手标签失败");
                }
                JSONObject dataObject = singerListObject.getJSONObject(RESPONSE_DATA_KEY);
                if (dataObject == null) {
                    throw new MusicPlayerException(ErrConstant.UNKNOWN_ERR, "获取歌手列表失败");
                }
                JSONObject tagsObject = dataObject.getJSONObject(RESPONSE_TAGS_KEY);
                if (tagsObject == null) {
                    throw new MusicPlayerException(ErrConstant.UNKNOWN_ERR, "获取歌手标签失败");
                }
                return this.parseAndSaveTags(tagsObject);
            } else {
                logger.warn("调用QQ音乐接口失败");
                return null;
            }
        } catch (Exception e) {
            logger.error("获取歌手标签失败", e);
            throw new MusicPlayerException(ErrConstant.UNKNOWN_ERR, "获取歌手标签失败");
        }
    }

    /**
     * 解析并存储标签
     *
     * @param tagsObject 标签对象
     * @return 标签列表对象
     */
    @Override
    public SingerTagsCollectDTO parseAndSaveTags(JSONObject tagsObject) {
        JSONArray areaArray = tagsObject.getJSONArray("area");
        JSONArray genreArray = tagsObject.getJSONArray("genre");
        JSONArray indexArray = tagsObject.getJSONArray("index");
        JSONArray genderArray = tagsObject.getJSONArray("sex");
        List<SingerTagDTO> areaTags = JSONArray.parseArray(areaArray.toJSONString(), SingerTagDTO.class);
        List<SingerTagDTO> genreTags = JSONArray.parseArray(genreArray.toJSONString(), SingerTagDTO.class);
        List<SingerTagDTO> indexTags = JSONArray.parseArray(indexArray.toJSONString(), SingerTagDTO.class);
        List<SingerTagDTO> genderTags = JSONArray.parseArray(genderArray.toJSONString(), SingerTagDTO.class);
        SingerTagsCollectDTO result = new SingerTagsCollectDTO();
        result.setAreaTags(areaTags);
        result.setGenderTags(genderTags);
        result.setGenreTags(genreTags);
        result.setIndexTags(indexTags);
        return result;
    }

    /**
     * 按条件查询歌手
     *
     * @param queryParam 查询参数
     * @return 歌手列表
     */
    @Override
    public List<SingerInfoVO> querySingerByCondition(SingerQueryParam queryParam) {
        String queryData = this.buildQueryData(queryParam);
        String url = "https://u.y.qq.com/cgi-bin/musicu.fcg?-=getUCGI49693754063535467&g_tk=5381&loginUin=0&hostUin=0&format=json&inCharset=utf8&outCharset=utf-8&notice=0&platform=yqq.json&needNewCode=0&data=" +
                queryData + "%7D%7D";
        logger.info("format: {}", url);
        return this.helper(url);
    }

    private List<SingerInfoVO> helper(String url) {
        List<SingerInfoVO> result = new ArrayList<>();
        String responseString = restTemplate.getForObject(url, String.class);
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
    }

    private String buildQueryData(SingerQueryParam queryParam) {
        try {
            String data = "{\"comm\":{\"ct\":24,\"cv\":0},\"singerList\":{\"module\":\"Music.SingerListServer\",\"method\":\"get_singer_list\",\"param\":{\"area\":%d,\"sex\":%d,\"genre\":%d,\"index\":%d,\"sin\":0,\"cur_page\":%d}";
            String format = String.format(data, queryParam.getArea(), queryParam.getGender(), queryParam.getGenre(), queryParam.getIndex(), queryParam.getCurrentPage());
            // logger.info("data: {}", format);
            return URLEncoder.encode(format, StandardCharsets.UTF_8.displayName());
        } catch (Exception e) {
            return null;
        }
    }
}
