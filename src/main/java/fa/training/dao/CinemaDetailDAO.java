/*
	* @author bacng
	* @ Date Jul 17, 2022
*/
package fa.training.dao;

import java.util.List;
import java.util.Scanner;

import fa.training.entities.CinemaDetail;


public interface CinemaDetailDAO {
	CinemaDetail getCinemeDetailById(int CinemaDetaillId);

	List<CinemaDetail> getAllCinemeRoomDetail();

	String updateCinemaDetailByCinemaDetaillId(Scanner scanner);

	String deleteCinemaDetailByCinemaDetaillId(Scanner scanner);

	String insertCinemaDetail(Scanner scanner);

}
