package com.example.inventory.app;

import com.example.inventory.dao.ProductDao;
import com.example.inventory.model.Product;

public class Main {
    public static void main(String[] args) {

        ProductDao dao = new ProductDao();


        // 1 Create New Product

        Product p = new Product();
        p.setSku("SKU1001");
        p.setName("Wireless Mouse");
        p.setCategory("Electronics");
        p.setPurchasePrice(300);
        p.setSellingPrice(500);
        p.setQuantity(50);
        p.setReorderLevel(10);

        boolean created = dao.createProduct(p);
        System.out.println(created ? "Product Created Successfully!" : "Product Creation Failed");


        // 2 Find Product by SKU

        Product foundBySku = dao.findBySku("SKU1001");
        if (foundBySku != null) {
            System.out.println("Found by SKU: " + foundBySku.getName());
        } else {
            System.out.println("Product not found by SKU.");
        }


        // 3 List All Products

        System.out.println("\n--- Product List ---");
        for (Product product : dao.listAll()) {
            System.out.println(product.getProductId() + " | " +
                    product.getSku() + " | " +
                    product.getName() + " | Qty: " +
                    product.getQuantity());
        }


        // 4 Update Quantity

        boolean updated = dao.updateQuantity(1, 80); // product_id = 1
        System.out.println(updated ? "Quantity Updated Successfully!" : "Failed to Update Quantity");


        // Find Product by ID

        Product foundById = dao.findById(1);
        if (foundById != null) {
            System.out.println("\nFound by ID: " + foundById.getName() +
                    " | New Quantity = " + foundById.getQuantity());
        } else {
            System.out.println("Product not found by ID.");
        }
    }
}
