package com.example.wallpaperapp.Fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.wallpaperapp.Adapters.CollectionsAdapter;
import com.example.wallpaperapp.Models.Collection;
import com.example.wallpaperapp.R;
import com.example.wallpaperapp.Utils.Functions;
import com.example.wallpaperapp.Webservice.ApiInterface;
import com.example.wallpaperapp.Webservice.ServiceGenerator;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnItemClick;
import butterknife.Unbinder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CollectionsFragment extends Fragment {
    private final String TAG = CollectionsFragment.class.getSimpleName();
    @BindView(R.id.fragment_collections_grindView)
    GridView gridView;
    @BindView(R.id.fragment_collections_progressBar)
    ProgressBar progressBar;
    private Unbinder unbinder;

    private List<Collection> collections = new ArrayList<>();
    private CollectionsAdapter adapter;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_collections, container, false);
        unbinder = ButterKnife.bind(this, view);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        adapter = new CollectionsAdapter(getActivity(), collections);
        gridView.setAdapter(adapter);
        getCollections();
        showProgressBar(true);
        return view;
    }

    @OnItemClick(R.id.fragment_collections_grindView)
    public void onItemClick(int position){
        Collection collection = collections.get(position);
        Log.d(TAG, collection.getId() + "");
        Bundle bundle = new Bundle();
        bundle.putInt("collectionId", collection.getId());
        CollectionFragment collectionFragment = new CollectionFragment();
        collectionFragment.setArguments(bundle);
        Functions.changeMainFragmentWithBack(getActivity(), collectionFragment);
    }


    private void getCollections(){
        ApiInterface apiInterface = ServiceGenerator.createService(ApiInterface.class);
        Call<List<Collection>> call = apiInterface.getCollections();
        call.enqueue(new Callback<List<Collection>>() {
            @Override
            public void onResponse(Call<List<Collection>> call, Response<List<Collection>> response) {
                if(response.isSuccessful()){
                    for(Collection collection: response.body()){
                        collections.add(collection);
                    }
                    adapter.notifyDataSetChanged();
                    Log.d(TAG, "size " + collections.size());
                }else{
                    Log.d(TAG, "fail " + response.message());
                }
                showProgressBar(false);
            }

            @Override
            public void onFailure(Call<List<Collection>> call, Throwable t) {
                Log.d(TAG, "Fail " + t.getMessage());
                showProgressBar(false);
            }
        });
    }
    private void showProgressBar(boolean isShow){
        if(isShow){
            progressBar.setVisibility(View.VISIBLE);
            gridView.setVisibility(View.GONE);
        }else{
            progressBar.setVisibility(View.GONE);
            gridView.setVisibility(View.VISIBLE);
        }
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
