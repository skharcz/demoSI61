package pe.edu.upc.demosi61.serviceimplements;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.demosi61.entities.Room;
import pe.edu.upc.demosi61.repositories.IRoomRepository;
import pe.edu.upc.demosi61.serviceinterfaces.IRoomService;

import java.util.List;

@Service
public class RoomServiceImplement implements IRoomService {
    @Autowired
    private IRoomRepository rR;
    @Override
    public void insert(Room room) {
        rR.save(room);
    }

    @Override
    public List<Room> list() {
        return rR.findAll();
    }

    @Override
    public void delete(int idRoom) {
        rR.deleteById(idRoom);
    }

    @Override
    public Room listId(int idRoom) {
        return rR.findById(idRoom).orElse(new Room());
    }

    @Override
    public void update(Room room) {
        rR.save(room);
    }

    @Override
    public List<Room> findByName(String name) {
        return rR.buscarPorNombre(name);
    }


}
