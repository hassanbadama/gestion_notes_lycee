package gestion.com.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import gestion.com.entite.Bulletin;
import gestion.com.entite.RangBulletin;

public interface RangBulletinRepository extends JpaRepository<RangBulletin, String> {
	public List<RangBulletin> findAllByOrderByMoyenneDesc();

}
