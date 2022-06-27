package com.example.apirestful

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.json.JSONArray
import org.json.JSONObject

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }

   private fun api(view: View)
    {
        val txtr= findViewById<TextView>(R.id.respuesta)
        val q= Volley.newRequestQueue(this)
        var str:String;
        var cad= ""
        val i=0
        val request= JsonObjectRequest(
            Request.Method.GET,"https://gorest.co.in/public/v1/users",null,
        { response ->
            str=response.getString("data")
            val ljson= JSONArray(str)
           for (i in 0 until ljson.length()) {
               val ii=i+1
               val j: JSONObject =ljson.getJSONObject(i)
                       cad= cad+"\n RESPUESTA DE LA API N: "+ii+"\n"+
                       "\n" + "id: " + j.getString("id") + "\n"+
                       "\n" + "name: " + j.getString("name") + "\n"+
                       "\n" + "email: " + j.getString("email") + "\n"+
                       "\n" + "gender: " + j.getString("gender") + "\n"+
                       "\n" + "status: " + j.getString("status") + "\n"
            }
            txtr.text=cad;
        },
            {
           Response.ErrorListener {
                error->error.printStackTrace()
           }
            }
        )
        q.add(request)
    }

}