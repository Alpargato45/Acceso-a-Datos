package com.acdat.SpringbootLunes.demo.dao;

import com.acdat.SpringbootLunes.demo.jpa.DepartamentosEntity;
import org.springframework.data.repository.CrudRepository;

public interface IDepartamentoDAO extends CrudRepository<DepartamentosEntity, Integer> {

}
