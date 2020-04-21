package com.consultoraestrategia.ss_crmeducativo.login.tabProgress;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.core.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.consultoraestrategia.ss_crmeducativo.core2.R;
import com.consultoraestrategia.ss_crmeducativo.login.entity.LoginProgressPagerUi;

public class FragmentTabProgress extends Fragment  {
    TextView title;
    ImageView image;
    LinearLayout root;

    private static String DATA = "FragmentTabProgress.data";
    public static FragmentTabProgress newInstance(LoginProgressPagerUi loginProgressPagerUi) {
        Bundle args = new Bundle();
        args.putSerializable(DATA, loginProgressPagerUi);
        FragmentTabProgress fragment = new FragmentTabProgress();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tab_progress, container, false);
        title = view.findViewById(R.id.title);
        image = view.findViewById(R.id.image);
        root = view.findViewById(R.id.root);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        LoginProgressPagerUi loginProgressPagerUi = (LoginProgressPagerUi)getArguments().getSerializable(DATA);
        if(loginProgressPagerUi!=null)showConten(loginProgressPagerUi);
    }

    public void showConten(LoginProgressPagerUi loginProgressPagerUi) {
        title.setText(loginProgressPagerUi.getTitulo());
        //root.setBackgroundColor(ContextCompat.getColor(root.getContext(), loginProgressPagerUi.getColor()));
        image.setImageDrawable(ContextCompat.getDrawable(image.getContext(), loginProgressPagerUi.getDrawable()));
    }

}
