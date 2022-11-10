package fr.greta.soutenance2application;



import java.sql.*;
import java.util.logging.Logger;

/**
 * The type Db utils.
 */
public class DBUtils {


    /**
     * Scrawl data saving.
     */
    public static void ScrawlDataSaving(){


        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/scrawldata", "root", "");
            System.out.println("Connection Successful...");

            String query = "insert into recherche (titre, description, prix, id_rgenre, anne)  values (?,?,?,?,?)";
            PreparedStatement pst = conn.prepareStatement(query);
            pst.setString(1, "titre");
            pst.setString(2, "description");
            pst.setString(3, "prix");
            pst.setInt(4, 1);
            pst.setDate(5, new Date(1668002840));

            pst.executeUpdate();
            //int count = pst.executeUpdate(query);
            //System.out.println("rows updated: " + count);

        } catch(ClassNotFoundException e) {
            Logger.getLogger(DBUtils.class.getName());
            e.printStackTrace();
        } catch (SQLException e) {

            e.printStackTrace();
        }


    }




}
