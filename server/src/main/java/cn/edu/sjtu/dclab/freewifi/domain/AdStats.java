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
import cn.edu.sjtu.dclab.freewifi.enums.BabyState;
import cn.edu.sjtu.dclab.freewifi.enums.BusinessType;
import cn.edu.sjtu.dclab.freewifi.enums.Education;
import cn.edu.sjtu.dclab.freewifi.enums.EngageState;
import cn.edu.sjtu.dclab.freewifi.enums.Gender;
import cn.edu.sjtu.dclab.freewifi.enums.IncomeType;
import cn.edu.sjtu.dclab.freewifi.enums.Job;

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
	
	@Enumerated(EnumType.ORDINAL)
	@Column(nullable = true)
	private BusinessType business;
	
	@Enumerated(EnumType.ORDINAL)
	@Column(nullable = true)
	private EngageState engage;
	
	@Enumerated(EnumType.ORDINAL)
	@Column(nullable = true)
	private Job job;
	
	@Enumerated(EnumType.ORDINAL)
	@Column(nullable = true)
	private BabyState baby;
	
	
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
			AgeType age, BusinessType business, EngageState engage, Job job,
			BabyState baby) {
		super();
		this.ad = ad;
		this.sex = sex;
		this.income = income;
		this.education = education;
		this.age = age;
		this.business = business;
		this.engage = engage;
		this.job = job;
		this.baby = baby;
		this.impression = 0;
		this.click = 0;
	}

	public BusinessType getBusiness() {
		return business;
	}

	public void setBusiness(BusinessType business) {
		this.business = business;
	}

	public EngageState getEngage() {
		return engage;
	}

	public void setEngage(EngageState engage) {
		this.engage = engage;
	}

	public Job getJob() {
		return job;
	}

	public void setJob(Job job) {
		this.job = job;
	}

	public BabyState getBaby() {
		return baby;
	}

	public void setBaby(BabyState baby) {
		this.baby = baby;
	}
	
}
