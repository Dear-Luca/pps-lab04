package it.unibo.pps.tasks.adts

import org.junit.*
import org.junit.Assert.*
import it.unibo.pps.tasks.adts.SchoolModel.BasicSchoolModule
import it.unibo.pps.tasks.adts.SchoolModel.*

class SchoolModelTest:
  val schoolModule: SchoolModule = BasicSchoolModule
  import schoolModule.*

  @Test def testTeacherCons() =
    assertEquals("John", teacher("John"))


