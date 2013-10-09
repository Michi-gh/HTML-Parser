package org.deepnic.htmlparser;

import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class HTMLParseUtility {
	public static final String VERSION_KEY = "version";
	public static final String ENCODING_KEY = "encoding";

	public static String dropDecoration(String str, int headLen, int tailLen) {
		return str.substring(headLen, str.length() - tailLen).trim();
	}

	public static Map<String, String> getAttirbutes(String str) {
		Map<String, String> result = new HashMap<String, String>();
		StringTokenizer eTokens = new StringTokenizer(str, "=");
		if(!eTokens.hasMoreTokens()) {
			return result;
		}
		String rest = eTokens.nextToken();
		while (eTokens.hasMoreTokens()) {
			if (rest.length() == 0) {
				rest = eTokens.nextToken();
				continue;
			}
			String name = setSimpleAttributes(result, rest);

			String vSrc = eTokens.nextToken();
			switch (vSrc.charAt(0)) {
			case '"':
				rest = getValue(vSrc, "\"", result, name, eTokens);
				break;

			case '\'':
				rest = getValue(vSrc, "'", result, name, eTokens);
				break;

			default:
				StringTokenizer tokens = new StringTokenizer(vSrc);
				int len = tokens.nextToken().length();
				result.put(name, vSrc.substring(0, len));
				rest = vSrc.substring(len);
			}

		}
		setSimpleAttributes(result, rest);

		return result;
	}

	private static String setSimpleAttributes(Map<String, String> prop, String str) {
		String name = null;
		StringTokenizer tokens = new StringTokenizer(str);
		while (tokens.hasMoreTokens()) {
			name = tokens.nextToken();
			prop.put(name, name);
		}

		return name;
	}

	private static String getValue(String source, String quot, Map<String, String> target, String name, StringTokenizer tokens) {
		StringBuilder builder = new StringBuilder(source.substring(1));
		int len = -1;
		while ((len = builder.indexOf(quot)) < 0) {
			if (!tokens.hasMoreTokens()) {
				len = builder.length();
				break;
			}
			builder.append('=').append(tokens.nextToken());
		}
		target.put(name, builder.substring(0, len));
		return builder.substring(len);
	}
}
