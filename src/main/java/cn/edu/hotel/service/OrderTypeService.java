package cn.edu.hotel.service;

import cn.edu.hotel.entity.OrderType;

import java.util.List;

/**
 * 订单类型接口
 */

public interface OrderTypeService {

    int insertOrderType(OrderType orderType);

    int deleteOrderType(int typeId);

    int updateOrderType(OrderType orderType);

    OrderType selectById(int typeId);

    List<OrderType> selectAll();
}
