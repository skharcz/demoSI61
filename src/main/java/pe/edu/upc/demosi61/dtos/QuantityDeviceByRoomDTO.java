package pe.edu.upc.demosi61.dtos;

public class QuantityDeviceByRoomDTO {
    private String nameRoom;
    private  int quantityDevice;

    public String getNameRoom() {
        return nameRoom;
    }

    public void setNameRoom(String nameRoom) {
        this.nameRoom = nameRoom;
    }

    public int getQuantityDevice() {
        return quantityDevice;
    }

    public void setQuantityDevice(int quantityDevice) {
        this.quantityDevice = quantityDevice;
    }
}
