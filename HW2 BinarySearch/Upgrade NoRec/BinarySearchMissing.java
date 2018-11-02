public class BinarySearchMissing {
	//(x - int) && (a[i] - int) && (a[i] <= a[i - 1], i:1..args.length - 1)
    public static void main(String[] args) {
        int x = Integer.valueOf(args[0]);
		int[] a = new int[args.length - 1];
		for (int i = 1; i < args.length; i++) {
			a[i - 1] = Integer.valueOf(args[i]);
		}
		int l = 0, r = args.length - 1;
		while (l < r) {
			//I: (0 <= l <= args.length - 1) && (0 <= r <= args.length - 1) && (l < r) && (l <= l' <= r) && (r - l > r' - l')
			int m = (l + r) / 2;
			//I && (0 <= l <= m <= r <= args.length - 1)
			if (a[m] > x) {
				//I && (a[m] > x)
				l = m + 1;
				//I && (l' = m + 1) && (r' = r)
			} else {
				//I && (a[m] <= x)
				r = m;
				//I && (l' = l) && (r' = m)
			}
		}
		if (l == args.length - 1 || a[l] != x) {
			//(a[i] != x, i:0..args.length - 1
			System.out.println(- l - 1);
		} else {
			//(l < args.length - 1) && (exist i:a[i] = x)
			System.out.println(l);
		}
	}
	//(R = min(i): a[R] <= x) || (if (x != a[i] i:0..args.length - 1) :R = - l - 1)
}