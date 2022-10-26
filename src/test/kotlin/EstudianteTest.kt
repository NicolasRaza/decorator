import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe


class EstudianteTest : DescribeSpec({
    describe("estudiante") {
        val estudiante = Estudiante(
            nombre = "Franco",
            apellido = "Beckam",
            mail = "fBeckam@gmail.com"
        )
        val curso = CursoBuilder(CursoPosta(nombre = "Programacion"))
            .build()
        val curso2 = CursoBuilder(CursoPosta(nombre = "Programacion2"))
            .build()
        it("se agregarAptitudesDeseadas agregue un aptitud a la lista") {
            estudiante.aptitudesDeseada.contains("python") shouldBe false
            estudiante.agregarAptitudesDeseadas("python")
            estudiante.aptitudesDeseada.contains("python") shouldBe true
        }
        it("se testea que AgregarCurso agregue un curso a la lista") {
            estudiante.cursosRealizados.contains(curso) shouldBe false
            estudiante.agregarCurso(curso)
            estudiante.cursosRealizados.contains(curso) shouldBe true
        }
        it("se testea quitar aptitud deseada") {
            estudiante.aptitudesDeseada.contains("python") shouldBe true
            estudiante.quitarAptitudesDeseadas("python")
            estudiante.aptitudesDeseada.contains("python") shouldBe false
        }
        it("se testea tieneAptitudEspecifica ") {
            curso.agregarAptitudesOtorgadas(listOf("python"))
            estudiante.agregarCurso(curso)
            estudiante.tieneAptitudEspecifica("python") shouldBe true
        }
        it("se testea no tieneAptitudEspecifica") {
            curso.agregarAptitudesOtorgadas(listOf("python"))
            estudiante.agregarCurso(curso)
            estudiante.tieneAptitudEspecifica("kotlin") shouldBe false
        }
        it("se testea LeInteresa ") {
            curso.agregarAptitudesOtorgadas(listOf("python", "kotlin", "java"))
            estudiante.agregarAptitudesDeseadas("python")
            estudiante.leInteresaCurso(curso) shouldBe true
            curso2.agregarAptitudesOtorgadas(listOf("javaScript"))
            estudiante.leInteresaCurso(curso2) shouldBe false
        }

    }
})

