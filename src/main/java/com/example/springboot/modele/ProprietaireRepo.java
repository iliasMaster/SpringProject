package com.example.springboot.modele;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProprietaireRepo extends CrudRepository<Proprietaire, Long> {
}
