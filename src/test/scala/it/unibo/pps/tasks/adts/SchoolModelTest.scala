package it.unibo.pps.tasks.adts

import org.junit.*
import org.junit.Assert.*
import it.unibo.pps.tasks.adts.SchoolModel.BasicSchoolModule
import it.unibo.pps.tasks.adts.SchoolModel.*
import it.unibo.pps.u03.extensionmethods.Sequences.Sequence
import Sequence.*
import it.unibo.pps.tasks.adts.SchoolModel.BasicSchoolModule.*


class SchoolModelTest:
  val schoolModule: SchoolModule = BasicSchoolModule

  import schoolModule.*


  @Test def testTeacherCons(): Unit =
    assertEquals("John", teacher("John"))

  @Test def testCourseCons(): Unit =
    assertEquals("Math", course("Math"))

  @Test def testEmptySchoolCons(): Unit =
    assertEquals(BasicSchoolModule.School(Nil(), Nil(), Nil()), emptySchool)

  @Test def testGetCourses(): Unit =
    val courses = Cons("Math", Cons("DSA", Cons("Algebra", Nil())))
    val school = School(courses, Nil(), Nil())
    assertEquals(courses, school.courses)

  @Test def testGetTeachers(): Unit =
    val teachers = Cons("Mario", Cons("Luigi", Cons("Pippo", Nil())))
    val school = School(Nil(), teachers, Nil())
    assertEquals(teachers, school.teachers)

  @Test def testSetTeacherToCoursesEmptySchool(): Unit =
    val school = emptySchool.setTeacherToCourse(teacher("Mario"), course("Math"))
    val s = School(Cons("Math", Nil()), Cons("Mario", Nil()), Cons(("Mario", "Math"), Nil()))
    assertEquals(s, school)
      
    
    
    

