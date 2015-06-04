package cn.edu.sjtu.dclab.freewifi.domain;

import java.io.Serializable;
import java.util.Date;

public class Gift  implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 698508053879647501L;
	private long id;
	private String name;
	private Date startDate;
	private Date endDate;
	private String description;
	private int cost;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getCost() {
		return cost;
	}
	public void setCost(int cost) {
		this.cost = cost;
	}
	

}
