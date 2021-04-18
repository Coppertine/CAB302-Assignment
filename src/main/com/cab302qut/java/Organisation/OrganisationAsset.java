package com.cab302qut.java.Organisation;

import com.cab302qut.java.Items.Asset;

public class OrganisationAsset extends Asset {
    private int quantity;

    public OrganisationAsset(String assetName,Integer id,Integer price) {
        super(assetName,id,price);
    }

    public OrganisationAsset() {

    }
}
