package com.ifsul.restaurante.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ifsul.restaurante.model.Restaurante;
import com.ifsul.restaurante.repository.RestauranteRepository;

@Service
public class RestauranteService {
    @Autowired
    private RestauranteRepository restauranteRepository;

    public List<Restaurante> listarRestaurantes() {
        return restauranteRepository.findAll();
    }

    public Restaurante criarRestaurante(Restaurante restaurante) {
        return restauranteRepository.save(restaurante);
    }

    public Restaurante buscarRestaurantePorId(Long id) {
        return restauranteRepository.findById(id).orElse(null);
    }

    public void deletarRestaurante(Long id) {
        restauranteRepository.deleteById(id);
    }
    
    public List<Restaurante> buscarRestaurantesPorNome(String nome) {
        return restauranteRepository.findByNomeContaining(nome);
    }

    public List<Restaurante> buscarRestaurantesPorEndereco(String endereco) {
        return restauranteRepository.findByEnderecoContaining(endereco);
    }

    public List<Restaurante> buscarRestaurantesPorTipoCozinha(String tipoCozinha) {
        return restauranteRepository.findByTipoCozinhaContaining(tipoCozinha);
    }
    
    
}
