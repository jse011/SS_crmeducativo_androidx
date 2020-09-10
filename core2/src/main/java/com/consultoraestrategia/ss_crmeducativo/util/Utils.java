package com.consultoraestrategia.ss_crmeducativo.util;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import androidx.annotation.DrawableRes;
import androidx.appcompat.app.AlertDialog;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;

import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.consultoraestrategia.ss_crmeducativo.api.RestAPI;
import com.consultoraestrategia.ss_crmeducativo.core2.R;
import com.consultoraestrategia.ss_crmeducativo.entities.EvaluacionResultadoC;
import com.consultoraestrategia.ss_crmeducativo.entities.EvaluacionResultadoC_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.GlobalSettings;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.Phonenumber;
import com.raizlabs.android.dbflow.config.FlowManager;
import com.raizlabs.android.dbflow.sql.language.NameAlias;
import com.raizlabs.android.dbflow.sql.language.SQLite;
import com.raizlabs.android.dbflow.sql.language.property.IProperty;
import com.raizlabs.android.dbflow.sql.language.property.Property;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.Normalizer;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;

/**
 * Created by irvinmarin on 13/04/2017.
 */

public class Utils {

    private static final String TAG = Utils.class.getSimpleName();

    public static boolean isLinkYoutube(String txtUrlVideo) {
        Log.d(TAG,"txtUrlVideo: " + txtUrlVideo);
        String[] linkYoutube = {"youtube.com", "youtu.be"};
        if(txtUrlVideo.contains(linkYoutube[0])||txtUrlVideo.contains(linkYoutube[1])){
            return true;
        }else {
            return false;
        }
    }

    public static boolean isOnlineNet() {
        try {
            Process p = Runtime.getRuntime().exec("ping -c 1 ss.consultoraestrategia.com");
            int val = p.waitFor();
            return (val == 0);
        } catch (Exception e) {
            Log.e("isOnlineNet", e.toString());
        }
        return false;
    }

    public static boolean checkServerConnection() {
        try {
            String serverUrl = GlobalSettings.getServerUrl();
            if (TextUtils.isEmpty(serverUrl)) {
                serverUrl = RestAPI.REMOTE_URL;
            }
            String startString = "http://";
            String endString = "/";
            if (TextUtils.isEmpty(serverUrl)) return false;

            int prefixLength = startString.length();
            String domain = serverUrl.substring(prefixLength, serverUrl.indexOf(endString, prefixLength + 1));
            Log.d(TAG, "domain: " + domain);

            Process p = Runtime.getRuntime().exec("ping -c 1 " + domain);
            int val = p.waitFor();
            Log.d(TAG, "val: " + val);
            return (val == 0);
        } catch (Exception e) {
            Log.e("isOnlineNet", e.toString());
        }
        return false;
    }

    public static boolean isOnlineInterNet() {

        try {
            Process p = Runtime.getRuntime().exec("ping -c 1 www.google.com");

            int val = p.waitFor();
            boolean reachable = (val == 0);
            return reachable;

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return false;
    }

    public static boolean isSuccess(JSONObject jsonObject) {
        boolean estado;
        try {
            estado = jsonObject.getBoolean("Successful");
        } catch (JSONException e) {
            e.printStackTrace();
            estado = false;
        }
        return estado;
    }

    public static String getJsonObResultArray(JSONObject jsonObject) {
        String s = "";
        try {
            s = jsonObject.getJSONArray("Value").toString();

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return s;
    }

    public static IProperty[] f_allcolumnTable(IProperty... ALL_COLUMN_PROPERTIES) {
        for (int i = 0; i < ALL_COLUMN_PROPERTIES.length; i++) {
            ALL_COLUMN_PROPERTIES[i] = ALL_COLUMN_PROPERTIES[i].withTable();
        }
        return ALL_COLUMN_PROPERTIES;
    }


    public static IProperty[] f_allcolumnTable(IProperty[] ALL_COLUMN_PROPERTIES, IProperty... ALL_SECOND_COLUMN_PROPERTIES) {

        for (int i = 0; i < ALL_COLUMN_PROPERTIES.length; i++) {
            ALL_COLUMN_PROPERTIES[i] = ALL_COLUMN_PROPERTIES[i].withTable();
        }

        for (int i = 0; i < ALL_SECOND_COLUMN_PROPERTIES.length; i++) {
            ALL_SECOND_COLUMN_PROPERTIES[i] = ALL_SECOND_COLUMN_PROPERTIES[i];
        }

        int lengA = ALL_COLUMN_PROPERTIES.length;
        int lengB = ALL_SECOND_COLUMN_PROPERTIES.length;

        IProperty[] iProperties = new IProperty[lengA + lengB];
        System.arraycopy(ALL_COLUMN_PROPERTIES, 0, iProperties, 0, lengA);
        System.arraycopy(ALL_SECOND_COLUMN_PROPERTIES, 0, iProperties, lengA, lengB);


        return iProperties;
    }

    public static String getDateChat(Date date){

        String dateConvert= Utils.f_fecha_letras(date.getTime()).toUpperCase();

        Calendar calendarToday=Calendar.getInstance();
        int dayToday= calendarToday.get(Calendar.DAY_OF_MONTH);
        int monthToday= calendarToday.get(Calendar.MONTH);
        int yearToday= calendarToday.get(Calendar.YEAR);


        calendarToday.setTime(date);
        int day= calendarToday.get(Calendar.DAY_OF_MONTH);
        int month= calendarToday.get(Calendar.MONTH);
        int year= calendarToday.get(Calendar.YEAR);

        if(year==yearToday&&month==monthToday &&day==dayToday)dateConvert="HOY";
        return dateConvert;
    }

    public static String f_fecha_letras(String vstr_Start) {
        String mstr_fecha = "";
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
        try {

            String[] vobj_days = {"Domingo", "Lunes", "Martes", "Miércoles", "Jueves", "Viernes", "Sábado"};
            String[] vobj_Meses = {"Ene.", "Feb.", "Mar.", "Abr.", "May.", "Jun.", "Jul.", "Ago.", "Sept.", "Oct.", "Nov.", "Dic."};

            Date date = format.parse(vstr_Start);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            int year = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH); // Jan = 0, dec = 11
            int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
            int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);

            mstr_fecha = vobj_days[dayOfWeek - 1] + " " + dayOfMonth + " de " + vobj_Meses[month];
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return mstr_fecha;
    }


    public static String f_fecha_letras(long timesTamp) {

        String mstr_fecha = "";

        String[] vobj_days = {"Dom", "Lun", "Mart", "Mié", "Jue", "Vie", "Sáb"};
        String[] vobj_Meses = {"Ene.", "Feb.", "Mar.", "Abr.", "May.", "Jun.", "Jul.", "Ago.", "Sept.", "Oct.", "Nov.", "Dic."};

        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(timesTamp);
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH); // Jan = 0, dec = 11
        int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
        mstr_fecha = vobj_days[dayOfWeek - 1] + " " + dayOfMonth + " de " + vobj_Meses[month];

        return mstr_fecha;
    }

    public static String f_dia_semana(long timesTamp) {

        String mstr_fecha = "";

        String[] vobj_days = {"domingo", "lunes", "martes", "miércoles", "jueves", "viernes", "sábado"};

        Date date = new Date(timesTamp);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
        mstr_fecha = vobj_days[dayOfWeek - 1];

        return mstr_fecha;
    }

    public static String f_fecha_letras_2(long timesTamp) {

        String mstr_fecha = "";

        String[] vobj_days = {"Dom", "Lun", "Mart", "Mié", "Jue", "Vie", "Sáb"};
        String[] vobj_Meses = {"Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"};

        Date date = new Date(timesTamp);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH); // Jan = 0, dec = 11
        int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
        mstr_fecha = vobj_days[dayOfWeek - 1] + ", " + dayOfMonth  + " "+ vobj_Meses[month] + " " + year;

        return mstr_fecha;
    }

    public static String f_fecha_letras_dos(long timesTamp) {

        String mstr_fecha = "";

        String[] vobj_Meses = {"Ene.", "Feb.", "Mar.", "Abr.", "May.", "Jun.", "Jul.", "Ago.", "Sept.", "Oct.", "Nov.", "Dic."};

        Date date = new Date(timesTamp);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH); // Jan = 0, dec = 11
        int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
        mstr_fecha = dayOfMonth + " " + vobj_Meses[month] + ", "+ year;

        return mstr_fecha;
    }


    public static Date convertirfecha(String dateInString){
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy", Locale.US);
        try {
            return formatter.parse(dateInString);
        } catch (ParseException e) {
            e.printStackTrace();
            return new Date(0);
        }
    }

    public static String capitalize(String x) {
        StringBuilder result = new StringBuilder();
        if(TextUtils.isEmpty(x))return "";
        int count = 0;
        for (String ws : x.split(" ")){
            count++;
            if(count > 1) result.append(" ");
            if(ws.length()<2){
                result.append(ws);
            }else {
                result.append(ws.substring(0, 1).toUpperCase()).append(ws.substring(1).toLowerCase());
            }
        }

        return result.toString();
    }

    public static String formatPhoneNumber(TelephonyManager manager, String phoneNumber) {
        Log.d(TAG, "formatPhoneNumber: " + phoneNumber);
        String formatNumber = null;
        //TelephonyManager manager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        String usersCountryISOCode = manager.getNetworkCountryIso().toUpperCase();
//Log.d(TAG, "usersCountryISOCode: " + usersCountryISOCode);

        try {

            PhoneNumberUtil phoneUtil = PhoneNumberUtil.getInstance();

            Phonenumber.PhoneNumber numberProto = phoneUtil.parse(phoneNumber,
                    "PE");
            if (phoneUtil.isValidNumber(numberProto)) {
                formatNumber = phoneUtil.format(numberProto, PhoneNumberUtil.PhoneNumberFormat.E164);
                //Log.d(TAG, "formatPhoneNumber: " + phoneNumber + " -> " + formatNumber);
            }
        } catch (Exception e) {
            Log.d(TAG, "phoneNumber: " + phoneNumber + " Exception: " + e.getMessage());
            return null;
        }
        return formatNumber;
    }


    public static String limpiarAcentos(String cadena) {
        String limpio = null;
        if (cadena != null) {
            String valor = cadena;
            valor = valor.toUpperCase();
            // Normalizar texto para eliminar acentos, dieresis, cedillas y tildes
            limpio = Normalizer.normalize(valor, Normalizer.Form.NFD);
            // Quitar caracteres no ASCII excepto la enie, interrogacion que abre, exclamacion que abre, grados, U con dieresis.
            limpio = limpio.replaceAll("[^\\p{ASCII}(N\u0303)(n\u0303)(\u00A1)(\u00BF)(\u00B0)(U\u0308)(u\u0308)]", "");
            // Regresar a la forma compuesta, para poder comparar la enie con la tabla de valores
            limpio = Normalizer.normalize(limpio, Normalizer.Form.NFC);
        }
        return limpio;
    }

    public static String getFirstWord(String text) {
        if(TextUtils.isEmpty(text))return "";
        if (text.indexOf(' ') > -1) { // Check if there is more than one word.
            return text.substring(0, text.indexOf(' ')); // Extract first word.
        } else {
            return text; // Text is the first word itself.
        }
    }

    public static String getDatePhone() {
        Date now = new Date();
        Date alsoNow = Calendar.getInstance().getTime();
        String nowAsString = new SimpleDateFormat("dd/MM/yyyy").format(now);
        return nowAsString;
    }


    /**
     * a = valor minimo del origen
     * b = valor maximo del origen
     * x = valor a transformar
     * c = valor minimo transformado
     * d = valor maximo transformado
     */
    public static double transformacionInvariante(double a, double b, double x, double c, double d) {
        double t = (1 - ((b - x) / (b - a))) * (d - c);
        //Log.d(TAG, "notaTransformada: " + "1 - ((" + b + "-" + x + ")/(" + b + "-" + a + "))) * (" + d + " - " + c + ") = " + t);
        return t;
    }

    public static Double formatearDecimales(Double numero, Integer numeroDecimales) {
        return Math.round(numero * Math.pow(10, numeroDecimales)) / Math.pow(10, numeroDecimales);
    }

    public static double formulaConPeso(double a, double b, double x, double c, double d, double peso) {
        double T = transformacionInvariante(a, b, x, c, d);
        return T / 1 - peso;
    }

    public static double stdDev(double promedio,  List<Double> listaNotas) {
        double prom, sum = 0;
        int i;
        double n = listaNotas.size();
        prom = promedio;

        for (i = 0; i < n; i++){
            sum += Math.pow( listaNotas.get(i)- prom, 2);
        }

        return Math.sqrt(sum / (double) n);
    }

    private static RequestOptions getOptions() {
        return new RequestOptions();
    }

    public static RequestOptions getGlideRequestOptionsSimple() {
        return getOptions()
                .diskCacheStrategy(DiskCacheStrategy.ALL);
    }


    public static RequestOptions getGlideRequestOptions() {
        return getOptions()
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .error(R.drawable.ic_error_outline_black);
    }

    public static RequestOptions getGlideRequestOptions(@DrawableRes int res) {
        return getOptions()
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .error(res);
    }

    public static RequestOptions getGlideRequestOptions(Drawable drawable) {
        return getOptions()
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .error(drawable);
    }

    public static String getFechaDetalle(long fecha) {

        Date date = new Date(fecha);
        SimpleDateFormat formatterUI = new SimpleDateFormat("EEEE, d MMM ", Locale.getDefault());
        return formatterUI.format(date);
    }

    public static String getFechaDiaMesAnho(long fecha) {

        Date date = new Date(fecha);
        SimpleDateFormat formatterUI = new SimpleDateFormat("d MMM yyyy", Locale.getDefault());
        return formatterUI.format(date);
    }

    public static String tiempoFechaCreacion(long fecha){

        Calendar calendarActual = Calendar.getInstance();
        int anhoActual = calendarActual.get(Calendar.YEAR);
        int mesActual = calendarActual.get(Calendar.MONTH);
        int diaActual = calendarActual.get(Calendar.DAY_OF_MONTH);

        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(fecha);
        int anhio = calendar.get(Calendar.YEAR);
        int mes= calendar.get(Calendar.MONTH);
        int dia = calendar.get(Calendar.DAY_OF_MONTH);
        int hora = calendar.get(Calendar.HOUR_OF_DAY);
        int minuto = calendar.get(Calendar.MINUTE);

        calendarActual.set(Calendar.DAY_OF_YEAR,+1);

        int anioManiana = calendarActual.get(Calendar.YEAR);
        int mesManiana= calendarActual.get(Calendar.MONTH);
        int diaManiana = calendarActual.get(Calendar.DAY_OF_MONTH);

        if (anhio==anhoActual&&mesActual==mes&&dia==diaActual){
            if(hora==0&&minuto==0){
                return "para hoy";
            }else {
                return "para hoy a las " + changeTime12Hour(hora,minuto);
            }

        }else if (anhio==anioManiana&&mesManiana==mes&&dia==diaManiana) {
            if(hora==0&&minuto==0){
                return "para mañana";
            }else {
                return "para mañana a las " + changeTime12Hour(calendar.get(Calendar.HOUR_OF_DAY),calendar.get(Calendar.MINUTE));
            }

        }else if(anhio==anhoActual){
            if(hora==0&&minuto==0){
                return "para el "+Utils.f_fecha_letras(fecha);
            }else {
                return "para el "+Utils.f_fecha_letras(fecha) + " " + changeTime12Hour(calendar.get(Calendar.HOUR_OF_DAY),calendar.get(Calendar.MINUTE));
            }
        }else {
            if(hora==0&&minuto==0){
                return  "para el "+Utils.getFechaDiaMesAnho(fecha);
            }else {
                return  "para el "+Utils.getFechaDiaMesAnho(fecha) + " " + changeTime12Hour(calendar.get(Calendar.HOUR_OF_DAY),calendar.get(Calendar.MINUTE));
            }
        }

    }

    public static String getFechaHora(long fecha){

        Date date = new Date(fecha);
        SimpleDateFormat formatterUI = new SimpleDateFormat("hh:mm a", Locale.getDefault());
        return formatterUI.format(date);
    }

    public static String getHoraConFecha(long fecha){
        Calendar calendarHoy = Calendar.getInstance();
        calendarHoy.set(Calendar.MILLISECOND, 0);
        calendarHoy.set(Calendar.SECOND, 0);
        calendarHoy.set(Calendar.MINUTE, 0);
        calendarHoy.set(Calendar.HOUR, 0);
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(fecha);
        calendar.set(Calendar.MILLISECOND, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.HOUR, 0);

        if(calendarHoy.equals(calendar)){
            return getFechaHora(fecha);
        }else {
           return getFechaDetalle(fecha) + "" + getFechaHora(fecha);
        }


    }

    public static String getFechaDiaMes(long fecha){

        Date date = new Date(fecha);
        SimpleDateFormat formatterUI = new SimpleDateFormat("dd MMMM", Locale.getDefault());
        return formatterUI.format(date);
    }


    public static String getHoraformt24(Date date){
        Calendar calendar = GregorianCalendar.getInstance(); // creates a new calendar instance
        calendar.setTime(date);   // assigns calendar to given date
        calendar.get(Calendar.HOUR_OF_DAY); // gets hour in 24h format
        int horas = calendar.get(Calendar.HOUR);        // gets hour in 12h format
        int minutos = calendar.get(Calendar.MINUTE);
        calendar.get(Calendar.MONTH);       // gets month number, NOTE this is zero based!
        return horas + ":" + minutos;
    }

    public static Calendar getCalendar(Date date){
        Calendar calendar = GregorianCalendar.getInstance(); // creates a new calendar instance
        calendar.setTime(date);   // assigns calendar to given date
        return calendar;
    }

    public static String f_mediaDesviacionEstandar(int key) {
        String mediaDesviacionEstandar = "0.0(0.0)";
        List<Double>listNotas= new ArrayList<>();

        double media=0.0;
        List<EvaluacionResultadoC>evaluaciones= SQLite.select().from(EvaluacionResultadoC.class)
                .where(EvaluacionResultadoC_Table.rubroEvalResultadoId.eq(key)).queryList();
                for(EvaluacionResultadoC e: evaluaciones){
                    media+=e.getNota()/evaluaciones.size();
                    listNotas.add(e.getNota());
               }
        if(evaluaciones!=null){
            double stdDev = Utils.stdDev(media, listNotas);
            String avg = String.format("%.1f", media);
            String varianza = String.format("%.2f", stdDev);
            if (listNotas.size() == 0) varianza = "0";
            mediaDesviacionEstandar = (avg + "(" + varianza + ")");
        }


        return mediaDesviacionEstandar;
    }

    public static String getFechaDetalleSumado(int nroDiasSumadoDesdeHoy) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_YEAR, nroDiasSumadoDesdeHoy);  // numero de días a añadir, o restar en caso de días<0

        Date date = new Date(calendar.getTimeInMillis());
        SimpleDateFormat formatterUI = new SimpleDateFormat("EEEE, d MMM ", Locale.getDefault());
        return formatterUI.format(date);
    }



    public static double moda(Integer[] valores) {

        Integer resultado = null;
        Integer contador = 0;
        Integer contadorFinal = 0;


        for (int i = 0; i < valores.length; i++) {

            contador = 0;
            for (int j = 0; j < valores.length; j++) {
                    if (valores[i] ==valores[j])
                        contador++;
            }

            if (contador > contadorFinal) {
                resultado = valores[i];
                contadorFinal = contador;
            }


        }
        Log.d(TAG, "resultado : " + resultado + " repite total de vecves " + contadorFinal);
        return resultado;
    }

    public static IProperty withTableAs(IProperty property, String aliasName){
        NameAlias tableNameAlias = NameAlias.builder(FlowManager.getTableName(property.getTable())).build();
        NameAlias withTableNameAlias = NameAlias.builder(property.getNameAlias().name()).withTable(tableNameAlias.name()).build();
        return  new Property<>(property.getTable(), withTableNameAlias.getFullQuery(), aliasName);


    }


    public static String changeTime12Hour(int hr , int min){
        return  hr%12 + ":" + min + " " + ((hr>=12) ? "PM" : "AM");
    }

    public static String changeTime12Hour(String _24HourTime){
        String hora = null;
        try {
            SimpleDateFormat _24HourSDF = new SimpleDateFormat("HH:mm");
            SimpleDateFormat _12HourSDF = new SimpleDateFormat("hh:mm a");
            Date _24HourDt = _24HourSDF.parse(_24HourTime);
            hora = _12HourSDF.format(_24HourDt);
        }catch (Exception e){
            e.printStackTrace();

        }
        return  hora;
    }

    public static List<Integer> changeHourMinuto(String _24HourTime){
       List<Integer> arrayList = new ArrayList<>();
        try {
            String[] time = _24HourTime.split ( ":" );
            int hour = Integer.parseInt ( time[0].trim() );
            int min = Integer.parseInt ( time[1].trim() );
            arrayList.add(hour);
            arrayList.add(min);
        }catch (Exception e){
            arrayList.add(0);
            arrayList.add(0);
            e.printStackTrace();

        }
        return  arrayList;
    }



    public static void MsgConfirm(Activity activity, String titulo, String msg, DialogInterface.OnClickListener listenerSi, DialogInterface.OnClickListener listenerNo) {
        AlertDialog.Builder alert = new AlertDialog.Builder(activity);
        alert.setTitle(titulo);
        alert.setMessage(msg);
        alert.setPositiveButton("Si", listenerSi);
        alert.setNegativeButton("No", listenerNo);
        alert.show();
    }

    public static  <T> List<List<T>> chunkSizeList(List<T> list, int chunkSize) {
        if (chunkSize <= 0) {
            throw new IllegalArgumentException("Invalid chunk size: " + chunkSize);
        }
        List<List<T>> chunkList = new ArrayList<>(list.size() / chunkSize);
        for (int i = 0; i < list.size(); i += chunkSize) {
            chunkList.add(list.subList(i, i + chunkSize >= list.size() ? list.size() : i + chunkSize));
        }
        return chunkList;
    }


    public static  <T> List<List<T>> chunkList(List<T> list, int chunk) {
        if (chunk <= 0 || list.size() <  chunk) {
            throw new IllegalArgumentException("Invalid chunk size: " + chunk);
        }

        int chunkSize = list.size() / chunk;

        List<List<T>> result = new ArrayList<>();
        int salto = 0;
        for (int i = 0; i < chunk; i++) {
            result.add(list.subList(salto,salto + chunkSize >= list.size() ? list.size() : salto + chunkSize));
            salto = salto + chunkSize;
        }

        if(salto <  list.size()){
            int cantidaddfaltante = list.size() - salto;
            int posicion = 0;
            for (int i = 0; i < cantidaddfaltante; i ++) {
                if(posicion > list.size())posicion = 0;
                List<T> tList = new ArrayList<>(result.get(posicion));
                tList.add(list.get(salto + i));
                result.set(posicion, tList);
                posicion++;
            }
        }

        return result;
    }

    public static long transformarFecha_a_FechaAsistenciaSinHora(long fechaAsistencia) {
        Date date =  new Date(fechaAsistencia);
        Calendar cal = Calendar.getInstance(); // locale-specific
        cal.setTime(date);
        cal.set(Calendar.HOUR, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTimeInMillis();
    }
    public static List<Date> getDaysBetweenDates(Date startdate, Date enddate) {
        List<Date> dates = new ArrayList<Date>();
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(startdate);

        while (calendar.getTime().before(enddate)) {
            Date result = calendar.getTime();
            dates.add(result);
            calendar.add(Calendar.DATE, 1);
        }
        return dates;
    }
    public static String f_diaSemana(int dia) {
        if (dia > 6) dia = 0;
        String[] vobj_days = {"dom.", "lun.", "mar.", "mié.", "jue.", "Vie.", "Sáb."};
        return vobj_days[dia];
    }

    public static String f_intervaloSemana(int semanas , int anio) {

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, anio);
        calendar.set(Calendar.WEEK_OF_YEAR, semanas);

        String[] vobj_Meses = {"ene.", "feb.", "mar.", "abr.", "may.", "jun.", "jul.", "ago.", "sept.", "oct.", "nov.", "dic."};
        int dia =0;
        int diaF=0;

        while (calendar.get(Calendar.DAY_OF_WEEK) > calendar.getFirstDayOfWeek()) {
            calendar.add(Calendar.DATE, -1);
            dia = calendar.get(Calendar.DAY_OF_MONTH);
            diaF=dia+6;
        }

        int numDiasMes= 0;
        switch (calendar.get(Calendar.MONTH)){
            case 0: case 2: case 4: case 6: case 7: case 9: case 11:
                numDiasMes = 31;
                break;
            case 3: case 5: case 8: case 10:
                numDiasMes = 30;
                break;
            case 1:
                if((anio%4==0 && anio%100!=0) || anio%400==0){
                    numDiasMes = 29;
                }
                else numDiasMes = 28;
                break;
        }
        if(diaF>numDiasMes){
            diaF= diaF-numDiasMes;
            int mes= calendar.get(Calendar.MONTH)+1;
            if(mes>11)mes=0;
            return "semana del " + dia+ " al " + diaF + " de "+vobj_Meses[mes];
        }else  return "semana del " + dia+ " al " + diaF + " de "+vobj_Meses[calendar.get(Calendar.MONTH)];
        //int start = calendar.get(Calendar.)

    }

    /**
     * This method converts dp unit to equivalent pixels, depending on device density.
     *
     * @param dp A value in dp (density independent pixels) unit. Which we need to convert into pixels
     * @param context Context to get resources and device specific display metrics
     * @return A float value to represent px equivalent to dp depending on device density
     */
    public static float convertDpToPixel(float dp, Context context){
        return dp * ((float) context.getResources().getDisplayMetrics().densityDpi / DisplayMetrics.DENSITY_DEFAULT);
    }

    /**
     * This method converts device specific pixels to density independent pixels.
     *
     * @param px A value in px (pixels) unit. Which we need to convert into db
     * @param context Context to get resources and device specific display metrics
     * @return A float value to represent dp equivalent to px value
     */
    public static float convertPixelsToDp(float px, Context context){
        return px / ((float) context.getResources().getDisplayMetrics().densityDpi / DisplayMetrics.DENSITY_DEFAULT);
    }

    public static String tiempoCreacion(long fecha){

        Calendar calendarActual = Calendar.getInstance();
        int anhoActual = calendarActual.get(Calendar.YEAR);
        int mesActual = calendarActual.get(Calendar.MONTH);
        int diaActual = calendarActual.get(Calendar.DAY_OF_MONTH);
        int horaActual = calendarActual.get(Calendar.HOUR_OF_DAY);
        int minutoActual = calendarActual.get(Calendar.MINUTE);
        int segundosActual = calendarActual.get(Calendar.SECOND);

        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(fecha);
        int anhio = calendar.get(Calendar.YEAR);
        int mes= calendar.get(Calendar.MONTH);
        int dia = calendar.get(Calendar.DAY_OF_MONTH);
        int hora = calendar.get(Calendar.HOUR_OF_DAY);
        int minuto = calendar.get(Calendar.MINUTE);
        int segundos= calendar.get(Calendar.SECOND);

        calendarActual.set(Calendar.DAY_OF_YEAR,-1);

        int anioAyer = calendarActual.get(Calendar.YEAR);
        int mesAyer= calendarActual.get(Calendar.MONTH);
        int diaAyer = calendarActual.get(Calendar.DAY_OF_MONTH);

        if (anhio==anhoActual&&mesActual==mes&&dia==diaActual&&horaActual==hora&&minutoActual==minuto&&segundosActual>=segundos){
            return "hace " + (segundosActual-segundos) + (segundosActual-segundos==1?" segundo":" segundos");
        } else if (anhio==anhoActual&&mesActual==mes&&dia==diaActual&&horaActual==hora&&minutoActual>minuto){
            return "hace " + (minutoActual-minuto) + (minutoActual-minuto==1?" minuto":" minutos");
        }else if (anhio==anhoActual&&mesActual==mes&&dia==diaActual){
            return "hoy a las " + changeTime12Hour(hora,minuto);
        }else if (anhio==anioAyer&&mesAyer==mes&&dia==diaAyer) {
            return "ayer a las " + changeTime12Hour(calendar.get(Calendar.HOUR_OF_DAY),calendar.get(Calendar.MINUTE));
        }else if(anhio==anhoActual){
            return "el "+Utils.f_fecha_letras(fecha) + " " + changeTime12Hour(calendar.get(Calendar.HOUR_OF_DAY),calendar.get(Calendar.MINUTE));
        }else {
            return  "el "+Utils.getFechaDiaMesAnho(fecha) + " " + changeTime12Hour(calendar.get(Calendar.HOUR_OF_DAY),calendar.get(Calendar.MINUTE));
        }

    }



}
