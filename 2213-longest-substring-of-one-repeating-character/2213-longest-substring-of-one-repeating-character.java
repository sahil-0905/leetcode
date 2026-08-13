class Solution {

    static class Node {
        char leftChar;
        char rightChar;

        int len;
        int prefix;
        int suffix;
        int max;

        Node(char leftChar, char rightChar,
             int len, int prefix, int suffix, int max) {

            this.leftChar = leftChar;
            this.rightChar = rightChar;
            this.len = len;
            this.prefix = prefix;
            this.suffix = suffix;
            this.max = max;
        }
    }

    Node[] tree;

    Node merge(Node a, Node b) {

        if (a == null) return b;
        if (b == null) return a;

        Node res = new Node(
            a.leftChar,
            b.rightChar,
            a.len + b.len,
            0,
            0,
            0
        );

        // Prefix
        res.prefix = a.prefix;

        if (a.prefix == a.len &&
            a.rightChar == b.leftChar) {

            res.prefix = a.len + b.prefix;
        }

        // Suffix
        res.suffix = b.suffix;

        if (b.suffix == b.len &&
            a.rightChar == b.leftChar) {

            res.suffix = b.len + a.suffix;
        }

        // Maximum
        res.max = Math.max(a.max, b.max);

        if (a.rightChar == b.leftChar) {

            res.max = Math.max(
                res.max,
                a.suffix + b.prefix
            );
        }

        return res;
    }

    void build(String s, int idx, int l, int r) {

        if (l == r) {

            char ch = s.charAt(l);

            tree[idx] = new Node(
                ch,
                ch,
                1,
                1,
                1,
                1
            );

            return;
        }

        int mid = l + (r - l) / 2;

        build(s, idx * 2, l, mid);
        build(s, idx * 2 + 1, mid + 1, r);

        tree[idx] = merge(
            tree[idx * 2],
            tree[idx * 2 + 1]
        );
    }

    void update(int idx, int l, int r,
                int pos, char ch) {

        if (l == r) {

            tree[idx] = new Node(
                ch,
                ch,
                1,
                1,
                1,
                1
            );

            return;
        }

        int mid = l + (r - l) / 2;

        if (pos <= mid) {

            update(
                idx * 2,
                l,
                mid,
                pos,
                ch
            );

        } else {

            update(
                idx * 2 + 1,
                mid + 1,
                r,
                pos,
                ch
            );
        }

        tree[idx] = merge(
            tree[idx * 2],
            tree[idx * 2 + 1]
        );
    }

    public int[] longestRepeating(
            String s,
            String queryCharacters,
            int[] queryIndices) {

        int n = s.length();

        tree = new Node[4 * n];

        build(
            s,
            1,
            0,
            n - 1
        );

        int[] ans = new int[queryIndices.length];

        for (int i = 0; i < queryIndices.length; i++) {

            update(
                1,
                0,
                n - 1,
                queryIndices[i],
                queryCharacters.charAt(i)
            );

            ans[i] = tree[1].max;
        }

        return ans;
    }
}