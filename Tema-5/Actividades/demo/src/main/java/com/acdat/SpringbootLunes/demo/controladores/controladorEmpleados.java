package com.acdat.SpringbootLunes.demo.controladores;

import com.acdat.SpringbootLunes.demo.dao.IEmpleadosDAO;
import com.acdat.SpringbootLunes.demo.jpa.EmpleadosEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api-rest/empleados")
public class controladorEmpleados {
    @Autowired
    IEmpleadosDAO empleadosDAO;

    @GetMapping
    public List<EmpleadosEntity> buscarEmpleados() {
        return (List<EmpleadosEntity>) empleadosDAO.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmpleadosEntity> buscarEmpleadosPorId(@PathVariable(value = "id")int id) {
        Optional<EmpleadosEntity> empleado = empleadosDAO.findById(id);

        if (empleado.isPresent()) {
            return ResponseEntity.ok().body(empleado.get());
        }else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public EmpleadosEntity guardarEmpleados(@Validated @RequestBody EmpleadosEntity empleado) {
        return empleadosDAO.save(empleado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> borrarEmpleado(@PathVariable(value = "id") int id) {
        Optional<EmpleadosEntity> empleado = empleadosDAO.findById(id);
        if (empleado.isPresent()) {
            empleadosDAO.deleteById(id);
            return ResponseEntity.ok().body("Borrado");
        }else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizarEmpleado(@RequestBody EmpleadosEntity nuevoEmpleado, @PathVariable(value =  "id") int id) {
        Optional<EmpleadosEntity> empleado = empleadosDAO.findById(id);
        if (empleado.isPresent()) {
            empleado.get().setNombre(nuevoEmpleado.getNombre());
            empleado.get().setDepno(nuevoEmpleado.getDepno());
            empleado.get().setPuesto(nuevoEmpleado.getPuesto());
            empleadosDAO.save(empleado.get());
            return ResponseEntity.ok().body("Update");
        }else {
            return ResponseEntity.notFound().build();
        }
    }

}
