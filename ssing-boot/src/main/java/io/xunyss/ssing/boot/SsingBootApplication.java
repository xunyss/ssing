package io.xunyss.ssing.boot;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 
 * @author XUNYSS
 */
@SpringBootApplication
public class SsingBootApplication implements CommandLineRunner {
	
	// mysqld
	// mysqladmin -u root shutdown
	
	public static void main(String[] args) {
		SpringApplication.run(SsingBootApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.err.println("ZXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
		System.err.println("ZXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
		System.err.println("ZXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
		System.err.println("ZXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
		for (String arg : args) {
			System.err.println(arg);
		}
	}
}
