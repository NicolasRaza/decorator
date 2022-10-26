import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe


class CursoConRegistroTest : DescribeSpec({

    describe("estudiante2") {
        val estudiante = Estudiante(
            nombre = "Franco",
            apellido = "Beckam",
            mail = "fBeckam@gmail.com")


        val curso = CursoBuilder(CursoPosta(nombre = "Algoritmo"))
            .CursoRegistroDecantidad()
            .build()

        it("se testea cantidad de registro sea 0") {
            Registro.cantidad shouldBe 0
        }

        it("se testea cantidad de registro sea 1") {
            curso.dictarA(estudiante)
            Registro.cantidad shouldBe 1
        }

    }
})