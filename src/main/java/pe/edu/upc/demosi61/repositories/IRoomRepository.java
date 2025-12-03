package pe.edu.upc.demosi61.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pe.edu.upc.demosi61.entities.Room;

import java.time.LocalDate;
import java.util.List;


@Repository
public interface IRoomRepository extends JpaRepository<Room, Integer> {
    @Query("SELECT m FROM Room m WHERE m.nameRoom LIKE %:nombre%")
    public List<Room> buscarPorNombre(@Param("nombre") String nombre);

}
