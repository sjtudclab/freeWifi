package cn.edu.sjtu.dclab.freewifi.dao;

import cn.edu.sjtu.dclab.freewifi.domain.User;

public interface IUserDao {
	public boolean addUser(User user);
	public User getUserById(long id);
	public User getUserByDeviceId(String deviceId);
}
