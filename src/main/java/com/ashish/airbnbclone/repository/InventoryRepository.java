package com.ashish.airbnbclone.repository;

import com.ashish.airbnbclone.entity.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InventoryRepository extends JpaRepository<Inventory,Long> {
}
