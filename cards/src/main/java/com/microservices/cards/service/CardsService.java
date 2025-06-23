package com.microservices.cards.service;

import com.microservices.cards.dto.CardsDTO;

public interface CardsService {

    String createCard(String mobileNumber, String cardType);

    CardsDTO fetchCard(String mobileNumber);

    boolean updateCard(CardsDTO cardsDTO);

    String deleteCard(String mobileNumber);

}
