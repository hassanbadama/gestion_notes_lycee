package gestion.com.entite;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

@Entity
public class RangBulletin {
	@Id
	private String id;
	private String nom;
	private String prenom;
	private String sexe;
	private String classe;
	private String Nomclasse;
	private int coef;
	private float moyenne;
	private int rang;
	@OneToOne
	@JoinColumn(name="bulletin")
	private Bulletin bulletin;
	
	public RangBulletin() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public RangBulletin(String id, String nom, String prenom, String sexe, String classe, String nomclasse, int coef,
			float moyenne, int rang) {
		super();
		this.id = id;
		this.nom = nom;
		this.prenom = prenom;
		this.sexe = sexe;
		this.classe = classe;
		Nomclasse = nomclasse;
		this.coef = coef;
		this.moyenne = moyenne;
		this.rang = rang;
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
	public String getClasse() {
		return classe;
	}
	public void setClasse(String classe) {
		this.classe = classe;
	}
	public String getNomclasse() {
		return Nomclasse;
	}
	public void setNomclasse(String nomclasse) {
		Nomclasse = nomclasse;
	}
	public int getCoef() {
		return coef;
	}
	public void setCoef(int coef) {
		this.coef = coef;
	}
	public float getMoyenne() {
		return moyenne;
	}
	public void setMoyenne(float moyenne) {
		this.moyenne = moyenne;
	}
	
	public int getRang() {
		return rang;
	}

	public void setRang(int rang) {
		this.rang = rang;
	}

	public Bulletin getBulletin() {
		return bulletin;
	}
	public void setBulletin(Bulletin bulletin) {
		this.bulletin = bulletin;
	}
	
	

}
