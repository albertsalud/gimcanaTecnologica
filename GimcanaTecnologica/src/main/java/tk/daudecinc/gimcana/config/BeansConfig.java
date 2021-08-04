package tk.daudecinc.gimcana.config;

import javax.annotation.PostConstruct;
import javax.servlet.ServletContext;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeansConfig {
	
	@Autowired
	private ServletContext sc;
	
	@Value("${dd5.web.url}")
	private String webURL;
	
	@PostConstruct
	public void initGlobalParameters() {
		sc.setAttribute("webURL", webURL);
	}
	
	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

}
