package com.cunjunwang.music.player.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.cunjunwang.music.player.constant.Constant;
import com.github.pagehelper.PageHelper;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

/**
 * Created by CunjunWang on 2019-06-29.
 */
@Configuration
@MapperScan(basePackages = {"com.cunjunwang.music.player.dao"}, sqlSessionFactoryRef = "musicPlayerSqlSessionFactory")
public class DatabaseConfiguration {

    private static final Logger logger = LoggerFactory.getLogger(Constant.LOGGER);

    @Value("${spring.datasource.music.player.druid.filters:stat}")
    private String filter;

    @Value("${spring.datasource.music.player.druid.maxActive:20}")
    private int maxActive;

    @Value("${spring.datasource.music.player.druid.minIdle:5}")
    private int minIdle;

    @Value("${spring.datasource.music.player.druid.initialSize:5}")
    private int initialSize;

    @Value("${spring.datasource.music.player.druid.maxWait:90000}")
    private int maxWait;

    @Value("${spring.datasource.music.player.druid.timeBetweenEvictionRunsMillis:60000}")
    private int timeBetweenEvictionRunsMillis;

    @Value("${spring.datasource.music.player.druid.minEvictableIdleTimeMillis:3000000}")
    private int minEvictableIdleTimeMillis;

    @Value("${spring.datasource.music.player.druid.poolPreparedStatements:false}")
    private boolean poolPreparedStatements;

    @Primary
    @Bean(name = "musicPlayerDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.music.player")
    public DataSource musicPlayerDataSource() {
        // 指定使用DruidDataSource
        DruidDataSource dataSource = DataSourceBuilder.create().type(com.alibaba.druid.pool.DruidDataSource.class).build();
        try {
            dataSource.setInitialSize(initialSize);
            dataSource.setMinIdle(minIdle);
            dataSource.setMaxActive(maxActive);
            dataSource.setMaxWait(maxWait);
            dataSource.setTimeBetweenEvictionRunsMillis(timeBetweenEvictionRunsMillis);
            dataSource.setMinEvictableIdleTimeMillis(minEvictableIdleTimeMillis);
            dataSource.setPoolPreparedStatements(poolPreparedStatements);
            dataSource.setFilters(filter);
            logger.info("music player dataSource druid监控配置成功");
        } catch (Exception e) {
            logger.warn("druid监控配置失败");
        }
        return dataSource;
    }

    @Primary
    @Bean(name = "musicPlayerSqlSessionFactory")
    public SqlSessionFactory musicPlayerSqlSessionFactory(@Qualifier("musicPlayerDataSource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        Interceptor[] plugins = new Interceptor[]{new PageHelper()};
        bean.setPlugins(plugins);
        bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mybatis/mappers/*.xml"));
        return bean.getObject();
    }

    @Primary
    @Bean(name = "musicPlayerTransactionManager")
    public PlatformTransactionManager musicPlayerTransactionManager(@Qualifier("musicPlayerDataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Primary
    @Bean(name = "musicPlayerSqlSessionTemplate")
    public SqlSessionTemplate musicPlayerSqlSessionTemplate(
            @Qualifier("musicPlayerSqlSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory);
    }

}
