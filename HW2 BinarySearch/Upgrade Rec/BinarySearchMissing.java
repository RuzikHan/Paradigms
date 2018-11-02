public class BinarySearchMissing {
	//(x - int) && (a[i] - int) && (a[i] <= a[i - 1], i:1..args.length - 1)
    public static void main(String[] args) {
        int x = Integer.valueOf(args[0]);
		int[] a = new int[args.length - 1];
		for (int i = 1; i < args.length; i++) {
			a[i - 1] = Integer.valueOf(args[i]);
		}
		int l = 0, r = args.length - 1;
		int ans = BinarySearchRec(l, r, x, a);
		if (ans == args.length - 1 || a[ans] != x) {
			//(a[i] != x, i:0..args.length - 1
			System.out.println(- ans - 1);
		} else {
			//(ans < args.length - 1) && (exist i:a[i] = x)
			System.out.println(ans);
		}
	}
	//(R = min(i): a[R] <= x) || (if (x != a[i] i:0..args.length - 1) :R = - ans - 1)
	
	//(x - int) && (a[i] - int) && (l, r - int) && (a[i] <= a[i - 1], i:1..args.length - 1) && (0 <= l <= args.length - 1) && (0 <= r <= args.length - 1) && (l <= r)
	public static int BinarySearchRec(int l, int r, int x, int[] a) {
	    if (l < r) {
			//I: (0 <= l <= args.length - 1) && (0 <= r <= args.length - 1) && (l < r) && (l <= l' <= r) && (r - l > r' - l')
		    int m = (l + r) / 2;
			//I && (0 <= l <= m <= r <= args.length - 1)
		    if (a[m] > x) {
				//I && (a[m] > x) 
			    return BinarySearchRec(m + 1, r, x, a);
		    } else {
				//I && (a[m] <= x)
			    return BinarySearchRec(l, m, x, a);
		    }
	    } else {
			return l;
		}
	}
	//(R = min(i): a[R] <= x) || (if (x != a[i] i:0..args.length - 1) :R = - l - 1)
}