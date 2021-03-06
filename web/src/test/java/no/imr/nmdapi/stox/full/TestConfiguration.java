package no.imr.nmdapi.stox.full;

import java.io.File;
import org.apache.commons.configuration.ConfigurationException;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 *
 * @author kjetilf
 */
@Configuration
public class TestConfiguration {

    @Bean
    public org.apache.commons.configuration.Configuration configuration() throws ConfigurationException {
        org.apache.commons.configuration.PropertiesConfiguration configuration = new org.apache.commons.configuration.PropertiesConfiguration();
        configuration.setProperty("pre.data.dir", System.getProperty("java.io.tmpdir") + File.separator + "stox" + File.separator);
        configuration.setProperty("default.readrole", "unrestricted");
        configuration.setProperty("default.writerole", "SG-FAG-WRITE");
        configuration.setProperty("default.owner", "imr");
        configuration.setProperty("app.packages", "no.imr.nmd.commons.dataset.jaxb:no.imr.nmd.commons.stox.jaxb.v1");
        configuration.setProperty("admin.role", "SG-FAG-430-NMD");
        return configuration;
    }

}
