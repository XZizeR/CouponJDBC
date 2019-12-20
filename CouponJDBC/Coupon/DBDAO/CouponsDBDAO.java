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
import DAO.CouponsDAO;

public class CouponsDBDAO implements CouponsDAO {
	private ConnectionPool pool = ConnectionPool.getInstance();

	// Add one coupon // works.
	@Override
	public void addCoupon(Coupon coupon) throws SQLException {
		Connection con = pool.getConnction();
		String sql = "INSERT INTO Coupons( Company_ID, Amount, Category_ID, Title, Description, Image , Start_Date, End_Date, Price) VALUES(?,?,?,?,?,?,?,?,?)";
		try {
			PreparedStatement st = con.prepareStatement(sql);
			st.setInt(1, coupon.getCompanyID());
			st.setInt(2, coupon.getAmount());
			st.setInt(3, coupon.getCategory().ordinal() + 1);
			st.setString(4, coupon.getTitle());
			st.setString(5, coupon.getDescription());
			st.setString(6, coupon.getImage());
			st.setDate(7, coupon.getStartDate());
			st.setDate(8, coupon.getEndDate());
			st.setDouble(9, coupon.getPrice());
			st.executeUpdate();
		} catch (SQLException e) {
			throw new SQLException(e);
		} finally {
			pool.returnConnection(con);
		}
	}

	// Update one coupon // works.
	@Override
	public void updateCoupon(Coupon coupon) throws SQLException {
		Connection con = pool.getConnction();
		String sql = "UPDATE Coupons SET Company_ID  = ?, Category_ID = ?, Title = ?, Description = ?, Start_Date = ?, End_Date = ?, Amount = ?, Price = ?, Image = ? WHERE ID = ?";
		try {
			PreparedStatement st = con.prepareStatement(sql);
			st.setInt(10, coupon.getId());
			st.setInt(1, coupon.getCompanyID());
			st.setInt(2, coupon.getCategory().ordinal() + 1);
			st.setString(3, coupon.getTitle());
			st.setString(4, coupon.getDescription());
			st.setDate(5, coupon.getStartDate());
			st.setDate(6, coupon.getEndDate());
			st.setInt(7, coupon.getAmount());
			st.setDouble(8, coupon.getPrice());
			st.setString(9, coupon.getImage());
			st.execute();
		} catch (SQLException e) {
			throw new SQLException(e);
		} finally {
			pool.returnConnection(con);
		}
	}

	// Deletes one coupon // works.
	@Override
	public void deleteCoupon(int couponID) throws SQLException {
		Connection con = pool.getConnction();
		String sql = "DELETE FROM Coupons WHERE ID = ?";
		try {
			PreparedStatement st = con.prepareStatement(sql);
			st.setInt(1, couponID);
			st.executeUpdate();
		} catch (SQLException e) {
			throw new SQLException(e);
		} finally {
			pool.returnConnection(con);
		}
	}

	// Show all coupons // works.
	@Override
	public ArrayList<Coupon> getAllCoupons() throws SQLException {
		Connection con = pool.getConnction();
		ArrayList<Coupon> coupons = new ArrayList<>();
		String sql = "SELECT * FROM Coupons";
		try {
			PreparedStatement st = con.prepareStatement(sql);
			ResultSet rs = st.executeQuery();
			while (rs.next()) {
				int id = rs.getInt("id");
				int companyID = rs.getInt("company_id");
				int amount = rs.getInt("amount");
				Category category = Category.values()[rs.getInt("category_id") - 1];
				String title = rs.getString("title");
				String description = rs.getString("title");
				String image = rs.getString("image");
				Date startDate = rs.getDate("start_Date");
				Date endDate = rs.getDate("end_Date");
				Double price = rs.getDouble("price");

				coupons.add(new Coupon(id, companyID, amount, category, title, description, image, startDate, endDate,
						price));
			}
		} catch (SQLException e) {
			throw new SQLException(e.getMessage());
		} finally {
			pool.returnConnection(con);
		}
		return coupons;
	}
	
	// Show one coupon // works.
	@Override
	public Coupon getOneCoupon(int couponID) throws SQLException {
		Connection con = pool.getConnction();
		String sql = "SELECT * FROM Coupons WHERE ID =?";
		try {
			PreparedStatement st = con.prepareStatement(sql);
			st.setInt(1, couponID);
			ResultSet rs = st.executeQuery();

			if (rs.next()) {
				int id = rs.getInt("id");
				int companyID = rs.getInt("company_id");
				int amount = rs.getInt("amount");
				Category category = Category.values()[rs.getInt("category_id") - 1];
				String title = rs.getString("title");
				String description = rs.getString("title");
				String image = rs.getString("image");
				Date startDate = rs.getDate("start_Date");
				Date endDate = rs.getDate("end_Date");
				Double price = rs.getDouble("price");

				System.out.println(new Coupon(id, companyID, amount, category, title, description, image, startDate,
						endDate, price));
			}

		} catch (SQLException e) {
			throw new SQLException(e.getMessage());
		} finally {
			pool.returnConnection(con);
		}
		return null;
	}

	// Assign the coupon to a customer // works.
	@Override
	public void addCouponPurchase(int customerID, int couponID) throws SQLException {
		Connection con = pool.getConnction();
		String sql = "INSERT INTO Customers_vs_Coupons(Customer_ID,Coupon_ID) VALUES(?,?)";
		try {
			PreparedStatement st = con.prepareStatement(sql);
			st.setInt(1, customerID);
			st.setInt(2, couponID);
			st.executeUpdate();
		} catch (SQLException e) {
			throw new SQLException(e.getMessage());
		} finally {
			pool.returnConnection(con);
		}
	}

	// Deletes the coupon from a customer // works.
	@Override
	public void deleteCouponPurchase(int customerID, int couponID) throws SQLException {
		Connection con = pool.getConnction();
		String sql = "DELETE FROM Customers_vs_Coupons WHERE Customer_ID = ? AND Coupon_ID = ?";
		try {
			PreparedStatement st = con.prepareStatement(sql);
			st.setInt(1, customerID);
			st.setInt(2, couponID);
			st.executeUpdate();
		} catch (SQLException e) {
			throw new SQLException(e.getMessage());
		} finally {
			pool.returnConnection(con);
		}
	}

}
