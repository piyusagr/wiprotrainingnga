package com.example.dao;



import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.example.model.Contact;
import com.example.util.DBUtil;

public class ContactDAO {

    public boolean addContact(Contact c) throws Exception {
        Connection con = DBUtil.getConnection();
        String sql = "INSERT INTO CONTACT(name, phone, email) VALUES(?, ?, ?)";
        PreparedStatement ps = con.prepareStatement(sql);

        ps.setString(1, c.getName());
        ps.setString(2, c.getPhone());
        ps.setString(3, c.getEmail());

        return ps.executeUpdate() > 0;
    }

    public List<Contact> getAllContacts() throws Exception {
        Connection con = DBUtil.getConnection();
        String sql = "SELECT * FROM CONTACT";
        ResultSet rs = con.createStatement().executeQuery(sql);

        List<Contact> list = new ArrayList<>();
        while (rs.next()) {
            Contact c = new Contact();
            c.setId(rs.getInt("id"));
            c.setName(rs.getString("name"));
            c.setPhone(rs.getString("phone"));
            c.setEmail(rs.getString("email"));
            list.add(c);
        }
        return list;
    }

    public Contact getContactById(int id) throws Exception {
        Connection con = DBUtil.getConnection();
        String sql = "SELECT * FROM CONTACT WHERE id=?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1, id);

        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            Contact c = new Contact();
            c.setId(rs.getInt("id"));
            c.setName(rs.getString("name"));
            c.setPhone(rs.getString("phone"));
            c.setEmail(rs.getString("email"));
            return c;
        }
        return null;
    }

    public boolean updateContact(Contact c) throws Exception {
        Connection con = DBUtil.getConnection();
        String sql = "UPDATE CONTACT SET name=?, phone=?, email=? WHERE id=?";
        PreparedStatement ps = con.prepareStatement(sql);

        ps.setString(1, c.getName());
        ps.setString(2, c.getPhone());
        ps.setString(3, c.getEmail());
        ps.setInt(4, c.getId());

        return ps.executeUpdate() > 0;
    }

    public boolean deleteContact(int id) throws Exception {
        Connection con = DBUtil.getConnection();
        String sql = "DELETE FROM CONTACT WHERE id=?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1, id);

        return ps.executeUpdate() > 0;
    }
}
