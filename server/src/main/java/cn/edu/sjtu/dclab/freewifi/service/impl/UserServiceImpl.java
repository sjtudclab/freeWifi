package cn.edu.sjtu.dclab.freewifi.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.edu.sjtu.dclab.freewifi.dao.IUserDao;
import cn.edu.sjtu.dclab.freewifi.domain.User;
import cn.edu.sjtu.dclab.freewifi.service.IUserService;

@Service("userService")
@Transactional
public class UserServiceImpl implements IUserService {
    @Resource(name = "userDao")
    private IUserDao dao;

    @Override
    public boolean addUser(User user) {
        return dao.addUser(user);
    }

    @Override
    public User getUserById(long id) {
        return dao.getUserById(id);
    }

    @Override
    public User getUserByDeviceId(String deviceId) {
        return dao.getUserByDeviceId(deviceId);
    }
}
