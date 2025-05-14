package Other;
import Dao.UserDAOJSON;
import Dao.UserDao;
import Dao.UserDaoMYSQL;


import java.io.IOException;
import java.util.Properties;
import java.io.FileInputStream;
public class FactoryDao {
    private static final String CONFIG_FILE = "resources/resources/config.properties";
    private static final Properties properties = new Properties();
    private FactoryDao() {}

    public static UserDao getUserDAO() {
        try (FileInputStream input = new FileInputStream(CONFIG_FILE)) {
            properties.load(input);

        } catch (IOException ex) {
            Stampa.errorPrint("Impossibile aprire il file di configurazione.");
        }

        String daoType = properties.getProperty("persistence.type");
        if ("mysql".equalsIgnoreCase(daoType)) {
            return new UserDaoMYSQL();
        } else if ("json".equalsIgnoreCase(daoType)) {
            return new UserDAOJSON();
        }
        return null;
    }
}