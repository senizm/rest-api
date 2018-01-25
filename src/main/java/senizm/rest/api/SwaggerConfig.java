package senizm.rest.api;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * <b>Project api</b><br />
 * SwaggerConfig.java<br />
 *
 * @author senizm
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {
	
    /**
     * api
     *
     * @return
     * 
     * @author senizm
     */
    @Bean
    public Docket api() { 
        return new Docket(DocumentationType.SWAGGER_2)  
          .select()
          .apis(RequestHandlerSelectors.basePackage("senizm"))
          .paths(PathSelectors.any())                          
          .build()
          .apiInfo(apiInfo());                                         
    }
    
    /**
     * apiInfo
     *
     * @return
     * 
     * @author senizm
     */
    private ApiInfo apiInfo() {
    	ApiInfoBuilder builder = new ApiInfoBuilder();
  	  	builder.title("Senizm Rest API Reference");
  	  	builder.version("0.0.0");
  	  	builder.description("");
  	  return builder.build();
    }
}