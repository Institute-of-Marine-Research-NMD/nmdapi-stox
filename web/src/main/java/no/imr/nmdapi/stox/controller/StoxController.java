package no.imr.nmdapi.stox.controller;

import no.imr.framework.logging.slf4j.aspects.stereotype.PerformanceLogging;
import no.imr.nmd.commons.dataset.jaxb.DatasetType;
import no.imr.nmd.commons.dataset.jaxb.DatasetsType;
import no.imr.nmd.commons.stox.jaxb.v1.StoxProjectType;
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
     * Url part that defines it as mission.
     */
    public static final String STOX_URL = "/stox";

    /**
     * Class logger.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(StoxController.class);

    /**
     * Service layer object for nmd mission queries.
     */
    @Autowired
    private NMDStoxService nmdStoxService;

    /**
     * Get data for cruiseserie.
     *
     * @param name
     * @return
     */
    @PerformanceLogging
    @RequestMapping(value = "/{name}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Object find(@PathVariable(value = "name") String name) {
        LOGGER.info("Start StoxController.find");
        return nmdStoxService.getData(name);
    }

    /**
     * Delete cruiseserie data for mission.
     *
     * @param name
     */
    @PerformanceLogging
    @RequestMapping(value = "/{name}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public void delete(@PathVariable(value = "name") String name) {
        LOGGER.info("Start StoxController.delete");
        nmdStoxService.deleteData(name);
    }

    /**
     *  Insert mission data for mission.
     *
     * @param name
     * @param stoxProjectType
     */
    @PerformanceLogging
    @RequestMapping(value = "/{name}", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public void insert(@PathVariable(value = "name") String name, @RequestBody StoxProjectType stoxProjectType) {
        LOGGER.info("Start StoxController.insert");
        nmdStoxService.insertData(name, stoxProjectType);
    }

    /**
     * Update  mission data for mission.
     *
     * @param name
     * @param stoxProjectType
     */
    @PerformanceLogging
    @RequestMapping(value = "/{name}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public void update(@PathVariable(value = "name") String name, @RequestBody StoxProjectType stoxProjectType) {
        LOGGER.info("Start StoxController.update");
        nmdStoxService.updateData(name, stoxProjectType);
    }

    /**
     * List all stox.
     *
     * @return
     */
    @PerformanceLogging
    @RequestMapping(value = "/", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public ListElementType list() {
        LOGGER.info("Start StoxController.list");
        return nmdStoxService.list();
    }

    /**
     * List all dataset information.
     *
     * @return
     */
    @PerformanceLogging
    @RequestMapping(params = "dataset",value = "/", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public DatasetsType listDatasets() {
        LOGGER.info("Start StoxController.listDatasets");
        return nmdStoxService.listDatasets();
    }

    /**
     * Update dataset information.
     *
     * @param dataset
     */
    @PerformanceLogging
    @RequestMapping(params = "dataset",value = "/", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public void updateDatasetInfo(@RequestBody DatasetType dataset) {
        LOGGER.info("Start StoxController.updateDatasetInfo");
        nmdStoxService.updateDatasets(dataset);
    }

    /**
     * Get data.
     *
     * @param name
     * @return
     */
    @PerformanceLogging
    @RequestMapping(value = "/{name}", method = RequestMethod.GET, params = {"type=info"})
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Object findInfo(@PathVariable(value = "name") String name) {
        LOGGER.info("Start StoxController.findInfo");
        return nmdStoxService.getInfo(name);
    }


}

