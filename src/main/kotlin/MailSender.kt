interface MailSender {

    fun enviarMail(mail: Mail)

}

data class Mail(val to: String, val subject: String) {}