package com.user.pesador;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;

public class Juego extends ApplicationAdapter {
    private float delta;
    public static float stateTime;
    private float tiempoEntrePeces;
    private SpriteBatch batch;
    private ShapeRenderer bordes;
    private OrthographicCamera camara;
    public static final boolean debug = true;

    @Override
    public void create() {
        Assets.cargarTexturas();
        batch = new SpriteBatch();
        bordes = new ShapeRenderer();
        camara = new OrthographicCamera();
        Gdx.input.setInputProcessor(new Controles());
        stateTime = 0;
        tiempoEntrePeces = 0;
    }

    @Override
    public void resize(int width, int height) {
        camara.setToOrtho(false, Mundo.Ancho, Mundo.Alto);
        camara.update();
        //importane! pasar por la camara:
        batch.setProjectionMatrix(camara.combined);
        bordes.setProjectionMatrix(camara.combined);
    }

    @Override
    public void render() {
        refrescarPantalla();
        delta = Gdx.graphics.getDeltaTime();
        stateTime += delta;
        tiempoEntrePeces += delta;
        if (tiempoEntrePeces > Mundo.tiempoParaPez) {
            if (Mundo.peces.size() <= Mundo.maxPeces) {
                Mundo.peces.add(new Pez());
                tiempoEntrePeces = 0;
            }
        }
        bordes.begin(ShapeRenderer.ShapeType.Line);
        batch.begin();
        batch.draw(Assets.fondo, 0, 0, Mundo.Ancho, Mundo.Alto);
        renderPescador();
        renderAnzuelo();
        renderPeces();
        batch.end();
        bordes.end();
    }

    private void renderPeces() {
        for (int i = Mundo.peces.size() - 1; i > 0; i--) {
            Mundo.peces.get(i).actualizar(delta);
            Mundo.peces.get(i).dibujar(batch, bordes);
            if (Mundo.anzuelo.getMovimientoY() == MovimientoY.SUBIENDO && !Mundo.anzuelo.isPescando()) {
                if (Intersector.overlaps(
                        new Rectangle(Mundo.anzuelo.getPosicion().x, Mundo.anzuelo.getPosicion().y, Mundo.anzuelo.getTamaño().x, Mundo.anzuelo.getTamaño().y),
                        new Rectangle(Mundo.peces.get(i).getPosicion().x, Mundo.peces.get(i).getPosicion().y, (Mundo.peces.get(i).getTamaño().x * Mundo.porcentajeBoca), Mundo.peces.get(i).getTamaño().y))
                ) {
                    Mundo.peces.get(i).setPescado();
                    Mundo.anzuelo.setPescando();
                }
            }
            if (Mundo.peces.get(i).isMuerto()) {
                Mundo.peces.remove(i);
            }
        }
    }

    private void renderAnzuelo() {
        Mundo.anzuelo.actualizar(delta);
        Mundo.anzuelo.dibujar(batch, bordes);
    }

    private void renderPescador() {
        Mundo.pescador.actualizar(delta);
        Mundo.pescador.dibujar(batch, bordes);
    }

    private void refrescarPantalla() {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    }

    @Override
    public void dispose() {
        batch.dispose();
        Assets.liberarTexturas();
    }
}
