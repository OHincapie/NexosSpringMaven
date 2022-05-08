package com.nexos.Nexos.controllers;

import com.nexos.Nexos.models.Usuario;
import com.nexos.Nexos.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Date;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping(value = "/users/")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    private ResponseEntity<List<Usuario>> getAllUsuarios(){
        return ResponseEntity.ok(usuarioService.findAll());
    }

    @PostMapping("createUser")
    @ResponseStatus(HttpStatus.CREATED)
    public @ResponseBody ResponseEntity<String> createUser(@RequestBody Usuario user){
        try{
            user.setFechaIngreso(new Date());
            usuarioService.save(user);
        } catch (Exception e){
            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, e.getMessage());
        }
        return ResponseEntity.ok("CREATED");
    }

    @PutMapping("updateUser")
    @ResponseStatus(HttpStatus.CREATED)
    public @ResponseBody ResponseEntity<String> updateUser(@RequestBody Usuario user){
        Optional<Usuario> userAux = usuarioService.findById(user.getId());
        if (userAux.isPresent()){
            try{
                usuarioService.save(user);
            } catch (Exception e){
                throw new ResponseStatusException(HttpStatus.NOT_MODIFIED, e.getMessage());
            }
        } else {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "The user can't be updated");
        }
        return ResponseEntity.ok("UPDATED");
    }

    @DeleteMapping("deleteUser/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public @ResponseBody ResponseEntity<String> deleteUser(@PathVariable("id") Long id){
        Optional<Usuario> userAux = usuarioService.findById(id);
        if (userAux.isPresent()){
            try{
                usuarioService.deleteById(id);
            } catch (Exception e){
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
            }
        } else {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "The user can't be deleted");
        }
        return ResponseEntity.ok("DELETED");
    }


}
