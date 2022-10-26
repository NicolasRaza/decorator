import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe
import io.kotest.core.spec.IsolationMode


class CursoCupoLimitadoTest : DescribeSpec({
    isolationMode = IsolationMode.InstancePerTest
    describe("Curso con cupo limitado") {
        val estudiante = Estudiante(
            nombre = "Franco",
            apellido = "Beckam",
            mail = "fBeckam@gmail.com"
        )
        val curso = CursoBuilder(CursoPosta(nombre = "Programacion"))
            .CursoCupoLimitado(25)
            .build()

        curso.agregarAptitudesOtorgadas(listOf("Python"))
        it("el estudiante puede inscribirse") {
            estudiante.agregarAptitudesDeseadas("Python")
            curso.validarCursadaDe(estudiante) shouldBe true
        }
        it("el estudiante no puede inscribirse") {
            estudiante.agregarAptitudesDeseadas("JavaScrip")
            curso.validarCursadaDe(estudiante) shouldBe false
        }

    }


})














