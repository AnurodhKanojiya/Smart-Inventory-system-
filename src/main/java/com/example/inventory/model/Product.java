package com.example.inventory.model;

public class Product {
    private int productId;
    private String sku;
    private String name;
    private String category;
    private double purchasePrice;
    private double sellingPrice;
    private int quantity;
    private int reorderLevel;

    public Product() {}

    // useful constructor
    public Product(String sku, String name, String category, double purchasePrice, double sellingPrice, int quantity, int reorderLevel)
    {
        this.sku = sku;
        this.name = name;
        this.category = category;
        this.purchasePrice = purchasePrice;
        this.sellingPrice = sellingPrice;
        this.quantity = quantity;
        this.reorderLevel = reorderLevel;
    }


    // getters & setters
    public int getProductId() { return productId; }
    public void setProductId(int productId) { this.productId = productId; }

    public String getSku() { return sku; }
    public void setSku(String sku) { this.sku = sku; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }

    public double getPurchasePrice() { return purchasePrice; }
    public void setPurchasePrice(double purchasePrice) { this.purchasePrice = purchasePrice; }

    public double getSellingPrice() { return sellingPrice; }
    public void setSellingPrice(double sellingPrice) { this.sellingPrice = sellingPrice; }

    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }

    public int getReorderLevel() { return reorderLevel; }
    public void setReorderLevel(int reorderLevel) { this.reorderLevel = reorderLevel; }

    @Override
    public String toString() {
        return String.format("%s | %s | Rs %.2f | qty=%d", sku, name, sellingPrice, quantity);
    }
}
