package id.sch.smktelkom_mlg.project2.xirpl60203122324.kos_moklet;


import android.content.Intent;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

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

        mAdapter.setOnItemClickListener(new ClickListener() {
            @Override
            public void onItemClick(int position, View v) {
                Kost k = mList.get(position);
                Bundle bnd = new Bundle();
                bnd.putString("detail_nama", k.judul);
                bnd.putString("detail_deskripsi", k.deskripsi);
                bnd.putString("detail_lokasi", k.lokasi);
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

        recyclerView.setAdapter(mAdapter);

        //fillData();
        getDataFromFirebase();
        return view;
    }

    private void getDataFromFirebase(){
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("kos_putra");
        //StorageReference storageRef = storage.getReference();
       // StorageReference sref = FirebaseStorage.getInstance().getReference();
        ////StorageReference imgref = sref.getReferenceFromUrl("gs://bucket/images/stars.jpg");
        //StorageReference imgref = FirebaseStorage.getInstance().getReferenceFromUrl("gs://kosmo-5684f.appspot.com/kosputra1.jpg");

//        final StorageReference logoRef = sref.child("kos_putra/kosputra1.jpg");

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot ds) {
                Resources resources = getResources();
                TypedArray a = resources.obtainTypedArray(R.array.places_picture);
                //Drawable[] arFoto = new Drawable[a.length()];
                //final Drawable[] arFoto = new Drawable[1];

              /*
                for (int i = 0; i < arFoto.length; i++) {
                    BitmapDrawable bd = (BitmapDrawable) a.getDrawable(i);
                    RoundedBitmapDrawable rbd =
                            RoundedBitmapDrawableFactory.create(getResources(), bd.getBitmap());
                    rbd.setCircular(true);
                    arFoto[i] = rbd;
                }

                a.recycle();
                */

                Drawable[] arFoto = new Drawable[(int) ds.getChildrenCount()];
                int x = 0;

                for (DataSnapshot data:ds.getChildren()){

                    String lokasi = data.child("lokasi").getValue().toString();
                    String deskripsi = data.child("deskripsi").getValue().toString();
                    String nama = data.child("nama").getValue().toString();
                    String kamar = data.child("jumlah_kamar").getValue().toString();
                    String gambar = data.child("gambar").getValue().toString();
                    byte[] bbb = Base64.decode(gambar, Base64.DEFAULT);
                    Bitmap decodedByte = BitmapFactory.decodeByteArray(bbb, 0, bbb.length);
                    Drawable d = new BitmapDrawable(getContext().getResources(), decodedByte);
                    arFoto[x] = d;
                    mList.add(new Kost(nama, deskripsi, lokasi, arFoto[x]));
                    ++x;
                }

                mAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

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

    private byte[] convertDrawtableToByteArray(Drawable d){
        Bitmap bitmap = ((BitmapDrawable) d).getBitmap();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
        byte[] result = stream.toByteArray();
        return result;
    }
}

