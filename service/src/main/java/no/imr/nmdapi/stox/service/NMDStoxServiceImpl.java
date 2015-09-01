package no.imr.nmdapi.stox.service;

import java.util.List;
import no.imr.nmd.commons.stox.jaxb.v1.StoxProjectType;
import no.imr.nmdapi.dao.file.NMDSeriesReferenceDao;
import no.imr.nmdapi.generic.response.v1.ListElementType;
import no.imr.nmdapi.generic.response.v1.ResultElementType;
import org.apache.commons.configuration.Configuration;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * NMDCruiseseries service layer implementation.
 *
 * @author kjetilf
 */
public class NMDStoxServiceImpl implements NMDStoxService {
    /**
     * Data type.
     */
    private static final String TYPE = "stox";

    @Autowired
    private NMDSeriesReferenceDao seriesReferenceDao;

    @Autowired
    private Configuration configuration;

     @Override
    public Object getData(final String name) {
        return seriesReferenceDao.get(name, StoxProjectType.class.getPackage().getName());
    }

    @Override
    public void deleteData(final String name) {
        seriesReferenceDao.delete(TYPE, name, true);
    }

   @Override
    public void insertData(final String name, final StoxProjectType stoxProjectType) {
        String readRole = configuration.getString("default.readrole");
        String writeRole = configuration.getString("default.writerole");
        String owner = configuration.getString("default.owner");
        seriesReferenceDao.insert(writeRole, readRole, owner, TYPE, name, stoxProjectType, true);
    }

    @Override
    public void updateData(final String name, final StoxProjectType stoxProjectType) {
        seriesReferenceDao.update(name, stoxProjectType);
    }

    @Override
    public ListElementType list() {
        List<String> names = seriesReferenceDao.list();
        ListElementType elementType = new ListElementType();
        for (String name : names) {
            ResultElementType resultElementType = new ResultElementType();
            resultElementType.setResult(name);
            elementType.getElement().add(resultElementType);
        }
        return elementType;
    }

}
