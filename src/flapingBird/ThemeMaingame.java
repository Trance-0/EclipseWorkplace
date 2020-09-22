package flapingBird;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
//���봦������Ŀ��ļ�
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;

/**
 * ��Ϸ������������, ʵ�� ApplicationListener �ӿ�
 */
public class ThemeMaingame implements Screen {
	private flapingBird flapingBird;
	private Stage stage;

	public ThemeMaingame(flapingBird t) {
		flapingBird = t;
	}

	private BitmapFont scoreText, fpsText;
	private Music bgm;
	// ����Ļ���
	private SpriteBatch batch;
	// ����
	private Texture texture;

	// ������
	private Sprite bg;

	private ActorBird bbb;
	private ActorPillar pillar;

	@Override
	public void show() {
		stage = new Stage();
		bbb = new ActorBird();
		pillar = new ActorPillar(bbb);
		bgm = Gdx.audio.newMusic(Gdx.files.internal("sounds\\gameBG.mp3"));
		scoreText = new BitmapFont(Gdx.files.internal("font\\Haha.fnt"));
		fpsText = new BitmapFont(Gdx.files.internal("font\\Haha.fnt"));
		batch = new SpriteBatch();
		texture = new Texture("background.png");
		bg = new Sprite(texture);
		bgm.setLooping(true);
		bgm.setVolume(0.1F);
		bgm.play();
		scoreText.getData().setScale(0.9F);
		fpsText.setColor(Color.CYAN);
		scoreText.setColor(Color.valueOf("FF00EEAA"));
		Gdx.app.log("Texture Width", texture.getWidth() + "px");
		Gdx.app.log("Texture Height", texture.getHeight() + "px");
		stage.addActor(bbb);
		stage.addActor(pillar);
	}

	@Override
	public void render(float delta) {
		/*
		 * ����������ɫΪ��ɫ��RGBA��,
		 * 
		 * LibGDX ��ʹ�� 4 ���������ͱ�����ֵ��Χ 0.0 ~ 1.0����ʾһ����ɫ���ֱ��ʾ��ɫ�� RGBA �ĸ�ͨ����,
		 * 
		 * ʮ��������ɫ�븡����ɫ֮���ת��: ��ʮ��������ɫ��ÿһ���������� 255 �õ��ĸ��������Ǹ�����ɫ��Ӧ��ͨ��ֵ��
		 */
		stage.act(delta);
		// ����Ļ���
		batch.begin();
		bg.draw(batch);
		scoreText.draw(batch, "����:" + Double.toString((double) pillar.score / 2), 10, 500);
		fpsText.draw(batch, "FPS:" + Gdx.graphics.getFramesPerSecond(), 10, 400);
		batch.end();

		stage.draw();
		if (pillar.gameover)
			flapingBird.showEnd();
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub

	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub

	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub

	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub

	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		// ���ٻ���,�ͷſռ�
		if (bgm != null) {
			bgm.dispose();
		}
		if (texture != null) {
			texture.dispose();
		}
	}
}