/*
	* @author bacng
	* @ Date Jul 17, 2022
*/
package fa.training.application;

import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import fa.training.dao.CinemaDetailDAOImpl;
import fa.training.dao.CinemaRoomDAOImpl;
import fa.training.dao.SeatDAOImpl;
import fa.training.entities.CinemaDetail;
import fa.training.entities.CinemaRoom;
import fa.training.entities.Seat;
import fa.training.utils.HibernateConnectionUtil;

public class ManagementApplication {
	public static void main(String[] args) {
		Scanner  scanner = new Scanner(System.in);
		CinemaRoomDAOImpl cinemaRoomDAOImpl =null;
		boolean check = false;
		do {
			check = true;
			menu();
			System.out.println("Press function:");
			int key = Integer.parseInt(scanner.nextLine());
			switch (key) {
			case 1:
				cinemaRoomDAOImpl = new CinemaRoomDAOImpl();
				System.out.println(cinemaRoomDAOImpl.insertCinemaRoom(scanner));
				break;
			case 2:
				cinemaRoomDAOImpl = new CinemaRoomDAOImpl();
				List<CinemaRoom> allCinemaRoom = cinemaRoomDAOImpl.getAllCinemaRoom();
				for (CinemaRoom cinemaRoom : allCinemaRoom) {
					System.out.println(cinemaRoom);
				}
				break;
			case 3:
				cinemaRoomDAOImpl = new CinemaRoomDAOImpl();
				System.out.println("Enter id want to find:");
				int id = Integer.parseInt(scanner.nextLine());
				CinemaRoom cinemaRoomByCinemaRoomId = cinemaRoomDAOImpl.getCinemaRoomByCinemaRoomId(id);
				System.out.println(cinemaRoomByCinemaRoomId);
				break;
			case 4:
				cinemaRoomDAOImpl = new CinemaRoomDAOImpl();
			
				System.out.println(cinemaRoomDAOImpl.updateCinemaRoomByCinemaRoomId(scanner));
				
				break;
			case 5:
				cinemaRoomDAOImpl = new CinemaRoomDAOImpl();
			
				System.out.println(cinemaRoomDAOImpl.deleteCinemaRoomByCinemaRoomId(scanner));
				
				break;	
					
				

			case 0:
				check =false;
				break;
			}
		} while (check);


	}

	private static void menu() {
		System.out.println("Press 1: Create new Cinema room \n"
							+"Press 2: Find All Cinema room \n"
							+"Press 3: Find Cinema room by room Id \n"
							+"Press 4: Update Cinema room  by room Id\n"
							+"Press 5: Delete Cinema room  by room Id\n"
							+"Press 6: Create new Cinema Detail\n"
							+"Press 7: Find All Cinema Detail\n"
							+"Press 8: Find Cinema Detail by room Id \n"
							+"Press 9: Update Cinema Detail  by room Id\n"
							+"Press 10: Delete Cinema Detail by room Id\n"
							+"Press 11: Create new seat\n"
							+"Press 12: Find All seat\n"
							+"Press 13: Find seat by seat Id \n"
							+"Press 14: Update seat by seat Id\n"
							+"Press 15: Delete seat by seat Id\n"
							+"Press 0: Exit");
		
	}

//	private static void createSeat() {
//		SessionFactory sessionFactory = HibernateConnectionUtil.getSessionFactory();
//		Set<Seat> seats = new HashSet<Seat>();
//		CinemaRoom cinemaRoom = new CinemaRoom("Romm1", 2);
//		seats.add(new Seat("1", 1, "Available", "VIP",cinemaRoom));
//		seats.add(new Seat("2", 2, "Available", "VIP",cinemaRoom));
//		
//		try {
//			Session session = sessionFactory.openSession();
//			session.beginTransaction();
//			session.save(cinemaRoom);
//			for (Seat seat : seats) {
//				session.save(seat);
//			}
//			
//			session.getTransaction().commit();
//			session.close();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//
//	}

//	private static void createCinameRoom() {
//		SessionFactory sessionFactory = HibernateConnectionUtil.getSessionFactory();
//		try {
//			Session session = sessionFactory.openSession();
//			session.beginTransaction();
//			CinemaRoom cinemaRoom = new CinemaRoom("Room 1", 100);
//			session.save(cinemaRoom);
//			session.getTransaction().commit();
//			session.close();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//
//	}

}
