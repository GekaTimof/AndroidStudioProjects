package com.example.a3_random_films

class Randomizer(films_arr: List<String>) {
    private val films: List<String> = films_arr
    private var mixFilms: List<String> = emptyList()
    private var currentPosition: Int = 0
    private val lastPosition: Int

    init {
        randomize()
        lastPosition = mixFilms.size - 1
    }

    fun randomize() {
        mixFilms = films.shuffled()
        currentPosition = 0
    }

    fun nextFilm(): String {
        return if (currentPosition <= lastPosition) {
            val film = mixFilms[currentPosition]
            currentPosition++
            film
        } else {
            "Films finished"
        }
    }

    fun leftPosition(): String {
        return (lastPosition - currentPosition + 1).toString()
    }
}