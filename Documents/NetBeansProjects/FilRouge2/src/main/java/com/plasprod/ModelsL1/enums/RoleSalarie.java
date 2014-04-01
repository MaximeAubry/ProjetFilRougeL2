package models.enums;

/**
 * Date: 29/08/13
 */
public enum  RoleSalarie {

    admin("admin"), commercial("commercial"), logistique("logistique");
    /**
     * String representation of the RoleSalarie.
     */
    private String type;

    /**
     * Constructor.
     * @param type
     */
    RoleSalarie(String type) {
        this.type = type;
    }

    /**
     * Get the String representation of the RoleSalarie.
     * @return type
     */
    public String asString() {
        return type;
    }

    /**
     * Return an RoleSalarie from a String value.
     * @param that
     * @return RoleSalarie.
     */
    public static RoleSalarie valueOfByString(String that) {
        switch (that) {
            case "admin" : return admin;
            case "commercial" : return commercial;
            case "logistique" : return logistique;
            default: throw new IllegalArgumentException(that + " is not a Type.");
        }
    }
}
