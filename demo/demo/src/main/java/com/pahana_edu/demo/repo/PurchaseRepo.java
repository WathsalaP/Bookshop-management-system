package com.pahana_edu.demo.repo;

import com.pahana_edu.demo.model.PurchaseModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PurchaseRepo extends JpaRepository<PurchaseModel, Long> {

    List<PurchaseModel> findByPurchaseCode(String purchaseCode);

    @Query("SELECT MAX(p.purchaseCode) FROM PurchaseModel p")
    String findMaxPurchaseCode();
}
