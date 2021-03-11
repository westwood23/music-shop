package com.bsuir.musicshop.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import com.bsuir.musicshop.consumer.ItemRestConsumer;
import com.bsuir.musicshop.model.Item;
import com.bsuir.musicshop.validator.ItemValidator;

@Controller
@RequestMapping("/items")
public class ItemController {

    private final ItemRestConsumer itemRestConsumer;

    private final ItemValidator validator;


    @Autowired
    public ItemController(ItemRestConsumer itemRestConsumer, ItemValidator validator) {
        this.itemRestConsumer = itemRestConsumer;
        this.validator = validator;
    }

    @GetMapping
    public final String allItems(Model model){
        System.out.println("test controller");
        model.addAttribute("assortment", itemRestConsumer.findAllItems());
        return "assortment";
    }

    @GetMapping("/new")
    public final String goToAddItemPage(Model model){
        Item item = new Item();
        model.addAttribute("isNew", true);
        model.addAttribute("item", item);
        return "item";
    }

    @PostMapping("/new")
    public final String addItem(@Valid Item item, BindingResult result, Model model){

        validator.validate(item, result);
        if(result.hasErrors()){
            model.addAttribute("isNew", true);
            return "item";
        }else{
            this.itemRestConsumer.addItem(item);
            return "redirect:/items";
        }
    }

    @GetMapping(value = "/edit/{id}")
    public final String goToEditItemPage(@PathVariable Integer id, Model model){

        Item item = itemRestConsumer.findItemById(id);
        model.addAttribute("isNew", false);
        model.addAttribute("item", item);
        return "item";
    }

    @PostMapping(value = "/edit/{id}")
    public final String updateItem(@Valid Item item, BindingResult result){

        validator.validate(item, result);

        if(result.hasErrors()){
            return "item";
        }else {
            this.itemRestConsumer.updateItem(item);
            return "redirect:/items";
        }
    }

    @GetMapping(value = "/{id}")
    public final String deleteItem(@PathVariable Integer id){
        this.itemRestConsumer.deleteItem(id);
        return "redirect:/items";
    }
}
