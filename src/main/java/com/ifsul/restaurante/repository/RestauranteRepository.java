package com.ifsul.restaurante.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ifsul.restaurante.model.Restaurante;

public interface RestauranteRepository extends JpaRepository<Restaurante, Long> {
	 	List<Restaurante> findByNomeContaining(String nome);

	    List<Restaurante> findByEnderecoContaining(String endereco);

	    List<Restaurante> findByTipoCozinhaContaining(String tipoCozinha);
}



