public class BinarySearchSpan {
	// (x - int) && (a[i] - int) && (a[i - 1] >= a[i], i:1..args.length - 1)
    public static void main(String[] args) {
        int x = Integer.valueOf(args[0]);
        int[] a = new int[args.length - 1];
        for (int i = 1; i < args.length; i++) {
            a[i - 1] = Integer.valueOf(args[i]);
        }
        int l = 0, r = 0;
		l = BinarySearchRecl(0, args.length - 1, x, a);
		if (l == args.length - 1 || a[l] != x) {
           System.out.println(l + " " + 0);
        }
        else {
	        r = BinarySearchRec(0, args.length - 1, x, a);
	        // r >= l
	        System.out.print(l + " ");
	        System.out.println(r - l + 1);
	    }
    }
	// (R = l..r: a[l] = a[l + 1] = ... = a[r] = x) && (if (x < a[i], i:1..args.length - 1) R = args.length - 1..0)
	
    // (x - int) && (a[i] - int) && (a[i - 1] >= a[i], i:1..args.length - 1) && (0 <= l < r <= args.length - 1)   
    public static int BinarySearchRecl(int l, int r, int x, int[] a) {
		// I: (0 <= l <= args.length - 1) && (0 <= r <= args.length - 1) && (r - l > r' - l') && (a[i]' = a[i], i:1..args.length - 1)
        if (l >= r) {
			// (l >= r) && (a[i]' = a[i], i:1..args.length - 1)
			return l;
		} else {
			int m = l + (r - l) / 2;
            // I && (0 <= l <= m < r <= args.length - 1) && (m = l + (r - l) / 2) && (l < r) && (a[i]' = a[i], i:1..args.length - 1)
            if (a[m] > x) {
                return BinarySearchRecl(m + 1, r, x, a);
                // I && (l' = m + 1) && (r' = r) && (a[m] > x)
            }
             else {
                return BinarySearchRecl(l, m, x, a);
                // I && (r' = m) && (l' = l) && (a[m] <= x)
            }
            // I && (((r' = m) && (l' = l)) || ((r' = r) && (l'= m + 1))) && (r' - l' < r - l)    
        }
	}
	// (R = min(l): a[l] <= x) && (if (x < a[i], i:1..args.length - 1) l = args.length - 1)

	// (x - int) && (a[i] - int) && (a[i - 1] >= a[i], i:1..args.length - 1) && (0 <= l < r <= args.length - 1)
	public static int BinarySearchRec(int l, int r, int x, int[] a) {
        // I: 0 <= l <= args.length - 1) && (0 <= r <= args.length - 1) && (r - l > r' - l') && (a[i]' = a[i], i:1..args.length - 1)
        if (l >= r - 1) {
            // (l >= r - 1) && (a[i]' = a[i], i:1..args.length - 1)
            return l;
        } else {
            int m = l + (r - l) / 2;
             // I && (l < r - 1) && (0 <= l <= m < r <= args.length - 1) && (m = l + (r - l) / 2) && (a[i]' = a[i], i:1..args.length - 1)
			if (a[m] >= x) {
                return BinarySearchRec(m, r, x, a);
				// I && (l' = m) && (r' = r) && (a[m] >= x) && (a[i]' = a[i], i:1..args.length - 1)
            } else {
				return BinarySearchRec(l, m, x, a);
                // I && (l' = l) && (r' = m) && (a[m] < x ) && (a[i]' = a[i], i:1..args.length - 1)
                }
           }
     }
	// (R = max(l): a[l] <= x) && (if (x < a[i], i:1..args.length - 1):l = args.length - 1)
}