package fr.greta.soutenance2application;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlAnchor;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

import java.io.IOException;
import java.util.List;

/**
 * The type Discogs.
 */
public class Discogs {

    /**
     * Scrawl sites string.
     *
     * @param searchkey the searchkey
     * @return the string
     * @throws IOException the io exception
     * @throws IOException the io exception
     */
    public String ScrawlSites(String searchkey) throws IOException, IOException {

        String url = "https://www.discogs.com/search/?q=" + searchkey;


        WebClient webClient = new WebClient(BrowserVersion.FIREFOX);
        webClient.getOptions().setUseInsecureSSL(true);
        webClient.getOptions().setCssEnabled(false);
        webClient.getOptions().setJavaScriptEnabled(false);
        HtmlPage htmlPage = webClient.getPage(url);


        List<HtmlAnchor> webLinks = htmlPage.getByXPath("//*[@class='cards cards_layout_large']");

        String searchResults = "";

        int numSearch = 10;
        for (HtmlElement e : webLinks) {
            if(numSearch-- <= 0){
                break;
            }
            HtmlPage htmlPage1 = webClient.getPage(e.click().getUrl()   );
            String titreArtist = "";
            String prixArticle = "";
            String description = "";
            String datePublish = "";

            List<HtmlElement> titre = htmlPage1.getByXPath("/html/body/div[1]/div[4]/div[2]/div[1]/div[1]/div/h1[2]");
            //List<HtmlElement> prix = htmlPage1.getByXPath(".//span[@class='price']");
            List<HtmlElement> desc = htmlPage1.getByXPath("/html/body/div[1]/div[4]/div[3]/div[2]/ul/li[9]/div[1]/a");
            List<HtmlElement> date = htmlPage.getByXPath("/html/body/div[1]/div/div/div[2]/div[1]/div[2]/div/div[2]/table/tbody/tr[4]/td/a/time");


            for (HtmlElement n : titre) {

                titreArtist = n.getTextContent();

            }
//            for (HtmlElement p : prix) {
//                prixArticle = p.getTextContent();
//                prixArticle = prixArticle.replace("\u00a0", "");
//            }
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
        Discogs ds = new Discogs();
        ds.ScrawlSites("Bobby Vee");
    }


}
