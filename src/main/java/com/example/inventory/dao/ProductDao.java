package com.example.inventory.dao;

import com.example.inventory.model.Product;
import com.example.inventory.util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDao {

    // create product (own connection)
    public boolean createProduct(Product p) {
        String sql = "INSERT INTO products (sku,name,category,purchase_price,selling_price,quantity,reorder_level) VALUES (?,?,?,?,?,?,?)";
        try (Connection c = DBConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, p.getSku());
            ps.setString(2, p.getName());
            ps.setString(3, p.getCategory());
            ps.setDouble(4, p.getPurchasePrice());
            ps.setDouble(5, p.getSellingPrice());
            ps.setInt(6, p.getQuantity());
            ps.setInt(7, p.getReorderLevel());
            return ps.executeUpdate() == 1;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    // find by SKU (own connection)
    public Product findBySku(String sku) {
        String sql = "SELECT * FROM products WHERE sku = ?";
        try (Connection c = DBConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, sku);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) return mapRowToProduct(rs);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    // find by product_id (own connection)
    public Product findById(int productId) {
        String sql = "SELECT * FROM products WHERE product_id = ?";
        try (Connection c = DBConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1, productId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) return mapRowToProduct(rs);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    // transaction-friendly: find by id using provided connection
    public Product findById(Connection conn, int productId) throws SQLException {
        String sql = "SELECT * FROM products WHERE product_id = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, productId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) return mapRowToProduct(rs);
            }
        }
        return null;
    }

    // update quantity (own connection)
    public boolean updateQuantity(int productId, int newQty) {
        String sql = "UPDATE products SET quantity = ? WHERE product_id = ?";
        try (Connection c = DBConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1, newQty);
            ps.setInt(2, productId);
            return ps.executeUpdate() == 1;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }
    // transaction-friendly update quantity
    public boolean updateQuantity(Connection conn, int productId, int newQty) throws SQLException {
        String sql = "UPDATE products SET quantity = ? WHERE product_id = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, newQty);
            ps.setInt(2, productId);
            return ps.executeUpdate() == 1;
        }
    }

    // list all products
    public List<Product> listAll() {
        String sql = "SELECT * FROM products ORDER BY name";
        List<Product> out = new ArrayList<>();
        try (Connection c = DBConnection.getConnection();
             Statement s = c.createStatement();
             ResultSet rs = s.executeQuery(sql)) {
            while (rs.next()) out.add(mapRowToProduct(rs));
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return out;
    }

    private Product mapRowToProduct(ResultSet rs) throws SQLException {
        Product p = new Product();
        p.setProductId(rs.getInt("product_id"));
        p.setSku(rs.getString("sku"));
        p.setName(rs.getString("name"));
        p.setCategory(rs.getString("category"));
        p.setPurchasePrice(rs.getDouble("purchase_price"));
        p.setSellingPrice(rs.getDouble("selling_price"));
        p.setQuantity(rs.getInt("quantity"));
        p.setReorderLevel(rs.getInt("reorder_level"));
        return p;
    }
}
