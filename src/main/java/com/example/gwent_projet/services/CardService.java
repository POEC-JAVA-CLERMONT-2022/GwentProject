package com.example.gwent_projet.services;

import com.example.gwent_projet.entity.*;
import com.example.gwent_projet.services.dto.CardDTO;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public interface CardService {

    List<Card> getAllCards();

    CardDTO saveCard(Card createCard);
    //Card saveCard(Card card);

    CardDTO getCardById(Long id);

    //CardDTO updateCard(Long id, Card editCard);
     Card updateCard(Card card);

    void deleteCardById(Long id);

}

