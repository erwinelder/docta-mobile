package cz.cvut.docta.achievement.domain.model

enum class AchievementName(val key: String) {

    // ----------- General achievements ------------
    FirstStep(key = "first_step"),
    ConfidentStart(key = "confident_start"),
    EducationalMarathon(key = "educational_marathon"),
    KnowledgeIsPower(key = "knowledge_is_power"),
    TopStudent(key = "top_student"),

    // --------- Achievements for tests ------------
    FirstTest(key = "first_test"),
    OneHundredResult(key = "one_hundred_result"),
    SerialAStudent(key = "serial_a_student"),
    ErrorWinner(key = "error_winner"),
    SubjectMatterExpert(key = "subject_matter_expert"),

    // ----------- Special achievements ------------
    FlashStudent(key = "flash_student"),
    FutureProfessor(key = "future_professor"),
    NightCoder(key = "night_coder"),
    IronWill(key = "iron_will"),
    FacultyLegend(key = "faculty_legend");

    companion object {

        fun fromKey(key: String): AchievementName {
            val name = entries.find { it.key == key }
            if (name == null) {
                throw Exception("Achievement name not found")
            }
            return name
        }

        fun hasKey(key: String): Boolean = entries.find { it.key == key } != null

    }
}