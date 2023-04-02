package com.ivanconsalter.ionicspring.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ivanconsalter.ionicspring.domain.Categoria;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Long>{

}
