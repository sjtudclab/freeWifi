package cn.edu.sjtu.dclab.freewifi.domain;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "orientation")
public class Orientation implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8921290423032449348L;
	@Column(nullable = true)
	private String sex;
	// 18岁以下，20-35，35-50，50以上
	@Column(nullable = true)
	private String birtdate;
	// 高中以下、高中、大专、本科、研究生及以上
	@Column(nullable = true)
	private String education;
	// 3000元以下、3000-7000元、7000-10000元、10000元以上
	@Column(nullable = true)
	private String income;
	@ManyToOne(cascade = { CascadeType.REFRESH })
	@JoinColumn(name = "ad_id")
	private Ad ad;
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getBirtdate() {
		return birtdate;
	}
	public void setBirtdate(String birtdate) {
		this.birtdate = birtdate;
	}
	public String getEducation() {
		return education;
	}
	public void setEducation(String education) {
		this.education = education;
	}
	public String getIncome() {
		return income;
	}
	public void setIncome(String income) {
		this.income = income;
	}
	public Ad getAd() {
		return ad;
	}
	public void setAd(Ad ad) {
		this.ad = ad;
	}
	

}
