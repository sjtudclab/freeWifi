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
@Table(name = "wifi")
public class WIFI implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@Column(nullable = true)
	private String ssid;

	@Column(nullable = true)
	private String password;

	@ManyToOne(cascade = { CascadeType.REFRESH })
	@JoinColumn(name = "merchant_id")
	private Merchant merchant;

	// 经度
	@Column(nullable = true)
	private double longitude;
	// 纬度
	@Column(nullable = true)
	private double latitude;

	@JsonIgnore
	public Merchant getMerchant() {
		return merchant;
	}

	public void setMerchant(Merchant merchant) {
		this.merchant = merchant;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getSsid() {
		return ssid;
	}

	public void setSsid(String ssid) {
		this.ssid = ssid;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public WIFI(String ssid, String password, Merchant merchant,
			double longitude, double latitude) {
		super();
		this.ssid = ssid;
		this.password = password;
		this.merchant = merchant;
		this.longitude = longitude;
		this.latitude = latitude;
	}

	public WIFI() {
		super();
	}

}
