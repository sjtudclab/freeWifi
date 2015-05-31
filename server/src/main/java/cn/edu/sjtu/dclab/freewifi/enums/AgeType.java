package cn.edu.sjtu.dclab.freewifi.enums;

import java.util.Calendar;
import java.util.Date;

public enum AgeType {
	// 18岁以下，18-35，35-50，50以上
	TEENAGER("18岁以下",0),YOUNTH("18岁-35岁",1),MIDDLE_AGE("35岁-50岁",2),ELDER("50岁以上",3);
	private String name;
    private int value;
	private AgeType(String name, int value) {
		this.name = name;
		this.value = value;
	}
	public String getName() {
		return name;
	}
	public int getValue() {
		return value;
	}
	
	public static AgeType getByAge(int age){
		if (age>0 && age  < 18) {
			return TEENAGER;
		}else if (age>= 18 && age<35) {
			return YOUNTH;
		}else if (age<50) {
			return MIDDLE_AGE;
		}else if(age >50){
			return ELDER;
		}else{
			return YOUNTH;
		}
	}
	public static AgeType getByBirthDate(Date date){
		Calendar birthCalendar = Calendar.getInstance();
		birthCalendar.setTime(date);
		int age = Calendar.getInstance().get(Calendar.YEAR) - birthCalendar.get(Calendar.YEAR);	
		return getByAge(age);
	}

}
