package us.lidaka.jasa.Model;

import com.google.gson.annotations.SerializedName;

import us.lidaka.jasa.AssetType;

/**
 * Created by augustus on 10/15/15.
 */
public abstract class SwapiAsset {
    public String name;

    public AssetType assetType;

    public String url;

    private int id = -1;

    public SwapiAsset(AssetType assetType) {
        this.assetType = assetType;
    }

    public int getId() {
        if (this.id == -1) {
            this.id = SwapiAsset.extractIdFromUrl(this.url);
        }

        return this.id;
    }

    private static int extractIdFromUrl(String url) {
        int id = -1;

        String[] tokens = url.split("/");
        if (tokens.length > 0) {
            String lastToken = tokens[tokens.length - 1];
            id = Integer.parseInt(lastToken);
        }

        return id;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
