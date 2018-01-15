package id.sch.smktelkom_mlg.project2.xirpl60203122324.kos_moklet.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import id.sch.smktelkom_mlg.project2.xirpl60203122324.kos_moklet.R;

/**
 * Created by Cipta-NB on 09-Jan-18.
 */

public class ImageAdapter extends PagerAdapter {

    Context context;
    private int[] GalImages = new int[] {
            R.drawable.anin,
            R.drawable.tum,
            R.drawable.gazka,
            R.drawable.tam,
    };
    public ImageAdapter(Context context){
        this.context = context;
    }
    @Override
    public int getCount() {
        return GalImages.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        ImageView imageView = new ImageView(context);
       // int padding = context.getResources().getDimensionPixelSize(R.dimen.padding_medium);
        int padding = 2;
        imageView.setPadding(padding, padding, padding, padding);
        imageView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
        imageView.setImageResource(GalImages[position]);
        container.addView(imageView, 0);
        return imageView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((ImageView) object);
    }
}
