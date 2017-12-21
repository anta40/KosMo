package id.sch.smktelkom_mlg.project2.xirpl60203122324.kos_moklet;


import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import id.sch.smktelkom_mlg.project2.xirpl60203122324.kos_moklet.adapter.KostAdapter;
import id.sch.smktelkom_mlg.project2.xirpl60203122324.kos_moklet.kost.Kost;


/**
 * A simple {@link Fragment} subclass.
 */
public class kos_putra extends Fragment {

    ArrayList<Kost> mList = new ArrayList<>();
    KostAdapter mAdapter;


    public kos_putra() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.kos_putra, container, false);

        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        mAdapter = new KostAdapter(mList);
        recyclerView.setAdapter(mAdapter);

        fillData();
        return view;
    }

    private void fillData() {
        Resources resources = getResources();
        String[] arJudul = resources.getStringArray(R.array.places);
        String[] arDeskripsi = resources.getStringArray(R.array.place_desc);
        String[] arLokasi = resources.getStringArray(R.array.place_locations);
        TypedArray a = resources.obtainTypedArray(R.array.places_picture);
        Drawable[] arFoto = new Drawable[a.length()];
        for (int i = 0; i < arFoto.length; i++) {
            BitmapDrawable bd = (BitmapDrawable) a.getDrawable(i);
            RoundedBitmapDrawable rbd =
                    RoundedBitmapDrawableFactory.create(getResources(), bd.getBitmap());
            rbd.setCircular(true);
            arFoto[i] = rbd;
        }
        a.recycle();

        for (int i = 0; i < arJudul.length; i++) {
            mList.add(new Kost(arJudul[i], arDeskripsi[i], arLokasi[i], arFoto[i]));
        }
        mAdapter.notifyDataSetChanged();
    }
}

