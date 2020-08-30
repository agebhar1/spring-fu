package org.springframework.samples.petclinic.visit

import org.springframework.dao.DataAccessException
import kotlin.jvm.Throws

interface VisitRepository {

    @Throws(DataAccessException::class)
    fun save(visit: Visit)

    fun findByPetId(petId: Int): Set<Visit>

}