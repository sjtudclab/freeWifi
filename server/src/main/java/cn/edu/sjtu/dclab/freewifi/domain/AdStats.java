package cn.edu.sjtu.dclab.freewifi.domain;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnore;

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
	
	@Column(nullable = true)
	private long push;
	
	@Column(nullable = true)
	private String orientation;
	
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	@JsonIgnore
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

	public long getPush() {
		return push;
	}

	public void setPush(long push) {
		this.push = push;
	}

	public String getOrientation() {
		return orientation;
	}

	public void setOrientation(String orientation) {
		this.orientation = orientation;
	}

	public AdStats(Ad ad, String orientation) {
		super();
		this.ad = ad;
		this.orientation = orientation;
		this.push = 0;
		this.impression = 0;
		this.click  = 0;
	}

	public AdStats() {
		super();
	}
	
}
