


package com.inventory.hibernate_inventory;

import org.hibernate.Session;
import org.hibernate.Transaction;

public class App {

    public static void main(String[] args) {

        // 1️⃣ INSERT
        insertProduct("Laptop", "Gaming Laptop", 75000, 10);
        insertProduct("Mouse", "Wireless Mouse", 1200, 50);

        // 2️⃣ READ
        readProduct(1);

        // 3️⃣ UPDATE
        updateProduct(1, 80000, 8);

        // 4️⃣ DELETE
        deleteProduct(2);
    }

    // INSERT
    public static void insertProduct(String name, String desc, double price, int qty) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();

        Product p = new Product(name, desc, price, qty);
        session.save(p);

        tx.commit();
        session.close();
        System.out.println("Product inserted");
    }

    // READ
    public static void readProduct(int id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Product p = session.get(Product.class, id);

        if (p != null) {
            System.out.println("Product Name: " + p.getName());
            System.out.println("Price: " + p.getPrice());
        }
        session.close();
    }

    // UPDATE
    public static void updateProduct(int id, double price, int qty) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();

        Product p = session.get(Product.class, id);
        p.setPrice(price);
        p.setQuantity(qty);

        session.update(p);
        tx.commit();
        session.close();
        System.out.println("Product updated");
    }

    // DELETE
    public static void deleteProduct(int id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();

        Product p = session.get(Product.class, id);
        session.delete(p);

        tx.commit();
        session.close();
        System.out.println("Product deleted");
    }
}