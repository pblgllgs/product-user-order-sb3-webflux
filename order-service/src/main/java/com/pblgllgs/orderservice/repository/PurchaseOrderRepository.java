package com.pblgllgs.orderservice.repository;
/*
 *
 * @author pblgl
 * Created on 13-12-2023
 *
 */

import com.pblgllgs.orderservice.entity.PurchaseOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PurchaseOrderRepository extends JpaRepository<PurchaseOrder, Integer> {
    List<PurchaseOrder> findByUserId(int userId);
}
