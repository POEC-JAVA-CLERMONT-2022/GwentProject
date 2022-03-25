package com.example.gwent_projet.services;

import com.example.gwent_projet.models.*;
import com.example.gwent_projet.repository.CardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CardService {

    //// Emeric LiveCoding
    @Autowired
    private CardRepository cardRepository;

    public List<Card> findAll() {
      return cardRepository.findAll();
    }

    public Card findById(Long id) {
        return cardRepository.getById(id);
    }

}
