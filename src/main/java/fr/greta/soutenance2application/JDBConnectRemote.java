package fr.greta.soutenance2application;


import java.sql.*;

/**
 * The type Jdb connect remote.
 */
public class JDBConnectRemote {

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     * @throws SQLException the sql exception
     */
    public static void main(String[] args) throws SQLException {

            // get connection to the database
            Connection connection = DriverManager.getConnection("jdbc:mysql://109.234.161.28/gretaxao_momodou-njie", "gretaxao_momodou-njie", "MNjie2022!");
            // create a statement
            Statement statement = connection.createStatement();


            // execute sql query
        ResultSet result = statement.executeQuery("Select * from test");
            //ResultSet result = statement.executeQuery("select * from  recherche r inner join genre g on r.id_genre = g.id_Genre ");
            // Process the result

            while (result.next()) {
                //System.out.println(result.getString("Titre") + ", " + (result.getString("Description")) + ", " + (result.getString("Prix")) + ", " + (result.getString("Annee")));
                System.out.println("result");
            }




    }

}
