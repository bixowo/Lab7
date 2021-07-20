package com.example.lab7

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.core.view.get
import androidx.fragment.app.Fragment


class Tablero : Fragment() {

    var m: Int = 0 // columnas
    var n: Int = 0 // filas
    var xMoves = arrayOf(2, 1, -1, -2, -2, -1, 1, 2) // Movimientos posibles del caballo en x
    var yMoves = arrayOf(1, 2, 2, 1, -1, -2, -2, -1) // Movimientos posibles del caballo en y
    var positions = Array(8) { 0 } // Movimientos (Filas,Columnas) pero para el indice Position.

    override fun onCreateView(
        inflater: LayoutInflater, g: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
// Infla la distribucion de elementos para este fragmento
        val view = inflater.inflate(R.layout.fragment_tablero, g, false)

        m = arguments?.getInt("columna")!!
        n = arguments?.getInt("fila")!!

        for (i in positions.indices) {
            positions[i] = xMoves[i] + yMoves[i] * n
        }
        return view
    }


    @SuppressLint("ResourceAsColor")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var mutableList = mutableListOf<Any>()
        var mutableListM = mutableListOf<Any>()
        var mutableListN = mutableListOf<Any>()
        var arrayAdapter: ArrayAdapter<*>
        var Tablero =
            getView()?.findViewById<GridView>(R.id.lista_tablero) // Elemento Grafico que llenara los datos
        var count = 1

        // Lista de Filas

        for (i in 1 until (n + 1)) {
            for (j in 0 until n) {
                mutableListM.add("$i")
            }
        }
        // Lista de Columnas
        for (i in 0 until n) {
            for (j in 1 until n + 1) {
                mutableListN.add("$j")
            }
        }
        // Lista del GridView
        for (i in 0 until m * n) {
            mutableList.add("${mutableListM[i]},${mutableListN[i]}")
        }

        arrayAdapter = activity?.let {
            ArrayAdapter(
                it,
                android.R.layout.simple_list_item_1,
                mutableList
            )
        }!!
        Tablero?.adapter = arrayAdapter
        Tablero?.numColumns = n

        var matrizSolucion = Array(m) { Array(n) { 0 } } // Matriz que almacena las casillas visitadas
        var matrizRestriccion = Array(m) { Array(n) { 0 } } // Matriz variable que restringe el movimiento
        var matrizHamilton = Array(m) { Array(n) { 1 } } // Matriz que almacena primera casilla
        var restriccionHamilton = Array(m) { Array(n) { 1 } } // Movimientos posibles libres de restricc

        var suma: Int
        var sumaHamilton: Int
        Tablero?.setOnItemClickListener { _, _: View, pos: Int, _: Long ->

            var x = pos % n
            var y = pos / n
            var column = x
            var row = y


            if (matrizRestriccion[row][column] == 0 && matrizSolucion[row][column] == 0) {
                for (j in 0..m * n - 1) {

                    Tablero.get(j).isEnabled = false
                    Tablero.get(j).isFocusable = false
                }
                for (i in 0..m - 1) {
                    for (p in 0..n - 1) {
                        matrizRestriccion[i][p] = 1
                        restriccionHamilton[i][p] = 1
                    }
                }
                if (count == 1) {
                    matrizHamilton[row][column] = 0
                }
                for (k in positions.indices) {

                    if (pos + positions[k] >= 0 && pos + positions[k] <= m * n - 1) {

                        for (k in xMoves.indices) {

                            if (row + yMoves[k] >= 0 && column + xMoves[k] >= 0 && row + yMoves[k] < m && column + xMoves[k] < n) {

                                if (matrizSolucion[row + yMoves[k]][column + xMoves[k]] == 0) {
                                    Tablero.get(pos + positions[k]).isEnabled = true
                                    Tablero.get(pos).setBackgroundColor(R.color.black)
                                    matrizRestriccion[row + yMoves[k]][column + xMoves[k]] = 0
                                    matrizRestriccion[row][column] = 1
                                    matrizSolucion[row][column] = 1
                                }
                            restriccionHamilton[row + yMoves[k]][column + xMoves[k]] = 0
                            }
                        }
                    }
                }
            val texto = getView()?.findViewById<TextView>(R.id.contador)
            texto?.setText("Jugada numero: " + count)
            // Toast.makeText(activity, "Casilla seleccionada ${y+1},${x+1}", Toast.LENGTH_SHORT).show()
            count = count + 1
            suma = 0
            sumaHamilton = 0
            for (i in 0..m-1) {
                for (j in 0..n-1) {
                    if (matrizSolucion[i][j] == 1 || matrizRestriccion[i][j] == 1) {
                    suma = suma + 1}
                    if (matrizHamilton[i][j] == 0 && restriccionHamilton[i][j] == 0) {
                        sumaHamilton = sumaHamilton + 1
                    }
                }
            }
                if (suma == m*n) {
                    val fin = getView()?.findViewById<FrameLayout>(R.id.finalizar)
                    fin?.alpha = 1f
                    val fin1 = getView()?.findViewById<TextView>(R.id.finalizado)
                    count = count - 1
                    fin1?.setText("Puntuación: " + count)
                    val fin2 = getView()?.findViewById<TextView>(R.id.finalizado2)
                    if (count == m*n && sumaHamilton == 1)
                        fin2?.setText("Felicidades! Se ha realizado un ciclo Hamiltoniano.")
                    else if (count == m*n )
                    fin2?.setText("Se ha realizado un ciclo abierto.")
                    else
                        fin2?.setText("No se ha podido completar un ciclo =(.")
                }




        }

            else if (matrizSolucion[row][column] == 1) {
                Toast.makeText(activity, "Casilla ya visitada", Toast.LENGTH_SHORT).show()

            }

            else { Toast.makeText(activity, "Movimiento incorrecto", Toast.LENGTH_SHORT).show() }





        }


    }

}