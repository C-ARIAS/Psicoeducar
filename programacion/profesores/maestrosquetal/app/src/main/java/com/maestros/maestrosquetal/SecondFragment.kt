package com.maestros.maestrosquetal

import android.app.AlertDialog
import android.app.Notification
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.Navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.iid.FirebaseInstanceId
import kotlinx.android.synthetic.main.fragment_second.*










setup()





    private fun setup() {
        cualquiera.setOnClickListener {
            if  (editTextTextPassword.text.toString() != editTextTextPassword2.text.toString()) {

                login()
                base()
                notification()
            }

            if (editTextTextEmailAddress.text.isNotEmpty() && editTextTextPassword2.text.isNotEmpty() && editTextTextPassword.text.toString() == editTextTextPassword2.text.toString()) {

                FirebaseAuth.getInstance()
                    .createUserWithEmailAndPassword(
                        editTextTextEmailAddress.text.toString(),
                        editTextTextPassword2.text.toString()
                    ).addOnCompleteListener {
                        if (it.isSuccessful) {
                            showHome()
                            base()

                        } else {
                            showAlert()
                        }
                    }

            }
        }
    }

    private fun showAlert() {
        val builder = AlertDialog.Builder(context)
        builder.setTitle("Error")
        builder.setMessage("Hubo un error :( ¡La cuenta de mail deve ser valida y la clave deve tener almenos 6 digitos!")
        builder.setPositiveButton("Aceptar",null)
        val dialog: AlertDialog = builder.create()
        dialog.show()}
    private fun login() {
        val correcto = AlertDialog.Builder(context)
        correcto.setTitle("Error")
        correcto.setMessage("Hubo un error :( ¡Las contraseñas no coinsiden entre si!")
        correcto.setPositiveButton("Aceptar",null)
        val noSe: AlertDialog = correcto.create()
        noSe.show()}
    enum class providerTipe {
        BASIC }
    private fun showHome() {
        val hola: Intent = Intent(context, logute::class.java)
        startActivity(hola)
    }
private val db = FirebaseFirestore.getInstance()

    private fun base() {

    db.collection("maestros").document(Name.text.toString()).set(
        hashMapOf("Email" to editTextTextEmailAddress.text.toString())
    )

}
}