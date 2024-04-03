package gestion.com.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import gestion.com.entite.Professeur;


public interface ProfesseurRepository extends JpaRepository <Professeur, String>{
	
}
