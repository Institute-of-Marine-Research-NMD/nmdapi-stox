package no.imr.nmdapi.stox.service;

import no.imr.nmd.commons.stox.jaxb.v1.StoxProjectType;
import no.imr.nmdapi.dao.file.NMDDataDao;
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
    public Object getData(final String missiontype, final String year, final String platform, final String delivery) {
        return nmdDataDao.get(missiontype, year, platform, delivery, StoxProjectType.class);
    }

    @Override
    public void deleteData(final String missiontype, final String year, final String platform, final String delivery) {
        nmdDataDao.delete(missiontype, year, platform, delivery);
        nmdDataDao.deleteDataset(missiontype, year, platform, delivery, "ECHOSOUNDER");
    }

   @Override
    public void insertData(final String missiontype, final String year, final String platform, final String delivery, final StoxProjectType dataset) {
        nmdDataDao.insert(missiontype, year, platform, delivery, dataset, StoxProjectType.class);
        nmdDataDao.insertDataset(missiontype, year, platform, delivery, "ECHOSOUNDER");
    }


    @Override
    public void updateData(final String missiontype, final String year, final String platform, final String delivery, final StoxProjectType dataset) {
        nmdDataDao.update(missiontype, year, platform, delivery, dataset, StoxProjectType.class);
    }

    @Override
    public boolean hasData(String missiontype, String year, String platform, String delivery) {
        return nmdDataDao.hasData(missiontype, year, platform, delivery);
    }

    @Override
    public boolean hasDataByCruiseNr(final String cruisenr) {
        return nmdDataDao.hasDataByCruiseNr(cruisenr);
    }

    @Override
    public Object getDataByCruiseNr(String cruisenr) {
        return nmdDataDao.getByCruiseNr(StoxProjectType.class, cruisenr);
    }


}
