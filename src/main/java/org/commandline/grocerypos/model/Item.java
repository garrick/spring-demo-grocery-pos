package org.commandline.grocerypos.model;

import lombok.*;

@Data
@NoArgsConstructor
public class Item {
    private Long id;
    private String displayname;
    private String description;

    public Item(String displayname, String description) {
        this.displayname = displayname;
        this.description = description;
    }

}
