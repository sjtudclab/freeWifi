package cn.edu.sjtu.dclab.freewifi.domain;

import java.io.Serializable;
import java.util.Date;

public class AdCollect  implements Serializable {
/**
	 * 
	 */
	private static final long serialVersionUID = 1669130286805845417L;
private long id;
private Ad ad;
private User user;
private Date addDate;
public long getId() {
	return id;
}
public void setId(long id) {
	this.id = id;
}
public Ad getAd() {
	return ad;
}
public void setAd(Ad ad) {
	this.ad = ad;
}
public User getUser() {
	return user;
}
public void setUser(User user) {
	this.user = user;
}
public Date getAddDate() {
	return addDate;
}
public void setAddDate(Date addDate) {
	this.addDate = addDate;
}
public AdCollect(Ad ad, User user, Date addDate) {
	super();
	this.ad = ad;
	this.user = user;
	this.addDate = addDate;
}

}
