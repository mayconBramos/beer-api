package net.atos.beerapi.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.jsonwebtoken.Claims;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import net.atos.beerapi.model.Beer;
import net.atos.beerapi.provider.JwtTokenProvider;
import net.atos.beerapi.service.BeerService;

@RestController
@RequestMapping("/api/beers")
@CrossOrigin(origins = "*", maxAge = 3600)
public class BeerController implements IBeerController {

	private final BeerService beerService;
	private final JwtTokenProvider tokenProvider;

	public BeerController(BeerService beerService, JwtTokenProvider tokenProvider) {
		this.beerService = beerService;
		this.tokenProvider = tokenProvider;
	}

	@Override
	@ApiImplicitParams({
			@ApiImplicitParam(name = "Authorization", value = "JWT token", required = true, paramType = "header", dataType = "string") })
	@GetMapping
	public ResponseEntity<List<Beer>> getAllBeer(@RequestHeader("Authorization") String token) {
		if (token == null || !token.startsWith("Bearer ")) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		}

		String jwtToken = token.substring(7);
		Claims claims = tokenProvider.validateToken(jwtToken);
		if (claims == null) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		}

		List<Beer> beers = beerService.getAllBeers();
		return ResponseEntity.ok(beers);
	}

	@Override
	@GetMapping("/{id}")
	public Beer getBeerById(@PathVariable Long id) {
		return beerService.getBeerById(id);
	}

	@Override
	@PostMapping
	public Beer saveBeer(@RequestBody Beer beer) {
		return beerService.saveBeer(beer);
	}

	@Override
	@DeleteMapping("/{id}")
	public void deleteBeer(@PathVariable Long id) {
		beerService.deleteBeer(id);
	}

	@GetMapping("/type/{type}")
	public List<Beer> getBeersByType(@PathVariable String type) {
		return beerService.getBeersByType(type);
	}

	@GetMapping("/manufacturing/{manufacturingDate}")
	public List<Beer> getBeersManufacturedOn(
			@PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate manufacturingDate) {
		return beerService.getBeersManufacturedOn(manufacturingDate);
	}

	@GetMapping("/expiration/{expirationDate}")
	public List<Beer> getBeersExpiringBefore(
			@PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate expirationDate) {
		return beerService.getBeersExpiringBefore(expirationDate);
	}
}
