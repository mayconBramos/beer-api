package net.atos.beerapi.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import net.atos.beerapi.model.Beer;
import net.atos.beerapi.repository.BeerRepository;

@Service
public class BeerService implements IBeerService {

	private final BeerRepository beerRepository;

	public BeerService(BeerRepository beerRepository) {
		this.beerRepository = beerRepository;
	}

	@Override
	public List<Beer> getAllBeers() {
		return (List<Beer>) this.beerRepository.findAll();
	}

	@Override
	public Beer getBeerById(Long id) {
		Optional<Beer> optionalBeer = this.beerRepository.findById(id);
		return optionalBeer.orElse(null);
	}

	@Override
	public Beer saveBeer(Beer beer) {
		return this.beerRepository.save(beer);
	}

	@Override
	public void deleteBeer(Long id) {
		this.beerRepository.deleteById(id);
	}

	@Override
	public List<Beer> getBeersByType(String type) {
		return beerRepository.findByType(type);
	}

	@Override
	public List<Beer> getBeersManufacturedOn(LocalDate manufacturingDate) {
		return beerRepository.findByManufacturingDate(manufacturingDate);
	}

	@Override
	public List<Beer> getBeersExpiringBefore(LocalDate expirationDate) {
		return beerRepository.findByExpirationDateBefore(expirationDate);
	}

}
