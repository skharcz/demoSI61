package pe.edu.upc.demosi61.serviceinterfaces;

import pe.edu.upc.demosi61.entities.Device;

import java.time.LocalDate;
import java.util.List;

public interface IDeviceService {
    public void insert(Device device);
    public List<Device> list();
    public void delete(int idDevice);
    public Device listId(int idDevice);
    public void update(Device device);
    public List<Device> findByFecha(LocalDate fecha);
    public List<String[]> obtenerCantidad();
    public List<String[]>obtenerSumas();

}
