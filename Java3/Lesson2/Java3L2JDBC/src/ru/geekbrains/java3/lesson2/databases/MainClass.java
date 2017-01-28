package ru.geekbrains.java3.lesson2.databases;

public class MainClass {
    private static Connection conn = null;
    private static PreparedStatement ps;
    private static Statement stmt;

    private static void connect() {
        try {
            Class.forName("org.sqlite.JDBC");
            conn = DriverManager.getConnection("jdbc:sqlite:dbt.db");
            stmt = conn.createStatement();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void disconnect() {
        try {
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void testInsUpdDelSel() {
        try {
            conn.setAutoCommit(false);
            stmt.executeUpdate("INSERT INTO Students (name, score) VALUES ('Bob', 20)");
            stmt.executeUpdate("INSERT INTO Students (name, score) VALUES ('Bob2', 40)");
            stmt.executeUpdate("UPDATE Students SET score = 100 WHERE name = 'Bob'");
            stmt.executeUpdate("DELETE FROM Students WHERE name = 'Bob2'");
            ResultSet rs = stmt.executeQuery("SELECT * FROM Students");
            while (rs.next()) {
                System.out.println(rs.getInt("id") + " " + rs.getString("name") + " " + rs.getInt("score"));
            }
            conn.setAutoCommit(true);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void preparedStmtTest() {
        try {
            conn.setAutoCommit(false);
            ps = conn.prepareStatement("INSERT INTO Students (name, score) VALUES (?, ?)");
            for (int i = 0; i < 10000; i++) {
                ps.setString(1, "Bob" + i);
                ps.setInt(2, i);
                ps.executeUpdate();
            }
            conn.setAutoCommit(true);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void batchTest() {
        try {
            conn.setAutoCommit(false);
            ps = conn.prepareStatement("INSERT INTO Students (name, score) VALUES (?, ?)");
            for (int i = 0; i < 100; i++) {
                ps.setString(1, "Bob" + i);
                ps.setInt(2, i);
                ps.addBatch();
            }
            ps.executeBatch();
            conn.setAutoCommit(true);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void clearTable() {
        try {
            stmt.execute("DELETE FROM Students"); // очистка содержимого таблицы
            stmt.execute("DELETE FROM sqlite_sequence WHERE name = 'Students';"); // очистка первичного ключа
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        connect();
        try {
            clearTable();
            testInsUpdDelSel();
            clearTable();
            preparedStmtTest();
            clearTable();
            batchTest();
        } finally {
            disconnect();
        }
    }
}
