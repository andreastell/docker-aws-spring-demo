package se.callistaenterprise.demo;

import net.bull.javamelody.MonitoredWithAnnotationPointcut;
import net.bull.javamelody.MonitoringFilter;
import net.bull.javamelody.MonitoringSpringAdvisor;
import net.bull.javamelody.Parameter;
import org.springframework.aop.support.annotation.AnnotationMatchingPointcut;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.DispatcherType;

/**
 * Created by tell on 2016-08-29.
 */
@Configuration
@ImportResource("classpath:net/bull/javamelody/monitoring-spring.xml")
@SuppressWarnings("javadoc")
public class MelodyConfiguration { //implements ServletContextInitializer {

    @Bean
    public FilterRegistrationBean filterRegistration() {
        final FilterRegistrationBean filterRegistration = new FilterRegistrationBean();
        filterRegistration.setFilter(new MonitoringFilter());
        filterRegistration.setAsyncSupported(true);
        filterRegistration.setDispatcherTypes(DispatcherType.REQUEST, DispatcherType.ASYNC);
        // see the list of parameters:
        // https://github.com/javamelody/javamelody/wiki/UserGuide#6-optional-parameters
        filterRegistration.addInitParameter(Parameter.LOG.getCode(), "true");
        filterRegistration.addInitParameter(Parameter.NO_DATABASE.getCode(), "true");
        // to exclude images, css, fonts and js urls from the monitoring:
        filterRegistration.addInitParameter(Parameter.URL_EXCLUDE_PATTERN.getCode(), "(/webjars/.*|/css/.*|/images/.*|/fonts/.*|/js/.*)");
        filterRegistration.addUrlPatterns("/*");
        return filterRegistration;
    }

    // monitoring of beans or methods having @MonitoredWithSpring:
    @Bean
    public MonitoringSpringAdvisor monitoringSpringAdvisor() {
        final MonitoringSpringAdvisor advisor = new MonitoringSpringAdvisor();
        advisor.setPointcut(new MonitoredWithAnnotationPointcut());
        return advisor;
    }

    // monitoring of all services and controllers
    @Bean
    public MonitoringSpringAdvisor monitoringSpringServiceAdvisor() {
        final MonitoringSpringAdvisor advisor = new MonitoringSpringAdvisor();
        advisor.setPointcut(new AnnotationMatchingPointcut(Service.class));
        return advisor;
    }

    @Bean
    public MonitoringSpringAdvisor monitoringSpringControllerAdvisor() {
        final MonitoringSpringAdvisor advisor = new MonitoringSpringAdvisor();
        advisor.setPointcut(new AnnotationMatchingPointcut(RestController.class));
        return advisor;
    }
}
