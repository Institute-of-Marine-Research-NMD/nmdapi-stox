package no.imr.nmdapi.stox.controller;

import javax.servlet.http.HttpServletResponse;
import no.imr.framework.logging.slf4j.aspects.stereotype.PerformanceLogging;
import no.imr.nmd.commons.stox.jaxb.v1.StoxProjectType;
import no.imr.nmdapi.exceptions.BadRequestException;
import no.imr.nmdapi.generic.response.v1.ListElementType;
import no.imr.nmdapi.stox.service.NMDStoxService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Controller object for mission requests.
 *
 * @author kjetilf
 */
@Controller
public class StoxController {
  /**
     * Url part that defines it as echosounder.
     */
    public static final String STOX_URL = "/stox";

    /**
     * Class logger.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(StoxController.class);

    /**
     * Service layer object for nmd biotic queries.
     */
    @Autowired
    private NMDStoxService nmdStoxService;

    /**
     * Get echosounder data for mission.
     *
     * @param mission
     * @return Response object.
     */
    @PerformanceLogging
    @RequestMapping(value = "/{missiontype}/{year}/{platform}/{delivery}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Object findByMission(@PathVariable(value = "missiontype") String missiontype, @PathVariable(value = "year") String year, @PathVariable(value = "platform") String platform, @PathVariable(value = "delivery") String delivery) {
        LOGGER.info("Start EchosounderController.findByMission");
        return nmdStoxService.getData(missiontype, year, platform, delivery);
    }

    /**
     * Delete echosounder data for mission.
     *
     * @param mission
     */
    @PerformanceLogging
    @RequestMapping(value = "/{missiontype}/{year}/{platform}/{delivery}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public void deleteByMission(@PathVariable(value = "missiontype") String missiontype, @PathVariable(value = "year") String year, @PathVariable(value = "platform") String platform, @PathVariable(value = "delivery") String delivery) {
        LOGGER.info("Start EchosounderController.deleteByMission");
        nmdStoxService.deleteData(missiontype, year, platform, delivery);
    }

    /**
     * Update echosounder data for mission.
     *
     * @param mission
     * @param
     */
    @PerformanceLogging
    @RequestMapping(value = "/{missiontype}/{year}/{platform}/{delivery}", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public void insertByMission(@PathVariable(value = "missiontype") String missiontype, @PathVariable(value = "year") String year, @PathVariable(value = "platform") String platform, @PathVariable(value = "delivery") String delivery, @RequestBody StoxProjectType projectType) {
        LOGGER.info("Start EchosounderController.insertByMission");
        nmdStoxService.insertData(missiontype, year, platform, delivery, projectType);
    }

     /**
     * insert echosounder data for mission.
     *
     * @param mission
     * @param projectType
     */
    @PerformanceLogging
    @RequestMapping(value = "/{missiontype}/{year}/{platform}/{delivery}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public void updateByMission(@PathVariable(value = "missiontype") String missiontype, @PathVariable(value = "year") String year, @PathVariable(value = "platform") String platform, @PathVariable(value = "delivery") String delivery, @RequestBody StoxProjectType projectType) {
        LOGGER.info("Start EchosounderController.updateByMission");
        nmdStoxService.updateData(missiontype, year, platform, delivery, projectType);
    }


    /**
     * Does the mission have data
     *
     * @param missiontype
     * @param year
     * @param platform
     * @param delivery
     * @return
     */
    @PerformanceLogging
    @RequestMapping(value = "/{missiontype}/{year}/{platform}/{delivery}", method = RequestMethod.HEAD)
    @ResponseBody
    public void  hasData(HttpServletResponse httpServletResponse,@PathVariable(value = "missiontype") String missiontype, @PathVariable(value = "year") String year, @PathVariable(value = "platform") String platform, @PathVariable(value = "delivery") String delivery) {
        LOGGER.info("Start EchosounderController.hasData");
        if (nmdStoxService.hasData(missiontype, year, platform, delivery)){
           httpServletResponse.setStatus(HttpServletResponse.SC_OK);

        } else {
         httpServletResponse.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }
    }

    /**
     * Get data by id or cruise number.
     *
     * @return Response object.
     */
    @PerformanceLogging
    @RequestMapping(value = "/find", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Object find(@RequestParam(value = "cruisenr", required = false) String cruisenr) {
        LOGGER.info("Start BioticController.find");
        if (cruisenr != null) {
            return nmdStoxService.getDataByCruiseNr(cruisenr);
        } else {
            throw new BadRequestException("Cruisenr parameters must be set.");
        }
    }

    /**
     * Get data by id or cruise number.
     *
     * @return Response object.
     */
    @PerformanceLogging
    @RequestMapping(value = "/find", method = RequestMethod.HEAD)
    @ResponseBody
    public void find(HttpServletResponse httpServletResponse, @RequestParam(value = "cruisenr", required = false) String cruisenr) {
        LOGGER.info("Start BioticController.find");
        if (nmdStoxService.hasDataByCruiseNr(cruisenr)) {
            httpServletResponse.setStatus(HttpServletResponse.SC_OK);
        } else {
            httpServletResponse.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }
    }


}

