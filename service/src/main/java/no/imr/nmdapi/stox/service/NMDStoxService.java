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
     * @param missiontype
     * @param year
     * @param platform
     * @param delivery
     * @return              Mission data.
     */
    Object getData(String missiontype, String year, String platform, String delivery);

    /**
     * Delete
     *
     * @param missiontype
     * @param year
     * @param platform
     * @param delivery
     */
    void deleteData(String missiontype, String year, String platform, String delivery);

    /**
     * Update
     *
     * @param missiontype
     * @param year
     * @param platform
     * @param delivery
     * @param dataset
     */
    void updateData(String missiontype, String year, String platform, String delivery, StoxProjectType dataset);

    /**
     * Insert
     *
     * @param missiontype
     * @param year
     * @param platform
     * @param delivery
     * @param dataset
     */
    void insertData(String missiontype, String year, String platform, String delivery, StoxProjectType dataset);

    /**
     *
     * @param missiontype
     * @param year
     * @param platform
     * @param delivery
     * @return
     */
    boolean hasData(String missiontype, String year, String platform, String delivery);

    /**
     *
     * @param cruisenr
     * @return
     */
    boolean hasDataByCruiseNr(String cruisenr);

    /**
     *
     * @param cruisenr
     * @return
     */
    Object getDataByCruiseNr(String cruisenr);
}
