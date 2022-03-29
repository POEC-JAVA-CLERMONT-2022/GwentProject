package com.example.gwent_projet.services;

import com.example.gwent_projet.entity.*;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public interface CardService {

    List<Card> getAllCards();

    Card saveCard(Card card);

    Card getCardById(Long id);

    Card updateCard(Card card);

    void deleteCardById(Long id);

}

