/*
	* @author bacng
	* @ Date Jul 17, 2022
*/
package fa.training.entities;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class CinemaRoom {
	@Id
	@Column(name = "CINEMA_ROOM_ID")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int cinemaRoomId;
	@Column(name = "CINEMA_ROOM_NAME")
	private String cinemaRoomName;
	@Column(name = "SEAT_QUANTITY")
	private int seatQuantity;
	@OneToMany(mappedBy = "cinemaRoom")
	private Set<Seat> seats = new HashSet<Seat>();
	
	public Set<Seat> getSeats() {
		return seats;
	}
	public void setSeats(Set<Seat> seats) {
		this.seats = seats;
	}
	public int getCinemaRoomId() {
		return cinemaRoomId;
	}
	public void setCinemaRoomId(int cinemaRoomId) {
		this.cinemaRoomId = cinemaRoomId;
	}
	public String getCinemaRoomName() {
		return cinemaRoomName;
	}
	public void setCinemaRoomName(String cinemaRoomName) {
		this.cinemaRoomName = cinemaRoomName;
	}
	public int getSeatQuantity() {
		return seatQuantity;
	}
	public void setSeatQuantity(int seatQuantity) {
		this.seatQuantity = seatQuantity;
	}

	public CinemaRoom(String cinemaRoomName, int seatQuantity) {
		super();
		this.cinemaRoomName = cinemaRoomName;
		this.seatQuantity = seatQuantity;
		
	}
	public CinemaRoom() {
		super();
	}
	@Override
	public String toString() {
		return "cinemaRoomId=" + cinemaRoomId + ", cinemaRoomName=" + cinemaRoomName + ", seatQuantity="
				+ seatQuantity ;
	}
	public void input(Scanner scanner) {
		boolean check = false;
		
		System.out.println("Enter cinema room name:");
		cinemaRoomName = scanner.nextLine();
		do {
			try {
				System.out.println("Enter seat quantity:");
				seatQuantity = Integer.parseInt(scanner.nextLine());
				check = false;
			} catch (Exception e) {
				System.out.println("try again");
				check = true;
			}
		} while (check);
		
		
	}
	
	

}
