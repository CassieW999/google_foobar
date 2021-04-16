public class GrandestStaircase {
    public static int ans;
    public static int solution(int n) {
        // Your code here
        ans = 0;
        if (n == 0)return 0;
        if (n < 3)return 1;
        dfs(n, 0, 0);
        return ans;
    }

    public static void dfs(int n, int prev, int curr) {
        if (curr == n) {
            ans++;
            return;
        }
        if (prev + 1 >= n){
            return;
        }
        for (int i = prev + 1; i < n; i++) {
            if (curr + i <= n){
                dfs(n, i, curr + i);
            }
        }
    }

    static int[][] mem = new int[202][202];

    public static int answer(int n) {
        return helper(1, n) - 1;
    }

    public static int helper(int height, int left) {
        if (left == 0) {
            return 1;
        }else if (left < height) {
            return 0;
        }else if (mem[height][left] != 0) {
            return mem[height][left];
        }

        int value = helper(height + 1, left - height) + helper(height + 1, left);
        mem[height][left] = value;
        return value;
    }

    public static int answer3(int n) {
        int[][] solutions = new int[n+1][n+1];
        for(int i = 0; i < n+1; i++) {
            for(int j = 0; j < n+1; j++) {
                solutions[i][j] = 0; //init
                int maxBrick = (j)*(j+1)/2; //max possible bricks with j height is just the sum equation
                if(i == 0 || j == 0 || i > maxBrick) { //no bricks, no height, too many bricks for our max height
                    solutions[i][j] = 0;
                    continue;
                }
                else if(i == j) {
                    solutions[i][j] = 1;
                }

                if(maxBrick == i) {
                    solutions[i][j] = 1;
                }
                else if(i < maxBrick){ ///should also be else
                    for(int k = Math.min(i,j); k >= 1; k--) {
                        solutions[i][j] += solutions[i-k][k-1];
                    }
                    solutions[i][j] = Math.max(solutions[i][j], solutions[i][j-1]);
                }
            }
        }
        return(solutions[n][n] - 1); // remove the case of just n bricks stacked high
    }

    public static void main(String[] args) {
//        for (int i = 3; i <= 20; i++) {
//            if (solution(i) != answer(i)){
//                System.out.println("不同答案出现的位置为" + i + "错误答案" + solution(i) + "正确答案" + answer(i));
//            }
//        }
//        System.out.println(solution(200));
        System.out.println(answer(3));
    }
}
