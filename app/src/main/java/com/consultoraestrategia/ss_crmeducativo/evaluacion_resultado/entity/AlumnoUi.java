package com.consultoraestrategia.ss_crmeducativo.evaluacion_resultado.entity;

import org.parceler.Parcel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by @stevecampos on 16/10/2017.
 */
@Parcel
public class AlumnoUi {
    public String id;
    public String name;
    public String lastName;
    public String urlProfile;
    public List<NotaUi> notaUiList = new ArrayList<>();


    public AlumnoUi() {
    }

    public AlumnoUi(String id) {
        this.id = id;
    }

    public AlumnoUi(String id, String name, String lastName, String urlProfile) {
        this.id = id;
         this.name = name;
        this.lastName = lastName;
        this.urlProfile = urlProfile;
    }

    public AlumnoUi(String id, String name, String urlProfile, List<NotaUi> notaUiList) {
        this.id = id;

        this.name = name;
        this.urlProfile = urlProfile;
        this.notaUiList.addAll(notaUiList);
    }

    public AlumnoUi(String id, String name, String lastName, String urlProfile, List<NotaUi> notaUiList) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.urlProfile = urlProfile;
        this.notaUiList.addAll(notaUiList);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getUrlProfile() {
        return urlProfile;
    }

    public void setUrlProfile(String urlProfile) {
        this.urlProfile = urlProfile;
    }

    public List<NotaUi> getNotaUiList() {
        return notaUiList;
    }

    public void setNotaUiList(List<NotaUi> notaUiList) {
        this.notaUiList = notaUiList;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }



    @Override
    public boolean equals(Object obj) {
        if (getClass() != obj.getClass())
            return false;

        boolean equals = false;
        AlumnoUi alumno = (AlumnoUi) obj;
        if (alumno.getId().equals(id)) {
            equals = true;
        }
        return equals;
    }

    @Override
    public int hashCode() {
        return Integer.parseInt(id);
    }

    public String getUrlPicture() {
        return urlProfile;
    }

    @Override
    public String toString() {
        return "id: " + id + "\n" +
                "name: " + name + "\n" +
                "lastName: " + lastName + "\n" +
                "urlProfile: " + urlProfile + "\n";
    }
}
