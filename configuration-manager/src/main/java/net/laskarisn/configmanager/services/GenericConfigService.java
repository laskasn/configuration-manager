package net.laskarisn.configmanager.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import net.laskarisn.configmanager.beans.GenericConfig;
import net.laskarisn.configmanager.repositories.GenericRepository;

@Service
public class GenericConfigService {

	
	@Autowired
	private GenericRepository genericRepository;
	
	
	@Cacheable("GenericConfigService")
	public GenericConfig getByID(UUID id) {
		Optional<GenericConfig> optional = genericRepository.findById(id);
		return optional.isPresent() ? optional.get() : null;
	}
	
	
	public List<GenericConfig> getByGroup(String group){
		return genericRepository.findByGroup(group);
	}
	
	@CachePut(value="GenericConfigService")
	public GenericConfig store(GenericConfig genericConfig) {
		return genericRepository.save(genericConfig);
	}
	
	@CacheEvict(value = "GenericConfigService", allEntries = true)
	public void delete(UUID id) {
		genericRepository.deleteById(id);
	}
	
	@CacheEvict(value = "GenericConfigService", allEntries = true)
	public void delete(GenericConfig genericConfig) {
		genericRepository.delete(genericConfig);
	}
	
	//TODO: Please, find a better way to delete (in batch) 
	public void deleteGroup(String group){
		genericRepository.findByGroup(group).stream().forEach(conf -> genericRepository.delete(conf));
	}
	
	
	
}
