package com.DataBaseProject.PCenter.service.order;

import com.DataBaseProject.PCenter.data.OrderProduct;
import com.DataBaseProject.PCenter.repository.OrderProductRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
@Transactional //jeśli wystąpi błąd to będzie rollback (anulowanie wprowadzonych zmian)
public class OrderProductServiceImpl implements OrderProductService {
    private OrderProductRepository orderProductRepository;
    @Override
    public OrderProduct create(OrderProduct orderProduct){
        return this.orderProductRepository.save(orderProduct);
    }
}
