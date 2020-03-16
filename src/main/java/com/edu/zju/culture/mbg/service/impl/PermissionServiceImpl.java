package com.edu.zju.culture.mbg.service.impl;

import com.edu.zju.culture.mbg.entity.Permission;
import com.edu.zju.culture.mbg.mapper.PermissionMapper;
import com.edu.zju.culture.mbg.service.IPermissionService;
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
 * @since 2020-03-15
 */
@Service
@Transactional
public class PermissionServiceImpl extends ServiceImpl<PermissionMapper, Permission> implements IPermissionService {

    @Override
    public boolean removeById(Serializable id) {
        PermissionMapper permissionMapper = this.getBaseMapper();
        //根据权限或菜单ID删除权限表各和角色的关系表里面的数据
        permissionMapper.deleteRolePermissionByPid(id);
        return super.removeById(id);//删除 权限表的数据
    }

}
