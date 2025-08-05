package com.pahana_edu.demo.service;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pahana_edu.demo.dto.ItemDTO;
import com.pahana_edu.demo.model.ItemModel;
import com.pahana_edu.demo.repo.ItemRepo;

@Service
@Transactional
public class ITemService {
    @Autowired
    private ItemRepo itemRepo;

    @Autowired
    private ModelMapper modelMapper;

    public List<ItemDTO> getAllItems() {
        List<ItemModel> itemsList = itemRepo.findAll();
        return modelMapper.map(itemsList, new TypeToken<List<ItemDTO>>() {
        }.getType());
    }

    public ItemDTO saveItem(ItemDTO itemDTO) {
        itemRepo.save(modelMapper.map(itemDTO, ItemModel.class));
        return itemDTO;
    }

    public ItemDTO updateItem(ItemDTO itemDTO) {
        Optional<ItemModel> optionalItem = itemRepo.findById(itemDTO.getItemCode());

        if (optionalItem.isPresent()) {
            ItemModel existingItem = optionalItem.get();

            existingItem.setItemName(itemDTO.getItemName());
            existingItem.setPrice(itemDTO.getPrice());
            existingItem.setStock(itemDTO.getStock());

            itemRepo.save(existingItem);
            return itemDTO;
        } else {
            throw new RuntimeException("Item not found with ID: " + itemDTO.getItemCode());
        }
    }

    public void deleteItem(String itemCodegetItemCode) {
        Optional<ItemModel> optionalItem = itemRepo.findById(itemCodegetItemCode);
        if (optionalItem.isPresent()) {
            itemRepo.delete(optionalItem.get());
        } else {
            throw new RuntimeException("Item not found with ID: " + itemCodegetItemCode);
        }
    }

}
