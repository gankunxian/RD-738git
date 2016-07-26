/**
 * Program  : GsonUtils.java
 * Author   : misery
 * Create   : 2013-3-13 上午10:17:01
 *
 */

package com.hotshare.util;

import java.lang.reflect.Type;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

/**
 * gson工具类（性能比json-lib快一些）
 * 
 * @author misery
 * @version 1.0.0
 * @2013-3-13 上午10:17:01
 */
public class GsonUtils {

	/**
	 * Gson类库的封装工具类，专门负责解析json数据</br> 内部实现了Gson对象的单例
	 * 
	 * @version 1.0
	 * @since 2012-9-18
	 */

	private static Gson gson = null;

	static {
		if (gson == null) {
			gson = new GsonBuilder().serializeNulls().create();
		}
	}

	private GsonUtils() {

	}

	/**
	 * 将对象转换成json格式
	 * 
	 * @author misery
	 * @param ts
	 * @return
	 */
	public static String objectToJson(Object ts) {
		String jsonStr = null;
		if (gson != null) {
			jsonStr = gson.toJson(ts);
			jsonStr = jsonStr.replaceAll("\":null", "\":\"\"");
		}
		return jsonStr;
	}
	
	/**
	 * 将对象转换成json格式
	 * 
	 * @author misery
	 * @param ts
	 * @return
	 */
	public static String objectToJsonWithFilter(Object ts) {
		String jsonStr = null;
		gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
		jsonStr = gson.toJson(ts);
		return jsonStr;
	}

	/**
	 * 将对象转换成json格式(并自定义日期格式)
	 * 
	 * @author misery
	 * @param ts
	 * @return
	 */
	public static String objectToJsonDateSerializer(Object ts, final String dateformat) {
		String jsonStr = null;
		gson = new GsonBuilder().serializeNulls().registerTypeHierarchyAdapter(Date.class, new JsonSerializer<Date>() {

			public JsonElement serialize(Date src, Type typeOfSrc, JsonSerializationContext context) {
				SimpleDateFormat format = new SimpleDateFormat(dateformat);
				return new JsonPrimitive(format.format(src));
			}
		}).setDateFormat(dateformat).create();
		if (gson != null) {
			jsonStr = gson.toJson(ts);
			jsonStr = jsonStr.replaceAll("\":null", "\":\"\"");
		}
		return jsonStr;
	}
	
	
	/**
	 * 将对象转换成json格式(并自定义日期格式,可过滤标注过的属性)
	 * 
	 * @author misery
	 * @param ts
	 * @return
	 */
	public static String objectToJsonDateSerializerWithFilter(Object ts, final String dateformat) {
		String jsonStr = null;
		gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().registerTypeHierarchyAdapter(Date.class, new JsonSerializer<Date>() {

			public JsonElement serialize(Date src, Type typeOfSrc, JsonSerializationContext context) {
				SimpleDateFormat format = new SimpleDateFormat(dateformat);
				return new JsonPrimitive(format.format(src));
			}
		}).setDateFormat(dateformat).create();
		if (gson != null) {
			jsonStr = gson.toJson(ts);
		}
		return jsonStr;
	}


	/**
	 * 将json格式转换成list对象
	 * 
	 * @author misery
	 * @param jsonStr
	 * @return
	 */
	public static List<?> jsonToList(String jsonStr) {
		List<?> objList = null;
		if (gson != null) {
			java.lang.reflect.Type type = new com.google.gson.reflect.TypeToken<List<?>>() {}.getType();
			objList = gson.fromJson(jsonStr, type);
		}
		return objList;
	}

	/**
	 * 将json格式转换成list对象，并准确指定类型
	 * 
	 * @author misery
	 * @param jsonStr
	 * @param type
	 * @return
	 */
	public static List<?> jsonToList(String jsonStr, java.lang.reflect.Type type) {
		List<?> objList = null;
		if (gson != null) {
			objList = gson.fromJson(jsonStr, type);
		}
		return objList;
	}

	/**
	 * 将json格式转换成map对象
	 * 
	 * @author misery
	 * @param jsonStr
	 * @return
	 */
	public static Map<?, ?> jsonToMap(String jsonStr) {
		Map<?, ?> objMap = null;
		if (gson != null) {
			java.lang.reflect.Type type = new com.google.gson.reflect.TypeToken<Map<?, ?>>() {}.getType();
			objMap = gson.fromJson(jsonStr, type);
		}
		return objMap;
	}

	/**
	 * 将json转换成bean对象
	 * 
	 * @author misery
	 * @param jsonStr
	 * @return
	 */
	public static Object jsonToBean(String jsonStr, Class<?> cl) {
		Object obj = null;
		if (gson != null) {
			obj = gson.fromJson(jsonStr, cl);
		}
		return obj;
	}
	
	/**
	 * 将json转换成bean对象
	 * 
	 * @author misery
	 * @param jsonStr
	 * @return
	 */
	public static Object jsonToBean(String jsonStr, Type type) {
		Object obj = null;
		if (gson != null) {
			obj = gson.fromJson(jsonStr, type);
		}
		return obj;
	}

	/**
	 * 将json转换成bean对象
	 * 
	 * @author misery
	 * @param jsonStr
	 * @param cl
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <T> T jsonToBeanDateSerializer(String jsonStr, Class<T> cl, final String pattern) {
		Object obj = null;
		gson = new GsonBuilder().registerTypeAdapter(Date.class, new JsonDeserializer<Date>() {

			public Date deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
				SimpleDateFormat format = new SimpleDateFormat(pattern);
				String dateStr = json.getAsString();
				try {
					return format.parse(dateStr);
				} catch (ParseException e) {
					e.printStackTrace();
				}
				return null;
			}
		}).setDateFormat(pattern).create();
		if (gson != null) {
			obj = gson.fromJson(jsonStr, cl);
		}
		return (T) obj;
	}

	/**
	 * json数据中取值
	 * 
	 * @author misery
	 * @param jsonStr
	 * @param key
	 * @return
	 */
	public static Object getJsonValue(String jsonStr, String key) {
		Object rulsObj = null;
		Map<?, ?> rulsMap = jsonToMap(jsonStr);
		if (rulsMap != null && rulsMap.size() > 0) {
			rulsObj = rulsMap.get(key);
		}
		return rulsObj;
	}

	public static void main(String[] args) {
		// Master master = new Master();
		// master.setId("1");
		// master.setLogincount("23");
		// master.setLoginip("192.168.2.201");
		// master.setLogintime("2013-03-13");
		// master.setPwd("password");
		// master.setUser("root");
		// master.setUsertype("old");
		// List<Master> masterList = new ArrayList<Master>();
		// masterList.add(master);
		// masterList.add(master);
		//
		// // String json = GsonUtils.objectToJson(master);
		// String json = GsonUtils.objectToJson(masterList);
		// System.out.println(json);
		
		Map<String, String> map = new HashMap<String, String>();  
		map.put("id", "001");  
		map.put("name", "张三");  
		map.put("age", "27");  
//		String result = GsonUtils.objectToJson(map);

	}

}
