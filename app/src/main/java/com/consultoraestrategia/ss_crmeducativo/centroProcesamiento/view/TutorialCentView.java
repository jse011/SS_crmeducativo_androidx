package com.consultoraestrategia.ss_crmeducativo.centroProcesamiento.view;

import com.consultoraestrategia.ss_crmeducativo.centroProcesamiento.CentProcesoPresenter;

public interface TutorialCentView  {

    void setTitulo(String tituloCurso);

    void setTema(String color1, String color2, String color3);

    void setPresenter(CentProcesoPresenter presenter);
}
