package cn.edu.sjtu.dclab.freewifi.enums;

public enum Gender {
	MALE("男", 0), FEMALE("女", 1), ALL("不限", 2);
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

	public static Gender get(int value) {
		switch (value) {
		case 0:
			return MALE;
		case 1:
			return FEMALE;
		case 2:
			return ALL;
		}
		return ALL;
	}
	
	public static String[] getTypes(){
		Gender[] genderTypes = Gender.values();
		String[] types = new String[genderTypes.length];
		for (int i = 0; i < types.length; i++) {
			types[i] = genderTypes[i].getName();
		}
		return types;
	}
}
