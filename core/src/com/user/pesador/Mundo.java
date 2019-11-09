package com.user.pesador;

import java.util.ArrayList;

public class Mundo {

    public static float TiempoDeJuego = 15;
    public static final int tiempoParaPez = 1;
    public static final int maxPeces = 10;
    public static final int tamMinPez = 20;
    public static final int tamMaxPez = 35;
    public static final int tiposPez = 3;
    public static final int maxYPez = 80;
    public static final int maxVelPez = 75;
    public static final int minVelPez = 25;
    public static final float porcentajeBoca = 0.20f;
    public static final float TIEMPO_BONIFICACION_PESCA = 5;

    public final static float Ancho = 332;
    public final static float Alto = 200;

    public static Pescador pescador = new Pescador(Assets.pescador);
    public static Anzuelo anzuelo = new Anzuelo(Assets.anzuelo);
    public static ArrayList<Pez> peces = new ArrayList<Pez>();
	//...
}