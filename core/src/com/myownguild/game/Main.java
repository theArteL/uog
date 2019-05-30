package com.myownguild.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Preferences;
import com.myownguild.game.screens.MenuScreen;

public class Main extends Game {

	public final static String GAME_NAME = "My Own Guild"; //для пк версии, название
	public final static int WIDTH = 720; // ширина
	public final static int HEIGHT = 1280; // высота

	public static SoundsManager soundsManager;
	public Preferences preferences;

	public String guildName = "YourGuild";

	public int guildLvl = 1;

	public int gold = 11111;
	public int day = 1111;

	public int curArmy = 1;
	public int maxArmy = guildLvl * 50;
	public int powerForEach = 1;

	public int power = curArmy * powerForEach;


	private boolean paused; // стоит ли на паузе игра, для проверок

	@Override
	public void create () {
		this.setScreen(new MenuScreen(this));

	}

	//
	public boolean isPaused() {
		return paused;
	}

	public void setPaused(boolean paused) {
		this.paused = paused;
	}
	//
	private void load(){
	}

	@Override
	public void dispose () {
	}
}
