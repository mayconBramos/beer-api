package net.atos.beerapi.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

import net.atos.beerapi.model.Beer;

public interface BeerRepository extends CrudRepository<Beer, Long> {

	List<Beer> findByManufacturingDate(LocalDate manufacturingDate);

	List<Beer> findByExpirationDateBefore(LocalDate expirationDate);

	List<Beer> findByType(String type);

}