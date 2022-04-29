package com.example.gwent_projet.integ.service.cardDeck;

import com.example.gwent_projet.entity.card.CardDeck;
import com.example.gwent_projet.repository.CardDeckRepository;
import com.example.gwent_projet.services.CardDeckService;
import com.example.gwent_projet.services.dto.cardDeck.CardDeckDTO;
import com.example.gwent_projet.services.dto.cardDeck.CreateCardDeckDTO;

import org.jeasy.random.EasyRandom;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Random;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
public class CardDeckServiceTests {

	@Autowired
	private CardDeckService cardDeckService;
	@Autowired
	private CardDeckRepository cardDeckRepository;

	private Long tableLength;

	private EasyRandom RNGenerator = new EasyRandom();

	CardDeck cardDeck;

	CreateCardDeckDTO createCardDeckDTO = new CreateCardDeckDTO("card deck");

	// --------------------------------------------------------------------------------

	@BeforeEach
	public void initRepo() {
		// random index for table length
		Random random = new Random();
		tableLength = random.nextLong(10);
		// ensure that it is not null
		if (tableLength == 0l) {
			tableLength++;
		}

		// populate repository
		for (Long sweeper = 0l; sweeper < tableLength; sweeper++) {
			// new random card deck
			CardDeck tempCardDeck = RNGenerator.nextObject(CardDeck.class);
			tempCardDeck.setId(null);
			tempCardDeck = cardDeckRepository.save(tempCardDeck); 

			// get the first card of each instance of the repo
			// this will be used for our tests
			if (cardDeck == null) {
				cardDeck = tempCardDeck;
			}
		}
	}

	@AfterEach
	public void cleanupRepo() {
		cardDeckRepository.deleteAll();
	}

	// --------------------------------------------------------------------------------

	public void cardDeckEqualsAssertions(CardDeckDTO cardDeck1, CardDeckDTO cardDeck2) {
		assertThat(cardDeck1).isNotNull();
		assertThat(cardDeck2).isNotNull();
		assertThat(cardDeck1.getId()).isEqualTo(cardDeck2.getId());
		assertThat(cardDeck1.getName()).isEqualTo(cardDeck2.getName());
	}

	public void cardDeckAssertions(CardDeckDTO cardDeck) {
		assertThat(this.cardDeck).isNotNull();
		assertThat(this.cardDeck.getId()).isNotNull();
		assertThat(this.cardDeck.getName()).isNotEmpty();
	}

	// --------------------------------------------------------------------------------

	@Test
	@DisplayName("Card Deck - Find All")
	void testCardDeckFindAll() {
		List<CardDeckDTO> cardDeckDTOS = cardDeckService.getAllCardDecks();

		assertThat(cardDeckDTOS).isNotEmpty();
		assertThat(cardDeckDTOS.size()).isEqualTo(cardDeckRepository.count());

		for (int sweeper = 0; sweeper < cardDeckRepository.count(); sweeper++) {
			cardDeckAssertions(cardDeckDTOS.get(sweeper));
		}
	}

	// --------------------------------------------------------------------------------

	@Test
	@DisplayName("Card Deck - Find by ID")
	void testCardDeckFindById() {

		CardDeckDTO searchCardDeck = cardDeckService.getCardDeckById(cardDeck.getId());
		// assertions
		cardDeckAssertions(searchCardDeck);
		cardDeckEqualsAssertions(searchCardDeck, cardDeckService.getCardDeckById(cardDeck.getId()));
	}

	// --------------------------------------------------------------------------------

	@Test
	@DisplayName("Card Deck - Create")
	void testCardDeckCreation() {

		long nbCardDecks = this.cardDeckRepository.count();
		CardDeckDTO cardDeckDTO = cardDeckService.createCardDeck(createCardDeckDTO);

		// assertions
		cardDeckAssertions(cardDeckDTO);
		assertThat(cardDeckDTO.getName()).isEqualTo(createCardDeckDTO.getName());
		assertThat(this.cardDeckRepository.count()).isGreaterThan(nbCardDecks);
	}

	// --------------------------------------------------------------------------------

	@Test
	@DisplayName("Card Deck - Update")
	void testCardDeckEdit() {
		CardDeckDTO beforeCardDeckDTO = cardDeckService.createCardDeck(createCardDeckDTO);
		Long id = beforeCardDeckDTO.getId();

		// modifier
		CreateCardDeckDTO cardDeckUpdate = new CreateCardDeckDTO("neoMomo");

		CardDeckDTO afterCardDeckDTO = this.cardDeckService.updateCardDeck(id, cardDeckUpdate);

		// assertions
		assertThat(beforeCardDeckDTO).isNotEqualTo(afterCardDeckDTO);
	}

	// --------------------------------------------------------------------------------

	@Test
	@DisplayName("Card Deck - Delete")
	void testCardDeckDelete() {
		// get last card deck in repository
		Long repoSizeBefore = cardDeckRepository.count();
		CardDeck searchResultBefore = cardDeckRepository.findById(cardDeck.getId()).orElse(null);

		// method to test
		cardDeckService.deleteCardDeckById(cardDeck.getId());

		Long repoSizeAfter = cardDeckRepository.count();
		CardDeck searchResultAfter = cardDeckRepository.findById(cardDeck.getId()).orElse(null);

		// assertions
		assertThat(searchResultBefore).isNotEqualTo(searchResultAfter);
		assertThat(repoSizeBefore).isNotEqualTo(repoSizeAfter);
	}
}
