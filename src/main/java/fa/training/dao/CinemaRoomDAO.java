/*
	* @author bacng
	* @ Date Jul 17, 2022
*/
package fa.training.dao;

import java.util.List;
import java.util.Scanner;

import fa.training.entities.CinemaRoom;


public interface CinemaRoomDAO {
	CinemaRoom getCinemaRoomByCinemaRoomId(int CinemaRoomId);

	List<CinemaRoom> getAllCinemaRoom();

	String updateCinemaRoomByCinemaRoomId(Scanner scanner);

	String deleteCinemaRoomByCinemaRoomId(Scanner scanner);

	String insertCinemaRoom(Scanner scanner);

}
