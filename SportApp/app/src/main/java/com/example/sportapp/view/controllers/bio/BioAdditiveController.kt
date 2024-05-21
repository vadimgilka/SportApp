package com.example.sportapp.view.controllers.bio

import Model.SportAppApi
import com.example.sportapp.models.DTO.bio.BioAdditiveCreation
import com.example.sportapp.models.DTO.bio.BioAdditiveInfo
import com.example.sportapp.models.DTO.bio.BioAdditiveUpdation
import com.example.sportapp.models.DTO.remind.RemindCreation
import com.example.sportapp.models.DTO.remind.RemindInfo
import com.example.sportapp.models.DTO.remind.RemindToBioCreation

class BioAdditiveController(var api: SportAppApi) {

    private val map: Map<String, String>;
    var operation: Operation = Operation.CREATE;
    var bioAdditiveInfo: BioAdditiveInfo = BioAdditiveInfo(
        id = -1,
        name = "name",
        description = "description",
        author_id = 1,
        bioType = "Pill",
        createdAt = "data",
        updatedAt = "data",
        reminds = mutableListOf()
    );

    init {
        map = mapOf(PILL to PILL_RUSSIAN, POWDER to POWDER_RUSSIAN);
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

    fun remindInfoToCreation(remindInfos: MutableList<RemindInfo>): MutableList<RemindToBioCreation> {

        var mutableList: MutableList<RemindToBioCreation> = mutableListOf();

        for (info in remindInfos) {
            var remind = RemindToBioCreation(
                info.time,
                period = info.period,
                info.measure,
                info.count_reception,
                info.last_reception,
                info.token
            )
            mutableList.add(remind)
        }

        return mutableList;
    }

    fun intToTimeString(minutes: Int): String {
        val hours = minutes / 60
        val remainingMinutes = minutes % 60
        return String.format("%02d:%02d", hours, remainingMinutes)
    }

    fun timeStringToInt(time: String): Int {

        val regex = """^\d{2}:\d{2}$""".toRegex()
        if (!regex.matches(time)) {
            return 0
        }

        val parts = time.split(":")
        val hours = parts[0].toInt()
        val minutes = parts[1].toInt()

        if (hours !in 0..23 || minutes !in 0..59) {
            return 0
        }

        return hours * 60 + minutes
    }

    suspend fun makeOperation() {
        when (this.operation) {
            Operation.CREATE -> {
                var bioAdditiveCreation = BioAdditiveCreation(
                    bioAdditiveInfo.name,
                    bioAdditiveInfo.description,
                    remindInfoToCreation(bioAdditiveInfo.reminds),
                    bioAdditiveInfo.bioType
                )

                this.create(bioAdditiveCreation);
            };
            Operation.DELETE -> {
                delete(bioAdditiveInfo.id)
            };
            Operation.UPDATE -> {
                var bioAdditiveUpdation = BioAdditiveUpdation(
                    bioAdditiveInfo.id,
                    bioAdditiveInfo.name,
                    bioAdditiveInfo.description,
                    remindInfoToCreation(bioAdditiveInfo.reminds),
                    bioAdditiveInfo.bioType
                )

                this.update(bioAdditiveUpdation);
            };

        }
    }
}