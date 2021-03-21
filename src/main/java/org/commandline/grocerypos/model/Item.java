package org.commandline.grocerypos.model;

import lombok.Data;

@Data
public class Item {
    private Long id;
    private String displayname;
    private String description;
}
