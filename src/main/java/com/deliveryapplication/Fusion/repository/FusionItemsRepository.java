package com.deliveryapplication.Fusion.repository;

import com.deliveryapplication.Fusion.model.Items;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//This layer handles all the interactions with the database and the controller
@Repository
public interface FusionItemsRepository  extends JpaRepository<Items,Long> {
}
