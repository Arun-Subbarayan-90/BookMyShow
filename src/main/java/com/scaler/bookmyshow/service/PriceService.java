package com.scaler.bookmyshow.service;

import com.scaler.bookmyshow.models.Show;
import com.scaler.bookmyshow.models.ShowSeat;
import com.scaler.bookmyshow.models.ShowSeatType;
import com.scaler.bookmyshow.repository.ShowSeatTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PriceService {
    @Autowired
    private ShowSeatTypeRepository showSeatTypeRepository;
    public int calculatePrice(List<ShowSeat> showSeats, Show show){
        List<ShowSeatType> showSeatTypes = showSeatTypeRepository.findAllByShow(show);
        int amount = 0;
        for(ShowSeat showSeat: showSeats){
            for(ShowSeatType showSeatType: showSeatTypes){
                if(showSeat.getSeat().getSeatType().equals(showSeatType.getSeatType())){
                    amount+= showSeatType.getPrice();
                }
            }
        }
        return amount;
    }
}
