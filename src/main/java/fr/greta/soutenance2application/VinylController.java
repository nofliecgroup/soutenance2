package fr.greta.soutenance2application;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.controlsfx.control.CheckComboBox;

import java.io.*;
import java.util.ArrayList;

/**
 * The type Vinyl controller.
 */
public class VinylController {

    /**
     * The Vinyl scrawler.
     */
    VinylScrawler vinylScrawler = new VinylScrawler();

    /**
     * The Lebon coin.
     */
    LebonCoin lebonCoin = new LebonCoin();
    Fnac scrawlFnac = new Fnac();
    /**
     * The Vcorder.
     */
    VCorner vcorder =  new VCorner();
    /**
     * The Scrawwl mesvil.
     */
    Mesvil scrawwlMesvil = new Mesvil();
    Cfactory scrawfactory = new Cfactory();
    /**
     * The Sm.
     */
    SendMail sm = new SendMail();

    @FXML
    private CheckBox cfactory;

    @FXML
    private DatePicker datePick;

    @FXML
    private CheckBox discogs;

    @FXML
    private Button effacher;

    @FXML
    private CheckBox fnac;

    @FXML
    private CheckBox lbcoin;

    @FXML
    private TextField maxprix;

    @FXML
    private CheckBox mesvil;

    @FXML
    private TextField minprix;

    @FXML
    private TextField titre;

    @FXML
    private CheckBox vicorner;
    @FXML
    private Button btn_effacer;

    @FXML
    private Button btn_valider;

    @FXML
    private PasswordField lb_password;

    @FXML
    private TextField lb_username;

    @FXML
    private Label errorLabel;

    @FXML
    private Button btn_recherche;

    @FXML
    private TextField nomdatabase;

    @FXML
    private TextField nomserveur;

    @FXML
    private TextField nport;

    @FXML
    private PasswordField password;

    @FXML
    private TextField username;

    @FXML
    private Button valider;

    @FXML
    private TextArea rechercheResult;

    @FXML
    private ComboBox<String> genreCheck;

    @FXML
    private TextField emailAddress;

    /**
     * The Result 1.
     */
    ArrayList<ScrawlBluePrint> result1 = new ArrayList<>();

    /**
     * The List of genre.
     */
    ObservableList<String> listOfGenre = FXCollections.observableArrayList("Selectionnez un genre ", "Rock",
            "Blues",
            "Jazz",
            "Reggae",
            "Funk",
            "Electro",
            "DubStep",
            "Soul");

    /**
     * The Check combo box.
     */
    CheckComboBox<String> checkComboBox = new CheckComboBox<>(listOfGenre);

    /**
     * Sets check combo box.
     *
     * @param checkComboBox the check combo box
     */
    public void setCheckComboBox(CheckComboBox<String> checkComboBox) {
        this.checkComboBox = checkComboBox;
    }

    /**
     * Gets check combo box.
     *
     * @return the check combo box
     */
    public CheckComboBox<String> getCheckComboBox() {
        return checkComboBox;
    }


    /**
     * On click effacer.
     */
    @FXML
    void onClickEffacer() {

        lb_username.setText("");
        lb_password.setText("");

    }

    /**
     * On click login.
     *
     * @param event the event
     * @throws IOException the io exception
     */
    @FXML
    void onClickLogin(ActionEvent event) throws IOException {
        if (lb_username.getText().equals("root") && lb_password.getText().equals("password")) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setContentText("Welcome to the scrawler you can now proceed");
            alert.close();
            //alert.showAndWait();
            FXMLLoader fxmlLoader = new FXMLLoader(VinylApplication.class.getResource("vinyl-view.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 960, 691);
            Stage email = new Stage();
            //email.setTitle("Envoie Couriel");
            email.setScene(scene);
            email.show();

        } else {
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setContentText("Username or password is incorrect ");
            errorLabel.setText("Username or password is incorrect ");
            errorAlert.showAndWait();

        }

    }

    /**
     * On click btn effacer.
     *
     * @param event the event
     */
    @FXML
    void onClickBtnEffacer(ActionEvent event) {
        titre.setText("");
        datePick.setValue(null);
        minprix.setText("");
        maxprix.setText("");
        discogs.setSelected(false);
        fnac.setSelected(false);
        vicorner.setSelected(false);
        lbcoin.setSelected(false);
        mesvil.setSelected(false);
        cfactory.setSelected(false);
        genreCheck.getSelectionModel().select(0);

    }






//    @FXML
//    void onClickComboCheck(ActionEvent event) {
//        String selected = "";
//
//    }


    /**
     * On date check.
     *
     * @param event the event
     */
    @FXML
    void onDateCheck(ActionEvent event) {
        //to do a small control to prevent user to select future date

    }


    /**
     * Handle click.
     *
     * @throws Exception the exception
     */
    void handleClick() throws Exception {
        if (titre.getText().equals("")) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("le titre est obligatoire, veuillez sélectionner le titre");
            alert.showAndWait();

        } else if (genreCheck.getItems().isEmpty()) {
            System.out.println("No Item was selected");
        } else if (minprix.getText().equals("")) {
            System.out.println("Aucune valeur entrée pour le prix min ");
        } else if (maxprix.getText().equals("")) {
            System.out.println("Aucune valeur entrée pour le prix max");
        }

        if (fnac.isSelected() ){
            fnac.setSelected(true);

            System.out.println("Fnac is selected...");
        } else if (discogs.isSelected()) {
            discogs.setSelected(true);
            System.out.println("Discog is selected...");
        } else if (vicorner.isSelected()) {

            //VCorner vc = new VCorner();

            vicorner.setSelected(true);
        } else if (lbcoin.isSelected()){
            String bcorner = lebonCoin.search(titre.getText());
            lebonCoin.search(titre.getText());

            System.out.println(bcorner);
            //lbcoin.setSelected(true);


        } else if (mesvil.isSelected()) {
            mesvil.setSelected(true);

        } else if (cfactory.isSelected()) {
            cfactory.setSelected(true);
        } else  {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Sélectionnez l'url d'un site web");
            alert.showAndWait();
        }

    }

/*
    void displaySelected(){
//         rechercheResult.setText(titre.getText());
//        rechercheResult.appendText(genreCheck.getSelectionModel().getSelectedItem().toString());
//        rechercheResult.appendText(datePick.getValue().toString());
//        rechercheResult.appendText(minprix.getText().toString());
//        rechercheResult.appendText(maxprix.getText().toString());

 }fnac.isSelected()
  */

    /**
     * On click recherce array list.
     *
     * @return the array list
     * @throws Exception the exception
     */
    @FXML
    public ArrayList<ScrawlBluePrint> OnClickRecherce() throws Exception {
     //String mesvil = vinylScrawler.search(titre.getText());
     // rechercheResult.setText(mesvil);
        String output;

        output = "";
       if(fnac.isSelected()){
           String fnaac = scrawlFnac.ScrawlFnac(titre.getText());
           output += fnaac;

       }
        if (vicorner.isSelected()){
            result1 = vcorder.search(titre.getText());
           // String vcorner = VCorner.search().toString();
             //   output += vcorner;
        } if( lbcoin.isSelected()){
            String lbcoin = lebonCoin.search(titre.getText());
              output += lbcoin;

        } if (mesvil.isSelected()){
           String mvRes = scrawwlMesvil.search(titre.getText());
           output +=mvRes;
        } if (discogs.isSelected()) {

        } if(cfactory.isSelected()){
            String resCf = scrawfactory.ScrawlCFactory(titre.getText());
            output += resCf;
        }

        System.out.println(lbcoin);
        rechercheResult.setText(output);
        //this methods controls if the required items are check at program inception...
       handleClick();

        return result1;
    }


    /**
     * Quitter.
     *
     * @param actionEvent the action event
     */
    public void quitter(ActionEvent actionEvent) {
        Platform.exit();
    }


    /**
     * Enregistre fichier.
     *
     * @param actionEvent the action event
     */
    public void EnregistreFichier(ActionEvent actionEvent) {
        Label savedFile = new Label();
        savedFile.setText(rechercheResult.getText().toString());
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Text files (*.txt)", "*.txt");

        fileChooser.getExtensionFilters().add(extFilter);
        File selectedFile = fileChooser.showSaveDialog(null);
        if(selectedFile != null){
            //SaveFile(TextArea.getText(), selectedFile);
            SaveFile(savedFile.getText(), selectedFile);

        }
    }


    /**
     * Enregistre fichier to db.
     *
     * @throws IOException the io exception
     */
    public void EnregistreFichierToDB() throws IOException {

        BufferedWriter bw = null;
        try {

            String server = nomserveur.getText().toString();
            String dbname = nomdatabase.getText();
            String portn = nport.getText();
            String uname = username.getText();
            String passwd = password.getText();

            File file = new File("C:\\Users\\mnjie\\reports\\paeametresDonnees.txt");
            if (!file.exists()) {
                //file.createNewFile();
                file.delete();
            }

            FileWriter fw = new FileWriter(file);
            bw = new BufferedWriter(fw);
            bw.write(server + "\n");
            bw.write(dbname + "\n");
            bw.write(portn + "\n");
            bw.write(uname + "\n");
            bw.write(passwd + "\n");
            System.out.println("File written Successfully");

        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        finally
        {
            try{
                if(bw!=null)
                    bw.close();
            }catch(Exception ex){
                System.out.println("Error in closing the BufferedWriter"+ex);
            }
        }

        PopSceneDonne();

    }


    /**
     * Read parametres donnes.
     */
    public void ReadParametresDonnes(){


    }



    private void SaveFile(String content, File file){
        try {
            FileWriter fileWriter;

            fileWriter = new FileWriter(file);
            fileWriter.write(content);
            fileWriter.close();
        } catch (IOException ex) {
            ex.printStackTrace();
            System.out.println("Error Occured writing to file");
        }

    }


    /**
     * Enregister base donne.
     */
    public void EnregisterBaseDonne() {

        DBUtils.ScrawlDataSaving();


    }


    /**
     * On click fermer donnees.
     *
     * @param actionEvent the action event
     */
    public void OnClickFermerDonnees(ActionEvent actionEvent) {
        nomserveur.setText("");
        nomdatabase.setText("");
        nport.setText("");
        username.setText("");
        password.setText("");



    }

    /**
     * Serveur nom verification.
     *
     * @param actionEvent the action event
     */
    public void serveurNomVerification(ActionEvent actionEvent) {


    }

    /**
     * Db nom verification.
     *
     * @param actionEvent the action event
     */
    public void dbNomVerification(ActionEvent actionEvent) {


    }

    /**
     * Port number verification.
     *
     * @param actionEvent the action event
     * @throws IOException the io exception
     */
    public void portNumberVerification(ActionEvent actionEvent) throws IOException {
        if (!nport.getText().equals("3306")){
            PopScene();
        }


    }

    /**
     * Username verification.
     *
     * @param actionEvent the action event
     */
    public void usernameVerification(ActionEvent actionEvent) {

    }

    /**
     * Password verification.
     *
     * @param actionEvent the action event
     */
    public void passwordVerification(ActionEvent actionEvent) {

    }


    /**
     * Connect base donnee.
     */
    public static void connectBaseDonnee() {



    }


    /**
     * On click send email.
     *
     * @param event the event
     * @throws IOException the io exception
     */
    public void OnClickSendEmail(ActionEvent event) throws IOException {

        String toSendEmail = emailAddress.getText();
        String toSendFile = "report";


        if (emailAddress.getText().equals("")){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Email is required...");
            alert.showAndWait();

        } else {
            sm.sendMail(toSendEmail, toSendFile);
           // Alert msg = new Alert(Alert.AlertType.CONFIRMATION);
           // msg.setContentText("L'email a été envoyé avec succès");
           // msg.showAndWait();
            PopSceneEmail();
        }

        // Sending the email
    }


    /**
     * On click annule.
     *
     * @param event the event
     */
    public void OnClickAnnule(ActionEvent event) {
        emailAddress.setText("");

    }


    /**
     * Parametres donne.
     *
     * @param event the event
     * @throws IOException the io exception
     */
    public void parametresDonne(ActionEvent event) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(VinylApplication.class.getResource("basedonnees.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 703, 462);
        Stage bdd = new Stage();
        bdd.setTitle("Enregistre Dans la base de donnees");
        bdd.setScene(scene);
        bdd.show();





    }

    /**
     * On click valider donnees.
     *
     * @param actionEvent the action event
     * @throws IOException the io exception
     */
    public void OnClickValiderDonnees(ActionEvent actionEvent) throws IOException {

       //saveDataFromParametre(nomserveur.getText(), nomdatabase.getText(), nport.getText(), username.getText(), password.getText() );

        //DBUtils.setDBParameters(dbHost, dbName, portNumber, userName, passwd);
       // saveDBParamToFile();
        EnregistreFichierToDB();

    }

    /**
     * Pop scene.
     *
     * @throws IOException the io exception
     */
    public void PopScene() throws IOException {
        Stage popupwindow=new Stage();
        popupwindow.initModality(Modality.APPLICATION_MODAL);
        popupwindow.setTitle("Popup");
        Label label1= new Label("Les info bien enregistrer ");
        Button button1= new Button("fermer");
        button1.setOnAction(e -> popupwindow.close());
        VBox layout= new VBox(10);
        layout.getChildren().addAll(label1,button1);
        layout.setAlignment(Pos.CENTER);
        Scene scene1= new Scene(layout, 300, 250);
        popupwindow.setScene(scene1);
        popupwindow.showAndWait();
    }

    /**
     * Pop scene email.
     *
     * @throws IOException the io exception
     */
    public void PopSceneEmail() throws IOException {
        Stage popupwindow=new Stage();
        popupwindow.initModality(Modality.APPLICATION_MODAL);
        popupwindow.setTitle("Popup");
        Label label1= new Label("L'email envoyer avec succées ");
        Button button1= new Button("fermer");
        button1.setOnAction(e -> popupwindow.close());
        VBox layout= new VBox(10);
        layout.getChildren().addAll(label1,button1);
        layout.setAlignment(Pos.CENTER);
        Scene scene1= new Scene(layout, 300, 250);
        popupwindow.setScene(scene1);
        popupwindow.showAndWait();
    }


    /**
     * Pop scene donne.
     *
     * @throws IOException the io exception
     */
    public void PopSceneDonne() throws IOException {
        Stage popupwindow=new Stage();
        popupwindow.initModality(Modality.APPLICATION_MODAL);
        popupwindow.setTitle("Popup");
        Label label1= new Label("File written Successfully ");
        Button button1= new Button("fermer");
        button1.setOnAction(e -> popupwindow.close());
        VBox layout= new VBox(10);
        layout.getChildren().addAll(label1,button1);
        layout.setAlignment(Pos.CENTER);
        Scene scene1= new Scene(layout, 300, 250);
        popupwindow.setScene(scene1);
        popupwindow.showAndWait();
    }


    /**
     * Gets date pick.
     *
     * @return the date pick
     */
    public DatePicker getDatePick() {
        return datePick;
    }

    /**
     * Sets date pick.
     *
     * @param datePick the date pick
     */
    public void setDatePick(DatePicker datePick) {
        this.datePick = datePick;
    }

    /**
     * Gets discogs.
     *
     * @return the discogs
     */
    public CheckBox getDiscogs() {
        return discogs;
    }

    /**
     * Sets discogs.
     *
     * @param discogs the discogs
     */
    public void setDiscogs(CheckBox discogs) {
        this.discogs = discogs;
    }

    /**
     * Gets effacher.
     *
     * @return the effacher
     */
    public Button getEffacher() {
        return effacher;
    }

    /**
     * Sets effacher.
     *
     * @param effacher the effacher
     */
    public void setEffacher(Button effacher) {
        this.effacher = effacher;
    }

    /**
     * Gets fnac.
     *
     * @return the fnac
     */
    public CheckBox getFnac() {
        return fnac;
    }

    /**
     * Sets fnac.
     *
     * @param fnac the fnac
     */
    public void setFnac(CheckBox fnac) {
        this.fnac = fnac;
    }

    /**
     * Gets lbcoin.
     *
     * @return the lbcoin
     */
    public CheckBox getLbcoin() {
        return lbcoin;
    }

    /**
     * Sets lbcoin.
     *
     * @param lbcoin the lbcoin
     */
    public void setLbcoin(CheckBox lbcoin) {
        this.lbcoin = lbcoin;
    }

    /**
     * Gets maxprix.
     *
     * @return the maxprix
     */
    public TextField getMaxprix() {
        return maxprix;
    }

    /**
     * Sets maxprix.
     *
     * @param maxprix the maxprix
     */
    public void setMaxprix(TextField maxprix) {
        this.maxprix = maxprix;
    }

    /**
     * Gets mesvil.
     *
     * @return the mesvil
     */
    public CheckBox getMesvil() {
        return mesvil;
    }

    /**
     * Sets mesvil.
     *
     * @param mesvil the mesvil
     */
    public void setMesvil(CheckBox mesvil) {
        this.mesvil = mesvil;
    }

    /**
     * Gets minprix.
     *
     * @return the minprix
     */
    public TextField getMinprix() {
        return minprix;
    }

    /**
     * Sets minprix.
     *
     * @param minprix the minprix
     */
    public void setMinprix(TextField minprix) {
        this.minprix = minprix;
    }

    /**
     * Gets titre.
     *
     * @return the titre
     */
    public TextField getTitre() {
        return titre;
    }

    /**
     * Sets titre.
     *
     * @param titre the titre
     */
    public void setTitre(TextField titre) {
        this.titre = titre;
    }

    /**
     * Gets vicorner.
     *
     * @return the vicorner
     */
    public CheckBox getVicorner() {
        return vicorner;
    }

    /**
     * Sets vicorner.
     *
     * @param vicorner the vicorner
     */
    public void setVicorner(CheckBox vicorner) {
        this.vicorner = vicorner;
    }

    /**
     * Gets genre check.
     *
     * @return the genre check
     */
    public ComboBox<String> getGenreCheck() {
        return genreCheck;
    }

    /**
     * Sets genre check.
     *
     * @param genreCheck the genre check
     */
    public void setGenreCheck(ComboBox<String> genreCheck) {
        this.genreCheck = genreCheck;
    }


    /**
     * Send email.
     *
     * @param event the event
     * @throws IOException the io exception
     */
    public void SendEmail(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(VinylApplication.class.getResource("courielview.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 541, 310);
        Stage email = new Stage();
        email.setTitle("Envoie Couriel");
        email.setScene(scene);
        email.show();

    }

    /**
     * Validate send bdd.
     *
     * @param event the event
     */
    public void validateSendBDD(ActionEvent event) {



    }

    /**
     * Anuller send bdd.
     *
     * @param event the event
     */
    public void anullerSendBDD(ActionEvent event) {


    }

    /**
     * On click combo check.
     *
     * @param event the event
     */
    public void onClickComboCheck(ActionEvent event) {

    }

    public void FermerModeDemploi(ActionEvent event) {
        Platform.exit();
    }

    public void DisplayModeEmploi(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(VinylApplication.class.getResource("mod-emploi.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 659, 654);
        Stage bdd = new Stage();
        bdd.setTitle("Mode D'emploi");
        bdd.setScene(scene);
        bdd.show();

    }
}

