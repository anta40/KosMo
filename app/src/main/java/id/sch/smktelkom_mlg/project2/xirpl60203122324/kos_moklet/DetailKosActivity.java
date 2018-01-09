package id.sch.smktelkom_mlg.project2.xirpl60203122324.kos_moklet;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class DetailKosActivity extends AppCompatActivity {

    ImageView detail_gambar;
    TextView detail_nama;
    TextView detail_deskripsi;
    TextView detail_lokasi;
    TextView detail_kamar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_kos);

        Intent iii = getIntent();
        Bundle bnd = iii.getBundleExtra("detail_kos");

        byte[] bbb = bnd.getByteArray("detail_gambar");

        detail_gambar = (ImageView) findViewById(R.id.detail_gambar);
        detail_nama = (TextView) findViewById(R.id.detail_nama);
        detail_deskripsi = (TextView) findViewById(R.id.detail_deskripsi);
        detail_lokasi = (TextView) findViewById(R.id.detail_lokasi);
        detail_kamar = (TextView) findViewById(R.id.detail_kamar);

        detail_nama.setText(bnd.getString("detail_nama"));
        detail_deskripsi.setText("No telp: "+bnd.getString("detail_deskripsi"));
        detail_lokasi.setText(bnd.getString("detail_lokasi"));
        detail_kamar.setText("Jumlah kamar: "+bnd.getString("defail_kamar"));

        Bitmap bm = BitmapFactory.decodeByteArray(bbb, 0, bbb.length);
        detail_gambar.setImageBitmap(bm);

        detail_deskripsi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               String input = detail_deskripsi.getText().toString();
               input = input.replace("No telp: ","");

               Intent callIntent = new Intent(Intent.ACTION_DIAL);
               callIntent.setData(Uri.parse("tel:"+Uri.encode(input.trim())));
               callIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
               startActivity(callIntent);
            }
        });
    }
}
