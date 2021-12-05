package com.challenge.order.generator.config.property;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;

@Validated
@Configuration
@ConfigurationProperties(prefix = "trust-store-config")
public class TrustStoreProperties {

    /**
     * Trust store
     */
    @NotNull
    private Resource resource;

    /**
     * Password of the trust store
     */
    @NotNull
    private String password;

    public Resource getResource() {
        return resource;
    }

    public void setResource(Resource resource) {
        this.resource = resource;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
