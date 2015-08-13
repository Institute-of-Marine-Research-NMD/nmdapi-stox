package no.imr.nmdapi.stox.service;

import no.imr.nmd.commons.stox.jaxb.v1.StoxProjectType;
import no.imr.nmdapi.generic.response.v1.ListElementType;


/**
 * Service API for stox data.
 *
 * @author kjetilf
 */
public interface NMDStoxService {

    /**
     * Get .
     *
     * @param name
     * @return
     */
    Object getData(String name);

    /**
     * Delete
     *
     * @param name
     */
    void deleteData(String name);

    /**
     * Update
     *
     * @param name
     * @param projectType
     */
    void updateData(String name, StoxProjectType projectType);

    /**
     * Insert
     *
     * @param name
     * @param projectType
     */
    void insertData(String name, StoxProjectType projectType);

    /**
     *
     * @return
     */
    ListElementType list();

}
