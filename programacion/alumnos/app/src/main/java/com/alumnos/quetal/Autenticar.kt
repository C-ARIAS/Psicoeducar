package com.alumnos.quetal

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.iid.FirebaseInstanceId
import kotlinx.android.synthetic.main.autenticar.*

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class Autenticar : Fragment() {

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.autenticar, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<Button>(R.id.button_first).setOnClickListener {
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
        }
        sup()
        token()
    }



private fun token() {
FirebaseInstanceId.getInstance().instanceId.addOnCompleteListener {
    it.result?.token?.let {
println("este es el tolk:${it} ")
    }
}

}





    private fun sup() {

        button.setOnClickListener {
            if (email1.text.isNotEmpty() && editTextTextPassword.text.isNotEmpty()) {

                FirebaseAuth.getInstance()
                    .signInWithEmailAndPassword(
                        email1.text.toString(),
                        editTextTextPassword.text.toString()
                    ).addOnCompleteListener {
                        if (it.isSuccessful) {
                            show()

                        } else {
                            Alert()
                        }
                    }}

        }}


    private fun Alert() {
        val builder = AlertDialog.Builder(context)
        builder.setTitle("Error")
        builder.setMessage("el mail o la contraseña es incorrecta :( ")
        builder.setPositiveButton("Aceptar",null)
        val dialog: AlertDialog = builder.create()
        dialog.show()}
    private fun login() {
        val correcto = AlertDialog.Builder(context)
        correcto.setTitle("Cuenta creada :)")
        correcto.setPositiveButton("Aceptar",null)
        val noSe: AlertDialog = correcto.create()
        noSe.show()}
    enum class providerTipe {
        BASIC }
    private fun show() {
        val hola: Intent = Intent(context, logute::class.java)
        startActivity(hola)
}}