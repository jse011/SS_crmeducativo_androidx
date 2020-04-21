package com.consultoraestrategia.ss_crmeducativo.services.uploadFile;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.consultoraestrategia.ss_crmeducativo.R;
import com.consultoraestrategia.ss_crmeducativo.services.uploadFile.entities.RecursoUploadFile;

import java.util.ArrayList;
import java.util.List;

public class UpLoadAdapter extends RecyclerView.Adapter<UpLoadAdapter.DialogUpLoadFileHolder> {


    private List<RecursoUploadFile> recursoUploadFiles = new ArrayList<>();

    public UpLoadAdapter(List<RecursoUploadFile> recursoUploadFiles) {
        this.recursoUploadFiles.addAll(recursoUploadFiles);
    }

    @NonNull
    @Override
    public DialogUpLoadFileHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new DialogUpLoadFileHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_dialog_uploadfile, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull DialogUpLoadFileHolder holder, int position) {
        holder.bind(recursoUploadFiles.get(position));
    }

    @Override
    public int getItemCount() {
        return recursoUploadFiles.size();
    }

    public void setList(List<RecursoUploadFile> recursoUploadFiles) {
        this.recursoUploadFiles.clear();
        this.recursoUploadFiles.addAll(recursoUploadFiles);
        Log.d(getClass().getSimpleName(),"size: " + this.recursoUploadFiles.size());
        notifyDataSetChanged();
    }

    public class DialogUpLoadFileHolder extends RecyclerView.ViewHolder{

        TextView txtFile;

        public DialogUpLoadFileHolder(View itemView) {
            super(itemView);
            txtFile = itemView.findViewById(R.id.txt_file);
        }

        public void bind(RecursoUploadFile recursoUploadFile) {
            txtFile.setText(recursoUploadFile.getNombre());
        }
    }





}
