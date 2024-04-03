package gestion.com;


import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.util.CollectionUtils;

import gestion.com.entite.*;
import gestion.com.repository.ClasseRepository;
import gestion.com.repository.EleveRepository;
import gestion.com.repository.EnseignementRepository;
import gestion.com.repository.MatiereRepository;
import gestion.com.repository.Mot_de_passeRepositiry;
import gestion.com.repository.NoteRepository;
import gestion.com.repository.ProfesseurRepository;
@SpringBootApplication

public class GestionnoteslyceeApplication implements CommandLineRunner{
	@Autowired
	private MatiereRepository matiereRepository;
	@Autowired
	private ProfesseurRepository professeurRepository;
	@Autowired
	private ClasseRepository classeRepository;
	@Autowired
	private EleveRepository eleveRepository;
	@Autowired
	private Mot_de_passeRepositiry motpasse;
	
	

	public static void main(String[] args) {
		SpringApplication.run(GestionnoteslyceeApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("hassane badama ggg");
		List<Professeur>Prof=professeurRepository.findAll();
		if(CollectionUtils.isEmpty(Prof)) {
			Date date = new Date(0,0,0);
			//(String matricule, String nom, String prenom, String sexe, String contact, Date dateNaiss,String code) 
			Professeur prof = new Professeur("0000","0000", "0000","M", "0000", date,"0000");
			professeurRepository.save(prof);
			Mot_de_Passe mot = new Mot_de_Passe("0000","0000");
			motpasse.save(mot);
			
		}
		
		
		/*	
		Matiere matiere = new Matiere("inf","informatique",2);
		matiereRepository.save(matiere);
		Classe classe = new Classe("U1", "seconde", prof);
		classeRepository.save(classe);*/
	//	Enseignement enseigne = new Enseignement(prof,matiere,  classe);
		//enseignementRepository.save(enseigne);
		//Eleve eleve = new Eleve("iddd", "kaka", "carlos", "f","2023",classe);
		//eleveRepository.save(eleve);
	//	Note note = new Note(16, matiere, eleve);
	//	noteRepository.save(note);
		
	}

}
