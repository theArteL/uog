package com.myownguild.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.SplitPane;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.myownguild.game.Main;
import com.myownguild.game.Missons;
import com.myownguild.game.StringSplit;
import com.myownguild.game.StylesUI;
import com.myownguild.game.UI.CustomLabel;
import com.myownguild.game.UI.CustomWindow;

public class GameScreen implements Screen {

    protected Main game; // protected, как я понял, позволяет в дочерних классах юзать себя
    protected Stage stage;  // каждый screen имеет свой stage
    protected OrthographicCamera camera; //камера
    protected SpriteBatch batch; // рисовалка

    //init ui
    Skin skin = StylesUI.skin;
    BitmapFont font = StylesUI.FONT;
    Window.WindowStyle windowStyle;
    TextButton.TextButtonStyle textButtonStyle;
    Label.LabelStyle labelStyle;

    Table table;
    // finals
    final int pad = 15;

    Window stats;
    Window guildInfo;
    Window missionInfo;

    //stats
    Label curGold;
    Label curDay;

    // guildInfo

    Label guildLvl;
    Label guildStats;

    // misson info
    Missons missons;
    Label missionName;
    Label missionLvl;
    Label missionHp;
    Label missionReward;
    //

    public GameScreen(Main game) {
        this.game = game;
        createCamera();
        stage = new Stage(new StretchViewport(Main.WIDTH, Main.HEIGHT, camera)); // скретч - обрезает, фит - сохраняеет соотношения
        batch = new SpriteBatch();
        Gdx.input.setInputProcessor(stage); // загружаем stage (???)
        init(); // проводим инцилизацию нужных компонентов
    }


    private void init() {
        skin.addRegions(StylesUI.ATLAS);

        //
        Styles();
        //
        table = new Table();
        table.setSize(game.WIDTH, game.HEIGHT);
        table.setDebug(true);
        //

        initLabels();
        initWindows();

        initWindowsActors();

        initTable();

        stage.addActor(table);
    }
    private void Styles(){
        //window style
        windowStyle = new Window.WindowStyle();
        windowStyle.background = skin.getDrawable("window");
        windowStyle.titleFont = StylesUI.FONT;
        //Label style
        labelStyle = new Label.LabelStyle();
        labelStyle.font = font;
        labelStyle.fontColor = Color.BLACK;
    }

    private void initTable(){
        table.add(stats).pad(pad).width(game.WIDTH/1.5f).height(game.HEIGHT/10);
        table.row();
        table.add(guildInfo).pad(pad).width(game.WIDTH/1.5f).height(game.HEIGHT/8);
        table.row();
        table.add(missionInfo).pad(pad).width(game.WIDTH/1.5f).height(game.HEIGHT/6);
        table.align(Align.top);
    }

    private void initLabels(){
        Label.LabelStyle statsLabelStyle = new Label.LabelStyle();
        Label.LabelStyle statsLabelStyle1 = new Label.LabelStyle();
        statsLabelStyle.font = StylesUI.FONT;
        statsLabelStyle1.font = StylesUI.FONT;
        statsLabelStyle.fontColor = Color.BLACK;
        statsLabelStyle1.fontColor = Color.ORANGE;
        curGold = new CustomLabel("Gold: " + game.gold, statsLabelStyle1);
        curDay = new CustomLabel("Day: "+game.day, statsLabelStyle);

        guildLvl = new CustomLabel("Guild LVL: " + game.guildLvl, labelStyle);

        guildStats = new CustomLabel("Army " + game.curArmy + "/" + game.maxArmy + " | " + "Power " + game.power + "pp", labelStyle);

        missons = new Missons(game.guildLvl);
        missionName = new CustomLabel(missons.getName(), labelStyle);
        missionLvl = new CustomLabel("Mission lvl: " + missons.getLvl().toString(), labelStyle);
        missionHp = new CustomLabel("CurHP" + missons.getReward().toString(), labelStyle);
        missionReward = new CustomLabel("Reward: " + missons.getReward().toString(), labelStyle);
    }

    private void initWindows(){
        guildInfo = new Window("Guild Info", windowStyle);
        guildInfo.setMovable(false);
        guildInfo.getTitleLabel().setAlignment(0);

        stats = new Window(game.guildName, windowStyle);
        stats.setMovable(false);
        stats.getTitleLabel().setAlignment(0);

        missionInfo = new Window("Mission", windowStyle);
        missionInfo.setMovable(false);
        missionInfo.getTitleLabel().setAlignment(0);
    }

    private void initWindowsActors(){
        stats.add(curGold).pad(5).expand();
        stats.add(curDay).pad(5).expand();
        guildInfo.add(guildLvl).pad(5).row();
        guildInfo.add(guildStats).pad(5);

        missionInfo.add(missionName).top().width(200).row();
        missionInfo.add(missionLvl).pad(pad);;
        missionInfo.add(missionReward).pad(pad).row();
        missionInfo.add(missionHp);

    }

    private void createCamera(){
        camera = new OrthographicCamera();
        camera.setToOrtho(false, Main.WIDTH, Main.HEIGHT);
        camera.update(); // обновление камеры
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        update(delta);
        batch.begin();
        batch.end();
    }

    private void update(float delta){
        Gdx.gl.glClearColor(0, 1, 0, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        camera.update();
        stage.act(delta);
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
