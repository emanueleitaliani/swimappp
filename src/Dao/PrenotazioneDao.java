package Dao;
import Model.PrenotazioneModel;
import Other.Connect;
import Other.Stampa;
import Query.QueryLezioni;
import java.sql.*;


public class PrenotazioneDao{
    public void prenota(PrenotazioneModel prenotazioneModel) throws SQLException {
        /*
        fa la query per inserire la richiesta di ripetizione nel database
         */
        Connection connection;
        Statement stmt = null;

        try{
            connection = Connect.getInstance().getDBConnection();
            stmt = connection.createStatement();

            QueryLezioni.PrenotaLezione(stmt, prenotazioneModel);

        } catch (SQLException e) {
            handleDAOException(e);
        } finally {
            // Chiusura delle risorse
            closeResources(stmt,null);
        }
    }
    private void closeResources(Statement stmt, ResultSet rs) {
        try {
            if (rs != null) {
                rs.close();
            }
            if (stmt != null) {
                stmt.close();
            }
        } catch (SQLException e) {
            handleDAOException(e);
        }
    }


    private void handleDAOException(Exception e) {
        Stampa.errorPrint(String.format("PrenotazioneDAO: %s", e.getMessage()));
    }

}
