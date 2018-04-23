package net.laskarisn.configmanager.controllers;

import java.util.Date;
import java.util.UUID;


import net.laskarisn.configmanager.beans.GenericConfig;
import net.laskarisn.configmanager.services.GenericConfigService;
import net.laskarisn.configmanager.various.CustomResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/generic")
public class ManageConfigs {

	
	@Autowired
	private GenericConfigService genericConfigService;
	
	
	@RequestMapping(value = "/get/{id}", method = RequestMethod.GET, produces="application/json")
	//in case of exception, spring will use the DefaultHandlerExceptionResolver
	public @ResponseBody ResponseEntity<Object> get(@PathVariable(value = "id") UUID id) { 
		
		GenericConfig genericConfig = genericConfigService.getByID(id);
		if(genericConfig!=null)
			return ResponseEntity.status(HttpStatus.OK).body(genericConfig);
		else
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new CustomResponse("There's no config by that id"));
	}
	
	@RequestMapping(value = "/create", method = RequestMethod.POST, produces="application/json")
	//in case of exception, spring will use the DefaultHandlerExceptionResolver
	public @ResponseBody ResponseEntity<Object> create(@RequestBody GenericConfig config) { 
		
		System.out.println("Storing config with id: "+ config.getId().toString());
		
		config.setCreated(new Date()); //prolly set already within the bean, but just in case
		config.setUpdated(new Date()); //prolly set already within the bean, but just in case
		
		config = genericConfigService.store(config);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(config);
		
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.PUT, produces="application/json")
	public @ResponseBody ResponseEntity<Object> update(@RequestBody GenericConfig config) {
		
		System.out.println("Updating config with id: "+ config.getId().toString());
		
		config.setUpdated(new Date()); //prolly set already within the bean, but just in case
		
		config = genericConfigService.store(config);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(config);
	}
	
	
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE, produces="application/json")
	public @ResponseBody ResponseEntity<Object> delete(@PathVariable(value = "id") UUID id) {
		
		System.out.println("Deleting config with id: "+ id);
		genericConfigService.delete(id);
		return ResponseEntity.status(HttpStatus.OK).body(new CustomResponse("Successfully deleted the configuration with id: "+id));
		
	}
	
	
	
}
