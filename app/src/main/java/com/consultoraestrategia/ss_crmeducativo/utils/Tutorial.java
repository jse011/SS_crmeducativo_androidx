package com.consultoraestrategia.ss_crmeducativo.utils;

import android.app.Activity;
import androidx.fragment.app.Fragment;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import java.util.List;

import uk.co.deanwild.materialshowcaseview.MaterialShowcaseSequence;
import uk.co.deanwild.materialshowcaseview.MaterialShowcaseView;
import uk.co.deanwild.materialshowcaseview.ShowcaseConfig;

public class Tutorial {

    private static final String SECUENCIA_EVALAUCION_ID = "Tutorial.showTutorialEvaluacionUno";
    private static final String SECUENCIA_EVALAUCION_ALUMNO_ID = "Tutorial.showTutorialEvaluacionAlumno";
    private static final String SECUENCIA_EVALAUCION_ALUMNO_DOS_ID = "Tutorial.showTutorialEvaluacionDos";

    public static void showTutorialEvaluacionUno(Fragment fragment,
                                                 final NestedScrollView nestedScrollView,
                                                 RecyclerView rowHeaderRecyclerView,
                                                 RecyclerView cellRecyclerView,
                                                 RecyclerView columRecyclerView,
                                                 ImageView btnClear,
                                                 ImageView btnChange,
                                                 final EditText inputComentario,
                                                 final Button btnAddEvidencias, boolean notSecuence) {
        View column1 = null;
        View column2 = null;
        if(columRecyclerView.getChildCount()>=1) column1 = columRecyclerView.getChildAt(0);
        if(columRecyclerView.getChildCount()>=2) column2 = columRecyclerView.getChildAt(1);
        View cell1 = null;
        View cell2 = null;
        RecyclerView recyclerView = null;
        if(cellRecyclerView.getChildCount()>=1) recyclerView = (RecyclerView) cellRecyclerView.getChildAt(0);
        if(recyclerView!=null){
            if(recyclerView.getChildCount()>=1) cell1 = recyclerView.getChildAt(0);
            if(recyclerView.getChildCount()>=2) cell2 = recyclerView.getChildAt(1);
        }
        View row = null;
        if(rowHeaderRecyclerView.getChildCount()>=1) row = rowHeaderRecyclerView.getChildAt(0);


        // sequence example
        ShowcaseConfig config = new ShowcaseConfig();
        config.setDelay(500); // half second between each showcase view
        MaterialShowcaseSequence sequence;
        if(notSecuence){
            sequence = new MaterialShowcaseSequence(fragment);
        }else {
            sequence = new MaterialShowcaseSequence(fragment,SECUENCIA_EVALAUCION_ID);
        }


        sequence.setConfig(config);
        addInitTurorial(sequence, fragment.getActivity());
        if(cell1!=null)sequence.addSequenceItem(cell1,
                "Usa este boton para configurar el peso del indicador.", "Entendido");

        if(cell2!=null)sequence.addSequenceItem(cell2,
                "Usa este boton para configurar una nota teniendo encuenta que:\nAl presionar selecciona.\nAl presionar otra vez muestra la selecciona avanzada.\nAl mantener presionado deselecciona.", "Entendido");

        if(row!=null)sequence.addSequenceItem(row,
                MaterialShowcaseView.Builder.RECTANGLE_SHAPE,
                "Usa este boton e informarte los detalles del indicador.", "Entendido");

        sequence.addSequenceItem(btnClear,
                "Usa este boton para deseleccionar todos las notas.", "Entendido");

        sequence.addSequenceItem(btnChange,
                "Usa este boton para cambiar el modo de evaluación avanzado.", "Entendido");

        sequence.addSequenceItem(inputComentario,
                MaterialShowcaseView.Builder.RECTANGLE_SHAPE,
                "Dijita cualquier evidencia de la evaluación.", "Entendido");

        sequence.addSequenceItem(btnAddEvidencias,
                MaterialShowcaseView.Builder.RECTANGLE_SHAPE,
                "Ingrese cualquier evidencia de la evaluación.", "Entendido");


        sequence.setOnItemDismissedListener(new MaterialShowcaseSequence.OnSequenceItemDismissedListener() {
            @Override
            public void onDismiss(MaterialShowcaseView itemView, int position) {
                if(position==4){
                    int scrollTo =  (int) btnAddEvidencias.getY();
                    nestedScrollView.smoothScrollTo(0, scrollTo);
                }
            }
        });

        sequence.start();

    }


    public static void showTutorialEvaluacionAlumno(Activity activity,
                                                    RecyclerView columRecyclerView,
                                                    RecyclerView rowHeaderRecyclerView,
                                                    RecyclerView cellRecyclerView,
                                                    MenuItem searchView, boolean notSecuence) {
        View column2 = null;
        if(columRecyclerView.getChildCount()>=2) column2 = columRecyclerView.getChildAt(1);
        View cell1 = null;
        View cell2 = null;
        RecyclerView recyclerView = null;
        if(cellRecyclerView.getChildCount()>=1) recyclerView = (RecyclerView) cellRecyclerView.getChildAt(0);
        if(recyclerView!=null){
            if(recyclerView.getChildCount()>=1) cell1 = recyclerView.getChildAt(0);
            if(recyclerView.getChildCount()>=2) cell2 = recyclerView.getChildAt(1);
        }
        View row = null;
        if(rowHeaderRecyclerView.getChildCount()>=1) row = rowHeaderRecyclerView.getChildAt(0);

        // sequence example
        ShowcaseConfig config = new ShowcaseConfig();
        config.setDelay(500); // half second between each showcase view
        MaterialShowcaseSequence sequence;
        if(notSecuence){
            sequence = new MaterialShowcaseSequence(activity);
        }else {
            sequence = new MaterialShowcaseSequence(activity,SECUENCIA_EVALAUCION_ALUMNO_ID);
        }

        sequence.setConfig(config);
        addInitTurorial(sequence, activity);
        if(searchView!=null&&searchView.isVisible())sequence.addSequenceItem(searchView.getActionView(),
                "Usa este boton para buscar a los alumnos", "Entendido");

        if(column2!=null)sequence.addSequenceItem(column2,
                MaterialShowcaseView.Builder.RECTANGLE_SHAPE,
                "Usa este boton e informarte los detalles del indicador.", "Entendido");

        if(row!=null)sequence.addSequenceItem(row,
                MaterialShowcaseView.Builder.RECTANGLE_SHAPE,
                "Usa este boton e informarte los detalles del alumno.", "Entendido");

        if(cell2!=null)sequence.addSequenceItem(cell2,
                "Usa este boton para evaluar al alumno.", "Entendido");

        sequence.start();

    }

    public static void showTutorialEvaluacionDos(Activity activity,
                                                 View row,
                                                 List<View> columRecyclerView,
                                                 List<List<View>> bodyViewTable,
                                                 MenuItem searchView, ImageView btnClear, ImageView btnFooter, ImageView btnEye, boolean notSecuence) {
        View cell1 = null;
        View cell2 = null;
        List<View> viewList = null;
        if(bodyViewTable.size()>=1) viewList = bodyViewTable.get(0);
        if(viewList!=null){
            if(viewList.size()>=1) cell1 = viewList.get(0);
            if(viewList.size()>=2) cell2 = viewList.get(1);
        }
        View column = null;
        if(columRecyclerView.size()>=1) column = columRecyclerView.get(0);

        // sequence example
        ShowcaseConfig config = new ShowcaseConfig();
        config.setDelay(500); // half second between each showcase view


        final MaterialShowcaseSequence sequence2;
        if(notSecuence){
            sequence2 = new MaterialShowcaseSequence(activity);
        }else {
            sequence2 = new MaterialShowcaseSequence(activity,SECUENCIA_EVALAUCION_ALUMNO_DOS_ID);
        }

        sequence2.setConfig(config);

        addInitTurorial(sequence2, activity);
        if(searchView!=null&&searchView.isVisible())sequence2.addSequenceItem(searchView.getActionView(),
                "Usa este boton para buscar a los alumnos", "Entendido");


        if(row!=null)sequence2.addSequenceItem(row,
                MaterialShowcaseView.Builder.RECTANGLE_SHAPE,
                "Usa este boton e informarte los detalles del indicador.", "Entendido");

        if(column!=null)sequence2.addSequenceItem(column,
                MaterialShowcaseView.Builder.RECTANGLE_SHAPE,
                "Usa este boton e informarte los detalles del alumno.", "Entendido");

        if(cell1!=null)sequence2.addSequenceItem(cell1,
                "Usa este boton para configurar una nota teniendo encuenta que:\nAl presionar selecciona.\nAl presionar otra vez muestra la selecciona avanzada.\nAl mantener presionado deselecciona.", "Entendido");

        sequence2.addSequenceItem(btnClear,
                "Usa este boton para deseleccionar todos las notas.", "Entendido");

        sequence2.addSequenceItem(btnFooter,
                "Usa este boton para mostar más información de las notas..", "Entendido");

        sequence2.addSequenceItem(btnEye,
                "Usa este boton para cambiar el modo de evaluación avanzado.", "Entendido");




        sequence2.start();
    }

    private static void addInitTurorial(MaterialShowcaseSequence  materialShowcaseSequence, Activity activity){
        materialShowcaseSequence.addSequenceItem(
                new MaterialShowcaseView.Builder(activity)
                        .setTarget(new View(activity.getApplicationContext()))
                        .setTitleText("Tutorial")
                        .renderOverNavigationBar()
                        .setDismissText("INICIARLO")
                        .setContentText("Este es un tutorial rápido sobre cómo usar esta pantalla. Presione iniciarlo para continuar o presione atras para omitir esta guía. La interfaz de la aplicación se desactivará mientras esté leyendo este tutorial.")
                        .setSequence(true)
                        .build());

    }
}
