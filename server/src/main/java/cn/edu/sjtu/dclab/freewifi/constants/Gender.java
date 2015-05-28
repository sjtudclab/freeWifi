package cn.edu.sjtu.dclab.freewifi.constants;

public enum Gender {
	MALE("男",0),FEMALE("女",1),ALL("不限",2);
	private String name;
    private int value;
	private Gender(String name, int value) {
		this.name = name;
		this.value = value;
	}
	public String getName() {
		return name;
	}
	public int getValue() {
		return value;
	}
}
