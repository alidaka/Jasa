package us.lidaka.jasa.Model;

import java.io.Serializable;
import java.util.ArrayList;

import com.google.gson.annotations.SerializedName;

import us.lidaka.jasa.AssetType;

/**
 * Created by augustus on 10/15/15.
 */
public class Film extends SwapiAsset implements Serializable {
    public String title;

    @SerializedName("episode_id")
    public int episodeId;

    @SerializedName("opening_crawl")
    public String openingCrawl;

    public String director;

    public String producer;

    @SerializedName("release_date")
    public String releaseDate;

    @SerializedName("characters")
    public ArrayList<String> characterUrls;

    @SerializedName("planets")
    public ArrayList<String> planetUrls;

    @SerializedName("starships")
    public ArrayList<String> starshipUrls;

    @SerializedName("vehicles")
    public ArrayList<String> vehicleUrls;

    @SerializedName("species")
    public ArrayList<String> speciesUrls;

    @Override
    public String toString() {
        return this.title;
    }

    public Film() {
        super(AssetType.FILM);
    }
}
