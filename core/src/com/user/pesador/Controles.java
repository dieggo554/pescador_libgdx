package com.user.pesador;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;

public class Controles implements InputProcessor {
    @Override
    public boolean keyDown(int keycode) {

        switch (keycode) {
            case Input.Keys.DPAD_DOWN:
                Mundo.anzuelo.bajar();
                break;
            case Input.Keys.DPAD_LEFT:
                Mundo.pescador.aIzquierda();
                break;
            case Input.Keys.DPAD_RIGHT:
                Mundo.pescador.aDerecha();
                break;
            default:
                return false;
        }
        return true;

    }

    @Override
    public boolean keyUp(int keycode) {

        switch (keycode) {

            case Input.Keys.SPACE:
            case Input.Keys.DPAD_DOWN:
                    Mundo.anzuelo.subir();
                break;

            case Input.Keys.DPAD_LEFT:
                if (Gdx.input.isKeyPressed(Input.Keys.DPAD_RIGHT)) {
                    Mundo.pescador.aDerecha();
                } else {
                    Mundo.pescador.parar();
                }
                break;
            case Input.Keys.DPAD_RIGHT:
                if (Gdx.input.isKeyPressed(Input.Keys.DPAD_LEFT)) {
                    Mundo.pescador.aIzquierda();
                } else {
                    Mundo.pescador.parar();
                }
                break;
            default:
                return false;
        }
        return true;

    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }
}

enum MovimientoX {
    DERECHA,IZQUIERDA,PARADO
}

enum MovimientoY {
    SUBIENDO,BAJANDO, PARADO
}