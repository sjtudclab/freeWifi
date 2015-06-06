package cn.edu.sjtu.dclab.freewifi.dao;

import cn.edu.sjtu.dclab.freewifi.domain.Ad;
import cn.edu.sjtu.dclab.freewifi.domain.AdStats;
import cn.edu.sjtu.dclab.freewifi.enums.AgeType;
import cn.edu.sjtu.dclab.freewifi.enums.BabyState;
import cn.edu.sjtu.dclab.freewifi.enums.BusinessType;
import cn.edu.sjtu.dclab.freewifi.enums.Education;
import cn.edu.sjtu.dclab.freewifi.enums.EngageState;
import cn.edu.sjtu.dclab.freewifi.enums.Gender;
import cn.edu.sjtu.dclab.freewifi.enums.IncomeType;
import cn.edu.sjtu.dclab.freewifi.enums.Job;

public interface IAdStatsDao {
	public boolean addAdStats(AdStats adStats);

	public AdStats getAdStats(Gender gender, Education education,
			IncomeType incomeType, AgeType age, BusinessType business,
			EngageState engage,Job job,BabyState baby,Ad ad);

	public boolean updateAdStats(AdStats adStats);
}
