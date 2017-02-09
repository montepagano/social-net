/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package socialnetwork;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author utente
 */
public class UserDAO {
    //throws SQLException con questa riga il metodo tira fuori l'eccezione,dunque avrei dovuto usare un try catch

    User findById(long id) throws SQLException, Exception {
        Connection conn = null;

        String db_user = "monte";
        String db_user_password = "monter";

        conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/social_network",
                db_user, db_user_password);

        Statement st1 = null;
        ResultSet rs1 = null;
        try {
            st1 = conn.createStatement();

            String sql1 = String.format("select name,password from user where id=" + id);

            rs1 = st1.executeQuery(sql1);

            if (rs1.next()) {

                String name = rs1.getString("name");
                String pwd = rs1.getString("password");
                User user = new User(id, name, pwd);
                return user;
            } else {
                throw new Exception("ATTENZIONE UTENTE NO TROVATO");
            }
        } catch (SQLException e) {
            System.out.println("Errore in findById: " + e);
            throw e;
        } finally {
            if (rs1 != null) {
                try {
                    rs1.close();
                } catch (SQLException e) {
                }
            }
            if (st1 != null) {
                try {
                    st1.close();
                } catch (SQLException e) {
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                }
            }

        }
    }

    ArrayList<User> findAll() throws SQLException {
        Connection conn = null;

        String db_user = "monte";
        String db_user_password = "monter";

        conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/social_network",
                db_user, db_user_password);

        Statement st1 = null;
        ResultSet rs1 = null;
        try {
            st1 = conn.createStatement();

            String sql1 = String.format("select id,name,password from user");

            rs1 = st1.executeQuery(sql1);

            ArrayList<User> users = new ArrayList<>();

            while (rs1.next()) {

                Long id = rs1.getLong("id");
                String name = rs1.getString("name");
                String pwd = rs1.getString("password");
                User user = new User(id, name, pwd);
                users.add(user);

            }
            return users;

        } catch (SQLException e) {
            System.out.println("Errore in findById: " + e);
            throw e;
        } finally {
            if (rs1 != null) {
                try {
                    rs1.close();
                } catch (SQLException e) {
                }
            }
            if (st1 != null) {
                try {
                    st1.close();
                } catch (SQLException e) {
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                }

            }
        }

    }

    void UserSave(User user ) throws SQLException {
       

        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

      try {
            String db_user = "monte";
            String db_user_password = "monter";
            conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/social_network",
                db_user, db_user_password);
            
            stmt = conn.createStatement();

            String sql =String.format 
        ("INSERT INTO users (id,name,password) VALUES (%d,%s,%s);",user.id,user.name,user.password);
                int rows =stmt.executeUpdate(sql);

        } catch (SQLException e) {
            System.out.println("Errore: " + e);
            throw e;
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                }
            }
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException e) {
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                }

            }
        }

    }
}
