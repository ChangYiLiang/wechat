package com.chyl.wx.test.config;

import com.google.common.cache.CacheBuilder;
import lombok.Getter;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.guava.GuavaCache;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

/**
 * @Author: chyl
 * @Date: 2019/6/10 18:00
 */
@Configuration
@EnableCaching
public class GuavaCacheConfig {

    private static final int DEFAULT_MAXSIZE = 1000;

    private static final int DEFAULT_TTL = 3600;

    /**
     * 缓存配置
     */
    @Bean
    public CacheManager cacheManager() {
        SimpleCacheManager manager = new SimpleCacheManager();
        //把各个cache注册到cacheManager中
        ArrayList<GuavaCache> caches = new ArrayList<>();
        for (Caches c : Caches.values()) {
            caches.add(new GuavaCache(c.name(), CacheBuilder.newBuilder()
                    .recordStats()
                    .expireAfterWrite(c.getTtl(), TimeUnit.SECONDS).maximumSize(c.getMaxSize())
                    .build()));
        }
        manager.setCaches(caches);
        return manager;
    }

    /**
     * 定义cache名称、超时时长秒、最大个数
     * 每个cache缺省3600秒过期，最大个数1000
     */
    @Getter
    public enum Caches {
        /***微信accessToken*/
        wx_access_token(300,10);

        private int ttl = DEFAULT_TTL;        //过期时间（秒）
        private int maxSize = DEFAULT_MAXSIZE;    //最大數量

        Caches() {
        }

        Caches(int ttl) {
            this.ttl = ttl;
        }

        Caches(int ttl, int maxSize) {
            this.ttl = ttl;
            this.maxSize = maxSize;
        }
    }
}
