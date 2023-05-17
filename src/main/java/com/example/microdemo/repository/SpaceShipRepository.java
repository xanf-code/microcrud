package com.example.microdemo.repository;

import com.example.microdemo.model.Spaceship;
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.repository.CrudRepository;

import java.util.Optional;

@Repository
public interface SpaceShipRepository extends CrudRepository<Spaceship,Integer> {
    Iterable<Spaceship> findByName(String value);
}
