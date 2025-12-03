package pe.edu.upc.demosi61.serviceimplements;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.demosi61.entities.Device;
import pe.edu.upc.demosi61.repositories.IDeviceRepository;
import pe.edu.upc.demosi61.serviceinterfaces.IDeviceService;

import java.time.LocalDate;
import java.util.List;

@Service
public class DeviceServiceImplement implements IDeviceService {
    @Autowired
    private IDeviceRepository dR;

    @Override
    public void insert(Device device) {
        dR.save(device);
    }

    @Override
    public List<Device> list() {
        return dR.findAll();
    }

    @Override
    public void delete(int idDevice) {
        dR.deleteById(idDevice);
    }

    @Override
    public Device listId(int idDevice) {
        return dR.findById(idDevice).orElse(new Device());
    }

    @Override
    public void update(Device device) {
        dR.save(device);
    }

    @Override
    public List<Device> findByFecha(LocalDate fecha) {
        return dR.findByPurchaseDateDevice(fecha);
    }

    @Override
    public List<String[]> obtenerCantidad() {
        return dR.cantidad();
    }

    @Override
    public List<String[]> obtenerSumas() {
        return dR.sumas();
    }
}
