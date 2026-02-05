package Dao;

import Model.SchedaNuotoModel;
import Other.Connect;
import Other.Stampa;
import Query.QuerySchedaNuoto;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SchedaNuotoDaoMYSQL implements SchedaNuotoDao {

    @Override
    public void insertScheda(SchedaNuotoModel scheda) {
        Connection connection = null;
        Statement stmt = null;
        try {
            connection = Connect.getInstance().getDBConnection();
            stmt = connection.createStatement();

            // La query effettiva è in QuerySchedaNuoto
            QuerySchedaNuoto.inserisciScheda(stmt, scheda);

        } catch (SQLException e) {
            handleDAOException(e);
        } finally {
            closeResources(stmt, null);
        }
    }

    @Override
    public void updateScheda(SchedaNuotoModel scheda) {
        Connection connection = null;
        Statement stmt = null;
        try {
            connection = Connect.getInstance().getDBConnection();
            stmt = connection.createStatement();
            QuerySchedaNuoto.aggiornaScheda(stmt, scheda);
        } catch (SQLException e) {
            handleDAOException(e);
        } finally {
            closeResources(stmt, null);
        }
    }

    @Override
    public void deleteScheda(String idScheda) {
        Connection connection = null;
        Statement stmt = null;
        try {
            connection = Connect.getInstance().getDBConnection();
            stmt = connection.createStatement();
            QuerySchedaNuoto.cancellaScheda(stmt, idScheda);
        } catch (SQLException e) {
            handleDAOException(e);
        } finally {
            closeResources(stmt, null);
        }
    }

    @Override
    public SchedaNuotoModel getSchedaById(String idScheda) {
        Connection connection = null;
        Statement stmt = null;
        ResultSet rs = null;
        SchedaNuotoModel scheda = null;

        try {
            connection = Connect.getInstance().getDBConnection();
            stmt = connection.createStatement();
            rs = QuerySchedaNuoto.cercaSchedaById(stmt, idScheda);

            if (rs.next()) {
                scheda = new SchedaNuotoModel(
                        rs.getString("idScheda"),
                        rs.getInt("distanzaTotale"),
                        rs.getInt("durata"),
                        rs.getString("livello")
                );
            }

        } catch (SQLException e) {
            handleDAOException(e);
        } finally {
            closeResources(stmt, rs);
        }

        return scheda;
    }

    @Override
    public List<SchedaNuotoModel> getAllSchede() {
        Connection connection = null;
        Statement stmt = null;
        ResultSet rs = null;
        List<SchedaNuotoModel> schede = new ArrayList<>();

        try {
            connection = Connect.getInstance().getDBConnection();
            stmt = connection.createStatement();
            rs = QuerySchedaNuoto.getAllSchede(stmt);

            while (rs.next()) {
                schede.add(new SchedaNuotoModel(
                        rs.getString("idScheda"),
                        rs.getInt("distanzaTotale"),
                        rs.getInt("durata"),
                        rs.getString("livello")
                ));
            }

        } catch (SQLException e) {
            handleDAOException(e);
        } finally {
            closeResources(stmt, rs);
        }

        return schede;
    }

    // --- Metodi di supporto ---
    private void closeResources(Statement stmt, ResultSet rs) {
        try {
            if (rs != null) rs.close();
            if (stmt != null) stmt.close();
        } catch (SQLException e) {
            handleDAOException(e);
        }
    }

    private void handleDAOException(Exception e) {
        Stampa.errorPrint("SchedaNuotoDAO: " + e.getMessage());
    }
}

