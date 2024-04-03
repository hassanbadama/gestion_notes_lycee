package gestion.com.entite;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Embeddable
public class EleveKey {
	
	@JoinColumn(name="CodeMatiere")
	private String CodeMatiere;
	@JoinColumn(name="IdEleve")
	private String IdEleve;
	
	
	public EleveKey() {
		super();
		// TODO Auto-generated constructor stub
	}
	

	public EleveKey(String codeMatiere, String idEleve) {
		super();
		CodeMatiere = codeMatiere;
		IdEleve = idEleve;
	}

	public String getCodeMatiere() {
		return CodeMatiere;
	}


	public void setCodeMatiere(String codeMatiere) {
		CodeMatiere = codeMatiere;
	}


	public String getIdEleve() {
		return IdEleve;
	}


	public void setIdEleve(String idEleve) {
		IdEleve = idEleve;
	}
	
	


	
}
