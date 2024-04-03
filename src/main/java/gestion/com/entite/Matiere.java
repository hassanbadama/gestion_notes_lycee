package gestion.com.entite;

import java.util.Collection;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Matiere {
	@Id
   private String codMatiere;
   private String NomMatiere;
   private int coef;
   
   @OneToMany(mappedBy="CodMatiere", fetch=FetchType.LAZY)
   private Collection<Enseignement> enseignement;
   @OneToMany(mappedBy="CodeMatiere", fetch=FetchType.LAZY)
   private Collection<Note> note;
   
public Matiere() {
	super();
	// TODO Auto-generated constructor stub
}
public Matiere(String codMatiere, String nomMatiere, int coef) {
	super();
	this.codMatiere = codMatiere;
	NomMatiere = nomMatiere;
	this.coef = coef;
}
public String getCodMatiere() {
	return codMatiere;
}
public void setCodMatiere(String codMatiere) {
	this.codMatiere = codMatiere;
}
public String getNomMatiere() {
	return NomMatiere;
}
public void setNomMatiere(String nomMatiere) {
	NomMatiere = nomMatiere;
}
public int getCoef() {
	return coef;
}
public void setCoef(int coef) {
	this.coef = coef;
}
public Collection<Enseignement> getEnseignement() {
	return enseignement;
}
public void setEnseignement(Collection<Enseignement> enseignement) {
	this.enseignement = enseignement;
}
public Collection<Note> getNote() {
	return note;
}
public void setNote(Collection<Note> note) {
	this.note = note;
}

   
}
