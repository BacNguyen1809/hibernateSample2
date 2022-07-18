/*
	* @author bacng
	* @ Date Jul 17, 2022
*/
package fa.training.entities;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import fa.training.dao.CinemaRoomDAOImpl;

@Entity
public class CinemaDetail {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "CINEMA_DETAIL_ID")
	private int cinemaDetailId;
	@OneToOne
	private CinemaRoom cinemaRoom;
	@Column(name = "ROOM_RATE")
	private int roomRate;
	@Column(name = "ACTIVE_DATE")
	private LocalDate activeDate;
	@Column(name = "ROOM_DESCRIPTION")
	private String roomDescription;

	public CinemaRoom getCinemaRoom() {
		return cinemaRoom;
	}

	public void setCinemaRoom(CinemaRoom cinemaRoom) {
		this.cinemaRoom = cinemaRoom;
	}

	public int getRoomRate() {
		return roomRate;
	}

	public void setRoomRate(int roomRate) {
		this.roomRate = roomRate;
	}

	public LocalDate getActiveDate() {
		return activeDate;
	}

	public void setActiveDate(LocalDate activeDate) {
		this.activeDate = activeDate;
	}

	public String getRoomDescription() {
		return roomDescription;
	}

	public void setRoomDescription(String roomDescription) {
		this.roomDescription = roomDescription;
	}

	public CinemaDetail(CinemaRoom cinemaRoom, int roomRate, LocalDate activeDate, String roomDescription) {
		super();
		this.cinemaRoom = cinemaRoom;
		this.roomRate = roomRate;
		this.activeDate = activeDate;
		this.roomDescription = roomDescription;
	}

	public CinemaDetail() {
		super();
	}
	

	@Override
	public String toString() {
		return "cinemaDetailId=" + cinemaDetailId + ", cinemaRoomID=" + cinemaRoom.getCinemaRoomId() + ", roomRate="
				+ roomRate + ", activeDate=" + activeDate + ", roomDescription=" + roomDescription ;
	}

	public void input(Scanner scanner) {
		System.out.println("Enter cinema room id:");
		int id = Integer.parseInt(scanner.nextLine());
		CinemaRoomDAOImpl cImpl = new CinemaRoomDAOImpl();
		CinemaRoom cinemaRoomByCinemaRoomId = cImpl.getCinemaRoomByCinemaRoomId(id);
		cinemaRoom = cinemaRoomByCinemaRoomId;
		System.out.println("Enter room rate");
		roomRate = Integer.parseInt(scanner.nextLine());
		System.out.println("Enter active date");
		DateTimeFormatter dayFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		String date = scanner.nextLine();
		activeDate = LocalDate.parse(date, dayFormatter);
		System.out.println("Enter room description");
		roomDescription = scanner.nextLine();

	}

}
