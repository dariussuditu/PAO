package Repository;

import DataBaseConfig.DatabaseConfiguration;
import Domain.Persoana;
import Domain.contBancar;
import Servicii.ServiciiCont;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class ContBancarRepository {

    private final PersoanaRepository persoanaRepository = new PersoanaRepository();

    public void insert(contBancar contBancar,ServiciiCont serviciiCont) {
        String insertContBancarSql = "INSERT INTO CONTBANCAR (numarCont, suma, idPersoana) VALUES (?, ?, ?)";
        Connection conn = DatabaseConfiguration.getDatabaseConnection();

        try {
            // Begin transaction
            conn.setAutoCommit(false);

            // Insert the bank account
            PreparedStatement preparedStatement = conn.prepareStatement(insertContBancarSql);
            preparedStatement.setString(1, contBancar.getNumarCont());
            preparedStatement.setDouble(2, contBancar.getSold());

            // Read the name of the person from the console
            Scanner scanner = new Scanner(System.in);
            System.out.println("Numele persoanei este: ");
            String nume = scanner.nextLine();

            // Check if the person with the same name already exists in the database
            PersoanaRepository persoanaRepository = new PersoanaRepository();
            int idPersoana = persoanaRepository.getIdByName(nume);

            if (idPersoana == -1) {
                // If the person doesn't exist, insert it into the PERSOANA table
                //ServiciiCont serviciiCont = new ServiciiCont();
                Persoana persoana = serviciiCont.adaugaPersoana(); // Assuming you have a constructor for name only
                persoanaRepository.insert(persoana,serviciiCont);

                // Get the ID of the newly inserted person
                idPersoana = persoanaRepository.getIdByName(nume);
            }

            // Set the ID of the person in the PreparedStatement
            preparedStatement.setInt(3, idPersoana);

            // Execute the SQL query to insert the bank account
            preparedStatement.executeUpdate();

            // Commit the transaction
            conn.commit();
            serviciiCont.getListaConturi().add(contBancar);
            System.out.println("Bank account inserted successfully: " + contBancar);
        } catch (SQLException e) {
            // Rollback the transaction if any error occurs
            try {
                conn.rollback();
            } catch (SQLException rollbackException) {
                rollbackException.printStackTrace();
            }
            e.printStackTrace();
        } finally {
            // Set auto-commit back to true
            try {
                conn.setAutoCommit(true);
            } catch (SQLException autoCommitException) {
                autoCommitException.printStackTrace();
            }
        }
    }




    public Optional<contBancar> getById(int id) {
        String selectSql = "SELECT * FROM CONTBANCAR WHERE id = ?";
        Connection conn = DatabaseConfiguration.getDatabaseConnection();

        try {
            PreparedStatement preparedStatement = conn.prepareStatement(selectSql);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            return mapToContBancar(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return Optional.empty();
    }

    public Optional<contBancar> getByNumarCont(String numarCont) {
        String selectSql = "SELECT * FROM CONTBANCAR WHERE numarCont = ?";
        Connection conn = DatabaseConfiguration.getDatabaseConnection();

        try {
            PreparedStatement preparedStatement = conn.prepareStatement(selectSql);
            preparedStatement.setString(1, numarCont);
            ResultSet resultSet = preparedStatement.executeQuery();
            return mapToContBancar(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return Optional.empty();
    }

    public int getIdByNrCont(String nrCont) {
        String selectSql = "SELECT id FROM CONTBANCAR WHERE numarCont = ?";
        Connection conn = DatabaseConfiguration.getDatabaseConnection();

        try {
            PreparedStatement preparedStatement = conn.prepareStatement(selectSql);
            preparedStatement.setString(1, nrCont);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt("id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return -1; // Return -1 if the ID is not found or an error occurs
    }

    public List<contBancar> getAllCont() {
        List<contBancar> conturi = new ArrayList<>();
        String selectAllSql = "SELECT * FROM CONTBANCAR";
        Connection conn = DatabaseConfiguration.getDatabaseConnection();

        try {
            PreparedStatement preparedStatement = conn.prepareStatement(selectAllSql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String numarCont = resultSet.getString("numarCont");
                double suma = resultSet.getDouble("suma");
                int idPersoana = resultSet.getInt("idPersoana");
                Optional<Persoana> detinatorCont = persoanaRepository.getById(idPersoana);
                if (detinatorCont.isPresent()) {
                    contBancar cb = new contBancar(numarCont, detinatorCont.get(), suma);
                    conturi.add(cb);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return conturi;
    }


    public void update(String numarCont, contBancar contBancar) {
        String updateSql = "UPDATE CONTBANCAR SET numarCont = ?, suma = ?, idPersoana = ? WHERE numarCont = ?";
        Connection conn = DatabaseConfiguration.getDatabaseConnection();

        try {
            Optional<contBancar> existingContBancar = getByNumarCont(numarCont);
            if (existingContBancar.isPresent()) {
                PreparedStatement preparedStatement = conn.prepareStatement(updateSql);
                preparedStatement.setString(1, contBancar.getNumarCont());
                preparedStatement.setDouble(2, contBancar.getSold());
                preparedStatement.setString(3, contBancar.getDetinatorCont().getCnp());
                preparedStatement.setString(4, numarCont);
                int rowsUpdated = preparedStatement.executeUpdate();
                if (rowsUpdated > 0) {
                    System.out.println("Bank account updated successfully: " + contBancar);
                } else {
                    System.out.println("No bank account found with account number: " + numarCont);
                }
            } else {
                System.out.println("No bank account found with account number: " + numarCont);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(String numarCont) {
        String deleteSql = "DELETE FROM CONTBANCAR WHERE numarCont = ?";
        Connection conn = DatabaseConfiguration.getDatabaseConnection();

        try {
            PreparedStatement preparedStatement = conn.prepareStatement(deleteSql);
            preparedStatement.setString(1, numarCont);
            int rowsDeleted = preparedStatement.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("Bank account deleted successfully with account number: " + numarCont);
            } else {
                System.out.println("No bank account found with account number: " + numarCont);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Optional<contBancar> mapToContBancar(ResultSet resultSet) throws SQLException {
        if (resultSet.next()) {
            String numarCont = resultSet.getString("numarCont");
            double suma = resultSet.getDouble("suma");
            int idPersoana = resultSet.getInt("idPersoana");
            Optional<Persoana> detinatorCont = persoanaRepository.getById(idPersoana);
            if (detinatorCont.isPresent()) {
                return Optional.of(new contBancar(numarCont, detinatorCont.get(), suma));
            }
        }
        return Optional.empty();
    }
}
