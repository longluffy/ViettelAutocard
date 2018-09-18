package com.vt.dto;

public enum LoginMethodEnum {
	MOBILE_HOMEPHONE_DCOM("mob"),
	ADSL_FTTH_NEXTTV("adsl"),
	PSTN("pstn"),
	TRUYEN_HINH_SO("multi"),
	DICH_VU_KHAC("other");

	private String name;

	private LoginMethodEnum(String stringVal) {
		name = stringVal;
	}

	public String toString() {
		return name;
	}

	public static String getEnumByString(String code) {
		for (LoginMethodEnum e : LoginMethodEnum.values()) {
			if (code == e.name)
				return e.name();
		}
		return null;
	}
}
