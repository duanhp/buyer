package com.duan.buyer.cache;


import org.ehcache.Cache;
import org.ehcache.CacheManager;
import org.ehcache.config.builders.CacheConfigurationBuilder;
import org.ehcache.config.builders.CacheManagerBuilder;
import org.ehcache.config.builders.ResourcePoolsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class EhcacheMgr {


    @Autowired
    private CacheManager cacheManager;

    private static String CACHE_NAME="buyer";


    @Bean
    public CacheManager cacheManager(){
        CacheManager cacheManagerBean = CacheManagerBuilder.newCacheManagerBuilder()
                .withCache(CACHE_NAME,
                        CacheConfigurationBuilder.newCacheConfigurationBuilder(String.class, String.class,
                                ResourcePoolsBuilder.heap(100))
                                .build())
                .build(true);
        return cacheManagerBean;
    }


    public String get(String key){
        Cache<String, String> cache
                = cacheManager.getCache(CACHE_NAME, String.class, String.class);
        return cache.get(key);
    }

    public void put(String key,String value){
        Cache<String, String> cache
                = cacheManager.getCache(CACHE_NAME, String.class, String.class);
        cache.put(key,value);
    }

}
