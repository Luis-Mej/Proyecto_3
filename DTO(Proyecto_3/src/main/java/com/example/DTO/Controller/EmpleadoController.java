package com.example.DTO.Controller;


import com.example.DTO.DTOEmpleado.DTOEmpleado;
import com.example.DTO.Entity.Empleado;
import com.example.DTO.Repository.EmpleadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/empleados")
public class EmpleadoController {

    @Autowired
    private EmpleadoRepository empleadoRepository;

    @GetMapping
    public List<DTOEmpleado> getEmpleados(){
        return  empleadoRepository.findAll().stream().map(empleado -> {
            DTOEmpleado dto = new DTOEmpleado();
            dto.setCedula(empleado.getCedula());
            dto.setNombres_Completos(empleado.getNombres() + " " + empleado.getApellidos());
            dto.setEstado(empleado.getEstado());
            return dto;
        }).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<DTOEmpleado> getEmpleado(@PathVariable Integer id) {
        return empleadoRepository.findById(id).map(empleado -> {
            DTOEmpleado dto = new DTOEmpleado();
            dto.setCedula(empleado.getCedula());
            dto.setNombres_Completos(empleado.getNombres() + " " + empleado.getApellidos());
            dto.setEstado(empleado.getEstado());
            return ResponseEntity.ok(dto);
        }).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Empleado createEmpleado(@RequestBody Empleado empleado) {
        return empleadoRepository.save(empleado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Empleado> updateEmpleado(@PathVariable Integer id, @RequestBody Empleado empleadoDetails) {
        Optional<Empleado> optionalEmpleado = empleadoRepository.findById(id);

        if (optionalEmpleado.isPresent()) {
            Empleado empleado = optionalEmpleado.get();
            empleado.setCedula(empleadoDetails.getCedula());
            empleado.setNombres(empleadoDetails.getNombres());
            empleado.setApellidos(empleadoDetails.getApellidos());
            empleado.setSueldo(empleadoDetails.getSueldo());
            empleado.setEstado(empleadoDetails.getEstado());
            empleado.setUsuarioModificacion(empleadoDetails.getUsuarioModificacion());
            empleado.setFechaModificacion(empleadoDetails.getFechaModificacion());
            return ResponseEntity.ok(empleadoRepository.save(empleado));
        }
        else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmpleado(@PathVariable Integer id) {
        if (empleadoRepository.existsById(id)) {
            empleadoRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        else {
            return ResponseEntity.notFound().build();
        }
    }
}
