package fr.greta.soutenance2application;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlAnchor;
import com.gargoylesoftware.htmlunit.html.HtmlDivision;
import com.gargoylesoftware.htmlunit.html.HtmlHeading1;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

import java.util.List;

/**
 * The type Mesvil.
 */
public class Mesvil {

    /**
     * Search string.
     *
     * @param searchWord the search word
     * @return the string
     * @throws Exception the exception
     */
    public String search(String searchWord) throws Exception{

        String url = "https://mesvinyles.fr/fr/recherche?controller=search&s=" + searchWord;

        WebClient webClient = new WebClient();

        webClient.getOptions().setUseInsecureSSL(true);
        webClient.getOptions().setCssEnabled(false);
        webClient.getOptions().setJavaScriptEnabled(false);
        HtmlPage htmlPage = webClient.getPage(url);

        List<HtmlAnchor> links = htmlPage.getByXPath("//a[@class='thumbnail product-thumbnail']");

        String searchResult = "";
        int limit = 15;
        for (int i = 0; i < links.size(); i++) {
            if (i == limit){
                break;
            }
            String resultUrl = String.valueOf(links.get(i).click().getUrl());
            try{
                HtmlPage htmlPage1 = webClient.getPage(resultUrl);
                String titre = ((HtmlHeading1) htmlPage1.getByXPath(".//h1[@class='h1 productpage_title']").get(0)).getTextContent();
                String price = ((HtmlDivision) htmlPage1.getByXPath(".//div[@class='current-price']").get(0)).getTextContent();
                // String priceByDiv = ((HtmlElement) htmlPage1.getByXPath("/html/body/main/section/div/div/div/section/div[1]/div[2]/div[2]/div[2]/form/div[2]/div[1]/div/span").get(0)).getTextContent();
                String description = ((HtmlDivision) htmlPage1.getByXPath(".//div[@class='product-description']").get(0)).getTextContent();

                searchResult += "Title :- " + titre + '\n' +
                        "Price :- " + price + '\n' +
                        "Description :- " + description + '\n' +
                        "URL :- " + resultUrl +
                        "\n--------------------------------------------------------------------------------------------\n";
                System.out.println(searchResult);
            } catch(Exception e){
                e.printStackTrace();
            }
        }
        return searchResult;



    }

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     * @throws Exception the exception
     */
    public static void main(String[] args) throws Exception {

        Mesvil vil = new Mesvil();
        vil.search("Taylor Perry");
    }
}
