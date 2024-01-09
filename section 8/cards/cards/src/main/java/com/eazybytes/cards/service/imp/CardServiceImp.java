package com.eazybytes.cards.service.imp;

import com.eazybytes.cards.constants.CardsConstants;
import com.eazybytes.cards.dto.CardsDto;
import com.eazybytes.cards.entity.Cards;
import com.eazybytes.cards.exception.CardAlreadyExistsException;
import com.eazybytes.cards.exception.ResourceNotFoundException;
import com.eazybytes.cards.mappers.CardsMapper;
import com.eazybytes.cards.repository.CardsRepository;
import com.eazybytes.cards.service.ICardsService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

@Service
@AllArgsConstructor
public class CardServiceImp implements ICardsService {

    private CardsRepository cardsRepository ;
    /**
     * @param mobileNumber - Mobile Number of the Customer
     */
    @Override
    public void createCard(String mobileNumber) {
        Optional<Cards> byMobileNumber = cardsRepository.findByMobileNumber(mobileNumber);
        if (byMobileNumber.isPresent()){
            throw new CardAlreadyExistsException("Card already registered with given mobileNumber "+mobileNumber);
        }
        cardsRepository.save(createNewCard(mobileNumber));
    }

    private Cards createNewCard(String mobileNumber) {
        long randomCardNumber = 100000000000L + new Random().nextInt(900000000);
        Cards card =Cards.builder()
                .cardType(CardsConstants.CREDIT_CARD)
                .cardNumber(Long.toString(randomCardNumber))
                .mobileNumber(mobileNumber)
                .amountUsed(0)
                .availableAmount(CardsConstants.NEW_CARD_LIMIT)
                .totalLimit(CardsConstants.NEW_CARD_LIMIT)
                .build();
        return card ;
    }

    /**
     * @param mobileNumber - Input mobile Number
     * @return Card detail based on a given mobileNumber
     */
    @Override
    public CardsDto fetchCard(String mobileNumber) {
        Cards cards = cardsRepository.findByMobileNumber(mobileNumber).orElseThrow(
                ()->new ResourceNotFoundException("Card", "mobileNumber", mobileNumber)
        );
        return CardsMapper.mapToCardsDto(cards,new CardsDto());
    }

    /**
     * @param cardsDto - CardDto object
     * @return boolean indicating if the update is successful
     */
    @Override
    public boolean updateCard(CardsDto cardsDto) {
        Cards cards = cardsRepository.findByCardNumber(cardsDto.getCardNumber()).orElseThrow(
                () -> new ResourceNotFoundException("Card", "CardNumber", cardsDto.getCardNumber()));
        CardsMapper.mapToCards(cardsDto,cards);
        cardsRepository.save(cards);
        return true ;

    }

    /**
     * @param mobileNumber - input mobileNumber
     * @return boolean indicating if delete is successful
     */
    @Override
    public boolean deleteCard(String mobileNumber) {
        Cards cards = cardsRepository.findByMobileNumber(mobileNumber).orElseThrow(
                () -> new ResourceNotFoundException("Card", "mobileNumber", mobileNumber)
        );
        cardsRepository.deleteById(cards.getCardId());
        return true ;
    }
}
