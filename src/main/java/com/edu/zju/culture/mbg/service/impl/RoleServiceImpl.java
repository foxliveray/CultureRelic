package com.edu.zju.culture.mbg.service.impl;

import com.edu.zju.culture.mbg.entity.Role;
import com.edu.zju.culture.mbg.mapper.RoleMapper;
import com.edu.zju.culture.mbg.service.IRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

}
