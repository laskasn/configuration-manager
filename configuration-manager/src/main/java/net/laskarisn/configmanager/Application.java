package net.laskarisn.configmanager;


import net.laskarisn.configmanager.repositories.GenericRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;


@SpringBootApplication
@EnableCaching
@ComponentScan({ "net.laskarisn.configmanager" })
public class Application extends SpringBootServletInitializer {	

	
	@Autowired
	private GenericRepository genericRepository;
	
	
    public static void main(String[] args) throws Exception {
    	SpringApplication.run(Application.class, args);
    }
    
    
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
    	return application.sources(Application.class);
    }
    
    
    
    @Bean
    public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
        return args -> {
        	
//        	System.out.println(genericRepository.findAll());
        	

        };
    }

}
