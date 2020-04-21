package com.consultoraestrategia.ss_crmeducativo.createTeam;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import androidx.annotation.StringRes;
import android.text.TextUtils;
import android.util.Log;

import com.consultoraestrategia.ss_crmeducativo.R;
import com.consultoraestrategia.ss_crmeducativo.base.UseCase;
import com.consultoraestrategia.ss_crmeducativo.base.UseCaseHandler;
import com.consultoraestrategia.ss_crmeducativo.createTeam.domain.usecase.CreateTeam;
import com.consultoraestrategia.ss_crmeducativo.createTeam.domain.usecase.GetDimension;
import com.consultoraestrategia.ss_crmeducativo.createTeam.domain.usecase.GetPersonList;
import com.consultoraestrategia.ss_crmeducativo.createTeam.domain.usecase.GetTeam;
import com.consultoraestrategia.ss_crmeducativo.createTeam.entities.CreateTeamAccionListWrapper;
import com.consultoraestrategia.ss_crmeducativo.createTeam.entities.DimensionUi;
import com.consultoraestrategia.ss_crmeducativo.createTeam.entities.Person;
import com.consultoraestrategia.ss_crmeducativo.createTeam.ui.CreateTeamActivity;
import com.consultoraestrategia.ss_crmeducativo.createTeamList.ui.CreateTeamListActivity;
import com.consultoraestrategia.ss_crmeducativo.grouplist.entities.Group;
import com.consultoraestrategia.ss_crmeducativo.grouplist.entities.Team;
import com.consultoraestrategia.ss_crmeducativo.util.ImageCompression;
import com.consultoraestrategia.ss_crmeducativo.util.MediaFile;
import com.theartofdev.edmodo.cropper.CropImage;

import org.parceler.Parcels;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by @stevecampos on 18/09/2017.
 */

public class CreateTeamPresenterImpl implements CreateTeamPresenter {
    private static final String TAG = CreateTeamPresenterImpl.class.getSimpleName();

    private CreateTeamView view;

    private UseCaseHandler useCaseHandler;
    private GetPersonList getPersonList;
    private GetTeam getTeam;
    private CreateTeam createTeam;
    private File cacheDir;
    private ContentResolver contentResolver;
    private Team team;
    private List<Person> personasUis;
    private Group createGroup;
    private boolean filtraUsuarioDeseleccionados;
    private GetDimension getDimension;
    private DimensionUi dimensionUiseleted;
    private List<DimensionUi>dimensionUiList;

    public CreateTeamPresenterImpl(UseCaseHandler useCaseHandler, GetTeam getTeam, GetPersonList getPersonList, CreateTeam createTeam, File cacheDir, ContentResolver contentResolver, GetDimension getDimension) {
        this.useCaseHandler = useCaseHandler;
        this.getTeam = getTeam;
        this.getPersonList = getPersonList;
        this.createTeam = createTeam;
        this.cacheDir = cacheDir;
        this.contentResolver = contentResolver;
        this.getDimension=getDimension;
    }

    @Override
    public void attachView(CreateTeamView view) {
        Log.d(TAG, "attachView");
        this.view = view;
    }

    @Override
    public void onCreate() {
        Log.d(TAG, "onCreate");
        getPersonList();
    }


    private Team mTeam;

    private void getTeam(String equipoId, int entidadId, int georeferenciaId) {
        Log.d(TAG, "getTeam: " + equipoId);
        useCaseHandler.execute(
                getTeam,
                new GetTeam.RequestValues(equipoId, entidadId, georeferenciaId),
                new UseCase.UseCaseCallback<GetTeam.ResponseValue>() {
                    @Override
                    public void onSuccess(GetTeam.ResponseValue response) {
                        Log.d(TAG, "onSuccess: ");
                        Team team = response.getTeam();
                        if (team != null) {
                            mTeam = team;
                            showTeam(mTeam);
                        }
                    }

                    @Override
                    public void onError() {

                    }
                }
        );
    }

    private void showTeamName(String name) {
        if (view != null) {
            view.showName(name);
        }
    }

    private void showTeam(Team mTeam) {
        try {
        Log.d(TAG, "showTeam: " + mTeam);
        String teamName = mTeam.getName();
        String urlImg = mTeam.getUrlImage();
        showTeamName(teamName);
        if (!TextUtils.isEmpty(urlImg)) {
            showPicture(Uri.parse(urlImg));
        }

        List<Person> personList = mTeam.getPersonList();
        addPersonList(personList);
        for (Person person : personList) {
            onPersonSelected(person);
        }


            Group group = mTeam.getGroup();
            for (Team team : group.getTeams()){
                if(team.equals(mTeam)) continue;
                for (Person person: team.getPersonList()){
                    if(view!=null)view.deletePerson(person);
                }
            }

        }catch (Exception e){
            Log.d(TAG, "deletePerson Error: "+ e);
        }
    }

    @Override
    public void onStart() {
        Log.d(TAG, "onStart");
    }

    @Override
    public void onResume() {
        Log.d(TAG, "onResume");
        restoreSelectedMembers();
        restoreSelectedImage();
    }

    private void restoreSelectedMembers() {
        List<Person> members = new ArrayList<>(membersMap.values());
        for (Person person :
                members) {
            selectPersonUi(person);
        }
    }

    @Override
    public void onPause() {
        Log.d(TAG, "onPause");
    }

    @Override
    public void onStop() {
        Log.d(TAG, "onStop");
    }

    @Override
    public void onDestroy() {
        Log.d(TAG, "onDestroy");
        view = null;
    }

    @Override
    public void onBackPressed() {
        Log.d(TAG, "onBackPressed");
        if (view != null) {
            view.finishActivity();
        }
    }

    private void getPersonList() {
        useCaseHandler.execute(
                getPersonList,
                new GetPersonList.RequestValues(cargaCursoId, grupoEquipoId, entidadId, georeferenciaId),
                new UseCase.UseCaseCallback<GetPersonList.ResponseValue>() {
                    @Override
                    public void onSuccess(GetPersonList.ResponseValue response) {
                        personasUis=response.getPersonList();
                        addPersonList(response.getPersonList());
                        if(team != null){
                            mTeam = team;
                            showTeam(mTeam);
                        }else {
                            if (!TextUtils.isEmpty(equipoId)) getTeam(equipoId, entidadId, georeferenciaId);
                        }

                        try {
                            Log.d(TAG , "CREATE GROUIP "+ createGroup.getTeams().size());
                            Group group = createGroup;
                            for (Team team : group.getTeams()){
                                if(team.equals(mTeam)) continue;
                                for (Person person: team.getPersonList()){
                                    if(view!=null)view.deletePerson(person);
                                }
                            }
                        }catch (Exception e){

                        }


                    }

                    @Override
                    public void onError() {
                        showError(R.string.unknown_error);
                    }
                }

        );
    }

    private void addPersonList(List<Person> personList) {
        if (view != null) {
            if(personList.isEmpty()){
                view.showMsjListEmpty("No hay Alumnos");
            }else {
                view.hideMsjListEmpty();
            }
            view.addPersonList(personList);
        }
    }


    private void showError(@StringRes int error) {
        if (view != null) {
            view.showError(error);
        }
    }

    private String cargaCursoId;
    private String grupoEquipoId;
    private String equipoId;
    private int orden;
    private int entidadId;
    private int georeferenciaId;

    @Override
    public void setExtras(Bundle extras) {
        cargaCursoId = extras.getString(CreateTeamListActivity.EXTRA_CARGA_CURSO_ID);
        grupoEquipoId = extras.getString(CreateTeamActivity.EXTRA_GRUPO_ID);
        orden = extras.getInt(CreateTeamActivity.EXTRA_ORDEN);
        entidadId = extras.getInt(CreateTeamActivity.EXTRA_ENTIDAD_ID, 0);
        georeferenciaId = extras.getInt(CreateTeamActivity.EXTRA_GEOREFENCIA_ID, 0);
        CreateTeamAccionListWrapper wrapper = Parcels.unwrap(extras.getParcelable(CreateTeamActivity.EXTRA_TEAM));
        team = wrapper.getItem();
        if(team!=null) equipoId = team.getId();
        createGroup = wrapper.getGroup();
        Log.d(TAG, "grupoid "+grupoEquipoId);
        if(team!=null){

            Log.d(TAG, "team setextrs "+ team.getId());
            Log.d(TAG, "equipoId "+equipoId);
        }
    }

    @Override
    public void onPersonSelected(Person person) {
        addPersonToMap(person);
        selectPersonUi(person);
    }

    private void selectPersonUi(Person person) {
        if (view != null) {
            person.setMember(true);
            view.showMemberRecycler();
            view.updatePerson(person);
            view.addMember(person);
        }
    }

    private void updateMemberRecyclerVisibility() {
        if (view != null) {
            int countMembers = view.getMemberCount();
            if (countMembers == 0) {
                view.hideMemberRecycler();
            } else {
                view.showMemberRecycler();
            }
        }
    }

    @Override
    public void onPersonUnselected(Person person) {
        removePersonFromMap(person);
        if (view != null) {
            person.setMember(false);
            view.updatePerson(person);
            view.removeMember(person);
            updateMemberRecyclerVisibility();
        }
    }

    private Map<String, Person> membersMap = new HashMap<>();

    private void addPersonToMap(Person person) {
        if (person != null) {
            String id = person.getId();
            boolean containsKey = membersMap.containsKey(id);
            if (!containsKey) {
                membersMap.put(person.getId(), person);
            }
        }
    }

    private void removePersonFromMap(Person person) {
        if (person != null) {
            membersMap.remove(person.getId());
        }
    }

    private Uri selectedImageUri = Uri.EMPTY;

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.d(TAG, "onActivityResult: " + requestCode + ", resultCode: " + resultCode + ", data: " + data);
        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            if (resultCode == Activity.RESULT_OK) {
                Log.d(TAG, "uri: " + result.getUri());
                selectedImageUri = result.getUri();
                showPicture(selectedImageUri);
                compressImage(result.getUri());
            } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
                showMessage(R.string.unknown_error);
            }
            //return;
        }
    }

    private Team getTeamUpdated() {
        if (mTeam == null) {
            mTeam = new Team();
        }

        if (!TextUtils.isEmpty(grupoEquipoId)) {
            mTeam.setGroupId(grupoEquipoId);
        }

        if (!TextUtils.isEmpty(equipoId)) {
            mTeam.setId(equipoId);
        }

        if (!TextUtils.isEmpty(name)) {
            mTeam.setName(name);
        }
        if (!TextUtils.isEmpty(compressedImageUri.toString())) {
            mTeam.setUrlImage(compressedImageUri.toString());
        }

        mTeam.setOrden(orden);
        mTeam.setPersonList(new ArrayList<>(membersMap.values()));

        return mTeam;

    }

    private String name;

    @Override
    public void createTeam(String name) {
        if (!isValidTeamName(name)) {
            showMessage(R.string.create_team_msg_invalidad_name);
            return;
        }

        if (membersMap.size() == 0) {
            showMessage(R.string.create_team_msg_invalid_members_count);
            return;
        }


        this.name = name;
        /*
        if (compressedImageUri.equals(Uri.EMPTY)){
            showMessage(R.string.create_team_msg_invalidad_selected_image);
            return;
        }*/

        Team team = getTeamUpdated();
        if(view!=null)view.devolverResultado(team);

        /*
        useCaseHandler.execute(
                createTeam,
                new CreateTeam.RequestValues(team),
                new UseCase.UseCaseCallback<CreateTeam.ResponseValue>() {
                    @Override
                    public void onSuccess(CreateTeam.ResponseValue response) {
                        boolean success = response.isSucess();
                        if (success) {
                            onSuccessCreatedGroup();
                        } else {
                            showError(R.string.unknown_error);
                        }
                    }

                    @Override
                    public void onError() {
                        showError(R.string.unknown_error);
                    }
                }
        );*/
    }

    @Override
    public void onClickFiltro() {
        if(!filtraUsuarioDeseleccionados){
            filtraUsuarioDeseleccionados = true;
            siFiltraUsuarioDeseleccionados();
        }else {
            filtraUsuarioDeseleccionados = false;
            noFiltraUsuarioDeseleccionados();
        }
    }

    @Override
    public void onClickInfoPersona(Person person) {
        if(view!=null)view.showinfoUsuario(person);
    }

    @Override
    public void onItemSelectedEstiloAprendizaje(Object singleItem, int selectedPosition) {
        if (view == null)return;
        List<Person>people;
        if(mTeam!=null)people=mTeam.getPersonList();
        else people= personasUis;

        if(singleItem instanceof DimensionUi){
            DimensionUi dimensionUiseleted= (DimensionUi)singleItem;
            this.dimensionUiseleted= dimensionUiseleted;
            view.filter(dimensionUiseleted);
        }
        if(singleItem instanceof String){
            this.dimensionUiseleted=null;
            view.addPersonList(people);
        }
    }

    @Override
    public void showDialogEstilosAprendizaje() {
        int position=0;
        List<Object>objectList= new ArrayList<>();
        objectList.add("Sin filtro");
        GetDimension.Response response= getDimension.execute();
        dimensionUiList= response.getDimensionUiList();
        objectList.addAll(dimensionUiList);
        for(DimensionUi dimensionUi:dimensionUiList)
            if(dimensionUiseleted!=null)if(dimensionUi.getId()==dimensionUiseleted.getId())position= dimensionUiList.indexOf(dimensionUi)+1;
        if(view!=null)view.showListSingleChooserEstilosAprendizaje("Seleccione Estilo Aprendizaje", objectList, position);

    }

    private void siFiltraUsuarioDeseleccionados(){
        if(view == null)return;
        view.siFiltraUsuarioDeseleccionados();
        view.cambiarColorFiltorDeseleccionado(R.color.md_orange_500);
    }

    private void noFiltraUsuarioDeseleccionados(){
        if(view == null) return;
        view.noFiltraUsuarioDeseleccionados();
        view.cambiarColorFiltorDeseleccionado(R.color.md_red_500);
    }

    private boolean isValidTeamName(String name) {
        return !TextUtils.isEmpty(name);
    }

    private void onSuccessCreatedGroup() {
        onBackPressed();
    }

    private void showPicture(Uri uri) {
        if (view != null) {
            view.showPicture(uri);
        }
    }

    private void showMessage(@StringRes int message) {
        if (view != null) {
            view.shoMessage(message);
        }
    }

    private Uri compressedImageUri = Uri.EMPTY;

    private void compressImage(Uri imageUri) {
        ImageCompression imageCompression = new ImageCompression(cacheDir, contentResolver) {
            @Override
            protected void onPostExecute(MediaFile mediaFile) {
                Log.d(TAG, "compressedImageUri path: " + compressedImageUri);
                // image here is compressed & ready to be sent to the server
                compressedImageUri = mediaFile.getLocalUri();
                //updateUserPhoto(compressedImageUri);
            }
        };
        imageCompression.execute(imageUri);// imagePath as a string
    }

    private void restoreSelectedImage() {
        showPicture(selectedImageUri);
    }

}
