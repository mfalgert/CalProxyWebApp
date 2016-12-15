package se.kth.calproxy.web.config;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.context.support.GenericWebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;
import java.util.Set;

/**
* Created by Robert Norgren Erneborg on 2015-09-23.
*/
public class AppInitializer implements WebApplicationInitializer {

	@Override
	public void onStartup(final ServletContext servletContext) throws ServletException{
		System.out.println("MainWebAppInitializer.onStartup()");

		// Create the 'root' Spring application context
		final AnnotationConfigWebApplicationContext root = new AnnotationConfigWebApplicationContext();
		root.register(WebMVCConfig.class);
		// root.getEnvironment().setDefaultProfiles("embedded");

//		// Manages the lifecycle of the root application context
		servletContext.addListener(new ContextLoaderListener(root));

		FilterRegistration.Dynamic fr = servletContext.addFilter("encodingFilter",
				new CharacterEncodingFilter());
		fr.setInitParameter("encoding", "UTF-8");
		fr.setInitParameter("forceEncoding", "true");
		fr.addMappingForUrlPatterns(null, true, "/*");

		// Handles requests into the application
		final ServletRegistration.Dynamic appServlet = servletContext.addServlet("dispatcher", new DispatcherServlet(new GenericWebApplicationContext()));
		appServlet.setLoadOnStartup(1);
		final Set<String> mappingConflicts = appServlet.addMapping("/");
		if (!mappingConflicts.isEmpty()) {
			throw new IllegalStateException("'appServlet' could not be mapped to '/' due " + "to an existing mapping. This is a known issue under Tomcat versions " + "<= 7.0.14; see https://issues.apache.org/bugzilla/show_bug.cgi?id=51278");
		}
	}

}
