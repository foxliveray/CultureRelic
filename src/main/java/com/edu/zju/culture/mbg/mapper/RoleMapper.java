package com.edu.zju.culture.mbg.mapper;

import com.edu.zju.culture.mbg.entity.Role;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.io.Serializable;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author y4oung
 * @since 2020-03-16
 */
public interface RoleMapper extends BaseMapper<Role> {
    /**
     * 根据角色ID删除role_permission
     * @param id
     */
    void deleteRolePermissionByRid(Serializable id);
    /**
     * 根据角色ID删除role_user
     * @param id
     */
    void deleteRoleUserByRid(Serializable id);
}
