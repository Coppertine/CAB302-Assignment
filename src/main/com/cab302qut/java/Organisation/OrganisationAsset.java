package com.cab302qut.java.Organisation;

import com.cab302qut.java.Items.Asset;

/**
 * An asset that is currently owned/controlled by an organisation
 * @author Brodie
 */
public class OrganisationAsset extends Asset {
    private int quantity;

    /**
     * Creates a new organisation asset with default quantity 0
     * @param assetName the name of this asset
     */
    public OrganisationAsset(String assetName) {
        super(assetName);
        this.quantity = 0;
    }

    /**
     * Creates a new organisation asset with specified quantity
     * @param assetName the name of this asset
     * @param quantity the quantity that the organisation owns of this asset
     */
    public OrganisationAsset(String assetName, int quantity) {
        super(assetName);
        this.quantity = quantity;
    }

    public OrganisationAsset(Asset asset) {
        super(asset.getAssetName());
        this.quantity = 0;
    }

    public OrganisationAsset(Asset asset, int quantity) {
        super(asset.getAssetName());
        this.quantity = quantity;
    }

    /**
     * Gets the quantity of this asset
     * @return quantity of this asset
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * Sets a new quantity of this asset
     * @param newQuantity the new quantity of this asset
     */
    public void setQuantity(int newQuantity) {
        quantity = newQuantity;
    }
}
