package com.example.sportapp.view.controllers.bio

import Model.SportAppApi
import android.content.Context
import android.util.Log
import androidx.navigation.NavController
import com.example.sportapp.models.DTO.bio.*
import com.example.sportapp.models.DTO.remind.RemindInfo
import com.example.sportapp.models.DTO.remind.RemindToBioCreation
import com.example.sportapp.services.NotificationMessagingService
import java.lang.IllegalArgumentException

class BioAdditiveController(var api: SportAppApi, var context: Context) {

    private val map: Map<String, String>;
    var operation: Operation = Operation.CREATE;
    private var currentBioAdditive = BioAdditiveDTO(
        id = -1,
        name = "default",
        description = "default",
        reminds = mutableListOf(),
        bioType = PILL
    );

    fun setDefaultBioAdditive(){
        currentBioAdditive = BioAdditiveDTO(
            id = -1,
            name = "default",
            description = "default",
            reminds = mutableListOf(),
            bioType = PILL
        );
    }

    fun initBioAdditive(info: BioAdditiveInfo) {
        currentBioAdditive = BioAdditiveDTO(
            id = info.id,
            name = info.name,
            description = info.description,
            reminds = infoToDto(info.reminds),
            bioType = info.bioType
        )
    }

    fun getName(): String {
        return currentBioAdditive.name
    }

    fun setName(name: String) {
        currentBioAdditive.name = name
    }

    fun setBioType(type: String) {
        currentBioAdditive.bioType = type
    }

    fun addRemind() {
        currentBioAdditive.reminds.add(RemindDto(-1, 100, "08:00", "set_token"))
    }

    fun getReminds(): MutableList<RemindDto> {
        return currentBioAdditive.reminds
    }


    init {
        map = mapOf(PILL to PILL_RUSSIAN, POWDER to POWDER_RUSSIAN)
    }

    suspend fun create(bioAdditiveCreation: BioAdditiveCreation): BioAdditiveInfo? {
        return api.createBioAdditive(bioAdditiveCreation);
    }

    suspend fun update(bioAdditiveUpdation: BioAdditiveUpdation): BioAdditiveInfo? {
        return api.updateBioAdditive(bioAdditiveUpdation);
    }

    suspend fun getMany(): List<BioAdditiveInfo> {
        return api.getBioAdditiveList();
    }

    suspend fun getOne(id: Int): BioAdditiveInfo? {
        return api.getBioAdditive(id);
    }

    suspend fun delete(id: Int): BioAdditiveInfo? {
        return api.deleteBioAdditive(id);
    }

    fun typeToEnglish(res: String): String {
        for ((key, value) in map) {
            if (key.equals(res)) {
                return value;
            }
        }

        return PILL;
    }

    fun typeToRussian(res: String): String {
        return map.getOrDefault(res, PILL_RUSSIAN)
    }

    companion object {
        public const val POWDER = "Powder"
        public const val PILL = "Pill"
        public const val POWDER_RUSSIAN = "Порошки"
        public const val PILL_RUSSIAN = "Таблетки"
    }


    enum class Operation {
        CREATE,
        DELETE,
        UPDATE,
    }


    fun remindInfoToCreation(reminds: MutableList<RemindDto>): MutableList<RemindToBioCreation> {

        var mutableList: MutableList<RemindToBioCreation> = mutableListOf()

        for (info in reminds) {
            var remind = RemindToBioCreation(
                timeStringToInt(info.time),
                period = 1,
                info.measure,
                30,
                NotificationMessagingService.getToken(context)
            )
            mutableList.add(remind)
        }

        return mutableList;
    }

    fun infoToDto(reminds: MutableList<RemindInfo>): MutableList<RemindDto> {
        var list = mutableListOf<RemindDto>()

        for (remind in reminds) {
            list.add(
                RemindDto(
                    remind.id,
                    measure = remind.measure,
                    time = intToTimeString(remind.time),
                    token = remind.token
                )
            )
        }
        return list
    }

    fun intToTimeString(minutes: Int): String {
        val hours = minutes / 60
        val remainingMinutes = minutes % 60
        return String.format("%02d:%02d", hours, remainingMinutes)
    }

    fun timeStringToInt(time: String): Int {

        val regex = """^\d{1,2}:\d{2}$""".toRegex()
        if (!regex.matches(time)) {
            throw IllegalArgumentException("Некорректное время")
        }

        val parts = time.split(":")
        val hours = parts[0].toInt()
        val minutes = parts[1].toInt()

        if (hours !in 0..23 || minutes !in 0..59) {
            throw IllegalArgumentException("Некорректное время")
        }

        return hours * 60 + minutes
    }

    suspend fun makeOperation() {
        when (this.operation) {
            Operation.CREATE -> {
                var bioAdditiveCreation = BioAdditiveCreation(
                    currentBioAdditive.name,
                    currentBioAdditive.description,
                    remindInfoToCreation(currentBioAdditive.reminds),
                    currentBioAdditive.bioType
                )
                setDefaultBioAdditive()
                this.create(bioAdditiveCreation);
            };
            Operation.DELETE -> {
                delete(currentBioAdditive.id)
                setDefaultBioAdditive()
            };
            Operation.UPDATE -> {
                var bioAdditiveUpdation = BioAdditiveUpdation(
                    currentBioAdditive.id,
                    currentBioAdditive.name,
                    currentBioAdditive.description,
                    remindInfoToCreation(currentBioAdditive.reminds),
                    currentBioAdditive.bioType
                )
                Log.d("DEBUG", bioAdditiveUpdation.toString())
                setDefaultBioAdditive()
                this.update(bioAdditiveUpdation);
            };

        }
    }

     fun onEditPressed(bio : BioAdditiveInfo, nav : NavController){
        operation = Operation.UPDATE;
        initBioAdditive(bio);
        nav.navigate("bioUpdateCreateView")
    }
    suspend fun onDeletePressed(bio : BioAdditiveInfo) {
        operation = Operation.DELETE;
        initBioAdditive(bio);
        makeOperation()
    }

    fun deleteElementFromList(list : List<BioAdditiveInfo>, bio : BioAdditiveInfo): List<BioAdditiveInfo> {
        var newList = list.filter { it.id != bio.id}
        return newList
    }

    fun setReminds(remindList: MutableList<RemindDto>) {
        currentBioAdditive.reminds = remindList
    }

}