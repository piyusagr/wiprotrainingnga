package com.example.controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.dao.ContactDAO;
import com.example.model.Contact;

@WebServlet("/contact")
public class ContactController extends HttpServlet {

    ContactDAO dao = new ContactDAO();

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, java.io.IOException {
        String action = req.getParameter("action");

        switch (action) {
            case "view":
                try {
                    req.setAttribute("contacts", dao.getAllContacts());
                } catch (Exception e) { e.printStackTrace(); }
                req.getRequestDispatcher("viewContacts.jsp").forward(req, resp);
                break;

            case "edit":
                try {
                    int id = Integer.parseInt(req.getParameter("id"));
                    req.setAttribute("contact", dao.getContactById(id));
                } catch (Exception e) { e.printStackTrace(); }
                req.getRequestDispatcher("editContact.jsp").forward(req, resp);
                break;

            case "delete":
                try {
                    dao.deleteContact(Integer.parseInt(req.getParameter("id")));
                    req.setAttribute("message", "Contact deleted successfully");
                } catch (Exception e) {
                    req.setAttribute("error", "Delete failed!");
                }
                req.getRequestDispatcher("contact?action=view").forward(req, resp);
                break;

            default:
                req.getRequestDispatcher("index.jsp").forward(req, resp);
        }
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, java.io.IOException {
        String action = req.getParameter("action");

        if ("add".equals(action)) {
            Contact c = new Contact();
            c.setName(req.getParameter("name"));
            c.setPhone(req.getParameter("phone"));
            c.setEmail(req.getParameter("email"));

            try {
                if (dao.addContact(c)) {
                    req.setAttribute("message", "Contact added successfully");
                } else {
                    req.setAttribute("error", "Contact not added");
                }
            } catch (Exception e) {
                req.setAttribute("error", "Error in insertion");
            }
            req.getRequestDispatcher("addContact.jsp").forward(req, resp);
        }

        if ("update".equals(action)) {
            Contact c = new Contact();

            c.setId(Integer.parseInt(req.getParameter("id")));
            c.setName(req.getParameter("name"));
            c.setPhone(req.getParameter("phone"));
            c.setEmail(req.getParameter("email"));

            try {
                dao.updateContact(c);
                req.setAttribute("message", "Updated successfully");
            } catch (Exception e) {
                req.setAttribute("error", "Update failed");
            }
            req.getRequestDispatcher("contact?action=view").forward(req, resp);
        }
    }
}
