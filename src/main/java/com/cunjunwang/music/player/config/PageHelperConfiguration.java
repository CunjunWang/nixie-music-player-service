package com.cunjunwang.music.player.config;

import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.Properties;

/**
 * Created by CunjunWang on 2019-06-29.
 */

@Component
@Configuration
public class PageHelperConfiguration {

    @Value("${pageHelper.offsetAsPageNum}")
    private String offsetAsPageNum;

    @Value("${pageHelper.rowBoundsWithCount}")
    private String rowBoundsWithCount;

    @Value("${pageHelper.reasonable}")
    private String reasonable;

    @Value("${pageHelper.dialect}")
    private String dialect;

    @Value("${pageHelper.dialectDB}")
    private String oracle;

    @Value("${pageHelper.value.true}")
    private String PageHelperTrue;

    @Value("${pageHelper.value.false}")
    private String PageHelperFalse;

    @Bean
    public PageHelper pageHelper() {
        PageHelper pageHelper = new PageHelper();
        Properties p = new Properties();
        // RowBounds参数offset作为PageNum使用 - 默认不使用
        p.setProperty(offsetAsPageNum, PageHelperTrue);
        // RowBounds是否进行count查询 - 默认不查询
        p.setProperty(rowBoundsWithCount, PageHelperTrue);
        // 分页合理化
        p.setProperty(reasonable, PageHelperTrue);
        // 使用数据库
        p.setProperty(dialect, oracle);
        pageHelper.setProperties(p);
        return pageHelper;
    }

}
