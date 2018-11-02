public class SumLongHex {
	private static long fromHexToDec(String HexStr) {
		long result = Long.parseUnsignedLong(HexStr, 16); 
		return result;
	}
	
	public static void main(String[] args) {
		long sum = 0;
		for (int i = 0; i < args.length; i++) {
			for (String s : "0 ".concat(args[i]).split("\\p{javaWhitespace}+")) {
				String temp = s.toLowerCase();
				if (temp.startsWith("0x")) {
					sum += fromHexToDec(temp.substring(2));
				} else {
					sum += Long.valueOf(s);
				}	
			}	
		}
		System.out.println(sum); 
	}
}