package com.consultoraestrategia.ss_crmeducativo.entities;

import com.consultoraestrategia.ss_crmeducativo.lib.AppDatabase;
import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;

@Table(database = AppDatabase.class)
public class PersonaGeoOrg extends BaseModel {
    @PrimaryKey
    private int personaGeoRefOrgId ;
    @Column
    private int geoRefOrganigramaId ;
    @Column
    private int personaId ;

    public int getPersonaGeoRefOrgId() {
        return personaGeoRefOrgId;
    }

    public void setPersonaGeoRefOrgId(int personaGeoRefOrgId) {
        this.personaGeoRefOrgId = personaGeoRefOrgId;
    }

    public int getGeoRefOrganigramaId() {
        return geoRefOrganigramaId;
    }

    public void setGeoRefOrganigramaId(int geoRefOrganigramaId) {
        this.geoRefOrganigramaId = geoRefOrganigramaId;
    }

    public int getPersonaId() {
        return personaId;
    }

    public void setPersonaId(int personaId) {
        this.personaId = personaId;
    }
}
