package com.edu.zju.culture.mbg.service.impl;

import com.edu.zju.culture.mbg.entity.User;
import com.edu.zju.culture.mbg.mapper.UserMapper;
import com.edu.zju.culture.mbg.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

}
