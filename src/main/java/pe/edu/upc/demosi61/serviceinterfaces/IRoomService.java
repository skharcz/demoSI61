package pe.edu.upc.demosi61.serviceinterfaces;

import pe.edu.upc.demosi61.entities.Room;

import java.time.LocalDate;
import java.util.List;

public interface IRoomService {
    public void insert(Room room);
    public List<Room> list();
    public void delete(int idRoom);
    public Room listId(int idRoom);
    public void update(Room room);
    public List<Room> findByName(String name);

}
