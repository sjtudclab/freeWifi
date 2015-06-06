package cn.edu.sjtu.dclab.freewifi.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import cn.edu.sjtu.dclab.freewifi.enums.BabyState;
import cn.edu.sjtu.dclab.freewifi.enums.Education;
import cn.edu.sjtu.dclab.freewifi.enums.EngageState;
import cn.edu.sjtu.dclab.freewifi.enums.Gender;
import cn.edu.sjtu.dclab.freewifi.enums.IncomeType;
import cn.edu.sjtu.dclab.freewifi.enums.Job;

@Entity
@Table(name = "user")
public class User implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4958576058673636022L;

	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@Column(nullable = true)
	private String deviceId;
	
	//private String password;
	@Enumerated(EnumType.ORDINAL)
	@Column(nullable = true)
	private Gender sex;
	
	@Enumerated(EnumType.ORDINAL)
	@Column(nullable = true)
	private EngageState engage;
	
	@Enumerated(EnumType.ORDINAL)
	@Column(nullable = true)
	private Job job;
	
	@Enumerated(EnumType.ORDINAL)
	@Column(nullable = true)
	private BabyState baby;
	
	@Column(nullable = false)
	private String password;
	
	public User() {
		super();
	}
	@Column(nullable = true)
	private String tel;
	
	@Temporal(TemporalType.DATE)
	@Column(nullable = true)
	private Date birthdate;
	
	//高中以下、高中、大专、本科、研究生及以上
	@Enumerated(EnumType.ORDINAL)
	@Column(nullable = true)
	private Education education;
	
	//3000元以下、3000-7000元、7000-10000元、10000元以上
	@Enumerated(EnumType.ORDINAL)
	@Column(nullable = true)
	private IncomeType income;
	
	@Column(nullable = true)
	private int score;
	
	public String getDeviceId() {
		return deviceId;
	}
	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public Gender getSex() {
		return sex;
	}
	public void setSex(Gender sex) {
		this.sex = sex;
	}

	public Date getBirthdate() {
		return birthdate;
	}
	public void setBirthdate(Date birthdate) {
		this.birthdate = birthdate;
	}
	public Education getEducation() {
		return education;
	}
	public void setEducation(Education education) {
		this.education = education;
	}
	public IncomeType getIncome() {
		return income;
	}
	public void setIncome(IncomeType income) {
		this.income = income;
	}
	
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
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
	public User(String deviceId, Gender sex, EngageState engage, Job job,
			BabyState baby, String password, String tel, Date birthdate,
			Education education, IncomeType income, int score) {
		super();
		this.deviceId = deviceId;
		this.sex = sex;
		this.engage = engage;
		this.job = job;
		this.baby = baby;
		this.password = password;
		this.tel = tel;
		this.birthdate = birthdate;
		this.education = education;
		this.income = income;
		this.score = score;
	}
}
