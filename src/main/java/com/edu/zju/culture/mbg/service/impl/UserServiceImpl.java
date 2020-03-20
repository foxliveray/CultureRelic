package com.edu.zju.culture.mbg.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.edu.zju.culture.mbg.entity.User;
import com.edu.zju.culture.mbg.mapper.RoleMapper;
import com.edu.zju.culture.mbg.mapper.UserMapper;
import com.edu.zju.culture.mbg.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;

/**
 * <p>
 * 后台用户表 服务实现类
 * </p>
 *
 * @author y4oung
 * @since 2020-03-13
 */
@Service
@Transactional
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {
    @Autowired
    private RoleMapper roleMapper;

    /**
     * 通过uid查询角色名
     * @param uid
     * @return
     */
    @Override
    public String getRoleByUid(Long uid) {
        return this.getBaseMapper().getRoleByUid(uid);
    }

    /**
     * 保存用户和角色之间的关系
     * @param uid
     * @param ids
     */
    @Override
    public void saveUserRole(Integer uid, Integer[] ids) {
        //根据用户ID删除role_user里面的数据
        this.roleMapper.deleteRoleUserByUid(uid);
        if(null!=ids&&ids.length>0) {
            for (Integer rid : ids) {
                this.roleMapper.insertUserRole(uid,rid);
            }
        }
    }

    @Override
    public boolean save(User entity) {
        return super.save(entity);
    }

    @Override
    public User getById(Serializable id) {
        return super.getById(id);
    }

    @Override
    public boolean removeById(Serializable id) {
        //根据用户ID删除用户角色中间表的数据
        roleMapper.deleteRoleUserByUid(id);
        return super.removeById(id);
    }

    @Override
    public User getOne(Wrapper<User> queryWrapper) {
        return super.getOne(queryWrapper);
    }
}

