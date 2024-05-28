package Repository;

import DataBaseConfig.DatabaseConfiguration;
import Domain.Persoana;
import Servicii.ServiciiCont;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

public class PersoanaRepository {

    public void insert(Persoana persoana, ServiciiCont serviciiCont) {
        String insertPersoanaSql = "INSERT INTO PERSOANA (nume, cnp, adresa, telefon) VALUES (?, ?, ?, ?)";
        Connection conn = DatabaseConfiguration.getDatabaseConnection();

        try {
            PreparedStatement preparedStatement = conn.prepareStatement(insertPersoanaSql);
            preparedStatement.setString(1, persoana.getNume());
            preparedStatement.setString(2, persoana.getCnp());
            preparedStatement.setString(3, persoana.getAdresa());
            preparedStatement.setString(4, persoana.getTelefon());
            preparedStatement.executeUpdate();
            System.out.println("Person inserted successfully: " + persoana);

            // Add the newly inserted person to the listaPersoane of the provided ServiciiCont
            serviciiCont.getListaPersoane().add(persoana);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



    public Optional<Persoana> getById(int id) {
        String selectSql = "SELECT * FROM PERSOANA WHERE id = ?";
        Connection conn = DatabaseConfiguration.getDatabaseConnection();

        try {
            PreparedStatement preparedStatement = conn.prepareStatement(selectSql);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            return mapToPersoana(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return Optional.empty();
    }

    public Optional<Persoana> getByCnp(String cnp) {
        String selectSql = "SELECT * FROM PERSOANA WHERE cnp = ?";
        Connection conn = DatabaseConfiguration.getDatabaseConnection();

        try {
            PreparedStatement preparedStatement = conn.prepareStatement(selectSql);
            preparedStatement.setString(1, cnp);
            ResultSet resultSet = preparedStatement.executeQuery();
            return mapToPersoana(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return Optional.empty();
    }


    public int getIdByName(String name) {
        String selectSql = "SELECT id FROM PERSOANA WHERE nume = ?";
        Connection conn = DatabaseConfiguration.getDatabaseConnection();

        try {
            PreparedStatement preparedStatement = conn.prepareStatement(selectSql);
            preparedStatement.setString(1, name);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt("id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return -1; // Return -1 if the name is not found or an error occurs
    }
    public List<Persoana> getAllPersons() {
        List<Persoana> persons = new ArrayList<>();
        String selectAllSql = "SELECT * FROM PERSOANA";
        Connection conn = DatabaseConfiguration.getDatabaseConnection();

        try {
            PreparedStatement preparedStatement = conn.prepareStatement(selectAllSql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String nume = resultSet.getString("nume");
                String cnp = resultSet.getString("cnp");
                String adresa = resultSet.getString("adresa");
                String telefon = resultSet.getString("telefon");
                Persoana persoana = new Persoana(nume, cnp, adresa, telefon);
                persons.add(persoana);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return persons;
    }

    public void update(int id, Persoana persoana) {
        String updateSql = "UPDATE PERSOANA SET nume = ?, cnp = ?, adresa = ?, telefon = ? WHERE id = ?";
        Connection conn = DatabaseConfiguration.getDatabaseConnection();

        try {
            Optional<Persoana> existingPersoana = getById(id);
            if (existingPersoana.isPresent()) {
                PreparedStatement preparedStatement = conn.prepareStatement(updateSql);
                preparedStatement.setString(1, persoana.getNume());
                preparedStatement.setString(2, persoana.getCnp());
                preparedStatement.setString(3, persoana.getAdresa());
                preparedStatement.setString(4, persoana.getTelefon());
                preparedStatement.setInt(5, id);
                int rowsUpdated = preparedStatement.executeUpdate();
                if (rowsUpdated > 0) {
                    System.out.println("Person updated successfully: " + persoana);
                } else {
                    System.out.println("No person found with id: " + id);
                }
            } else {
                System.out.println("No person found with id: " + id);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(int id) {
        String deleteSql = "DELETE FROM PERSOANA WHERE id = ?";
        Connection conn = DatabaseConfiguration.getDatabaseConnection();

        try {
            PreparedStatement preparedStatement = conn.prepareStatement(deleteSql);
            preparedStatement.setInt(1, id);
            int rowsDeleted = preparedStatement.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("Person deleted successfully with id: " + id);
            } else {
                System.out.println("No person found with id: " + id);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Optional<Persoana> mapToPersoana(ResultSet resultSet) throws SQLException {
        if (resultSet.next()) {
            String nume = resultSet.getString("nume");
            String cnp = resultSet.getString("cnp");
            String adresa = resultSet.getString("adresa");
            String telefon = resultSet.getString("telefon");
            return Optional.of(new Persoana(nume, cnp, adresa, telefon));
        }
        return Optional.empty();
    }
}
