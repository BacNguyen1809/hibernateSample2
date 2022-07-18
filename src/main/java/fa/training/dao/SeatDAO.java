/*
	* @author bacng
	* @ Date Jul 17, 2022
*/
package fa.training.dao;

import java.util.List;
import java.util.Scanner;

import fa.training.entities.Seat;

interface SeatDAO {
	Seat getSeatBySeatId(int SeatId);

	List<Seat> getAllSeat();

	void updateSeatBySeatId(Scanner scanner);

	String deleteSeatBySeatId(Scanner scanner);

	String insertSeat(Scanner scanner);

}
