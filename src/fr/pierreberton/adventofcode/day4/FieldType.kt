package fr.pierreberton.adventofcode.day4

enum class FieldType {
    BYR, // Birth Year
    IYR, // Issue Year
    EYR, // Expiration Year
    HGT, // Height
    HCL, // Hair Color
    ECL, // Eye Color
    PID, // Passport ID
    CID; // Country ID

    fun isValid(data: String): Boolean {
        try {
            return when (this) {
                BYR -> data.toInt() in 1920..2002
                IYR -> data.toInt() in 2010..2020
                EYR -> data.toInt() in 2020..2030
                HGT -> {
                    when (data.takeLast(2)) {
                        "cm" -> data.dropLast(2).toInt() in 150..193
                        "in" -> data.dropLast(2).toInt() in 59..76
                        else -> false
                    }
                }
                HCL -> """#[0-9a-f]{6}""".toRegex().matches(data)
                ECL -> EyeColor.values().map { it.name }.contains(data.toUpperCase())
                PID -> """\d{9}""".toRegex().matches(data)
                CID -> return true
            }
        } catch (e: Exception) {
            return false
        }
    }
}

enum class EyeColor {
    AMB, BLU, BRN, GRY, GRN, HZL, OTH
}