package com.nexos.Nexos.service;

import com.nexos.Nexos.models.Producto;
import com.nexos.Nexos.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@Service
public class ProductoService implements ProductoRepository{

    @Autowired
    private ProductoRepository productoRepository;


    @Override
    public List<Producto> findAll() {
        return productoRepository.findAll();
    }

    public List<Producto> findByUser(Long id){
        List<Producto> productosRespuesta = new ArrayList<>();
        List<Producto> productos = productoRepository.findAll();
        for(int i=0;i<productos.size();i++){
            if (productos.get(i).getCreadoPor().getId()==id){
                productosRespuesta.add(productos.get(i));
            }
        }
        return productosRespuesta;
    }

    public List<Producto> findValidarPorNombre(String name){
        List<Producto> productosRespuesta = new ArrayList<>();
        List<Producto> productos = productoRepository.findAll();
        for(int i=0;i<productos.size();i++){
            if (productos.get(i).getNombreProducto().equals(name)){
                productosRespuesta.add(productos.get(i));
            }
        }
        return  productosRespuesta;
    }

    @Override
    public List<Producto> findAll(Sort sort) {
        return productoRepository.findAll(sort);
    }

    @Override
    public Page<Producto> findAll(Pageable pageable) {
        return productoRepository.findAll(pageable);
    }

    @Override
    public List<Producto> findAllById(Iterable<Long> longs) {
        return productoRepository.findAllById(longs);
    }

    @Override
    public long count() {
        return  productoRepository.count();
    }

    @Override
    public void deleteById(Long aLong) {
        productoRepository.deleteById(aLong);
    }

    @Override
    public void delete(Producto entity) {
        productoRepository.delete(entity);
    }

    @Override
    public void deleteAllById(Iterable<? extends Long> longs) {
    productoRepository.deleteAllById(longs);
    }

    @Override
    public void deleteAll(Iterable<? extends Producto> entities) {
    productoRepository.deleteAll(entities);
    }

    @Override
    public void deleteAll() {
    productoRepository.deleteAll();
    }

    @Override
    public <S extends Producto> S save(S entity) {
        return  productoRepository.save(entity);
    }

    @Override
    public <S extends Producto> List<S> saveAll(Iterable<S> entities) {
        return productoRepository.saveAll(entities);
    }

    @Override
    public Optional<Producto> findById(Long aLong) {
        return productoRepository.findById(aLong);
    }

    @Override
    public boolean existsById(Long aLong) {
        return productoRepository.existsById(aLong);
    }

    @Override
    public void flush() {
        productoRepository.flush();
    }

    @Override
    public <S extends Producto> S saveAndFlush(S entity) {
        return productoRepository.saveAndFlush(entity);
    }

    @Override
    public <S extends Producto> List<S> saveAllAndFlush(Iterable<S> entities) {
        return productoRepository.saveAllAndFlush(entities);
    }

    @Override
    public void deleteAllInBatch(Iterable<Producto> entities) {
    productoRepository.deleteAllInBatch();
    }

    @Override
    public void deleteAllByIdInBatch(Iterable<Long> longs) {
    productoRepository.deleteAllByIdInBatch(longs);
    }

    @Override
    public void deleteAllInBatch() {
    productoRepository.deleteAllInBatch();
    }

    @Override
    public Producto getOne(Long aLong) {
        return productoRepository.getOne(aLong);
    }

    @Override
    public Producto getById(Long aLong) {
        return productoRepository.getById(aLong);
    }

    @Override
    public <S extends Producto> Optional<S> findOne(Example<S> example) {
        return productoRepository.findOne(example);
    }

    @Override
    public <S extends Producto> List<S> findAll(Example<S> example) {
        return productoRepository.findAll(example);
    }

    @Override
    public <S extends Producto> List<S> findAll(Example<S> example, Sort sort) {
        return null;
    }

    @Override
    public <S extends Producto> Page<S> findAll(Example<S> example, Pageable pageable) {
        return null;
    }

    @Override
    public <S extends Producto> long count(Example<S> example) {
        return 0;
    }

    @Override
    public <S extends Producto> boolean exists(Example<S> example) {
        return false;
    }

    @Override
    public <S extends Producto, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
        return null;
    }
}
