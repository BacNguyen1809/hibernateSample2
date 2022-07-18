/*
	* @author bacng
	* @ Date Jul 17, 2022
*/
package fa.training.entities;

import java.util.Scanner;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import fa.training.dao.CinemaRoomDAOImpl;

@Entity
public class Seat {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "SEAT_ID")
	private int id;
	@Column(name = "SEAT_COLUMN")
	private String seatColumn;
	@Column(name = "SEAT_ROW")
	private int seatRow;
	@Column(name = "SEAT_STATUS")
	@Pattern(regexp = "Available|Not Available|Booked")
	@NotNull
	private String seatStatus;
	@Column(name = "SEAT_TYPE")
	@Pattern(regexp = "VIP|Normal")
	@NotNull
	private String seatType;
	@ManyToOne
	@JoinColumn(name = "CINEMA_ROOM_ID", nullable = false)
	private CinemaRoom cinemaRoom;
	
	public CinemaRoom getCinemaRoom() {
		return cinemaRoom;
	}

	public void setCinemaRoom(CinemaRoom cinemaRoom) {
		this.cinemaRoom = cinemaRoom;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getSeatColumn() {
		return seatColumn;
	}

	public void setSeatColumn(String seatColumn) {
		this.seatColumn = seatColumn;
	}

	public int getSeatRow() {
		return seatRow;
	}

	public void setSeatRow(int seatRow) {
		this.seatRow = seatRow;
	}

	public String getSeatStatus() {
		return seatStatus;
	}

	public void setSeatStatus(String seatStatus) {
		this.seatStatus = seatStatus;
	}

	public String getSeatType() {
		return seatType;
	}

	public void setSeatType(String seatType) {
		this.seatType = seatType;
	}



	public Seat(String seatColumn, int seatRow, String seatStatus, String seatType, CinemaRoom cinemaRoom) {
		super();
		this.seatColumn = seatColumn;
		this.seatRow = seatRow;
		this.seatStatus = seatStatus;
		this.seatType = seatType;
		this.cinemaRoom = cinemaRoom;
	}

	public Seat() {
		super();
	}

	@Override
	public String toString() {
		return "id=" + id + ", seatColumn=" + seatColumn + ", seatRow=" + seatRow + ", seatStatus=" + seatStatus
				+ ", seatType=" + seatType + ", cinemaRoomID=" + cinemaRoom.getCinemaRoomId() ;
	}

	public void input(Scanner scanner) {
		boolean check = false;
		System.out.println("Enter seat column: ");
		seatColumn = scanner.nextLine();

		do {
			try {
				System.out.println("Enter seat row: ");
				seatRow = Integer.parseInt(scanner.nextLine());
				check = false;
			} catch (Exception e) {
				System.out.println("try again");
				check = true;
			}
		} while (check);
		System.out.println("Enter seat status: ");
		seatStatus = scanner.nextLine();
		System.out.println("Enter seat type:");
		seatType = scanner.nextLine();
		System.out.println("Enter cinema room id:");
		int id = Integer.parseInt(scanner.nextLine());
		CinemaRoomDAOImpl cImpl = new CinemaRoomDAOImpl();
		CinemaRoom cinemaRoomByCinemaRoomId = cImpl.getCinemaRoomByCinemaRoomId(id);
		cinemaRoom = cinemaRoomByCinemaRoomId;

	}

}
