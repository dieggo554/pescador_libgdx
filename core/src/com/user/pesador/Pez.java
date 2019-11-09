package com.user.pesador;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import java.util.Random;

public class Pez extends Personaje {

    private boolean muerto;
    private MovimientoX movimientoX;
    private MovimientoY movimientoY;
    private Random aleatorio;
    private float muerteDerecha;
    private float muerteIzquierda;
    private float muerteArriba;

    public Pez() {
        aleatorio = new Random();
        iniciarPez();
    }

    private void iniciarPez() {
        movimientoY = MovimientoY.PARADO;
        switch(aleatorio.nextInt(Mundo.tiposPez)){
            case 0:
                setAnimacion(Assets.pez1Ani);
                break;
            case 1:
                setAnimacion(Assets.pez2Ani);
                break;
            case 2:
                setTextura(Assets.pez3);
                break;
        }

        setVelocidad(aleatorio.nextInt(Mundo.maxVelPez - Mundo.minVelPez) + Mundo.minVelPez);

        setTamY(aleatorio.nextInt(Mundo.tamMaxPez - Mundo.tamMinPez) + Mundo.tamMinPez);
        setTamX(getTamaño().y);

        setPosY(aleatorio.nextInt(Mundo.maxYPez));
        muerteDerecha = Mundo.Ancho + getTamaño().x;
        muerteIzquierda = 0 - getTamaño().x;
        muerteArriba = 156;
        if(aleatorio.nextBoolean()){
            desdeIzquierda();
        } else {
            desdeDerecha();
        }
    }

    private void desdeDerecha() {
        movimientoX = MovimientoX.IZQUIERDA;
        setPosX(Mundo.Ancho);
        setPosX(Mundo.Ancho + getTamaño().x);
    }

    private void desdeIzquierda() {
        movimientoX = MovimientoX.DERECHA;
        setPosX(0 - getTamaño().x);
        setTamX(getTamaño().x * -1);
    }

    @Override
    public void dibujar(SpriteBatch batch, ShapeRenderer bordes) {
        if (textura != null) {
            batch.draw(textura, posicion.x, posicion.y, tamaño.x, tamaño.y);
        } else {
            batch.draw(((TextureRegion)animacion.getKeyFrame(Juego.stateTime, true)), posicion.x, posicion.y, tamaño.x, tamaño.y);
        }
        if (Juego.debug){
            bordes.rect(posicion.x, posicion.y, (tamaño.x * Mundo.porcentajeBoca), tamaño.y);
        }
    }

    @Override
    public void actualizar(float delta) {
        switch (movimientoX){
            case DERECHA:
                setPosX(getPosicion().x + (delta * velocidad));
                break;
            case IZQUIERDA:
                setPosX(getPosicion().x - (delta * velocidad));
                break;
            case PARADO:
        }

        if (movimientoY == MovimientoY.SUBIENDO) {
            setPosY(Mundo.anzuelo.getPosicion().y);
        }

        if(getPosicion().x < muerteIzquierda | getPosicion().x > muerteDerecha | getPosicion().y > muerteArriba){
            setMuerto();
        }
    }

    public void setPescado() {
        movimientoX = MovimientoX.PARADO;
        movimientoY = MovimientoY.SUBIENDO;
    }

    public void setMuerto() {
        this.muerto = true;
    }

    public boolean isMuerto() {
        return muerto;
    }
}
