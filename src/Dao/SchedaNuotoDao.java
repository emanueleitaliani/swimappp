package Dao;

import Model.SchedaNuotoModel;
import java.util.List;

public interface SchedaNuotoDao {
    void insertScheda(SchedaNuotoModel scheda);
    List<SchedaNuotoModel> getAllSchede();
    SchedaNuotoModel getSchedaById(String idScheda);
    void updateScheda(SchedaNuotoModel scheda);
    void deleteScheda(String idScheda);
}
