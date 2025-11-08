package com.Reparafacil.ReparafacilV2.service.impl;

import com.Reparafacil.ReparafacilV2.exception.NotFoundException;
import com.Reparafacil.ReparafacilV2.model.Agenda;
import com.Reparafacil.ReparafacilV2.repository.AgendaRepository;
import com.Reparafacil.ReparafacilV2.service.AgendaService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@SuppressWarnings("null") // esto es para los warnings de @NonNull que te está tirando Eclipse
public class AgendaServiceImpl implements AgendaService {

    private final AgendaRepository repo;

    public AgendaServiceImpl(AgendaRepository repo) {
        this.repo = repo;
    }

    @Override
    public List<Agenda> listar() {
        return repo.findAll();
    }

    @Override
    public Agenda buscarPorId(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new NotFoundException("Agenda no encontrada con id: " + id));
    }

    @Override
    public Agenda crear(Agenda agenda) {
        return repo.save(agenda);
    }

    @Override
    public Agenda actualizar(Long id, Agenda agenda) {
        // primero valido que exista
        buscarPorId(id);
        // me aseguro de guardar con el mismo id
        agenda.setId(id);
        return repo.save(agenda);
    }

    @Override
    public void eliminar(Long id) {
        Agenda actual = buscarPorId(id);
        repo.delete(actual);
    }
}
