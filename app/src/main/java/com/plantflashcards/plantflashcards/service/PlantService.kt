package com.plantflashcards.plantflashcards.service

import com.plantflashcards.plantflashcards.dto.Plant

class PlantService {
    fun parsePlantFromJsonData(difficulty: String?): List<Plant>? {
        //declare our return type
        var allPlants = ArrayList<Plant>()

        //create a new plant
        var plant = Plant()
        // Open connection to data feed.
        // Parse to plant objects

        //Add plant object to a collection.
        allPlants.add(plant)

        //return the collection.
        return allPlants
    }
}