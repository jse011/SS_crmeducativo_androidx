package com.consultoraestrategia.ss_crmeducativo.grouplist.entities;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by @stevecampos on 18/09/2017.
 */

public class User {
    private String id;
    private String name;
    private String urlPicture;

    public User() {
    }

    public User(String id, String name, String urlPicture) {
        this.id = id;
        this.name = name;
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

    public String getUrlPicture() {
        return urlPicture;
    }

    public void setUrlPicture(String urlPicture) {
        this.urlPicture = urlPicture;
    }

}
