package cn.edu.sjtu.dclab.freewifi.enums;

public enum BusinessType {
	//家电行业 IT电子 生活用品 娱乐休闲 礼品饰品 餐饮业 服装鞋帽
	APPLICANCE("家电行业",0),IT_ELEC("IT电子",1),LIFE("生活用品",2),ENTERTAINMENT("娱乐休闲",3),
	GIFT("礼品饰品",4),REPAST("餐饮业",5),DRESS("服装鞋帽",6);
	
	private String name;
    private int value;
	private BusinessType(String name, int value) {
		this.name = name;
		this.value = value;
	}
	public String getName() {
		return name;
	}
	public int getValue() {
		return value;
	}
	
	public static BusinessType get(int value) {
		switch (value) {
		case 0:
			return APPLICANCE;
		case 1:
			return IT_ELEC;
		case 2:
			return LIFE;
		case 3:
			return ENTERTAINMENT;
		case 4:
			return GIFT;
		case 5:
			return REPAST;
		case 6:
			return DRESS;
		}
		return APPLICANCE;
	}
}
