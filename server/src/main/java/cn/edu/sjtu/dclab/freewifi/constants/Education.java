package cn.edu.sjtu.dclab.freewifi.constants;

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
}
