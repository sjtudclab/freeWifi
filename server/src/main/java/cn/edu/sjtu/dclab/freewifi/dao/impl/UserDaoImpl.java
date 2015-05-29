package cn.edu.sjtu.dclab.freewifi.dao.impl;

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

}
