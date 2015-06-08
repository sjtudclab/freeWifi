package cn.edu.sjtu.dclab.freewifi.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "merchant_collect")
public class MerchantCollect implements Serializable {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1669130286805845417L;
		
		@Id
		@GeneratedValue(strategy = GenerationType.AUTO)
		private long id;
		
		@ManyToOne(cascade = { CascadeType.REFRESH })
		@JoinColumn(name = "merchant_id")
		private Merchant merchant;
		
		@ManyToOne(cascade = { CascadeType.REFRESH })
		@JoinColumn(name = "user_id")
		private User user;
		
		@Temporal(TemporalType.DATE)
		@Column(nullable = true, name = "add_date")
		private Date addDate;

		public Merchant getMerchant() {
			return merchant;
		}

		public void setMerchant(Merchant merchant) {
			this.merchant = merchant;
		}

		public User getUser() {
			return user;
		}

		public void setUser(User user) {
			this.user = user;
		}

		public Date getAddDate() {
			return addDate;
		}

		public void setAddDate(Date addDate) {
			this.addDate = addDate;
		}

		public MerchantCollect() {
			super();
		}

		public MerchantCollect(Merchant merchant, User user, Date addDate) {
			super();
			this.merchant = merchant;
			this.user = user;
			this.addDate = addDate;
		}
		
}
