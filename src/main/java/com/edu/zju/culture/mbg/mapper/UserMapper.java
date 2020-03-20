package com.edu.zju.culture.mbg.mapper;

import com.edu.zju.culture.mbg.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 * 后台用户表 Mapper 接口
 * </p>
 *
 * @author y4oung
 * @since 2020-03-13
 */
public interface UserMapper extends BaseMapper<User> {

    /**
     * 通过uid查询角色名
     * @param uid
     * @return
     */
    String getRoleByUid(Long uid);
}
