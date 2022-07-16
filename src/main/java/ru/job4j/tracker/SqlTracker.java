package ru.job4j.tracker;

import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class SqlTracker implements Store, AutoCloseable {

    private Connection cn;

    public void init() {
        try (InputStream in = SqlTracker.class.getClassLoader().getResourceAsStream("app.properties")) {
            Properties config = new Properties();
            config.load(in);
            Class.forName(config.getProperty("driver-class-name"));
            cn = DriverManager.getConnection(
                    config.getProperty("url"),
                    config.getProperty("username"),
                    config.getProperty("password")
            );
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public void close() {
        try {
            if (cn != null) {
                cn.close();
            }
        } catch (SQLException ex) {
            throw new IllegalStateException(ex);
        }
    }

    @Override
    public Item add(Item item) {
        init();
        try (PreparedStatement statement =
                     cn.prepareStatement("INSERT INTO items(name, created) VALUES (?, ?);",
                             Statement.RETURN_GENERATED_KEYS
                     )) {
            statement.setString(1, item.getName());
            Timestamp timestampFromLDT = Timestamp.valueOf(item.getDateTime());
            statement.setTimestamp(2, timestampFromLDT);
            statement.execute();
            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    item.setId(generatedKeys.getInt(1));
                }
            }
        } catch (SQLException ex) {
            throw new IllegalStateException(ex);
        }
        close();
        return item;
    }

    @Override
    public boolean replace(int id, Item item) {
        init();
        var rsl = false;
        try (PreparedStatement statement =
                     cn.prepareStatement(
                             "UPDATE items SET name = ?, created = ? WHERE id = ?;"
                     )) {
            statement.setString(1, item.getName());
            Timestamp timestampFromLDT = Timestamp.valueOf(item.getDateTime());
            statement.setTimestamp(2, timestampFromLDT);
            statement.setInt(3, id);
            rsl = statement.executeUpdate() > 0;
            item.setId(id);
        } catch (SQLException ex) {
            throw new IllegalStateException(ex);
        }
        close();
        return rsl;
    }

    @Override
    public boolean delete(int id) {
        init();
        var rsl = false;
        try (PreparedStatement statement =
                     cn.prepareStatement("DELETE FROM items WHERE id = ?;"
                     )) {
            statement.setInt(1, id);
            rsl = statement.executeUpdate() > 0;
        } catch (SQLException ex) {
            throw new IllegalStateException(ex);
        }
        close();
        return rsl;
    }

    @Override
    public List<Item> findAll() {
        init();
        List<Item> items = new ArrayList<>();
        try (PreparedStatement statement = cn.prepareStatement("SELECT * FROM items;")) {
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    var item = new Item(resultSet.getInt("id"),
                            resultSet.getString("name"),
                            resultSet.getTimestamp("created").toLocalDateTime());
                    items.add(item);
                }
            }
        } catch (Exception ex) {
            throw new IllegalStateException(ex);
        }
        close();
        return items;
    }

    @Override
    public List<Item> findByName(String key) {
        init();
        List<Item> items = new ArrayList<>();
        try (PreparedStatement statement =
                     cn.prepareStatement("SELECT * FROM items WHERE name = ?;"
                     )) {
            statement.setString(1, key);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    var item = new Item(resultSet.getInt("id"),
                            key,
                            resultSet.getTimestamp("created").toLocalDateTime());
                    items.add(item);
                }
            }
        } catch (Exception ex) {
            throw new IllegalStateException(ex);
        }
        close();
        return items;
    }

    @Override
    public Item findById(int id) {
        init();
        Item items = null;
        try (PreparedStatement statement =
                     cn.prepareStatement("SELECT * FROM items WHERE id = ?;"
                     )) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    items = new Item(id,
                            resultSet.getString("name"),
                            resultSet.getTimestamp("created").toLocalDateTime());
                }
            }
        } catch (Exception ex) {
            throw new IllegalStateException(ex);
        }
        close();
        return items;
    }
}
