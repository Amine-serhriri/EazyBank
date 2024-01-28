package com.eazybytes.cards.service;

import com.eazybytes.cards.dto.CardsDto;

public interface ICardsService {
    /**
     *
     * @param mobileNumber - Mobile Number of the Customer
     */
    void createCard(String mobileNumber);

    /**
     *
     * @param mobileNumber - Input mobile Number
     * @return Card detail based on a given mobileNumber
     */
    CardsDto fetchCard(String mobileNumber);

    /**
     *
     * @param cardsDto - CardDto object
     * @return boolean indicating if the update is successful
     */
    boolean updateCard(CardsDto cardsDto);

    /**
     *
     * @param mobileNumber - input mobileNumber
     * @return boolean indicating if delete is successful
     */
    boolean deleteCard(String mobileNumber);

}
