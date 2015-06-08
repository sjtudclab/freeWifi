package cn.edu.sjtu.dclab.freewifi.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * 广告收藏夹
 * 
 * @author sunke03
 *
 */
@Entity
@Table(name = "ad_collect")
public class AdCollect implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1669130286805845417L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	@ManyToOne(cascade = { CascadeType.REFRESH })
	@JoinColumn(name = "ad_id")
	private Ad ad;
	@ManyToOne(cascade = { CascadeType.REFRESH })
	@JoinColumn(name = "user_id")
	private User user;
	@Temporal(TemporalType.DATE)
	@Column(nullable = true, name = "add_date")
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

	public AdCollect() {
		super();
	}
}
