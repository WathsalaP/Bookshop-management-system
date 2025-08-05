package com.pahana_edu.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.pahana_edu.demo.model.CustomerModel;

@Repository
public interface CustomerRepo extends JpaRepository<CustomerModel, String> {
    // Additional query methods can be defined here if needed

}
