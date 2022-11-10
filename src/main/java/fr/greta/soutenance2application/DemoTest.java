package fr.greta.soutenance2application;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlAnchor;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * The type Demo test.
 */
public class DemoTest {

    /**
     * Scrawl sites string.
     *
     * @param searchkey the searchkey
     * @return the string
     * @throws IOException the io exception
     */
    public String ScrawlSites(String searchkey) throws IOException {

        //String url = "https://www.vinylcorner.fr/catalogsearch/result/?q=" + searchkey;


        String url = "https://leboncoin.fr/recherche?text=" + searchkey;


        WebClient webClient = new WebClient(BrowserVersion.FIREFOX);
        webClient.getOptions().setUseInsecureSSL(true);
        webClient.getOptions().setCssEnabled(false);
        webClient.getOptions().setJavaScriptEnabled(false);
        HtmlPage htmlPage = webClient.getPage(url);


        List<HtmlAnchor> webLinks = htmlPage.getByXPath("/html/body/div[2]/div/div[2]/div/div[1]/main/div[5]/div/div[6]/div[1]/div[1]");

        String searchResults = "";

        int numSearch = 2;
            for (HtmlElement e : webLinks) {
                if(numSearch-- <= 0){
                    break;
                }
                HtmlPage htmlPage1 = webClient.getPage(e.click().getUrl());
                String titreArtist = "";
                String prixArticle = "";
                String description = "";
                String datePublish = "";

                List<HtmlElement> titre = htmlPage1.getByXPath(".//a/div/div[2]/div[1]/div[1]/p");
                List<HtmlElement> prix = htmlPage1.getByXPath(".//a/div/div[2]/div[1]/div[2]/p/span/span");
                List<HtmlElement> desc = htmlPage1.getByXPath(".//a/div/div[2]/div[2]/div[1]/p[1]");
                List<HtmlElement> date = htmlPage.getByXPath(".//a/div/div[2]/div[2]/div[1]/p[3]");


                for (HtmlElement n : titre) {

                    titreArtist = n.getTextContent();

                }
                for (HtmlElement p : prix) {
                    prixArticle = p.getTextContent();
                    prixArticle = prixArticle.replace("\u00a0", "");
                }
                for (HtmlElement d : desc) {
                    description = d.getTextContent();
                }

                for (HtmlElement dt : date) {
                    datePublish = dt.getTextContent();
                }

                searchResults += "Artist : " + titreArtist + "\nPrix : " + prixArticle + "\nDescription de l'article : " + datePublish + "\nDate : " + "\nDescription" + description + "" + htmlPage1.getUrl() +

                        "\n--------------------------------------------------------------------------------------------\n";

            }
            return searchResults;
        }


    /**
     * The entry point of application.
     *
     * @param args the input arguments
     * @throws IOException the io exception
     */
    public static void main(String[] args) throws IOException {
        DemoTest dt = new DemoTest();
        System.out.println( dt.ScrawlSites("Bob Marley"));

    }



}
