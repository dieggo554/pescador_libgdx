package com.user.pesador;


import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;

public class Anzuelo extends Personaje {

    final static float desplazamientoAnzuelo = 41;
    final static float posY = 158;
    private MovimientoY movimientoY;
    private boolean pescando;

    public Anzuelo(TextureRegion textura) {
        super(new Vector2(Mundo.pescador.getPosicion().x + Mundo.pescador.getTamaño().x, posY), new Vector2(10, 10), textura);
        movimientoY = MovimientoY.PARADO;
        pescando = false;
    }

    @Override
    public void actualizar(float delta) {
        switch (movimientoY) {
            case SUBIENDO:
                setPosY(getPosicion().y + (delta * velocidad));
                break;
            case BAJANDO:
                setPosY(getPosicion().y - (delta * velocidad));
                break;
        }

        if (getPosicion().y < 0) {
            movimientoY = MovimientoY.SUBIENDO;
        } else if (getPosicion().y >= posY) {
            pescando = false;
            movimientoY = MovimientoY.PARADO;
            setPosY(posY);
        }
    }

    public void dibujar(SpriteBatch batch, ShapeRenderer bordes){
        batch.draw(textura, posicion.x, posicion.y, tamaño.x, tamaño.y);
        //sedal
        batch.draw(Assets.sedal, posicion.x + (tamaño.x/2), posicion.y + tamaño.y, 1,posY + tamaño.y - (posicion.y + tamaño.y));

        if (Juego.debug){
            //TODO
            bordes.rect(posicion.x, posicion.y, tamaño.x, tamaño.y);
        }
    }

    public boolean isPescando() {
        return pescando;
    }

    public void setPescando() {
        this.pescando = true;
    }

    public MovimientoY getMovimientoY(){
        return movimientoY;
    }

    public void bajar() {
        if (movimientoY == MovimientoY.PARADO) {
            movimientoY = MovimientoY.BAJANDO;
            Mundo.pescador.parar();
        }
    }

    public void subir() {
        movimientoY = MovimientoY.SUBIENDO;
    }
}