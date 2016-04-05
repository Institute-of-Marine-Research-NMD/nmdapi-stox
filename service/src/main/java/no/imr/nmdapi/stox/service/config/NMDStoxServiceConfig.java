package no.imr.nmdapi.stox.service.config;

import no.imr.nmdapi.stox.service.NMDStoxService;
import no.imr.nmdapi.stox.service.NMDStoxServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * This contains all configuration for the reference services.
 *
 * @author kjetilf
 */
@Configuration
public class NMDStoxServiceConfig {

    /**
     * Creates the service implementation.
     *
     * @return  A reference service implementation.
     */
    @Bean(name="nmdCruiseService")
    public NMDStoxService getNMDCruiseService() {
        return new NMDStoxServiceImpl();
    }

}
