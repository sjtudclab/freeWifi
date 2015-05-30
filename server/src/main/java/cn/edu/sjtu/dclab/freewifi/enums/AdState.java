package cn.edu.sjtu.dclab.freewifi.enums;

public enum AdState {
	AUDIT("待审核",0),READY("就绪",1),LAUNCHING("投放",2),DELETE("删除",3);
	private String name;
    private int value;
	private AdState(String name, int value) {
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
