package cn.edu.sjtu.dclab.freewifi.enums;


public enum IncomeType {
	THREE_THOUSAND("3000元以下",0),SEVEN_THOUSAND("3000-7000元",1),TEN_THOUSAND("7000-10000元",2),
	ABOVE_TEN_THOUSAND("10000元以上",3),ALL("不限",4);
	private String name;
    private int value;
	private IncomeType(String name, int value) {
		this.name = name;
		this.value = value;
	}
	public String getName() {
		return name;
	}
	public int getValue() {
		return value;
	}
	public static IncomeType get(int value) {
		switch (value) {
		case 0:
			return THREE_THOUSAND;
		case 1:
			return SEVEN_THOUSAND;
		case 2:
			return TEN_THOUSAND;
		case 3:
			return ABOVE_TEN_THOUSAND;
		case 4:
			return ALL;
		}
		return ALL;
	}
	
	public static String[] getTypes(){
		IncomeType[] incomeTypes = IncomeType.values();
		String[] types = new String[incomeTypes.length];
		for (int i = 0; i < types.length; i++) {
			types[i] = incomeTypes[i].getName();
		}
		return types;
	}
}
