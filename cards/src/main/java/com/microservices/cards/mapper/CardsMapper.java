package com.microservices.cards.mapper;

import com.microservices.cards.entity.Cards;
import com.microservices.cards.dto.CardsDTO;

public class CardsMapper {

    public static CardsDTO mapToCardsDTO(Cards cards, CardsDTO cardsDTO) {
        cardsDTO.setCardNumber(cards.getCardNumber());
        cardsDTO.setCardType(cards.getCardType());
        cardsDTO.setMobileNumber(cards.getMobileNumber());
        cardsDTO.setTotalLimit(cards.getTotalLimit());
        cardsDTO.setBalanceAmount(cards.getBalanceAmount());
        cardsDTO.setAmountUsed(cards.getAmountUsed());
        return cardsDTO;
    }

    public static Cards mapToCards(CardsDTO cardsDTO, Cards cards) {
        cards.setCardNumber(cardsDTO.getCardNumber());
        cards.setCardType(cardsDTO.getCardType());
        cards.setMobileNumber(cardsDTO.getMobileNumber());
        cards.setTotalLimit(cardsDTO.getTotalLimit());
        cards.setBalanceAmount(cardsDTO.getBalanceAmount());
        cards.setAmountUsed(cardsDTO.getAmountUsed());
        return cards;
    }

}
