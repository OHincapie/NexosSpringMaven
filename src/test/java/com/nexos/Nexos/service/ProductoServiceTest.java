package com.nexos.Nexos.service;

import com.nexos.Nexos.models.Producto;
import com.nexos.Nexos.models.Usuario;
import com.nexos.Nexos.repository.ProductoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class ProductoServiceTest {

    @Mock
    private ProductoRepository productoRepository;

    @InjectMocks
    private ProductoService productoService;

    private Producto producto;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);

        producto= new Producto();
        producto.setCreadoPor(new Usuario());
        producto.setNombreProducto("ACEITE HONDA");
        producto.setModificadoPor("David");
        producto.setFechaIngreso(new Date());
        producto.setFechaModificacion(new Date());
        producto.setCantidad(new Integer(5));
        producto.setId(new Long(7));
    }

    @Test
    void findAll() {
        when(productoRepository.findAll()).thenReturn(Arrays.asList(producto));
        assertNotNull(productoService.findAll());
    }
}