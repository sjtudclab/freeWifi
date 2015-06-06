package cn.edu.sjtu.dclab.freewifi.domain;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import cn.edu.sjtu.dclab.freewifi.enums.AgeType;
import cn.edu.sjtu.dclab.freewifi.enums.Education;
import cn.edu.sjtu.dclab.freewifi.enums.Gender;
import cn.edu.sjtu.dclab.freewifi.enums.IncomeType;

@Entity
@Table(name = "ad_stats")
public class AdStats implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1884434710694133972L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@ManyToOne(cascade = { CascadeType.REFRESH })
	@JoinColumn(name = "ad_id")
	private Ad ad;
	
	@Column(nullable = true)
	private long impression;
	
	@Column(nullable = true)
	private long click;
	
	@Enumerated(EnumType.ORDINAL)
	@Column(nullable = true)
	private Gender sex;
	
	@Enumerated(EnumType.ORDINAL)
	@Column(nullable = true)
	private IncomeType income;
	
	@Enumerated(EnumType.ORDINAL)
	@Column(nullable = true)
	private Education education;
	
	@Enumerated(EnumType.ORDINAL)
	@Column(nullable = true)
	private AgeType age;
	
	
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

	public long getImpression() {
		return impression;
	}

	public void setImpression(long impression) {
		this.impression = impression;
	}

	public long getClick() {
		return click;
	}

	public void setClick(long click) {
		this.click = click;
	}

	public Gender getSex() {
		return sex;
	}

	public void setSex(Gender sex) {
		this.sex = sex;
	}

	public IncomeType getIncome() {
		return income;
	}

	public void setIncome(IncomeType income) {
		this.income = income;
	}

	public Education getEducation() {
		return education;
	}

	public void setEducation(Education education) {
		this.education = education;
	}

	public AgeType getAge() {
		return age;
	}

	public void setAge(AgeType age) {
		this.age = age;
	}

	public AdStats(Ad ad, Gender sex, IncomeType income, Education education,
			AgeType age) {
		super();
		this.ad = ad;
		this.sex = sex;
		this.income = income;
		this.education = education;
		this.age = age;
		this.impression = 0;
		this.click = 0;
	}
	
	
}
