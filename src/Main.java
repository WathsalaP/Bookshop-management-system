
package src;

import static spark.Spark.*;
import java.sql.*;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        // Configure port
        port(4567);

        // Serve static files (HTML/CSS/JS)
        staticFiles.location("/frontend");

        // Handle form submission
        post("/api/customer", (req, res) -> {
            Gson gson = new Gson();
            Map<String, String> data = gson.fromJson(req.body(), Map.class);
            try (Connection conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/pahana_edu_db",
                    "root",
                    "Admin@1234")) {

                String sql = "INSERT INTO customers (account_number, name, address, telephone) VALUES (?, ?, ?, ?)";
                PreparedStatement stmt = conn.prepareStatement(sql);
                stmt.setString(1, req.queryParams("accountNumber"));
                stmt.setString(2, req.queryParams("name"));
                stmt.setString(3, req.queryParams("address"));
                stmt.setString(4, req.queryParams("telephone"));
                stmt.executeUpdate();

                return "Data saved!";
            } catch (SQLException e) {
                res.status(500);
                return "Error: " + e.getMessage();
            }
        });
        get("/api/customers", (req, res) -> {
            try (Connection conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/pahana_edu_db",
                    "root",
                    "Admin@1234")) {
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT * FROM customers");

                StringBuilder html = new StringBuilder(
                        "<table border='1'><tr><th>ID</th><th>Account</th><th>Name</th><th>Address</th><th>Phone</th></tr>");

                while (rs.next()) {
                    html.append(String.format(
                            "<tr><td>%s</td><td>%s</td><td>%s</td><td>%s</td><td>%s</td></tr>",
                            rs.getInt("id"),
                            rs.getString("account_number"),
                            rs.getString("name"),
                            rs.getString("address"),
                            rs.getString("telephone")));
                }

                html.append("</table>");
                return html.toString();
            }
        });
    }
}