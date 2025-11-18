package com.Reparafacil.ReparafacilV2.service.impl;

import com.Reparafacil.ReparafacilV2.exception.NotFoundException;
import com.Reparafacil.ReparafacilV2.model.Tecnico;
import com.Reparafacil.ReparafacilV2.repository.TecnicoRepository;
import com.Reparafacil.ReparafacilV2.service.TecnicoService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@SuppressWarnings("null")
public class TecnicoServiceImpl implements TecnicoService {

    private final TecnicoRepository repo;

    public TecnicoServiceImpl(TecnicoRepository repo) {
        this.repo = repo;
    }

    @Override
    public List<Tecnico> listar() {
        return repo.findAll();
    }

    @Override
    public Tecnico buscarPorId(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new NotFoundException("Técnico no encontrado con id: " + id));
    }

    @Override
    public Tecnico crear(Tecnico tecnico) {
        return repo.save(tecnico);
    }

    @Override
    public Tecnico actualizar(Long id, Tecnico tecnico) {
        // verifico que exista
        buscarPorId(id);
        // guardo con el id que viene por la ruta
        tecnico.setId(id);
        return repo.save(tecnico);
    }

    @Override
    public void eliminar(Long id) {
        Tecnico actual = buscarPorId(id);
        repo.delete(actual);
    }
}
