-- users 
CREATE TABLE users (
  user_id SERIAL PRIMARY KEY,
  username VARCHAR(50) UNIQUE NOT NULL,
  password_hash VARCHAR(255),
  role VARCHAR(20) DEFAULT 'admin',
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

select * from users;


-- products
CREATE TABLE products (
  product_id SERIAL PRIMARY KEY,
  sku VARCHAR(50) UNIQUE NOT NULL,
  name VARCHAR(200) NOT NULL,
  category VARCHAR(100),
  purchase_price NUMERIC(12,2) NOT NULL,
  selling_price NUMERIC(12,2) NOT NULL,
  quantity INTEGER DEFAULT 0,
  reorder_level INTEGER DEFAULT 0,
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

select * from products;

CREATE TABLE customers (
  customer_id SERIAL PRIMARY KEY,
  name VARCHAR(150) NOT NULL,
  phone VARCHAR(30),
  email VARCHAR(100),
  address TEXT,
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

select * from customers;

-- invoices
CREATE TABLE invoices (
  invoice_id SERIAL PRIMARY KEY,
  invoice_number VARCHAR(50) UNIQUE NOT NULL,
  customer_id INTEGER REFERENCES customers(customer_id),
  total_amount NUMERIC(12,2) NOT NULL,
  total_tax NUMERIC(12,2) DEFAULT 0,
  final_amount NUMERIC(12,2) NOT NULL,
  created_by INTEGER REFERENCES users(user_id),
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

select * from invoices;

-- invoice items
CREATE TABLE invoice_items (
  item_id SERIAL PRIMARY KEY,
  invoice_id INTEGER REFERENCES invoices(invoice_id) ON DELETE CASCADE,
  product_id INTEGER REFERENCES products(product_id),
  unit_price NUMERIC(12,2) NOT NULL,
  quantity INTEGER NOT NULL,
  line_total NUMERIC(12,2) NOT NULL,
  line_tax NUMERIC(12,2) DEFAULT 0
);

select * from invoice_items;


INSERT INTO users (username, password_hash, role) VALUES
('admin','<put-hashed-password-here>','admin');

--sample entry

INSERT INTO products (sku, name, category, purchase_price, selling_price, quantity, reorder_level)
VALUES
('SKU-001','USB Mouse','Accessories',150.00,249.00,120,10),
('SKU-002','Mechanical Keyboard','Accessories',950.00,1299.00,60,5),
('SKU-003','22-inch Monitor','Monitors',5500.00,7999.00,30,3);

INSERT INTO customers (name, phone, email)
VALUES ('Walk-in Customer', '0000000000', NULL),
       ('Acme Pvt Ltd', '9876543210', 'orders@acme.example');
	   
