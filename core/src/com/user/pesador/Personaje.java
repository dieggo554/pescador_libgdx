package com.user.pesador;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;

public abstract class Personaje {
    protected Vector2 posicion, tamaño;
    protected TextureRegion textura;
    protected Animation animacion;
    protected float velocidad = 50;
	//...

    public Personaje(){
        posicion = new Vector2();
        tamaño = new Vector2();
    }

    public Personaje(Vector2 posicion, Vector2 tamaño, TextureRegion textura){
        this.posicion = posicion;
        this.tamaño = tamaño;
        this.textura = textura;
    }

    public abstract void dibujar(SpriteBatch batch, ShapeRenderer bordes);

    public abstract void actualizar(float delta);

    public void setPosicion(Vector2 posicion) {
        this.posicion = posicion;
    }

    public void setTamaño(Vector2 tamaño) {
        this.tamaño = tamaño;
    }

    public void setTextura(TextureRegion textura) {
        this.textura = textura;
    }

    public void setPosX(float posx){
        posicion.x=posx;
    }

    public void setPosY(float posy){
        posicion.y=posy;
    }

    public void setTamX(float tamX){
        tamaño.x=tamX;
    }

    public void setTamY(float tamY){
        tamaño.y=tamY;
    }

    public Vector2 getPosicion() {
        return posicion;
    }

    public Vector2 getTamaño() {
        return tamaño;
    }

    public void setVelocidad(float velocidad) {
        this.velocidad = velocidad;
    }

    public void setAnimacion(Animation animacion) {
        this.animacion = animacion;
    }

    public Animation getAnimacion() {
        return animacion;
    }
}