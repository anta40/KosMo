package id.sch.smktelkom_mlg.project2.xirpl60203122324.kos_moklet;


import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

import id.sch.smktelkom_mlg.project2.xirpl60203122324.kos_moklet.adapter.ClickListener;
import id.sch.smktelkom_mlg.project2.xirpl60203122324.kos_moklet.adapter.KostAdapter;
import id.sch.smktelkom_mlg.project2.xirpl60203122324.kos_moklet.kost.Kost;


/**
 * A simple {@link Fragment} subclass.
 */
public class kos_putra extends Fragment {

    ArrayList<Kost> mList = new ArrayList<>();
    KostAdapter mAdapter;
    SearchView searchView;

    public kos_putra() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.kos_putra, container, false);
        final RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        mAdapter = new KostAdapter(mList);

        mAdapter.setOnItemClickListener(new ClickListener() {
            @Override
            public void onItemClick(int position, View v) {
                //Kost k = mList.get(position);
                Kost k = mAdapter.getFilteredList().get(position);
                Bundle bnd = new Bundle();
                bnd.putString("detail_nama", k.judul);
                bnd.putString("detail_deskripsi", k.deskripsi);
                bnd.putString("detail_lokasi", k.lokasi);
                bnd.putString("detail_kamar", k.kamar);
                bnd.putString("detail_latitude", k.latitute);
                bnd.putString("detail_longitude", k.longitude);
                byte[] bbb = convertDrawtableToByteArray(k.foto);
                bnd.putByteArray("detail_gambar", bbb);

                Intent iii = new Intent(getActivity(), DetailKosActivity.class);
                iii.putExtra("detail_kos", bnd);

                startActivity(iii);
            }

            @Override
            public void onItemLongClick(int position, View v) {
            }
        });

        searchView = (SearchView) view.findViewById(R.id.searchView);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                mAdapter.getFilter().filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String query) {
                mAdapter.getFilter().filter(query);
                return false;
            }
        });

        mAdapter.notifyDataSetChanged();
        recyclerView.setAdapter(mAdapter);
        getDataFromFirebase();
        return view;
    }

    private void getDataFromFirebase(){
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("kos_putra");
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot ds) {
                Resources resources = getResources();
                Drawable[] arFoto = new Drawable[(int) ds.getChildrenCount()];
                int x = 0;

                for (DataSnapshot data:ds.getChildren()){
                    String lokasi = data.child("lokasi").getValue().toString();
                    String deskripsi = data.child("deskripsi").getValue().toString();
                    String nama = data.child("nama").getValue().toString();
                    String kamar = data.child("jumlah_kamar").getValue().toString();
                    String gambar = data.child("gambar").getValue().toString();
                    String latitude = data.child("latitude").getValue().toString().trim();
                    String longitude = data.child("longitude").getValue().toString().trim();
                    byte[] bbb = Base64.decode(gambar, Base64.DEFAULT);
                    Bitmap decodedByte = BitmapFactory.decodeByteArray(bbb, 0, bbb.length);
                    Drawable d = new BitmapDrawable(getContext().getResources(), decodedByte);
                    arFoto[x] = d;
                    mList.add(new Kost(nama, deskripsi, kamar, lokasi, latitude, longitude, arFoto[x]));
                    ++x;
                }

                mAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    private byte[] convertDrawtableToByteArray(Drawable d){
        Bitmap bitmap = ((BitmapDrawable) d).getBitmap();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
        byte[] result = stream.toByteArray();
        return result;
    }

}

