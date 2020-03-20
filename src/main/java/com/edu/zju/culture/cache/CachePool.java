package com.edu.zju.culture.cache;

import com.edu.zju.culture.common.SpringUtil;
import com.edu.zju.culture.mbg.entity.User;
import com.edu.zju.culture.mbg.mapper.UserMapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author y4oung
 * @date 2020/3/20 3:41 PM
 * @description 缓存
 */
public class CachePool {
    /**
     * 所有的缓存数据放到这个CACHE_CONTAINER类似于redis
     */
    public static volatile Map<String, Object> CACHE_CONTAINER = new HashMap<>();

    /**
     * 根据KEY删除缓存
     * @param key
     */
    public static void removeCacheByKey(String key) {
        if(CACHE_CONTAINER.containsKey(key)) {
            CACHE_CONTAINER.remove(key);
        }
    }

    /**
     * 清空所有缓存
     * @param
     */
    public static void removeAll() {
        CACHE_CONTAINER.clear();
    }

    /**
     * 同步缓存
     */
    public static void syncData() {
        //同步用户数据
        UserMapper userMapper = SpringUtil.getBean(UserMapper.class);
        List<User> userList = userMapper.selectList(null);
        for (User user : userList) {
            CACHE_CONTAINER.put("user:"+user.getId(), user);
        }
    }

}
