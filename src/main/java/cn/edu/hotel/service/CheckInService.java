package cn.edu.hotel.service;

import cn.edu.hotel.entity.CheckIn;
import cn.edu.hotel.entity.Room;

import java.util.List;

/**
 * @author cyh
 * 入住信息接口
 */
public interface CheckInService {

    int insert(CheckIn checkIn);

    Room checkIn(CheckIn checkIn);

    int delete(int checkInId);

    int update(CheckIn checkIn);

    int checkOut(String roomNumber);

    int updateByRoomNumber(String roomNumber);

    CheckIn selectById(int checkInId);

    List<CheckIn> selectAll();

}
