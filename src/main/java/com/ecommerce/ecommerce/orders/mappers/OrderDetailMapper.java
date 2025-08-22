package com.ecommerce.ecommerce.orders.mappers;

import com.ecommerce.ecommerce.orders.dto.OrderDetailRequestDTO;
import com.ecommerce.ecommerce.orders.model.OrderDetail;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OrderDetailMapper {

    OrderDetail toEntity(OrderDetailRequestDTO orderDetailRequestDTO);

}
