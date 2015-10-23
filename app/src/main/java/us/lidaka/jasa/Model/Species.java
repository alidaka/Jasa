package us.lidaka.jasa.Model;

import java.io.Serializable;
import java.util.ArrayList;

import com.google.gson.annotations.SerializedName;

import us.lidaka.jasa.AssetType;

/**
 * Created by augustus on 10/18/15.
 */
public class Species extends SwapiAsset implements Serializable {
    public String classification;

    public String designation;

    @SerializedName("average_height")
    public String averageHeight;

    @SerializedName("skin_colors")
    public String skinColors;

    @SerializedName("hair_colors")
    public String hairColors;

    @SerializedName("eye_colors")
    public String eyeColors;

    @SerializedName("average_lifespan")
    public String averageLifespan;

    @SerializedName("homeworld")
    public String homeworldUrl;

    public String language;

    @SerializedName("people")
    public ArrayList<String> peopleUrls;

    @SerializedName("films")
    public ArrayList<String> filmUrls;

    public Species() {
        super(AssetType.SPECIES);
    }
}
