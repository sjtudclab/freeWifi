package cn.edu.sjtu.dclab.freewifi.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.codehaus.jackson.annotate.JsonIgnore;

import cn.edu.sjtu.dclab.freewifi.enums.AdState;
import cn.edu.sjtu.dclab.freewifi.enums.AdType;

@Entity
@Table(name = "ad")
public class Ad implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 440788003715274206L;
	
	@ManyToOne(cascade = { CascadeType.REFRESH })
	@JoinColumn(name = "merchant_id")
	private Merchant merchant;
	
	
	@Temporal(TemporalType.DATE)
	@Column(nullable = true,name="add_date")
	private Date addDate;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@Enumerated(EnumType.ORDINAL)
	@Column(nullable = true,name="ad_type")
	private AdType adType;
	
	@Lob
	@Column(nullable = true,columnDefinition="BLOB")
	private String content;
	
	@Temporal(TemporalType.DATE)
	@Column(nullable = true,name="start_date")
	private Date startDate;
	
	@Temporal(TemporalType.DATE)
	@Column(nullable = true,name="end_date")
	private Date endDate;
	
	@Column(nullable = true,name="start_hour")
	private int startHour;
	
	@Column(nullable = true,name="name")
	private String name;
	
	@Column(nullable = true,name="end_hour")
	private int endHour;
	
	@Enumerated(EnumType.ORDINAL)
	@Column(nullable = true)
	private AdState state;

	@ManyToOne(cascade = { CascadeType.REFRESH })
	@JoinColumn(name = "orientation_id")
	private Orientation orientation;
	
	public Orientation getOrientation() {
		return orientation;
	}

	public void setOrientation(Orientation orientation) {
		this.orientation = orientation;
	}
	
	public Merchant getMerchant() {
		return merchant;
	}

	public void setMerchant(Merchant merchant) {
		this.merchant = merchant;
	}

	@JsonIgnore
	public Date getAddDate() {
		return addDate;
	}

	public void setAddDate(Date addDate) {
		this.addDate = addDate;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public AdType getAdType() {
		return adType;
	}

	public void setAdType(AdType adType) {
		this.adType = adType;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
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

	public int getStartHour() {
		return startHour;
	}

	public void setStartHour(int startHour) {
		this.startHour = startHour;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getEndHour() {
		return endHour;
	}

	public void setEndHour(int endHour) {
		this.endHour = endHour;
	}

	public AdState getState() {
		return state;
	}

	public void setState(AdState state) {
		this.state = state;
	}

	
	public Ad(Merchant merchant, Date addDate, AdType adType, String content,
			Date startDate, Date endDate, int startHour, String name,
			int endHour, AdState state, Orientation orientation) {
		super();
		this.merchant = merchant;
		this.addDate = addDate;
		this.adType = adType;
		this.content = content;
		this.startDate = startDate;
		this.endDate = endDate;
		this.startHour = startHour;
		this.name = name;
		this.endHour = endHour;
		this.state = state;
		this.orientation = orientation;
	}

	public Ad() {
		super();
	}
	
}
