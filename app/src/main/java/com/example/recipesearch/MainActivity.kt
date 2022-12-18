package com.example.recipesearch

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.recipesearch.adapters.ItemAdapter
import com.example.recipesearch.database.History
import com.example.recipesearch.database.RecipeDatabase
import com.example.recipesearch.databinding.ActivityMainBinding
import com.example.recipesearch.models.RecipeCollection
import com.example.recipesearch.models.RecipeHits
import com.example.recipesearch.network.RecipeClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Callback
import retrofit2.Call
import retrofit2.Response
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityMainBinding
    private val recipeList = ArrayList<RecipeCollection>()
    lateinit var searchQuery: String
    lateinit var timeOfDayQuery: String
    lateinit var dbInstance: RecipeDatabase


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        binding.recipeRecyclerView.setHasFixedSize(true)
        binding.recipeRecyclerView.layoutManager = LinearLayoutManager(this)
        searchQuery = "fish"

        showRecipeByTime()
        binding.searchBtn.setOnClickListener(this)
        binding.toHistoryBtn.setOnClickListener {
            val intent = Intent(this, HistoryActivity::class.java)
            startActivity(intent)
        }
        binding.toSettingsBtn.setOnClickListener {
            val intent = Intent(this, SettingsActivity::class.java)
            startActivity(intent)
        }



    }

    //By Time of day
    //Change Time settings on emulator to 24 hour format
    private fun showRecipeByTime() {
        val calendar = Calendar.getInstance()
        val hour = calendar.get(Calendar.HOUR_OF_DAY)
        Log.i("Check hour: ", "hour var: $hour")
        timeOfDayQuery = when(hour) {
            in 6..10 -> "Breakfast"
            in 11..14 -> "Lunch"
            in 15..18 -> "Dinner"
            else -> "Snack"
        }

        RecipeClient.instance.getRecipeByTime("$timeOfDayQuery",).enqueue(object :
            Callback<RecipeHits> {
            override fun onResponse(call: Call<RecipeHits>, response: Response<RecipeHits>) {
                val response = response.body()?.hits
                response?.let { recipeList.addAll(it) }
                val adapter = ItemAdapter(recipeList)
                binding.recipeRecyclerView.adapter = adapter
                binding.searchInputText.text = null

            }

            override fun onFailure(call: Call<RecipeHits>, t: Throwable) {
                Log.i("Mainactivity", "show list function failed")
            }

        })
    }

    ///Result by Search Query
    private fun showRecipeList() {
        if(searchQuery.isNullOrEmpty()) searchQuery = "chicken"
        RecipeClient.instance.getRecipeHits("public", "$searchQuery",).enqueue(object :
            Callback<RecipeHits> {
            override fun onResponse(call: Call<RecipeHits>, response: Response<RecipeHits>) {
                val response = response.body()?.hits
                response?.let { recipeList.addAll(it) }
                val adapter = ItemAdapter(recipeList)
                binding.recipeRecyclerView.adapter = adapter
                binding.searchInputText.text = null
                Log.i("OnResponse", "RecipeList: $recipeList")

                addToHistory(recipeList)

            }

            override fun onFailure(call: Call<RecipeHits>, t: Throwable) {
                Log.i("Mainactivity", "show list function failed")
            }

        })
    }
    //save searchList to history database
    private fun addToHistory(list: ArrayList<RecipeCollection>) {
    var newHistory: History
        for(item in list) {
            newHistory.recipeName = item.recipe.label


            GlobalScope.launch(Dispatchers.IO) {
                dbInstance.historyDao().insertAll(newHistory)
            }

        }

    }


    override fun onClick(p0: View?) {
        val searchInput = binding.searchInputText.text.toString()
        recipeList.clear()
        searchQuery = searchInput
        showRecipeList()


    }
}