package gestion.com.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import gestion.com.entite.Bulletin;

@Repository
public interface BullentinRepository extends JpaRepository <Bulletin, String> {
	public List<Bulletin> findAllByOrderByMoyenneDesc();

}
