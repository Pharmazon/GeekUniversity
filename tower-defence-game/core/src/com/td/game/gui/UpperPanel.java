package com.td.game.gui;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.td.game.util.Assets;
import com.td.game.util.Config;

import java.io.Serializable;

public class UpperPanel implements Serializable {
    private transient Group core;
    private transient Label moneyLabel;
    private transient Label hpLabel;
    private transient BitmapFont font36;

    private StringBuilder tmpSB;
    private PlayerInfo playerInfo;

    public UpperPanel(PlayerInfo playerInfo, Stage stage, int x, int y) {
        this.playerInfo = playerInfo;
        font36 = Assets.getInstance().getAssetManager().get(Config.FONTS_PATH +
                "zorque36.ttf", BitmapFont.class);
        Label.LabelStyle labelStyle = new Label.LabelStyle();
        labelStyle.font = font36;
        core = new Group();
        tmpSB = new StringBuilder(20);
        hpLabel = new Label("", labelStyle);
        moneyLabel = new Label("", labelStyle);
        core.setPosition(x, y);
        hpLabel.setPosition(160, 30);
        moneyLabel.setPosition(400, 30);
        Image panelImage = new Image(Assets.getInstance().getAtlas().findRegion("upperPanel"));
        core.addActor(panelImage);
        core.addActor(hpLabel);
        core.addActor(moneyLabel);
        stage.addActor(core);
    }

    public void update() {
        tmpSB.setLength(0);
        tmpSB.append(playerInfo.getHp()).append(" / ").append(playerInfo.getHpMax());
        hpLabel.setText(tmpSB);
        tmpSB.setLength(0);
        tmpSB.append(playerInfo.getMoney());
        moneyLabel.setText(tmpSB);
    }
}
