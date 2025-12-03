package pe.edu.upc.demosi61.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pe.edu.upc.demosi61.entities.Device;
import pe.edu.upc.demosi61.entities.Room;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface IDeviceRepository extends JpaRepository<Device, Integer> {
    public List<Device> findByPurchaseDateDevice(LocalDate fecha);
    @Query(value = "SELECT r.name_room, COUNT(d.id_device) \n" +
            " FROM Device d \n" +
            " JOIN Room r ON d.id_room = r.id_room \n" +
            " GROUP BY r.name_room ",nativeQuery = true)
    public List<String[]> cantidad();

    @Query(value ="SELECT r.name_room, sum(d.price_device)   \n" +
            "              from Device d   \n" +
            "             JOIN Room r ON d.id_room = r.id_room   \n" +
            "             group by r.name_room " ,nativeQuery = true)
    public List<String[]>sumas();
}
