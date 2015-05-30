package cn.edu.sjtu.dclab.freewifi.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 商家
 */
@Entity
@Table(name = "merchant")
public class Merchant implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1490545911509368537L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	@Column(nullable = true)
	private String loginname;
	@Column(nullable = true)
	private String password;
	@Column(nullable = true)
	private String name;
	@Column(nullable = true)
	private String address;
	@Column(nullable = true)
	private String tel;



	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getLoginname() {
		return loginname;
	}

	public void setLoginname(String loginname) {
		this.loginname = loginname;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public Merchant() {
		super();
	}

	public Merchant(String loginname, String password, String name,
			String address, String tel) {
		super();
		this.loginname = loginname;
		this.password = password;
		this.name = name;
		this.address = address;
		this.tel = tel;
	}
	
}
