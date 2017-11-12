package com.example.lida.myapplication

import android.content.Context
import android.content.Intent
import android.os.AsyncTask
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.View
import android.widget.Toast
import com.example.lida.myapplication.adapter.RecyclerTouchListener
import com.example.lida.myapplication.adapter.mp_adapter
import com.example.lida.myapplication.decorators.DividerItemDecoration
import com.example.lida.myapplication.models.mp
import org.json.JSONObject
import java.util.*

class mps : AppCompatActivity() {
    internal var sh: String? = null
    private val mplist = ArrayList<mp>()
    private var mp: RecyclerView? = null
    private var mAdapter: mp_adapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mps)

       /* val md = getSharedPreferences("abc", Context.MODE_PRIVATE)
        val s = md.getString("type", null)

        if (s.equals("complaint", ignoreCase = true)) {
            supportActionBar!!.setTitle("Complaint Registration")
        }
        if (s.equals("feedback", ignoreCase = true)) {
            supportActionBar!!.setTitle("Feedback Or suggetion")
        }*/

        val mpView = findViewById<View>(R.id.mp_recyclerview) as RecyclerView

        mAdapter = mp_adapter(mplist)
        mpView.setHasFixedSize(true)
        val mLayoutManager = LinearLayoutManager(applicationContext)
        mpView.setLayoutManager(mLayoutManager)
        mpView.addItemDecoration(DividerItemDecoration(this, LinearLayoutManager.VERTICAL))
        mpView.setItemAnimator(DefaultItemAnimator())
        mpView.setAdapter(mAdapter)
        val n = NewClass()
        n.execute()
        mpView.addOnItemTouchListener(RecyclerTouchListener(applicationContext, mpView, object : RecyclerTouchListener.ClickListener {
            override fun onClick(view: View, position: Int) {
                val ml = mplist.get(position)
                Toast.makeText(applicationContext, ml.getName() + " is selected!", Toast.LENGTH_SHORT).show()
                val sh = getSharedPreferences("mlaid", Context.MODE_PRIVATE)
                val edsh = sh.edit()
                edsh.putString("id", ml.getId())
                edsh.putString("name", ml.getName())
                edsh.commit()

                val md = getSharedPreferences("abc", Context.MODE_PRIVATE)
                val s = md.getString("type", null)
                if (s!!.equals("complaint", ignoreCase = true)) {
                    val i = Intent(this@mps, Compreg::class.java)
                    startActivity(i)
                }
                if (s.equals("feedback", ignoreCase = true)) {
                    val i = Intent(this@mps, Feedback::class.java)
                    startActivity(i)
                }


            }

            override fun onLongClick(view: View, position: Int) {

            }
        }))

    }

    inner class NewClass : AsyncTask<String, String, String>() {
        internal var urlParameters = ""

        override fun doInBackground(vararg strings: String): String {


            sh = Connectivity.excutePost(Constants.MLA_URL,
                    "")
            Log.e("You are at", "" + sh)

            return (sh as String?).toString()

        }

        override fun onPostExecute(s: String) {
            super.onPostExecute(s)

            //   if (sh.contains("SUCCESS")) {
            //   Toast.makeText(getApplicationContext(), sh, Toast.LENGTH_SHORT).show();
            parsingmethod(s)
            mAdapter!!.notifyDataSetChanged()
            //   }
        }


    }

    fun parsingmethod(resp: String) {
        try {
            val object0 = JSONObject(resp)
            val jobject1 = object0.getJSONObject("Event")
            val ja = jobject1.getJSONArray("Details")
            val length = ja.length()
            val label = ArrayList<String>()

            for (i in 0 until length) {
                val data1 = ja.getJSONObject(i)
                val id = arrayOf(data1.getString("id"))
                val name = data1.getString("name")
                val mlaid = data1.getString("mlaid")

                val ml = com.example.lida.myapplication.models.mp(name, mlaid, "con")


                mplist.add(ml)

            }

        } catch (e: Exception) {
            e.printStackTrace()
            Log.e("mException", "qqqqqq" + e)
        }

    }

}

