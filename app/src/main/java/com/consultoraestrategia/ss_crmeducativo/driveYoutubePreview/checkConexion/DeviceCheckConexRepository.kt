package com.consultoraestrategia.padre_mentor.clean.device.checkConexion

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.util.Log
import com.consultoraestrategia.ss_crmeducativo.driveYoutubePreview.checkConexion.CheckConexRepository
import kotlinx.coroutines.*
import java.net.HttpURLConnection
import java.net.URL
import java.util.*

class DeviceCheckConexRepository private constructor(val context: Context): CheckConexRepository {
    private val serviceJob = SupervisorJob()
    private val serviceScope = CoroutineScope(Dispatchers.Main + serviceJob)

    companion object {
        private const val TAG = "AndroidOnlineImpl"
        @Volatile
        private var instance: DeviceCheckConexRepository? = null
        @Volatile
        private var consultTime: Long = 0
        @Volatile
        var consultOnline = false

        fun getInstance(context: Context) =
                instance ?: synchronized(this) {
                    instance ?: DeviceCheckConexRepository(context).also { instance = it }
                }
    }


    private fun isNetworkAvailable(context: Context): Boolean {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val nw      = connectivityManager.activeNetwork ?: return false
            val actNw = connectivityManager.getNetworkCapabilities(nw) ?: return false
            return when {
                actNw.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                actNw.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                //for other device how are able to connect with Ethernet
                actNw.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
                //for check internet over Bluetooth
                actNw.hasTransport(NetworkCapabilities.TRANSPORT_BLUETOOTH) -> true
                else -> false
            }
        } else {
            val nwInfo = connectivityManager.activeNetworkInfo ?: return false
            return nwInfo.isConnected
        }
    }

    /*
    * private Boolean isNetworkAvailable(Application application) {
    ConnectivityManager connectivityManager = (ConnectivityManager) application.getSystemService(Context.CONNECTIVITY_SERVICE);
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        Network nw = connectivityManager.getActiveNetwork();
        if (nw == null) return false;
        NetworkCapabilities actNw = connectivityManager.getNetworkCapabilities(nw);
        return actNw != null && (actNw.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) || actNw.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) || actNw.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) || actNw.hasTransport(NetworkCapabilities.TRANSPORT_BLUETOOTH));
    } else {
        NetworkInfo nwInfo = connectivityManager.getActiveNetworkInfo();
        return nwInfo != null && nwInfo.isConnected();
    }
}
    * */

    override fun online(callback: CheckConexRepository.Callback?) {
        if (isNetworkAvailable(context)) {
            serviceScope.launch {
                callback?.onLoad(checketOnline())
            }
        } else {
            Log.d(TAG, "No network available!")
            Log.d(TAG, "onLoad: " + false)
            callback?.onLoad(false)
        }
    }

    suspend fun checketOnline():Boolean{
        return withContext(Dispatchers.IO) {
            val time = Date().time
            val result = time - consultTime

            if (result > 1000) {
                consultTime = time
                //autoCancel();
                try {
                    var urlc = URL("http://www.google.com").openConnection() as HttpURLConnection
                    urlc.setRequestProperty("User-Agent", "Test")
                    urlc.setRequestProperty("Connection", "close")
                    urlc.setConnectTimeout(1500)
                    urlc.connect()
                    consultOnline = urlc.getResponseCode() == 200
                    Log.d(TAG, "onLoad: " + consultOnline)
                    //if (!success) publishProgress(AndroidOnline2Impl.consultOnline)
                    return@withContext consultOnline
                } catch (e: Exception) {
                    e.printStackTrace()
                    Log.e(TAG, "Error checking internet connection", e)
                    Log.d(TAG, "onLoad: " + false)
                    return@withContext false
                }
            } else {
                val online = consultOnline
                Log.d(TAG, "onLoad cache: $online")
                return@withContext consultOnline
            }
        }
    }


    fun cancel() {
        serviceJob.cancel()
    }
}