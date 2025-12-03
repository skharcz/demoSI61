package pe.edu.upc.demosi61.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.demosi61.dtos.RoomDTO;
import pe.edu.upc.demosi61.entities.Room;
import pe.edu.upc.demosi61.serviceinterfaces.IRoomService;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/habitaciones")
public class RoomController {
    @Autowired
    private IRoomService rS;

    @PostMapping
    public void registrar(@RequestBody RoomDTO dto) {
        ModelMapper m = new ModelMapper();
        Room d = m.map(dto, Room.class);
        rS.insert(d);
    }

    @GetMapping
    public List<RoomDTO> listar() {
        return rS.list().stream().map(x->{
            ModelMapper m=new ModelMapper();
            return m.map(x,RoomDTO.class);
        }).collect(Collectors.toList());
    }


    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable("id") Integer id) {
        rS.delete(id);
    }

    @GetMapping("/{id}")
    public RoomDTO listarId(@PathVariable("id") Integer id) {
        ModelMapper m=new ModelMapper();
        RoomDTO dto=m.map(rS.listId(id),RoomDTO.class);
        return dto;
    }
    @PutMapping
    public void modificar(@RequestBody RoomDTO dto) {
        ModelMapper m = new ModelMapper();
        Room d = m.map(dto, Room.class);
        rS.update(d);
    }

    @GetMapping("/buscar")
    public List<RoomDTO> buscar(@RequestParam String nombre) {
        return rS.findByName(nombre).stream().map(x->{
            ModelMapper m=new ModelMapper();
            return m.map(x,RoomDTO.class);
        }).collect(Collectors.toList());
    }

}
