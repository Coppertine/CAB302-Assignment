
import com.cab302qut.java.Items.Asset;
import com.cab302qut.java.Trades.Trade;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class TestAsset {

    Asset testAsset;
    // Assuming an organisation would have a collection
    // of Asset objects, this collection will mock that behaviour for tests.
    ArrayList<Asset> arrayTestAssets;

    public static void main (String[] args){}

    @BeforeEach
    // Create new Asset
    public void createNewAsset() {
        testAsset = new Asset("Widgets",0,10);
        arrayTestAssets = new ArrayList<>() {{
            add(testAsset);
            add(new Asset("CPU Hours",1,35));
            add(new Asset("GPU Hours",2,45));
        }};
    }


    // Get list of assets
    public void getListAssets() {
        ArrayList<Asset> assetList = arrayTestAssets;
    }

    @Test
    // Change name of asset
    public void changeAssetName(){
        testAsset.setAssetName("Widget");
        assertEquals("Widget",testAsset.getAssetName());
    }

    @Test
    // Get specific asset by name
    public void getSpecificAssetByName() {
        String findAsset = "CPU Hours";
        Asset theAssetFound = null;
        boolean assetFound = false;
        for (Integer i = 0; assetFound == false && i < arrayTestAssets.size(); i++) {
            if (arrayTestAssets.get(i).getAssetName().equals(findAsset)) {
                theAssetFound = arrayTestAssets.get(i);
                assetFound = true;
            }
        }
        assertEquals(arrayTestAssets.get(1),theAssetFound);
    }
    // Get asset name
    public void getAssetName() {
        String theAssetName = testAsset.getAssetName();
    }
    // Get Id of newly created asset
    public void getAssetId() {
        Integer theAssetId = testAsset.getAssetId();
    }
    // Delete asset (after asset is created)
    public void deleteAsset() {
        arrayTestAssets.remove(2);
    }

    // remove asset by id
    public void removeAssetById() {
        Integer assetId = 2;
        boolean assetFound = false;
        for (Integer i = 0; assetFound == false && i < arrayTestAssets.size(); i++) {
            if (arrayTestAssets.get(i).getAssetName().equals(assetId)) {
                arrayTestAssets.remove(i);
                assetFound = true;
            }
        }
    }

    // get asset from id
    public void getAssetById(){
        Integer findAsset = 2;
        Asset theAssetFound = null;
        boolean assetFound = false;
            for (Integer i = 0; assetFound == false && i < arrayTestAssets.size(); i++) {
            if (arrayTestAssets.get(i).getAssetId().equals(findAsset)) {
                theAssetFound = arrayTestAssets.get(i);
                assetFound = true;
            }
        }
    }

    // Get asset's trade history.
    public void getAssetTradeHistory() {
        ArrayList<Trade> theTradeHistory = testAsset.getTradeHistory();
    }
    // Get most recent trade history's price.
    public void getRecentTradeHistoryPrice(){
        // Currently don't have access to Trade class implementation
        // but process would be
        //  Double recentTradePrice = <assetName>.getTradeHistory().get(0).getPrice()
        //

    }
}
