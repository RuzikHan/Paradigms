public class BinarySearch {
	//(x - int) && (a[i] <= a[i - 1], i:1..args.length - 1)
    public static void main(String[] args) {
        int x = Integer.valueOf(args[0]);
		int[] a = new int[args.length - 1];
		for (int i = 1; i < args.length; i++) {
			a[i - 1] = Integer.valueOf(args[i]);
		}
		int l = 0, r = args.length - 1;
		System.out.println(BinarySearchRec(l, r, x, a));
	}
	//(R = min(i): a[R] <= x) || (if (x < a[args.length - 1]) :R = args.length)
	
	//(x - int) && (a[i] <= a[i - 1], i:1..args.length - 1) && (l >= 0) && (l <= args.length - 1) && (r >= 0) && (r <= args.length - 1)
	public static int BinarySearchRec(int l, int r, int x, int[] a) {
	    if (l < r) {
			//I: (l <= l' <= r) && (r - l > r' - l')
		    int m = (l + r) / 2;
		    if (a[m] > x) {
			    return BinarySearchRec(m + 1, r, x, a);
		    } else {
			    return BinarySearchRec(l, m, x, a);
		    }
	    } else {
			return l;
		}
	}
	//(R = min(i): a[R] <= x) || (if (x < a[args.length - 1]) :R = args.length)
}