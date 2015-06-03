package cn.edu.sjtu.dclab.freewifi.dao;

import java.util.List;

import cn.edu.sjtu.dclab.freewifi.domain.AdCollect;
import cn.edu.sjtu.dclab.freewifi.domain.User;

public interface IAdCollectDao {
public boolean addAdCollect(AdCollect adCollect);
public List<AdCollect> getAdCollectsByUser(User user);
}
