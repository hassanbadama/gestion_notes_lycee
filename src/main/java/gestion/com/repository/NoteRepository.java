package gestion.com.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import gestion.com.entite.EleveKey;
import gestion.com.entite.Enseignement;
import gestion.com.entite.Enseignementkey;
import gestion.com.entite.Note;


public interface NoteRepository extends JpaRepository <Note, EleveKey>{
	//public List<Note> findByIdEleve(EleveKey key);
	@Query("select n from Note n where n.IdEleve like:infn")
	public List<Note> IdEleve(@Param("infn")String key );
	/*@Query("SELECT e FROM eleve e JOINT e.note n WHERE e.id=n.id_eleve AND n.code_matiere:codem AND e.classe:codec")
	   List<Note> finByAlle(
			    @Param("codem")String matiere,
			    @Param("codec")String classe
			   );
*/
}
