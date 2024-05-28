package DataBaseConfig;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class CreateTables {

    public void createTablePersoana() {
        String createTableSql = """
            CREATE TABLE IF NOT EXISTS PERSOANA (
                id INT PRIMARY KEY AUTO_INCREMENT,
                nume VARCHAR(100) NOT NULL,
                cnp VARCHAR(13) NOT NULL,
                adresa VARCHAR(255) NOT NULL,
                telefon VARCHAR(15) NOT NULL
            )
            """;

        Connection connection = DatabaseConfiguration.getDatabaseConnection();

        try {
            Statement statement = connection.createStatement();
            statement.execute(createTableSql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void createTableContBancar() {
        String createTableSql = """
            CREATE TABLE IF NOT EXISTS ContBancar (
                id INT PRIMARY KEY AUTO_INCREMENT,
                numarCont VARCHAR(20) NOT NULL,
                suma DOUBLE NOT NULL,
                idPersoana INT NOT NULL,
                FOREIGN KEY (idPersoana) REFERENCES PERSOANA (id)
            )
            """;

        Connection connection = DatabaseConfiguration.getDatabaseConnection();

        try {
            Statement statement = connection.createStatement();
            statement.execute(createTableSql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void createTableTranzactie() {
        String createTableSql = """
            CREATE TABLE IF NOT EXISTS TRANZACTIE (
                id INT PRIMARY KEY AUTO_INCREMENT,
                idContEmitator INT,
                idContReceptor INT,
                suma DOUBLE,
                data DATE,
                FOREIGN KEY (idContEmitator) REFERENCES ContBancar (id),
                FOREIGN KEY (idContReceptor) REFERENCES ContBancar (id)
            )
            """;

        Connection connection = DatabaseConfiguration.getDatabaseConnection();

        try {
            Statement statement = connection.createStatement();
            statement.execute(createTableSql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void createTableCard() {
        String createTableSql = """
            
                CREATE TABLE IF NOT EXISTS CARD (
                id INT PRIMARY KEY AUTO_INCREMENT,
                numarCard VARCHAR(20),
                dataExpirare DATE NOT NULL,
                codCVV VARCHAR(4) NOT NULL,
                idContAsociat INT,
                FOREIGN KEY (idContAsociat) REFERENCES ContBancar (id)
 
                          );
            
            """;

        Connection connection = DatabaseConfiguration.getDatabaseConnection();

        try {
            Statement statement = connection.createStatement();
            statement.execute(createTableSql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
