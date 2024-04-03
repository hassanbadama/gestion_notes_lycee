package gestion.com.entite;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;

@Entity
public class Bulletin {
	@Id
	private String id;
	private String nom;
	private String prenom;
	private String sexe;
	private String classe;
	private String Nomclasse;
	private int coef;
	private float moyenne;
	@OneToOne
	@JoinColumn(name="eleve")
	private Eleve eleve;
	@OneToOne(mappedBy="bulletin", fetch=FetchType.LAZY)
	 private RangBulletin bulletin;
	public Bulletin() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Bulletin(String id, String nom, String prenom, String sexe, String classe, String nomclasse, int coef,
			float moyenne) {
		super();
		this.id = id;
		this.nom = nom;
		this.prenom = prenom;
		this.sexe = sexe;
		this.classe = classe;
		Nomclasse = nomclasse;
		this.coef = coef;
		this.moyenne = moyenne;
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
	
	public String getNomclasse() {
		return Nomclasse;
	}

	public void setNomclasse(String nomclasse) {
		Nomclasse = nomclasse;
	}

	public void setClasse(String classe) {
		this.classe = classe;
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
	public Eleve getEleve() {
		return eleve;
	}
	public void setEleve(Eleve eleve) {
		this.eleve = eleve;
	}
	

}
