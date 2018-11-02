public class SumLong {
    public static void main(String[] args) {
        long s = 0;
        for (int i = 0; i < args.length; i++) {
			args[i].trim();
            for (String ms: args[i].split("\\p{javaWhitespace}+")) {
                    s += Long.valueOf(ms);
            }
        }
        System.out.println(s);
    }
}