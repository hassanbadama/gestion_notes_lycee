package gestion.com.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import gestion.com.entite.Eleve;

public interface EleveRepository extends JpaRepository <Eleve, String> {
	//public List<Eleve> findByClasse(@Param("key")String key);

}
