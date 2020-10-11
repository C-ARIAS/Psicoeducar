package com.alumnos.quetal

import android.app.AlertDialog
import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat.startActivity
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.crearcuenta.*
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.app.AppOpsManagerCompat
import androidx.core.text.isDigitsOnly
import androidx.navigation.ui.AppBarConfiguration
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.resources.CancelableFontCallback
import kotlin.math.log


/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */





class crearcuenta : Fragment() {
    private val db = FirebaseFirestore.getInstance()





    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.crearcuenta, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<Button>(R.id.button_second).setOnClickListener {
            findNavController().navigate(R.id.action_SecondFragment_to_FirstFragment)
        }
        setup()


    }



    private fun setup() {

        button2.setOnClickListener {

            if (editTextTextPassword2.text.toString() != editTextTextPassword3.text.toString()) {
                login()

            }

            if (editTextTextEmailAddress.text.isNotEmpty() && editTextTextPassword2.text.isNotEmpty() && editTextTextPassword3.text.toString() == editTextTextPassword2.text.toString()) {

                FirebaseAuth.getInstance()
                    .createUserWithEmailAndPassword(
                        editTextTextEmailAddress.text.toString(),
                        editTextTextPassword2.text.toString()
                    ).addOnCompleteListener {
                        if (it.isSuccessful) {
prueba()
                            showHome()

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
        builder.setPositiveButton("Aceptar", null)
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }

    fun prueba () {
        db.collection("maestros").whereEqualTo("maestros",Emailmaestro.text.toString()).get().addOnSuccessListener {

val nombre= it.documents.toString()

            db.collection("maestros").document(nombre).collection("alumnos").document(editTextTextPersonName.text.toString()).set(
                    hashMapOf("Email" to editTextTextEmailAddress.text.toString(),
                    "Nombre" to editTextTextPersonName.text.toString())
                )

            db.collection("alumnos").document().set(
                hashMapOf("profe" to Emailmaestro.text.toString()))

    }}




    private fun login() {
        val correcto = AlertDialog.Builder(context)
        correcto.setTitle("Error")
        correcto.setMessage("Hubo un error :( ¡Las contraseñas no coinsiden entre si!")
        correcto.setPositiveButton("Aceptar", null)
        val noSe: AlertDialog = correcto.create()
        noSe.show()
    }

    private fun error() {
        val correcto = AlertDialog.Builder(context)
        correcto.setTitle("Error")
        correcto.setMessage("Hubo un error :( ¡La cuenta de mail del maestro suministrada no coinside con ninguna cuenta registrada!")
        correcto.setPositiveButton("Aceptar", null)
        val noSe: AlertDialog = correcto.create()
        noSe.show()
    }


    private fun showHome() {
        val hola: Intent = Intent(context, logute::class.java)
        startActivity(hola)}
    }








