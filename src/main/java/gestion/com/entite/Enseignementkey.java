package gestion.com.entite;

import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Embeddable
public class Enseignementkey {
	
	
	@JoinColumn(name="matricule")
	private String matricule;
	@JoinColumn(name="CodMatiere")
	private String CodMatiere;
	@JoinColumn(name="CodClasse")
	private String CodClasse;
	
	
	public Enseignementkey() {
		super();
		// TODO Auto-generated constructor stub
	}


	public Enseignementkey(String matricule, String codMatiere, String codClasse) {
		super();
		this.matricule = matricule;
		CodMatiere = codMatiere;
		CodClasse = codClasse;
	}


	public String getMatricule() {
		return matricule;
	}


	public void setMatricule(String matricule) {
		this.matricule = matricule;
	}


	public String getCodMatiere() {
		return CodMatiere;
	}


	public void setCodMatiere(String codMatiere) {
		CodMatiere = codMatiere;
	}


	public String getCodClasse() {
		return CodClasse;
	}


	public void setCodClasse(String codClasse) {
		CodClasse = codClasse;
	}
	
	

	
}
