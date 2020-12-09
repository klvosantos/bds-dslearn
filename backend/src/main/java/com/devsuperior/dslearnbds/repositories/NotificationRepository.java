package com.devsuperior.dslearnbds.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.devsuperior.dslearnbds.entities.Notification;
import com.devsuperior.dslearnbds.entities.User;

public interface NotificationRepository extends JpaRepository<Notification, Long>{
	
	@Query("SELECT obj FROM Notification obj WHERE "
			+ "(obj.user = :user) AND "
			+ "(:unreadOnly = false OR obj.read = false) "
			+ "ORDER BY  obj.moment DESC")			
	Page<Notification> find(User user, boolean unreadOnly, Pageable pageable);	
}

// como é uma busca de notificações por usuario, o user vai como argumento.
// como sera restringido as notificações lidas das não lidas vai um arg boleano.
// Em toda busca paginada do spring data jpa é necessario o arg Pageable.

// se unreadOnly for falso ele não restringe e busca todos // nem faz o OR.
// se unreadOnly for verdadeiro ele faz a expressão apos o OR e mostra apenas as notificações  não lidas.