package us.lidaka.jasa.Model;

import android.os.AsyncTask;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.InvalidParameterException;

import us.lidaka.jasa.AssetType;

/**
 * Created by augustus on 10/16/15.
 */
public class SwapiAssetRequest extends AsyncTask<Void, Void, SwapiAsset> {
    private TypeToken typeToken;
    private String url;
    private SwapiResponseListener listener;

    public SwapiAssetRequest(AssetType assetType, int id, SwapiResponseListener listener) {
        this.listener = listener;

        switch (assetType) {
            case FILM:
                this.typeToken = new TypeToken<Film>(){};
                this.url = AssetType.getUrl(AssetType.FILM, id);
                break;
            case PERSON:
                this.typeToken = new TypeToken<Person>(){};
                this.url = AssetType.getUrl(AssetType.PERSON, id);
                break;
            case PLANET:
                this.typeToken = new TypeToken<Planet>(){};
                this.url = AssetType.getUrl(AssetType.PLANET, id);
                break;
            case SPECIES:
                this.typeToken = new TypeToken<Species>(){};
                this.url = AssetType.getUrl(AssetType.SPECIES, id);
                break;
            case STARSHIP:
                this.typeToken = new TypeToken<Starship>(){};
                this.url = AssetType.getUrl(AssetType.STARSHIP, id);
                break;
            case VEHICLE:
                this.typeToken = new TypeToken<Vehicle>(){};
                this.url = AssetType.getUrl(AssetType.VEHICLE, id);
                break;
            default:
                throw new InvalidParameterException("invalid SWAPI asset type");
        }
    }

    public SwapiAssetRequest(AssetType assetType, String url, SwapiResponseListener listener) {
        this(assetType, -1, listener);
        this.url = url;
    }

    @Override
    protected void onPostExecute(SwapiAsset result) {
        super.onPostExecute(result);
        this.listener.onAssetResponseReceived(result);
    }

    @Override
    protected SwapiAsset doInBackground(Void... params) {
        SwapiAsset result = null;

        // TODO: implement a caching layer and check it here

        // We don't have the data cached, so we'll have to fetch it
        this.listener.onNetworkRequestInitiated();

        try {
            URL url = new URL(this.url);
            HttpURLConnection connection = (HttpURLConnection)url.openConnection();
            connection.connect();

            int code = connection.getResponseCode();

            if (code == 200 || code == 0) {
                result = parseResponse(connection);
            }
        }
        catch (IOException e) {
        }

        return result;
    }

    private SwapiAsset parseResponse(HttpURLConnection connection) throws IOException {
        SwapiAsset result = null;
        InputStream stream = null;
        Reader reader = null;

        try {
            stream = connection.getInputStream();
            reader = new InputStreamReader(stream);

            Gson gson = new Gson();
            result = gson.fromJson(reader, (this.typeToken).getType());
        } finally {
            if (stream != null) {
                stream.close();
            }

            if (reader != null) {
                reader.close();
            }
        }

        return result;
    }
}
