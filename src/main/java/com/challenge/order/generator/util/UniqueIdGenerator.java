package com.challenge.order.generator.util;

import com.challenge.order.generator.config.property.SnowflakeProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import xyz.downgoon.snowflake.Snowflake;

@Component
public class UniqueIdGenerator {

    private final Snowflake snowflake;

    @Autowired
    public UniqueIdGenerator(SnowflakeProperties properties) {
        // Initialize snowflake
        this.snowflake = new Snowflake(properties.getDataCenterId(), properties.getWorkerId());
    }

    /**
     * Get next unique ID
     *
     * @return next unique ID
     */
    public long getNextId() {
        return snowflake.nextId();
    }

    /**
     * Get next unique ID (in String format)
     *
     * @return next unique ID (in String format)
     */
    public String getNextIdInString() {
        return String.valueOf(getNextId());
    }

}
