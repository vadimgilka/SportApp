package com.example.sportapp

import com.example.sportapp.models.DTO.exercise.ExerciseCreation
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.junit.Test

import org.junit.Assert.*
import Model.SportAppApi as SportAppApi

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    @Test
    suspend fun createExercise(){
        var sportAppApi = SportAppApi("yes");
        sportAppApi.authorization("test2", "password");
        var exercise = ExerciseCreation("teest", "teeest teeest teeest", null, null, "Neck");

        val job = GlobalScope.launch {
            val result = sportAppApi.createExercise(exercise) // Вызов асинхронного запроса
            // Здесь можно обрабатывать результат
            println(result)
        }

        job.join()

        println("Запрос createExercise завершен")
        assertEquals(1, 1);
    }

}