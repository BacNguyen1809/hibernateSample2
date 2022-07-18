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

import fa.training.entities.CinemaDetail;
import fa.training.entities.CinemaDetail;
import fa.training.utils.HibernateConnectionUtil;

public class CinemaDetailDAOImpl implements CinemaDetailDAO {
	private SessionFactory sessionFactory = null;

	public CinemaDetail getCinemeDetailById(int CinemaDetaillId) {
		sessionFactory = HibernateConnectionUtil.getSessionFactory();
		CinemaDetail cinemaDetail = null;
		try {
			Session session = sessionFactory.openSession();
			session.beginTransaction();
			cinemaDetail = (CinemaDetail) session.get(CinemaDetail.class, CinemaDetaillId);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {

		}
		return cinemaDetail;

	}

	public List<CinemaDetail> getAllCinemeRoomDetail() {
		sessionFactory = HibernateConnectionUtil.getSessionFactory();
		Session session = null;
		try {
			session = sessionFactory.openSession();
			session.beginTransaction();
			Criteria criteria = session.createCriteria(CinemaDetail.class);
			List<CinemaDetail> list = criteria.list();
			// other way
//			@SuppressWarnings("unchecked")
//			List<Seat> list = sessionFactory.openSession().createCriteria(Seat.class).list();
			return list;
		} catch (Exception e) {
			return new ArrayList<CinemaDetail>();
		} finally {
			if (session != null) {
				session.close();
			}
		}
	}

	public String updateCinemaDetailByCinemaDetaillId(Scanner scanner) {
		sessionFactory = HibernateConnectionUtil.getSessionFactory();
		Session session = null;
		try {
			session = sessionFactory.openSession();
			session.beginTransaction();
			String queryStr = "UPDATE CinemaDetail " + "SET ROOM_RATE = :roomRate WHERE CINEMA_DETAIL_ID = :cinemaDetailId";
			Query query = session.createQuery(queryStr);
			System.out.println("Enter Cinema Detailid want to change status:");
			int cinemaDetailId = Integer.parseInt(scanner.nextLine());
			query.setParameter("cinemaDetailId", cinemaDetailId);
			System.out.println("Enter room rate want to change:");
			int roomRate = Integer.parseInt(scanner.nextLine());
			query.setParameter("roomRate", roomRate);
			int results = query.executeUpdate();
			if (results > 0) {
				session.getTransaction().commit();
				return "Update success";

			} else {
				return "No seat Updated";
			}

		} catch (Exception e) {
			return "Update fail\n" + e.getMessage();
		} finally {
			if (session != null) {
				session.close();
			}
		}

	}

	public String deleteCinemaDetailByCinemaDetaillId(Scanner scanner) {
		sessionFactory = HibernateConnectionUtil.getSessionFactory();
		Session session = null;
		try {
			session = sessionFactory.openSession();
			session.beginTransaction();
			String queryStr = "DELETE CinemaDetail " + "WHERE CINEMA_DETAIL_ID = :cinemaDetailId";
			Query query = session.createQuery(queryStr);
			System.out.println("Enter Cinema room id want to delete:");
			int cinemaDetailId = Integer.parseInt(scanner.nextLine());
			query.setParameter("cinemaDetailId", cinemaDetailId);
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

	public String insertCinemaDetail(Scanner scanner) {
		sessionFactory = HibernateConnectionUtil.getSessionFactory();
		Session session = null;
		Set<CinemaDetail> cinemaDetails = new HashSet<CinemaDetail>();
		CinemaDetail cinemaDetail = null;
		String condition;
		do {
			cinemaDetail = new CinemaDetail();
			cinemaDetail.input(scanner);
			cinemaDetails.add(cinemaDetail);
			System.out.println("Do you want to continue?(Y/N)");
			condition = scanner.nextLine();
		} while (condition.equalsIgnoreCase("y"));
		try {
			session = sessionFactory.openSession();
			session.beginTransaction();
			for (CinemaDetail cinemaDetail2 : cinemaDetails) {
				session.save(cinemaDetail2);
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
