package com.pentas.sellerweb.common.module.util;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

import org.apache.commons.collections4.map.ListOrderedMap;

public class DevMap extends ListOrderedMap<Object, Object>{

	private static final long serialVersionUID = -4485149791269586840L;

	@Override
	public Object put(Object key, Object value) {
		return super.put(convert2CamelCase((String)key), value);
	}
	
	private String convert2CamelCase(String underScore){
		if(underScore.indexOf("_") < 0 && Character.isLowerCase(underScore.charAt(0))) {
			return underScore;
		}
		StringBuilder result = new StringBuilder();
		boolean nextUpper = false;
		int len = underScore.length();
		
		for (int i = 0; i<len; i++) {
			char currentChar = underScore.charAt(i);
			if(currentChar == '_') {
				nextUpper = true;
			} else {
				if(nextUpper) {
					result.append(Character.toUpperCase(currentChar));
					nextUpper = false;
				} else {
					result.append(Character.toLowerCase(currentChar));
				}
			}
		}
		return result.toString();
	}
	
	public String getString(Object key) {
		return (String)get(key);
	}
	
	public String getString(Object key, String deflt) {
		String rslt = (String)get(key);
		return rslt == null ? deflt : rslt;
	}
	
	public Boolean getBoolean(Object key) {
		Object value = get(key);
		if (value == null) {
			return null;
		} else if (value instanceof Boolean) {
			return (Boolean) value;
		} else {
			return Boolean.parseBoolean(value.toString().trim());
		}
	}
	
	public Boolean getBoolean(Object key, boolean deflt) {
		Object value = get(key);
		if (value == null) {
			return deflt;
		} else if (value instanceof Boolean) {
			return (Boolean) value;
		} else {
			return Boolean.parseBoolean(value.toString().trim());
		}
	}
	
	public Integer getInt(Object key) {
		return getInteger(key);
	}
	
	public Integer getInteger(Object key) {
		Object value = get(key);
		if (value == null) {
			return null;
		} else if (value instanceof Integer) {
			return (Integer) value;
		} else {
			return Integer.valueOf(value.toString().trim());
		}
	}
	
	public Long getLong(Object key) {
		Object value = get(key);
		if (value == null) {
			return null;
		} else if (value instanceof Long) {
			return (Long) value;
		} else {
			return Long.valueOf(value.toString().trim());
		}
	}

	public Float getFloat(Object key) {
		Object value = get(key);
		if (value == null) {
			return null;
		} else if (value instanceof Float) {
			return (Float) value;
		} else {
			return Float.valueOf(value.toString().trim());
		}
	}

	public Double getDouble(Object key) {
		Object value = get(key);
		if (value == null) {
			return null;
		} else if (value instanceof Double) {
			return (Double) value;
		} else {
			return Double.valueOf(value.toString().trim());
		}
	}

	public BigDecimal getBigDecimal(Object key) {
		Object value = get(key);
		if (value == null) {
			return null;
		} else if (value instanceof BigDecimal) {
			return (BigDecimal) value;
		} else {
			return new BigDecimal(value.toString().trim());
		}
	}
	
	public DevMap getDevMap(Object key) {
		Object value = get(key);
		if (value == null) {
			return null;
		} else if (value instanceof DevMap) {
			return (DevMap) value;
		} else {
			return null;
		}
	}
	
	public HashMap getHashMap(Object key) {
		Object value = get(key);
		if (value == null) {
			return null;
		} else if (value instanceof HashMap) {
			return (HashMap) value;
		} else {
			return null;
		}
	}

	public Date getDate(Object key) {
		return getDateFormat(key, "yyyy-MM-dd");
	}

	public Date getDateTime(Object key) {
		return getDateFormat(key, "yyyy-MM-dd HH:mm:ss");
	}

	public Date getDateFormat(Object key, String format) {
		Object value = get(key);
		if (value == null) {
			return null;
		} else if (value instanceof java.sql.Date) {
			java.sql.Date sqlDate = (java.sql.Date) value;
			return new Date(sqlDate.getTime());
		} else if (value instanceof java.util.Date) {
			return (Date) value;
		} else {
			String str = value.toString().trim();
			SimpleDateFormat sdf = new SimpleDateFormat(format);
			sdf.setLenient(false);
			try {
				return sdf.parse(str);
			} catch (ParseException e) {
				return null;
			}
		}
	}

	public Timestamp getTimestamp(Object key) {
		Object value = get(key);
		if (value == null) {
			return null;
		} else if (value instanceof java.sql.Date) {
			java.sql.Date sqlDate = (java.sql.Date) value;
			return new Timestamp(sqlDate.getTime());
		} else if (value instanceof Timestamp) {
			return (Timestamp) value;
		} else if (value instanceof Date) {
			Date date = (Date) value;
			return new Timestamp(date.getTime());
		} else {
			return Timestamp.valueOf(value.toString());
		}
	}
}
