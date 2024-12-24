package cz.cvut.docta.section.domain.usecase

import cz.cvut.docta.section.domain.model.Section

interface GetCourseSectionsUseCase {
    suspend fun execute(courseCode: String): List<Section>
}