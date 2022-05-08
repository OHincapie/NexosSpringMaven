package com.nexos.Nexos.controllers;

import com.nexos.Nexos.models.Producto;
import com.nexos.Nexos.models.Usuario;
import com.nexos.Nexos.service.ProductoService;
import com.nexos.Nexos.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/products/")
public class ProductoController {

    @Autowired
    private ProductoService productoService;

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    public ResponseEntity<List<Producto>> getAllProducts(){
        return ResponseEntity.ok(productoService.findAll());
    }

    @GetMapping(value = "{idUser}")
    public ResponseEntity<List<Producto>> getProductsByUser(@PathVariable("idUser") Long idUser){
        List<Producto> productos = new ArrayList<>();
        Optional<Usuario> usuario = usuarioService.findById(idUser);
        if (!usuario.isEmpty()){
            productos = productoService.findByUser(idUser);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
        return ResponseEntity.ok(productos);
    }


    @PostMapping("createProduct/{idUser}")
    @ResponseStatus(HttpStatus.CREATED)
    public @ResponseBody ResponseEntity<String> createProduct(@PathVariable("idUser") Long idUser,
    @RequestBody Producto product ){
        Optional<Usuario> usuario = usuarioService.findById(idUser);
        if(usuario.isPresent()){
            List<Producto> validarPorNombre = productoService.findValidarPorNombre(product.getNombreProducto());
            if(validarPorNombre.isEmpty()){
                try{
                    product.setFechaIngreso(new Date());
                    product.setCreadoPor(usuario.get());
                    productoService.save(product);
                } catch (Exception e){
                    throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, e.getMessage());
                }
            } else {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "THE PRODUCT ALREADY EXIST");
            }
        } else {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "The product can't be created");
        }
        return ResponseEntity.ok("CREATED");
    }



    @PutMapping("updateProduct/{idUser}")
    @ResponseStatus(HttpStatus.CREATED)
    public @ResponseBody ResponseEntity<String> updateProduct(@PathVariable("idUser") Long idUser,
    @RequestBody Producto product){
        Optional<Usuario> user = usuarioService.findById(idUser);
        if(user.isPresent()){
            Optional<Producto> productAux=productoService.findById(product.getId());
            if(productAux.isPresent()){
                if(user.get().getId() == product.getCreadoPor().getId()){
                    try{
                        product.setFechaModificacion(new Date());
                        product.setModificadoPor(user.get().getNombre());
                        productoService.save(product);
                    } catch (Exception e){
                        throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, e.getMessage());                    }

                } else {
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "USER IS NOT AUTHORIZED");
                }
            } else{
                throw new ResponseStatusException(HttpStatus.FORBIDDEN, "The product can't be updated");
            }
        } else {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "The product can't be updated because the user doesn't exist");
        }
        return ResponseEntity.ok("UPDATED");
    }


    @DeleteMapping("deleteProduct/{idUser}/{idProduct}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public @ResponseBody ResponseEntity<String> deleteProduct(@PathVariable("idUser") Long idUser,
    @PathVariable("idProduct") Long idProduct){
        Optional<Usuario> user = usuarioService.findById(idUser);
        if(user.isPresent()){
            Optional<Producto> product = productoService.findById(idProduct);
            if (product.isPresent()){
                if (user.get() == product.get().getCreadoPor()){
                    try{
                        productoService.delete(product.get());
                    } catch (Exception e ){
                        throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
                    }
                } else {
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "USER IS NOT AUTHORIZED");
                }
            } else {
                throw new ResponseStatusException(HttpStatus.FORBIDDEN, "The product can't be deleted");
            }
        } else {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "The product can't be deleted because the user doesn't exist");
        }
        return ResponseEntity.ok("DELETED");
    }
}
