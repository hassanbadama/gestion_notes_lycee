package gestion.com.controller;


//import java.awt.PageAttributes.MediaType;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.spi.InetAddressResolverProvider.Configuration;
import java.sql.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;
import javax.sql.*;
//import javax.activation.*;
//import javax.mail.util.ByteArrayDataSource;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.*;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import gestion.com.entite.*;
import gestion.com.pdf.ExportPdf;
import gestion.com.repository.BullentinRepository;
import gestion.com.repository.ClasseRepository;
import gestion.com.repository.EleveRepository;
import gestion.com.repository.EnseignementRepository;
import gestion.com.repository.MatiereRepository;
import gestion.com.repository.Mot_de_passeRepositiry;
import gestion.com.repository.NoteRepository;
import gestion.com.repository.ProfesseurRepository;
import gestion.com.repository.RangBulletinRepository;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
//import com.emaillistattachement.export.ExportPdf;
import jakarta.mail.util.ByteArrayDataSource;

@Controller
public class usrController {
	@Autowired
	private MatiereRepository matiereRepository;
	@Autowired
	private ProfesseurRepository professeurRepository;
	@Autowired
	private ClasseRepository classeRepository;
	@Autowired
	private EleveRepository eleveRepository;
	@Autowired
	private EnseignementRepository enseignementRepository;
	@Autowired
	private NoteRepository noteRepository;
	@Autowired
	private BullentinRepository bullentinRepository;
	@Autowired
	private RangBulletinRepository rangBulletinRepository;
	@Autowired
	private Mot_de_passeRepositiry motpasse;
	
	@Autowired
	JavaMailSender javaMailSender;
	
	
	public static String ID;
	public static float som;
	public static int coef;
	public static float moyenne;
	public static float moyen;
	public static int rang;
	public static int admis;
	public static int echoue;
	public static int nombre;
	public static float pourcentage;
	public static String classeteste;
	public static String matiereTeste;
	public static String classeTeste;
	
	@GetMapping(path="/accueil")
	public String accueil(Model model) {
		
		return "Accueil";
	}
	/*les operations sur professeur*/
	
	@GetMapping(path="/prof")
	public String ajouterProfesseur(Model model) {
		List<Professeur>Professeur = professeurRepository.findAll();
		model.addAttribute("professeur",Professeur);
		List<Matiere>codematiere = matiereRepository.findAll();
		model.addAttribute("codematiere",codematiere);
		//return "Professeur";
		return "proff";
	}
	@PostMapping(path="/AjouterProfesseur")
	public String AjouterProf(Model model,String id,String nom, String prenom,String sexe,String numero,Date date, String code ) {
		Professeur prof = new Professeur(id,nom, prenom,sexe, numero, date,code);
		professeurRepository.save(prof);
		String nometprenom = nom.concat(prenom);
		Mot_de_Passe motPasse = new Mot_de_Passe(id,nometprenom);
		motpasse.save(motPasse);
		List<Professeur>Professeur = professeurRepository.findAll();
		model.addAttribute("professeur",Professeur);
		return "redirect:/prof";
	}
	@GetMapping(path="/supprimerProfesseur")
	public String SupprimerProfesseur(Model model, String matricule) {
		
		try {
			professeurRepository.deleteById(matricule);
			motpasse.deleteById(matricule);
			return "redirect:/prof";
			
		}
		catch(Exception e) {
			List<Professeur>Professeur = professeurRepository.findAll();
			model.addAttribute("professeur",Professeur);
			List<Matiere>codematiere = matiereRepository.findAll();
			model.addAttribute("codematiere",codematiere);
			String erreur="professeur ne peut pas etre supprimer "
					+ "verifier s'il ou elle n'enseigne pas une matiere dans une classe";
			model.addAttribute("erreur",erreur);
			return "proff";
			
		}
		
	}
	@GetMapping(path="/modifierProfesseur")
	public String modifierProfesseur(Model model,String matricule) {
	    Professeur p=professeurRepository.findById(matricule).get();
		model.addAttribute("p",p);
		List<Matiere>codematiere = matiereRepository.findAll();
		model.addAttribute("codematiere",codematiere);
		return "modifierprofesseurs";
	}
	
	@GetMapping(path="/pageprof")
	public String PaggeProfesseur(){
		return "Pageprof";
	}
	/*les operations sur matieres*/
	
	@GetMapping(path="/matiere")
	public String ajoutermatiere(Model model) {
		//Matiere matiere = (Matiere) matiereRepository.findAll();
		List<Matiere>matiere = matiereRepository.findAll();
		model.addAttribute("matiere",matiere);
		return "matieres";
	}
	@PostMapping(path="/AjouterMatiere")
	public String AjouterMatiere(Model model,String code,String nom, int coefficient ) {
		Matiere matier = new Matiere(code,nom,coefficient);
		matiereRepository.save(matier);
		List<Matiere>matiere = matiereRepository.findAll();
		model.addAttribute("matiere",matiere);
		return "redirect:/matiere";
	}
	@GetMapping(path="/supprimerMtiere")
	public String SupprimerMatiere(Model model, String code) {
		
		try {
			matiereRepository.deleteById(code);
			return "redirect:/matiere";
			
		}
		catch(Exception e) {
			List<Matiere>matiere = matiereRepository.findAll();
			model.addAttribute("matiere",matiere);
			String erreur="la matiere ne peut pas etre supprimer "
					+ "verifier si elle n'a pas un enseignement et une note attribue";
			model.addAttribute("erreur",erreur);
			return "matieres";
			
		}
		
	}
	@GetMapping(path="/modifierMatiere")
	public String modifierMatiere(Model model,String code) {
	    Matiere p=matiereRepository.findById(code).get();
		model.addAttribute("p",p);
		return "modifiermatieres";
	}
	
	/*les operations sur les eleves*/
	
	@GetMapping(path="/eleve")
	public String Eleve(Model model) {
		List<Eleve>eleve = eleveRepository.findAll();
		model.addAttribute("eleve",eleve);
		List<Classe>classe = classeRepository.findAll();
		model.addAttribute("classe",classe);	
		//classeteste=classe;
		model.addAttribute("classeteste",classeteste);
		return "eleves";
	}
	
	@PostMapping(path="/AjouterEleve")
	public String AjouterEleve(Model model,String id,String nom, String prenom,String sexe,Date dateNaissance ,String numero, String classe ) {
	    Classe clas=classeRepository.findById(classe).get();
		Eleve elev = new Eleve(id,nom,prenom,sexe,dateNaissance,numero,clas);
		eleveRepository.save(elev);
		String nometprenom = nom.concat(prenom);
		Mot_de_Passe motPasse = new Mot_de_Passe(id,nometprenom);
		motpasse.save(motPasse);
		String notification="operation a été effectué avec succés";
		model.addAttribute("notification",notification);
		List<Eleve>eleve = eleveRepository.findAll();
		classeteste=classe;
		model.addAttribute("classeteste",classeteste);
		model.addAttribute("eleve",eleve);
		List<Classe>classe1 = classeRepository.findAll();
		model.addAttribute("classe",classe1);
		return "eleves";
	}
	
	@PostMapping(path="/ConsulterEleve")
	public String ConsulterParclasseEleve(Model model, String classe ) {
		List<Eleve>eleve = eleveRepository.findAll();
	    classeteste=classe;
		model.addAttribute("classeteste",classeteste);
		model.addAttribute("eleve",eleve);
		List<Classe>classe1 = classeRepository.findAll();
		model.addAttribute("classe",classe1);
		return "eleves";
	}
	
	@GetMapping(path="/supprimerEleve")
	public String SupprimerEleve(Model model, String id) {
		
		try {
			eleveRepository.deleteById(id);
			motpasse.deleteById(id);
			String notification="Eleve est supprimé avec succés";
			model.addAttribute("notification",notification);
			List<Eleve>eleve = eleveRepository.findAll();
		   // classeteste=classe;
			model.addAttribute("classeteste",classeteste);
			model.addAttribute("eleve",eleve);
			List<Classe>classe1 = classeRepository.findAll();
			model.addAttribute("classe",classe1);
			return "eleves";
		}
		catch(Exception e) {
			List<Eleve>eleve = eleveRepository.findAll();
			model.addAttribute("eleve",eleve);
			List<Classe>classe = classeRepository.findAll();
			model.addAttribute("classe",classe);
			String erreur="eleve ne peut pas etre supprime car il a une note";
			model.addAttribute("erreur",erreur);
			model.addAttribute("classeteste",classeteste);
			return "eleves";
			
		}

	}
	
	@GetMapping(path="/modifierEleve")
	public String modifierEleve(Model model,String id) {
	    Eleve eleve=eleveRepository.findById(id).get();
		model.addAttribute("eleve",eleve);
		List<Classe>classe = classeRepository.findAll();
		model.addAttribute("classe",classe);
		return "modifiereleves";
	}
	
	
	
/*les operations sur les classes*/
	@GetMapping(path="/classe")
	public String classe(Model model) {
		List<Classe>classe = classeRepository.findAll();
		model.addAttribute("classe",classe);
		return "classes";
	}
	
	
	@PostMapping(path="/Ajouteclasse")
	public String Ajouterclasse(Model model,String code,String nom, String matricule ) {
		try {
			Professeur prof=professeurRepository.findById(matricule).get();
			Classe classe1 = new Classe(code, nom, prof);
			classeRepository.save(classe1);
			List<Classe>classe = classeRepository.findAll();
			model.addAttribute("classe",classe);
			String lo="";
			model.addAttribute("lo",lo);
			return "redirect:/classe";
		}
		catch(Exception e) {
			List<Classe>classe = classeRepository.findAll();
			model.addAttribute("classe",classe);
			String lo="le matricule du professeur que vous venez de saisir n'existe pas Donc la classe ne peut pas etre ajoute ";
			model.addAttribute("lo",lo);
			return "classes";
		}
		
	}
	
	@GetMapping(path="/supprimerClasse")
	public String SupprimerClasse(Model model, String code) {
		
		try {
			classeRepository.deleteById(code);
			return "redirect:/classe";
		}
		catch(Exception e) {
			String lo="la classe ne peut pas etre supprime verifier si ya peut etre des eleves inscrire ou il ya des matieres qu'on enseigne dans cette classe";
			model.addAttribute("lo",lo);
			List<Classe>classe = classeRepository.findAll();
			model.addAttribute("classe",classe);
			return "classes";
		}
		
	}
	
	@GetMapping(path="/modifierClasse")
	public String modifierClasse(Model model,String code) {
	    Classe classe=classeRepository.findById(code).get();
		model.addAttribute("classe",classe);
		return "modifierclasses";
	}
	
	/*les operations sur enseignement*/
	
	@GetMapping(path="/enseignement")
	public String enseignement(Model model) {
		List<Enseignement>enseigne = enseignementRepository.findAll();
		model.addAttribute("enseigne",enseigne);
		List<Classe>classe = classeRepository.findAll();
		model.addAttribute("classe",classe);
		List<Matiere>codematiere = matiereRepository.findAll();
		model.addAttribute("codematiere",codematiere);
		return "enseignements";
	}
	
	@PostMapping(path="/AjouterEnseignement")
	public String AjouterEnseignement(Model model,String matricule,String matiere, String classe ) {
		Classe clas=classeRepository.findById(classe).get();
		Professeur prof=professeurRepository.findById(matricule).get();
		Matiere matier=matiereRepository.findById(matiere).get();
		Enseignementkey key = new Enseignementkey(matricule,matiere,classe);
		Enseignement enseign = new Enseignement(prof,matier,clas);
		enseign.setKeyer(key);
		enseignementRepository.save(enseign);
		List<Enseignement>enseigne = enseignementRepository.findAll();
		model.addAttribute("enseigne",enseigne);
		return "redirect:/enseignement";
	}
	
	@GetMapping(path="/supprimerEnseignement")
	public String Supprimerenseignement(String matricule,String codem,String codec) {
		Enseignementkey key = new Enseignementkey(matricule,codem,codec);
		enseignementRepository.deleteById(key);
		return "redirect:/enseignement";
	}
	
	@GetMapping(path="/ModifierEnseignement")
	public String modifierEnseignement(Model model,String matricule,String codem,String codec) {
		Enseignementkey key = new Enseignementkey(matricule,codem,codec);
		Enseignement modif=enseignementRepository.findById(key).get();
		model.addAttribute("modif",modif);
		List<Classe>classe = classeRepository.findAll();
		model.addAttribute("classe",classe);
		List<Matiere>codematiere = matiereRepository.findAll();
		model.addAttribute("codematiere",codematiere);
		return "modifierenseignementss";
	}
	
/*les operations sur les notes*/
	
	@GetMapping(path="/note")
	public String note(Model model) {
		//List<Eleve>lisnote = eleveRepository.findAll();
		List<Note>lisnote = noteRepository.findAll();
		model.addAttribute("lisnote",lisnote);
		List<Enseignement>enseigne = enseignementRepository.findAll();
		model.addAttribute("enseigne",enseigne);
		model.addAttribute("ID",ID);
		model.addAttribute("matiere",matiereTeste);
		model.addAttribute("classe",classeTeste);
		return "notes";
	}
	
	@GetMapping(path="/afficherNote")
	public String afficherNote(Model model) {
		List<Enseignement>enseigne = enseignementRepository.findAll();
		model.addAttribute("enseigne",enseigne);
		model.addAttribute("ID",ID);
		return "AfficherNote";
	}
	@PostMapping(path="/afficherValideNote")
	public String AfficherNote(Model model, String classe1,String matiere) {
		//List<Eleve>lisnote = eleveRepository.findAll();
		List<Note>lisnote = noteRepository.findAll();
		model.addAttribute("lisnote",lisnote);
		List<Enseignement>enseigne = enseignementRepository.findAll();
		model.addAttribute("enseigne",enseigne);
		model.addAttribute("ID",ID);
		matiereTeste=matiere;
	    classeTeste=classe1;
		model.addAttribute("matiere",matiere);
		model.addAttribute("classe",classe1);
		return "AfficherNote";
	}
	
	@PostMapping(path="/consultenote")
	public String ConsulterNote(Model model,String classe1,String matiere) {
		List<Note>lisnote = noteRepository.findAll();
		model.addAttribute("lisnote",lisnote);
		List<Enseignement>enseigne = enseignementRepository.findAll();
		model.addAttribute("enseigne",enseigne);
		model.addAttribute("ID",ID);
		matiereTeste=matiere;
	    classeTeste=classe1;
		model.addAttribute("matiere",matiere);
		model.addAttribute("classe",classe1);
		
		
		return"notes";
		
	}
	
	@PostMapping(path="/AjouterNote")
	public String AjouterNote(Model model,String classe1,String id,String matiere, String note ) {
		
		try {
			
			Eleve eleve=eleveRepository.findById(id).get();
			Matiere matier=matiereRepository.findById(matiere).get();
			EleveKey key = new EleveKey(matiere,id);
			float not=Float.parseFloat(note);
			if(not>=21) {
				String N="impossible d'enregistre note supperieur a 20 ";
				model.addAttribute("N",N);
				List<Enseignement>enseigne = enseignementRepository.findAll();
				model.addAttribute("enseigne",enseigne);
				model.addAttribute("ID",ID);
				List<Note>lisnote = noteRepository.findAll();
				model.addAttribute("lisnote",lisnote);
				matiereTeste=matiere;
			    classeTeste=classe1;
				model.addAttribute("matiere",matiere);
				model.addAttribute("classe",classe1);
				return "notes";
			}
			else {
				Note note1 = new Note(matier,eleve,not);
				note1.setKey(key);
				noteRepository.save(note1);
				List<Note>lisnote = noteRepository.findAll();
				model.addAttribute("lisnote",lisnote);
				List<Enseignement>enseigne = enseignementRepository.findAll();
				model.addAttribute("enseigne",enseigne);
				model.addAttribute("ID",ID);
				matiereTeste=matiere;
			    classeTeste=classe1;
				model.addAttribute("matiere",matiere);
				model.addAttribute("classe",classe1);
				String teste="v";
				model.addAttribute("teste",teste);
			//	List<Note>lisnote = noteRepository.finByCodeMatiere(matiere);
				//model.addAttribute("lisnote",lisnote);
				/*String erreurN="";
			    String erreurI="";
				model.addAttribute("erreurI",erreurI);
				model.addAttribute("erreurN",erreurN);
				*/
				return "notes";
				
			}
		}
		catch(Exception e) {
			List<Classe>classe = classeRepository.findAll();
			model.addAttribute("classe",classe);
			String N="Identifiant de l'eleve n'existe pas Donc la note ne peut etre ajoute ";
			model.addAttribute("N",N);
			List<Note>lisnote = noteRepository.findAll();
			model.addAttribute("lisnote",lisnote);
			List<Enseignement>enseigne = enseignementRepository.findAll();
			model.addAttribute("enseigne",enseigne);
			model.addAttribute("ID",ID);
			matiereTeste=matiere;
		    classeTeste=classe1;
			model.addAttribute("matiere",matiere);
			model.addAttribute("classe",classe1);
			String teste="v";
			model.addAttribute("teste",teste);
			return "notes";
		}
		//Classe clas=classeRepository.findById(classe).get();
		
	}
	@GetMapping(path="/supprimerNote")
	public String SupprimeNote(String matricule,String codem) {
		System.out.println("supprimer"+matricule+" "+codem);
		EleveKey key = new EleveKey(codem,matricule);
		noteRepository.deleteById(key);
		return "redirect:/note";
	}
	
	@GetMapping(path="/ModifierNote")
	public String modifierNote(Model model,String matricule,String codem) {
		EleveKey key = new EleveKey(codem,matricule);
		Note modif=noteRepository.findById(key).get();
		model.addAttribute("modif",modif);
		List<Note>lisnote = noteRepository.findAll();
		model.addAttribute("lisnote",lisnote);
		return "modifiernotes";
	}
	
	/*les operations sur les bulletin*/
	/*@GetMapping(path="/bulletin")
	public String bulletin(Model model) {
		List<Note>lisnote = noteRepository.findAll();
		model.addAttribute("lisnote",lisnote);
		Eleve eleve=eleveRepository.findById(ID).get();
		model.addAttribute("eleve",eleve);
		model.addAttribute("ID",ID);
		
		return "Bulletin";
	}*/
	@GetMapping(path="/bulletin")
		public String bulletin(Model model) {
		List<RangBulletin>eleve1=rangBulletinRepository.findAll();
		//List<Bulletin>eleve1=bullentinRepository.findAll();
		List<Note>lisnote = noteRepository.findAll();
		model.addAttribute("lisnote",lisnote);
		Eleve elev=eleveRepository.findById(ID).get();
		model.addAttribute("elev",elev);
		model.addAttribute("ID",ID);
		model.addAttribute("eleve1",eleve1);
		return "BulletinE";
	}
	
	
	@GetMapping(path="/bulletinP")
	public String bulletinP(Model model, String classe){
		List<Note>lisnote = noteRepository.findAll();
		model.addAttribute("lisnote",lisnote);
		ID=classe;
		List<Eleve>eleve=eleveRepository.findAll();
		model.addAttribute("eleve",eleve);
		som=0;
		moyenne=0;
		moyen=0;
		coef=0;
		admis=0;
		echoue=0;
		nombre=0;
		pourcentage=0;
		
	
		eleve.forEach(p->{
			if(p.getClasse().getCodClasse().equals(ID)){
			  lisnote.forEach(b->{
			    if(b.getIdEleve().getId().equals(p.getId())) {
					coef=coef+b.getCodeMatiere().getCoef();
					som=som+b.getNote();
					moyen=moyen + b.getCodeMatiere().getCoef() * b.getNote();
				 }
			});
			  moyenne=moyen/coef;
			 // (String id, String nom, String prenom, String sexe, String classe, String coef, float moyenne)
			  Bulletin bulletin = new Bulletin(p.getId(),p.getNom(),p.getPrenom(),p.getSexe(),p.getClasse().getCodClasse(),p.getClasse().getNomClasse(),coef,moyenne);
			  bullentinRepository.save(bulletin);
			 
			  som=0;
			  moyenne=0;
			  moyen=0;
			  coef=0;
		}
	}); 
		rang=0;
		List<Bulletin>eleve2=bullentinRepository.findAllByOrderByMoyenneDesc();
		eleve2.forEach(b->{
			 if(b.getClasse().equals(ID)){
				 rang=rang+1;
				 System.out.println("id"+b.getId()+"nom"+b.getNom()+"Prenom"+b.getPrenom()+"classe"+b.getClasse());
				 RangBulletin rangBulletin =new RangBulletin(b.getId(),b.getNom(), b.getPrenom(), b.getSexe(),b.getClasse(),b.getNomclasse(), b.getCoef(),
				 b.getMoyenne(),rang);
				 rangBulletinRepository.save(rangBulletin);
				 System.out.println("rang"+rang);
				 nombre=nombre+1;
				 if(b.getMoyenne()>=10) {
					  admis=admis+1;
				  }
				  if(b.getMoyenne()<10) {
					  echoue=echoue+1;
				  }
				 
				 
				 }
			});
		pourcentage=(admis/nombre)*100;
		//nombre=nombre*100;
		List<RangBulletin>eleve1=rangBulletinRepository.findAllByOrderByMoyenneDesc();
		model.addAttribute("nombre",nombre);
		model.addAttribute("admis",admis);
		model.addAttribute("echoue",echoue);
		model.addAttribute("ID",ID);
		model.addAttribute("eleve1",eleve1);
		model.addAttribute("rang",rang);
		return "BulletinP";
	}
	@GetMapping(path="/page")
	public String Page(Model model) {
		List<Classe>enseigne = classeRepository.findAll();
		model.addAttribute("enseigne",enseigne);
		Professeur p=professeurRepository.findById(ID).get();
		model.addAttribute("p",p);
	//	Eleve eleve=eleveRepository.findById(ID).get();
		//model.addAttribute("eleve",eleve);
		model.addAttribute("ID",ID);
		return "Pageprof";
	}
	
	@GetMapping(path="/vosnote")
	public String vosnote(Model model) {
		List<Note>lisnote = noteRepository.findAll();
		model.addAttribute("lisnote",lisnote);
		Eleve eleve=eleveRepository.findById(ID).get();
		model.addAttribute("eleve",eleve);
		model.addAttribute("ID",ID);
		List<Eleve>liste=eleveRepository.findAll();
		model.addAttribute("liste",liste);
		return "NoteParEleve";
	}
	
	/*les operations sur la connexion*/
	@GetMapping(path="/connexion1")
	public String connexio(Model model) {
		String erreurI="Identifiant n'existe pas";
		model.addAttribute("erreurI",erreurI);
		return "connexion";
	}
	@PostMapping(path="/connexion")
	public String connexion(Model model, String role, String id, String mot) {
		 ID=id;
		if(role.equals("Eleve")) {
			
			try {
				Mot_de_Passe motp = motpasse.findByNom(mot);
				 if(motp.getNom().equals(mot)) {
					 Eleve eleve=eleveRepository.findById(id).get();
					  model.addAttribute("eleve",eleve);
				 }
				
			  return "redirect:/vosnote";
			}
			catch(Exception e) {				
				String erreurI="Identifiant de l'eleve n'existe pas";
				model.addAttribute("erreurI",erreurI);
				return "redirect:/connexion1";
			}
		}
		
	  if(role.equals("Professeur")) {
			
			try {
				Professeur p=professeurRepository.findById(id).get();
				 Mot_de_Passe motp = motpasse.findByNom(mot);
				model.addAttribute("p",p);
			  return "Pageprof";
			}
			catch(Exception e) {				
				String erreurI="Identifiant de professeur n'existe pas";
				model.addAttribute("erreurI",erreurI);
				return "redirect:/connexion1";
			}
		}
	  if(role.equals("Professeur Principale")) {
			
			try {
				Mot_de_Passe motp = motpasse.findByNom(mot);
				 if(motp.getNom().equals(mot)) {
					 System.out.println("mot de passe");
					 Professeur p=professeurRepository.findById(id).get();
					 model.addAttribute("p",p);
				 }
			    return "redirect:/page";
			}
			catch(Exception e) {				
				String erreurI="Identifiant de professeur n'existe pas";
				model.addAttribute("erreurI",erreurI);
				return "redirect:/connexion1";
			}
		}
	if(role.equals("Administrateur")) {
			try {
				Mot_de_Passe motp = motpasse.findByNom(mot);
				 if(motp.getNom().equals(mot)) {
					 System.out.println("mot de passe");
					 Professeur p=professeurRepository.findById(id).get();
					 model.addAttribute("p",p);
				 }
				
				
			  return "pageAdmin";
			}
			catch(Exception e) {				
				String erreurI="Identifiant de l'eleve n'existe pas";
				model.addAttribute("erreurI",erreurI);
				return "redirect:/connexion1";
			}
		}
		
		return "redirect:/connexion1";
	}
	
	@GetMapping(path="/modifierMotpasse")
	public String modifierMotPaasse(Model model,String id) {
	    Mot_de_Passe motp=motpasse.findById(id).get();
		model.addAttribute("motp",motp);
		return "modifierMotPasse";
	}
	
	@PostMapping(path="/AjouterMotpasse")
	public String AjouterMotpasse(Model model,String id,String mot) {
		Mot_de_Passe mot1 = new Mot_de_Passe(id,mot);
		motpasse.save(mot1);
		
		return "redirect:/connexion1";
	}
	
   
	@GetMapping(path="/tp")
	public String teste() {
		List<RangBulletin>eleve1=rangBulletinRepository.findAllByOrderByMoyenneDesc();
		List<Note>lisnote = noteRepository.findAll();
		eleve1.forEach(b->{
		if(b.getClasse().equals(ID)) {
			sendmail(b);
		}
		});
		//sendmail();
		return "index";
	}
	
	//@GetMapping(path="/tp")
	private void  sendmail(RangBulletin b) {
		Mot_de_Passe mot_de_passe = motpasse.findById(b.getId()).get();
		
		String appreciation="";
		String decision;
		if(b.getMoyenne()>=10 && b.getMoyenne()<=11.99) {
			appreciation="PASSABLE";
		}
		if(b.getMoyenne()>=12 && b.getMoyenne()<=13.99) {
			appreciation="ASSEZ BIEN";
		}
		if(b.getMoyenne()>=14 && b.getMoyenne()<=15.99) {
			appreciation="BIEN";
		}
		if(b.getMoyenne()>=16 && b.getMoyenne()<=17.99) {
			appreciation="TRES BIEN";
		}
		if(b.getMoyenne()>=18 && b.getMoyenne()<=19.99) {
			appreciation="EXECELLENTE";
		}
		if(b.getMoyenne()==20) {
			appreciation="PARFAIT";
		}
		if(b.getMoyenne()<10) {
			appreciation="FAIBLE";
		}
		
		if(b.getMoyenne()>=10) {
			decision="Admis";
		}else {
			decision="Echoué";
		}
		String eleve = b.getId();
	     Eleve elev=eleveRepository.findById(eleve).get();
		 System.out.println("---bien arriveee---" );
		final String emailToRecipient = elev.getNumeroParent();
		//final String emailToRecipient = "badamahassane@gmail.com";
		final String emailSubject = "le bulletin";
		final String message ="<!DOCTYPE html>\n"
				+ "<html xmlns:th=\"http://www.thymeleaf.org\">\n"
				+ "	<head>\n"
				+ "	<meta charset=\"UTF-8\"/>\n"
				+ "	<title>Insert title here</title>\n"
				+ "	<link rel=\"stylesheet\" type=\"text/css\" th:href=\"@{/csRRs/style.css}\"/>\n"
				+ "	</head>\n"
				+ "<body style=\"padding:0px;\n"
				+ "	margin:0px;\n"
				+ "	background: rgb(160, 155, 163);\">\n"
				+ "    <section Style=\"margin-top:130px;\n"
				+ "	\n"
				+ "	justify-content: center;\" class=\"bulle1\">\n"
				+ "	  \n"
				+ "   <h5 style=\"font-family: andalus;\n"
				+ "	letter-spacing: 2px;\n"
				+ "	color: aliceblue;\n"
				+ "	font-family: Impact, Haettenschweiler, 'Arial Narrow Bold', sans-serif\" >Chers parents,</h5>\n"
				+ "	           <h5 style=\"font-family: andalus;\n"
				+ "	letter-spacing: 2px;\n"
				+ "	color: aliceblue;\n"
				+ "	font-family: Impact, Haettenschweiler, 'Arial Narrow Bold', sans-serif\" >Nous les responsables de lycee Miskine Kaltane vous informe que le bulletin de votre enfant :" +b.getNom()+ "  " +b.getPrenom()+ "</h5>\n"
				+ "	           <h5 style=\"font-family: andalus;\n"
				+ "	letter-spacing: 2px;\n"
				+ "	color: aliceblue;\n"
				+ "	font-family: Impact, Haettenschweiler, 'Arial Narrow Bold', sans-serif\">est disponible vous pouvez consulter via miskine.com avec son matricule : "+mot_de_passe.getId()+" et son mot de passe : "+mot_de_passe.getNom()+"</h5>\n"
				+ "	        \n"
				+ "Voici l'essentiel de son travail  \n"
				+ "	 <table style=\"padding: 15px 50px;\n"
				+ "	border: 2px solid rgb(245, 245, 245);\n"
				+ "	color: rgb(255, 255, 255);\n"
				+ "	font-family: Impact, Haettenschweiler, 'Arial Narrow Bold', sans-serif;\" class=\"entete3\">\n"
				+ "	          <tr style=\"padding: 15px 50px;\n"
				+ "	border: 2px solid rgb(245, 245, 245);\n"
				+ "	color: rgb(255, 255, 255);\n"
				+ "	font-family: Impact, Haettenschweiler, 'Arial Narrow Bold', sans-serif;\" class=\"tp\">\n"
				+ "	            <td style=\"padding: 15px 50px;\n"
				+ "	border: 2px solid rgb(245, 245, 245);\n"
				+ "	color: rgb(255, 255, 255);\n"
				+ "	font-family: Impact, Haettenschweiler, 'Arial Narrow Bold', sans-serif;\" class=\"tp\">Moyenne</td> <td style=\"padding: 15px 50px;\n"
				+ "	border: 2px solid rgb(245, 245, 245);\n"
				+ "	color: rgb(255, 255, 255);\n"
				+ "	font-family: Impact, Haettenschweiler, 'Arial Narrow Bold', sans-serif;\" class=\"tp\"> APPRECIATION</td> <td style=\"padding: 15px 50px;\n"
				+ "	border: 2px solid rgb(245, 245, 245);\n"
				+ "	color: rgb(255, 255, 255);\n"
				+ "	font-family: Impact, Haettenschweiler, 'Arial Narrow Bold', sans-serif;\" class=\"tp\">RANG</td> <td style=\"padding: 15px 50px;\n"
				+ "	border: 2px solid rgb(245, 245, 245);\n"
				+ "	color: rgb(255, 255, 255);\n"
				+ "	font-family: Impact, Haettenschweiler, 'Arial Narrow Bold', sans-serif;\" class=\"tp\"> DECISION</td> \n"
				+ "	          </tr>\n"
				+ "	          <tr style=\"padding: 15px 50px;\n"
				+ "	border: 2px solid rgb(245, 245, 245);\n"
				+ "	color: rgb(255, 255, 255);\n"
				+ "	font-family: Impact, Haettenschweiler, 'Arial Narrow Bold', sans-serif;\" class=\"tp\">\n"
				+ "					<td style=\"padding: 15px 50px;\n"
				+ "	border: 2px solid rgb(245, 245, 245);\n"
				+ "	color: rgb(255, 255, 255);\n"
				+ "	font-family: Impact, Haettenschweiler, 'Arial Narrow Bold', sans-serif;\" class=\"tp\">"+b.getMoyenne()+ "/20</td>\n"
				+ "					<td style=\"padding: 15px 50px;\n"
				+ "	border: 2px solid rgb(245, 245, 245);\n"
				+ "	color: rgb(255, 255, 255);\n"
				+ "	font-family: Impact, Haettenschweiler, 'Arial Narrow Bold', sans-serif;\" class=\"tp\">" + appreciation + "</td>\n"
				+ "					<td style=\"padding: 15px 50px;\n"
				+ "	border: 2px solid rgb(245, 245, 245);\n"
				+ "	color: rgb(255, 255, 255);\n"
				+ "	font-family: Impact, Haettenschweiler, 'Arial Narrow Bold', sans-serif;\" class=\"tp\">"+b.getRang()+"e</td>\n"
				+ "					<td style=\"padding: 15px 50px;\n"
				+ "	border: 2px solid rgb(245, 245, 245);\n"
				+ "	color: rgb(255, 255, 255);\n"
				+ "	font-family: Impact, Haettenschweiler, 'Arial Narrow Bold', sans-serif;\" class=\"tp\">"+ decision +"</td>\n"
				+ "					\n"
				+ "	 </tr>\n"
				+ "			  \n"
				+ "	   </table>\n"
				+ "	      \n"
				+ "	    </div>\n"
				+ "	   </div>\n"
				+ "	 </section>\n"
				+ "  </body>\n"
				+ "</html>";
		
		javaMailSender.send(new MimeMessagePreparator() {

			@Override
			public void prepare(MimeMessage mimeMessage) throws Exception {
				// TODO Auto-generated method stub
				MimeMessageHelper Helper=new MimeMessageHelper(mimeMessage, true, "UTF-8");
				Helper.setTo(emailToRecipient);
				Helper.setText(message, true);
				Helper.setSubject(emailSubject);
				
				
			}
			
		});
		
	}
}
	//MimeMessage message = javaMailSender.createMimeMessage();
			//MimeMessageHelper helper = new MimeMessageHelper(message,MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED);
			//helper.addAttachment("logo.png", new ClassPathResource("logo.png"));
			//Template template = configuration.getTemplates("index");
	
	//@GetMapping(path="/eleve_id") @PathVariable("id")
 /*  @GetMapping(path="/{id}",produces=MediaType.APPLICATION_PDF_VALUE)
	public ResponseEntity<InputStreamResource>EleveReports(@PathVariable("id")String id){
	   //String n=id;
	   System.out.println("---bien arriveee---" +id);
		 Eleve eleve=eleveRepository.findById("13").get();
		 System.out.println("ppp---bien arriveee---");
		ByteArrayInputStream bis = ExportPdf.eleveReport(eleve);
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Disposition","attachment.filename="+eleve.getNom()+"report.pdf");
		sendmail(eleve,bis);
		
		return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF).body(new InputStreamResource(bis));
	}
	private void sendmail(Eleve eleve, ByteArrayInputStream bis) {
		// TODO Auto-generated method stub
		final String emailToRecipent ="hassanebadamasinna@gmail.com";
		final String emailSubject = "Regarding Report";
		final String emailMessage1 ="<html>"
				+ "<head>"
				+ "</head>"
				+ "<body>"
				+ "    <h1>teset javamail bommmm</h1>"
				+ "</body>"
				+ "</html>";
		mailSenderObj.send(new MimeMessagePreparator() {
			public void prepare(MimeMessage mimeMessage) throws MessagingException, IOException{
				MimeMessageHelper mimeMessageHelperObj=new MimeMessageHelper(mimeMessage, true, "UTF-8");
				mimeMessageHelperObj.setTo(emailToRecipent);
				mimeMessageHelperObj.setText(emailMessage1);
				mimeMessageHelperObj.setSubject(emailSubject);
				
				jakarta.activation.DataSource attachment = new ByteArrayDataSource(bis,"application/pdf");
				
				mimeMessageHelperObj.addAttachment(eleve.getNom()+".pdf",attachment);
			}
		});
		
	}*/
	
	
	

