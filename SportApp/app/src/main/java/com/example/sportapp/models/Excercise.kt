package com.example.sportapp.models

public class Excercise(private val name: String, private val link: String, private val weight: Int, private val repeats: Int, private val algorithm: List<String>, private val group: String) {

    public fun getName(): String {
        return this.name
    }

    public fun getLink(): String {
        return this.link
    }

    public fun getWeight(): Int {
        return this.weight
    }

    public fun getRepeats(): Int {
        return this.repeats
    }

    public fun getAlgorithm(): List<String> {
        return this.algorithm
    }

    public fun getGroup(): String {
        return this.group
    }
}