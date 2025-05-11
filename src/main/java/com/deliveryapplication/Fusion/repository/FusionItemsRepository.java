package com.deliveryapplication.Fusion.repository;

import com.deliveryapplication.Fusion.model.Items;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

//This layer handles all the interactions with the database and the controller
@Repository
public interface FusionItemsRepository  extends JpaRepository<Items,Long> {

    @Query(value="Select * from items Where sub_category = :subCategory",nativeQuery = true)
    List<Items> findItemsBySubCategory(@Param("subCategory") String subcategory);
}
