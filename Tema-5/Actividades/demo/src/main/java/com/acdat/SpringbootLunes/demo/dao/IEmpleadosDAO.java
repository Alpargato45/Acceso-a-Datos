package com.acdat.SpringbootLunes.demo.dao;

import com.acdat.SpringbootLunes.demo.jpa.EmpleadosEntity;
import org.springframework.data.repository.CrudRepository;

public interface IEmpleadosDAO extends CrudRepository<EmpleadosEntity, Integer> {



}
