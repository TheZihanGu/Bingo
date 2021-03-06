package github.apjifengc.bingo.util;

import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.bukkit.Bukkit;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;
import lombok.Getter;

public class Message {

	@Getter
	static YamlConfiguration config = new YamlConfiguration();

	public static String getMessage(String path, String... args) {
		String src = config.getString(path);
		if (src == null) {
			return "§c§l[Unknown Message]";
		}
		for (int i = 0; i < args.length; i++) {
			src = src.replace("%" + i + "$s", args[i]);
		}
		return src;
	}

	/**
	 * 在插件启用时运行，初始化 Message 类。
	 * 
	 * @throws IOException
	 * @throws InvalidConfigurationException
	 */
	public static void loadConfig() {
		Plugin plugin = Bukkit.getPluginManager().getPlugin("Bingo");
		File file = new File(plugin.getDataFolder(), "messages.yml");
		try {
			config.load(file);
		} catch (IOException | InvalidConfigurationException e) {
			e.printStackTrace();
		}
		YamlConfiguration defConfig = YamlConfiguration
				.loadConfiguration(new InputStreamReader(plugin.getResource("messages.yml")));
		config.setDefaults(defConfig);
	}

	/**
	 * 转换一串字符串中的 Unicode 字符。
	 * 
	 * @param src 源字符串
	 * @return 转换后的字符串
	 */
	public static String unicodeToCn(String src) {
		StringBuilder sb = new StringBuilder(src);
		while (true) {
			Matcher m = Pattern.compile("\\\\u[a-z,A-Z,0-9]{4}").matcher(sb.toString());
			if (!m.find()) {
				return sb.toString();
			}
			sb.replace(m.start(), m.end(), String
					.valueOf((char) Integer.valueOf(sb.toString().substring(m.start() + 2, m.end()), 16).intValue()));
		}
	}

}
