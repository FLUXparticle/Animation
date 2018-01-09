package de.fluxparticle.animation.output;

public class Log {

	public static Log getLog(int level) {
		return new Log(level);
	}

	private final String indent;

	private Log(int level) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < level; i++) {
			sb.append(' ');
			sb.append(' ');
		}
		indent = sb.toString();
	}

	public void info(String line) {
//		System.out.println(indent + line);
	}

}
