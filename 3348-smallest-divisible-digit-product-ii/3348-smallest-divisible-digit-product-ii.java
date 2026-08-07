import java.util.*;

class Solution {
    // digit -> [e2, e3, e5, e7] exponents contributed
    static final int[][] VEC = {
        {0,0,0,0}, // 0 (never used, zero-free)
        {0,0,0,0}, // 1
        {1,0,0,0}, // 2
        {0,1,0,0}, // 3
        {2,0,0,0}, // 4
        {0,0,1,0}, // 5
        {1,1,0,0}, // 6
        {0,0,0,1}, // 7
        {3,0,0,0}, // 8
        {0,2,0,0}  // 9
    };
    static final int INF = Integer.MAX_VALUE / 2;

    int c2, c3, c5, c7;
    int[][][][] minDigits; // minDigits[a][b][c][d] = min digits to reach at least (a,b,c,d)

    public String smallestNumber(String num, long t) {
        c2 = 0; while (t % 2 == 0) { t /= 2; c2++; }
        c3 = 0; while (t % 3 == 0) { t /= 3; c3++; }
        c5 = 0; while (t % 5 == 0) { t /= 5; c5++; }
        c7 = 0; while (t % 7 == 0) { t /= 7; c7++; }
        if (t != 1) return "-1"; // t has a prime factor other than 2,3,5,7 -> impossible

        buildMinDigitsTable();

        int n = num.length();
        int minLen = minDigits[c2][c3][c5][c7];

        if (minLen > n) {
            return buildSmallest(minLen, c2, c3, c5, c7);
        }

        String sameLen = tryBuildSameLength(num);
        if (sameLen != null) return sameLen;

        return buildSmallest(n + 1, c2, c3, c5, c7);
    }

    private void buildMinDigitsTable() {
        minDigits = new int[c2 + 1][c3 + 1][c5 + 1][c7 + 1];
        for (int[][][] x : minDigits) for (int[][] y : x) for (int[] z : y) Arrays.fill(z, INF);
        minDigits[0][0][0][0] = 0;

        for (int a = 0; a <= c2; a++)
            for (int b = 0; b <= c3; b++)
                for (int c = 0; c <= c5; c++)
                    for (int d = 0; d <= c7; d++) {
                        int cur = minDigits[a][b][c][d];
                        if (cur == INF) continue;
                        for (int dig = 1; dig <= 9; dig++) {
                            int[] v = VEC[dig];
                            int na = Math.min(a + v[0], c2);
                            int nb = Math.min(b + v[1], c3);
                            int nc = Math.min(c + v[2], c5);
                            int nd = Math.min(d + v[3], c7);
                            if (cur + 1 < minDigits[na][nb][nc][nd]) {
                                minDigits[na][nb][nc][nd] = cur + 1;
                            }
                        }
                    }
    }

    // smallest zero-free number of exact length len covering (a,b,c,d) worth of factors
    private String buildSmallest(int len, int a, int b, int c, int d) {
        StringBuilder sb = new StringBuilder();
        for (int pos = 0; pos < len; pos++) {
            int left = len - pos - 1;
            for (int dig = 1; dig <= 9; dig++) {
                int[] v = VEC[dig];
                int na = Math.max(0, a - v[0]);
                int nb = Math.max(0, b - v[1]);
                int nc = Math.max(0, c - v[2]);
                int nd = Math.max(0, d - v[3]);
                if (minDigits[na][nb][nc][nd] <= left) {
                    sb.append(dig);
                    a = na; b = nb; c = nc; d = nd;
                    break;
                }
            }
        }
        return sb.toString();
    }

    // try to build smallest n-digit zero-free number >= num satisfying requirement
    private String tryBuildSameLength(String num) {
        int n = num.length();
        int[] prefA = new int[n + 1], prefB = new int[n + 1], prefC = new int[n + 1], prefD = new int[n + 1];
        int firstZero = n;
        for (int i = 0; i < n; i++) {
            int dig = num.charAt(i) - '0';
            if (dig == 0 && firstZero == n) firstZero = i;
            int[] v = VEC[dig];
            prefA[i + 1] = Math.min(prefA[i] + v[0], c2);
            prefB[i + 1] = Math.min(prefB[i] + v[1], c3);
            prefC[i + 1] = Math.min(prefC[i] + v[2], c5);
            prefD[i + 1] = Math.min(prefD[i] + v[3], c7);
        }

        // check num itself
        if (firstZero == n) {
            if (prefA[n] == c2 && prefB[n] == c3 && prefC[n] == c5 && prefD[n] == c7) {
                return num;
            }
        }

        for (int p = n - 1; p >= 0; p--) {
            if (p > firstZero) continue; // prefix would contain a 0

            int ra = c2 - prefA[p], rb = c3 - prefB[p], rc = c5 - prefC[p], rd = c7 - prefD[p];
            int digitAtP = num.charAt(p) - '0';
            int left = n - p - 1;

            for (int dig = digitAtP + 1; dig <= 9; dig++) {
                int[] v = VEC[dig];
                int na = Math.max(0, ra - v[0]);
                int nb = Math.max(0, rb - v[1]);
                int nc = Math.max(0, rc - v[2]);
                int nd = Math.max(0, rd - v[3]);
                if (minDigits[na][nb][nc][nd] <= left) {
                    String suffix = buildSmallest(left, na, nb, nc, nd);
                    return num.substring(0, p) + dig + suffix;
                }
            }
        }
        return null;
    }
}