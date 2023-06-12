/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import context.DBContext;
import entity.Cart;
import entity.Category;
import entity.Item;
import entity.Order;
import entity.OrderDetail;
import entity.Product;
import entity.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author trinh
 */
public class DAO extends DBContext {

    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    public List<Product> getAllProduct() {
        List<Product> list = new ArrayList<>();
        String query = "select * from Products";
        try {
            ps = connection.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Product(rs.getInt("product_id"),
                        rs.getString("name"),
                        rs.getInt("quantity"),
                        rs.getDouble("price"),
                        rs.getString("describe"),
                        rs.getString("image")));
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }

    public List<Product> getTop6Product() {
        List<Product> list = new ArrayList<>();
        String query = "select top 6 * from Products";
        try {
            ps = connection.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Product(rs.getInt("product_id"),
                        rs.getString("name"),
                        rs.getInt("quantity"),
                        rs.getDouble("price"),
                        rs.getString("describe"),
                        rs.getString("image")));
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }

    public List<Product> getNext6Product(int amount) {
        List<Product> list = new ArrayList<>();
        String query = "SELECT *\n"
                + "FROM\n"
                + "    products\n"
                + "ORDER BY product_id\n"
                + "OFFSET ? ROWS \n"
                + "FETCH NEXT 6 ROWS ONLY;";
        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1, amount);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Product(rs.getInt("product_id"),
                        rs.getString("name"),
                        rs.getInt("quantity"),
                        rs.getDouble("price"),
                        rs.getString("describe"),
                        rs.getString("image")));
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }

    public List<Category> getAllCategory() {
        List<Category> list = new ArrayList<>();
        String sql = "select * from Categories";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Category c = new Category(rs.getInt("category_id"),
                        rs.getString("name"),
                        rs.getString("describe"));
                list.add(c);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }

    public List<Product> getProductByCID(String category_id) {
        List<Product> list = new ArrayList<>();
        String query = "select * from Products where category_id = ?";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, category_id);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Product(rs.getInt("product_id"),
                        rs.getString("name"),
                        rs.getInt("quantity"),
                        rs.getDouble("price"),
                        rs.getString("describe"),
                        rs.getString("image")));
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }

    public Product getProductByID(String product_id) {
        String query = "select * from Products where product_id = ?";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, product_id);
            rs = ps.executeQuery();
            if (rs.next()) {
                Product p = new Product(
                        rs.getInt("product_id"),
                        rs.getString("name"),
                        rs.getInt("quantity"),
                        rs.getDouble("price"),
                        rs.getString("describe"),
                        rs.getString("image"));
                return p;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }

    public Product getLast() {
        String query = "select top 1 * from products\n"
                + "order by product_id desc";
        try {
            ps = connection.prepareStatement(query);
            rs = ps.executeQuery();
            if (rs.next()) {
                return new Product(rs.getInt("product_id"),
                        rs.getString("name"),
                        rs.getInt("quantity"),
                        rs.getDouble("price"),
                        rs.getString("describe"),
                        rs.getString("image"));
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }

    public List<Product> searchByName(String txtSearch) {
        List<Product> list = new ArrayList<>();
        String query = "select * from Products where [name] like ?";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, "%" + txtSearch + "%");
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Product(rs.getInt("product_id"),
                        rs.getString("name"),
                        rs.getInt("quantity"),
                        rs.getDouble("price"),
                        rs.getString("describe"),
                        rs.getString("image")));
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }

    public User login(String email, String pass) {
        String query = "select * from Users where [user_email] = ? and [user_password] = ?";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, email);
            ps.setString(2, pass);
            rs = ps.executeQuery();
            while (rs.next()) {
                User x = new User(rs.getInt("user_id"),
                        rs.getString("user_name"),
                        rs.getString("user_email"),
                        rs.getString("user_password"),
                        rs.getString("address"),
                        rs.getInt("isAdmin"));
                return x;
            }
        } catch (SQLException e) {
            System.out.println(e);

        }
        return null;
    }

    public User checkUserExist(String email) {
        String query = "select * from Users where [user_email] = ?";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, email);
            rs = ps.executeQuery();
            while (rs.next()) {
                User x = new User(rs.getInt("user_id"),
                        rs.getString("user_name"),
                        rs.getString("user_email"),
                        rs.getString("user_password"),
                        rs.getString("address"),
                        rs.getInt("isAdmin"));
                return x;
            }
        } catch (SQLException e) {
            System.out.println(e);

        }
        return null;
    }

    public void signUp(String user_name, String email, String pass, String address) {
        String query = "INSERT INTO [dbo].[Users]\n"
                + "           ([user_name]\n"
                + "           ,[user_email]\n"
                + "           ,[user_password]\n"
                + "           ,[address]\n"
                + "           ,[isAdmin])\n"
                + "     VALUES\n"
                + "           (? , ? , ? , ? , ?)";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, user_name);
            ps.setString(2, email);
            ps.setString(3, pass);
            ps.setString(4, address);
            ps.setInt(5, 0);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);

        }
    }

    public void deleteProductByID(String product_id) {
        String query = "delete from Products where product_id = ?";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, product_id);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public void addProductByID(String cid, String name, String quantity, String price, String describe, String image) {
        String query = "INSERT INTO [dbo].[Products]\n"
                + "           ([category_id]\n"
                + "           ,[name]\n"
                + "           ,[quantity]\n"
                + "           ,[price]\n"
                + "           ,[describe]\n"
                + "           ,[image])\n"
                + "     VALUES (? , ? , ? , ? , ? ,?)";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, cid);
            ps.setString(2, name);
            ps.setString(3, quantity);
            ps.setString(4, price);
            ps.setString(5, describe);
            ps.setString(6, image);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public void editProductByID(String cid, String name, String quantity, String price, String describe, String image, String pid) {
        String query = "update products \n"
                + "set category_id = ? , \n"
                + "name = ?,\n"
                + "quantity = ?,\n"
                + "price =?,\n"
                + "describe = ?,\n"
                + "image = ?\n"
                + "where product_id = ?";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, cid);
            ps.setString(2, name);
            ps.setString(3, quantity);
            ps.setString(4, price);
            ps.setString(5, describe);
            ps.setString(6, image);
            ps.setString(7, pid);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public List<User> getAllUser() {
        List<User> list = new ArrayList<>();
        String query = "select * from Users";
        try {
            ps = connection.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new User(rs.getInt("user_id"),
                        rs.getString("user_name"),
                        rs.getString("user_email"),
                        rs.getString("user_password"),
                        rs.getString("address"),
                        rs.getInt("isAdmin")));
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }

    public void deleteUserByID(String uid) {
        String query = "delete from Users where user_id = ?";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, uid);
            ps.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public User getUserByID(String uid) {
        String query = "select * from Users where user_id = ?";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, uid);
            rs = ps.executeQuery();
            if (rs.next()) {
                return (new User(rs.getInt("user_id"),
                        rs.getString("user_name"),
                        rs.getString("user_email"),
                        rs.getString("user_password"),
                        rs.getString("address"),
                        rs.getInt("isAdmin")));
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }

    public User EditUserByID(String uid, String user_name, String user_email, String user_password, String user_address, String isAdmin) {
        String query = "UPDATE [dbo].[Users]\n"
                + "   SET [user_name] = ?\n"
                + "      ,[user_email] = ?\n"
                + "      ,[user_password] = ?\n"
                + "      ,[address] = ?\n"
                + "      ,[isAdmin] = ?\n"
                + " WHERE user_id = ?";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, user_name);
            ps.setString(2, user_email);
            ps.setString(3, user_password);
            ps.setString(4, user_address);
            ps.setString(5, isAdmin);
            ps.setString(6, uid);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }

    public void deleteCategoryByID(String cid) {
        String query = "delete from Categories where category_id = ?";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, cid);
            ps.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public void addCategory(String name, String describe) {
        String query = "INSERT INTO [dbo].[Categories]\n"
                + "           ([name]\n"
                + "           ,[describe])\n"
                + "     VALUES\n"
                + "           (? , ?)";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, name);
            ps.setString(2, describe);
            ps.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public Category getCategoryByID(String cid) {
        String sql = "select * from Categories where category_id = ?";
        try {
            ps = connection.prepareStatement(sql);
            ps.setString(1, cid);
            rs = ps.executeQuery();
            if (rs.next()) {
                Category c = new Category(rs.getInt("category_id"),
                        rs.getString("name"),
                        rs.getString("describe"));
                return c;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }

    public void editCategory(String cid, String name, String describe) {
        String query = "UPDATE [dbo].[Categories]\n"
                + "   SET [name] = ?,\n"
                + "      [describe] = ?\n"
                + " WHERE category_id = ?";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, name);
            ps.setString(2, describe);
            ps.setString(3, cid);
            ps.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public void addOrder(User user, Cart cart, String address, String phoneNumber) {
        String query = "INSERT INTO [dbo].[Orders]\n"
                + "           ([user_id]\n"
                + "           ,[order_date]\n"
                + "           ,[total_price]\n"
                + "           ,[address]\n"
                + "           ,[phone_number]\n"
                + "           ,[status])\n"
                + "     VALUES\n"
                + "           (? , ? , ? , ?, ?, ?)";
        LocalDate curDate = LocalDate.now();
        String date = curDate.toString();
        try {
            // add order
            ps = connection.prepareStatement(query);
            ps.setInt(1, user.getUser_id());
            ps.setString(2, date);
            ps.setDouble(3, cart.getTotalMoney());
            ps.setString(4, address);
            ps.setString(5, phoneNumber);
            ps.setString(6, "Pending Approval");
            ps.executeUpdate();
            //lay id cua order vua add
            String query2 = "select top 1 order_id from [Orders] order by order_id desc";
            PreparedStatement ps2 = connection.prepareStatement(query2);
            ResultSet rs2 = ps2.executeQuery();
//            //add orderDetails;
            if (rs2.next()) {
                int order_id = rs2.getInt("order_id");
                for (Item i : cart.getItem()) {
                    String query3 = "INSERT INTO [dbo].[Order_Details]\n"
                            + "           ([order_id]\n"
                            + "           ,[product_id]\n"
                            + "           ,[quantity]\n"
                            + "           ,[price])\n"
                            + "     VALUES\n"
                            + "           (? , ? , ? , ?)";
                    ps2 = connection.prepareStatement(query3);
                    ps2.setInt(1, order_id);
                    ps2.setInt(2, i.getProduct().getProduct_id());
                    ps2.setInt(3, i.getQuantity());
                    ps2.setDouble(4, i.getPrice());
                    ps2.executeUpdate();
                }
            }
            //update quantity product
            String query4 = "update products set quantity = quantity-? where product_id=?";
            PreparedStatement ps4 = connection.prepareStatement(query4);
            for (Item i : cart.getItem()) {
                ps4.setInt(1, i.getQuantity());
                ps4.setInt(2, i.getProduct().getProduct_id());
                ps4.executeUpdate();

            }
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public List<Product> getProductByFilter(String queryCategory, String queryPrice) {
        List<Product> list = new ArrayList<>();
        String query = "SELECT p.product_id, p.category_id, p.name, p.quantity , p.price , p.describe , p.image\n"
                + "FROM products p\n"
                + "INNER JOIN categories c ON p.category_id = c.category_id\n"
                + "WHERE 1=1 ";
        try {
            if (queryCategory.length() > 0 && queryPrice.length() > 0) {
                query += "AND (" + queryCategory + ") " + "AND" + " (" + queryPrice + ")";
            } else if (queryPrice.length() > 0) {
                query += "AND (" + queryPrice + ")";
            } else if (queryCategory.length() > 0) {
                query += "AND (" + queryCategory + ")";
            }
            ps = connection.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Product(rs.getInt("product_id"),
                        rs.getString("name"),
                        rs.getInt("quantity"),
                        rs.getDouble("price"),
                        rs.getString("describe"),
                        rs.getString("image")));
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }
//    public void addOrdertest(int user_id, double money) {
//        String query = "INSERT INTO [dbo].[Orders]\n"
//                + "           ([user_id]\n"
//                + "           ,[order_date]\n"
//                + "           ,[total_price]\n"
//                + "           ,[status])\n"
//                + "     VALUES\n"
//                + "           (? , ? , ? , ?)";
//        LocalDate curDate = LocalDate.now();
//        String date = curDate.toString();
//        System.out.println(date);
//        try {
//            // add order
//            ps = connection.prepareStatement(query);
//            ps.setInt(1, user_id);
//            ps.setString(2, date);
//            ps.setDouble(3, money);
//            ps.setString(4, "Pending Approval");
//            ps.executeUpdate();
//            //lay id cua order vua add
//        } catch (SQLException e) {
//            System.out.println(e);
//        }
//    }
    public List<Order> getOrderByUserID (int user_id){
        List<Order> list = new ArrayList<>();
        String query = "select * from [orders] where user_id = ? order by order_id desc";
        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1, user_id);
            rs = ps.executeQuery();
            while (rs.next()) {
                Order order = new Order(rs.getInt("order_id"),
                                        rs.getInt("user_id"),
                                        rs.getString("order_date"),
                                        rs.getDouble("total_price"),
                                        rs.getString("address"),
                                        rs.getString("phone_number"),
                                        rs.getString("status")
                                        );
                list.add(order);
                
            }
            return list;
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }
    public List<Order> getAllOrder (){
        List<Order> list = new ArrayList<>();
        String query = "select * from [orders]  order by order_id desc";
        try {
            ps = connection.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                Order order = new Order(rs.getInt("order_id"),
                                        rs.getInt("user_id"),
                                        rs.getString("order_date"),
                                        rs.getDouble("total_price"),
                                        rs.getString("address"),
                                        rs.getString("phone_number"),
                                        rs.getString("status")
                                        );
                list.add(order);                
            }
            return list;
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }
    public List<OrderDetail> getAllOrderDetail (){
        List<OrderDetail> list = new ArrayList<>();
        String query = "select * from [order_details]";
        try {
            ps = connection.prepareStatement(query);        
            rs = ps.executeQuery();
            
            
            while (rs.next()) {
                //lay product_id
                int product_id = rs.getInt("product_id");
                DAO dao = new DAO();
                Product product = dao.getProductByID(product_id+"");
                
                OrderDetail orderdetail = new OrderDetail(rs.getInt("order_detail_id"),
                                        rs.getInt("order_id"),
                                        product_id,
                                        rs.getInt("quantity"),
                                        rs.getDouble("price"),
                                        product
                                        );
                list.add(orderdetail);                
            }
            return list;
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }
    public void editStatusOrderPendingApproval(int order_id) {
        String query = "UPDATE orders SET status = 'Pending Approval' WHERE order_id = ?;";
        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1, order_id);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
     public void editStatusOrderApproved(int order_id) {
        String query = "UPDATE orders SET status = 'Approved' WHERE order_id = ?;";
        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1, order_id);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
    
    public static void main(String[] args){
        DAO dao = new DAO();
        List<Order> list = dao.getAllOrder();
        for(Order x : list){
            System.out.println(x);
        }
    }

}
