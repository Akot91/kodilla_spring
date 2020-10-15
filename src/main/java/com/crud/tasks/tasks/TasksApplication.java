package com.crud.tasks.tasks;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class TasksApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(com.crud.tasks.TasksApplication.class, args);
	}

	/*@Override
	protected SpringApplicationBuilder configure (SpringApplicationBuilder application) {
		return application.sources(TasksApplication.class);
	}*/

}

