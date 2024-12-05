package com.deliveryapplication.Fusion.service;

import com.deliveryapplication.Fusion.model.Items;
import com.deliveryapplication.Fusion.repository.FusionItemsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

//This service layer contains the business logic , This interacts with "FusionItemsRepository"

@Service
public class FusionItemsService {
    @Autowired
    private final FusionItemsRepository fusionItemsRepository;

    public FusionItemsService(FusionItemsRepository fusionItemsRepository) {
        this.fusionItemsRepository = fusionItemsRepository;
    }

    public List<Items> getAllItems(){
        List<Items> itemsList= fusionItemsRepository.findAll();
        for(Items item:itemsList){
            System.out.println(item);
        }

        return itemsList;
    }

}
