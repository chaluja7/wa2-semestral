package cz.cvut.wa2.service.googleMaps;

import cz.cvut.wa2.service.googleMaps.resource.GoogleAddressResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * @author jakubchalupa
 * @since 14.05.16
 */
@Service
public class GoogleMapsAddressProvider {

    @Autowired
    protected GoogleMapsAddressManager googleMapsAddressManager;

    /**
     * will download incident address from lat&lon
     * @param lat latitude
     * @param lon longitude
     * @throws BadGPSException
     */
    public String getAddressFromGPS(double lat, double lon) throws BadGPSException {
        //ziskame adresu
        GoogleAddressResource googleAddresses = googleMapsAddressManager.getGoogleAddresses(lat, lon);
        if(googleAddresses.getResults().size() > 0) {
            String address = googleAddresses.getResults().get(0).getFormatted_address();
            if(!StringUtils.isEmpty(address)) {
                return address;
            }
        }

        throw new BadGPSException();
    }

}

