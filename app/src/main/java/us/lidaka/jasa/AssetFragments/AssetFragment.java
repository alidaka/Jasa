package us.lidaka.jasa.AssetFragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

import us.lidaka.jasa.AssetType;
import us.lidaka.jasa.MainActivity;
import us.lidaka.jasa.Model.SwapiAsset;
import us.lidaka.jasa.Model.SwapiAssetRequest;
import us.lidaka.jasa.Model.SwapiListPage;
import us.lidaka.jasa.Model.SwapiResponseListener;
import us.lidaka.jasa.R;

/**
 * Created by augustus on 10/25/15.
 */
public abstract class AssetFragment extends Fragment implements SwapiResponseListener {

    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_ASSET_URL = "asset_url";

    private String url;

    private LinearLayout bigSpinner = null;

    private AssetType assetType;

    private int viewResourceId;

    protected SwapiAsset asset;

    public AssetFragment(AssetType assetType, int viewResourceId) {
        this.assetType = assetType;
        this.viewResourceId = viewResourceId;
    }
    protected static void addUrlArgument(AssetFragment fragment, String url) {
        Bundle args = new Bundle();
        args.putString(ARG_ASSET_URL, url);
        fragment.setArguments(args);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(this.viewResourceId, container, false);

        this.bigSpinner = (LinearLayout)view.findViewById(R.id.loading_spinner);

        // Must update view state in case the response callback ran before creating the adapter
        this.updateView();

        return view;
    }

    protected void setSpinnerVisibility(boolean visible) {
        if (this.bigSpinner != null) {
            this.bigSpinner.setVisibility(visible ? View.VISIBLE : View.GONE);
        }
    }

    protected void setText(View containerView, int textViewId, int stringFormatId, String value) {
        String s = String.format(getString(stringFormatId), value);
        setText(containerView, textViewId, s);
    }

    protected void setText(View containerView, int textViewId, String value) {
        TextView tv = (TextView)containerView.findViewById(textViewId);
        tv.setText(value);
    }

    protected void setList(View containerView, int listViewId, int headerId, List<String> value) {
        View headerListView = containerView.findViewById(listViewId);

        TextView header = (TextView)headerListView.findViewById(R.id.list_header);
        header.setText(getString(headerId));

        ListView lv = (ListView)headerListView.findViewById(R.id.list);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this.getActivity(), android.R.layout.simple_list_item_1, value);
        lv.setAdapter(adapter);

        AssetFragment.setListViewHeightBasedOnChildren(lv, adapter);

        // TODO: set onClickItemListener
    }

    public static void setListViewHeightBasedOnChildren(ListView view, ListAdapter adapter) {
        int totalHeight = view.getPaddingTop() + view.getPaddingBottom();
        for (int i = 0; i < adapter.getCount(); i++) {
            // Note: if the View is a ViewGroup, need to set layout params appropriately here--but they won't be for our purposes
            View listItem = adapter.getView(i, null, view);
            listItem.measure(0, 0);
            totalHeight += listItem.getMeasuredHeight();
        }

        ViewGroup.LayoutParams params = view.getLayoutParams();
        params.height = totalHeight + (view.getDividerHeight() * (adapter.getCount() - 1));
        view.setLayoutParams(params);
    }

    protected void setTitle(String title) {
        MainActivity activity = (MainActivity)this.getActivity();
        activity.onSectionAttached(title);
    }

    protected abstract void updateView();

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        this.url = getArguments().getString(ARG_ASSET_URL);

        SwapiAssetRequest request = new SwapiAssetRequest(this.assetType, this.url, this);
        request.execute();
    }

    @Override
    public void onNetworkRequestInitiated() {
        final AssetFragment _this = this;
        this.getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                _this.setSpinnerVisibility(true);
            }
        });
    }

    @Override
    public void onListResponseReceived(SwapiListPage swapiListPage) {
    }

    @Override
    public void onAssetResponseReceived(SwapiAsset swapiAsset) {
        this.asset = swapiAsset;
        this.updateView();
    }
}
