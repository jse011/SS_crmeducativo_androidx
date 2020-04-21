package com.consultoraestrategia.ss_crmeducativo.entities;

import com.consultoraestrategia.ss_crmeducativo.lib.AppDatabase;
import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;

@Table(database = AppDatabase.class)
public class ParametroConfiguracion extends BaseModel {

public static final int O1=1, O2=2, O3=3, O4=4;
public static final String CONCEPTO= "Situación Unidad";

 @PrimaryKey
 public int id;
 @Column
 public String concepto;
 @Column
 public String parametro;
 @Column
 public int entidadId;
 @Column
 public int orden;



 public int getId() {
  return id;
 }

 public void setId(int id) {
  this.id = id;
 }

 public String getConcepto() {
  return concepto;
 }

 public void setConcepto(String concepto) {
  this.concepto = concepto;
 }

 public String getParametro() {
  return parametro;
 }

 public void setParametro(String parametro) {
  this.parametro = parametro;
 }

 public int getEntidadId() {
  return entidadId;
 }

 public void setEntidadId(int entidadId) {
  this.entidadId = entidadId;
 }

 public int getOrden() {
  return orden;
 }

 public void setOrden(int orden) {
  this.orden = orden;
 }

 @Override
 public String toString() {
  return "ParametroConfiguracion{" +
          "id=" + id +
          ", concepto='" + concepto + '\'' +
          ", parametro='" + parametro + '\'' +
          ", entidadId=" + entidadId +
          ", orden=" + orden +
          '}';
 }
}
