import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class ClouderaImpalaJdbcTester {

    private static final String CONNECTION_URL_PROPERTY = "connection.url";
    private static final String JDBC_DRIVER_NAME_PROPERTY = "jdbc.driver.class.name";

    private static String connectionUrl;
    private static String jdbcDriverName;

    private static void loadConfiguration() throws IOException {
        InputStream input = null;
        try {
            String filename = ClouderaImpalaJdbcTester.class.getSimpleName() + ".conf";
            filename = "ClouderaImpalaJdbcTester.conf";
            input = ClouderaImpalaJdbcTester.class.getClassLoader().getResourceAsStream(filename);
            Properties prop = new Properties();
            prop.load(input);

            connectionUrl = prop.getProperty(CONNECTION_URL_PROPERTY);
            jdbcDriverName = prop.getProperty(JDBC_DRIVER_NAME_PROPERTY);
        } finally {
            try {
                if (input != null)
                    input.close();
            } catch (IOException e) {
                // ToDo
            }
        }
    }

    public static void main(String[] args) throws IOException {
        //String sqlStatement = args[0];
        String sqlStatement = "show databases";
        //sqlStatement = "select * from <table>;";

        loadConfiguration();

        System.out.println("\n=============================================");
        System.out.println("Cloudera Impala JDBC Tester");
        System.out.println("Using Connection URL: " + connectionUrl);
        System.out.println("Running Query: " + sqlStatement);

        Connection con = null;
        try {

            Class.forName(jdbcDriverName);
            con = DriverManager.getConnection(connectionUrl);
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sqlStatement);
            System.out.println("\n== Begin Query Results ======================");
            // print the results to the console
            while (rs.next()) {
                // the tester query returns one String column
                System.out.println(rs.getString(1));
            }
            System.out.println("== End Query Results =======================\n\n");
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                con.close();
            } catch (Exception e) {
                // ToDo
            }
        }
    }
}