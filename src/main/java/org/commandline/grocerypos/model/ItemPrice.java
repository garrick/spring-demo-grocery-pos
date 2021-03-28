package org.commandline.grocerypos.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class ItemPrice {
    private Long id;
    private Long item_id;
    private Integer price;
    private LocalDateTime start_date = LocalDateTime.now();
    private LocalDateTime end_date = LocalDateTime.now().plusYears(100);

    public ItemPrice(Long itemId, int price) {
        this.item_id = itemId;
        this.price = price;
    }
}
