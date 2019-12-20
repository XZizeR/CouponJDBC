package DBDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Date;

import Beans.Category;
import Beans.Company;
import Beans.ConnectionPool;
import Beans.Coupon;
import DAO.CompaniesDAO;


public class CompaniesDBDAO implements CompaniesDAO {
	// Create connection
	private ConnectionPool pool = ConnectionPool.getInstance();

	// Company exists // works.
	@Override
	public boolean isCompanyExists(String email, String password) throws SQLException {
		Connection con = pool.getConnction();
		String sql = "SELECT * FROM Companies WHERE Email=? AND Password = ?";
		try {
			boolean exist = false;
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, email);
			st.setString(2, password);
			ResultSet rs = st.executeQuery();
			if (rs.next()) {
				exist = true;
				if (exist)
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
	@Override
	public int isCompanyExistsInt(String email, String password) throws SQLException {
		Connection con = pool.getConnction();
		String sql = "SELECT * FROM Companies WHERE Email=? AND Password = ?";
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

	// Add one company // works.
	@Override
	public void addCompany(Company company) throws SQLException {
		Connection con = pool.getConnction();
		String sql = "INSERT INTO Companies( Name, Email, Password) VALUES(?,?,?)";
		try {
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, company.getName());
			st.setString(2, company.getEmail());
			st.setString(3, company.getPassword());

			st.execute();
		} catch (SQLException e) {
			throw new SQLException(e.getMessage());
		} finally {
			pool.returnConnection(con);
		}
	}

	// Update one company // works.
	@Override
	public void updateCompany(Company company) throws SQLException {
		Connection con = pool.getConnction();
		String sql = "UPDATE Companies SET Name = ?, Email = ?, Password = ? WHERE ID = ?";
		try {
			PreparedStatement st = con.prepareStatement(sql);
			st.setInt(4, company.getId());
			st.setString(1, company.getName());
			st.setString(2, company.getEmail());
			st.setString(3, company.getPassword());

			st.execute();
		} catch (SQLException e) {
			throw new SQLException(e.getMessage());
		} finally {
			pool.returnConnection(con);
		}
	}

	// Deletes one company // works.
	@Override
	public void deleteCompany(int companyID) throws SQLException {
		Connection con = pool.getConnction();
		String sql = "DELETE FROM Companies WHERE Id = ?";
		try {
			PreparedStatement st = con.prepareStatement(sql);
			st.setInt(1, companyID);
			
			st.executeUpdate();
		} catch (SQLException e) {
			throw new SQLException(e.getMessage());
		} finally {
			pool.returnConnection(con);
		}
	}

	// Show one company // works.
	@Override
	public Company getOneCompany(int companyID) throws SQLException {
		int id = 0;
		String name = null, email = null, password = null;		
		Connection con = pool.getConnction();
		ArrayList<Coupon> coupons = new ArrayList<Coupon>();
		try {
			PreparedStatement st1 = con.prepareStatement("SELECT * FROM Companies WHERE ID =?");
			st1.setInt(1, companyID);
			ResultSet rs1 = st1.executeQuery();
			if (rs1.next()) {
				id = rs1.getInt("id");
				name = rs1.getString("name");
				email = rs1.getString("email");
				password = rs1.getString("password");
			}
			
			PreparedStatement st2 = con.prepareStatement("SELECT * FROM Coupons WHERE Company_ID =?");
			st2.setInt(1, companyID);
			ResultSet rs2 = st2.executeQuery();
			while (rs2.next()) {
				int idCoupon = rs2.getInt("id");
				companyID = rs2.getInt("company_id");
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
		return new Company(id, name, email, password, coupons);
	}

	// Show all companies // works.
	@Override
	public ArrayList<Company> getAllCompanies() throws SQLException {
		Connection con = pool.getConnction();
		ArrayList<Company> companies = new ArrayList<Company>();
		String sql = "SELECT * FROM Companies";
		try {
			PreparedStatement st = con.prepareStatement(sql);
			ResultSet rs = st.executeQuery();
			while (rs.next()) {
				companies.add(getOneCompany(rs.getInt("id")));
			}
		} catch (SQLException e) {
			throw new SQLException(e.getMessage());
		} finally {
			pool.returnConnection(con);
		}
		return companies;
	}
}