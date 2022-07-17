package ru.job4j.tracker;

import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class SqlTracker implements Store, AutoCloseable {

    private static final Logger LOG = LogManager.getLogger(SqlTracker.class.getName());
    private Connection cn;

    public SqlTracker() {
    }

    public SqlTracker(Connection cn) {
        this.cn = cn;
    }

    public void init() {
        try (InputStream in = SqlTracker.class.getClassLoader().getResourceAsStream("app.properties")) {
            Properties config = new Properties();
            config.load(in);
            Class.forName(config.getProperty("driver"));
            cn = DriverManager.getConnection(
                    config.getProperty("url"),
                    config.getProperty("username"),
                    config.getProperty("password")
            );
        } catch (Exception ex) {
           LOG.error("Exception ", ex);
        }
    }

    @Override
    public void close() {
        try {
            if (cn != null) {
                cn.close();
            }
        } catch (SQLException ex) {
            LOG.error("Exception ", ex);
        }
    }

    @Override
    public Item add(Item item) {
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
            LOG.error("Exception ", ex);
        }
        return item;
    }

    @Override
    public boolean replace(int id, Item item) {
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
        } catch (SQLException ex) {
            LOG.error("Exception ", ex);
        }
        return rsl;
    }

    @Override
    public boolean delete(int id) {
        var rsl = false;
        try (PreparedStatement statement =
                     cn.prepareStatement("DELETE FROM items WHERE id = ?;"
                     )) {
            statement.setInt(1, id);
            rsl = statement.executeUpdate() > 0;
        } catch (SQLException ex) {
            LOG.error("Exception ", ex);
        }
        return rsl;
    }

    @Override
    public List<Item> findAll() {
        List<Item> items = new ArrayList<>();
        try (PreparedStatement statement = cn.prepareStatement("SELECT * FROM items;")) {
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    items.add(getItem(resultSet));
                }
            }
        } catch (Exception ex) {
            LOG.error("Exception ", ex);
        }
        return items;
    }

    @Override
    public List<Item> findByName(String key) {
        List<Item> items = new ArrayList<>();
        try (PreparedStatement statement =
                     cn.prepareStatement("SELECT * FROM items WHERE name = ?;"
                     )) {
            statement.setString(1, key);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    items.add(getItem(resultSet));
                }
            }
        } catch (Exception ex) {
            LOG.error("Exception ", ex);
        }
        return items;
    }

    @Override
    public Item findById(int id) {
        Item items = null;
        try (PreparedStatement statement =
                     cn.prepareStatement("SELECT * FROM items WHERE id = ?;"
                     )) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    items = getItem(resultSet);
                }
            }
        } catch (Exception ex) {
            LOG.error("Exception ", ex);
        }
        return items;
    }

    private Item getItem(ResultSet resultSet) throws SQLException {
        return new Item(resultSet.getInt("id"),
                resultSet.getString("name"),
                resultSet.getTimestamp("created").toLocalDateTime());
    }
}
