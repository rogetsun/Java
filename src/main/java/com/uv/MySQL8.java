package com.uv;

import java.sql.*;

/**
 * @author uvsun 2018/8/8 下午9:23
 */
public class MySQL8 {

    public static void main(String[] args) {

        String driver = "com.mysql.cj.jdbc.Driver";

        String url = "jdbc:mysql://192.168.1.206:3306/mysql";
        String username = "root";
        String password = "123456";
        Connection conn = null;
        try {
            Class.forName(driver); //classLoader,加载对应驱动
            conn = DriverManager.getConnection(url, username, password);
            System.out.println(conn);
            PreparedStatement pt = conn.prepareStatement("select * from user;");
            ResultSet rs = pt.executeQuery();
            int col = rs.getMetaData().getColumnCount();
            System.out.println("============================");
            while (rs.next()) {
                for (int i = 1; i <= col; i++) {
                    System.out.print(rs.getString(i) + "\t");
                    if ((i == 2) && (rs.getString(i).length() < 8)) {
                        System.out.print("\t");
                    }
                }
                System.out.println("");
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

}
