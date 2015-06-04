package cn.edu.sjtu.dclab.freewifi.domain;

import java.io.Serializable;
import java.util.Date;

public class Deal implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1080021742034422021L;
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Gift getGift() {
		return gift;
	}

	public void setGift(Gift gift) {
		this.gift = gift;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	private long id;
	private User user;
	private Gift gift;
	private Date date;

}
