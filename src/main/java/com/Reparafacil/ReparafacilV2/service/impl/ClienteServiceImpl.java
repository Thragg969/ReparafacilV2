package com.Reparafacil.ReparafacilV2.service.impl;

import com.Reparafacil.ReparafacilV2.exception.NotFoundException;
import com.Reparafacil.ReparafacilV2.model.Cliente;
import com.Reparafacil.ReparafacilV2.repository.ClienteRepository;
import com.Reparafacil.ReparafacilV2.service.ClienteService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@SuppressWarnings("null") // para los warnings de null-safety que te está marcando Eclipse
public class ClienteServiceImpl implements ClienteService {

    private final ClienteRepository repo;

    public ClienteServiceImpl(ClienteRepository repo) {
        this.repo = repo;
    }

    @Override
    public List<Cliente> listar() {
        return repo.findAll();
    }

    @Override
    public Cliente buscarPorId(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new NotFoundException("Cliente no encontrado con id: " + id));
    }

    @Override
    public Cliente crear(Cliente cliente) {
        return repo.save(cliente);
    }

    @Override
    public Cliente actualizar(Long id, Cliente cliente) {
        // verifico que exista
        buscarPorId(id);
        // me aseguro de que se guarde con ese id
        cliente.setId(id);
        return repo.save(cliente);
    }

    @Override
    public void eliminar(Long id) {
        Cliente actual = buscarPorId(id);
        repo.delete(actual);
    }
}
