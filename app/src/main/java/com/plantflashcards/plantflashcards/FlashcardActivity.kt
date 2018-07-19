package com.plantflashcards.plantflashcards

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.os.AsyncTask
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.plantflashcards.plantflashcards.dto.Plant
import com.plantflashcards.plantflashcards.service.PlantService
import kotlinx.android.synthetic.main.activity_flashcard.*
import org.jetbrains.anko.find

class FlashcardActivity : AppCompatActivity() {
    val CAMERA_ACTIVITY_REQUEST = 10
    var imageView: ImageView? = null
    var button1: Button? = null
    var button2: Button? = null
    var button3: Button? = null
    var button4: Button? = null
    var status: TextView? = null
    var correctAnswer: Int = 0
    var allPlants: List<Plant>? = ArrayList()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_flashcard)
        setSupportActionBar(toolbar)

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }
        imageView = find(R.id.imageView)
        button1 = find(R.id.button1)
        button2 = find(R.id.button2)
        button3 = find(R.id.button3)
        button4 = find(R.id.button4)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_flashcard, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    fun onButton1Clicked(v: View) {
        evaluate(0)
    }

    fun onButton2Clicked(v: View) {
        evaluate(1)
    }

    fun onButton3Clicked(v: View) {
        evaluate(2)
    }

    fun onButton4Clicked(v: View) {
        evaluate(3)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {

            if (requestCode == CAMERA_ACTIVITY_REQUEST) {
                var image = data?.extras?.get("data") as Bitmap
                imageView?.setImageBitmap(image)
            }
        }
    }
    private fun evaluate(i: Int) {

    }
    /***
     * Open a connection to  a data feed to retrieve data over a network.
     * @param search to search the text that will narrow down the results
     * @return a collection of Plant objects that were parsed from JSON.
     */

    inner class GetPlantsActivity : AsyncTask<String, Int, List<Plant>?>() {
        /**
         * Has access to the user interface thread, and the components of the
         * enclosing activity.
         * In this method, we can update the screen based on the data retrieved in
         * the background thread.
         */
        override fun onPostExecute(result: List<Plant>?) {
            super.onPostExecute(result)
            var numberResults = result?.size ?: 0
            if (numberResults > 3) {
                button1?.text = result?.get(0).toString()
                button2?.text = result?.get(1).toString()
                button3?.text = result?.get(2).toString()
                button4?.text = result?.get(3).toString()
                //pick one item to be the correct answer
                correctAnswer = (Math.random() * 4).toInt()

            }
            allPlants = result

        }

        override fun doInBackground(vararg search: String?): List<Plant>? {
            // we are collecting the first String passed to this function
            var difficulty = search[0]
            // create n instance if PlantService
            var plantService = PlantService()
            //invoke our parse plant function
            var allplants = plantService.parsePlantFromJsonData(difficulty)
            return allplants

        }

    }


}
