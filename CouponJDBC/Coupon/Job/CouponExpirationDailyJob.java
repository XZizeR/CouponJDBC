package Job;


import java.util.ArrayList;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;

import Beans.Coupon;
import Beans.Customer;
import DBDAO.CouponsDBDAO;
import Facade.AdminFacade;

public class CouponExpirationDailyJob implements Runnable {
	
	//	Fields
	private CouponsDBDAO couponsDBDAO = new CouponsDBDAO();
	private boolean quit = false;
	
	//	Constructor
	public CouponExpirationDailyJob() {
		super();
	}

	//	Run
	@Override
	public void run() {
		while(!quit) {
			try {
				Calendar cal = Calendar.getInstance();
				ArrayList<Coupon> coupons = couponsDBDAO.getAllCoupons();
				for (Coupon coup : coupons) {
					// if coupon found out dated - delete it.
					if(coup.getEndDate().before(cal.getTime())) {
						System.out.println("deleted coupon: "+coup.getId());
						couponsDBDAO.deleteCoupon(coup.getId());
						
					}
					// to the next time.
					System.out.println("Successfully launched CouponExpirationDailyJob");
					TimeUnit.HOURS.sleep(24);
				}
			}catch(Exception e) {
				System.out.println(e.getMessage());
			}
		}
	}
	
	//	Stop
	public void stop() {
		quit = true;
	}
}
