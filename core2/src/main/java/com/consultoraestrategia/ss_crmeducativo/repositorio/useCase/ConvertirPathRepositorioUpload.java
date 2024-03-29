package com.consultoraestrategia.ss_crmeducativo.repositorio.useCase;

import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;

import com.consultoraestrategia.ss_crmeducativo.base.UseCaseSincrono;
import com.consultoraestrategia.ss_crmeducativo.repositorio.entities.RepositorioTipoFileU;
import com.consultoraestrategia.ss_crmeducativo.repositorio.entities.UpdateRepositorioFileUi;
import com.consultoraestrategia.ss_crmeducativo.util.IdGenerator;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ConvertirPathRepositorioUpload extends UseCaseSincrono<Map<Uri,String>, List<UpdateRepositorioFileUi>> {
    private static final String TAG = ConvertirPathRepositorioUpload.class.getSimpleName();

    @Override
    public void execute(Map<Uri,String> response, Callback<List<UpdateRepositorioFileUi>> callback) {
        List<UpdateRepositorioFileUi> updateRepositorioFileUis = new ArrayList<>();

        for (Map.Entry<Uri, String> entry : response.entrySet()) {
            String fileName = entry.getValue();

            String extencion = "";
            int i = fileName.lastIndexOf('.');
            int p = Math.max(fileName.lastIndexOf('/'), fileName.lastIndexOf('\\'));
            String file = fileName.substring(p + 1);
            if (i > p) {
                extencion = fileName.substring(i+1);
            }

            // Remove the extension.
            file = IdGenerator.generateId() +"."+extencion;

            UpdateRepositorioFileUi recursoUploadFile = new UpdateRepositorioFileUi();

            if(!TextUtils.isEmpty(file)){
                Log.d(TAG, extencion);
                extencion = "." + extencion.toLowerCase();
                if (extencion.contains(".doc") || extencion.contains(".docx")) {
                    // Word document
                    recursoUploadFile.setTipoFileU(RepositorioTipoFileU.DOCUMENTO);
                } else if (extencion.contains(".pdf")) {
                    // PDF file
                    recursoUploadFile.setTipoFileU(RepositorioTipoFileU.PDF);
                } else if (extencion.contains(".ppt") || extencion.contains(".pptx")) {
                    // Powerpoint file
                    recursoUploadFile.setTipoFileU(RepositorioTipoFileU.DIAPOSITIVA);
                } else if (extencion.contains(".xls") || extencion.contains(".xlsx") || extencion.contains(".csv")) {
                    // Excel file
                    recursoUploadFile.setTipoFileU(RepositorioTipoFileU.HOJA_CALCULO);
                } else if (extencion.contains(".zip") || extencion.contains(".rar")) {
                    // WAV audio file
                    recursoUploadFile.setTipoFileU(RepositorioTipoFileU.COMPRESS);
                } else if (extencion.contains(".rtf")) {
                    // RTF file
                    recursoUploadFile.setTipoFileU(RepositorioTipoFileU.DOCUMENTO);
                } else if (extencion.contains(".wav") || extencion.contains(".mp3")) {
                    // WAV audio file
                    recursoUploadFile.setTipoFileU(RepositorioTipoFileU.AUDIO);
                } else if (extencion.contains(".gif")) {
                    // GIF file
                    recursoUploadFile.setTipoFileU(RepositorioTipoFileU.IMAGEN);
                } else if (extencion.contains(".jpg") || extencion.contains(".jpeg") || extencion.contains(".png")) {
                    // JPG file
                    recursoUploadFile.setTipoFileU(RepositorioTipoFileU.IMAGEN);
                } else if (extencion.contains(".txt")) {
                    // Text file
                    recursoUploadFile.setTipoFileU(RepositorioTipoFileU.DOCUMENTO);
                } else if (extencion.contains(".3gp") || extencion.contains(".mpg") || extencion.contains(".mpeg") || extencion.contains(".mpe") || extencion.contains(".mp4") || extencion.contains(".avi")) {
                    // Video files
                    recursoUploadFile.setTipoFileU(RepositorioTipoFileU.VIDEO);
                } else {
                    recursoUploadFile.setTipoFileU(RepositorioTipoFileU.MATERIALES);
                    // Other files
                    //intent.setDataAndType(uri, "*/*");
                }
            }

            recursoUploadFile.setArchivoId(IdGenerator.generateId());
            recursoUploadFile.setNombreArchivo(file);
            recursoUploadFile.setNombreRecurso(file);
            recursoUploadFile.setExtencionArchivoId(file);
            recursoUploadFile.setUri(entry.getKey());
            updateRepositorioFileUis.add(recursoUploadFile);
        }
        callback.onResponse(true, updateRepositorioFileUis);
    }





}
