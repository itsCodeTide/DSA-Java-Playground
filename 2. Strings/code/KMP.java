public class KMP {
    public void KMPSearch(String pat, String txt) {
        int M = pat.length(), N = txt.length();
        int[] lps = new int[M];
        computeLPSArray(pat, lps);

        int i = 0, j = 0;
        while (i < N) {
            if (pat.charAt(j) == txt.charAt(i)) {
                i++; j++;
            }
            if (j == M) {
                System.out.println("Found at index " + (i - j));
                j = lps[j - 1];
            } else if (i < N && pat.charAt(j) != txt.charAt(i)) {
                if (j != 0) j = lps[j - 1];
                else i++;
            }
        }
    }

    void computeLPSArray(String pat, int[] lps) {
        int len = 0, i = 1;
        lps[0] = 0;
        while (i < pat.length()) {
            if (pat.charAt(i) == pat.charAt(len)) {
                lps[i++] = ++len;
            } else if (len != 0) {
                len = lps[len - 1];
            } else {
                lps[i++] = 0;
            }
        }
    }

    public static void main(String[] args) {
        new KMP().KMPSearch("ABABCABAB", "ABABDABACDABABCABAB");
    }
}