package gestion.com.entite;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Mot_de_Passe {
	@Id
    private String id;
    private String nom;
    
	public Mot_de_Passe(String id, String nom) {
		this.id = id;
		this.nom = nom;
	}
	
	public Mot_de_Passe() {
		super();
		// TODO Auto-generated constructor stub
	}


	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNomEtprenom(String nom) {
		this.nom = nom;
	}
	
}
