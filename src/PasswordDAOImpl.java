import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PasswordDAOImpl implements PasswordDAO {
    private final static String DB_FILE_NAME = "db.sqlite3";
    private final static String DB_TABLE_NAME = "password";
    private final static String DB_COLUMN_NAME_URL = "url";
    private final static String DB_COLUMN_NAME_ID = "id";
    private final static String DB_COLUMN_NAME_PASSWORD = "password";
    private final static String DB_TABLE_SCHEME = "(%s text PRIMARY KEY, %s text, %s text)".formatted(DB_COLUMN_NAME_URL, DB_COLUMN_NAME_ID, DB_COLUMN_NAME_PASSWORD);

    Connection connection = null;
    ResultSet resultSet = null;
    Statement statement = null;

    public PasswordDAOImpl() {
        try {
            connection = DriverManager.getConnection("jdbc:sqlite:%s".formatted(DB_FILE_NAME));
            statement = connection.createStatement();

            // set timeout to 10 secs.
            statement.setQueryTimeout(10);

            createTable();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void createTable() throws SQLException {
        statement.executeUpdate("DROP TABLE IF EXISTS %s".formatted(DB_TABLE_NAME));
        statement.executeUpdate("CREATE TABLE %s %s".formatted(DB_TABLE_NAME, DB_TABLE_SCHEME));
    }

    @Override
    public void insert(PasswordInfo p) {
        try {
            statement.execute("INSERT INTO %s VALUES('%s','%s','%s')".formatted(DB_TABLE_NAME, p.getUrl(), p.getId(), p.getPassword()));
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<PasswordInfo> findAll() {
        List<PasswordInfo> founds = new ArrayList<>();
        try {
            resultSet = statement.executeQuery("SELECT * FROM %s".formatted(DB_TABLE_NAME));
            while (resultSet.next()) {
                PasswordInfo p = PasswordInfo.getBuilder()
                        .setUrl(resultSet.getString(DB_COLUMN_NAME_URL))
                        .setId(resultSet.getString(DB_COLUMN_NAME_ID))
                        .setPassword(resultSet.getString(DB_COLUMN_NAME_PASSWORD))
                        .build();
                founds.add(p);
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return founds;
    }

    @Override
    public PasswordInfo findByKey(String url) {
        PasswordInfo passwordInfo = null;
        try {
            resultSet = statement.executeQuery("SELECT * FROM %s WHERE %s = '%s'".formatted(DB_TABLE_NAME, DB_COLUMN_NAME_URL, url));
            if (resultSet.next()) {
                passwordInfo = PasswordInfo.getBuilder()
                        .setUrl(resultSet.getString(DB_COLUMN_NAME_URL))
                        .setId(resultSet.getString(DB_COLUMN_NAME_ID))
                        .setPassword(resultSet.getString(DB_COLUMN_NAME_PASSWORD))
                        .build();
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return passwordInfo;
    }

    @Override
    public void update(PasswordInfo p) {
        if (p == null) {
            return;
        }
        try {
            statement.execute("UPDATE %s SET %s = '%s', %s = '%s' WHERE %s = '%s'".formatted(DB_TABLE_NAME, DB_COLUMN_NAME_ID, p.getId(), DB_COLUMN_NAME_PASSWORD, p.getPassword(), DB_COLUMN_NAME_URL, p.getUrl()));
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(String url) {

    }

    @Override
    public void delete(PasswordInfo p) {

    }
}
