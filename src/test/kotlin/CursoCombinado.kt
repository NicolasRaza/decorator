import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe
import io.kotest.core.spec.IsolationMode


class CursoCombinado : DescribeSpec({
    isolationMode = IsolationMode.InstancePerTest
    describe("Curso combinado con cupo limitado y correlativo") {
        val estudiante = Estudiante(
            nombre = "Franco",
            apellido = "Beckam",
            mail = "fBeckam@gmail.com"
        )
        val curso = CursoBuilder(CursoPosta(nombre = "Assembler"))
            .CursoCupoLimitado(25)
            .CursoCorrelativa()
            .build()
        val curso3 = CursoBuilder(CursoPosta(nombre = "Algoritmo"))
            .CursoCupoLimitado(25)
            .CursoCorrelativa()
            .build()
        val curso4 = CursoBuilder(CursoPosta(nombre = "Algoritmo2"))
            .CursoCupoLimitado(25)
            .CursoCorrelativa()
            .build()

        curso.agregarAptitudesOtorgadas(listOf("Python"))
        it("el estudiante puede inscribirse") {
            estudiante.agregarAptitudesDeseadas("Python")
            val cupos = 15
            curso.validarCursadaDe(estudiante) shouldBe true
        }
        it("el estudiante no puede inscribirse") {
            estudiante.agregarAptitudesDeseadas("JavaScrip")
            val cupos = 25
            curso.validarCursadaDe(estudiante) shouldBe false
        }
        it("Puede inscribirse a la materia algoritmo 2") {
            curso3.dictarA(estudiante)
            curso3.otorgaAptitud("Algoritmo") shouldBe false
            curso3.agregarAptitudesOtorgadas(listOf("Algoritmo"))
            curso3.otorgaAptitud("Algoritmo") shouldBe true
            estudiante.tieneAptitudEspecifica("Algoritmo") shouldBe true
            curso4.agregarAptitudesRequeridas(listOf("Algoritmo2"))
            curso4.aptitudesRequeridas().contains("Algoritmo2") shouldBe true

        }

        it("NO Puede inscribirse a la materia algoritmo 2") {
            curso3.otorgaAptitud("Algoritmo") shouldBe false
            curso3.agregarAptitudesOtorgadas(listOf("Algoritmo"))
            curso3.otorgaAptitud("Algoritmo") shouldBe true
            curso4.agregarAptitudesRequeridas(listOf("Algoritmo2"))
            curso4.aptitudesRequeridas().contains("Algoritmo2") shouldBe true
            curso4.agregarAptitudesOtorgadas(listOf("Algoritmo2"))
            // curso4.dictarA(estudiante) FALTA QUE RETORNE UN TROW EXCEPCION


        }
    }

})