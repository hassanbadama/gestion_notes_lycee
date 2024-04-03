package gestion.com.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import gestion.com.entite.Matiere;
@Service
public interface MatiereRepository extends JpaRepository <Matiere, String>{

}
