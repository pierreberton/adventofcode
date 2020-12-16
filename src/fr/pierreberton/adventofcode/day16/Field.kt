package fr.pierreberton.adventofcode.day16

enum class Field(vararg var rules: String) {
    DEPARTURE_LOCATION("49-920", "932-950"),
    DEPARTURE_STATION("28-106", "130-969"),
    DEPARTURE_PLATFORM("47-633", "646-950"),
    DEPARTURE_TRACK("41-839", "851-967"),
    DEPARTURE_DATE("30-71", "88-966"),
    DEPARTURE_TIME("38-532", "549-953"),
    ARRIVAL_LOCATION("38-326", "341-968"),
    ARRIVAL_STATION("27-809", "834-960"),
    ARRIVAL_PLATFORM("29-314", "322-949"),
    ARRIVAL_TRACK("26-358", "368-966"),
    CLASS("34-647", "667-951"),
    DURATION("39-771", "785-958"),
    PRICE("43-275", "286-960"),
    ROUTE("28-235", "260-949"),
    ROW("48-373", "392-962"),
    SEAT("35-147", "172-953"),
    TRAIN("37-861", "885-961"),
    TYPE("38-473", "483-961"),
    WAGON("49-221", "228-973"),
    ZONE("46-293", "307-967");

    fun isRespectingAtLeastOneRule(intToTest: Int): Boolean {
        var valid = false
        for (rule in rules) {
            val p1 = rule.split("-")[0]
            val p2 = rule.split("-")[1]
            if (intToTest >= p1.toInt() && intToTest <= p2.toInt()) {
                valid = true
            }
        }

        return valid
    }

    companion object {
        fun isRespectingAtLeastOneRule(intToTest: Int): Boolean {
            var valid = false
            values().forEach {
                valid = valid || it.isRespectingAtLeastOneRule(intToTest)
            }

            return valid
        }

        fun getPossibleFields(intToTest: Int): List<Field> {
            val list = mutableListOf<Field>()
            list.addAll(values().filter { it.isRespectingAtLeastOneRule(intToTest) })
            return list
        }
    }
}