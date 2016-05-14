package cz.cvut.wa2.service.googleMaps;

import cz.cvut.wa2.service.googleMaps.resource.GoogleAddressResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

/**
 * Bean for handling address from google map api.
 *
 * @author jakubchalupa
 * @since 14.05.16
 */
@Service
public class GoogleMapsAddressManager {

    private static final String GOOGLE_API_URL = "https://maps.googleapis.com";

    private static final String GOOGLE_MAP_JSON_URI = "maps/api/geocode/json";

    private static final String LANGUAGE_PARAM = "language";

    private static final String GPS_PARAM = "latlng";

    /**
     * @param lat latitude
     * @param lon longitude
     * @return google address response on given latlon
     */
    public GoogleAddressResource getGoogleAddresses(double lat, double lon) {
        HttpEntity<GoogleAddressResource> googleAddressResponse = getGoogleAddressResponse(lat, lon, GoogleAddressResource.class);
        return googleAddressResponse.getBody();
    }

    /**
     * @param lat latitude
     * @param lon longitude
     * @return whole string of google address response on given latlon
     */
    public String getGoogleAddressesResponseString(double lat, double lon) {
        HttpEntity<String> googleAddressResponse = getGoogleAddressResponse(lat, lon, String.class);
        return googleAddressResponse.getBody();
    }

    /**
     * @param lat latitude
     * @param lon longitude
     * @return response from google api on given latlon
     */
    private <T> HttpEntity<T> getGoogleAddressResponse(double lat, double lon, Class<T> type) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);

        HttpEntity<?> entity = new HttpEntity<>(headers);

        UriComponentsBuilder builder = UriComponentsBuilder
            .fromHttpUrl(GOOGLE_API_URL)
            .path(GOOGLE_MAP_JSON_URI)
            .queryParam(LANGUAGE_PARAM, "cs")
            .queryParam(GPS_PARAM, lat + "," + lon);

        return new RestTemplate().exchange(builder.build().encode().toUri(), HttpMethod.GET, entity, type);
    }
}
