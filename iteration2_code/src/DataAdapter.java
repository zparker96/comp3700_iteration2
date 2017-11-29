import java.sql.*;

public class DataAdapter {
    private Connection connection;

    public DataAdapter(Connection connection) {
        this.connection = connection;
    }

    public Product loadProduct(int id) {
        try {
            String query = "SELECT * FROM Product WHERE ProductID = " + id;

            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            if (resultSet.next()) {
                Product product = new Product();
                product.setProductID(resultSet.getInt(1));
                product.setName(resultSet.getString(2));
                product.setPrice(resultSet.getDouble(3));
                product.setQuantity(resultSet.getDouble(4));
                resultSet.close();
                statement.close();

                return product;
            }

        } catch (SQLException e) {
            System.out.println("Database access error!");
            e.printStackTrace();
        }
        return null;
    }

    public boolean saveProduct(Product product) {
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM Product WHERE ProductID = ?");
            statement.setInt(1, product.getProductID());

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) { // this product exists, update its fields
                statement = connection.prepareStatement("UPDATE Product SET Name = ?, Price = ?, Quantity = ? WHERE ProductID = ?");
                statement.setString(1, product.getName());
                statement.setDouble(2, product.getPrice());
                statement.setDouble(3, product.getQuantity());
                statement.setInt(4, product.getProductID());
            }
            else { // this product does not exist, use insert into
                statement = connection.prepareStatement("INSERT INTO Product VALUES (?, ?, ?, ?)");
                statement.setString(2, product.getName());
                statement.setDouble(3, product.getPrice());
                statement.setDouble(4, product.getQuantity());
                statement.setInt(1, product.getProductID());
            }
            statement.execute();
            resultSet.close();
            statement.close();
            return true;        // save successfully

        } catch (SQLException e) {
            System.out.println("Database access error!");
            e.printStackTrace();
            return false; // cannot save!
        }
    }

    public Order loadOrder(int id) {
        try {
            Order order = null;
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM Order WHERE OrderID = " + id);

            if (resultSet.next()) {
                order = new Order();
                order.setOrderID(resultSet.getInt("OrderID"));
                order.setCustomerName(resultSet.getString("Customer"));
                order.setTotalCost(resultSet.getDouble("TotalCost"));
                order.setDate(resultSet.getString("OrderDate"));
                resultSet.close();
                statement.close();
            }

            // loading the order lines for this order
            resultSet = statement.executeQuery("SELECT * FROM OrderLine WHERE OrderID = " + id);

            while (resultSet.next()) {
                OrderLine line = new OrderLine();
                line.setOrderID(resultSet.getInt(1));
                line.setProductID(resultSet.getInt(2));
                line.setQuantity(resultSet.getInt(3));
                line.setCost(resultSet.getDouble(4));
                order.addLine(line);
            }

            return order;

        } catch (SQLException e) {
            System.out.println("Database access error!");
            e.printStackTrace();
            return null;
        }
    }
    
    public int nextOrderID() {
    	try {
        	Statement stmt = connection.createStatement();
			ResultSet res = stmt.executeQuery("SELECT max(OrderID) FROM 'Order'");
			if (res.next())  {
				int id = 1 + res.getInt(1);
				stmt.close();
				res.close();
						return id;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
    	return 0;
    }
    
    public boolean saveOrder(Order order) {
        try {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO 'Order' " +
                    "VALUES (?, ?, ?, ?, ?)");
            statement.setInt(1, order.getOrderID());
            statement.setString(2, order.getDate());
            statement.setString(3, order.getCustomerName());
            statement.setDouble(4, order.getTotalCost());
            statement.setDouble(5, order.getTotalTax());

            statement.execute();    // commit to the database;
            statement.close();

            statement = connection.prepareStatement("INSERT INTO OrderLine VALUES (?, ?, ?, ?)");
            for (OrderLine line: order.getLines()) { // store for each order line!
                statement.setInt(1, line.getOrderID());
                statement.setInt(2, line.getProductID());
                statement.setDouble(3, line.getQuantity());
                statement.setDouble(4, line.getCost());

                statement.execute();    // commit to the database;
            }
            statement.close();
            return true; // save successfully!
        }
        catch (SQLException e) {
            System.out.println("Database access error!");
            e.printStackTrace();
            return false;
        }
    }

    public boolean addUser(User user) {
        try {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO 'User' VALUES (?, ?, ?, ?, ?)");
            statement.setInt(1, user.getUserID());
            statement.setString(2, user.getUserName());
            statement.setString(3, user.getDisplayName());
            statement.setBoolean(4, user.getRole());
            statement.setString(5, user.getPassword());

            statement.execute();    // commit to the database;
            statement.close();

            return true; // save successfully!
        }
        catch (SQLException e) {
            System.out.println("Database access error!");
            e.printStackTrace();
            return false;
        }
    }

    public User loadUser(String userName) {
        try {
            User user = null;
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM User WHERE Username = "
                    + "\"" + userName + "\"");

            if (resultSet.next()) {
                user = new User();
                user.setUserID(resultSet.getInt("UserID"));
                user.setUserName(resultSet.getString("Username"));
                user.setDisplayName(resultSet.getString("DisplayName"));
                user.setRole(resultSet.getBoolean("IsManager"));
                user.setPassword(resultSet.getString("Password"));
                resultSet.close();
                statement.close();
            }

            return user;

        } catch (SQLException e) {
            System.out.println("Database access error!");
            e.printStackTrace();
            return null;
        }
    }

    public boolean changePassword(User user) {
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM User WHERE UserID = ?");
            statement.setInt(1, user.getUserID());

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) { // this user exists, update its fields
                statement = connection.prepareStatement("UPDATE User SET Username = ?, DisplayName = ?, " +
                        "IsManager = ?, Password = ? WHERE UserID = ?");
                statement.setString(1, user.getUserName());
                statement.setString(2, user.getDisplayName());
                statement.setBoolean(3, user.getRole());
                statement.setString(4, user.getPassword());
                statement.setInt(5, user.getUserID());
            }
//            User user = null;
//            PreparedStatement statement = connection.prepareStatement("UPDATE User SET Password = ? WHERE Username = ?");
//            statement.setString(1, user.getUserName());
//
            statement.execute();    // commit to the database;
            resultSet.close();
            statement.close();

            return true; // save successfully!
        }
        catch (SQLException e) {
            System.out.println("Database access error!");
            e.printStackTrace();
            return false;
        }
    }



}