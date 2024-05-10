package Model

import Model.DTO.LoginRequest
import Model.DTO.Registration
import com.example.sportapp.models.AbstractApi
import com.example.sportapp.models.DTO.ExerciseInfo
import com.example.sportapp.models.DTO.GroupPreview
import com.example.sportapp.models.DTO.bio.BioAdditiveCreation
import com.example.sportapp.models.DTO.bio.BioAdditiveInfo
import com.example.sportapp.models.DTO.bio.BioAdditiveUpdation
import com.example.sportapp.models.DTO.bodyreaction.BodyReactionCreation
import com.example.sportapp.models.DTO.bodyreaction.BodyReactionInfo
import com.example.sportapp.models.DTO.bodyreaction.BodyReactionUpdation
import com.example.sportapp.models.DTO.exercise.ExerciseCreation
import com.example.sportapp.models.DTO.exercise.ExerciseUpdation
import com.example.sportapp.models.DTO.remind.RemindCreation
import com.example.sportapp.models.DTO.remind.RemindInfo
import com.example.sportapp.models.DTO.remind.RemindUpdation
import com.example.sportapp.models.DTO.train.ExerciseTrainInfo
import com.example.sportapp.models.DTO.train.TrainInfo
import com.example.sportapp.models.api.ExerciseGroupsPreview
import com.example.sportapp.models.api.ExerciseListApi
import com.example.sportapp.models.api.BioAdditiveApi
import com.example.sportapp.models.api.BodyReactionApi
import com.example.sportapp.models.api.ExerciseApi
import com.example.sportapp.models.api.LoginApi
import com.example.sportapp.models.api.RegistrationApi
import com.example.sportapp.models.api.RemindApi
import com.example.sportapp.models.api.RequestApi
import com.example.sportapp.models.api.complexListApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

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

    public suspend fun getExerciseList(page: Int, muscleGroup: String): List<ExerciseInfo> {
        var exerciseList: List<ExerciseInfo> =
            listOf(ExerciseInfo(0, "", "", "", "", 0, "", "", ""))
        return withContext(Dispatchers.IO) {
            if (testConnection()) {
                val request = retrofit.baseUrl(url)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                    .create(ExerciseListApi::class.java)
                val res = request.getExercises(page, muscleGroup, "Bearer ".plus(token))
                if (!res.isEmpty()) {
                    exerciseList = res
                }
            }
            return@withContext exerciseList
        }
    }

    suspend fun getComplexList(page: Int): List<TrainInfo> {
        var complexList = listOf(
            TrainInfo(
                1, "", "", 1, "", "", mutableListOf<ExerciseTrainInfo>()
            )
        )
        return withContext(Dispatchers.IO) {
            if (testConnection()) {
                val request = retrofit.baseUrl(url)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                    .create(complexListApi::class.java)
                val res = request.getExercises(page, "Bearer ".plus(token))
                if (!res.isEmpty()) {
                    complexList = res
                }
            }
            return@withContext complexList
        }
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
            try {
                if (testConnection()) {
                    val request = exerciseApi();
                    val imageFile = exerciseCreation.image
                    val imageRequestBody = imageFile?.asRequestBody(null)
                    val imagePart = imageRequestBody?.let {
                        MultipartBody.Part.createFormData("image", imageFile.name, it)
                    }

                    // Создание объекта для передачи данных формы
                    val namePart =
                        exerciseCreation.name.toRequestBody("text/plain".toMediaTypeOrNull())
                    val descriptionPart =
                        exerciseCreation.description.toRequestBody("text/plain".toMediaTypeOrNull())
                    val videoPart =
                        exerciseCreation.video?.toRequestBody("text/plain".toMediaTypeOrNull())
                    val muscleGroupPart =
                        exerciseCreation.muscleGroup.toRequestBody("text/plain".toMediaTypeOrNull())
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
            } catch (e: retrofit2.HttpException) {

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
                val idPart =
                    exerciseUpdation.id.toString().toRequestBody("text/plain".toMediaTypeOrNull())
                val namePart = exerciseUpdation.name.toRequestBody("text/plain".toMediaTypeOrNull())
                val descriptionPart =
                    exerciseUpdation.description.toRequestBody("text/plain".toMediaTypeOrNull())
                val videoPart =
                    exerciseUpdation.video?.toRequestBody("text/plain".toMediaTypeOrNull())
                val muscleGroupPart =
                    exerciseUpdation.muscleGroup.toRequestBody("text/plain".toMediaTypeOrNull())

                // Вызов запроса
                val request = exerciseApi();// Создание экземпляра интерфейса Retrofit
                val result = request.update(
                    "Bearer ".plus(token),
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
                    groupPreviews = res
                }
            }
            return@withContext groupPreviews
        }
    }


    /*============================ bodyReaction ============================ */

    private fun bodyReactionApi(): BodyReactionApi {
        return retrofit.baseUrl(url)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(BodyReactionApi::class.java);
    }

    public suspend fun getBodyReactionList(): List<BodyReactionInfo>? {

        var bodyReactionList: List<BodyReactionInfo>? = null;
        return withContext(Dispatchers.IO) {
            if (testConnection()) {
                val request = bodyReactionApi();
                val res = request.getMany("Bearer ".plus(token))
                if (!res.isEmpty()) {
                    bodyReactionList = res
                }
            }
            return@withContext bodyReactionList;
        }
    }

    public suspend fun createBodyReaction(bodyReactionCreation: BodyReactionCreation): BodyReactionInfo? {
        var bodyReaction: BodyReactionInfo? = null;
        return withContext(Dispatchers.IO) {
            if (testConnection()) {
                val request = bodyReactionApi();
                val res = request.create("Bearer ".plus(token), bodyReactionCreation)
                if (res != null) {
                    bodyReaction = res
                }
            }
            return@withContext bodyReaction;
        }
    }

    public suspend fun updateBodyReaction(bodyReactionUpdation: BodyReactionUpdation): BodyReactionInfo? {
        var bodyReaction: BodyReactionInfo? = null;
        return withContext(Dispatchers.IO) {
            if (testConnection()) {
                val request = bodyReactionApi();
                val res = request.update("Bearer ".plus(token), bodyReactionUpdation)
                if (res != null) {
                    bodyReaction = res
                }
            }
            return@withContext bodyReaction;
        }
    }

    public suspend fun deleteBodyReaction(id: Int): BodyReactionInfo? {
        var bodyReaction: BodyReactionInfo? = null;
        return withContext(Dispatchers.IO) {
            if (testConnection()) {
                val request = bodyReactionApi();
                val res = request.delete("Bearer ".plus(token), id)
                if (res != null) {
                    bodyReaction = res
                }
            }
            return@withContext bodyReaction;
        }
    }

    public suspend fun getBodyReaction(id: Int): BodyReactionInfo? {
        var bodyReaction: BodyReactionInfo? = null;
        return withContext(Dispatchers.IO) {
            if (testConnection()) {
                val request = bodyReactionApi();
                val res = request.get("Bearer ".plus(token), id)
                if (res != null) {
                    bodyReaction = res
                }
            }
            return@withContext bodyReaction;
        }
    }

    /*-------------------- BIOADDITIVE -----------------------*/


    private fun bioAddtitveApi(): BioAdditiveApi {
        return retrofit.baseUrl(url)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(BioAdditiveApi::class.java);
    }

    public suspend fun getBioAdditiveList(): List<BioAdditiveInfo>? {

        var bioAdditive: List<BioAdditiveInfo>? = null;
        return withContext(Dispatchers.IO) {
            if (testConnection()) {
                val request = bioAddtitveApi();
                val res = request.getMany("Bearer ".plus(token))
                if (!res.isEmpty()) {
                    bioAdditive = res
                }
            }
            return@withContext bioAdditive;
        }
    }

    public suspend fun createBioAdditive(bioAdditiveCreation: BioAdditiveCreation): BioAdditiveInfo? {
        var bioAdditive: BioAdditiveInfo? = null;
        return withContext(Dispatchers.IO) {
            if (testConnection()) {
                val request = bioAddtitveApi();
                val res = request.create("Bearer ".plus(token), bioAdditiveCreation)
                if (res != null) {
                    bioAdditive = res
                }
            }
            return@withContext bioAdditive;
        }
    }

    public suspend fun updateBioAdditive(bioAdditiveUpdation: BioAdditiveUpdation): BioAdditiveInfo? {
        var bioAdditive: BioAdditiveInfo? = null;
        return withContext(Dispatchers.IO) {
            if (testConnection()) {
                val request = bioAddtitveApi();
                val res = request.update("Bearer ".plus(token), bioAdditiveUpdation)
                if (res != null) {
                    bioAdditive = res
                }
            }
            return@withContext bioAdditive;
        }
    }

    public suspend fun deleteBioAdditive(id: Int): BioAdditiveInfo? {
        var bioAdditive: BioAdditiveInfo? = null;
        return withContext(Dispatchers.IO) {
            if (testConnection()) {
                val request = bioAddtitveApi();
                val res = request.delete("Bearer ".plus(token), id)
                if (res != null) {
                    bioAdditive = res
                }
            }
            return@withContext bioAdditive;
        }
    }

    public suspend fun getBioAdditive(id: Int): BioAdditiveInfo? {
        var bioAdditive: BioAdditiveInfo? = null;
        return withContext(Dispatchers.IO) {
            if (testConnection()) {
                val request = bioAddtitveApi();
                val res = request.get("Bearer ".plus(token), id)
                if (res != null) {
                    bioAdditive = res
                }
            }
            return@withContext bioAdditive;
        }
    }


    /*-------------------- Remind -----------------------*/


    private fun remindApi(): RemindApi {
        return retrofit.baseUrl(url)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(RemindApi::class.java);
    }

    public suspend fun getRemindList(): List<RemindInfo>? {

        var remind: List<RemindInfo>? = null;
        return withContext(Dispatchers.IO) {
            if (testConnection()) {
                val request = remindApi();
                val res = request.getMany("Bearer ".plus(token))
                if (!res.isEmpty()) {
                    remind = res
                }
            }
            return@withContext remind;
        }
    }

    public suspend fun createRemind(remindCreation: RemindCreation): RemindInfo? {
        var remind: RemindInfo? = null;
        return withContext(Dispatchers.IO) {
            if (testConnection()) {
                val request = remindApi();
                val res = request.create("Bearer ".plus(token), remindCreation)
                if (res != null) {
                    remind = res
                }
            }
            return@withContext remind;
        }
    }

    public suspend fun updateRemind(remindUpdation: RemindUpdation): RemindInfo? {
        var remind: RemindInfo? = null;
        return withContext(Dispatchers.IO) {
            if (testConnection()) {
                val request = remindApi();
                val res = request.update("Bearer ".plus(token), remindUpdation)
                if (res != null) {
                    remind = res
                }
            }
            return@withContext remind;
        }
    }

    public suspend fun deleteRemind(id: Int): RemindInfo? {
        var remind: RemindInfo? = null;
        return withContext(Dispatchers.IO) {
            if (testConnection()) {
                val request = remindApi();
                val res = request.delete("Bearer ".plus(token), id)
                if (res != null) {
                    remind = res
                }
            }
            return@withContext remind;
        }
    }

    public suspend fun getRemind(id: Int): RemindInfo? {
        var Remind: RemindInfo? = null;
        return withContext(Dispatchers.IO) {
            if (testConnection()) {
                val request = remindApi();
                val res = request.get("Bearer ".plus(token), id)
                if (res != null) {
                    Remind = res
                }
            }
            return@withContext Remind;
        }
    }
}