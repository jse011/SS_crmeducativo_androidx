package com.consultoraestrategia.ss_crmeducativo.login.adapter.viewHolder;

import androidx.core.content.ContextCompat;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.consultoraestrategia.ss_crmeducativo.core2.R;
import com.consultoraestrategia.ss_crmeducativo.login.entity.LoginProgressPagerUi;

public class TabProgressViewHolder{
    TextView title;
    ImageView image;
    LinearLayout root;


    public TabProgressViewHolder(View view) {
        title = view.findViewById(R.id.title);
        image = view.findViewById(R.id.image);
        root = view.findViewById(R.id.root);
    }

    public void bind(LoginProgressPagerUi loginProgressPagerUi) {
        title.setText(loginProgressPagerUi.getTitulo());
        //root.setBackgroundColor(ContextCompat.getColor(root.getContext(), loginProgressPagerUi.getColor()));
        image.setImageDrawable(ContextCompat.getDrawable(image.getContext(), loginProgressPagerUi.getDrawable()));
    }

}
