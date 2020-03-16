package com.edu.zju.culture.mbg.service.impl;

import com.edu.zju.culture.mbg.entity.Order;
import com.edu.zju.culture.mbg.mapper.OrderMapper;
import com.edu.zju.culture.mbg.service.IOrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author y4oung
 * @since 2020-03-16
 */
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements IOrderService {

}
