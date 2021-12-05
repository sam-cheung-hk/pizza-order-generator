package com.challenge.order.generator.config.property;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;

@Validated
@Configuration
@ConfigurationProperties(prefix = "pizza-order-receiver-config")
public class PizzaOrderReceiverProperties {

    /**
     * Base URL for the service
     */
    private String basePath;

    /**
     * Relative path of APIs
     */
    @NestedConfigurationProperty
    private RelativePath relativePath = new RelativePath();

    public String getBasePath() {
        return basePath;
    }

    public void setBasePath(String basePath) {
        this.basePath = basePath;
    }

    public RelativePath getRelativePath() {
        return relativePath;
    }

    public void setRelativePath(RelativePath relativePath) {
        this.relativePath = relativePath;
    }

    @Override
    public String toString() {
        return "PizzaOrderReceiverProperties{" +
                "basePath='" + basePath + '\'' +
                ", relativePath=" + relativePath +
                '}';
    }

    public static class RelativePath {

        /**
         * Relative path for creating pizza order
         */
        @NotBlank
        private String createPizzaOrder;

        public String getCreatePizzaOrder() {
            return createPizzaOrder;
        }

        public void setCreatePizzaOrder(String createPizzaOrder) {
            this.createPizzaOrder = createPizzaOrder;
        }

        @Override
        public String toString() {
            return "RelativePath{" +
                    "createPizzaOrder='" + createPizzaOrder + '\'' +
                    '}';
        }
    }

}
