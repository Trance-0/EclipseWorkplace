package flapingBird;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

public class Terminal {

	public static void main(String[] args) {

		// Ӧ������
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();

		config.width = 280; // ָ�����ڿ��
		config.height = 512; // ָ�����ڸ߶�

		config.resizable = true; // ��������Ϊ��С���ɸı�

		// ������Ϸ��������������� MainGame ����, �������� config, ������Ϸ����
		new LwjglApplication(new flapingBird(), config);
	}

}
