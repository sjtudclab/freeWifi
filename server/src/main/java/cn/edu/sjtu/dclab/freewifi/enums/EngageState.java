package cn.edu.sjtu.dclab.freewifi.enums;

public enum EngageState {
	SINGLE("未婚",0),MARRIAGE("已婚",1);
	private String name;
    private int value;
	private EngageState(String name, int value) {
		this.name = name;
		this.value = value;
	}
	public String getName() {
		return name;
	}
	public int getValue() {
		return value;
	}
	
	public static EngageState get(int value) {
		switch (value) {
		case 0:
			return SINGLE;
		case 1:
			return MARRIAGE;
		}
		return SINGLE;
	}
}
