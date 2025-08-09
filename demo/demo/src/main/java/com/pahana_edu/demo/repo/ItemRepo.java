package com.pahana_edu.demo.repo;
/*
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.pahana_edu.demo.model.ItemModel;


@Repository
public interface ItemRepo extends JpaRepository<ItemModel, String> {
    // Additional query methods can be defined here if needed

}



// In ItemRepo.java (new method)

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepo extends JpaRepository<ItemModel, String> {

    // Find max itemCode (assuming lex order, else substring to get number)
    @Query(value = "SELECT i FROM ItemModel i ORDER BY i.itemCode DESC")
    List<ItemModel> findTopByOrderByItemCodeDesc();
} */

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.pahana_edu.demo.model.ItemModel;

@Repository
public interface ItemRepo extends JpaRepository<ItemModel, String> {
    ItemModel findTopByOrderByItemCodeDesc(); // ✅ returns single latest record
}