package pe.edu.upc.demosi61.entities;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "Room")
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idRoom;
    @Column(name = "nameRoom",nullable = false,length = 40)
    private String nameRoom;
    @Column(name = "locationRoom",nullable = false,length = 40)
    private  String ubicationRoom;

    public Room() {
    }

    public Room(int idRoom, String nameRoom, String ubicationRoom) {
        this.idRoom = idRoom;
        this.nameRoom = nameRoom;
        this.ubicationRoom = ubicationRoom;
    }

    public int getIdRoom() {
        return idRoom;
    }

    public void setIdRoom(int idRoom) {
        this.idRoom = idRoom;
    }

    public String getNameRoom() {
        return nameRoom;
    }

    public void setNameRoom(String nameRoom) {
        this.nameRoom = nameRoom;
    }

    public String getUbicationRoom() {
        return ubicationRoom;
    }

    public void setUbicationRoom(String ubicationRoom) {
        this.ubicationRoom = ubicationRoom;
    }
}
