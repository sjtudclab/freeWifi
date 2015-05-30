package cn.edu.sjtu.dclab.freewifi.enums;

public enum Education {
		UNDER_HIGH_SCHOOL("高中以下",0),HIGH_SCHOOL("高中",1),COLLEGE("大专",2),BACHELOR("本科",3),
		MASTER("研究生及以上",4),ALL("不限",5);
		private String name;
	    private int value;
		private Education(String name, int value) {
			this.name = name;
			this.value = value;
		}
		public String getName() {
			return name;
		}
		public int getValue() {
			return value;
		}
		public static Education get(int value) {
			switch (value) {
			case 0:
				return UNDER_HIGH_SCHOOL;
			case 1:
				return HIGH_SCHOOL;
			case 2:
				return COLLEGE;
			case 3:
				return BACHELOR;
			case 4:
				return MASTER;
			case 5:
				return ALL;
			}
			return ALL;
		}
}
