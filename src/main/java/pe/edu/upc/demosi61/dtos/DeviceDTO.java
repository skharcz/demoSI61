package pe.edu.upc.demosi61.dtos;

import pe.edu.upc.demosi61.entities.Room;

import java.time.LocalDate;

public class DeviceDTO {
    private int idDevice;
    private String nameDevice;
    private  String typeDevice;
    private  double priceDevice;
    private LocalDate purchaseDateDevice;
    private  int numberDeviceMaintenance;

    private Room room;

    public int getNumberDeviceMaintenance() {
        return numberDeviceMaintenance;
    }

    public void setNumberDeviceMaintenance(int numberDeviceMaintenance) {
        this.numberDeviceMaintenance = numberDeviceMaintenance;
    }

    public int getIdDevice() {
        return idDevice;
    }

    public void setIdDevice(int idDevice) {
        this.idDevice = idDevice;
    }

    public String getNameDevice() {
        return nameDevice;
    }

    public void setNameDevice(String nameDevice) {
        this.nameDevice = nameDevice;
    }

    public String getTypeDevice() {
        return typeDevice;
    }

    public void setTypeDevice(String typeDevice) {
        this.typeDevice = typeDevice;
    }

    public double getPriceDevice() {
        return priceDevice;
    }

    public void setPriceDevice(double priceDevice) {
        this.priceDevice = priceDevice;
    }

    public LocalDate getPurchaseDateDevice() {
        return purchaseDateDevice;
    }

    public void setPurchaseDateDevice(LocalDate purchaseDateDevice) {
        this.purchaseDateDevice = purchaseDateDevice;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }
}
