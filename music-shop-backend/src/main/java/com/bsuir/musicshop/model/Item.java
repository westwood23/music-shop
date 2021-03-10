package com.bsuir.musicshop.model;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@NamedNativeQuery(
        name = "getNotReservedItems",
        query = "select i.item_id, i.item_name, i.item_price from items i " +
                "left join order_items io on io.item_id = i.item_id " +
                "where i.item_id not in (select oi.item_id from order_items oi)",
        resultClass = Item.class
)
@Table(name = "items")
public class Item{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "item_id")
    private Integer itemId;

    @Column(name = "item_name")
    private String itemName;

    @Column(name = "item_price")
    private BigDecimal itemPrice;
}