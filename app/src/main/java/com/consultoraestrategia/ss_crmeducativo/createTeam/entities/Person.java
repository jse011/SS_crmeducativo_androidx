package com.consultoraestrategia.ss_crmeducativo.createTeam.entities;

import android.graphics.Bitmap;
import androidx.annotation.NonNull;

import com.consultoraestrategia.ss_crmeducativo.entities.EquipoIntegranteC;
import com.consultoraestrategia.ss_crmeducativo.entities.Persona;
import com.consultoraestrategia.ss_crmeducativo.util.Utils;

import org.parceler.Parcel;
import org.parceler.Transient;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by @stevecampos on 19/09/2017.
 */
@Parcel
public class Person   {
    public String id;
    public String equipoIntegranteId;
    public String name;
    public String fullName;
    public String status;
    public String urlPicture;
    public boolean isMember;
    public String tipoAprendizaje;
    @Transient
    public Bitmap bitmap;
    public static final String[] tipoAprendizajeList = {"Reflexivo", "Conceptualizador", "Exp. Activa", "Exp. concreta"};
    public List<DimensionObservadaUi> dimensionObservadasUiList;

    public Person() {
    }

    public Person(String id, String name, String urlPicture) {
        this.id = id;
        this.name = name;
        this.urlPicture = urlPicture;
    }

    public Person(String id, String name, String fullName, String urlPicture) {
        this.id = id;
        this.name = name;
        this.fullName = fullName;
        this.urlPicture = urlPicture;
    }

    public Person(String id, String name, String fullName, String status, String urlPicture, boolean isMember) {
        this.id = id;
        this.name = name;
        this.fullName = fullName;
        this.status = status;
        this.urlPicture = urlPicture;
        this.isMember = isMember;
    }

    public Person(String id, String name, String status, String urlPicture, boolean isMember) {
        this.id = id;
        this.name = name;
        this.status = status;
        this.urlPicture = urlPicture;
        this.isMember = isMember;
    }

    public String getUrlPicture() {
        return urlPicture;
    }

    public void setUrlPicture(String urlPicture) {
        this.urlPicture = urlPicture;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public boolean isMember() {
        return isMember;
    }

    public void setMember(boolean member) {
        isMember = member;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getFirstName() {
        return Utils.getFirstWord(this.getName());
    }

    public String getTipoAprendizaje() {
        int min = 0;
        int max = 3;
        int randomNum = ThreadLocalRandom.current().nextInt(min, max + 1);
        return tipoAprendizajeList[randomNum];
    }

    public void setTipoAprendizaje(String tipoAprendizaje) {
        this.tipoAprendizaje = tipoAprendizaje;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Person person = (Person) o;

        return id != null ? id.equals(person.id) : person.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }


    public static Person transform(Persona persona) {
        if (persona == null) return null;
        return new Person(
                String.valueOf(persona.getPersonaId()),
                persona.getFirstName(),
                persona.getNombreCompleto(),
                persona.getUrlPicture());
    }

    public static List<Person> transform(List<EquipoIntegranteC> integrantes) {
        List<Person> personList = new ArrayList<>();
        for (EquipoIntegranteC integrante :
                integrantes) {
            Persona persona = integrante.getPersona();
            Person person = transform(persona);
            if(person!=null)person.setEquipoIntegranteId(integrante.getKey());
            personList.add(person);
        }
        return personList;
    }

    public String getEquipoIntegranteId() {
        return equipoIntegranteId;
    }

    public void setEquipoIntegranteId(String equipoIntegranteId) {
        this.equipoIntegranteId = equipoIntegranteId;
    }

    public static String[] getTipoAprendizajeList() {
        return tipoAprendizajeList;
    }

    public List<DimensionObservadaUi> getDimensionObservadasUiList() {
        return dimensionObservadasUiList;
    }

    public void setDimensionObservadasUiList(List<DimensionObservadaUi> dimensionObservadasUiList) {
        this.dimensionObservadasUiList = dimensionObservadasUiList;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

}

