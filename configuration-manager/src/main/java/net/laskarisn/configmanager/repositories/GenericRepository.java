package net.laskarisn.configmanager.repositories;

import java.util.List;
import java.util.UUID;

import net.laskarisn.configmanager.beans.GenericConfig;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;

public interface GenericRepository extends MongoRepository<GenericConfig, UUID> {
	
	List<GenericConfig> findByGroup(@Param("group") String group);
	
	
	
}
