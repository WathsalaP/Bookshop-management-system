package com.pahana_edu.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
//import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pahana_edu.demo.dto.ItemDTO;
import com.pahana_edu.demo.service.ITemService;

@RestController
// @CrossOrigin
@CrossOrigin("*")
@RequestMapping(value = "api/v1")

public class ItemController {
    @Autowired
    private ITemService itemService;

    @GetMapping("/getitems")
    public List<ItemDTO> getItems() {
        return itemService.getAllItems();
    }

    @PostMapping("/additem")
    public String saveItem(@RequestBody ItemDTO itemDTO) {
        itemService.saveItem(itemDTO);
        return "Item added successfully!";
    }

    @PatchMapping("/updateitem/{id}")
    public String updateItem(@PathVariable String id, @RequestBody ItemDTO itemDTO) {
        itemDTO.setItemCode(id); // set itemId from path variable
        itemService.updateItem(itemDTO);
        return "Item updated successfully!";
    }

    @DeleteMapping("/deleteitem/{id}")
    public String deleteItem(@PathVariable String id) {
        itemService.deleteItem(id);
        return "Item deleted successfully!";
    }

}
