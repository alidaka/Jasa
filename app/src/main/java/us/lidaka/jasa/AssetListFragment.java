package us.lidaka.jasa;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;

import java.util.ArrayList;

import us.lidaka.jasa.Model.SwapiAsset;
import us.lidaka.jasa.Model.SwapiListPage;
import us.lidaka.jasa.Model.SwapiListRequest;
import us.lidaka.jasa.Model.SwapiResponseListener;

/**
 * Created by augustus on 10/16/15.
 */
public class AssetListFragment extends Fragment implements SwapiResponseListener, AdapterView.OnItemClickListener, AbsListView.OnScrollListener {
    private static final String ARG_ASSET_NUMBER = "category_number";

    private AssetType assetType = AssetType.INVALID;

    private String nextPageUrl = null;

    private ArrayList<SwapiAsset> results = new ArrayList<>();

    private ArrayAdapter adapter = null;

    private LinearLayout bigSpinner = null;

    private ListView listView = null;

    private LinearLayout spinnerFooter = null;

    // For long-running tasks
    private volatile int oustandingNetworkRequests = 0;

    // Potentially short-running, or hasn't yet hit the network
    private volatile int outstandingDataLoads = 0;

    public static AssetListFragment newInstance(AssetType assetType) {
        AssetListFragment fragment = new AssetListFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_ASSET_NUMBER, assetType.getValue());
        fragment.setArguments(args);
        return fragment;
    }

    public AssetListFragment() {
    }

    private void updateSpinnerState() {
        // If we are performing the initial request, show the big spinner; otherwise small
        // Note: slightly unconventional reordering to reduce volatile reads, and a bit verbose due to unknown initialization state
        if (this.results.size() > 0) {
            if (this.bigSpinner != null) {
                this.bigSpinner.setVisibility(View.GONE);
            }

            if (this.outstandingDataLoads > 0) {
                if (this.listView != null && this.spinnerFooter != null) {
                    this.listView.addFooterView(this.spinnerFooter);
                }
            } else {
                if (this.listView != null && this.spinnerFooter != null) {
                    this.listView.removeFooterView(this.spinnerFooter);
                }
            }
        } else {
            if (this.listView != null && this.spinnerFooter != null) {
                this.listView.removeFooterView(this.spinnerFooter);
            }

            if (this.oustandingNetworkRequests > 0) {
                if (this.bigSpinner != null) {
                    this.bigSpinner.setVisibility(View.VISIBLE);
                }
            } else {
                if (this.bigSpinner != null) {
                    this.bigSpinner.setVisibility(View.GONE);
                }
            }
        }
    }

    @Override
    public void onNetworkRequestInitiated() {
        this.oustandingNetworkRequests++;
        this.updateSpinnerState();
    }

    @Override
    public void onListResponseReceived(SwapiListPage swapiListPage) {
        this.nextPageUrl = swapiListPage.nextUrl;
        this.results.addAll(swapiListPage.results);
        this.oustandingNetworkRequests--;
        this.outstandingDataLoads--;

        this.updateSpinnerState();

        if (this.adapter != null) {
            this.adapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onAssetResponseReceived(SwapiAsset swapiAsset) {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_asset_list, container, false);

        this.adapter = new ArrayAdapter<SwapiAsset>(this.getActivity(), android.R.layout.simple_list_item_1, this.results);
        this.listView = (ListView)view.findViewById(R.id.asset_list);
        this.listView.setAdapter(this.adapter);

        this.bigSpinner = (LinearLayout)view.findViewById(R.id.loading_spinner);

        this.spinnerFooter = (LinearLayout)inflater.inflate(R.layout.layout_loading_spinner, null);
        this.spinnerFooter.setVisibility(View.VISIBLE);
        this.listView.setFooterDividersEnabled(false);

        // Must update view state in case the response callback ran before creating the adapter
        this.updateSpinnerState();
        this.adapter.notifyDataSetChanged();

        return view;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.assetType = AssetType.fromInt(getArguments().getInt(ARG_ASSET_NUMBER));

        this.outstandingDataLoads++;
        SwapiListRequest request = new SwapiListRequest(this.assetType, this);
        request.execute();

        ((MainActivity) activity).onSectionAttached(this.assetType);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        // TODO: navigate to that item!
    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        if (this.nextPageUrl != null) {
            if (this.oustandingNetworkRequests == 0) {
                this.outstandingDataLoads++;
                SwapiListRequest request = new SwapiListRequest(this.assetType, this.nextPageUrl, this);
                request.execute();
            }
        }
    }
}
