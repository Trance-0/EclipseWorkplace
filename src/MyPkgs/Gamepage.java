package MyPkgs;

import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Buttons;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
//���봦������Ŀ��ļ�
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;

/**
 * ��Ϸ������������, ʵ�� ApplicationListener �ӿ�
 */
public class Gamepage implements Screen {
	private Terminal terminal;

	public Gamepage(Terminal t) {
		terminal = t;
	}

	private float setTime;
	private TextureRegion animationRegion;
	private Animation flyingBird;
	private BitmapFont scoreText, fpsText;
	private boolean scorelock;
	private Music bgm;
	private Sound cs;
	private Sound fs;
	private Rectangle rb;
	private Rectangle ru;
	private Rectangle rd;
	private Random rand;
	private float gravity;
	private float birdSpeed;
	private int speed;
	private int score;
	// ����Ļ���
	private SpriteBatch batch;
	// ����
	private Texture texture;
	private Texture animationBird;

	// ������
	private Sprite bg, bird, up, dw;

	@Override
	public void show() {

		setTime = 0;
		scorelock = true;
		score = 0;
		bgm = Gdx.audio.newMusic(Gdx.files.internal("sounds\\gameBG.mp3"));
		cs = Gdx.audio.newSound(Gdx.files.internal("sounds\\ansRight.mp3"));
		fs = Gdx.audio.newSound(Gdx.files.internal("sounds\\ansWrong.mp3"));
		scoreText = new BitmapFont(Gdx.files.internal("font\\Haha.fnt"));
		fpsText = new BitmapFont(Gdx.files.internal("font\\Haha.fnt"));

		animationBird = new Texture("flyingBird.png");
//		animationBird.
		int frameW = animationBird.getWidth() / 8;
		int frameH = animationBird.getHeight();
		TextureRegion[][] tmp = TextureRegion.split(animationBird, frameW, frameH);

		flyingBird = new Animation(0.05F, tmp[0]);
		flyingBird.setPlayMode(Animation.PlayMode.LOOP);

		rb = new Rectangle();
		ru = new Rectangle();
		rd = new Rectangle();
		rand = new Random();
		gravity = 2.5F;
		speed = 100;
		birdSpeed = 0F;
		batch = new SpriteBatch();
		texture = new Texture("bg.png");
		bg = new Sprite(texture);
		texture = new Texture("bird.png");
		bird = new Sprite(texture);
		texture = new Texture("up.png");
		up = new Sprite(texture);
		texture = new Texture("dw.png");
		dw = new Sprite(texture);
		bg.setPosition(0, 0);
		bird.setPosition(50, 200);
		up.setPosition(200, 250);
		dw.setPosition(200, -150);
		bgm.setLooping(true);
		bgm.setVolume(0.1F);
		bgm.play();
		scoreText.getData().setScale(0.9F);
		fpsText.setColor(Color.CYAN);
		scoreText.setColor(Color.valueOf("FF00EEAA"));
		Gdx.app.log("Texture Width", texture.getWidth() + "px");
		Gdx.app.log("Texture Height", texture.getHeight() + "px");
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
//		Gdx.gl.glClearColor(1, 0, 0, 1);
//
//		// ����
//		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		if (up.getX() < -up.getWidth()) {
			scorelock = true;
			up.setY(rand.nextInt(240) + 180);
			dw.setY(up.getY() - 426);
			up.setX(290);
			dw.setX(290);
		}
		setTime += delta;
		animationRegion = flyingBird.getKeyFrame(setTime);

		birdSpeed += gravity;
		bird.translateY(-birdSpeed * Gdx.graphics.getDeltaTime());
		up.translateX(-speed * Gdx.graphics.getDeltaTime());
		dw.translateX(-speed * Gdx.graphics.getDeltaTime());
		bird.setRegion(animationRegion);
		// ����Ļ���
		batch.begin();
		bg.draw(batch);
		bird.draw(batch);
		up.draw(batch);
		dw.draw(batch);
//		batch.draw(animationRegion, bird.getX(), bird.getY());
		scoreText.draw(batch, "����:" + Double.toString((double) score / 2), 10, 500);
		fpsText.draw(batch, "FPS:" + Gdx.graphics.getFramesPerSecond(), 10, 400);
		batch.end();
		if (Gdx.input.isButtonPressed(Buttons.LEFT) && birdSpeed > 0) {

			cs.setVolume(cs.play(), 0.1F);

			birdSpeed = -speed;
		}
		if (up.getX() < bird.getX() && scorelock) {
			if (score >= speed - 90) {
				speed += 10;
			}
			score += 1;
			Gdx.app.log("Score", Integer.toString(score));

			Gdx.app.log("Speed", Integer.toString(speed));
			scorelock = false;
		}
		rb.set(bird.getX(), bird.getY(), bird.getWidth(), bird.getHeight());
		ru.set(up.getX(), up.getY(), up.getWidth(), up.getHeight());
		rd.set(dw.getX(), dw.getY(), dw.getWidth(), dw.getHeight());
		if (ru.overlaps(rb) || rd.overlaps(rb) || (bird.getY() > 512 || bird.getY() < 0)) {
			fs.setVolume(fs.play(), 0.1F);
			Gdx.app.log("Haha", "GameOver");
			terminal.showEnd();

		}
//		Gdx.app.log("���", Gdx.input.isButtonPressed(Buttons.LEFT));
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