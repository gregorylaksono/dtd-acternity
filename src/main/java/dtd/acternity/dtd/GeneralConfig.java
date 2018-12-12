package dtd.acternity.dtd;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GeneralConfig {
	
	@Bean
	public ModelMapper getModelMapper(){
		return new ModelMapper();
	}

}
