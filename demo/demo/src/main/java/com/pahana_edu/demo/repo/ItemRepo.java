package com.pahana_edu.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.pahana_edu.demo.model.ItemModel;

@Repository
public interface ItemRepo extends JpaRepository<ItemModel, String> {
    ItemModel findTopByOrderByItemCodeDesc();
}