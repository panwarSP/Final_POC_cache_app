package com.example.poccacheapp.Util

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.net.ConnectivityManager
import android.os.AsyncTask
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.View
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.example.poccacheapp.MainActivity
import com.example.poccacheapp.R
import kotlinx.android.synthetic.main.activity_splash.*
import org.json.JSONObject
import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL


/*
class SplashActivity : AppCompatActivity() {
    private val splashViewModel: SplashViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        PrefetchData()
    }

}
*/

var Preference: String? = "APIs"
private lateinit var i: Intent


class SplashActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        i = Intent(this@SplashActivity, MainActivity::class.java)
        getInfo(text)
        /*Handler().postDelayed(
            {
                PrefetchData().execute()
            }, 5000)*/

    }



    fun getInfo(view: View){
        if (NetworkAvail()){
            splash_logo.setImageResource(R.drawable.capture)
            PrefetchData().execute("https://run.mocky.io/v3/11070c5e-7bcb-436d-a5a1-3fb536fb86a2")
           /* if(getSharedPreferences("APIs",0).contains("url1")){
                Log.d("ds","getSharedPreferences exists")
                startActivity(i)
                // close this activity
                finish()
            }
            else {
                PrefetchData().execute("https://run.mocky.io/v3/11070c5e-7bcb-436d-a5a1-3fb536fb86a2")
            }*/
        }

        else{
            val API1 = getSharedPreferences("APIs",0).getString("url1","").toString()
            Log.d("ds",API1)

            startActivity(i)
            // close this activity
            finish()
        }
    }



    fun NetworkAvail(): Boolean{
        val cManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val network = cManager.activeNetwork

        if (network!=null){
            return true
        }
        return false
    }



    /**
     * Async Task to make http call
     */
    private inner class PrefetchData : AsyncTask<String, Void?, String>() {

        override fun onPreExecute() {
            super.onPreExecute()
            // before making http calls
        }

        override fun doInBackground(vararg params: String): String {
            val result = httpGet(params[0])
            return result
        }

        @RequiresApi(Build.VERSION_CODES.N)
        override fun onPostExecute(result: String) {
            super.onPostExecute(result)

            val dataJSON = JSONObject(result)
            val apiArray = dataJSON.getJSONArray("API_URLs")


            val obj1 = apiArray.getJSONObject(0)
            val u1 = obj1.getString("URL")
            val v1 = obj1.getString("version")
            /*val urlS1 = "https://run.mocky.io/v3/${u1.toString()}"
            Log.d("p",urlS1)
            val baseurl1 = URL(urlS1)
            val conn1 = baseurl1.openConnection() as HttpURLConnection
            conn1.connect()

            //val reader1 = BufferedReader(InputStreamReader(conn1.inputStream))
            //val response1 = reader1.lines()
            //val store1 = response1.toString()*/

            val obj2 = apiArray.getJSONObject(1)
            val u2 = obj2.getString("URL")
            val v2 = obj2.getString("version")
            /*val urlS2 = "https://run.mocky.io/v3/${u2.toString()}"
            val baseurl2 = URL(urlS2)
            val conn2 = baseurl2.openConnection() as HttpURLConnection
            conn2.connect()
            val reader2 = BufferedReader(InputStreamReader(conn2.inputStream))
            val response2 = reader2.lines()
            val store2 = response2.toString()*/

            val prefs = getSharedPreferences("APIs", Context.MODE_PRIVATE)
            val editor = prefs.edit()

            if(getSharedPreferences("APIs",0).contains("url1")){
               // val tempv1 = prefs.getString("version1","")
               // val tempv2 = prefs.getString("version2","")
                editor.putString("tempurl1",u1)
                editor.putString("tempurl2",u2)
                editor.putString("tempv1",v1)
                editor.putString("tempv2",v2)
                /*if(!(tempv1.equals(v1))){
                    editor.putString("version1",v1)
                    editor.putString("url1",u1)
                    //editor.putString("statesapi",store1)
                }

                if(!(tempv2.equals(v2))){
                    editor.putString("version2",v2)
                    editor.putString("url2",u2)
                    //editor.putString("photosapi",store2)
                }*/

            }
            else{
                editor.putString("url1",u1)
                editor.putString("url2",u2)
                editor.putString("version1",v1)
                editor.putString("version2",v2)
                editor.putString("tempurl1",u1)
                editor.putString("tempurl2",u2)
                editor.putString("tempv1",v1)
                editor.putString("tempv2",v2)
               // editor.putString("statesapi",store1)
               // editor.putString("photosapi",store2)
            }

            //editor.putString("baseurl1",store1)
            //editor.putString("baseurl2",store2)

            editor.apply()
            editor.commit()

            startActivity(i)
            // close this activity
            finish()
        }
    }
    private fun httpGet(myURL: String): String {

        val inputStream: InputStream
        val result:String

        // create URL
        val url:URL = URL(myURL)

        // create HttpURLConnection
        val conn:HttpURLConnection = url.openConnection() as HttpURLConnection

        // make GET request to the given URL
        conn.connect()

        // receive response as inputStream
        inputStream = conn.inputStream

        // convert inputstream to string
        if(inputStream != null) {
            result = inputStream.bufferedReader().use(BufferedReader::readText)
        }
        else
            result = " "
        return result
    }

}












/*
private val _properties1 = MutableLiveData<List<api>>()
val properties1: LiveData<List<api>>
    get() = _properties1

private val _status = MutableLiveData<AllApiStatus1>()
val status: LiveData<AllApiStatus1>
    get() = _status
*/