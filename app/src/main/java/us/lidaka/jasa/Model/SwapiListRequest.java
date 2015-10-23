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
public class SwapiListRequest extends AsyncTask<Void, Void, SwapiListPage> {
    private TypeToken typeToken;
    private String url;
    private SwapiResponseListener listener;

    public SwapiListRequest(AssetType assetType, SwapiResponseListener listener) {
        this.listener = listener;

        switch (assetType) {
            case FILM:
                this.typeToken = new TypeToken<SwapiListPage<Film>>(){};
                this.url = AssetType.getBaseUrl(AssetType.FILM);
                break;
            case PERSON:
                this.typeToken = new TypeToken<SwapiListPage<Person>>(){};
                this.url = AssetType.getBaseUrl(AssetType.PERSON);
                break;
            case PLANET:
                this.typeToken = new TypeToken<SwapiListPage<Planet>>(){};
                this.url = AssetType.getBaseUrl(AssetType.PLANET);
                break;
            case SPECIES:
                this.typeToken = new TypeToken<SwapiListPage<Species>>(){};
                this.url = AssetType.getBaseUrl(AssetType.SPECIES);
                break;
            case STARSHIP:
                this.typeToken = new TypeToken<SwapiListPage<Starship>>(){};
                this.url = AssetType.getBaseUrl(AssetType.STARSHIP);
                break;
            case VEHICLE:
                this.typeToken = new TypeToken<SwapiListPage<Vehicle>>(){};
                this.url = AssetType.getBaseUrl(AssetType.VEHICLE);
                break;
            default:
                throw new InvalidParameterException("invalid SWAPI asset type");
        }
    }

    public SwapiListRequest(AssetType assetType, String pageUrl, SwapiResponseListener listener) {
        this(assetType, listener);
        this.url = pageUrl;
    }

    @Override
    protected void onPostExecute(SwapiListPage result) {
        super.onPostExecute(result);
        this.listener.onListResponseReceived(result);
    }

    @Override
    protected SwapiListPage doInBackground(Void... params) {
        SwapiListPage result = null;

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

    private SwapiListPage parseResponse(HttpURLConnection connection) throws IOException {
        SwapiListPage result = null;
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
