/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ltd.services;

import com.ltd.pojo.Category;
import com.ltd.utils.JdbcConnector;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ASUS-PC
 */
public class CategoryServices {

    public List<Category> getCates() throws SQLException {
        Connection conn = JdbcConnector.getInstance().connect();
        Statement stm = conn.createStatement();
        ResultSet rs = stm.executeQuery("SELECT * from category");

        List<Category> cates = new ArrayList<>();
        while (rs.next()) {
            int id = rs.getInt("id");
            String name = rs.getString("name");

            Category c = new Category(id, name);
            cates.add(c);
        }
        conn.close();
        return cates;
    }
}
