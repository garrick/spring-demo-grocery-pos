package org.commandline.grocerypos.dto;

public class TestDataLineItemDTOBuilder {

    public static Long SLURM_ITEM_ID = 1L;
    public static String SLURM_DISPLAY_NAME = "Slurm";
    public static String SLURM_DESCRIPTION = "It's Highly Addictive!";
    public static Integer SLURM_PRICE = 150;
    public static Long BUZZ_COLA_ITEM_ID = 2L;
    public static String BUZZ_COLA_DISPLAY_NAME = "Buzz Cola";
    public static String BUZZ_COLA_DESCRIPTION = "Twice the sugar, twice the caffeine";
    public static Integer BUZZ_COLA_PRICE = 175;
    public static Integer QUANTITY_ONE = 1;

    public LineItemDTO buildSlurm() {
        return buildLineItemDtoWith(SLURM_ITEM_ID, SLURM_DISPLAY_NAME, SLURM_DESCRIPTION, SLURM_PRICE, QUANTITY_ONE);
    }

    public LineItemDTO buildBuzzCola() {
        return buildLineItemDtoWith(BUZZ_COLA_ITEM_ID, BUZZ_COLA_DISPLAY_NAME, BUZZ_COLA_DESCRIPTION, BUZZ_COLA_PRICE, QUANTITY_ONE);
    }

    private LineItemDTO buildLineItemDtoWith(Long id, String displayName, String description, int price, int quantity) {
        LineItemDTO lineItemDTO = new LineItemDTO();
        lineItemDTO.setId(id);
        lineItemDTO.setDisplayName(displayName);
        lineItemDTO.setDescription(description);
        lineItemDTO.setPrice(price);
        lineItemDTO.setQuantity(quantity);
        return lineItemDTO;
    }
}
