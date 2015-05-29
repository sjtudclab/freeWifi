package cn.edu.sjtu.dclab.freewifi.constants;

public enum AdType {
	DIANPING("大众点评",0),SELF("自制",1);
	private String name;
    private int value;
	private AdType(String name, int value) {
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
