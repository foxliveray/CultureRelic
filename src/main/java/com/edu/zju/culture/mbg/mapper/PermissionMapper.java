package com.edu.zju.culture.mbg.mapper;

import com.edu.zju.culture.mbg.entity.Permission;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.io.Serializable;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author y4oung
 * @since 2020-03-15
 */
public interface PermissionMapper extends BaseMapper<Permission> {

    /**
     * /根据权限或菜单ID删除权限表各和角色的关系表里面的数据
     * @param id
     */
    void deleteRolePermissionByPid(@Param("id")Serializable id);
}
