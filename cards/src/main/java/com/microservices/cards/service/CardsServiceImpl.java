package com.microservices.cards.service;

import com.microservices.cards.constants.CardsConstants;
import com.microservices.cards.dto.CardsDTO;
import com.microservices.cards.entity.Cards;
import com.microservices.cards.exception.CardAlreadyExistsException;
import com.microservices.cards.exception.ResourceNotFoundException;
import com.microservices.cards.mapper.CardsMapper;
import com.microservices.cards.repository.CardsRepository;
import com.microservices.cards.service.CardsService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;
import java.util.Random;

@Service
@AllArgsConstructor
public class CardsServiceImpl implements CardsService {

    private CardsRepository cardsRepository;

    //private CardsDTO cardsDTO;

    @Override
    public String createCard(String mobileNumber, String cardType) {
        Optional<Cards> optionalCards= cardsRepository.findByMobileNumber(mobileNumber);
        if(optionalCards.isPresent()){
            throw new CardAlreadyExistsException("Card already registered with given mobileNumber "+mobileNumber);
        }
        cardsRepository.save(createNewCard(mobileNumber,cardType));
        return cardType + "card created successfully for the mobile number " +mobileNumber;
    }


    private Cards createNewCard(String mobileNumber, String cardType) {
        Cards newCard = new Cards();
        long randomCardNumber = 100000000000L + new Random().nextInt(900000000);
        newCard.setCardNumber(Long.toString(randomCardNumber));
        newCard.setMobileNumber(mobileNumber);
        if(Objects.equals(cardType, "Debit")) {
            newCard.setCardType(CardsConstants.DEBIT_CARD);
        }else newCard.setCardType((CardsConstants.CREDIT_CARD));

        newCard.setTotalLimit(CardsConstants.NEW_CARD_LIMIT);
        newCard.setAmountUsed(0);
        newCard.setBalanceAmount(CardsConstants.NEW_CARD_LIMIT);
        return newCard;
    }


    @Override
    public CardsDTO fetchCard(String mobileNumber) {
        Cards cards = cardsRepository.findByMobileNumber(mobileNumber).orElseThrow(
                () -> new ResourceNotFoundException("Card", "mobileNumber", mobileNumber)
        );
        cards.setBalanceAmount(cards.getTotalLimit() - cards.getAmountUsed());
        return CardsMapper.mapToCardsDTO(cards, new CardsDTO());
    }


    @Override
    public boolean updateCard(CardsDTO cardsDto) {
        Cards cards = cardsRepository.findByCardNumber(cardsDto.getCardNumber()).orElseThrow(
                () -> new ResourceNotFoundException("Card", "CardNumber", cardsDto.getCardNumber()));
        CardsMapper.mapToCards(cardsDto, cards);
        cardsRepository.save(cards);
        return true;
    }
    
    @Override
    public String deleteCard(String mobileNumber) {
        Cards cards = cardsRepository.findByMobileNumber(mobileNumber).orElseThrow(
                () -> new ResourceNotFoundException("Card", "mobileNumber", mobileNumber)
        );
        cardsRepository.deleteById(cards.getCardId());
        return "Card  "+ cards.getCardNumber() + "deleted successfully";
    }


}
