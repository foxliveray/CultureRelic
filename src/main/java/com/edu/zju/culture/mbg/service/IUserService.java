package com.edu.zju.culture.mbg.service;

import com.edu.zju.culture.mbg.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 后台用户表 服务类
 * </p>
 *
 * @author y4oung
 * @since 2020-03-13
 */
public interface IUserService extends IService<User> {

    /**
     * 通过uid查询角色名
     * @param uid
     * @return
     */
    String getRoleByUid(Long uid);

    /**
     * 保存用户和角色之间的关系
     * @param uid
     * @param ids
     */
    void saveUserRole(Integer uid, Integer[] ids);
}
