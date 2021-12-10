package com.example.springboot;

import static org.assertj.core.api.Assertions.assertThat;

import com.example.springboot.modele.Proprietaire;
import com.example.springboot.modele.ProprietaireRepo;
import com.example.springboot.modele.Voiture;
import com.example.springboot.modele.VoitureRepo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;
@RunWith(SpringRunner.class)
@DataJpaTest
//Si le test concerne uniquement les composantes JPA
//Lorsque cette annotation est utilisée, H2, Hibernate et Spring Data sont configurés
//automatiquement pour le test.
public class VoitureRepoTest {
    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private ProprietaireRepo proprietaireRepo;
    //TestEntityManager est utilisée pour manipuler les entités persistantes
    @Autowired
    VoitureRepo voitureRepo;
    @Test
    public void ajouterVoiture() {
        Proprietaire proprietaire1 = new Proprietaire("Ali" , "Hassan");
        Proprietaire proprietaire2 = new Proprietaire("Najat" , "Bani");
        proprietaireRepo.save(proprietaire1);
        proprietaireRepo.save(proprietaire2);
        Voiture voiture = new Voiture ("MiolaCar","Uber","Blanche","M-2020", 2021, 180000,proprietaire1);
        entityManager.persistAndFlush(voiture);
//permet de faire persister ce tuple de l’entité Voiture
        assertThat(voiture.getId()).isNotNull();
//permet de tester qu'un tuple de Voiture a bien été ajoutée en mémoire H2
    }
    @Test
    public void supprimerVoiture() {
        Proprietaire proprietaire1 = new Proprietaire("Ali" , "Hassan");
        Proprietaire proprietaire2 = new Proprietaire("Najat" , "Bani");
        proprietaireRepo.save(proprietaire1);
        proprietaireRepo.save(proprietaire2);
        entityManager.persistAndFlush(new Voiture("MiolaCar","Uber","Blanche","M-2020", 2021,180000,proprietaire1));
        entityManager.persistAndFlush(new Voiture ("MiniCooper","Uber","Rouge","C-2020", 2021,
                180000,proprietaire2));
        voitureRepo.deleteAll();
        assertThat(voitureRepo.findAll()).isEmpty();
//permet de tester si tous les tuples ont été supprimés
    }
}
