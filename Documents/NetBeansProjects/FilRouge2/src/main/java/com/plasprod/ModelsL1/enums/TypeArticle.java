package models.enums;

/**
 * Created with IntelliJ IDEA.
 * User: antoine
 * Date: 10/08/13
 * Time: 13:11
 * To change this template use File | Settings | File Templates.
 */
public enum TypeArticle {
    produitFini("Produit Fini"), matierePremiere("Matière Première");
    /**
     * String representation of the Type.
     */
    private String typeArticle;

    /**
     * Constructor.
     * @param typeArticle
     */
    TypeArticle(String typeArticle) {
        this.typeArticle = typeArticle;
    }

    /**
     * Get the String representation of the Type.
     * @return typeArticle
     */
    public String asString() {
        return typeArticle;
    }

    /**
     * Return an Type from a String value.
     * @param that
     * @return Type.
     */
    public static TypeArticle valueOfByString(String that) {
        switch (that) {
            case "Produit Fini" : return produitFini;
            case "Matière Première" : return matierePremiere;
            default: throw new IllegalArgumentException(that + " is not a Type.");
        }
    }
}
