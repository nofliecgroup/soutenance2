package fr.greta.soutenance2application;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.*;

import java.util.ArrayList;
import java.util.List;

/**
 * The type V corner.
 */
public class VCorner {
    /**
     * Search array list.
     *
     * @param searchkey the searchkey
     * @return the array list
     * @throws Exception the exception
     */
    public ArrayList<ScrawlBluePrint> search(String searchkey) throws Exception{
        String url = "https://www.vinylcorner.fr/catalogsearch/result/?q=" + searchkey;

        // String url = "https://leboncoin.fr/recherche?text=" + searchkey;
        WebClient webClient = new WebClient(BrowserVersion.FIREFOX);

        webClient.getOptions().setUseInsecureSSL(true);
        webClient.getOptions().setCssEnabled(false);
        webClient.getOptions().setJavaScriptEnabled(false);
        HtmlPage htmlPage = webClient.getPage(url);
        List<HtmlAnchor> webLinks = htmlPage.getByXPath("//a[@class='product photo product-item-photo']");
        StringBuilder searchResults = new StringBuilder();

        ArrayList<ScrawlBluePrint> listScrawler = new ArrayList<>();

        int numSearch = 5;
        int i = 0;

        for (HtmlElement e : webLinks) {
            if(numSearch-- <= 0){
                break;
            }
            HtmlPage htmlPage1 = webClient.getPage(e.click().getUrl());
            String titre = "";
            String prix = "";
            String description = "";
            String annee = "";

            List<HtmlElement> title = htmlPage1.getByXPath("/html/body/div[2]/main/div/div/div/div[2]/div[2]/p");
            List<HtmlElement> price = htmlPage1.getByXPath("/html/body/div[2]/main/div/div/div/div[2]/div[2]/div[4]/div/span/span/span");
            List<HtmlElement> descript = htmlPage1.getByXPath("/html/body/div[2]/main/div/div/div/div[2]/div[2]/div[3]/span/span");
            List<HtmlElement> date = htmlPage.getByXPath("/html/body/div[2]/main/div/div/div/div[5]/div[2]/div/div/div[2]/div[1]/p[2]");

            for (HtmlElement n : title) {
                titre = n.getTextContent();
            }
            for (HtmlElement p : price) {
                prix = p.getTextContent();
                prix = prix.replace("\u00a0", "");
            }
            for (HtmlElement d : descript) {
                description = d.getTextContent();
            }

            for (HtmlElement dt : date) {
                annee = dt.getTextContent();
            }

          //  listScrawler.add(titre);

            searchResults.append("Artist : ").append(titre).append("\nPrix : ").append(prix).append("\nDescription de l'article : ").append(annee).append("\nDate : ").append("\nDescription").append(description).append(htmlPage1.getUrl()).append("\n--------------------------------------------------------------------------------------------\n");

        }
        return listScrawler;
    }


    /**
     * The entry point of application.
     *
     * @param args the input arguments
     * @throws Exception the exception
     */
    public static void main(String[] args) throws Exception {
        VCorner corner = new VCorner();
        corner.search("Taylor Smith");

    }

}
