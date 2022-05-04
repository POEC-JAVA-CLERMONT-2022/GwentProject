package com.example.gwent_projet.integ.service.card;


import com.example.gwent_projet.entity.card.*;
import com.example.gwent_projet.repository.CardDeckRepository;
import com.example.gwent_projet.repository.CardRepository;
import com.example.gwent_projet.services.CardDeckService;
import com.example.gwent_projet.services.CardService;
import com.example.gwent_projet.services.dto.card.CardDTO;
import com.example.gwent_projet.services.dto.cardDeck.CardDeckDTO;
import com.example.gwent_projet.services.dto.card.CreateCardDTO;
import com.example.gwent_projet.services.dto.cardDeck.CreateCardDeckDTO;

import org.jeasy.random.EasyRandom;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
public class CardServiceTest {

	@Autowired
	private CardService cardService;
	@Autowired
	private CardRepository cardRepository;

	@Autowired
	private CardDeckService cardDeckService;
	@Autowired
	private CardDeckRepository cardDeckRepository;
	
	private Long tableLength;

	private EasyRandom RNGenerator = new EasyRandom();

	private Card card;

	CreateCardDeckDTO createCardDeckDTO = new CreateCardDeckDTO("card deck");

	CreateCardDTO createCardDTO1 = new CreateCardDTO("name", "picture", 1, "description",
			"location", null, Ability.BERSERKER, Row.AGILE, Type.HERO);


	// --------------------------------------------------------------------------------
	@BeforeEach
	public void initRepo() {
		/*
		System.out.println("--------------------");
		System.out.println("initRepo - List of all users");
		System.out.println("--------------------");
		*/
		// random index for table length
		Random random = new Random();
		tableLength = random.nextLong(10);
		// ensure that it is not null
		if (tableLength == 0l) {
			tableLength++;
		}

		// populate repository
		for (Long sweeper = 0l; sweeper < tableLength; sweeper++) {
			// new random card
			Card tempCard = RNGenerator.nextObject(Card.class);
			tempCard.setId(null);
			
			// must be done in this way else we get a cascade exception
			// -- this is because the card cannot create a cardDeck on instantiation. it must be done separately
			// -- this could be an issue with the way the DB is set up.
			CardDeck tempCardDeck = RNGenerator.nextObject(CardDeck.class);
			tempCardDeck.setId(null);
			cardDeckRepository.save(tempCardDeck); 
			
			tempCard.setCardDeck(tempCardDeck);
			tempCard = cardRepository.save(tempCard);

			// get the first card of each instance of the repo
			// this will be used for our tests
			if (card == null) {
				card = tempCard;
			}

			/*
			Card repoReturnValue = cardRepository.findById(tempCard.getId()).orElse(null);
			System.out.println("Name: " + repoReturnValue.getName());
			System.out.println("Picture: " + repoReturnValue.getPicture());
			System.out.println("PowerLvl" + repoReturnValue.getPowerLvl());
			System.out.println("Description: " + repoReturnValue.getDescription());
			System.out.println("Location: " + repoReturnValue.getLocation());
			System.out.println("CardDeck: " + repoReturnValue.getCardDeck());
			System.out.println("Ability: " + repoReturnValue.getAbility());
			System.out.println("Row: " + repoReturnValue.getRowName());
			System.out.println("Type: " + repoReturnValue.getType());
			System.out.println("Id: " + repoReturnValue.getId());
			*/
		}
		/*
		System.out.println("Total repo size: " + cardRepository.count());
		System.out.println("glb user: " + card.getId() + " " + card.getName());
		*/
	}

	@AfterEach
	public void cleanupRepo() {
		cardRepository.deleteAll();
	}

	/*
	@AfterEach
	public void displayEnd() {
		System.out.println("----- end of task ------");
	}
	*/
	
	// --------------------------------------------------------------------------------

	public void cardEqualsAssertions(CardDTO card1, CardDTO card2) {
		assertThat(card1).isNotNull();
		assertThat(card2).isNotNull();
		assertThat(card1.getId()).isEqualTo(card2.getId());
		assertThat(card1.getName()).isEqualTo(card2.getName());
		assertThat(card1.getPicture()).isEqualTo(card2.getPicture());
		assertThat(card1.getPowerLvl()).isEqualTo(card2.getPowerLvl());
		assertThat(card1.getDescription()).isEqualTo(card2.getDescription());
		assertThat(card1.getLocation()).isEqualTo(card2.getLocation());
		assertThat(card1.getAbility()).isEqualTo(card2.getAbility());
		assertThat(card1.getRowName()).isEqualTo(card2.getRowName());
		assertThat(card1.getType()).isEqualTo(card2.getType());
	}

	public void cardAssertions(CardDTO card) {
		assertThat(this.card).isNotNull();
		assertThat(this.card.getId()).isNotNull();
		assertThat(this.card.getName()).isNotEmpty();
		assertThat(this.card.getPicture()).isNotEmpty();
		assertThat(this.card.getPowerLvl()).isNotNull();
		assertThat(this.card.getDescription()).isNotEmpty();
		assertThat(this.card.getLocation()).isNotEmpty();
		assertThat(this.card.getAbility()).isNotNull();
		assertThat(this.card.getRowName()).isNotNull();
		assertThat(this.card.getType()).isNotNull();
	}

	// --------------------------------------------------------------------------------

	@Test
	@DisplayName("Card - Find All")
	void testCardFindAll() {
		// get all users from the repository
		List<CardDTO> cards = new ArrayList<CardDTO>();
		cards.addAll(cardService.getAllCards());

		assertThat(cards).isNotEmpty();
		assertThat(cards.size()).isEqualTo(cardRepository.count());

		for (int sweeper = 0; sweeper < cardRepository.count(); sweeper++) {
			cardAssertions(cards.get(sweeper));
		}
	}

	// --------------------------------------------------------------------------------

	@Test
	@DisplayName("Card - Find by ID")
	void testCardFindById() {

		CardDTO searchCard = cardService.getCardById(card.getId());

		// assertions
		cardAssertions(searchCard);
		cardEqualsAssertions(searchCard, cardService.getCardById(card.getId()));
	}

	// --------------------------------------------------------------------------------

	@Test
	@DisplayName("Card - Creation")
	void testCardCreation() {
		long nbCards = this.cardRepository.count();
		CardDTO createdCard = cardService.createCard(createCardDTO1);

		// assertions
		cardAssertions(createdCard);
		assertThat(createdCard.getName()).isEqualTo(createCardDTO1.getName());
		assertThat(createdCard.getPicture()).isEqualTo(createCardDTO1.getPicture());
		assertThat(createdCard.getPowerLvl()).isEqualTo(createCardDTO1.getPowerLvl());
		assertThat(createdCard.getDescription()).isEqualTo(createCardDTO1.getDescription());
		assertThat(createdCard.getLocation()).isEqualTo(createCardDTO1.getLocation());
		assertThat(createdCard.getAbility()).isEqualTo(createCardDTO1.getAbility());
		assertThat(createdCard.getRowName()).isEqualTo(createCardDTO1.getRowName());
		assertThat(createdCard.getType()).isEqualTo(createCardDTO1.getType());
		assertThat(this.cardRepository.count()).isGreaterThan(nbCards);
		assertThat(this.cardRepository.getById(createdCard.getId())).isNotNull();
	}

	// --------------------------------------------------------------------------------

	@Test
	@DisplayName("Card - Update")
	void testCardEdit() {

		CardDTO beforeCard = cardService.createCard(createCardDTO1);
		Long id = beforeCard.getId();

		// modifier
		CreateCardDTO cardUpdate = new CreateCardDTO(
				"Momo", "Mama", 2, "description",
				"location", null, Ability.BERSERKER, Row.AGILE, Type.HERO);

		CardDTO afterCard = this.cardService.updateCard(id, cardUpdate);

		// assertions
		assertThat(beforeCard).isNotEqualTo(afterCard);
	}

	// --------------------------------------------------------------------------------

	@Test
	@DisplayName("Card - Delete")
	void testCardDelete() {
		// get last card in repository
		Long repoSizeBefore = cardRepository.count();
		Card searchResultBefore = cardRepository.findById(card.getId()).orElse(null);

		// method to test
		cardService.deleteCardById(card.getId());

		Long repoSizeAfter = cardRepository.count();
		Card searchResultAfter = cardRepository.findById(card.getId()).orElse(null);

		// assertions
		assertThat(searchResultBefore).isNotEqualTo(searchResultAfter);
		assertThat(repoSizeBefore).isNotEqualTo(repoSizeAfter);
	}

	// --------------------------------------------------------------------------------

	@Test
	@DisplayName("Card - Get by Deck ID")
	void testGetCardByDeck() {
		//création de cardDeck
		CardDeckDTO createCardDeck = cardDeckService.createCardDeck(createCardDeckDTO);
		Long deckId = createCardDeck.getId();
		CardDeck cardDeck = new CardDeck();
		BeanUtils.copyProperties(createCardDeck, cardDeck);

		//création de card
		CreateCardDTO createCardDTO = new CreateCardDTO("carte name", "picture", 1, "description",
				"location", cardDeck, Ability.BERSERKER, Row.AGILE, Type.HERO);
		CreateCardDTO createCardDTO1 = new CreateCardDTO("carte name 1", "picture", 1, "description",
				"location", cardDeck, Ability.BERSERKER, Row.AGILE, Type.HERO);
		CreateCardDTO createCardDTO2 = new CreateCardDTO("carte name 2", "picture", 1, "description",
				"location", cardDeck, Ability.BERSERKER, Row.AGILE, Type.HERO);

		// add cards to repo
		cardService.createCard(createCardDTO);
		cardService.createCard(createCardDTO1);
		cardService.createCard(createCardDTO2);


		// find those cards by deckId
		List<Card> cards = new ArrayList<>(cardService.findCardsByCardDeck(deckId));
		
		for (Card card : cards) {
			Assertions.assertNotNull(card.getId(), "id");
			Assertions.assertNotNull(card.getName(), "Name");

			assertThat(card).isNotNull();
			assertThat(card.getId()).isNotNull();
		}
	}
}