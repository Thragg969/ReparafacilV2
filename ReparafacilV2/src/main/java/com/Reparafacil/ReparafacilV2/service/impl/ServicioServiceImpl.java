package com.Reparafacil.ReparafacilV2.service.impl;

import com.Reparafacil.ReparafacilV2.exception.NotFoundException;
import com.Reparafacil.ReparafacilV2.model.Servicio;
import com.Reparafacil.ReparafacilV2.repository.ServicioRepository;
import com.Reparafacil.ReparafacilV2.service.ServicioService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@SuppressWarnings("null")
public class ServicioServiceImpl implements ServicioService {

    private final ServicioRepository repo;

    public ServicioServiceImpl(ServicioRepository repo) {
        this.repo = repo;
    }

    @Override
    public List<Servicio> listar() {
        return repo.findAll();
    }

    @Override
    public Servicio buscarPorId(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new NotFoundException("Servicio no encontrado con id: " + id));
    }

    @Override
    public Servicio crear(Servicio servicio) {
        return repo.save(servicio);
    }

    @Override
    public Servicio actualizar(Long id, Servicio servicio) {
        // verifico que exista
        buscarPorId(id);
        // fuerzo el mismo id que viene por la URL
        servicio.setId(id);
        return repo.save(servicio);
    }

    @Override
    public void eliminar(Long id) {
        Servicio actual = buscarPorId(id);
        repo.delete(actual);
    }
}
