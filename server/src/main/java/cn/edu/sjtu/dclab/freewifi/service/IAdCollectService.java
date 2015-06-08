package cn.edu.sjtu.dclab.freewifi.service;

import java.util.List;

import cn.edu.sjtu.dclab.freewifi.domain.Ad;
import cn.edu.sjtu.dclab.freewifi.domain.User;

public interface IAdCollectService {
public boolean addAdCollect(User user, Ad ad);
public List<Ad> getAdsByUser(User user);
}
