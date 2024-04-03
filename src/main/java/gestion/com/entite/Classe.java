package gestion.com.entite;

import java.util.Collection;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
//import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class Classe {
	 @Id
	 private String codClasse;
	 private String NomClasse;
	
	 @OneToMany(mappedBy="CodClasse", fetch=FetchType.LAZY)
	 private Collection<Enseignement> enseignement;
	 @OneToMany(mappedBy="classe", fetch=FetchType.LAZY)
	 private Collection<Eleve> eleve;
	 @ManyToOne
	 @JoinColumn(name="professeur")
	 private Professeur professeur;
	 
	public Classe() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Classe(String codClasse, String nomClasse, Professeur professeur) {
		super();
		this.codClasse = codClasse;
		NomClasse = nomClasse;
		this.professeur = professeur;
	}

	public String getCodClasse() {
		return codClasse;
	}

	public void setCodClasse(String codClasse) {
		this.codClasse = codClasse;
	}

	public String getNomClasse() {
		return NomClasse;
	}

	public void setNomClasse(String nomClasse) {
		NomClasse = nomClasse;
	}

	public Collection<Enseignement> getEnseignement() {
		return enseignement;
	}

	public void setEnseignement(Collection<Enseignement> enseignement) {
		this.enseignement = enseignement;
	}

	public Collection<Eleve> getEleve() {
		return eleve;
	}

	public void setEleve(Collection<Eleve> eleve) {
		this.eleve = eleve;
	}

	public Professeur getProfesseur() {
		return professeur;
	}

	public void setProfesseur(Professeur professeur) {
		this.professeur = professeur;
	}
	
  
}
