package com.example.lab7

import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import android.widget.TextView
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction

class MainActivity2 : AppCompatActivity(), Comunicador {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        val fragmentLista = MiListFragment()
        supportFragmentManager.beginTransaction()
            .replace(R.id.list_fragment, fragmentLista).commit()
    }

    override fun entregarDatos(columna: Int, fila: Int) {
        val bundleC = Bundle()
        bundleC.putInt("columna", columna)
        bundleC.putInt("fila", fila)

        val transaction = this.supportFragmentManager.beginTransaction()
        val fragmentLista = MiListFragment()
        val fragmentTablero = Tablero()
        fragmentTablero.arguments = bundleC

        if (resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT) {
            transaction.replace(R.id.list_fragment, fragmentTablero)
            transaction.commit()
        }
        else {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_tablero, fragmentTablero)
                .show(supportFragmentManager.findFragmentById(R.id.list_fragment)!!)
                .commit()
        }

    }
}







