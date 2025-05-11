package com.deliveryapplication.Fusion.controller;
import com.deliveryapplication.Fusion.model.Items;
import com.deliveryapplication.Fusion.service.FusionItemsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;




import java.util.List;


//The controller layer will expose a REST API endpoint that returns all the items,



@RestController
public class FusionItemsController {

    private final FusionItemsService fusionItemsService;

    @Autowired
    public FusionItemsController(FusionItemsService fusionItemsService) {      // constructor injection(DI - dependency injection)
        this.fusionItemsService = fusionItemsService;
    }

    @GetMapping("/all/items")
    public ResponseEntity<List<Items>> getAllItems() {
        List<Items> items = fusionItemsService.getAllItems();
        return ResponseEntity.ok(items);  //
    }

    @GetMapping("/items/{category}")
    public List<Items> getItemsByCategory(@PathVariable String category) {
        List<Items> itemsBySubCategory = fusionItemsService.getItemsBySubCategory(category);
        return itemsBySubCategory;
    }
}
