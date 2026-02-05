package Dao;

import Exceptions.UtentenonpresenteException;
import Model.RichiestaSchedaNuotoModel;
import Other.Connect;
import Other.Stampa;
import Other.StatoRichiestaScheda;
import Query.QueryRichiesteSchedaNuoto;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RichiestaSchedaNuotoDaoMYSQL implements RichiestaSchedaNuotoDao {

    @Override
    public void insertRichiesta(RichiestaSchedaNuotoModel richiesta) throws SQLException {
        Connection connection;
        Statement stmt = null;

        try {
            connection = Connect.getInstance().getDBConnection();
            stmt = connection.createStatement();
            QueryRichiesteSchedaNuoto.inserisciRichiesta(stmt, richiesta);

        } catch (SQLException e) {
            handleDAOException(e);
        } finally {
            closeResources(stmt, null);
        }
    }

    @Override
    public List<RichiestaSchedaNuotoModel> getRichiesteByEmailUser(String emailUser)
            throws SQLException, UtentenonpresenteException {

        List<RichiestaSchedaNuotoModel> lista = new ArrayList<>();
        Connection connection;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            connection = Connect.getInstance().getDBConnection();
            stmt = connection.createStatement();
            rs = QueryRichiesteSchedaNuoto.cercaRichiesteByEmailUser(stmt, emailUser);

            while (rs.next()) {
                RichiestaSchedaNuotoModel model = new RichiestaSchedaNuotoModel();
                model.setIdRichiesta(rs.getInt("idRichiesta"));
                model.setNome(rs.getString("nome"));
                model.setCognome(rs.getString("cognome"));
                model.setLivelloUtente(rs.getString("livelloUtente"));
                model.setEmailIstruttore(rs.getString("emailIstruttore"));
                model.setEmailUser(rs.getString("emailUser"));
                model.setInfo(rs.getString("info"));
                model.setDataRichiesta(rs.getDate("dataRichiesta").toLocalDate());

                // Controllo stato come in PrenotazioneDaoMYSQL
                String statoDalDB = rs.getString("status");
                if (statoDalDB != null) {
                    try {
                        model.setStatus(StatoRichiestaScheda.valueOf(statoDalDB.toUpperCase()));
                    } catch (IllegalArgumentException e) {
                        model.setStatus(StatoRichiestaScheda.INCORSO); // default sicuro
                    }
                } else {
                    model.setStatus(StatoRichiestaScheda.INCORSO);
                }

                lista.add(model);
            }

        } finally {
            if (rs != null) rs.close();
            closeResources(stmt, rs);
        }

        return lista;
    }

    @Override
    public List<RichiestaSchedaNuotoModel> getRichiesteByEmailIstruttore(String emailIstruttore)
            throws SQLException, UtentenonpresenteException {

        List<RichiestaSchedaNuotoModel> lista = new ArrayList<>();
        Connection connection;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            connection = Connect.getInstance().getDBConnection();
            stmt = connection.createStatement();
            rs = QueryRichiesteSchedaNuoto.cercaRichiesteByEmailIstruttore(stmt, emailIstruttore);

            while (rs.next()) {
                RichiestaSchedaNuotoModel model = new RichiestaSchedaNuotoModel();
                model.setIdRichiesta(rs.getInt("idRichiesta"));
                model.setNome(rs.getString("nome"));
                model.setCognome(rs.getString("cognome"));
                model.setLivelloUtente(rs.getString("livelloUtente"));
                model.setEmailIstruttore(rs.getString("emailIstruttore"));
                model.setEmailUser(rs.getString("emailUser"));
                model.setInfo(rs.getString("info"));
                model.setDataRichiesta(rs.getDate("dataRichiesta").toLocalDate());

                String statoDalDB = rs.getString("status");
                if (statoDalDB != null) {
                    try {
                        model.setStatus(StatoRichiestaScheda.valueOf(statoDalDB.toUpperCase()));
                    } catch (IllegalArgumentException e) {
                        model.setStatus(StatoRichiestaScheda.INCORSO);
                    }
                } else {
                    model.setStatus(StatoRichiestaScheda.INCORSO);
                }

                lista.add(model);
            }

        } finally {
            closeResources(stmt, rs);
        }

        return lista;
    }

    // Cancella richiesta
    public boolean deleteRichiesta(int idRichiesta, String emailUser)
            throws SQLException, UtentenonpresenteException {

        Connection connection;
        Statement stmt = null;
        boolean cancellata = false;

        try {
            connection = Connect.getInstance().getDBConnection();
            stmt = connection.createStatement();
            QueryRichiesteSchedaNuoto.cancellaRichiestaSchedaNuoto(stmt, idRichiesta, emailUser);
            cancellata = true;

        } catch (UtentenonpresenteException e) {
            Stampa.println("❌ Utente non presente: " + emailUser);
        } catch (SQLException e) {
            handleDAOException(e);
        }

        return cancellata;
    }

    @Override
    public void updateStato(int idRichiesta, StatoRichiestaScheda nuovoStato) throws SQLException {
        Connection connection;
        Statement stmt = null;

        try {
            connection = Connect.getInstance().getDBConnection();
            stmt = connection.createStatement();
            QueryRichiesteSchedaNuoto.aggiornaStatoRichiesta(stmt, idRichiesta, nuovoStato);

        } finally {
            closeResources(stmt, null);
        }
    }

    // --- Metodi di supporto per chiudere risorse e gestire eccezioni ---
    private void closeResources(Statement stmt, ResultSet rs) {
        try {
            if (rs != null) rs.close();
            if (stmt != null) stmt.close();
        } catch (SQLException e) {
            handleDAOException(e);
        }
    }

    private void handleDAOException(Exception e) {
        Stampa.errorPrint(String.format("RichiestaSchedaNuotoDAO: %s", e.getMessage()));
    }
}
