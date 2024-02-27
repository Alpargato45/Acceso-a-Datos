package com.acdat.SpringbootLunes.demo.controladores;

import com.acdat.SpringbootLunes.demo.dao.IDepartamentoDAO;
import com.acdat.SpringbootLunes.demo.jpa.DepartamentosEntity;
import com.acdat.SpringbootLunes.demo.jpa.EmpleadosEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api-rest/departamentos")
public class controladorDepartamentos {
    @Autowired
    IDepartamentoDAO departamentoDAO;

    @GetMapping
    public List<DepartamentosEntity> buscarDepartamento() {
        return (List<DepartamentosEntity>) departamentoDAO.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<DepartamentosEntity> buscarDepartamentosPorId(@PathVariable(value = "id")int id) {
        Optional<DepartamentosEntity> departamento = departamentoDAO.findById(id);

        if (departamento.isPresent()) {
            return ResponseEntity.ok().body(departamento.get());
        }else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public DepartamentosEntity guardarDepartamento(@Validated @RequestBody DepartamentosEntity departamento) {
        return departamentoDAO.save(departamento);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> borrarDepartamento(@PathVariable(value = "id") int id) {
        Optional<DepartamentosEntity> departamento = departamentoDAO.findById(id);
        if (departamento.isPresent()) {
            departamentoDAO.deleteById(id);
            return ResponseEntity.ok().body("Borrado");
        }else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizarDepartamento(@RequestBody DepartamentosEntity nuevoDepartamento, @PathVariable(value =  "id") int id) {
        Optional<DepartamentosEntity> departamento = departamentoDAO.findById(id);
        if (departamento.isPresent()) {
            departamento.get().setNombre(nuevoDepartamento.getNombre());
            departamento.get().setUbicacion(nuevoDepartamento.getUbicacion());
            departamentoDAO.save(departamento.get());
            return ResponseEntity.ok().body("Update");
        }else {
            return ResponseEntity.notFound().build();
        }
    }


}
