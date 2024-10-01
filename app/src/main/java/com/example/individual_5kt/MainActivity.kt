import java.util.Scanner

data class Usuario(
    val nombre: String,
    val apellido: String,
    val edad: Int,
    val correo: String,
    val sistemaSalud: String
)

fun main() {
    val scanner = Scanner(System.`in`)
    val usuarios = mutableListOf<Usuario>()

    println("¿Cuántos usuarios desea registrar?")
    val cantidad = scanner.nextLine().toInt()

    for (i in 1..cantidad) {
        println("=== Registro del usuario $i ===")

        print("Ingrese el nombre (1-20 caracteres): ")
        val nombre = validarNombre(scanner)

        print("Ingrese el apellido (solo letras): ")
        val apellido = validarApellido(scanner)

        print("Ingrese la edad (entre 0 y 120): ")
        val edad = validarEdad(scanner)

        print("Ingrese el correo (formato válido): ")
        val correo = validarCorreo(scanner)

        print("Seleccione el sistema de salud (Fonasa, Isapre, Particular): ")
        val sistemaSalud = validarSistemaSalud(scanner)

        usuarios.add(Usuario(nombre, apellido, edad, correo, sistemaSalud))
    }

    usuarios.sortBy { it.edad }

    println("\n=== Usuarios registrados ===")
    for (usuario in usuarios) {
        println("Nombre: ${usuario.nombre}, Apellido: ${usuario.apellido}, Edad: ${usuario.edad}, Correo: ${usuario.correo}, Sistema de Salud: ${usuario.sistemaSalud}")
    }
}

fun validarNombre(scanner: Scanner): String {
    var nombre: String
    do {
        nombre = scanner.nextLine()
        if (nombre.length !in 1..20) {
            println("Nombre inválido. Debe tener entre 1 y 20 caracteres.")
        }
    } while (nombre.length !in 1..20)
    return nombre
}

fun validarApellido(scanner: Scanner): String {
    var apellido: String
    do {
        apellido = scanner.nextLine()
        if (!apellido.all { it.isLetter() }) {
            println("Apellido inválido. Debe contener solo letras.")
        }
    } while (!apellido.all { it.isLetter() })
    return apellido
}

fun validarEdad(scanner: Scanner): Int {
    var edad: Int
    do {
        edad = scanner.nextLine().toIntOrNull() ?: -1
        if (edad !in 0..120) {
            println("Edad inválida. Debe ser un número entre 0 y 120.")
        }
    } while (edad !in 0..120)
    return edad
}

fun validarCorreo(scanner: Scanner): String {
    var correo: String
    do {
        correo = scanner.nextLine()
        if (!correo.matches(Regex("^[a-zA-Z0-9._-]+@[a-z]+\\.[a-z]+$"))) {
            println("Correo inválido. Ingrese un formato válido.")
        }
    } while (!correo.matches(Regex("^[a-zA-Z0-9._-]+@[a-z]+\\.[a-z]+$")))
    return correo
}

fun validarSistemaSalud(scanner: Scanner): String {
    var sistema: String
    do {
        sistema = scanner.nextLine()
        if (sistema.lowercase() !in listOf("fonasa", "isapre", "particular")) {
            println("Sistema de salud inválido. Debe ser Fonasa, Isapre o Particular.")
        }
    } while (sistema.lowercase() !in listOf("fonasa", "isapre", "particular"))
    return sistema.capitalize()
}
