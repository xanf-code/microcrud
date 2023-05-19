package com.example.microdemo.controller;

import com.example.microdemo.model.Spaceship;
import com.example.microdemo.repository.SpaceShipRepository;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.annotation.*;
import io.micronaut.http.exceptions.HttpStatusException;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;

@Controller(value = "/spaceship")
@RequiredArgsConstructor
public class SpaceShipController {
    private final SpaceShipRepository repository;

    @Post("/add/spaceship/all")
    public Iterable<Spaceship> addAllSpaceShip(@Body List<Spaceship> spaceships){
        return repository.saveAll(spaceships);
    }

    @Get("/spaceships")
    public Iterable<Spaceship> allSpaceships(){
        return repository.findAll();
    }

    @Post("/add/spaceship")
    public Spaceship addSpaceship(@Body Spaceship spaceship){
        return repository.save(spaceship);
    }

    @Get("/ships")
    public Iterable<Spaceship> spaceshipByName(@QueryValue String name){
        return repository.findByName(name);
    }

    @Patch("/ship/update/{id}")
    public Spaceship updateSpaceShip(@PathVariable @NonNull Integer id, @Body Spaceship spaceship) {
        Spaceship entity = repository.findById(id)
                .orElseThrow(() -> new HttpStatusException(HttpStatus.NOT_FOUND,"Spaceship not found for ID: " + id));

        entity.setCaptainName(spaceship.getCaptainName());
        entity.setName(spaceship.getName());
        entity.setModel(spaceship.getModel());
        entity.setFuel(spaceship.getFuel());

        return repository.update(entity);
    }

    @Get("/ship/")
    public Iterable<Spaceship> getSpaceShipByMultipleQueries(@QueryValue Integer fuel, @QueryValue String captainName){
        return repository.customQuery(fuel,captainName);
    }

    @Delete("/ship/delete/{id}")
    public Spaceship deleteSpaceShip(@PathVariable @NonNull Integer id){
        Optional<Spaceship> byId = repository.findById(id);
        if(byId.isEmpty()){
            throw new HttpStatusException(HttpStatus.NOT_FOUND,"Spaceship not found for ID: " + id);
        }
        repository.delete(byId.get());
        return byId.get();
    }
}
