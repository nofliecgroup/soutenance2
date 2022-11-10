package fr.greta.soutenance2application;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.*;

import java.util.List;

/**
 * The type Lebon coin.
 */
public class LebonCoin {

    /**
     * Search string.
     *
     * @param searchWord the search word
     * @return the string
     * @throws Exception the exception
     */
    public String search(String searchWord) throws Exception{

        String url = "https://www.leboncoin.fr/recherche?text=" + searchWord;

        WebClient webClient = new WebClient();

        webClient.getOptions().setUseInsecureSSL(true);
        webClient.getOptions().setCssEnabled(false);
        webClient.getOptions().setJavaScriptEnabled(false);
        HtmlPage htmlPage = webClient.getPage(url);

        List<HtmlAnchor> links = htmlPage.getByXPath("//a[@data-qa-id='aditem_container']");
       // List<HtmlElement> elementList = htmlPage.getByXPath("//body");

        String searchResult = "";

        int limit = 5;
        for (int i = 0; i < links.size(); i++) {
            if (i == limit){
                break;
            }
            String retrievedInfo = String.valueOf(links.get(i).click().getUrl());
            try{
                HtmlPage htmlPage1 = webClient.getPage(retrievedInfo);
                String titre = ((HtmlHeading1) htmlPage1.getByXPath("//h1[@data-qa-id='adview_title']").get(0)).getTextContent();
                String price = ((HtmlDivision) htmlPage1.getByXPath("//div[@data-qa-id='adview_price']").get(0)).getTextContent();
                //String date = ((HtmlElement) htmlPage1.getByXPath("/html/body/div[2]/div/div[1]/div/div[1]/main/div/div/article/article/section[1]/div[3]/div/div[2]/p\n").get(0)).getTextContent();
                String description = ((HtmlDivision) htmlPage1.getByXPath("//div[@data-qa-id='adview_description_container']").get(0)).getTextContent();

                searchResult += "Title :- " + titre + '\n' +
                       "Price :- " + price + '\n' +
                       // "Price :- " + date + '\n' +
                        "Description :- " + description + '\n' +
                        "URL :- " + retrievedInfo +
                        "\n--------------------------------------------------------------------------------------------\n";
                System.out.println(description);
            } catch(Exception e){
                e.printStackTrace();
                System.out.println("Error occured fetching the data");
            }
        }
        return searchResult;


    }

   /* public static void main(String[] args) throws Exception {
        LebonCoin l = new LebonCoin();

        String pr = l.search("Bob Marley");
        System.out.println(pr);

    }*/

}
