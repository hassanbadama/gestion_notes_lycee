package gestion.com.repository;

import org.springframework.beans.factory.annotation.Autowired;

import gestion.com.entite.Classe;
import gestion.com.entite.Professeur;

public class implementService implements ServiceUnir {
	private ProfesseurRepository professeurRepository;
	@Autowired
	private ClasseRepository classeRepository;

	@Override
	public void AjouteProfClasse(String Matriculeprof, String Codeclasse) {
		// TODO Auto-generated method stub
		 Professeur p=professeurRepository.findById(Matriculeprof).get();
		 Classe classe=classeRepository.findById(Codeclasse).get();
		 p.getClasse().add(classe);
		
	}

}
