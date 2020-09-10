package com.consultoraestrategia.ss_crmeducativo.login2.service2.Notify

import android.content.Context
import com.consultoraestrategia.ss_crmeducativo.R
import io.karn.notify.Notify
import java.util.*

class NotifyImpl {
     companion object{
         val channelProgressKey = "servicio_general_progress_notifications"

         fun EvaluacionesActualizada(context: Context, arrayList: ArrayList<String>) {
             Notify
                     .with(context)
                     .header {
                         icon = if (context.resources.getString(R.string.app_name) == "Educar Teacher") R.mipmap.ic_educar else R.mipmap.ic_launcher_v2
                     }.asTextList { // this: Payload.Content.TextList
                         // The lines that are shown when the notification is expanded.
                         if(arrayList.size>1)
                             lines = arrayList
                         // The title of the collapsed notification.
                         title = "Actualización de sus evaluaciones!"
                         // The second line of the collapsed notification.
                        if(arrayList.size>1)
                            text = lines.size.toString() + " rúbricas o rubros actualizados."
                         else if(arrayList.size==1)
                            text = arrayList[0] + " sé ha actualizado"

                     }
                     .show()
         }

         fun Sesioneshechas(context: Context, arrayList: ArrayList<String>) {
             Notify
                     .with(context)
                     .header {
                         icon = if (context.resources.getString(R.string.app_name) == "Educar Teacher") R.mipmap.ic_educar else R.mipmap.ic_launcher_v2
                     }.asTextList { // this: Payload.Content.TextList
                         // The lines that are shown when the notification is expanded.
                         if(arrayList.size>1)
                             lines = arrayList
                         // The title of the collapsed notification.
                         title = "Actualización de sus sesiones a hecho!"
                         // The second line of the collapsed notification.
                         if(arrayList.size>1)
                             text = lines.size.toString() + " sesiones marcadas como hecho"
                         else if(arrayList.size==1)
                             text = arrayList[0] + " sé ha marcado como hecho"

                     }
                     .show()
         }

         fun ServiceProgressFB(context: Context):Int{
            return Notify
                     .with(context)
                    .header {

                        icon = if (context.resources.getString(R.string.app_name) == "Educar Teacher") R.mipmap.ic_educar else R.mipmap.ic_launcher_v2
                    }
                    .alerting(channelProgressKey,{
                        channelImportance = Notify.IMPORTANCE_LOW
                    })
                     .asBigText  {
                         title = "Actualización en curso!"
                         expandedText = "Entorno virtual de aprendizaje"
                         bigText = "Se a detectado cambios en sus evaluaciones"
                     }
                     .progress {
                         showProgress = true
                         //For determinate progress
                        //enablePercentage = true
                         //progressPercent = 0
                     }.show()


         }

         fun ServiceProgress(context: Context):Int{
             return Notify
                     .with(context)
                     .header {

                         icon = if (context.resources.getString(R.string.app_name) == "Educar Teacher") R.mipmap.ic_educar else R.mipmap.ic_launcher_v2
                     }
                     .alerting(channelProgressKey,{
                         channelImportance = Notify.IMPORTANCE_LOW
                     })
                     .asBigText  {
                         title = "Enviado sus cambios!"
                         expandedText = "Servicio general"
                         bigText = "Guardando sus cambios en nuestros servidores"
                     }
                     .progress {
                         showProgress = true
                         //For determinate progress
                         //enablePercentage = true
                         //progressPercent = 0
                     }.show()


         }

         fun Cancel(indentificador: Int, context: Context){
             Notify.cancelNotification(context, indentificador)
         }
     }
}