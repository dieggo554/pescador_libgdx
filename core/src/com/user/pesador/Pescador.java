package com.user.pesador;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;

public class Pescador extends Personaje {

    private MovimientoX movimientoX;
    private float LimiteX = Mundo.Ancho - getTamaño().x;

    public Pescador(TextureRegion textura) {
        super(new Vector2(50, 128), new Vector2(50, 50), textura);
        movimientoX = MovimientoX.PARADO;
    }

    @Override
    public void actualizar(float delta) {
        switch (movimientoX) {
            case IZQUIERDA:
                setPosX(getPosicion().x - (delta * velocidad));
                break;
            case DERECHA:
                setPosX(getPosicion().x + (delta * velocidad));
                break;
        }

        if (getPosicion().x < 0) {
            setPosX(0);
        } else if (getPosicion().x > LimiteX) {
            setPosX(Mundo.Ancho - getTamaño().x);
        }

        Mundo.anzuelo.setPosX(getPosicion().x + Anzuelo.desplazamientoAnzuelo);
    }

    public void dibujar(SpriteBatch batch, ShapeRenderer bordes){
        batch.draw(textura, posicion.x, posicion.y, tamaño.x, tamaño.y);
        if (Juego.debug){
            bordes.rect(posicion.x, posicion.y, tamaño.x, tamaño.y);
        }
    }


    public void aIzquierda() {
        if (Mundo.anzuelo.getMovimientoY() != MovimientoY.PARADO) {
            movimientoX = MovimientoX.PARADO;
        } else {
            movimientoX = MovimientoX.IZQUIERDA;
        }
    }

    public void aDerecha() {
        if (Mundo.anzuelo.getMovimientoY() != MovimientoY.PARADO) {
            movimientoX = MovimientoX.PARADO;
        } else {
            movimientoX = MovimientoX.DERECHA;
        }
    }

    public void parar() {
        movimientoX = MovimientoX.PARADO;
    }
}