package pe.edu.upc.demosi61.dtos;

import java.time.LocalDate;

public class RoomDTO {
    private int idRoom;
    private String nameRoom;
    private  String ubicationRoom;

    public RoomDTO() {
    }

    public RoomDTO(int idRoom, String nameRoom, String ubicationRoom) {
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
