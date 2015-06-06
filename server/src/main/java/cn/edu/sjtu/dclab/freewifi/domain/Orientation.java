package cn.edu.sjtu.dclab.freewifi.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "orientation")
public class Orientation implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8921290423032449348L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	@Column(nullable = true)
	private String sex;
	// 18岁以下，20-35，35-50，50以上
	@Column(nullable = true)
	private String age;
	// 高中以下、高中、大专、本科、研究生及以上
	@Column(nullable = true)
	private String education;
	// 3000元以下、3000-7000元、7000-10000元、10000元以上
	@Column(nullable = true)
	private String income;
	
	@Column(nullable = true)
	private String engage;
	
	@Column(nullable = true)
	private String job;
	
	@Column(nullable = true)
	private String baby;
	
	
	
	public Orientation() {
		super();
	}
	
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
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
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getEngage() {
		return engage;
	}
	public void setEngage(String engage) {
		this.engage = engage;
	}
	public String getJob() {
		return job;
	}
	public void setJob(String job) {
		this.job = job;
	}
	public String getBaby() {
		return baby;
	}
	public void setBaby(String baby) {
		this.baby = baby;
	}

	public Orientation(String sex, String age, String education, String income,
			String engage, String job, String baby) {
		super();
		this.sex = sex;
		this.age = age;
		this.education = education;
		this.income = income;
		this.engage = engage;
		this.job = job;
		this.baby = baby;
	}
	
	
}
