package no.imr.nmdapi.stox.service;

import java.util.List;
import no.imr.nmd.commons.stox.jaxb.v1.StoxProjectType;
import no.imr.nmdapi.dao.file.NMDDataDao;
import no.imr.nmdapi.generic.response.v1.ListElementType;
import no.imr.nmdapi.generic.response.v1.ResultElementType;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * NMDCruiseseries service layer implementation.
 *
 * @author kjetilf
 */
public class NMDStoxServiceImpl implements NMDStoxService {

    @Autowired
    private NMDDataDao nmdDataDao;

    @Override
    public Object getData(final String name) {
        return nmdDataDao.get(name, StoxProjectType.class);
    }

    @Override
    public void deleteData(final String name) {
        nmdDataDao.delete(name);
    }

   @Override
    public void insertData(final String name, final StoxProjectType projectType) {
        nmdDataDao.insert(name, projectType, StoxProjectType.class);
    }

    @Override
    public void updateData(final String name, final StoxProjectType projectType) {
        nmdDataDao.update(name, projectType, StoxProjectType.class);
    }

    @Override
    public ListElementType list() {
        List<String> names = nmdDataDao.listSeries();
        ListElementType elementType = new ListElementType();
        for (String name : names) {
            ResultElementType resultElementType = new ResultElementType();
            resultElementType.setResult(name);
            elementType.getElement().add(resultElementType);
        }
        return elementType;
    }

}
