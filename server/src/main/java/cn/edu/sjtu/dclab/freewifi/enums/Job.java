package cn.edu.sjtu.dclab.freewifi.enums;

public enum Job {
	STUDENT("学生", 0), INDUSTRY("工业", 1), SERVICE("服务业", 2), IT("IT技术", 3), EDUCATION(
			"教育行业", 4), COMPANY("中小企业主", 5);
	private String name;
	private int value;

	private Job(String name, int value) {
		this.name = name;
		this.value = value;
	}

	public String getName() {
		return name;
	}

	public int getValue() {
		return value;
	}

	public static Job get(int value) {
		switch (value) {
		case 0:
			return STUDENT;
		case 1:
			return INDUSTRY;
		case 2:
			return SERVICE;
		case 3:
			return IT;
		case 4:
			return EDUCATION;
		case 5:
			return COMPANY;
		}
		return STUDENT;
	}
}
