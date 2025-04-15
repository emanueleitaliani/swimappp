package Dao;

import Model.PrenotazioneModel;
import Exceptions.NullException;
import Bean.Utenteloggatobean;
import Other.Stampa;
import Other.Connect;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class PrenotazioneDao {

    public void prenotalezione(PrenotazioneModel prenotazioneModel) throws SQLException {

        Connection connection;
        Statement stmt = null;
        try {
            connection = Connect.getInstance().getDBConnection();
            stmt = connection.createStatement();

            QueryPrenotazione.richiediRipetiz(stmt, prenotazioneModel);

        } catch (SQLException e) {
            handleDAOException(e);
        } finally {
            // Chiusura delle risorse
            closeResources(stmt, null);
        }
    }
    public void recensioneMethod(int idPrenotazione,int recensione){

        Connection connection;
        Statement stmt;

        try {
            connection = Connect.getInstance().getDBConnection();
            stmt = connection.createStatement();

            QueryPrenotazione.recensione(stmt, idPrenotazione,recensione);

        } catch(SQLException e){
            handleDAOException(e);
        }
    }



    /** Metodo utilizzato per chiudere le risorse utilizzate */
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
