package Sandglass;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

public class SandglassLauncher {

	public static void main(String[] args) {

		// Ӧ������
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();

		config.width = 800; // ָ�����ڿ��
		config.height = 1911; // ָ�����ڸ߶�

		config.resizable = true; // ��������Ϊ��С���ɸı�

		// ������Ϸ��������������� MainGame ����, �������� config, ������Ϸ����
		new LwjglApplication(new Sandglass(), config);
	}

}
