package com.user.pesador;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Assets {
    public static Texture fondo;
    public static TextureAtlas atlas;
    public static TextureRegion pescador,anzuelo,sedal,pez3;
    public static Animation pez1Ani, pez2Ani;

    public static Sound captura, finDelJuego;
    public static Music musicaDeFondo;

    public static void cargarTexturas(){
        fondo = new Texture(Gdx.files.internal("graficos/fondo.jpg"));

        captura=Gdx.audio.newSound(Gdx.files.internal("sonidos/captura.mp3"));
        finDelJuego=Gdx.audio.newSound(Gdx.files.internal("sonidos/finDelJuego.mp3"));
        musicaDeFondo=Gdx.audio.newMusic(Gdx.files.internal("sonidos/fondo.mp3"));

        atlas = new TextureAtlas(Gdx.files.internal("graficos/atlas.atlas"));
        pescador = atlas.findRegion("pescador");
        anzuelo = atlas.findRegion("anzuelo");
        sedal = atlas.findRegion("punto");
        TextureRegion pez1_animacion = atlas.findRegion("pez1_animacion");
        TextureRegion pez2_animacion = atlas.findRegion("pez2_animacion");
        pez3 = atlas.findRegion("pez3");
        pez1Ani = new Animation(0.15f,pez1_animacion.split(96, 96)[0]);
        pez2Ani = new Animation(0.15f,pez2_animacion.split(96, 96)[0]);
    }

    public static void liberarTexturas(){
        captura.dispose();
        musicaDeFondo.dispose();
        finDelJuego.dispose();
        fondo.dispose();
    //    fuente.dispose();
        atlas.dispose();
    }
}
