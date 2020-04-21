package com.consultoraestrategia.ss_crmeducativo.SendMessage_base.ui;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.os.Bundle;
import androidx.core.content.ContextCompat;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatCheckBox;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;

import com.consultoraestrategia.ss_crmeducativo.R;
import com.consultoraestrategia.ss_crmeducativo.SendMessage_base.SendMessageBasePresenter;
import com.consultoraestrategia.ss_crmeducativo.SendMessage_base.SendMessageBasePresenterImpl;
import com.consultoraestrategia.ss_crmeducativo.SendMessage_base.SendMessageBaseView;
import com.consultoraestrategia.ss_crmeducativo.SendMessage_base.adapters.CustomAutoCompleteAdapter;
import com.consultoraestrategia.ss_crmeducativo.SendMessage_base.adapters.IntencionAdapter;
import com.consultoraestrategia.ss_crmeducativo.SendMessage_base.adapters.PersonasDestinoAdapter;
import com.consultoraestrategia.ss_crmeducativo.SendMessage_base.entities.IntencionUI;
import com.consultoraestrategia.ss_crmeducativo.base.activity.BaseActivity;
import com.consultoraestrategia.ss_crmeducativo.lib.GlideImageLoader;
import com.consultoraestrategia.ss_crmeducativo.mensajePredeterminado.ui.DialogMensajePredeterminado;
import com.consultoraestrategia.ss_crmeducativo.mensajePredeterminado.ui.intitiesUI.MensajePredeterminadoUI;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by irvinmarin on 12/07/2018.
 */

public abstract class BaseSendMessageActivity extends BaseActivity<SendMessageBaseView, SendMessageBasePresenter> implements SendMessageBaseView, PersonasDestinoAdapter.OnPersonaDestinoClickedListener, DialogMensajePredeterminado.onMensajePredeterminadoListener {


    @BindView(R.id.ibtnVolver)
    ImageButton ibtnVolver;
    @BindView(R.id.txtTituloAccion)
    TextView txtTituloAccion;
    @BindView(R.id.ibtnEnviarMensaje)
    ImageButton ibtnEnviarMensaje;
    @BindView(R.id.ibtnMenu)
    ImageButton ibtnMenu;
    @BindView(R.id.linearLayout5)
    LinearLayout linearLayout5;
    @BindView(R.id.cbRespuesta)
    AppCompatCheckBox cbRespuesta;
    @BindView(R.id.contentCheck)
    LinearLayout contentCheck;
    @BindView(R.id.textView8)
    TextView textView8;
    @BindView(R.id.spnIntencion)
    Spinner spnIntencion;
    @BindView(R.id.btnTalento)
    ImageButton btnTalento;
    @BindView(R.id.contentIntencion)
    LinearLayout contentIntencion;
    @BindView(R.id.rvTalento)
    RecyclerView rvTalento;
    @BindView(R.id.view9)
    View view9;
    @BindView(R.id.txtAsunto)
    EditText txtAsunto;
    @BindView(R.id.btnShowMessagePredeter)
    ImageButton btnShowMessagePredeter;
    @BindView(R.id.txtDescripcionMensaje)
    EditText txtDescripcionMensaje;
    @BindView(R.id.txtAdjuntIcon)
    TextView txtAdjuntIcon;
    @BindView(R.id.txtParaFinal)
    TextView txtParaFinal;
    @BindView(R.id.txtPersonaPadresList)
    AutoCompleteTextView txtPersonaPadresList;
    @BindView(R.id.rvPersonasPadresDestino)
    RecyclerView rvPersonasPadresDestino;
    @BindView(R.id.txtCountRestPadres)
    TextView txtCountRestPadres;
    @BindView(R.id.textView99)
    TextView textView99;
    @BindView(R.id.txtPersonaHijosList)
    AutoCompleteTextView txtPersonaHijosList;
    @BindView(R.id.rvPersonasHijosDestino)
    RecyclerView rvPersonasHijosDestino;
    @BindView(R.id.txtCountRestHijos)
    TextView txtCountRestHijos;
    @BindView(R.id.lyContentRVPersonasHijos)
    LinearLayout lyContentRVPersonasHijos;
    @BindView(R.id.progress)
    ProgressBar progress;
    @BindView(R.id.lyContentRVPersonasPadre)
    LinearLayout lyContentRVPersonasPadre;
    private PersonasDestinoAdapter destinoAdapterPadres;

    protected abstract SendMessageBasePresenterImpl getPresenterImpl();

    private final String TAG = SendMessageBasePresenterImpl.class.getSimpleName();

    public static final String EXTRA_PERSON_LIST = "extra_person_list";
    public static final String EXTRA_ID_CARGA_CURSO = "extra_id_carga_curso";
    public static final String EXTRA_ID_RUBRO = "extra_id_rubro";
    public static final String EXTRA_TIPO_LAYOUT_MENSAJE = "extra_tipo_layout_mensaje";


    protected void setTxtTituloAccion(String titulo) {
        txtTituloAccion.setText(titulo);
    }

    protected void hideAdjuntText(int hideText) {
        txtAdjuntIcon.setVisibility(hideText);
    }

    protected void hideBtnMessajePred(int hideText) {
        btnShowMessagePredeter.setVisibility(hideText);
    }

    @SuppressLint("SetTextI18n")
    protected void setDataToImputs(String titulo, String contenido, String fechaEntrega) {
        txtAsunto.setText(titulo);
        txtDescripcionMensaje.setText("Fecha Entrega Limite: \n" +
                fechaEntrega + "\n" +
                "Realizar la Siguente Tarea : \n" +
                contenido);
    }

    protected void hideIntencionLayout(int hideContent) {
        contentIntencion.setVisibility(hideContent);
    }

    @Override
    protected String getTag() {
        return TAG;
    }

    @Override
    protected AppCompatActivity getActivity() {
        return this;
    }

    @Override
    protected SendMessageBasePresenter getPresenter() {

        return getPresenterImpl();
    }

    @Override
    protected SendMessageBaseView getBaseView() {
        return this;
    }

    @Override
    protected Bundle getExtrasInf() {
        return getIntent().getExtras();
    }
    int tipoLayout;
    @Override
    protected void setContentView() {
        tipoLayout = getIntent().getIntExtra(EXTRA_TIPO_LAYOUT_MENSAJE, 0);
        if (tipoLayout == 0) setContentView(R.layout.layout_mensaje_grupal);
        if (tipoLayout == 1) setContentView(R.layout.layout_mensaje_grupal_face);
        ButterKnife.bind(this);
        txtTituloAccion.setText("Mensaje Rubros");
        setupChecks();
        setupActiveImputs();
    }


    private void setupActiveImputs() {

    }

    boolean isActiveRvPersonas = false;

    private void setupChecks() {
        cbRespuesta.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                presenter.onRespuestaCheckedChange(isChecked);
            }
        });
    }

    @Override
    protected ViewGroup getRootLayout() {
        return linearLayout5;
    }


    @Override
    protected ProgressBar getProgressBar() {
        return progress;
    }


    @Override
    public void showTypeIntencionList(final List<IntencionUI> intencionUIList) {

        IntencionAdapter arrayAdapter = new IntencionAdapter(this, R.layout.item_intencion, R.id.txtNombreIntencion, intencionUIList);
        spnIntencion.setAdapter(arrayAdapter);
        spnIntencion.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                presenter.onIntencionSelected(intencionUIList.get(position));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }


    PersonasDestinoAdapter destinoAdapterHijos;


    @Override
    public void enableViews(boolean isEnable) {
        txtAsunto.setEnabled(isEnable);
        txtDescripcionMensaje.setEnabled(isEnable);
        ibtnEnviarMensaje.setEnabled(isEnable);
        cbRespuesta.setEnabled(isEnable);
    }

    @Override
    public void clearImputs() {
        txtDescripcionMensaje.setText("");

    }


    @OnClick(R.id.ibtnEnviarMensaje)
    public void onViewClicked() {
        presenter.onBtnSendClicked(txtAsunto, txtDescripcionMensaje);
    }

    String intencionSelected = "";

    @SuppressLint("SetTextI18n")
    @Override
    public void showIntencionNameSelected(String nombre) {
        intencionSelected = nombre;
        txtAsunto.setText(intencionSelected + " - " + asuntoMensajePredetSelected);
    }

    @OnClick(R.id.ibtnVolver)
    public void onibtnVolverClicked() {
        finish();
    }

    @Override
    public void onClickShowPersonaDestino(int tipoParentezco, PersonaRelacionesUI personaRelacionesUI) {
        isActiveRvPersonas = true;
//        setupRVClicked();
    }

    @Override
    public void onClickDeletePersonaDestino(int tipoParentezco, PersonaRelacionesUI personaRelacionesUI) {
        isActiveRvPersonas = true;
        switch (tipoParentezco) {
            case PersonaRelacionesUI.PARENTEZCO_ALUMNO:
                destinoAdapterHijos.delete(personaRelacionesUI);
                presenter.deletePersonaHijosDestinoOfListSelected(personaRelacionesUI);
                presenter.addAutoCompleteListHijosDelete(personaRelacionesUI);
                break;
            case PersonaRelacionesUI.PARENTEZCO_PADRE:
                destinoAdapterPadres.delete(personaRelacionesUI);
                presenter.deletePersonaPadreDestinoOfListSelected(personaRelacionesUI);
                presenter.addAutoCompleteLisPadresDelete(personaRelacionesUI);
                break;
        }
    }


    @Override
    public void showPersonListToSendHijos(List<PersonaRelacionesUI> personaMessageHijosList) {
        if (destinoAdapterHijos == null) {
            destinoAdapterHijos = new PersonasDestinoAdapter(tipoLayout,rvPersonasHijosDestino, new ArrayList<PersonaRelacionesUI>(), new GlideImageLoader(this), this);
            rvPersonasHijosDestino.setLayoutManager(new LinearLayoutManager(this));
            rvPersonasHijosDestino.setAdapter(destinoAdapterHijos);
        }
        rvPersonasHijosDestino.setVisibility(View.VISIBLE);
        setupCountPersonsRest(txtCountRestHijos, personaMessageHijosList.size());
        destinoAdapterHijos.setPersonaRelacionesList(personaMessageHijosList);
    }


    @Override
    public void setupAutoCompleteTexViewHijos(final List<PersonaRelacionesUI> personaRelacionesHijosDeleted) {
        Log.d(TAG, "setupAutoCompleteTexViewHijos: personaRelacionesDeleted " + personaRelacionesHijosDeleted.toString());
        final ArrayAdapter<PersonaRelacionesUI> adapter = new CustomAutoCompleteAdapter(this, android.R.layout.simple_dropdown_item_1line, personaRelacionesHijosDeleted);
        txtPersonaHijosList.setAdapter(adapter);
        txtPersonaHijosList.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        presenter.updateListPersonaHijosSelecetd((PersonaRelacionesUI) parent.getItemAtPosition(position));
                        destinoAdapterHijos.add((PersonaRelacionesUI) parent.getItemAtPosition(position));
                        presenter.removeFromAutoCompleteTextViewHijos((PersonaRelacionesUI) parent.getItemAtPosition(position));
                        txtPersonaHijosList.setText("");
                    }
                });
    }

    @SuppressLint("SetTextI18n")
    private void setupCountPersonsRest(TextView textView, int size) {
        textView.setVisibility(View.VISIBLE);
        if (size >= 1) {
            textView.setText("(x" + String.valueOf(size) + ")");
        }
        if (size == 0) {
            textView.setText("Nadie Disponible");
        }
    }

    @Override
    public void onSendMessajeInidivdualClicked(PersonaRelacionesUI personaRelaciones) {
        presenter.onBtnSendClickedIndividual(txtAsunto, txtDescripcionMensaje,personaRelaciones);

    }

    @Override
    public void showPersonListToSendPadres(List<PersonaRelacionesUI> personaMessageList) {
        if (destinoAdapterPadres == null) {
            destinoAdapterPadres = new PersonasDestinoAdapter(tipoLayout, rvPersonasPadresDestino, new ArrayList<PersonaRelacionesUI>(), new GlideImageLoader(this), this);
            rvPersonasPadresDestino.setLayoutManager(new LinearLayoutManager(this));
            rvPersonasPadresDestino.setAdapter(destinoAdapterPadres);
        }
        rvPersonasPadresDestino.setVisibility(View.VISIBLE);
        setupCountPersonsRest(txtCountRestPadres, personaMessageList.size());
        destinoAdapterPadres.setPersonaRelacionesList(personaMessageList);
    }

    @Override
    public void setupAutoCompleteTexViewPadres(List<PersonaRelacionesUI> personaRelacionesDeleted) {
        final ArrayAdapter<PersonaRelacionesUI> adapter = new CustomAutoCompleteAdapter(this, android.R.layout.simple_dropdown_item_1line, personaRelacionesDeleted);
        txtPersonaPadresList.setAdapter(adapter);
        txtPersonaPadresList.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        presenter.updateListPersonaPadresSelecetd((PersonaRelacionesUI) parent.getItemAtPosition(position));
                        destinoAdapterPadres.add((PersonaRelacionesUI) parent.getItemAtPosition(position));
                        presenter.removeFromAutoCompleteTextViewPadres((PersonaRelacionesUI) parent.getItemAtPosition(position));
                        txtPersonaPadresList.setText("");
                    }
                });
    }

    @Override
    public void showRvDestinoPersona(int typeVisibility, String wichRv) {
        switch (wichRv) {
            case SendMessageBasePresenterImpl.RV_HIJOS:
                rvPersonasHijosDestino.setVisibility(typeVisibility);
                break;
            case SendMessageBasePresenterImpl.RV_PADRES:
                rvPersonasPadresDestino.setVisibility(typeVisibility);
                break;
        }
    }

    boolean showAllRvHijos;
    boolean showAllRvPadres;

    @OnClick({R.id.txtCountRestPadres, R.id.txtCountRestHijos})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.txtCountRestPadres:

                presenter.onCountClicked(SendMessageBasePresenterImpl.RV_PADRES, showAllRvPadres);
                break;
            case R.id.txtCountRestHijos:

                presenter.onCountClicked(SendMessageBasePresenterImpl.RV_HIJOS, showAllRvHijos);
                break;
        }
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void showAllrvHijos(int sizeList) {
        showAllRvHijos = true;
        lyContentRVPersonasHijos.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT)
        );
        txtPersonaHijosList.setVisibility(View.VISIBLE);
        txtCountRestHijos.setText("x" + sizeList);

    }

    @SuppressLint("SetTextI18n")
    @Override
    public void showAllrvPadres(int sizeList) {
        showAllRvPadres = true;
        lyContentRVPersonasPadre.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT)
        );
        txtPersonaPadresList.setVisibility(View.VISIBLE);
        txtCountRestPadres.setText("x" + sizeList);
    }

    @Override
    public void showOneItemrvHijos(int size) {
        showAllRvHijos = false;
        lyContentRVPersonasHijos.setLayoutParams(
                new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, dp2px(60)));
        setupCountPersonsRest(txtCountRestHijos, size);
    }

    protected int dp2px(int dp) {
        final float scale = getResources().getDisplayMetrics().density;
        return (int) (dp * scale + 0.5f);
    }

    public int toPixels(int dp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, getResources().getDisplayMetrics());
    }

    @Override
    public void showOneItemrvPadres(int size) {
        showAllRvPadres = false;
        lyContentRVPersonasPadre.setLayoutParams(
                new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, toPixels(60)));
        setupCountPersonsRest(txtCountRestPadres, size);
    }

    @OnClick(R.id.btnShowMessagePredeter)
    public void onbtnShowMessagePredeterClicked() {
        presenter.showMensajePredSelected();
    }

    @Override
    public void showMensajePredSelecetd(int idIntencionSelected) {
        DialogMensajePredeterminado dialogMensajePredeterminado = DialogMensajePredeterminado.newInstace(this, idIntencionSelected);
        dialogMensajePredeterminado.show(getSupportFragmentManager(), "dialogMensajePred");
    }

    String asuntoMensajePredetSelected = "";

    @SuppressLint("SetTextI18n")
    @Override
    public void onClickMensajeSelectedListener(MensajePredeterminadoUI mensajePredeterminadoUI) {
        asuntoMensajePredetSelected = mensajePredeterminadoUI.getAsuntoMensaje();
        txtAsunto.setText(intencionSelected + " - " + asuntoMensajePredetSelected);
        txtDescripcionMensaje.setText(mensajePredeterminadoUI.getContenidoJoin());
    }

    boolean visibleLyHijos = false;

    @OnClick(R.id.txtParaFinal)
    public void ontxtParaFinalClicked() {
        presenter.changeVisivilityLyHijos(visibleLyHijos);
    }

    @Override
    public void showLyHijos() {
        txtParaFinal.setCompoundDrawablesWithIntrinsicBounds(
                null,
                null,
                null,
                ContextCompat.getDrawable(this, R.drawable.ic_expand_less));
        lyContentRVPersonasHijos.setVisibility(View.VISIBLE);
        presenter.removeAllHijosToSend(false);

    }

    @Override
    public void hideLyHijos() {
        txtParaFinal.setCompoundDrawablesWithIntrinsicBounds(
                null,
                null,
                null,
                ContextCompat.getDrawable(this, R.drawable.ic_expand_more));
        lyContentRVPersonasHijos.setVisibility(View.GONE);
        presenter.removeAllHijosToSend(true);

    }

    @Override
    public void updateVisivilityLy(boolean visibleLyHijosChanged) {
        this.visibleLyHijos = visibleLyHijosChanged;
    }

    @OnClick(R.id.ibtnMenu)
    public void onibtnEnviarMensajeClicked(View view) {
        CharSequence[] charSequences = new CharSequence[3];
        charSequences[0] = "Apoderado";
        charSequences[1] = "Madres";
        charSequences[2] = "Padres";
        showMultiChoiseDialogImc("Elija Tipo de Destinatarios", charSequences);
    }

    public void showMultiChoiseDialogImc(String title, final CharSequence[] items) {
        final List<CharSequence> itemsSeleccionados = new ArrayList<>();
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity(), R.style.DialogAppTheme);
        builder.setTitle(title)
                .setMultiChoiceItems(items, null, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                        Log.d(getTag(), "setMultiChoiceItems onClickTipoComportamiento which: " + which + ", isChecked: " + isChecked);
                        if (isChecked) {
                            // Guardar indice seleccionado
                            itemsSeleccionados.add(items[which]);

                        } else if (itemsSeleccionados.contains(which)) {
                            // Remover indice sin selección
                            itemsSeleccionados.remove(Integer.valueOf(which));
                        }
                    }
                })
                .setPositiveButton(R.string.global_btn_positive, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int position) {
                        presenter.onMultiItemsSelectedImc(itemsSeleccionados, false);
                        dialogInterface.dismiss();
                    }
                });
        builder.create().show();
    }


    @Override
    public void changeCount(String wichRv, int sizeList) {

        switch (wichRv) {
            case SendMessageBasePresenterImpl.RV_HIJOS:
                setupCountPersonsRest(txtCountRestHijos, sizeList);
                break;
            case SendMessageBasePresenterImpl.RV_PADRES:
                setupCountPersonsRest(txtCountRestPadres, sizeList);
                break;
        }
    }
}