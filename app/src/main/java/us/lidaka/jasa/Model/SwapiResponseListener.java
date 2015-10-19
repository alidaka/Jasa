package us.lidaka.jasa.Model;

/**
 * Created by augustus on 10/15/15.
 */
public interface SwapiResponseListener {
    public void onNetworkRequestInitiated();
    public void onListResponseReceived(SwapiListPage swapiListPage);
    public void onAssetResponseReceived(SwapiAsset swapiAsset);
}
