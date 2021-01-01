package cn.edu.hotel.service;

import cn.edu.hotel.entity.Hotel;

import java.util.List;

/**
 * @author cyh
 * 酒店信息接口
 */
public interface HotelService {

    int insert(Hotel hotel);
    int delete(int hotelId);
    int update(Hotel hotel);
    Hotel selectByName(String hotelName);
    Hotel selectById(int hotelId);
    List<Hotel> selectAll();

}
