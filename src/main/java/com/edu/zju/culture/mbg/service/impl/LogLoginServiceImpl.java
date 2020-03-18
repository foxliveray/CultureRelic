package com.edu.zju.culture.mbg.service.impl;

import com.edu.zju.culture.mbg.entity.LogLogin;
import com.edu.zju.culture.mbg.mapper.LogLoginMapper;
import com.edu.zju.culture.mbg.service.ILogLoginService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
public class LogLoginServiceImpl extends ServiceImpl<LogLoginMapper, LogLogin> implements ILogLoginService {

}
