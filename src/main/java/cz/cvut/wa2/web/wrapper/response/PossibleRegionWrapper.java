package cz.cvut.wa2.web.wrapper.response;

/**
 * @author jakubchalupa
 * @since 14.05.16
 */
public class PossibleRegionWrapper {

    private String possibleRegionName;

    private boolean computing;

    private boolean unknownRegion;

    public String getPossibleRegionName() {
        return possibleRegionName;
    }

    public void setPossibleRegionName(String possibleRegionName) {
        this.possibleRegionName = possibleRegionName;
    }

    public boolean isComputing() {
        return computing;
    }

    public void setComputing(boolean computing) {
        this.computing = computing;
    }

    public boolean isUnknownRegion() {
        return unknownRegion;
    }

    public void setUnknownRegion(boolean unknownRegion) {
        this.unknownRegion = unknownRegion;
    }
}
