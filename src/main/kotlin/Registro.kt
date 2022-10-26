object Registro {

    val egresados = mutableMapOf<String, Int>()
    var cantidad: Int = 0

    fun registrar(curso: Curso) {
        if (!this.existeCurso(curso)) {
            this.crear(curso)
        } else {
            egresados[curso.getNombre()] = cantidad++
        }
    }

    fun crear(curso: Curso) {
        egresados.put(key = curso.getNombre(), value = cantidad++)
    }

    fun existeCurso(curso: Curso): Boolean {

        return egresados.any { it.key == curso.getNombre() }
    }

}