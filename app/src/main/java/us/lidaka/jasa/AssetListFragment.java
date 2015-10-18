package us.lidaka.jasa;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import us.lidaka.jasa.Model.Film;
import us.lidaka.jasa.Model.SwapiAssetRequest;
import us.lidaka.jasa.Model.SwapiListRequest;
import us.lidaka.jasa.Model.SwapiResponseListener;

/**
 * Created by augustus on 10/16/15.
 */
public class AssetListFragment extends Fragment implements SwapiResponseListener {
    private static final String ARG_ASSET_NUMBER = "category_number";

    private String mContent = null;

    private SwapiResponseListener responseListener = null;

    public static AssetListFragment newInstance(AssetType asset) {
        AssetListFragment fragment = new AssetListFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_ASSET_NUMBER, asset.getValue());
        fragment.setArguments(args);
        return fragment;
    }

    public AssetListFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ListView view = (ListView)inflater.inflate(R.layout.fragment_asset_list, container, false);

        // TODO
        SwapiListRequest<Film> request = new SwapiListRequest(Film.class);
        request.execute();

        if (mContent != null) {

        }

        // else attach listener

        // Check in case the request returned during while hooking the listener
        if (mContent != null) {

        }

        return view;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        AssetType assetType = AssetType.fromInt(getArguments().getInt(ARG_ASSET_NUMBER));

        // TODO: initiate the service request

        ((MainActivity) activity).onSectionAttached(assetType);
    }

    @Override
    public void onResponseReceived(String s) {
        mContent = s;
        // TODO: update view
    }
}
