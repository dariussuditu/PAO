package Repository;

import DataBaseConfig.DatabaseConfiguration;
import Domain.Tranzactie;
import Domain.contBancar;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class TranzactieRepository {

    private final ContBancarRepository contBancarRepository = new ContBancarRepository();

    public void insert(Tranzactie tranzactie) {
        String insertTranzactieSql = "INSERT INTO TRANZACTIE (idContEmitator, idContReceptor, suma, data) VALUES (?, ?, ?, ?)";
        Connection conn = DatabaseConfiguration.getDatabaseConnection();

        try {
            conn.setAutoCommit(false); // Begin transaction

            Scanner scanner = new Scanner(System.in);
            System.out.println("Numarul contului emitator este : ");
            String nrContEmitator = scanner.nextLine();
            int idContEmitator = contBancarRepository.getIdByNrCont(nrContEmitator);

            System.out.println("Numarul contului receptor este : ");
            String nrContReceptor = scanner.nextLine();
            int idContReceptor = contBancarRepository.getIdByNrCont(nrContReceptor);

            // Fetch current balances
            Optional<contBancar> contEmitatorOpt = contBancarRepository.getById(idContEmitator);
            Optional<contBancar> contReceptorOpt = contBancarRepository.getById(idContReceptor);

            if (contEmitatorOpt.isPresent() && contReceptorOpt.isPresent()) {
                contBancar contEmitator = contEmitatorOpt.get();
                contBancar contReceptor = contReceptorOpt.get();

                // Calculate new balances
                double newSoldEmitator = contEmitator.getSold() - tranzactie.getSuma();
                double newSoldReceptor = contReceptor.getSold() + tranzactie.getSuma();

                // Update transaction
                PreparedStatement preparedStatement = conn.prepareStatement(insertTranzactieSql);
                preparedStatement.setInt(1, idContEmitator);
                preparedStatement.setInt(2, idContReceptor);
                preparedStatement.setDouble(3, tranzactie.getSuma());
                preparedStatement.setDate(4, Date.valueOf(tranzactie.getData()));
                preparedStatement.executeUpdate();

                // Update balances
                contEmitator.setSold(newSoldEmitator);
                contReceptor.setSold(newSoldReceptor);
                contBancarRepository.update(contEmitator.getNumarCont(), contEmitator);
                contBancarRepository.update(contReceptor.getNumarCont(), contReceptor);

                conn.commit(); // Commit transaction
                System.out.println("Transaction inserted and balances updated successfully: " + tranzactie);
            } else {
                System.out.println("One of the accounts does not exist.");
                conn.rollback(); // Rollback transaction in case of failure
            }
        } catch (SQLException e) {
            try {
                conn.rollback(); // Rollback transaction if any error occurs
            } catch (SQLException rollbackException) {
                rollbackException.printStackTrace();
            }
            e.printStackTrace();
        } finally {
            try {
                conn.setAutoCommit(true); // Set auto-commit back to true
            } catch (SQLException autoCommitException) {
                autoCommitException.printStackTrace();
            }
        }
    }

    public Optional<Tranzactie> getById(int id) {
        String selectSql = "SELECT * FROM TRANZACTIE WHERE id = ?";
        Connection conn = DatabaseConfiguration.getDatabaseConnection();

        try {
            PreparedStatement preparedStatement = conn.prepareStatement(selectSql);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            return mapToTranzactie(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return Optional.empty();
    }

    public List<Tranzactie> getAllTranzactii() {
        List<Tranzactie> tranzactii = new ArrayList<>();
        String selectAllSql = "SELECT * FROM TRANZACTIE";
        Connection conn = DatabaseConfiguration.getDatabaseConnection();

        try {
            PreparedStatement preparedStatement = conn.prepareStatement(selectAllSql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                mapToTranzactie(resultSet).ifPresent(tranzactii::add);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return tranzactii;
    }

    private Optional<Tranzactie> mapToTranzactie(ResultSet resultSet) throws SQLException {
        if (resultSet.next()) {
            int idContEmitator = resultSet.getInt("idContEmitator");
            int idContReceptor = resultSet.getInt("idContReceptor");
            double suma = resultSet.getDouble("suma");
            Date data = resultSet.getDate("data");

            Optional<contBancar> contEmitator = contBancarRepository.getById(idContEmitator);
            Optional<contBancar> contReceptor = contBancarRepository.getById(idContReceptor);

            if (contEmitator.isPresent() && contReceptor.isPresent()) {
                return Optional.of(new Tranzactie(contEmitator.get(), contReceptor.get(), suma));
            }
        }
        return Optional.empty();
    }
}
