package models.enums;

/**
 * Created with IntelliJ IDEA.
 * User: antoine
 * Date: 10/08/13
 * Time: 13:11
 * To change this template use File | Settings | File Templates.
 */
public enum Statut {
    creation("Création"), termine("Terminé"), expedition("Expédition"), reception("Réception");
    /**
     * String representation of the Statut.
     */
    private String libelle;

    /**
     * Constructor.
     * @param libelle
     */
    Statut(String libelle) {
        this.libelle = libelle;
    }

    /**
     * Get the String representation of the Statut.
     * @return libelle
     */
    public String asString() {
        return libelle;
    }

    /**
     * Return an Statut from a String value.
     * @param that
     * @return Statut.
     */
    public static Statut valueOfByString(String that) {
        switch (that) {
            case "Création" : return creation;
            case "Terminé" : return termine;
            case "Expédition" : return expedition;
            case "Réception" : return reception;
            default: throw new IllegalArgumentException(that + " is not a Statut.");
        }
    }
}
