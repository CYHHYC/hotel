package cn.edu.hotel.service.impl;

import cn.edu.hotel.service.OrderService;
import cn.edu.hotel.entity.Order;
import cn.edu.hotel.enums.OrderStatus;
import cn.edu.hotel.mapper.OrderMapper;
import cn.edu.hotel.service.RoomService;
import cn.edu.hotel.service.RoomTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private RoomTypeService roomTypeService;
    @Autowired
    private RoomService roomService;

    @Override
    public int insert(Order order) {
        return orderMapper.insert(order);
    }

    @Override
    public int addOrder(Order order) {
        return orderMapper.insertSelective(order);
    }

    @Override
    public int delete(Integer orderId) {
        return orderMapper.deleteByPrimaryKey(orderId);
    }

    @Override
    public Order selectById(Integer orderId) {
        return orderMapper.selectByPrimaryKey(orderId);
    }

    @Override
    public Order selectByNameAndPhone(String name, String phone) {
        Order order = new Order();
        order.setName(name);
        order.setPhone(phone);
        order.setOrderStatus(OrderStatus.PAID.getCode());
        return orderMapper.selectByNameAndPhone(order);
    }

    @Override
    public int update(Order order) {
        return orderMapper.updateByPrimaryKeySelective(order);
    }

    /**
     * 订单支付
     *  1.更改订单状态 -3
     *  2.修改房型余量 -2
     *  3.修改房间状态 0
     * @param orderId
     * @return
     */
    @Override
    @Transactional
    public int payOrder(int orderId) {
        Order order = orderMapper.selectByPrimaryKey(orderId);
        //订单存在
        if (order == null | order.getOrderStatus() != OrderStatus.UNPAID.getCode()) {
            return -3;
        }
        //更新剩余房间数量成功
        if (roomTypeService.updateRest(order.getRoomTypeId(),-1) != 1){
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return -2;
        }
        //更新订单状态为已支付
        order.setOrderStatus(OrderStatus.PAID.getCode());
        //更新数据库信息。
        if (orderMapper.updateByPrimaryKeySelective(order) != 1){
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return 0;
        }
        return 1;
    }

    /**
     * 取消订单
     * 1. 更改订单状态 -3
     * 2. 修改房型余量（已付款）-2
     * @param orderId
     * @return
     */
    @Override
    public int cancelOrder(int orderId) {
        Order order = orderMapper.selectByPrimaryKey(orderId);
        if (order == null ) return -3;
        //改为取消状态
        order.setOrderStatus(OrderStatus.WAS_CANCELED.getCode());
        // if (roomTypeService.updateRest(order.getRoomTypeId(),1) != 1){
        //     return -2;
        // }2
        return orderMapper.updateByPrimaryKeySelective(order);
    }

    @Override
    public Integer getOrderCount() {
        return orderMapper.getOrderCount();
    }

    @Override
    public List<Order> selectByUserId(int userId) {
        return orderMapper.selectByUserId(userId);
    }

    @Override
    public List<Order> AllOrders() {
        return orderMapper.selectAll();
    }

    @Override
    public List<Order> UsersAllOrders(int userId) {
        return orderMapper.selectAllByUser(userId, OrderStatus.WAS_DELETED.getCode());
    }


}
