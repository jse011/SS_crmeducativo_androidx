package com.consultoraestrategia.ss_crmeducativo.infoUsuario;

import android.app.Dialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.consultoraestrategia.ss_crmeducativo.R;
import com.consultoraestrategia.ss_crmeducativo.bundle.CRMBundle;
import com.consultoraestrategia.ss_crmeducativo.comportamiento.CreateComportamiento.ui.DialogCreareComportamiento;
import com.consultoraestrategia.ss_crmeducativo.entities.Persona;
import com.consultoraestrategia.ss_crmeducativo.entities.Persona_Table;
import com.consultoraestrategia.ss_crmeducativo.infoEstiloAprendizaje.listener.InfoEstiloAprendizajeListener;
import com.consultoraestrategia.ss_crmeducativo.infoEstiloAprendizaje.ui.InfoEstiloAprendizajeFragment;
import com.consultoraestrategia.ss_crmeducativo.infoRubro.ui.InfoRubroFragment;
import com.consultoraestrategia.ss_crmeducativo.sendMessage.sendMessageIndividual.ui.SendMessageNormalActivity;
import com.raizlabs.android.dbflow.sql.language.SQLite;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by SCIEV on 14/08/2018.
 */

public class InfoUsuarioFragment extends DialogFragment implements InfoEstiloAprendizajeListener {

    @BindView(R.id.img_user)
    ImageView imgUser;
    Unbinder unbinder;
    private final static String BYTE_IMAGEN_USUARIO = "infoUsuarioFragment.imagenByte";
    private final static String URL_IMAGEN_USUARIO = "infoUsuarioFragment.url";
    private final static String TITULO = "infoUsuarioFragment.titulo";
    private final static String ID_PERSONA = "infoUsuarioFragment.idAlumno";
    private final static String ID_ENTIDAD = "infoUsuarioFragment.idEntidad";
    private final static String ID_GEOREFERENCIA = "infoUsuarioFragment.idGeoreferencia";
    private final static String ID_RUBRICA = "infoUsuarioFragment.idRubrica";
    @BindView(R.id.txtTexto)
    TextView txtTexto;
    @BindView(R.id.imgMensaje)
    ImageView imgMensaje;
    @BindView(R.id.img_casos)
    ImageView imgCasos;

    public static InfoUsuarioFragment newInstance(byte[] byteArray, String url, String titulo, int idPersona, String rubricaId, CRMBundle crmBundle) {
        Bundle args = new Bundle();
        InfoUsuarioFragment fragment = new InfoUsuarioFragment();
        fragment.setArguments(args);
        args.putByteArray(BYTE_IMAGEN_USUARIO, byteArray);
        args.putString(TITULO, titulo);
        args.putString(ID_RUBRICA, rubricaId);
        args.putString(URL_IMAGEN_USUARIO, url);
        args.putInt(ID_PERSONA, idPersona);
        args.putAll(crmBundle.instanceBundle());
        args.putInt(ID_ENTIDAD, crmBundle.getEntidadId());
        args.putInt(ID_GEOREFERENCIA, crmBundle. getGeoreferenciaId());
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_info_usuario, container);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setupImagen();
    }

    private void setupImagen() {
        Bundle bundle = getArguments();
        if (bundle == null) return;
        Bitmap bmp = null;
        try {
            byte[] byteArray = bundle.getByteArray(BYTE_IMAGEN_USUARIO);
            if (byteArray != null)
                bmp = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
            if (bmp != null) imgUser.setImageBitmap(bmp);
            getArguments().remove("imagenByte");
        } catch (Exception e) {
            e.printStackTrace();
        }
        RequestOptions options = new RequestOptions()
                .diskCacheStrategy(DiskCacheStrategy.ALL);

        if (bmp != null) {
            Drawable drawable = new BitmapDrawable(getResources(), bmp);
            options = options.error(drawable);
        }

        String url = bundle.getString(URL_IMAGEN_USUARIO);
        Glide.with(imgUser.getContext())
                .load(url)
                .apply(options)
                .into(imgUser);

        String titulo = bundle.getString(TITULO, "");
        txtTexto.setText(titulo);
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = super.onCreateDialog(savedInstanceState);
        // request a window without the title
        if (dialog.getWindow() != null) dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        return dialog;

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick(R.id.imgMensaje)
    public void onViewClickedimgMensaje() {
        List<Persona> personaList = new ArrayList<>();
        Persona persona = null;
        if (getArguments() != null) persona = SQLite.select()
                .from(Persona.class)
                .where(Persona_Table.personaId.is(getArguments().getInt(ID_PERSONA, 0)))
                .querySingle();

        if (persona != null) personaList.add(persona);
        SendMessageNormalActivity.launchSendMessageNormalActivity(getContext(), personaList, 0, getArguments().getString(ID_RUBRICA));
        getDialog().dismiss();

    }

    @OnClick(R.id.img_estilo_apren)
    public void onViewClickedImgEstiloApren() {
        if (getActivity() != null && getArguments() != null) {
            InfoEstiloAprendizajeFragment infoRubroFragment = InfoEstiloAprendizajeFragment.newInstance(getArguments().getInt(ID_PERSONA), getArguments().getInt(ID_ENTIDAD), getArguments().getInt(ID_GEOREFERENCIA));
            infoRubroFragment.show(getActivity().getSupportFragmentManager(), InfoRubroFragment.class.getSimpleName());
            getDialog().dismiss();
        }

    }

    @OnClick(R.id.img_casos)
    public void onViewClickedImgCasos() {
        if (getActivity() != null && getArguments() != null) {
            int alumnoId = getArguments().getInt(ID_PERSONA);
            Log.d(getClass().getSimpleName(),"alumnoid "+ alumnoId);
            CRMBundle crmBundle = new CRMBundle(getArguments());
            FragmentManager manager = getActivity().getSupportFragmentManager();
            DialogCreareComportamiento dialogCreareComportamiento = DialogCreareComportamiento.newInstanceAlumno(crmBundle, alumnoId);
            dialogCreareComportamiento.show(manager, DialogCreareComportamiento.class.getSimpleName());
        }
    }
}
