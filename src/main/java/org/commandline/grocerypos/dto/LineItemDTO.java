package org.commandline.grocerypos.dto;

import lombok.Data;
import lombok.NonNull;
import org.commandline.grocerypos.model.Item;
import org.commandline.grocerypos.model.ItemPrice;

@Data
public class LineItemDTO {

    private final String displayName;
    private final String description;
    private final Integer price;
    private Integer quantity = 1;

    public LineItemDTO(@NonNull Item item, @NonNull ItemPrice itemPrice) {
        this.displayName = item.getDisplayname();
        this.description = item.getDescription();
        this.price = itemPrice.getPrice();
    }

    public LineItemDTO(@NonNull Item item, @NonNull ItemPrice itemPrice, @NonNull Integer quantity) {
        this(item, itemPrice);
        this.quantity = quantity;
    }
}
