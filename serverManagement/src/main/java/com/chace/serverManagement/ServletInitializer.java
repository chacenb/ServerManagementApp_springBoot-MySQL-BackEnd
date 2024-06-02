package com.chace.serverManagement;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/* WHY DO WE NEED THIS CLASS > https://howtodoinjava.com/spring-boot/spring-boot-servletinitializer/
*****************************************************
* Spring Boot provides SpringBootServletInitializer that runs a Spring Application "FROM A WAR DEPLOYMENT". 
* It binds Servlet, Filter and ServletContextInitializer beans from the spring application context to the server.
* Note that a WebApplicationInitializer is only needed IF WE BUILD A WAR FILE AND DEPLOY IT. 
* We won’t need this if we run an embedded web server.
**/
public class ServletInitializer extends SpringBootServletInitializer {

	/* SpringBootServletInitializer is an abstract class that implements the WebApplicationInitializer. 
	* It provides a way to run a Spring Boot application from a traditional WAR deployment.*/

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(ServerManagementApplication.class);
	}

}
