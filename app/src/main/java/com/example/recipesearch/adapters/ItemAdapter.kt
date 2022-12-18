package com.example.recipesearch.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.recipesearch.R
import com.example.recipesearch.databinding.RecipeRowBinding
import com.example.recipesearch.models.RecipeCollection
import com.squareup.picasso.Picasso
import kotlin.math.log

class ItemAdapter(val list: ArrayList<RecipeCollection>) : RecyclerView.Adapter<ItemAdapter.ItemViewHolder>() {

    inner class ItemViewHolder(val binding: RecipeRowBinding) : RecyclerView.ViewHolder(binding.root) {


        fun bindData(recipeCollection: RecipeCollection) {

            fun populateLabelViews(labelList: ArrayList<String>, labelViewTxt: TextView) {
                if(labelList.isNotEmpty()) {
                    Log.i("binder: ", "" + labelList.size)
                    labelViewTxt.visibility = View.VISIBLE
                    labelViewTxt.text = labelList[0]
                } else {
                    Log.i("binder: ", "" + labelList.size)
                    labelViewTxt.visibility = View.GONE
                }

            }
            with(binding) {
                Picasso.get().load(recipeCollection.recipe.image).into(recipeImage)
                recipeName.text = recipeCollection.recipe.label
                populateLabelViews(recipeCollection.recipe.dietLabels, dietLabelTxt)
                populateLabelViews(recipeCollection.recipe.healthLabels, healthLabelTxt)
                populateLabelViews(recipeCollection.recipe.mealType, mealTypeLabelTxt)

                var isFavorite = false

                favoriteButton.setOnClickListener {
                    if(!isFavorite) {
                        favoriteButton.setImageResource(R.drawable.ic_baseline_favorite_50)
                        isFavorite = true
                    } else if(isFavorite){
                        favoriteButton.setImageResource(R.drawable.ic_baseline_favorite_border_50)
                        isFavorite = false
                    }
                }




            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val view = RecipeRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bindData(list[position])
    }

    override fun getItemCount(): Int = list.size
}