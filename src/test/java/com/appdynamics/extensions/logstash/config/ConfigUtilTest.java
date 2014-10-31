package com.appdynamics.extensions.logstash.config;

import static org.junit.Assert.*;
import org.junit.Test;

import java.io.FileNotFoundException;

public class ConfigUtilTest {

    ConfigUtil<Configuration> configUtil = new ConfigUtil<Configuration>();

    @Test
    public void testLoadingCorrectConfigFile() throws FileNotFoundException {
        Configuration configuration = configUtil.readConfig(this.getClass().getResource("/conf/config.yaml").getFile(),Configuration.class);
        assertNotNull(configuration);
    }

    @Test(expected = Exception.class)
    public void testLoadingIncorrectConfigFileResultsInException() throws FileNotFoundException {
        configUtil.readConfig(this.getClass().getResource("/conf/config.yaml.error").getFile(),Configuration.class);
    }
    
}
