package database;

import model.Product;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ProductHandler extends Connection {
    public List<Product> getProducts() {
        List<Product> products = new ArrayList();
        java.sql.Connection connection = null;
        PreparedStatement pStatement = null;
        ResultSet resultSet = null;
        try {
            connection = getConnection();
            String sql = "SELECT * FROM products";
            pStatement = connection.prepareStatement(sql);
            resultSet = pStatement.executeQuery();
            while (resultSet.next()) {
                Product product = new Product();
                product.setName(resultSet.getString("name"));
                product.setDescription(resultSet.getString("description"));
                product.setPrice(resultSet.getFloat("price"));
                product.setId(resultSet.getInt("id"));
                products.add(product);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeDBObjects(resultSet, pStatement, connection);
        }
        return products;
    }

    public void insert(Product product) {
        java.sql.Connection connection = null;
        PreparedStatement pStatement = null;
        try {
            connection = getConnection();
            String sql = "INSERT INTO products(name, description, price) VALUES (?, ?, ?)";
            pStatement = connection.prepareStatement(sql);
            pStatement.setString(1, product.getName());
            pStatement.setString(2, product.getDescription());
            pStatement.setFloat(3, product.getPrice());
            pStatement.execute();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeDBObjects(null, pStatement, connection);
        }
    }

    public void update(Product product) {
        java.sql.Connection connection = null;
        PreparedStatement pStatement = null;
        try {
            connection = getConnection();
            String sql = "UPDATE products SET name=?, description=?, price=? where id=?";
            pStatement = connection.prepareStatement(sql);
            pStatement.setString(1, product.getName());
            pStatement.setString(2, product.getDescription());
            pStatement.setFloat(3, product.getPrice());
            pStatement.setInt(4, product.getId());
            pStatement.execute();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeDBObjects(null, pStatement, connection);
        }
    }

    public Product getProductByID(String id) {
        Product product = new Product();
        java.sql.Connection connection = null;
        PreparedStatement pStatement = null;
        ResultSet resultSet = null;
        try {
            connection = getConnection();
            String sql = "SELECT * FROM products where id=?";
            pStatement = connection.prepareStatement(sql);
            pStatement.setString(1, id);
            resultSet = pStatement.executeQuery();
            if (resultSet.next()) {
                product.setName(resultSet.getString("name"));
                product.setDescription(resultSet.getString("description"));
                product.setPrice(resultSet.getFloat("price"));
                product.setId(resultSet.getInt("id"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeDBObjects(resultSet, pStatement, connection);
        }
        return product;
    }
}
