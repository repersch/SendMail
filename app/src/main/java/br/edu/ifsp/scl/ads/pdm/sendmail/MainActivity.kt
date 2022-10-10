package br.edu.ifsp.scl.ads.pdm.sendmail

import android.content.Intent
import android.content.Intent.*
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.edu.ifsp.scl.ads.pdm.sendmail.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val amb: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(amb.root)

        amb.cleanBt.setOnClickListener {
            with (amb) {
                toEt.setText("")
                ccEt.setText("")
                bccEt.setText("")
                subjectEt.setText("")
                messageEt.setText("")
            }
        }

        amb.sendBt.setOnClickListener {
            val sendMailIntent = Intent(ACTION_SENDTO)

            with (sendMailIntent) {
                putExtra(EXTRA_EMAIL, arrayOf(amb.toEt.text.toString()))
                putExtra(EXTRA_CC, arrayOf(amb.ccEt.text.toString()))
                putExtra(EXTRA_BCC, arrayOf(amb.bccEt.text.toString()))
                putExtra(EXTRA_SUBJECT, arrayOf(amb.subjectEt.text.toString()))
                putExtra(EXTRA_TEXT, arrayOf(amb.messageEt.text.toString()))
                // compoem a intent que envia o email
                type = "message/rfc822"
                data = Uri.parse("mailto:")
            }

            // intent que possibilita que o usuário escolha um app de envio de email
            val chooserIntent = Intent(ACTION_CHOOSER)
            // encapsulando uma intent dentro da outra
            chooserIntent.putExtra(EXTRA_INTENT, sendMailIntent)
            startActivity(chooserIntent)
        }
    }
}
