package com.bsuir.musicshop.validator;

import com.bsuir.musicshop.model.Item;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.validation.*;

@Component
public class ItemValidator implements Validator {
    private static final int ITEM_NAME_MAX_SIZE = 255;

    @Override
    public boolean supports(Class<?> aClass) {
        return Item.class.equals(aClass);
    }

    @Override
    public void validate(Object target, Errors errors) {
        ValidationUtils.rejectIfEmpty(errors, "itemName", "itemName.empty");
        ValidationUtils.rejectIfEmpty(errors, "itemPrice", "itemPrice.empty");
        Item item = (Item)target;
        if(StringUtils.hasLength(item.getItemName()) && item.getItemName().length()>ITEM_NAME_MAX_SIZE){
            errors.rejectValue("itemName", "itemName.maxSize255");
        }

        if(item.getItemPrice()!=null && item.getItemPrice().floatValue()<0){
            errors.rejectValue("itemPrice", "itemPrice.negative");
        }
    }
}