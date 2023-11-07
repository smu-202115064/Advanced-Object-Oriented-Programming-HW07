import java.sql.*;
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
        return null;
    }

    @Override
    public PasswordInfo findByKey(String url) {
        return null;
    }

    @Override
    public void update(PasswordInfo p) {

    }

    @Override
    public void delete(String url) {

    }

    @Override
    public void delete(PasswordInfo p) {

    }
}
