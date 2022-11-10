package fr.greta.soutenance2application;

/**
 * The type Scrawl blue print.
 */
public class ScrawlBluePrint {

    private String titre;
    private String description;
    private String prix;
    private String genre;
    private String annees;


    /**
     * Instantiates a new Scrawl blue print.
     *
     * @param titre       the titre
     * @param description the description
     * @param prix        the prix
     * @param genre       the genre
     * @param annees      the annees
     */
    public ScrawlBluePrint(String titre, String description, String prix, String genre, String annees) {
        this.titre = titre;
        this.description = description;
        this.prix = prix;
        this.genre = genre;
        this.annees = annees;

    }

    /**
     * Instantiates a new Scrawl blue print.
     */
    public ScrawlBluePrint() {

    }

    /**
     * Gets titre.
     *
     * @return the titre
     */
    public String getTitre() {
        return titre;
    }

    /**
     * Sets titre.
     *
     * @param titre the titre
     */
    public void setTitre(String titre) {
        this.titre = titre;
    }

    /**
     * Gets description.
     *
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets description.
     *
     * @param description the description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Gets prix.
     *
     * @return the prix
     */
    public String getPrix() {
        return prix;
    }

    /**
     * Sets prix.
     *
     * @param prix the prix
     */
    public void setPrix(String prix) {
        this.prix = prix;
    }

    /**
     * Gets genre.
     *
     * @return the genre
     */
    public String getGenre() {
        return genre;
    }

    /**
     * Sets genre.
     *
     * @param genre the genre
     */
    public void setGenre(String genre) {
        this.genre = genre;
    }

    /**
     * Gets annees.
     *
     * @return the annees
     */
    public String getAnnees() {
        return annees;
    }

    /**
     * Sets annees.
     *
     * @param annees the annees
     */
    public void setAnnees(String annees) {
        this.annees = annees;
    }



    @Override
    public String toString() {
        return "ScrawlBluePrint{" +
                "titre='" + titre + '\'' +
                ", description='" + description + '\'' +
                ", prix='" + prix + '\'' +
                ", genre='" + genre + '\'' +
                ", annees='" + annees + '\'' +

                '}';
    }
}
