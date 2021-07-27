package com.consultoraestrategia.ss_crmeducativo.lib.imageViewZoom;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentActivity;

import com.bumptech.glide.Glide;
import com.consultoraestrategia.ss_crmeducativo.core2.R;
import com.consultoraestrategia.ss_crmeducativo.util.Utils;
import com.github.chrisbanes.photoview.PhotoView;


public class ImageZomDialog extends DialogFragment {
    private ImageView dialogBackImageView;
    private PhotoView dialogImageView;
    private View view;
    private String image;
    private RelativeLayout root;

    public ImageZomDialog() {
    }

    @Nullable
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        this.view = inflater.inflate(R.layout.dialog_util_image_zoom, container, false);
        this.createFullScreenDialogFragment();
        this.init();
        this.dialogBackImageView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if(dialogImageView!=null) Glide.with(getContext()).clear(dialogImageView);
                dismiss();
            }
        });
        Glide.with(getContext())
                .load(image)
                .apply(Utils.getGlideRequestOptionsSimple())
                .into(dialogImageView);
        return this.view;
    }

    public void init() {
        this.dialogBackImageView = (ImageView)this.view.findViewById(R.id.dialog_back_image_view);
        this.dialogImageView = (PhotoView)this.view.findViewById(R.id.dialog_image_view);
    }

    public void show(Context context, String image) {
        super.show(((FragmentActivity)context).getSupportFragmentManager(), "ContentValues");
        this.image = image;
    }


    public void onStart() {
        super.onStart();
        this.getDialog().getWindow().getAttributes().windowAnimations = R.style.DialogStyleUtilImageZoom;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    public void createFullScreenDialogFragment() {
        this.root = new RelativeLayout(this.getActivity());
        this.root.setLayoutParams(new ViewGroup.LayoutParams(-1, -1));
        this.getDialog().requestWindowFeature(1);
        this.getDialog().setContentView(this.root);
        this.getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(-16777216));
        this.getDialog().getWindow().setLayout(-1, -1);
    }
}

