package Other;
import Dao.UserDao;
import Exceptions.PersnonvalidaException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class FactoryDao {
    private static final String CONFIG_FILE = "config.properties";
    private static final Properties properties = new Properties();

    private FactoryDao() {}

    public static UserDao getUserDAO(){
        try (InputStream input = FactoryDao.class.getClassLoader().getResourceAsStream(CONFIG_FILE)) {
            if (input == null) {
                Stampa.errorPrint("Impossibile trovare " + CONFIG_FILE);
                throw new PersnonvalidaException();
            } else {
                properties.load(input);
            }
        } catch (IOException ex) {
            Stampa.errorPrint("Impossibile aprire il file di configurazione.");
            throw new PersnonvalidaException();
        }

        String daoType = properties.getProperty("persistence.type");
        if ("MYSQL".equalsIgnoreCase(daoType)) {
            return new UserDAOMySQL();
        } else if ("JSON".equalsIgnoreCase(daoType)) {
            return new UserDAOJSON();
        } else {
            throw new PersnonvalidaException();
        }
    }
}