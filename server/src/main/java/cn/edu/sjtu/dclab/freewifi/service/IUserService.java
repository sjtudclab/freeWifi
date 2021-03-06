package cn.edu.sjtu.dclab.freewifi.service;

import cn.edu.sjtu.dclab.freewifi.domain.User;

public interface IUserService {
public boolean addUser(User user);
public User getUserById(long id);
public User getUserByDeviceId(String deviceId);
public User getUserByTel(String tel);
public boolean updateUser(User user);
}