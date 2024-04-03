package gestion.com.entite;

import java.sql.Date;
import java.util.Collection;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Professeur {
	@Id
      private String matricule;
	
      private String nom;
	
      private String prenom;
      private String sexe;
     
      private String contact;
     @DateTimeFormat(pattern="yyyy-MM-dd")
      private Date dateNaiss;
      private String code;
      
      @OneToMany(mappedBy="matricule", fetch=FetchType.LAZY)
      private Collection<Enseignement> enseignement;
      @OneToMany(mappedBy="professeur", fetch=FetchType.LAZY)
      private Collection<Classe> classe;
      
	public Professeur() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Professeur(String matricule, String nom, String prenom, String sexe, String contact, Date dateNaiss,String code) {
		super();
		this.matricule = matricule;
		this.nom = nom;
		this.prenom = prenom;
		this.sexe = sexe;
		this.contact = contact;
		this.dateNaiss = dateNaiss;
		this.code= code;
	}
	
	
	public String getMatricule() {
		return matricule;
	}
	public void setMatricule(String matricule) {
		this.matricule = matricule;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	public String getSexe() {
		return sexe;
	}
	public void setSexe(String sexe) {
		this.sexe = sexe;
	}
	public String getContact() {
		return contact;
	}
	public void setContact(String contact) {
		this.contact = contact;
	}
	public Date getDateNaiss() {
		return dateNaiss;
	}
	public void setDateNaiss(Date dateNaiss) {
		this.dateNaiss = dateNaiss;
	}
	public Collection<Enseignement> getEnseignement() {
		return enseignement;
	}
	public void setEnseignement(Collection<Enseignement> enseignement) {
		this.enseignement = enseignement;
	}
	public Collection<Classe> getClasse() {
		return classe;
	}
	public void setClasse(Collection<Classe> classe) {
		this.classe = classe;
	}  
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
}
