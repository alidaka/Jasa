package us.lidaka.jasa.Model;

import android.os.AsyncTask;

/**
 * Created by augustus on 10/16/15.
 */
public class SwapiAssetRequest extends AsyncTask<String, Void, SwapiAsset> {
    @Override
    protected void onPostExecute(SwapiAsset result) {
        super.onPostExecute(result);

        // TODO: parse the JSON with gson
    }

    @Override
    protected SwapiAsset doInBackground(String... params) {
        String url = params.length > 0 ? params[0] : null;
        return null;
    }
}
