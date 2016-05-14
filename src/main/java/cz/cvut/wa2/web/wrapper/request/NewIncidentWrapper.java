package cz.cvut.wa2.web.wrapper.request;

/**
 * New incident model.
 *
 * @author jakubchalupa
 * @since 14.05.16
 */
public class NewIncidentWrapper {

    private String title;

    private String description;

    private double lat;

    private double lon;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLon() {
        return lon;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }
}
