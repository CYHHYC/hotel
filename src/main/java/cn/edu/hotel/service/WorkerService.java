package cn.edu.hotel.service;

import cn.edu.hotel.entity.Worker;

import java.util.List;

/**
 *  员工管理接口
 */
public interface WorkerService {
    int insert(Worker worker);
    int delete(int workerId);
    int updateById(Worker worker);
    Worker selectById(int workerId);
    Worker selectByUsername(String username);
    List<Worker> findAll();
    List<Worker> selectByRole(String role);
    Worker login(String username,String password,String role);
    Worker login(String username,String password);
}
