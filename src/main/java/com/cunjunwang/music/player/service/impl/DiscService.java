package com.cunjunwang.music.player.service.impl;

import com.cunjunwang.music.player.constant.Constant;
import com.cunjunwang.music.player.constant.ErrConstant;
import com.cunjunwang.music.player.constant.QQApiConstant;
import com.cunjunwang.music.player.exception.MusicPlayerException;
import com.cunjunwang.music.player.model.dto.DiscCreatorDTO;
import com.cunjunwang.music.player.model.dto.api.DiscDetailApiDTO;
import com.cunjunwang.music.player.model.dto.api.RecommendDiscApiDTO;
import com.cunjunwang.music.player.model.vo.disc.DiscDetailVO;
import com.cunjunwang.music.player.model.vo.disc.RecommendDiscGeneralVO;
import com.cunjunwang.music.player.service.impl.api.DiscApiService;
import com.cunjunwang.music.player.service.inf.IDiscService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.*;


/**
 * Created by CunjunWang on 2019-05-09.
 */
@Service
public class DiscService implements IDiscService {

    private static final Logger logger = LoggerFactory.getLogger(Constant.LOGGER);

    private String errMessage;

    @Autowired
    private DiscApiService discApiService;

    /**
     * 获取首页推荐歌单列表
     *
     * @return 首页推荐歌单列表
     */
    public List<RecommendDiscGeneralVO> getDiscList() {
        logger.info("获取首页推荐歌单列表");

        // 构造入参
        Map<String, String> params = new HashMap<>();
        params.put(QQApiConstant.QQ_API_KEY_G_TK, QQApiConstant.QQ_API_VALUE_G_TK);
        params.put(QQApiConstant.QQ_API_KEY_IN_CHAR_SET, StandardCharsets.UTF_8.displayName());
        params.put(QQApiConstant.QQ_API_KEY_OUT_CHAR_SET, StandardCharsets.UTF_8.displayName());
        params.put(QQApiConstant.QQ_API_KEY_NOTICE, "0");
        params.put(QQApiConstant.QQ_API_KEY_PLATFORM, QQApiConstant.QQ_API_VALUE_PLATFORM_YQQ);
        params.put(QQApiConstant.QQ_API_KEY_HOST_UIN, "0");
        params.put(QQApiConstant.QQ_API_KEY_SIN, "0");
        params.put(QQApiConstant.QQ_API_KEY_EIN, "29");
        params.put(QQApiConstant.QQ_API_KEY_SORT_ID, "5");
        params.put(QQApiConstant.QQ_API_KEY_NEED_NEW_CODE, "0");
        params.put(QQApiConstant.QQ_API_KEY_CATEGORY_ID, "10000000");
        params.put(QQApiConstant.QQ_API_KEY_FORMAT, QQApiConstant.QQ_API_VALUE_FORMAT_JSON);
        params.put(QQApiConstant.QQ_API_KEY_RND, String.valueOf(new Random().nextInt()));

        List<RecommendDiscApiDTO> recommendDiscList = discApiService.getRecommendDiscList(params);
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
     * 根据歌单Id查询歌单详情
     *
     * @param discId 歌单Id
     * @return 歌单详情
     */
    @Override
    public DiscDetailVO getDiscDetailById(String discId) {
        logger.info("根据歌单Id[{}]获取歌单详情", discId);

        // 构造入参
        Map<String, String> params = new HashMap<>();
        params.put(QQApiConstant.QQ_API_KEY_G_TK, QQApiConstant.QQ_API_VALUE_G_TK);
        params.put(QQApiConstant.QQ_API_KEY_IN_CHAR_SET, StandardCharsets.UTF_8.displayName());
        params.put(QQApiConstant.QQ_API_KEY_OUT_CHAR_SET, StandardCharsets.UTF_8.displayName());
        params.put(QQApiConstant.QQ_API_KEY_NOTICE, "0");
        params.put(QQApiConstant.QQ_API_KEY_FORMAT, QQApiConstant.QQ_API_VALUE_FORMAT_JSONP);
        params.put(QQApiConstant.QQ_API_KEY_PLATFORM, QQApiConstant.QQ_API_VALUE_PLATFORM_YQQ);
        params.put(QQApiConstant.QQ_API_KEY_HOST_UIN, "0");
        params.put(QQApiConstant.QQ_API_KEY_NEED_NEW_CODE, "0");
        params.put(QQApiConstant.QQ_API_KEY_TYPE, "1");
        params.put(QQApiConstant.QQ_API_KEY_JSON, "1");
        params.put(QQApiConstant.QQ_API_KEY_UTF_8, "1");
        params.put(QQApiConstant.QQ_API_KEY_ONLY_SONG, "0");
        params.put(QQApiConstant.QQ_API_KEY_DISSTID, discId);

        DiscDetailApiDTO discDetail = discApiService.getDiscDetail(params);
        if (discDetail == null) {
            errMessage = "获取歌单详情失败, 数据对象为空";
            logger.warn(errMessage);
            throw new MusicPlayerException(ErrConstant.NO_DATA_FOUND, errMessage);
        }

        DiscDetailVO discDetailVO = new DiscDetailVO();
        discDetailVO.setDiscId(discDetail.getDisstid());
        discDetailVO.setDiscKeyId(discDetail.getDissid());
        discDetailVO.setDescription(discDetail.getDesc());
        discDetailVO.setDiscName(discDetail.getDissname());
        discDetailVO.setLogo(discDetail.getLogo());
        discDetailVO.setNickname(discDetail.getNickname());
        discDetailVO.setSongNumber(discDetail.getSongnum());
        discDetailVO.setVisitNumber(discDetail.getVisitnum());
        discDetailVO.setTags(discDetail.getTags());

        return discDetailVO;
    }

    /**
     * 根据歌单Id查询歌曲列表
     *
     * @param discId 歌单Id
     * @return 歌曲列表
     */
    @Override
    public Boolean getDiscSongList(String discId) {
        logger.info("根据歌单Id[{}]获取歌曲列表", discId);

        // 构造入参
        Map<String, String> params = new HashMap<>();
        params.put(QQApiConstant.QQ_API_KEY_G_TK, QQApiConstant.QQ_API_VALUE_G_TK);
        params.put(QQApiConstant.QQ_API_KEY_IN_CHAR_SET, StandardCharsets.UTF_8.displayName());
        params.put(QQApiConstant.QQ_API_KEY_OUT_CHAR_SET, StandardCharsets.UTF_8.displayName());
        params.put(QQApiConstant.QQ_API_KEY_NOTICE, "0");
        params.put(QQApiConstant.QQ_API_KEY_FORMAT, QQApiConstant.QQ_API_VALUE_FORMAT_JSONP);
        params.put(QQApiConstant.QQ_API_KEY_PLATFORM, QQApiConstant.QQ_API_VALUE_PLATFORM_YQQ);
        params.put(QQApiConstant.QQ_API_KEY_HOST_UIN, "0");
        params.put(QQApiConstant.QQ_API_KEY_NEED_NEW_CODE, "0");
        params.put(QQApiConstant.QQ_API_KEY_TYPE, "1");
        params.put(QQApiConstant.QQ_API_KEY_JSON, "1");
        params.put(QQApiConstant.QQ_API_KEY_UTF_8, "1");
        params.put(QQApiConstant.QQ_API_KEY_ONLY_SONG, "0");
        params.put(QQApiConstant.QQ_API_KEY_DISSTID, discId);

        DiscDetailApiDTO discDetail = discApiService.getDiscDetail(params);
        if (discDetail == null) {
            errMessage = "获取歌单详情失败, 数据对象为空";
            logger.warn(errMessage);
            throw new MusicPlayerException(ErrConstant.NO_DATA_FOUND, errMessage);
        }

        DiscDetailVO discDetailVO = new DiscDetailVO();
        discDetailVO.setDiscId(discDetail.getDisstid());
        discDetailVO.setDiscKeyId(discDetail.getDissid());
        discDetailVO.setDescription(discDetail.getDesc());
        discDetailVO.setDiscName(discDetail.getDissname());
        discDetailVO.setLogo(discDetail.getLogo());
        discDetailVO.setNickname(discDetail.getNickname());
        discDetailVO.setSongNumber(discDetail.getSongnum());
        discDetailVO.setVisitNumber(discDetail.getVisitnum());
        discDetailVO.setTags(discDetail.getTags());

        return null;
    }

}
