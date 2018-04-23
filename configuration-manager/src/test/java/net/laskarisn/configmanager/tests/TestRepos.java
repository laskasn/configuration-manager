package net.laskarisn.configmanager.tests;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import net.laskarisn.configmanager.Application;
import net.laskarisn.configmanager.beans.GenericConfig;
import net.laskarisn.configmanager.services.GenericConfigService;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@WebAppConfiguration
public class TestRepos {

	@Autowired
	private GenericConfigService genericConfigService;
	
	
	private static String TESTING_GROUP_UNIQUE = "TESTING-dd3e86e1-fb7c-4343-bb06-b0308418ffc4";
	
	@Before
	public void setup() throws Exception {
		
		
	}
	
	
	
	@Test
    public void testCRUD() throws Exception {
		
		//check if there are existing testing configs (normally, shouldn't) and if so, delete them
		List<GenericConfig> existingConfigs = this.genericConfigService.getByGroup(TESTING_GROUP_UNIQUE);
		if(existingConfigs.size() > 0)
			existingConfigs.stream().forEach((config) -> {this.genericConfigService.delete(config);});
		
		assertTrue(this.genericConfigService.getByGroup(TESTING_GROUP_UNIQUE).size() == 0);
		
		
		//test creation
		GenericConfig genericConfig = new GenericConfig("test-config-1", TESTING_GROUP_UNIQUE, "{\"test-key\":\"test-value\"}");
		genericConfig = this.genericConfigService.store(genericConfig);
		
		assertTrue(this.genericConfigService.getByID(genericConfig.getId())!=null);
		assertTrue(this.genericConfigService.getByGroup(TESTING_GROUP_UNIQUE).size() == 1);
		
		GenericConfig storedGenericConfig = this.genericConfigService.getByID(genericConfig.getId());
		
		
		if(storedGenericConfig!=null) {
			assertTrue(TESTING_GROUP_UNIQUE.equals(storedGenericConfig.getGroup()));
			assertTrue("test-config-1".equals(storedGenericConfig.getName()));
		}

		
		//test editing
		storedGenericConfig.setName("test-config-2");
		storedGenericConfig.setData("non-json-data");
		this.genericConfigService.store(storedGenericConfig);
		
		GenericConfig editedGenericConfig = this.genericConfigService.getByID(genericConfig.getId());
		
		assertNotNull(editedGenericConfig);
		
		assertTrue("non-json-data".equals(editedGenericConfig.getData()));
		assertTrue("test-config-2".equals(editedGenericConfig.getName()));
		
		
		//test deletion
		this.genericConfigService.delete(editedGenericConfig);
		
		assertNull(this.genericConfigService.getByID(editedGenericConfig.getId()));
		
	}
	
	
	
}
