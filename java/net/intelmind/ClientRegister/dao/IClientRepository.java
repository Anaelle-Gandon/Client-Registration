package net.intelmind.ClientRegister.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import net.intelmind.ClientRegister.entities.Client;

public interface IClientRepository extends JpaRepository<Client, Integer> {

	

}
