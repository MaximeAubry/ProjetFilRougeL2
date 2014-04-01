package models.enums;

/**
 * Created with IntelliJ IDEA.
 * User: antoine
 * Date: 10/08/13
 * Time: 13:11
 * To change this template use File | Settings | File Templates.
 */
public enum Type {
    client("Client"), fournisseur("Fournisseur");
    /**
     * String representation of the Type.
     */
    private String type;

    /**
     * Constructor.
     * @param type
     */
    Type(String type) {
        this.type = type;
    }

    /**
     * Get the String representation of the Type.
     * @return type
     */
    public String asString() {
        return type;
    }

    /**
     * Return an Type from a String value.
     * @param that
     * @return Type.
     */
    public static Type valueOfByString(String that) {
        switch (that) {
            case "Client" : return client;
            case "Fournisseur" : return fournisseur;
            default: throw new IllegalArgumentException(that + " is not a Type.");
        }
    }
}
