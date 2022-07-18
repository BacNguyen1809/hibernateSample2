/*
	* @author bacng
	* @ Date Jul 17, 2022
*/
package fa.training.dao;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

import javax.persistence.criteria.CriteriaBuilder;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import fa.training.entities.Seat;
import fa.training.utils.HibernateConnectionUtil;

public class SeatDAOImpl implements SeatDAO {
	private static SessionFactory sessionFactory = null;

	public Seat getSeatBySeatId(int SeatId) {

		sessionFactory = HibernateConnectionUtil.getSessionFactory();
		Seat seat = null;
		try {
			Session session = sessionFactory.openSession();
			session.beginTransaction();
			seat = (Seat) session.get(Seat.class, SeatId);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {

		}
		return seat;

	}

	public List<Seat> getAllSeat() {

		sessionFactory = HibernateConnectionUtil.getSessionFactory();
		Session session = null;
		try {
			session = sessionFactory.openSession();
			session.beginTransaction();
			Criteria criteria = session.createCriteria(Seat.class);
			List<Seat> list = criteria.list();
			// other way
//			@SuppressWarnings("unchecked")
//			List<Seat> list = sessionFactory.openSession().createCriteria(Seat.class).list();
			return list;
		} catch (Exception e) {
			return new ArrayList<Seat>();
		} finally {
			if (session != null) {
				session.close();
			}
		}

	}

	public void updateSeatBySeatId(Scanner scanner) {
		sessionFactory = HibernateConnectionUtil.getSessionFactory();
		Session session = null;
		try {
			session = sessionFactory.openSession();
			session.beginTransaction();
			String queryStr = "UPDATE Seat " + "SET SEAT_STATUS = :seatStatus WHERE SEAT_ID = :SeatId";
			Query query = session.createQuery(queryStr);
			System.out.println("Enter seat id want to change status:");
			int seatId = Integer.parseInt(scanner.nextLine());
			query.setParameter("SeatId", seatId);
			System.out.println("Enter seat status want to change:");
			String seatStatus = scanner.nextLine();
			query.setParameter("seatStatus", seatStatus);
			int results = query.executeUpdate();
			session.getTransaction().commit();
			session.close();
			if (results > 0) {
				System.out.println("update success");
			}

		} catch (Exception e) {
			System.out.println("update fail");
			e.printStackTrace();
		} finally {
			if (session != null) {
				session.close();
			}
		}

	}

	public String insertSeat(Scanner scanner) {
		sessionFactory = HibernateConnectionUtil.getSessionFactory();
		Session session = null;
		Set<Seat> seats = new HashSet<Seat>();
		Seat seat = new Seat();
		String condition;
		do {
			seat.input(scanner);
			seats.add(seat);
			System.out.println("Do you want to continue?(Y/N)");
			condition = scanner.nextLine();
		} while (condition.equalsIgnoreCase("y"));
		try {
			session = sessionFactory.openSession();
			session.beginTransaction();
			for (Seat seat2 : seats) {
				session.save(seat2);
			}
			session.getTransaction().commit();
			return "Insert success";

		} catch (Exception e) {
			return "insert fail" + e.getMessage();

		} finally {
			if (session != null) {
				session.close();
			}

		}

	}

	public String deleteSeatBySeatId(Scanner scanner) {
		sessionFactory = HibernateConnectionUtil.getSessionFactory();
		Session session = null;
		try {
			session = sessionFactory.openSession();
			session.beginTransaction();
			String queryStr = "DELETE Seat " + "WHERE SEAT_ID = :SeatId";
			Query query = session.createQuery(queryStr);
			System.out.println("Enter seat id want to delete:");
			int seatId = Integer.parseInt(scanner.nextLine());
			query.setParameter("SeatId", seatId);
			int results = query.executeUpdate();

			if (results > 0) {
				session.getTransaction().commit();
				return "Delete success";

			} else {
				return "No seat deleted";
			}

		} catch (Exception e) {
			return "Delete fail\n" + e.getMessage();

		} finally {
			if (session != null) {
				session.close();
			}
		}
	}

}
