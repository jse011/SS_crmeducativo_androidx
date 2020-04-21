package com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.adapter;

import android.content.Context;
import android.content.DialogInterface;
import androidx.appcompat.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.consultoraestrategia.ss_crmeducativo.R;
import com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.rubBidTable.BodyCellViewGroup;
import com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.rubBidTable.FirstBodyCellViewGroup;
import com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.rubBidTable.FirstHeaderCellViewGroup;
import com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.rubBidTable.HeaderCellViewGroup;
import com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.rubBidTable.SectionCellViewGroup;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_resultado.entity.AlumnoUi;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_resultado.entity.NotaUi;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_resultado.entity.RubroEvaluacionUi;
import com.consultoraestrategia.ss_crmeducativo.tablefixheaders.TableFixHeaderAdapter;
import com.consultoraestrategia.ss_crmeducativo.tablefixheaders.adapters.BaseTableAdapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * Created by @stevecampos on 27/11/2017.
 */

public class RubBidAdapterBuilder {
    private Context context;
    private String firstHeader;
    private List<RubroEvaluacionUi> header;
    private List<AlumnoUi> body;
    private NotasListener listener;

    public interface NotasListener {
        void onNotaSelected(NotaUi nota, View view);

        void onAlumnoSelected(AlumnoUi alumnoUi, View view);

        void onRubroSelected(RubroEvaluacionUi rubroEvaluacionUi, View view);
    }

    public RubBidAdapterBuilder(Context context) {
        this.context = context;
    }

    public void setListener(RubBidAdapterBuilder.NotasListener listener) {
        this.listener = listener;
    }

    public BaseTableAdapter getInstance(String firstHeader, List<AlumnoUi> body, List<RubroEvaluacionUi> headers) {
        RubBidAdapter adapter = new RubBidAdapter(context, headers.size());
        /*this.body = body;
        this.header = headers;
        this.firstHeader = firstHeader;*/
       // body.add(new AlumnoUi("", "", "", ""));
        adapter.setFirstHeader(firstHeader);
        adapter.setHeader(headers);
        adapter.setFirstBody(body);
        adapter.setBody(body);
        adapter.setSection(body);

        setListeners(adapter);

        return adapter;
    }


    private void setListeners(final RubBidAdapter adapter) {
        TableFixHeaderAdapter.ClickListener<String, FirstHeaderCellViewGroup> clickListenerFirstHeader = new TableFixHeaderAdapter.ClickListener<String, FirstHeaderCellViewGroup>() {
            @Override
            public void onClickItem(String item, FirstHeaderCellViewGroup viewGroup, int row, int column) {
                //Snackbar.make(viewGroup, "Click on " + item + " (" + row + "," + column + ") ", Snackbar.LENGTH_SHORT).show();
            }
        };

        TableFixHeaderAdapter.ClickListener<RubroEvaluacionUi, HeaderCellViewGroup> clickListenerHeader = new TableFixHeaderAdapter.ClickListener<RubroEvaluacionUi, HeaderCellViewGroup>() {
            @Override
            public void onClickItem(RubroEvaluacionUi item, HeaderCellViewGroup viewGroup, int row, int column) {
                //Snackbar.make(viewGroup, "Click on " + item.getTitle() + " (" + row + "," + column + ") ", Snackbar.LENGTH_SHORT).show();
                if (listener != null) {
                    listener.onRubroSelected(item, viewGroup);
                }
            }
        };

        TableFixHeaderAdapter.ClickListener<AlumnoUi, BodyCellViewGroup> clickListenerBody = new TableFixHeaderAdapter.ClickListener<AlumnoUi, BodyCellViewGroup>() {
            boolean isSelected = false;

            @Override
            public void onClickItem(AlumnoUi item, BodyCellViewGroup viewGroup, int row, int column) {
                createCategoriaNivelDialog(viewGroup.getContext()).show();
                /*isSelected = !isSelected;
                int bgColor = R.attr.selectableItemBackground;
                if (isSelected) {
                    bgColor = R.color.colorAccent;
                    viewGroup.setBackgroundColor(ContextCompat.getColor(viewGroup.getContext(), bgColor));
                } else {
                    TypedValue outValue = new TypedValue();
                    viewGroup.getContext().getTheme().resolveAttribute(android.R.attr.selectableItemBackground, outValue, true);
                    viewGroup.setBackgroundResource(outValue.resourceId);
                }*/
                /*List<NotaUi> notaUiList = item.getNotaUiList();
                if (notaUiList.size() > column) {
                    NotaUi nota = item.getNotaUiList().get(column);
                    //Snackbar.make(viewGroup, "Click on " + item.getNotaUiList().get(column).getNota() + " (" + row + "," + column + ")", Snackbar.LENGTH_SHORT).show();
                    if (listener != null) {
                        listener.onHoraDiaSelected(nota, viewGroup);
                    }
                }*/
            }
        };

        TableFixHeaderAdapter.ClickListener<AlumnoUi, FirstBodyCellViewGroup> clickListenerFirstBody = new TableFixHeaderAdapter.ClickListener<AlumnoUi, FirstBodyCellViewGroup>() {
            @Override
            public void onClickItem(AlumnoUi item, FirstBodyCellViewGroup viewGroup, int row, int column) {
                createComentarioCategoria(viewGroup).show();
                if (listener != null) {
                    listener.onAlumnoSelected(item, viewGroup);
                }
                //Snackbar.make(viewGroup, "Click on " + item.getName() + " (" + row + "," + column + ")", Snackbar.LENGTH_SHORT).show();
            }
        };

        TableFixHeaderAdapter.ClickListener<AlumnoUi, SectionCellViewGroup> clickListenerSection = new TableFixHeaderAdapter.ClickListener<AlumnoUi, SectionCellViewGroup>() {
            @Override
            public void onClickItem(AlumnoUi item, SectionCellViewGroup viewGroup, int row, int column) {
                //Snackbar.make(viewGroup, "Click on " + item.getName() + " (" + row + "," + column + ")", Snackbar.LENGTH_SHORT).show();
            }
        };

        adapter.setClickListenerFirstHeader(clickListenerFirstHeader);
        adapter.setClickListenerHeader(clickListenerHeader);
        adapter.setClickListenerFirstBody(clickListenerFirstBody);
        adapter.setClickListenerBody(clickListenerBody);
        adapter.setClickListenerSection(clickListenerSection);
    }

    public List<RubroEvaluacionUi> getHeader() {
        final RubroEvaluacionUi headers[] = {
                new RubroEvaluacionUi("1", "5 Mayo", "Sesión 1\n8:00 AM"),
                new RubroEvaluacionUi("1", "12 Mayo", "Sesión 2\n8:00 AM"),
                new RubroEvaluacionUi("1", "19 Mayo", "Sesión 3\n8:00 AM"),
                new RubroEvaluacionUi("1", "26 Mayo", "Sesión 4\n8:00 AM"),
                new RubroEvaluacionUi("1", "2 Junio", "Sesión 4\n8:00 AM"),
                new RubroEvaluacionUi("1", "9 Junio", "Sesión 5\n8:00 AM"),
        };

        return Arrays.asList(headers);
    }

    public List<AlumnoUi> getBody() {
        List<NotaUi> resultados = new ArrayList<>();
        for (int i = 0; i <= 6; i++) {
            resultados.add(new NotaUi(String.valueOf(i), String.valueOf(14 + i)));
        }

        List<AlumnoUi> items = new ArrayList<>();
        items.add(new AlumnoUi("1","", "Steve", "https://lh3.googleusercontent.com/-B42xVxBMpx0/AAAAAAAAAAI/AAAAAAAAEc8/aZ66s9K0gR4/s60-p-rw-no/photo.jpg", resultados));
        items.add(new AlumnoUi("2","", "Nicolás", "https://lh3.googleusercontent.com/-ePcqW-Xh6o8/AAAAAAAAAAI/AAAAAAABPRQ/CbAtKlR8lgQ/s60-p-rw-no/photo.jpg", resultados));
        items.add(new AlumnoUi("3","", "Nick", "https://lh3.googleusercontent.com/-2H1epRsr7LA/AAAAAAAAAAI/AAAAAAAANqM/pBWZqKyMllw/s60-p-rw-no/photo.jpg", resultados));
        items.add(new AlumnoUi("4","", "Schopenhahuer", "https://upload.wikimedia.org/wikipedia/commons/thumb/8/8a/Schopenhauer.jpg/122px-Schopenhauer.jpg", resultados));
        items.add(new AlumnoUi("1", "","Steve", "https://lh3.googleusercontent.com/-B42xVxBMpx0/AAAAAAAAAAI/AAAAAAAAEc8/aZ66s9K0gR4/s60-p-rw-no/photo.jpg", resultados));
        items.add(new AlumnoUi("2", "","Nicolás", "https://lh3.googleusercontent.com/-ePcqW-Xh6o8/AAAAAAAAAAI/AAAAAAABPRQ/CbAtKlR8lgQ/s60-p-rw-no/photo.jpg", resultados));
        items.add(new AlumnoUi("3", "","Nick", "https://lh3.googleusercontent.com/-2H1epRsr7LA/AAAAAAAAAAI/AAAAAAAANqM/pBWZqKyMllw/s60-p-rw-no/photo.jpg", resultados));
        items.add(new AlumnoUi("4","", "Schopenhahuer", "https://upload.wikimedia.org/wikipedia/commons/thumb/8/8a/Schopenhauer.jpg/122px-Schopenhauer.jpg", resultados));
        items.add(new AlumnoUi("1", "","Steve", "https://lh3.googleusercontent.com/-B42xVxBMpx0/AAAAAAAAAAI/AAAAAAAAEc8/aZ66s9K0gR4/s60-p-rw-no/photo.jpg", resultados));
        items.add(new AlumnoUi("2", "","Nicolás", "https://lh3.googleusercontent.com/-ePcqW-Xh6o8/AAAAAAAAAAI/AAAAAAABPRQ/CbAtKlR8lgQ/s60-p-rw-no/photo.jpg", resultados));
        items.add(new AlumnoUi("3","", "Nick", "https://lh3.googleusercontent.com/-2H1epRsr7LA/AAAAAAAAAAI/AAAAAAAANqM/pBWZqKyMllw/s60-p-rw-no/photo.jpg", resultados));
        items.add(new AlumnoUi("4","", "Schopenhahuer", "https://upload.wikimedia.org/wikipedia/commons/thumb/8/8a/Schopenhauer.jpg/122px-Schopenhauer.jpg", resultados));
        return items;
    }

    public AlertDialog createCategoriaNivelDialog(Context context) {
        CharSequence[] items = new CharSequence[3];

        items[0] = "Identifica datos del problema";
        items[1] = "Organiza los datos del problema";
        items[2] = "Plantea modelo matemático";
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Categoría: Traduce\n" +
                "Nivel: Destacado")
                .setMessage("Traduce datos en términos algebraicos acorde al problema considerando expresiones algebraicas")
                .setView(R.layout.item_parent_checkedtext)
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                })
                .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                })
                .setNeutralButton("Agregar Otro", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
        return builder.create();
    }

    public AlertDialog createComentarioCategoria(ViewGroup parent) {

        AlertDialog.Builder builder = new AlertDialog.Builder(parent.getContext());
        LinearLayout view = (LinearLayout) LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_comentario_categoria_content, parent, false);

        builder.setTitle("Orden y Secuencia...")
                .setMessage("Comentarios:")
                .setView(view)
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                })
                .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                })
                /*.setNeutralButton("Agregar Otro", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClickTipoComportamiento(DialogInterface dialogInterface, int i) {

                    }
                })*/;
        ;
        return builder.create();
    }

}
