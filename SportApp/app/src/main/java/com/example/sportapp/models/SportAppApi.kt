package Model

import Model.DTO.LoginRequest
import Model.DTO.Registration
import android.util.Log
import com.example.sportapp.models.AbstractApi
import com.example.sportapp.models.DTO.ExerciseInfo
import com.example.sportapp.models.DTO.GroupPreview
import com.example.sportapp.models.DTO.exercise.ExerciseCreation
import com.example.sportapp.models.DTO.exercise.ExerciseUpdation
import com.example.sportapp.models.ExerciseGroupsPreview
import com.example.sportapp.models.ExerciseListApi
import com.example.sportapp.models.api.ExerciseApi
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONArray
import org.json.JSONObject
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.FileReader
import java.util.Properties

class SportAppApi : AbstractApi {
    private val CONFIG = "enviroment.properties"

    //private val file = FileReader("enviroment.properties")
    //private val prop = file.use { Properties().apply { load(it) } }
    private val url = "http://85.193.85.116/"
    private val retrofit = Retrofit.Builder()
    private var status = "NO CONNECTION"
    private lateinit var token: String
    override var login: String
        get() = TODO("Not yet implemented")
        set(value) {}
    override var password: String
        get() = TODO("Not yet implemented")
        set(value) {}
    override var connectionStatus: Byte
        get() = TODO("Not yet implemented")
        set(value) {}

    constructor(status: String) : super() {
        this.status = status
    }


    /**
     *
     * Авторизация в api по логину и паролю
     *
     * @param username
     * @param password
     *
     */
    override fun authorization(username: String, password: String) {
        if (testConnection()) {
            val request = this.retrofit.baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(LoginApi::class.java)
            val res = request.auth(LoginRequest(username, password)).execute()
            if (res.code() == 201) {
                this.status = "Authorized"
            }
            this.token = res.body()?.access_token.toString()
        }
    }

    /**
     *
     * Проверка соеденения с сервером
     *
     * @return результат проверки
     */
    override fun testConnection(): Boolean {
        val request = this.retrofit.baseUrl(url)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(RequestApi::class.java)
        val response = request.getResponse().execute()
        this.status = response.raw().message
        return response.code() == 401
    }

    /**
     *
     * Добавление нового пользователя
     *
     * @param username
     * @param email
     * @param password
     */
    override fun registration(username: String, email: String, password: String) {
        if (testConnection()) {
            val request = this.retrofit.baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(RegistrationApi::class.java)
            val res = request.registrate(Registration(username, email, password)).execute()
            if (res.code() == 201) {
                this.status = "Authorized"
            }
            this.token = res.body()?.access_token.toString()
        }
    }

    override fun loadTable(vararg targetName: String) {
        TODO("Not yet implemented")
    }

    override fun updateTable(vararg targetName: String) {
        TODO("Not yet implemented")
    }

    override fun createTable(vararg table: String) {
        TODO("Not yet implemented")
    }

    override fun changeStatus(vararg actualState: Byte) {
        TODO("Not yet implemented")
    }

    override fun getStatus(): String {
        return this.status
    }

    public fun getExerciseList(page: Int): JSONArray {
        if (testConnection()) {
            val request = this.retrofit.baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ExerciseListApi::class.java)
            val res = request.getExercises(1).execute()
            if (res.isSuccessful) {
                return JSONArray(res.message())
            }
        }
        return JSONArray("[]")
    }

    private fun exerciseApi(): ExerciseApi {
        return retrofit.baseUrl(url)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ExerciseApi::class.java);
    }

    public suspend fun createExercise(exerciseCreation: ExerciseCreation): ExerciseInfo? {
        var exercise: ExerciseInfo? = null

        return withContext(Dispatchers.IO) {
            if (testConnection()) {
                val request = exerciseApi();
                val imageFile = exerciseCreation.image
                val imageRequestBody = imageFile?.asRequestBody(null)
                val imagePart = imageRequestBody?.let {
                    MultipartBody.Part.createFormData("image", imageFile.name, it)
                }

                // Создание объекта для передачи данных формы
                val namePart = exerciseCreation.name.toRequestBody("text/plain".toMediaTypeOrNull())
                val descriptionPart = exerciseCreation.description.toRequestBody("text/plain".toMediaTypeOrNull())
                val videoPart = exerciseCreation.video?.toRequestBody("text/plain".toMediaTypeOrNull())
                val muscleGroupPart = exerciseCreation.muscleGroup.toRequestBody("text/plain".toMediaTypeOrNull())
                val res = request.create(
                    "Bearer ".plus(token),
                    namePart,
                    descriptionPart,
                    muscleGroupPart,
                    imagePart,
                    videoPart
                );

                println(res)
                if (res != null) {
                    exercise = res
                }
            }
            // Возвращаем значение exercise вне блока withContext
            return@withContext exercise
        }
    }

    public suspend fun updateExercise(exerciseUpdation: ExerciseUpdation): ExerciseInfo? {
        var exercise: ExerciseInfo? = null

        return withContext(Dispatchers.IO) {
            if (testConnection()) {
                // Создание объекта для передачи изображения
                val imageFile = exerciseUpdation.image
                val imageRequestBody = imageFile?.asRequestBody(null)
                val imagePart = imageRequestBody?.let {
                    MultipartBody.Part.createFormData("image", imageFile.name, it)
                }

                // Создание объектов для передачи данных формы
                val idPart = exerciseUpdation.id.toString().toRequestBody("text/plain".toMediaTypeOrNull())
                val namePart = exerciseUpdation.name.toRequestBody("text/plain".toMediaTypeOrNull())
                val descriptionPart = exerciseUpdation.description.toRequestBody("text/plain".toMediaTypeOrNull())
                val videoPart = exerciseUpdation.video?.toRequestBody("text/plain".toMediaTypeOrNull())
                val muscleGroupPart = exerciseUpdation.muscleGroup.toRequestBody("text/plain".toMediaTypeOrNull())

                // Вызов запроса
                val request = exerciseApi();// Создание экземпляра интерфейса Retrofit
                val result = request.update(
                    "Bearer ".plus("token"),
                    idPart,
                    namePart,
                    descriptionPart,
                    muscleGroupPart,
                    imagePart,
                    videoPart,
                )

                exercise = result
            }
            // Возвращаем значение exercise вне блока withContext
            return@withContext exercise
        }
    }


    public suspend fun getExerciseGroupsCount(): List<GroupPreview> {
        var groupPreviews: List<GroupPreview> =
            MutableList<GroupPreview>(1, { GroupPreview(0, "not set(") })
        return withContext(Dispatchers.IO) {
            if (testConnection()) {
                val request = retrofit.baseUrl(url)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                    .create(ExerciseGroupsPreview::class.java)
                val res = request.getExercisesGroups("Bearer ".plus(token))
                if (!res.isEmpty()) {
                    Log.e("tagCount", res.size.toString())
                    groupPreviews = res
                }
            }
            return@withContext groupPreviews
        }


//        CoroutineScope(Dispatchers.IO).async {
//            if (testConnection()) {
//                val request = retrofit.baseUrl(url)
//                    .addConverterFactory(GsonConverterFactory.create())
//                    .build()
//                    .create(ExerciseGroupsPreview::class.java)
//                val res = request.getExercisesGroups("Bearer ".plus(token))
//                if (!res.isEmpty()) {
//                    Log.e("tagCount", res.size.toString())
//                    groupPreviews = res
//                }
//            }
//        }
    }


    //private fun getProperty(name: String): String = prop.getProperty(name)
}