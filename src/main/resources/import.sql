/*PRODUCT*/
INSERT INTO Product (id, description, name, parent_product_id) VALUES (1, 'Product Description 1', 'Product 1', null);

INSERT INTO Product (id, description, name, parent_product_id) VALUES (2, 'Product Description 2', 'Product 2', null);

INSERT INTO Product (id, description, name, parent_product_id) VALUES (3, 'Product Description 3', 'Product 3', 1);

INSERT INTO Product (id, description, name, parent_product_id) VALUES (4, 'Product Description 4', 'Product 4', 1);

INSERT INTO Product (id, description, name, parent_product_id) VALUES (5, 'Product Description 5', 'Product 5', 2);

INSERT INTO Product (id, description, name, parent_product_id) VALUES (6, 'Product Description 6', 'Product 6', 5);

INSERT INTO Product (id, description, name, parent_product_id) VALUES (7, 'Product Description 7', 'Product 7', 6);

/*IMAGE*/
INSERT INTO Image (id, type, product_id) VALUES (1, 'Type A', 1);

INSERT INTO Image (id, type, product_id) VALUES (2, 'Type B', 1);

INSERT INTO Image (id, type, product_id) VALUES (3, 'Type C', 2);

INSERT INTO Image (id, type, product_id) VALUES (4, 'Type D', 3);

INSERT INTO Image (id, type, product_id) VALUES (5, 'Type E', 4);

INSERT INTO Image (id, type, product_id) VALUES (6, 'Type F', 6);

INSERT INTO Image (id, type, product_id) VALUES (7, 'Type G', 7);