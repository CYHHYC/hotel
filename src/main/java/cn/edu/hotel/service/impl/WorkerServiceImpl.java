package cn.edu.hotel.service.impl;

import cn.edu.hotel.entity.Worker;
import cn.edu.hotel.mapper.WorkerMapper;
import cn.edu.hotel.service.WorkerService;
import cn.edu.hotel.utils.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WorkerServiceImpl implements WorkerService {

    @Autowired
    private WorkerMapper workerMapper;

    @Override
    public int insert(Worker worker) {
        worker.setPassword(MD5Utils.MD5Encode(worker.getPassword()));
        return workerMapper.insertSelective(worker);
    }

    @Override
    public int delete(int workerId) {
        return workerMapper.deleteByPrimaryKey(workerId);
    }

    @Override
    public int updateById(Worker worker) {
        return workerMapper.updateByPrimaryKeySelective(worker);
    }

    @Override
    public Worker selectById(int workerId) {
        return workerMapper.selectByPrimaryKey(workerId);
    }

    @Override
    public Worker selectByUsername(String username) {
        return workerMapper.selectByUsername(username);
    }

    @Override
    public List<Worker> findAll() {
        return workerMapper.selectAll();
    }

    @Override
    public List<Worker> selectByRole(String role) {
        return workerMapper.selectByRole(role);
    }

    @Override
    public Worker login(String username, String password,String role) {
        //String password = MD5Utils.MD5Encode(password);
        return workerMapper.selectByUsernameAndPassword(username,password,role);
    }

    @Override
    public Worker login(String username, String password) {
       // String password = MD5Utils.MD5Encode(password);
        return workerMapper.selectByUsernamePassword(username,password);
    }
}
