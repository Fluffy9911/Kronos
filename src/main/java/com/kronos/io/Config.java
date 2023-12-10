package com.kronos.io;

import java.util.HashMap;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Config {

	HashMap<String,String> data;

	public Config() {
		super();
		data = new HashMap<String,String>();
	}
	
	public void appendString(String key,String value) {
		data.put(key, value);
	}
	
	public String readString(String key) {
		return data.get(key);
	}
	
	public void appendInt(String key,int i) {
    	data.put(key, Integer.toString(i));
    }
	
    public int readInt(String key) {
    	return Integer.parseInt(readString(key));
    }
    
    public void appendFloat(String key,float i) {
    	data.put(key, Float.toString(i));
    }
	
    public Float readFloat(String key) {
    	return Float.parseFloat(readString(key));
    }
    
    public void appendLong(String key,long i) {
    	data.put(key, Long.toString(i));
    }
	
    public Long readLong(String key) {
    	return Long.parseLong(readString(key));
    }
    
    public void appendShort(String key,short i) {
    	data.put(key, Short.toString(i));
    }
	
    public Short readShort(String key) {
    	return Short.parseShort(readString(key));
    }
    
    public void appendByte(String key,byte i) {
    	data.put(key, Byte.toString(i));
    }
	
    public Byte readByte(String key) {
    	return Byte.parseByte(readString(key));
    }
    
    public void appendBoolean(String key,boolean i) {
    	data.put(key, Boolean.toString(i));
    }
	
    public Boolean readBoolean(String key) {
    	return Boolean.parseBoolean(readString(key));
    }
    
    public void appendConfig(String key,Config value) {
    	Gson g = new Gson();
    	String v= g.toJson(value);
    	appendString(key, v);
    }
    
    public Config readConfig(String key) {
    	Gson g = new Gson();
    	Config v = g.fromJson(readString(key), Config.class);
    	return v;
    	
    	
    }
    
    public void appendConfigArray(String key,Config[] value) {
    	Gson g = new Gson();
    	String v= g.toJson(value);
    	appendString(key, v);
    }
    
    public Config[] readConfigArray(String key) {
    	Gson g = new Gson();
    	Config[] v = g.fromJson(readString(key), Config[].class);
    	return v;
    	
    	
    }
    
    public void appendStringArray(String key,String[] value) {
    	Gson g = new Gson();
    	String v= g.toJson(value);
    	appendString(key, v);
    }
    
    public String[] readStringArray(String key) {
    	Gson g = new Gson();
    	String[] v = g.fromJson(readString(key), String[].class);
    	return v;
    	
    	
    }
    
    public void appendIntegerArray(String key,Integer[] value) {
    	Gson g = new Gson();
    	String v= g.toJson(value);
    	appendString(key, v);
    }
    
    public Integer[] readIntegerArray(String key) {
    	Gson g = new Gson();
    	Integer[] v = g.fromJson(readString(key), Integer[].class);
    	return v;
    	
    	
    }
    
    public void appendFloatArray(String key,Float[] value) {
    	Gson g = new Gson();
    	String v= g.toJson(value);
    	appendString(key, v);
    }
    
    public Float[] readFloatArray(String key) {
    	Gson g = new Gson();
    	Float[] v = g.fromJson(readString(key), Float[].class);
    	return v;
    	
    	
    }
    
    public void appendLongArray(String key,Long[] value) {
    	Gson g = new Gson();
    	String v= g.toJson(value);
    	appendString(key, v);
    }
    
    public Long[] readLongArray(String key) {
    	Gson g = new Gson();
    	Long[] v = g.fromJson(readString(key), Long[].class);
    	return v;
    	
    	
    }
    
    public void appendBooleanArray(String key,Boolean[] value) {
    	Gson g = new Gson();
    	String v= g.toJson(value);
    	appendString(key, v);
    }
    
    public Boolean[] readBooleanArray(String key) {
    	Gson g = new Gson();
    	Boolean[] v = g.fromJson(readString(key), Boolean[].class);
    	return v;
    	
    	
    }
    
    public void appendByteArray(String key,Byte[] value) {
    	Gson g = new Gson();
    	String v= g.toJson(value);
    	appendString(key, v);
    }
    
    public Byte[] readByteArray(String key) {
    	Gson g = new Gson();
    	Byte[] v = g.fromJson(readString(key), Byte[].class);
    	return v;
    	
    	
    }
    
    public String writeOut() {
    	Gson g = new GsonBuilder().setPrettyPrinting().create();
    	return g.toJson(this);
    }
    
    
}
