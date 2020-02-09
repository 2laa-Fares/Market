package apit.net.sa.market.Views;

import android.os.Bundle;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import apit.net.sa.market.R;

/**
 * A slide page fragment.
 */
public class SlidePageFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(
                R.layout.fragment_slide_page, container, false);

        init(rootView);

        return rootView;
    }

    /**
     * Initiate fields.
     */
    private void init(ViewGroup view){
        ImageView slideImage = view.findViewById(R.id.slideImage);
        TextView slideTitle = view.findViewById(R.id.slideTitle);

        if(getArguments() != null){
            int position = getArguments().getInt(getString(R.string.sliding_no), 0);
            if(position == 0){
                slideImage.setImageResource(R.mipmap.farming);
                slideTitle.setText(R.string.slide1_title);
            }else if(position == 1){
                slideImage.setImageResource(R.mipmap.vegetables);
                slideTitle.setText(R.string.slide2_title);
            }else if(position == 2){
                slideImage.setImageResource(R.mipmap.car);
                slideTitle.setText(R.string.slide3_title);
            }
        }

    }
}
