package dtd.acternity.service.launcher;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.remoting.rmi.RmiProxyFactoryBean;

import de.act.common.interfaces.ILogin;
import de.act.common.interfaces.Webservice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@EnableAutoConfiguration
@EnableJpaRepositories(basePackages = ("dtd.acternity.service.db.repository"))
@EntityScan(basePackages = "dtd.acternity.service.model")
@ComponentScan(basePackages="dtd.acternity")
public class ApplicationLauncher {

	private static final Logger log = LoggerFactory.getLogger(ApplicationLauncher.class);

	public static void main(String[] args) {
        SpringApplication.run(ApplicationLauncher.class, args);
        log.info("Application is started......");
	}
	
    @Bean
    RmiProxyFactoryBean rmiProxy() {
        RmiProxyFactoryBean bean = new RmiProxyFactoryBean();
        bean.setServiceInterface(Webservice.class);
        bean.setServiceUrl("rmi://localhost:18886/mobileService");
        return bean;
    }
    
	@Bean
	RmiProxyFactoryBean rmiLoginProxy() {
		RmiProxyFactoryBean bean = new RmiProxyFactoryBean();
		bean.setServiceInterface(ILogin.class);
		bean.setServiceUrl("rmi://localhost:18886/login");

		return bean;
	}

}
