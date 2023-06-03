package _1;

import javax.swing.*;
import java.sql.*;

public class Main {
    public static Connection getConnection(String path) throws SQLException {
        String url = "jdbc:sqlite:" + path;
        return DriverManager.getConnection(url);
    }

    private static Object[][] getData(Connection c, String tableName) throws SQLException {
        Object[][] result = {};
        String sql = "select * from " + tableName;
        PreparedStatement ps = c.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            Object[] row = {rs.getInt("id"), rs.getString("name"), rs.getInt("total_stock"), rs.getInt("total_income")};
            result = addRow(result, row);
        }
        return result;
    }

    private static Object[][] addRow(Object[][] result, Object[] row) {
        Object[][] newResult = new Object[result.length + 1][columnNames.length];
        for (int i = 0; i < result.length; i++) {
            newResult[i] = result[i];
        }
        newResult[result.length] = row;
        return newResult;
    }

    private static final String[] columnNames = {"id", "name", "total_stock", "total_income"};

    private static Object[][] data = {};

    private static JTable table;

    public static void main(String[] args) throws SQLException {
        Connection c = getConnection("C:\\Users\\ilya1\\Desktop\\Java\\Programming\\Home\\this.db");
        System.out.println("DB Name: " + c.getMetaData().getDatabaseProductName());
        data = getData(c, "data");
        c.close();
        table = new JTable(data, columnNames);
        JFrame frame = new JFrame("App");
        frame.setSize(400, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.add(panel());
    }

    private static JPanel panel() {
        JPanel panel = new JPanel();
        panel.add(table);
        return panel;
    }
}