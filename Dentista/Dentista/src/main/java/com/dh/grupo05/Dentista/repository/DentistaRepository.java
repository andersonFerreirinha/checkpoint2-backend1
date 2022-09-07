package com.dh.grupo05.Dentista.repository;

import com.dh.grupo05.Dentista.model.Dentista;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DentistaRepository extends JpaRepository<Dentista, Long> {

}
