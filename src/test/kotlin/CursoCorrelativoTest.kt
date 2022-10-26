import io.kotest.core.spec.IsolationMode
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe


class CursoCorrelativoTest : DescribeSpec({
    isolationMode = IsolationMode.InstancePerTest
    describe("estudiante2") {
        val estudiante = Estudiante(
            nombre = "Franco",
            apellido = "Beckam",
            mail = "fBeckam@gmail.com")


        val curso3 = CursoBuilder(CursoPosta(nombre = "Algoritmo"))
            .CursoCorrelativa()
            .build()
        val curso4 = CursoBuilder(CursoPosta(nombre = "Algoritmo2"))
            .CursoCorrelativa()
            .build()

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
            //assertThat(curso4.dictarA(estudiante),equals("Error: no puede ser dictada"))





        }
    }


})
