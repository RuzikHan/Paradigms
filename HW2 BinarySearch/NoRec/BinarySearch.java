public class BinarySearch {
	//(x - int) && (a[i] <= a[i - 1], i:1..args.length - 1)
    public static void main(String[] args) {
        int x = Integer.valueOf(args[0]);
		int[] a = new int[args.length - 1];
		for (int i = 1; i < args.length; i++) {
			a[i - 1] = Integer.valueOf(args[i]);
		}
		int l = 0, r = args.length - 1;
		while (l < r) {
			//I: (l <= l' <= r) && (r - l > r' - l')
			int m = (l + r) / 2;
			if (a[m] > x) {
				l = m + 1;
			} else {
				r = m;
			}
		}
		System.out.println(l);
	}
	//(R = min(i): a[R] <= x) || (if (x < a[args.length - 1]) :R = args.length)
}