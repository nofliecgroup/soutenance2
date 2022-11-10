package fr.greta.soutenance2application;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlAnchor;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

import java.io.IOException;
import java.util.List;

/**
 * The type Fnac.
 */
public class Fnac {

    /**
     * Scrawl sites string.
     *
     * @param searchkey the searchkey
     * @return the string
     * @throws IOException the io exception
     * @throws IOException the io exception
     */
    public String ScrawlFnac(String searchkey) throws IOException, IOException {

        String url = "https://www.fnac.com/SearchResult/ResultList.aspx?SCat=0&Search=" + searchkey;


        WebClient webClient = new WebClient(BrowserVersion.FIREFOX);
        webClient.getOptions().setUseInsecureSSL(true);
        webClient.getOptions().setCssEnabled(false);
        webClient.getOptions().setJavaScriptEnabled(false);
        HtmlPage htmlPage = webClient.getPage(url);


        List<HtmlAnchor> webLinks = htmlPage.getByXPath(".//div[contains(@class,'Article-infoContent')]");

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

            //List<HtmlElement> titre = htmlPage1.getByXPath("//article/div/div[2]/h4/a");
            // List<HtmlElement> prix = htmlPage1.getByXPath("//article/div/div[2]/div[2]/span");
            //List<HtmlElement> desc = htmlPage1.getByXPath("//article/div/div[2]/h4/a");
            // List<HtmlElement> date = htmlPage.getByXPath("/html/body/div[2]/main/div/div/div/div[5]/div[2]/div/div/div[2]/div[1]/p[2]");

            // System.out.println(titre);

            for (HtmlElement n : webLinks) {

                titreArtist = n.getTextContent();
                System.out.println(titreArtist);
                if(titreArtist.toLowerCase().contains("bob marley") ){
                    htmlPage1 = n.click();
                    List<HtmlElement> titre = htmlPage1.getByXPath("//article/div/div[2]/h4/a");
                    List<HtmlElement> prix = htmlPage1.getByXPath("//article/form/div[2]/div/p[1]/a");
                    List<HtmlElement> desc = htmlPage1.getByXPath("//article/form/div[2]/div");


                    for (HtmlElement p : prix) {
                        prixArticle = p.getTextContent();
                        prixArticle = prixArticle.replace("\u00a0", "");
                        System.out.println(prixArticle);
                    }
                    for (HtmlElement d : desc) {
                        description = d.getTextContent();
                    }
                    for (HtmlElement p : prix) {
                        description = p.getTextContent();
                    }

                }

            }

//            for (HtmlElement dt : date) {
//                datePublish = dt.getTextContent();
//            }

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

        Fnac fc = new Fnac();

        String ScrawlFnac = fc.ScrawlFnac("Gaming Laptop ");
        System.out.println(ScrawlFnac + "");
    }




}
