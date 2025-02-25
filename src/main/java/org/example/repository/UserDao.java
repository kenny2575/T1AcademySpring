package org.example.repository;

import org.example.model.User;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Repository
public class UserDao {


    private static final String INSERT_SQL = "INSERT INTO users (username) VALUES (?)";
    private static final String SELECT_BY_ID_SQL = "SELECT * FROM users WHERE id = ?";
    private static final String SELECT_ALL = "SELECT * FROM users";
    private static final String DELETE_BY_ID = "DELETE FROM users WHERE id = ?";

    private final DataSource dataSource;

    public UserDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void save(User user) {
        try (Connection con = dataSource.getConnection();
             PreparedStatement statement = con.prepareStatement(INSERT_SQL)) {
            statement.setObject(1, user.username());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Ошибка сохранения пользователя");
        }
    }

    public Optional<User> findById(Long id) {
        try (Connection con = dataSource.getConnection()) {
            PreparedStatement statement = con.prepareStatement(SELECT_BY_ID_SQL);
            statement.setLong(1, id);

            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                return Optional.of(new User(rs.getLong("id"), rs.getString("username")));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Ошибка получения пользователя");
        }
        return Optional.empty();
    }

    public List<User> findAll() {
        List<User> users = new ArrayList<>();
        try (Connection conn = dataSource.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(SELECT_ALL)) {
            while (rs.next()) {
                users.add(new User(rs.getLong("id"), rs.getString("username")));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Ошибка при получении списка пользователей", e);
        }
        return users;
    }

    public void updateUser(User user) {

    }

    public void delete(Long id) {
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(DELETE_BY_ID)) {
            stmt.setLong(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Ошибка при удалении пользователя", e);
        }
    }
}
