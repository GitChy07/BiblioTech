package com.example.bibliotech;

import com.example.bibliotech.medium.Buch;
import com.example.bibliotech.medium.DVD;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Database {
    private static final String URL = "jdbc:mysql://localhost:3306/bibliothek";
    private static final String USER = "m226_test";
    private static final String PASSWORD = "pw_m226_test";

    public static Connection connect() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    public static void insertMedium(IMedium medium, String typ) {
        String sql = "INSERT INTO medien(typ, titel, autor, jahr) VALUES (?, ?, ?, ?)";
        try (Connection conn = connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, typ);
            pstmt.setString(2, medium.getTitel());
            pstmt.setString(3, medium.getAutor());
            pstmt.setInt(4, medium.getErscheinungsjahr());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void deleteMedium(IMedium medium) {
        String sql = "DELETE FROM medien WHERE titel = ? AND autor = ? AND jahr = ?";
        try (Connection conn = connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, medium.getTitel());
            pstmt.setString(2, medium.getAutor());
            pstmt.setInt(3, medium.getErscheinungsjahr());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static List<IMedium> getAllMedien() {
        List<IMedium> liste = new ArrayList<>();
        String sql = "SELECT * FROM medien";

        try (Connection conn = connect(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                String typ = rs.getString("typ");
                String titel = rs.getString("titel");
                String autor = rs.getString("autor");
                int jahr = rs.getInt("jahr");

                if (typ.equalsIgnoreCase("Buch")) {
                    liste.add(new Buch(titel, autor, jahr));
                } else if (typ.equalsIgnoreCase("DVD")) {
                    liste.add(new DVD(titel, autor, jahr));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return liste;
    }
}
