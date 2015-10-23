package us.lidaka.jasa.Model;

import java.io.Serializable;
import java.util.ArrayList;

import com.google.gson.annotations.SerializedName;

import us.lidaka.jasa.AssetType;

/**
 * Created by augustus on 10/18/15.
 */
public class Person extends SwapiAsset implements Serializable {
    public String height;

    public String mass;

    @SerializedName("hair_color")
    public String hairColor;

    @SerializedName("skin_color")
    public String skinColor;

    @SerializedName("eye_color")
    public String eyeColor;

    @SerializedName("birth_year")
    public String birthYear;

    public String gender;

    @SerializedName("homeworld")
    public String homeworldUrl;

    @SerializedName("films")
    public ArrayList<String> filmUrls;

    @SerializedName("species")
    public ArrayList<String> speciesUrls;

    @SerializedName("vehicles")
    public ArrayList<String> vehicleUrls;

    @SerializedName("starships")
    public ArrayList<String> starshipUrls;

    public Person() {
        super(AssetType.PERSON);
    }
}
