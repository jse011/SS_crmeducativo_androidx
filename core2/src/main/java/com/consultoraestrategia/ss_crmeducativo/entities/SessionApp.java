package com.consultoraestrategia.ss_crmeducativo.entities;

import com.consultoraestrategia.ss_crmeducativo.lib.AppDatabase;
import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;

/**
 * Created by irvinmarin on 26/09/2017.
 */
@Table(database = AppDatabase.class)
public class SessionApp extends BaseModel {

    @PrimaryKey(autoincrement = true)
    private int idSession;

    @Column
    private String userName;
    @Column
    private String userPassword;
    @Column
    private boolean importoDatos;

    public SessionApp() {
    }

    public SessionApp(int idSession, String userName, String userPassword, boolean importoDatos) {
        this.idSession = idSession;
        this.userName = userName;
        this.userPassword = userPassword;
        this.importoDatos = importoDatos;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public boolean isImportoDatos() {
        return importoDatos;
    }

    public void setImportoDatos(boolean importoDatos) {
        this.importoDatos = importoDatos;
    }

    public int getIdSession() {
        return idSession;
    }

    public void setIdSession(int idSession) {
        this.idSession = idSession;
    }

    @Override
    public String toString() {
        return "SessionApp{" +
                "idSession=" + idSession +
                ", userName='" + userName + '\'' +
                ", userPassword='" + userPassword + '\'' +
                ", importoDatos=" + importoDatos +
                '}';
    }
}
