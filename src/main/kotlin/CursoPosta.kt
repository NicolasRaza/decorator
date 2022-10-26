interface Curso {
    fun getNombre(): String

    fun validarCursadaDe(estudiante: Estudiante): Boolean

    fun dictarA(estudiante: Estudiante)

    fun agregarAptitudesOtorgadas(aptitudes: List<String>)

    fun agregarAptitudesRequeridas(aptitudes: List<String>)

    fun otorgaAptitud(aptitud: String): Boolean

    fun tieneAptitudes(): Boolean

    fun aptitudesOtorgadas(): MutableList<String>

    fun aptitudesRequeridas(): MutableList<String>

}

class CursoPosta(private val nombre: String) : Curso {

    val aptitudesOtorgadas: MutableList<String> = mutableListOf()
    val aptitudesRequiridas: MutableList<String> = mutableListOf()

    override fun getNombre(): String {
        return nombre
    }

    override fun validarCursadaDe(estudiante: Estudiante): Boolean {
        return estudiante.leInteresaCurso(this)
    }

    override fun dictarA(estudiante: Estudiante) {
        estudiante.agregarCurso(this)
    }

    override fun aptitudesOtorgadas(): MutableList<String> = aptitudesOtorgadas

    override fun aptitudesRequeridas(): MutableList<String> = aptitudesRequiridas

    override fun agregarAptitudesOtorgadas(aptitudes: List<String>) {
        if (!aptitudes.isNullOrEmpty()) {
            aptitudesOtorgadas.addAll(aptitudes)
        }
    }

    override fun agregarAptitudesRequeridas(aptitudes: List<String>) {
        if (!aptitudes.isNullOrEmpty()) {
            aptitudesRequiridas.addAll(aptitudes)
        }
    }


    override fun otorgaAptitud(aptitud: String): Boolean {
        return aptitudesOtorgadas.contains(aptitud)
    }

    override fun tieneAptitudes(): Boolean {
        return aptitudesOtorgadas.isNotEmpty()
    }
}

abstract class CursoInteresante(val curso: Curso) : Curso {

    override fun getNombre() = curso.getNombre()

    override fun validarCursadaDe(estudiante: Estudiante): Boolean {
        return curso.validarCursadaDe(estudiante)
    }

    override fun dictarA(estudiante: Estudiante) {
        curso.dictarA(estudiante)
    }

    override fun aptitudesOtorgadas() = curso.aptitudesOtorgadas()

    override fun aptitudesRequeridas() = curso.aptitudesRequeridas()

    override fun agregarAptitudesOtorgadas(aptitudes: List<String>) {
        curso.agregarAptitudesOtorgadas(aptitudes)
    }

    override fun agregarAptitudesRequeridas(aptitudes: List<String>) {
        curso.agregarAptitudesRequeridas(aptitudes)
    }

    override fun otorgaAptitud(aptitud: String): Boolean {
        return curso.otorgaAptitud(aptitud)
    }

    override fun tieneAptitudes(): Boolean {
        return curso.tieneAptitudes()
    }

}

class CursoCupoLimitado(curso: Curso, val cupoMaximo: Int) : CursoInteresante(curso) {

    var cupos: Int = 0

    override fun validarCursadaDe(estudiante: Estudiante): Boolean {
        if (!puedeInscribirse(estudiante)) {
            throw RuntimeException("el cupo esta al maximo")
        }
        cupos+=1
        return curso.validarCursadaDe(estudiante)
    }


    fun puedeInscribirse(estudiante: Estudiante): Boolean {
        return cupos <= cupoMaximo
    }

}

class CursoCorrelativa(curso: Curso) : CursoInteresante(curso) {

    fun puedeInscribirse(estudiante: Estudiante): Boolean {
        return curso.aptitudesRequeridas().all { estudiante.tieneAptitudEspecifica(it) }
    }

    override fun dictarA(estudiante: Estudiante) {
        if (!puedeInscribirse(estudiante)) {
            throw RuntimeException("Error: no puede ser dictada")
        }
        curso.dictarA(estudiante)
    }
}

class CursoCertificaciónDigital(curso: Curso) : CursoInteresante(curso) {
    lateinit var mailSender: MailSender

    override fun dictarA(estudiante: Estudiante) {
        curso.dictarA(estudiante)
        mailSender.enviarMail(Mail(estudiante.mail,"Usted" + estudiante.nombre + " " + estudiante.apellido +" se ha recibido del curso : " + curso.getNombre()))

    }
}


class CursoRegistroDecantidad(curso: Curso) : CursoInteresante(curso) {


    override fun dictarA(estudiante: Estudiante) {
        curso.dictarA(estudiante)
        Registro.registrar(this)
    }


}


class CursoBuilder(var curso: Curso) {

    fun CursoCupoLimitado(cupoMaximo: Int): CursoBuilder {
        curso = CursoCupoLimitado(curso, cupoMaximo)
        return this
    }

    fun CursoCorrelativa(): CursoBuilder {
        curso = CursoCorrelativa(curso)
        return this
    }

    fun CursoCertificaciónDigital(): CursoBuilder {
        curso = CursoCertificaciónDigital(curso)
        return this
    }

    fun CursoRegistroDecantidad(): CursoBuilder {
        curso = CursoRegistroDecantidad(curso)
        return this
    }

    fun build(): Curso {
        return curso
    }
}




