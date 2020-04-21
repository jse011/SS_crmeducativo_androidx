package com.consultoraestrategia.ss_crmeducativo.createTeam.entities;

import com.consultoraestrategia.ss_crmeducativo.grouplist.entities.Group;
import com.consultoraestrategia.ss_crmeducativo.grouplist.entities.Team;

import org.parceler.Parcel;

import java.util.List;

/**
 * Created by @stevecampos on 6/04/2018.
 */
@Parcel
public class CreateTeamAccionListWrapper {
    Team item;
    Group group;
    public CreateTeamAccionListWrapper() {
    }

    public CreateTeamAccionListWrapper(Team item) {
        this.item = item;
    }

    public CreateTeamAccionListWrapper(Team item, Group group) {
        this.item = item;
        this.group = group;
    }

    public Team getItem() {
        return item;
    }

    public void setItem(Team item) {
        this.item = item;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }
}
