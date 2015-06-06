package cn.edu.sjtu.dclab.freewifi.enums;

public enum BabyState {
	NO("无",0),PREGNANCY("孕期",1),ZERO_THREE("0-3岁",2),THREE_SIX("3-6岁",3);
	private String name;
    private int value;
	private BabyState(String name, int value) {
		this.name = name;
		this.value = value;
	}
	public String getName() {
		return name;
	}
	public int getValue() {
		return value;
	}
	
	public static BabyState get(int value) {
		switch (value) {
		case 0:
			return NO;
		case 1:
			return PREGNANCY;
		case 2:
			return ZERO_THREE;
		case 3:
			return THREE_SIX;
		}
		return NO;
	}
}
