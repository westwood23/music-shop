package com.bsuir.musicshop.validator;

import com.bsuir.musicshop.model.Order;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class OrderValidator implements Validator {

    @Override
    public boolean supports(Class<?> aClass) {
        return Order.class.equals(aClass);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Order order = (Order)target;
        if(order.getItemsIds().isEmpty()){
            errors.rejectValue("itemsIds", "itemsIds.empty");
        }
    }
}