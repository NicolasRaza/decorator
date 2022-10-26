import io.kotest.core.spec.IsolationMode
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.assertThrows


class CursoCertificacionTest2 : DescribeSpec({
    isolationMode = IsolationMode.InstancePerTest
    describe("Test Curso con certificacion digital") {
        val estudiante = Estudiante(
            nombre = "Franco",
            apellido = "Beckam",
            mail = "fBeckam@gmail.com"
        )
        val mockedMailSender = mockedMailSender()

        val curso = CursoCertificaciónDigital(curso = CursoPosta(nombre = "Assembler")).apply {
            mailSender = mockedMailSender
        }


        it("El ganador recibe un mail") {
            curso.dictarA(estudiante)
            verify(exactly = 1) {
                mockedMailSender.enviarMail(
                    mail = Mail(estudiante.mail,"Usted" + estudiante.nombre + " " + estudiante.apellido +" se ha recibido del curso : " + curso.getNombre()))
            }
        }
    }
})

fun mockedMailSender(): MailSender = mockk<MailSender>(relaxUnitFun = true)