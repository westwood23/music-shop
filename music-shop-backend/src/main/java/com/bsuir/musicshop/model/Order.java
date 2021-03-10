package com.bsuir.musicshop.model;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer orderId;

    @Column(name = "order_date")
    private LocalDate orderDate;

    @OneToMany()
    @JoinColumn(name = "item")
    private List<Item> itemsList;

    @Transient
    private List<String> itemsIds;

    @Column(name = "order_cost")
    private BigDecimal orderCost;
}