package com.paybuc.database;
import com.paybuc.database.DatabaseConnector;
import com.paybuc.pojos.PowerballDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DataAccessObject {
    Connection connection;

    public DataAccessObject(){
        try {
            connection = DatabaseConnector.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    private static final String INSERT_POWERBALL_SQL = "INSERT INTO Powerball (date, one, two, three, four,five, bonus) VALUES (?, ?, ?, ?, ?, ?, ?)";

    public void insertPowerball(PowerballDto powerball) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(INSERT_POWERBALL_SQL)) {

            preparedStatement.setDate(1, new java.sql.Date(powerball.getDate().getTime()));
            preparedStatement.setInt(2, powerball.getOne());
            preparedStatement.setInt(3, powerball.getTwo());
            preparedStatement.setInt(4, powerball.getThree());
            preparedStatement.setInt(5, powerball.getFour());
            preparedStatement.setInt(6, powerball.getFive());
            preparedStatement.setInt(7, powerball.getBonus());

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
