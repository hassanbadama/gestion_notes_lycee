package gestion.com.entite;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;

@Entity

public class Enseignement {
	@EmbeddedId
	private Enseignementkey keyer;
	//@Id
	@ManyToOne
	@MapsId("matricule")
	@JoinColumn(name="matricule")
	private Professeur matricule;
	//@Id
	@ManyToOne
	@MapsId("CodMatiere")
	@JoinColumn(name="CodMatiere")
	private Matiere CodMatiere;
	
//	@Id
	@ManyToOne
	@MapsId("CodClasse")
	@JoinColumn(name="CodClasse")
	private Classe CodClasse;
	
	public Enseignement() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	


	public Enseignement(Professeur matricule, Matiere codMatiere, Classe codClasse) {
		super();
		this.matricule = matricule;
		CodMatiere = codMatiere;
		CodClasse = codClasse;
	}


	public Enseignementkey getKeyer() {
		return keyer;
	}

	public void setKeyer(Enseignementkey keyer) {
		this.keyer = keyer;
	}

	public Professeur getMatricule() {
		return matricule;
	}

	public void setMatricule(Professeur matricule) {
		this.matricule = matricule;
	}

	public Matiere getCodMatiere() {
		return CodMatiere;
	}

	public void setCodMatiere(Matiere codMatiere) {
		CodMatiere = codMatiere;
	}

	public Classe getCodClasse() {
		return CodClasse;
	}

	public void setCodClasse(Classe codClasse) {
		CodClasse = codClasse;
	}
	
	
/*
    public Enseignement(Professeur matricule, Matiere codMatiere, Classe codClasse) {
		super();
		matricule = matricule;
		CodMatiere = codMatiere;
		CodClasse = codClasse;
	}

	public Professeur getMatriculeProf() {
		return matricule;
	}

	public void setMatricule(Professeur matricule) {
		matricule = matricule;
	}

	public Matiere getCodMatiere() {
		return CodMatiere;
	}

	public void setCodeMatiere(Matiere codMatiere) {
		CodMatiere = codMatiere;
	}

	public Classe getCodClasse() {
		return CodClasse;
	}

	public void setCodClasse(Classe codClasse) {
		CodClasse = codClasse;
	}
	*/
}

