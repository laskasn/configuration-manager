package net.laskarisn.configmanager.beans;

import java.util.Date;
import java.util.UUID;


import org.springframework.data.annotation.Id;

/**
 * 
 * Intended to be used as a base for storing configurations within the database.
 * All configurations should extend this base class.
 *
 */
public abstract class ConfigBase {

	@Id
	private UUID id;
	private String name;
	private String group;
	private Date created;
	private Date updated;
	
	public ConfigBase() {
		this.id = UUID.randomUUID();
		this.created = new Date();
		this.updated = new Date();
	}
	
	public ConfigBase(String name, String group) {
		this.id = UUID.randomUUID();
		this.name = name;
		this.group = group;
		this.created = new Date();
		this.updated = new Date();
	}
	
	public ConfigBase(UUID id, String name, String group) {
		this.id = id;
		this.name = name;
		this.group = group;
		this.created = new Date();
		this.updated = new Date();
	}
	

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getGroup() {
		return group;
	}

	public void setGroup(String group) {
		this.group = group;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public Date getUpdated() {
		return updated;
	}

	public void setUpdated(Date updated) {
		this.updated = updated;
	}

	@Override
	public String toString() {
		return "id=" + id + ", name=" + name + ", group=" + group  + ", created=" + created + ", updated=" + updated;
	}
	
	
	
	
	
}
