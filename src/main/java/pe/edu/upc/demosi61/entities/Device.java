package pe.edu.upc.demosi61.entities;

import jakarta.persistence.*;

import java.time.LocalDate;
@Entity
@Table(name = "Device")
public class Device {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idDevice;
    @Column(name = "nameDevice",nullable = false,length = 40)
    private String nameDevice;
    @Column(name = "typeDevice",nullable = false,length = 40)
    private  String typeDevice;
    @Column(name = "priceDevice",nullable = false)
    private  double priceDevice;
    @Column(name = "purchaseDateDevice",nullable = false)
    private LocalDate purchaseDateDevice;
    @Column(name = "numberDeviceMaintenance")
    private  int numberDeviceMaintenance;
    @ManyToOne
    @JoinColumn(name = "idRoom")
    private  Room room;

    public Device() {
    }

    public Device(int idDevice, String nameDevice, String typeDevice, double priceDevice, LocalDate purchaseDateDevice, int numberDeviceMaintenance, Room room) {
        this.idDevice = idDevice;
        this.nameDevice = nameDevice;
        this.typeDevice = typeDevice;
        this.priceDevice = priceDevice;
        this.purchaseDateDevice = purchaseDateDevice;
        this.numberDeviceMaintenance = numberDeviceMaintenance;
        this.room = room;
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

    public int getNumberDeviceMaintenance() {
        return numberDeviceMaintenance;
    }

    public void setNumberDeviceMaintenance(int numberDeviceMaintenance) {
        this.numberDeviceMaintenance = numberDeviceMaintenance;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }
}
