package DBDAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Beans.Category;
import Beans.ConnectionPool;
import Beans.Coupon;
import Beans.Customer;
import DAO.CustomersDAO;

public class CustomersDBDAO implements CustomersDAO {
	// Create connection
	private ConnectionPool pool = ConnectionPool.getInstance();

	// Customers exists // works.
	@Override
	public boolean isCustomerExists(String email, String password) throws SQLException {
		Connection con = pool.getConnction();
		String sql = "SELECT * FROM Customers WHERE Email=? AND Password = ?";
		try {
			boolean exist = false;
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, email);
			st.setString(2, password);
			ResultSet rs = st.executeQuery();
			if (rs.next()) {
				exist = true;
				System.out.println(exist);
			} else
				System.out.println(exist);
		} catch (SQLException e) {
			throw new SQLException(e);
		} finally {
			pool.returnConnection(con);
		}
		return false;
	}

	public int isCustomerExistsInt(String email, String password) throws SQLException {
		Connection con = pool.getConnction();
		String sql = "SELECT * FROM Customers WHERE Email=? AND Password = ?";
		try {
			boolean exist = false;
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, email);
			st.setString(2, password);
			ResultSet rs = st.executeQuery();
			if (rs.next()) {
				exist = true;
				if (exist)
					return rs.getInt("ID");
			} else
				System.out.println(exist);
		} catch (SQLException e) {
			throw new SQLException(e);
		} finally {
			pool.returnConnection(con);
		}
		return 0;
	}

	// Add one customer // works.
	@Override
	public void addCustomer(Customer customer) throws SQLException {
		Connection con = pool.getConnction();
		String sql = "INSERT INTO Customers( First_Name, Last_Name, Email, Password) VALUES(?,?,?,?)";
		try {
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, customer.getFirstName());
			st.setString(2, customer.getLastName());
			st.setString(3, customer.getEmail());
			st.setString(4, customer.getPassword());
			st.executeUpdate();
		} catch (SQLException e) {
			throw new SQLException(e);
		} finally {
			pool.returnConnection(con);
		}
	}

	// Update one customer // works.
	@Override
	public void updateCustomer(Customer customer) throws SQLException {
		Connection con = pool.getConnction();
		String sql = "UPDATE Customers SET First_Name = ?, Last_Name = ?, Email = ?, Password = ? WHERE ID = ?";
		try {
			PreparedStatement st = con.prepareStatement(sql);
			st.setInt(5, customer.getId());
			st.setString(1, customer.getFirstName());
			st.setString(2, customer.getLastName());
			st.setString(3, customer.getEmail());
			st.setString(4, customer.getPassword());
			st.execute();
		} catch (SQLException e) {
			throw new SQLException(e.getMessage());
		} finally {
			pool.returnConnection(con);
		}
	}

	// Deletes one customer // works.
	@Override
	public void deleteCustomer(int customerID) throws SQLException {
		Connection con = pool.getConnction();
		String sql = "DELETE FROM Customers WHERE Id = ?";
		try {
			PreparedStatement st = con.prepareStatement(sql);
			st.setInt(1, customerID);
			st.executeUpdate();
		} catch (SQLException e) {
			throw new SQLException(e);
		} finally {
			pool.returnConnection(con);
		}
	}

	// Show one customer // works.
	@Override
	public Customer getOneCustomer(int customerID) throws SQLException {
		int id = 0;
		String firstName = null, lastName = null, email = null, password = null;
		Connection con = pool.getConnction();
		ArrayList<Coupon> coupons = new ArrayList<Coupon>();
		try {
			PreparedStatement st1 = con.prepareStatement("SELECT * FROM Customers WHERE ID = ?");
			st1.setInt(1, customerID);
			ResultSet rs1 = st1.executeQuery();
			if (rs1.next()) {
				id = rs1.getInt("id");
				firstName = rs1.getString("first_name");
				lastName = rs1.getString("last_name");
				email = rs1.getString("email");
				password = rs1.getString("password");
			}

			PreparedStatement st2 = con.prepareStatement(
					"SELECT * FROM coupons join Customers_vs_Coupons on coupons.id = Customers_vs_Coupons.coupon_id WHERE Customer_ID =?");
			st2.setInt(1, customerID);
			ResultSet rs2 = st2.executeQuery();
			while (rs2.next()) {
				int idCoupon = rs2.getInt("id");
				int companyID = rs2.getInt("company_id");
				int amount = rs2.getInt("amount");
				Category category = Category.values()[rs2.getInt("category_id") - 1];
				String title = rs2.getString("title");
				String description = rs2.getString("title");
				String image = rs2.getString("image");
				Date startDate = rs2.getDate("start_Date");
				Date endDate = rs2.getDate("end_Date");
				Double price = rs2.getDouble("price");
				coupons.add(new Coupon(idCoupon, companyID, amount, category, title, description, image, startDate,
						endDate, price));
			}
		} catch (SQLException e) {
			throw new SQLException(e.getMessage());
		} finally {
			pool.returnConnection(con);
		}
		return new Customer(id, firstName, lastName, email, password, coupons);
	}

	// Show all customers
	@Override
	public ArrayList<Customer> getAllCustomers() throws SQLException {
		Connection con = pool.getConnction();
		ArrayList<Customer> customers = new ArrayList<Customer>();
		String sql = "SELECT * FROM Customers";
		try {
			PreparedStatement st = con.prepareStatement(sql);
			ResultSet rs = st.executeQuery();
			while (rs.next()) {
				customers.add(getOneCustomer(rs.getInt("id")));
			}
		} catch (SQLException e) {
			throw new SQLException(e.getMessage());
		} finally {
			pool.returnConnection(con);
		}
		return customers;
	}
}
