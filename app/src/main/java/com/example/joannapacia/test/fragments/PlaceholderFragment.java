package com.example.joannapacia.test.fragments;

/**
 * Created by joannapacia on 12/03/17.
 */

import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.joannapacia.test.R;
import com.example.joannapacia.test.utilities.AsyncListViewLoader;

/**
 * A placeholder fragment containing a simple view.
 */
public  class PlaceholderFragment extends ListFragment {
    /**
     * The fragment argument representing the link for this
     * fragment.
     */
    String urlLink;

    private String TAG = PlaceholderFragment.class.getSimpleName();

    private AsyncListViewLoader asyncListViewLoader;
    private ListView lv;

    public PlaceholderFragment() {
    }

    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public static PlaceholderFragment newInstance(String url) {
        PlaceholderFragment fragment = new PlaceholderFragment();
        Bundle args = new Bundle();
        args.putString("URL", url);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        urlLink = getArguments() != null ? getArguments().getString("URL") : "http://www.independent.co.uk/api/v1/11831/json";
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                          Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        lv = (ListView) rootView.findViewById(android.R.id.list);
        return rootView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        // load a list view
        asyncListViewLoader = new AsyncListViewLoader(getActivity(), lv);
        asyncListViewLoader.execute(getArguments().getString("URL"));
    }
}
