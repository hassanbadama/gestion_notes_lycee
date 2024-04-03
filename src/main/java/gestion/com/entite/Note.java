package gestion.com.entite;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;

@Entity
public class Note {
	@EmbeddedId
	private EleveKey key;
	
	@ManyToOne
	@MapsId("CodeMatiere")
	@JoinColumn(name="CodeMatiere")
	private Matiere CodeMatiere;
	
	@ManyToOne
	@MapsId("IdEleve")
	@JoinColumn(name="IdEleve")
	private Eleve IdEleve;
	
	private float note;
	
	public Note() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Note(Matiere codeMatiere, Eleve idEleve, float note) {
		super();
		CodeMatiere = codeMatiere;
		IdEleve = idEleve;
		this.note = note;
	}

	public EleveKey getKey() {
		return key;
	}

	public void setKey(EleveKey key) {
		this.key = key;
	}

	public Matiere getCodeMatiere() {
		return CodeMatiere;
	}

	public void setCodeMatiere(Matiere codeMatiere) {
		CodeMatiere = codeMatiere;
	}

	public Eleve getIdEleve() {
		return IdEleve;
	}

	public void setIdEleve(Eleve idEleve) {
		IdEleve = idEleve;
	}

	public float getNote() {
		return note;
	}

	public void setNote(float note) {
		this.note = note;
	}
	
	
	
	

	
}
