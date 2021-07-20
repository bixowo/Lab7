package com.example.lab7

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import android.content.Intent
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.fragment.app.ListFragment
import java.lang.RuntimeException

class MiListFragment : ListFragment() {

    private lateinit var comunicador: Comunicador

    var m = 0
    var n = 0
    private var fragmentView: View? = null
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
// Infla el archivo XML para este fragmento
        val fragmentView = inflater.inflate(R.layout.list_fragment, container, false)

        comunicador = activity as Comunicador

        return fragmentView

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val tableros = resources.getStringArray(R.array.misTableros)

// Un listFragment incluye su propia ListView llamada listView

        listView.adapter = ArrayAdapter(requireContext(), android.R.layout.simple_list_item_checked, tableros)
        listView.choiceMode = ListView.CHOICE_MODE_SINGLE
        listView.setOnItemClickListener { _, _: View, pos: Int, _: Long ->
            val s = tableros[pos]
            when (pos) {
                0 -> {  m = 3;  n = 4}
                1 -> {  m = 3;  n = 7}
                2 -> {  m = 3;  n = 8}
                3 -> {  m = 3;  n = 9}
                4 -> {  m = 3;  n = 10}
                5 -> {  m = 4;  n = 5}
                6 -> {  m = 4;  n = 6}
                7 -> {  m = 4;  n = 7}
                8 -> {  m = 4;  n = 8}
                9 -> {  m = 4;  n = 9}
                10-> {  m = 5;  n = 5}
                11-> {  m = 5;  n = 6}
                12-> {  m = 5;  n = 7}
                13-> {  m = 5;  n = 8}
                14-> {  m = 5;  n = 9}
            }
            Toast.makeText(context, "Se escogió $s", Toast.LENGTH_SHORT).show()
            comunicador.entregarDatos(m,n)
        }

    }


}