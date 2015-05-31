package cn.edu.sjtu.dclab.freewifi.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import cn.edu.sjtu.dclab.freewifi.dao.IBaseDao;
import cn.edu.sjtu.dclab.freewifi.dao.IUserDao;
import cn.edu.sjtu.dclab.freewifi.domain.User;
@Repository("userDao")
public class UserDaoImpl implements IUserDao {
	@Resource(name = "baseDao")
	private IBaseDao<User> baseDao;
	@Override
	public boolean addUser(User user) {
		baseDao.save(user);
		return true;
	}
	@Override
	public User getUserById(long id) {
		return baseDao.queryById(User.class, id);
	}
	@Override
	public User getUserByDeviceId(String deviceId) {
		List<User> users = baseDao.queryByProperty(User.class, "deviceId", deviceId);
		if (users != null && users.size() > 0) {
			return users.get(0);
		}else {
			return null;
		}
	}


}
