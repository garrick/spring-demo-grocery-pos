package org.commandline.grocerypos.model;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ItemPrice {
    private Long id;
    private Long item_id;
    private Integer price;
    private LocalDateTime start_date;
    private LocalDateTime end_date;
}
