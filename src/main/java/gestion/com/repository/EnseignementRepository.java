package gestion.com.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import gestion.com.entite.Enseignement;
import gestion.com.entite.Enseignementkey;
import gestion.com.entite.Matiere;


public interface EnseignementRepository extends JpaRepository <Enseignement, Enseignementkey>{
	//Enseignementkey key = new Enseignementkey();
	//key.setMatricule("hh");
	
	//Enseignement enseign = new Enseignement();
	// void deleteById();
	void deleteById(Enseignementkey key);

}
