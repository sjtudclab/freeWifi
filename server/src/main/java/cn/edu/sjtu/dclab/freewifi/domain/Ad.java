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

import cn.edu.sjtu.dclab.freewifi.constants.AdState;
import cn.edu.sjtu.dclab.freewifi.constants.AdType;

@Entity
@Table(name = "ad")
public class Ad implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 440788003715274206L;
	
	@ManyToOne(cascade = { CascadeType.REFRESH })
	@JoinColumn(name = "merchantid")
	private Merchant merchant;
	
	
	@Temporal(TemporalType.DATE)
	@Column(nullable = true)
	private Date addDate;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@Enumerated(EnumType.ORDINAL)
	@Column(nullable = true)
	private AdType adType;
	
	@Lob
	@Column(nullable = true,columnDefinition="BLOB")
	private String content;
	
	@Temporal(TemporalType.DATE)
	@Column(nullable = true)
	private Date startDate;
	
	@Temporal(TemporalType.DATE)
	@Column(nullable = true)
	private Date endDate;
	
	@Column(nullable = true)
	private int startHour;
	
	@Column(nullable = true)
	private int endHour;
	
	@Enumerated(EnumType.ORDINAL)
	@Column(nullable = true)
	private AdState state;
}
