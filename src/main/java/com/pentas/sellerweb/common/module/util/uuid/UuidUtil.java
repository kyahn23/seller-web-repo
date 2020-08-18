package com.pentas.sellerweb.common.module.util.uuid;

import java.util.UUID;

public class UuidUtil {
	
	static public String getUuid() {
		return UUID.randomUUID().toString();
	}
	
	static public String getUuidOnlyString() {
		return getUuid().replaceAll("-", "");
	}
	
	static public String getUuid(String input) {
		return UUID.nameUUIDFromBytes(input.getBytes()).toString();
	}
	
	static public String getUuidOnlyString(String input) {
		return getUuid(input).replaceAll("-", "");
	}
}
