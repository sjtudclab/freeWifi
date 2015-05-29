package cn.edu.sjtu.dclab.freewifi.constants;

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
}
