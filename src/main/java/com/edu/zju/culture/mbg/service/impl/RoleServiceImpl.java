package com.edu.zju.culture.mbg.service.impl;

import com.edu.zju.culture.mbg.entity.Role;
import com.edu.zju.culture.mbg.mapper.RoleMapper;
import com.edu.zju.culture.mbg.service.IRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author y4oung
 * @since 2020-03-16
 */
@Service
@Transactional
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements IRoleService {
    @Override
    public boolean removeById(Serializable id) {
        //根据角色ID删除sys_role_permission
        this.getBaseMapper().deleteRolePermissionByRid(id);
        //根据角色ID删除sys_role_user
        this.getBaseMapper().deleteRoleUserByRid(id);
        return super.removeById(id);
    }
}
