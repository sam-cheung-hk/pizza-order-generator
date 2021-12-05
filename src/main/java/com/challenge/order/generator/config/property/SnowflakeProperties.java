package com.challenge.order.generator.config.property;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Validated
@Configuration
@ConfigurationProperties(prefix = "snowflake-config")
public class SnowflakeProperties {

    /**
     * Data centre ID of Snowflake
     */
    @NotNull
    @Min(1)
    private Integer dataCenterId;

    /**
     * Worker ID of Snowflake
     */
    @NotNull
    @Min(1)
    @Max(31)
    private Integer workerId;

    public Integer getDataCenterId() {
        return dataCenterId;
    }

    public void setDataCenterId(Integer dataCenterId) {
        this.dataCenterId = dataCenterId;
    }

    public Integer getWorkerId() {
        return workerId;
    }

    public void setWorkerId(Integer workerId) {
        this.workerId = workerId;
    }

    @Override
    public String toString() {
        return "SnowflakeProperties{" +
                "dataCenterId=" + dataCenterId +
                ", workerId=" + workerId +
                '}';
    }
}
