package cz.cvut.fit.culkajac.dp;


public class Utils {
	public static int safeLongToInt(long l) {
		if (l < Integer.MIN_VALUE || l > Integer.MAX_VALUE) {
			throw new IllegalArgumentException(l + " cannot be cast to int without changing its value.");
		}
		return (int) l;
	}

	private static final String HEXES = "0123456789ABCDEF";

	private static String getHex(byte[] raw, String del) {
		if (raw == null) {
			return null;
		}
		final StringBuilder hex = new StringBuilder(2 * raw.length);
		for (final byte b : raw) {
			hex.append(HEXES.charAt((b & 0xF0) >> 4)).append(HEXES.charAt((b & 0x0F)));
			hex.append(del);
		}
		return hex.toString();
	}

}
