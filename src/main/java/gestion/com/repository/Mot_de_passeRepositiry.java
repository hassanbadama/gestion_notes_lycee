package gestion.com.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import gestion.com.entite.Mot_de_Passe;

public interface Mot_de_passeRepositiry extends JpaRepository <Mot_de_Passe, String>  {
	 Mot_de_Passe findByNom(String n);

}
