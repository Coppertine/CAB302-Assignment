package main.com.cab302qut.java.Organisation;

import main.com.cab302qut.java.Items.Asset;

public class OrganisationAsset extends Asset {
    private int quantity;
    private Organisation assetOwner;

    public OrganisationAsset(String assetName, Organisation assetOwner) {
        super(assetName);
        this.assetOwner = assetOwner;
        this.quantity = 0;
    }

    public OrganisationAsset(String assetName, Organisation assetOwner, Integer quantity) {
        super(assetName);
        this.assetOwner = assetOwner;
        this.quantity = quantity;
    }

    public int getQuantity() {
        return quantity;
    }
}
