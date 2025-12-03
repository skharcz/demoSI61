package pe.edu.upc.demosi61.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.demosi61.dtos.AmountByRoomDTO;
import pe.edu.upc.demosi61.dtos.DeviceDTO;
import pe.edu.upc.demosi61.dtos.QuantityDeviceByRoomDTO;
import pe.edu.upc.demosi61.entities.Device;
import pe.edu.upc.demosi61.serviceinterfaces.IDeviceService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/dispositivos")
public class DeviceController {
    @Autowired
    private IDeviceService dS;

    @PostMapping
    public void registrar(@RequestBody DeviceDTO dto) {
        ModelMapper m = new ModelMapper();
        Device d = m.map(dto, Device.class);
        dS.insert(d);
    }

    @GetMapping
    public List<DeviceDTO> listar() {
        return dS.list().stream().map(x->{
            ModelMapper m=new ModelMapper();
            return m.map(x,DeviceDTO.class);
        }).collect(Collectors.toList());
    }


    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable("id") Integer id) {
        dS.delete(id);
    }

    @GetMapping("/{id}")
    public DeviceDTO listarId(@PathVariable("id") Integer id) {
        ModelMapper m=new ModelMapper();
        DeviceDTO dto=m.map(dS.listId(id),DeviceDTO.class);
        return dto;
    }
    @PutMapping
    public void modificar(@RequestBody DeviceDTO dto) {
        ModelMapper m = new ModelMapper();
        Device d = m.map(dto, Device.class);
        dS.update(d);
    }

    @GetMapping("/buscar")
    public List<DeviceDTO> buscar(@RequestParam LocalDate fecha) {
        return dS.findByFecha(fecha).stream().map(x->{
            ModelMapper m=new ModelMapper();
            return m.map(x,DeviceDTO.class);
        }).collect(Collectors.toList());
    }
//ctrl+alt+O
@GetMapping("/cantidades")
public List<QuantityDeviceByRoomDTO> obtener() {
    List<String[]>lista=dS.obtenerCantidad();
    List<QuantityDeviceByRoomDTO>listaDTO=new ArrayList<>();
    for(String[]columna:lista){
        QuantityDeviceByRoomDTO dto=new QuantityDeviceByRoomDTO();
        dto.setNameRoom(columna[0]);
        dto.setQuantityDevice(Integer.parseInt(columna[1]));
        listaDTO.add(dto);
    }
    return listaDTO;
}
    @GetMapping("/sumas")
    public List<AmountByRoomDTO> obtenerSumas() {
        List<String[]>lista=dS.obtenerSumas();
        List<AmountByRoomDTO>listaDTO=new ArrayList<>();
        for(String[]columna:lista){
            AmountByRoomDTO dto=new AmountByRoomDTO();
            dto.setNameRoom(columna[0]);
            dto.setAmountByRoom(Double.parseDouble(columna[1]));
            listaDTO.add(dto);
        }
        return listaDTO;
    }

}
