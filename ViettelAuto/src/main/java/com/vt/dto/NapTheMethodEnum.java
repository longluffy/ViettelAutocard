package com.vt.dto;

public enum NapTheMethodEnum {
	DIDONG_DCOM("1"),
	HOME_PHONE("2"),
	PSTN("3"),
	ADSL_FTTH_NEXTTV("4"),
	MULTISCREEN("5");

	private String name;

	private NapTheMethodEnum(String stringVal) {
		name = stringVal;
	}

	public String toString() {
		return name;
	}

	public static String getEnumByString(String code) {
		for (NapTheMethodEnum e : NapTheMethodEnum.values()) {
			if (code == e.name)
				return e.name();
		}
		return null;
	}
}
