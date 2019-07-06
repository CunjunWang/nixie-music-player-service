package com.cunjunwang.music.player.service.impl;

import com.cunjunwang.music.player.constant.Constant;
import com.cunjunwang.music.player.constant.ErrConstant;
import com.cunjunwang.music.player.constant.QQApiConstant;
import com.cunjunwang.music.player.exception.MusicPlayerException;
import com.cunjunwang.music.player.model.dto.DiscCreatorDTO;
import com.cunjunwang.music.player.model.dto.api.RecommendDiscApiDTO;
import com.cunjunwang.music.player.model.vo.disc.RecommendDiscGeneralVO;
import com.cunjunwang.music.player.service.impl.api.RecommendApiService;
import com.cunjunwang.music.player.service.inf.IRecommendService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.*;


/**
 * Created by CunjunWang on 2019-05-09.
 */
@Service
public class RecommendService implements IRecommendService {

    private static final Logger logger = LoggerFactory.getLogger(Constant.LOGGER);

    private String errMessage;

    @Autowired
    private RecommendApiService recommendApiService;

    /**
     * 获取首页推荐歌单列表
     *
     * @return 首页推荐歌单列表
     */
    @Cacheable(value = "music:player:discList", key = "'music:player:discList'")
    public List<RecommendDiscGeneralVO> getDiscList() {
        logger.info("获取首页推荐歌单列表");

        // 构造入参
        Map<String, String> params = new HashMap<>();
        params.put(QQApiConstant.QQ_API_KEY_G_TK, QQApiConstant.QQ_API_VALUE_G_TK);
        params.put(QQApiConstant.QQ_API_KEY_IN_CHAR_SET, StandardCharsets.UTF_8.displayName());
        params.put(QQApiConstant.QQ_API_KEY_OUT_CHAR_SET, StandardCharsets.UTF_8.displayName());
        params.put(QQApiConstant.QQ_API_KEY_NOTICE, "0");
        params.put(QQApiConstant.QQ_API_KEY_PLATFORM, QQApiConstant.QQ_API_VALUE_PLATFORM);
        params.put(QQApiConstant.QQ_API_KEY_HOST_UIN, "0");
        params.put(QQApiConstant.QQ_API_KEY_SIN, "0");
        params.put(QQApiConstant.QQ_API_KEY_EIN, "29");
        params.put(QQApiConstant.QQ_API_KEY_SORT_ID, "5");
        params.put(QQApiConstant.QQ_API_KEY_NEED_NEW_CODE, "0");
        params.put(QQApiConstant.QQ_API_KEY_CATEGORY_ID, "10000000");
        params.put(QQApiConstant.QQ_API_KEY_FORMAT, QQApiConstant.QQ_API_VALUE_FORMAT_JSON);
        params.put(QQApiConstant.QQ_API_KEY_RND, String.valueOf(new Random().nextInt()));

        List<RecommendDiscApiDTO> recommendDiscList = recommendApiService.getRecommendDiscList(params);
        if (recommendDiscList == null || recommendDiscList.isEmpty()) {
            errMessage = "获取首页推荐歌单列表失败, 列表为空";
            logger.warn(errMessage);
            throw new MusicPlayerException(ErrConstant.NO_DATA_FOUND, errMessage);
        }
        List<RecommendDiscGeneralVO> result = new ArrayList<>();
        recommendDiscList.forEach(disc -> {
            RecommendDiscGeneralVO recommendDiscGeneralVO = new RecommendDiscGeneralVO();
            recommendDiscGeneralVO.setDiscId(disc.getDissid());
            recommendDiscGeneralVO.setDiscName(disc.getDissname());
            recommendDiscGeneralVO.setCreateDate(disc.getCreatetime());
            recommendDiscGeneralVO.setDescription(disc.getIntroduction());
            recommendDiscGeneralVO.setImageUrl(disc.getImgurl());
            recommendDiscGeneralVO.setListenNumber(disc.getListennum());
            DiscCreatorDTO creator = disc.getCreator();
            if (creator != null) {
                recommendDiscGeneralVO.setCreatorName(creator.getName());
                recommendDiscGeneralVO.setCreatorQQ(creator.getQq());
            }
            result.add(recommendDiscGeneralVO);
        });
        return result;
    }

    /**
     * 根据歌单Id查询歌曲列表
     *
     * @param discId 歌单Id
     * @return 歌曲列表
     */
    @Override
    public Boolean getDiscSongList(String discId) {
        return null;
    }

}
