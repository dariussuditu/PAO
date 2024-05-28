package Repository;

import DataBaseConfig.DatabaseConfiguration;
import Domain.Card;
import Domain.contBancar;
import Domain.Persoana;
import Servicii.ServiciiCont;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class CardRepository {

    private final ContBancarRepository contBancarRepository = new ContBancarRepository();
    private final PersoanaRepository persoanaRepository = new PersoanaRepository();


    public void insert(Card card) {
        String insertCardSql = "INSERT INTO CARD (numarCard, dataExpirare, codCVV, idContAsociat) VALUES (?, ?, ?, ?)";
        Connection conn = DatabaseConfiguration.getDatabaseConnection();

        try {
            PreparedStatement preparedStatement = conn.prepareStatement(insertCardSql);
            preparedStatement.setString(1, card.getNumarCard());
            preparedStatement.setDate(2, java.sql.Date.valueOf(card.getDataExpirare()));
            preparedStatement.setString(3, card.getCodCVV());

            Scanner scanner = new Scanner(System.in);
            System.out.println("Numarul contului este : ");
            String nrCont = scanner.nextLine();

            // Check if the account already exists in the database
            int idCont = contBancarRepository.getIdByNrCont(nrCont);

            if (idCont == -1) {
                // If the account doesn't exist, insert it into the CONTBANCAR table
                ServiciiCont serviciiCont = new ServiciiCont();
                contBancar cb = serviciiCont.adaugaCont(); // Assuming adaugaCont() method returns a contBancar object
                contBancarRepository.insert(cb,serviciiCont);

                // Get the ID of the newly inserted account
                idCont = contBancarRepository.getIdByNrCont(cb.getNumarCont());
            }

            // Set the ID of the associated account in the PreparedStatement
            preparedStatement.setInt(4, idCont);

            preparedStatement.executeUpdate();

            System.out.println("Card inserted successfully: " + card);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }




    public Optional<Card> getByNumarCard(String numarCard) {
        String selectSql = "SELECT * FROM CARDURI WHERE numarCard = ?";
        Connection conn = DatabaseConfiguration.getDatabaseConnection();

        try {
            PreparedStatement preparedStatement = conn.prepareStatement(selectSql);
            preparedStatement.setString(1, numarCard);
            ResultSet resultSet = preparedStatement.executeQuery();
            return mapToCard(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return Optional.empty();
    }

    public List<Card> getAllCards() {
        List<Card> cards = new ArrayList<>();
        String selectAllSql = "SELECT * FROM CARDURI";
        Connection conn = DatabaseConfiguration.getDatabaseConnection();

        try {
            PreparedStatement preparedStatement = conn.prepareStatement(selectAllSql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String numarCard = resultSet.getString("numarCard");
                int idTitular = resultSet.getInt("titular");
                LocalDate dataExpirare = resultSet.getDate("dataExpirare").toLocalDate();
                String codCVV = resultSet.getString("codCVV");
                int idContAsociat = resultSet.getInt("idContAsociat");

                Optional<Persoana> titular = persoanaRepository.getById(idTitular);
                Optional<contBancar> contAsociat = contBancarRepository.getById(idContAsociat);

                if (titular.isPresent() && contAsociat.isPresent()) {
                    // Adjust this line according to the actual constructor of the Card class
                    Card card = new Card(numarCard, titular.get(), codCVV, contAsociat.get());
                    cards.add(card);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return cards;
    }


    private Optional<Card> mapToCard(ResultSet resultSet) throws SQLException {
        if (resultSet.next()) {
            String numarCard = resultSet.getString("numarCard");
            int idTitular = resultSet.getInt("titular");
            LocalDate dataExpirare = resultSet.getDate("dataExpirare").toLocalDate();
            String codCVV = resultSet.getString("codCVV");
            int idContAsociat = resultSet.getInt("idContAsociat");

            Optional<Persoana> titular = persoanaRepository.getById(idTitular);
            Optional<contBancar> contAsociat = contBancarRepository.getById(idContAsociat);

            if (titular.isPresent() && contAsociat.isPresent()) {
                // Adjust this line according to the actual constructor of the Card class
                Card card = new Card(numarCard, titular.get(), codCVV, contAsociat.get());
                return Optional.of(card);
            }
        }
        return Optional.empty();
    }

}
