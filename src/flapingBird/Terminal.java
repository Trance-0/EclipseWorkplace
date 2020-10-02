package flapingBird;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

public class Terminal {

	public static void main(String[] args) {

		// 应用配置
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();

		config.width = 280; // 指定窗口宽度
		config.height = 512; // 指定窗口高度

		config.resizable = true; // 窗口设置为大小不可改变

		// 创建游戏主程序启动入口类 MainGame 对象, 传入配置 config, 启动游戏程序
		new LwjglApplication(new flapingBird(), config);
	}

}
