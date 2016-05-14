package cz.cvut.wa2.entity;

/**
 * Possible incident states
 *
 * @author jakubchalupa
 * @since 14.05.16
 */
public enum IncidentState {

    NEW,
    INVALID,
    IN_PROGRESS,
    SOLVED;

    public static IncidentState fromStringCode(String code) {
        if(code != null) {
            for(IncidentState incidentState : IncidentState.values()) {
                if(code.equalsIgnoreCase(incidentState.name())) {
                    return incidentState;
                }
            }
        }

        return null;
    }

}
