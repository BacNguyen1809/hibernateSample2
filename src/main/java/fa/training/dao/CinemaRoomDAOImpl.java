/*
	* @author bacng
	* @ Date Jul 18, 2022
*/
package fa.training.dao;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import fa.training.entities.CinemaRoom;
import fa.training.utils.HibernateConnectionUtil;

public class CinemaRoomDAOImpl implements CinemaRoomDAO {
	private SessionFactory sessionFactory = null;

	public CinemaRoom getCinemaRoomByCinemaRoomId(int CinemaRoomId) {
		sessionFactory = HibernateConnectionUtil.getSessionFactory();
		CinemaRoom cinemaRoom = null;
		try {
			Session session = sessionFactory.openSession();
			session.beginTransaction();
			cinemaRoom = (CinemaRoom) session.get(CinemaRoom.class, CinemaRoomId);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {

		}
		return cinemaRoom;
	}

	public List<CinemaRoom> getAllCinemaRoom() {

		sessionFactory = HibernateConnectionUtil.getSessionFactory();
		Session session = null;
		try {
			session = sessionFactory.openSession();
			session.beginTransaction();
			Criteria criteria = session.createCriteria(CinemaRoom.class);
			List<CinemaRoom> list = criteria.list();
			// other way
//			@SuppressWarnings("unchecked")
//			List<Seat> list = sessionFactory.openSession().createCriteria(Seat.class).list();
			return list;
		} catch (Exception e) {
			return new ArrayList<CinemaRoom>();
		} finally {
			if (session != null) {
				session.close();
			}
		}
	}

	public String updateCinemaRoomByCinemaRoomId(Scanner scanner) {
		sessionFactory = HibernateConnectionUtil.getSessionFactory();
		Session session = null;
		try {
			session = sessionFactory.openSession();
			session.beginTransaction();
			String queryStr = "UPDATE CinemaRoom "
					+ "SET SEAT_QUANTITY = :seatQuantity WHERE CINEMA_ROOM_ID = :cinemeRommID";
			Query query = session.createQuery(queryStr);
			System.out.println("Enter cinema room id want to change status:");
			int cinemaRoomId = Integer.parseInt(scanner.nextLine());
			query.setParameter("cinemeRommID", cinemaRoomId);
			System.out.println("Enter seat quantity want to change:");
			int seatQuantity = Integer.parseInt(scanner.nextLine());
			query.setParameter("seatQuantity", seatQuantity);
			int results = query.executeUpdate();
			if (results > 0) {
				session.getTransaction().commit();
				return "Update success";

			} else {
				return "No seat updated";
			}

		} catch (Exception e) {
			return "Updated fail\n" + e.getMessage();
		} finally {
			if (session != null) {
				session.close();
			}
		}

	}

	public String deleteCinemaRoomByCinemaRoomId(Scanner scanner) {
		sessionFactory = HibernateConnectionUtil.getSessionFactory();
		Session session = null;
		try {
			session = sessionFactory.openSession();
			session.beginTransaction();
			String queryStr = "DELETE CinemaRoom " + "WHERE CINEMA_ROOM_ID = :cinemaRommID";
			Query query = session.createQuery(queryStr);
			System.out.println("Enter Cinema room id want to delete:");
			int cinemaRommID = Integer.parseInt(scanner.nextLine());
			query.setParameter("cinemaRommID", cinemaRommID);
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

	public String insertCinemaRoom(Scanner scanner) {
		sessionFactory = HibernateConnectionUtil.getSessionFactory();
		Session session = null;
		Set<CinemaRoom> cinemaRooms = new HashSet<CinemaRoom>();
		CinemaRoom cinemaRoom = null;
		String condition;
		do {
			cinemaRoom = new CinemaRoom();
			cinemaRoom.input(scanner);
			cinemaRooms.add(cinemaRoom);
			System.out.println("Do you want to continue?(Y/N)");
			condition = scanner.nextLine();
		} while (condition.equalsIgnoreCase("y"));
		try {
			session = sessionFactory.openSession();
			session.beginTransaction();
			for (CinemaRoom cinema2 : cinemaRooms) {
				session.save(cinema2);
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

}
