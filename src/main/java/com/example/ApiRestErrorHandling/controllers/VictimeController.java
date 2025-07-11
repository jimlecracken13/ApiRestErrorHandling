package com.example.ApiRestErrorHandling.controllers;

import com.example.ApiRestErrorHandling.entities.Victime;
import com.example.ApiRestErrorHandling.services.VictimeService;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.tags.Tags;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/victimes")
@Tag(name = "victime-controller", description = "End point pour les victimes")
public class VictimeController {
    VictimeService victimeService;
    public VictimeController(VictimeService victimeService)
    {
        this.victimeService = victimeService;
    }

    @PostMapping
    public ResponseEntity<?> addVictime(@RequestBody Victime victime)
    {
        try {
            return new ResponseEntity<>(
                    victimeService.addVictime(victime),
                    HttpStatus.CREATED);
        }catch(EntityExistsException e){
            return new ResponseEntity<>(
                    e.getMessage(),
                    HttpStatus.CONFLICT
            );
        }
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable int id)
    {
        try{
            return new ResponseEntity<>(
                    victimeService.getVictimeById(id),
                    HttpStatus.FOUND
            );
        }catch (EntityNotFoundException e)
        {
            return new ResponseEntity<>(
                    e.getMessage(),
                    HttpStatus.NOT_FOUND
            );
        }
    }
    @GetMapping
    public ResponseEntity<?> getAll()
    {
        try{
            return new ResponseEntity<>(
                    victimeService.getAllVictimes(),
                    HttpStatus.OK
            );
        }catch(RuntimeException e)
        {
            return new ResponseEntity<>(
                    "Oups une erreur est survenue",
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
    }
    @PutMapping
    public ResponseEntity<?> updateVictime(@RequestBody Victime victime)
    {
        try{
            return new ResponseEntity<>(
                    victimeService.updateVictime(victime),
                    HttpStatus.OK
                    );
        }catch (EntityNotFoundException e)
        {
            return new ResponseEntity<>(
                    e.getMessage(),
                    HttpStatus.NOT_FOUND
            );
        }
    }
    @DeleteMapping("/{id}")
    public Boolean deleteById(@PathVariable int id)
    {
        return victimeService.deleteById(id);
    }
}
