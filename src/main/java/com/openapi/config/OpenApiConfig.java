package com.openapi.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Collections;

/**
 * @author sourabh
 * @implNote Configuration class for Open API Specifications
 */
@Configuration
public class OpenApiConfig {


    /**
     * Open API Configuration Bean
     * @param title
     * @param version
     * @param description
     * @return
     */
    @Bean
    public OpenAPI openApiConfiguration(
            @Value("${openapi.title}") final String title,
            @Value("${openapi.version}") final String version,
            @Value("${openapi.description}") final String description
    ) {
        return new OpenAPI()
                .info(new Info()
                        .title(title)
                        .version(version)
                        .description(description)
                        .termsOfService("Terms of service")
                        .license(getLicense())
                        .contact(getContact())
                );
    }

    /**
     * Contact details for the developer(s)
     * @return
     */
    private Contact getContact() {
        Contact contact = new Contact();
        contact.setEmail("sourabhanant@gmail.com");
        contact.setName("Sourabh Parsekar");
        contact.setUrl("https://medium.com/@sourabhanant");
        contact.setExtensions(Collections.emptyMap());
        return contact;
    }

    /**
     * License creation
     * @return
     */
    private License getLicense() {
        License license = new License();
        license.setName("Apache License, Version 2.0");
        license.setUrl("http://www.apache.org/licenses/LICENSE-2.0");
        license.setExtensions(Collections.emptyMap());
        return license;
    }

}
