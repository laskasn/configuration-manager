package net.laskarisn.configmanager.beans;

import java.util.UUID;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "generic")
public class GenericConfig extends ConfigBase {

	
	private Object data;

	public GenericConfig() {
		super();
	}
	
	
	public GenericConfig(UUID id) {
		super(id, null, null);
	}
	
	public GenericConfig(String name, String group, Object data) {
		super(name, group);
		this.data = data;
	}
	
	public GenericConfig(UUID id, String name, String group, Object data) {
		super(id, name, group);
		this.data = data;
	}
	
	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}


	@Override
	public String toString() {
		return super.toString() + ", data=" + data;
	}
	
	
	
	
	
}
