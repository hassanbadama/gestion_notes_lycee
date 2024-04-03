package gestion.com.entite;

import java.sql.Date;
import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

@Entity
public class Eleve {
	@Id
    private String id;
    private String nom;
    private String prenom;
    private String sexe;
    private Date dateNaissance;
    private String numeroParent;
    
    @ManyToOne
	@JoinColumn(name="classe")
    private Classe classe;
    @OneToMany(mappedBy="IdEleve", fetch=FetchType.LAZY)
    private Collection<Note> note;
    @OneToOne(mappedBy="eleve", fetch=FetchType.LAZY)
	 private Bulletin bulletin;
	public Eleve() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Eleve(String id, String nom, String prenom, String sexe, Date dateNaissance,String numeroParent, Classe classe) {
		super();
		this.id = id;
		this.nom = nom;
		this.prenom = prenom;
		this.sexe = sexe;
		this.dateNaissance = dateNaissance;
		this.numeroParent = numeroParent;
		this.classe = classe;
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
	public Date getDateNaissance() {
		return dateNaissance;
	}
	public void setDateNaissance(Date dateNaissance) {
		this.dateNaissance = dateNaissance;
	}
	
	public String getNumeroParent() {
		return numeroParent;
	}
	public void setNumeroParent(String numeroParent) {
		this.numeroParent = numeroParent;
	}
	public Classe getClasse() {
		return classe;
	}
	public void setClasse(Classe classe) {
		this.classe = classe;
	}
	public Collection<Note> getNote() {
		return note;
	}
	public void setNote(Collection<Note> note) {
		this.note = note;
	}
    
    
    
}
