package telran.library;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import telran.library.config.LibraryBooksConfigure;
import telran.library.config.LibraryLoggingAspect;


@SpringBootApplication
public class LibraryAppl {

	public static void main(String[] args) {
		SpringApplication.run(LibraryAppl.class, args);
		
	}

}
