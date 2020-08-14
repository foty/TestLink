package com.example.testlink.calculate;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

/**
 * Created by lxx on 2019/8/21.
 * Use by
 */

public class StringCalculates {

    private static void showResult(int[][] A) {
        System.out.print("[");
        for (int i = 0; i < A.length; i++) {
            System.out.print("[");
            for (int j = 0; j < A[i].length; j++) {
                if (j == A[i].length - 1) {
                    System.out.print(A[i][j] + "]");
                } else {
                    System.out.print(A[i][j] + ",");
                }
            }
            if (i == A.length - 1) {
                System.out.print("]");
            } else {
                System.out.print(",");
            }
        }
    }

    private static void showResult(String s) {
        System.out.print("[");
        for (int i = 0; i < s.length(); i++) {
            System.out.print(s.charAt(i));
            if (i == s.length() - 1) {
                System.out.print("]");
            } else {
                System.out.print(", ");
            }
        }
    }

    public static void main(String[] args) {
        String[] strings = new String[]{"hello", "world", "canada", "apple"};
        char[] chars = new char[]{'h', 'e', 'l', 'l'};
        String srings = "Let's take LeetCode contest";
        String[] strs = new String[]{"test.email+alex@leetcode.com", "test.e.mail+bob.cathy@leetcode.com",
                "testemail+david@lee.tcode.com"};
        String[] s1s = new String[]{"abc", "acb", "bac", "bca", "cab", "cba"};

        String[] s1 = new String[]{"cbd"};
        String[] s2 = new String[]{"a1 9 2 3 1", "g1 act car", "zo4 4 7", "ab1 off key dog", "a8 act zoo"};

        String cs = "}{";
        System.out.println(problem20(cs));

    }

    /**
     * 转换成小写字母
     * 实现函数 ToLowerCase()，该函数接收一个字符串参数 str，并将该字符串中的大写字母转换成小写字母，之后返回新的字符串。
     * 示例 1：
     * <p>
     * 输入: "Hello"
     * 输出: "hello"
     * 示例 2：
     * <p>
     * 输入: "here"
     * 输出: "here"
     * 示例 3：
     * <p>
     * 输入: "LOVELY"
     * 输出: "lovely"
     * <p>
     * <p>
     * 65 - 90 A-Z
     * 97 - 122 a-z
     */
    public static String problem709(String str) {

        StringBuilder sb = new StringBuilder();
//        String result = "";
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (c >= 65 && c <= 90) {
                c += 32;
            }
            sb.append(c);
//            result += c;
        }

//        执行用时 :1 ms, 在所有 Java 提交中击败了 19.46% 的用户
//        内存消耗 :34.7 MB, 在所有 Java 提交中击败了 55.51% 的用户

        /**
         * 将StringBuilder替换成 String，差距也太大了。emmmmm
         */
//        return result

//        执行用时 :0 ms, 在所有 Java 提交中击败了 100.00% 的用户
//        内存消耗 :34.6 MB, 在所有 Java 提交中击败了 55.51% 的用户

        return sb.toString();
    }

    /**
     * 806 唯一摩尔斯密码词
     * 国际摩尔斯密码定义一种标准编码方式，将每个字母对应于一个由一系列点和短线组成的字符串， 比如: "a" 对应 ".-", "b" 对应 "-...",
     * "c" 对应 "-.-.", 等等。为了方便，所有26个英文字母对应摩尔斯密码表如下：
     * <p>
     * [".-","-...","-.-.","-..",".","..-.","--.","....","..",".---","-.-",".-..","--","-.","---",".--.","--.-",
     * ".-.","...","-","..-","...-",".--","-..-","-.--","--.."]
     * 给定一个单词列表，每个单词可以写成每个字母对应摩尔斯密码的组合。例如，"cab" 可以写成 "-.-..--..."，
     * (即 "-.-." + "-..." + ".-"字符串的结合)。我们将这样一个连接过程称作单词翻译。
     * 返回我们可以获得所有词不同单词翻译的数量。
     * <p>
     * 例如:
     * 输入: words = ["gin", "zen", "gig", "msg"]
     * 输出: 2
     * 解释:
     * 各单词翻译如下:
     * "gin" -> "--...-."
     * "zen" -> "--...-."
     * "gig" -> "--...--."
     * "msg" -> "--...--."
     * <p>
     * 共有 2 种不同翻译, "--...-." 和 "--...--.".
     * <p>
     * 注意:
     * <p>
     * 单词列表words 的长度不会超过 100。
     * 每个单词 words[i]的长度范围为 [1, 12]。
     * 每个单词 words[i]只包含小写字母。
     *
     * @param words
     * @return
     */
    public static int problem806(String[] words) {
        String mos[] = new String[]{".-", "-...", "-.-.", "-..", ".", "..-.", "--.", "....", "..", ".---", "-.-", ".-..",
                "--", "-.", "---", ".--.", "--.-", ".-.", "...", "-", "..-", "...-", ".--", "-..-", "-.--", "--.."};
        HashMap<String, String> map = new HashMap<>();
        for (int i = 0; i < words.length; i++) {
            String tempStr = words[i];
            String mosStr = "";
            for (int j = 0; j < tempStr.length(); j++) {
                char c = tempStr.charAt(j);
                mosStr += mos[c - 97];
//                System.out.println(mos[c - 97] + " " + 'a');
            }
            map.put(mosStr, tempStr);
        }

//        执行用时 :6 ms, 在所有 Java 提交中击败了 46.05% 的用户
//        内存消耗 :36.6 MB, 在所有 Java 提交中击败了 60.58% 的用户

        return map.size();
    }

    /**
     * 在二维平面上，有一个机器人从原点 (0, 0) 开始。给出它的移动顺序，判断这个机器人在完成移动后是否在 (0, 0) 处结束。
     * 移动顺序由字符串表示。字符 move[i] 表示其第 i 次移动。机器人的有效动作有 R（右），L（左），U（上）和 D（下）。
     * 如果机器人在完成所有动作后返回原点，则返回 true。否则，返回 false。
     * 注意：机器人“面朝”的方向无关紧要。 “R” 将始终使机器人向右移动一次，“L” 将始终向左移动等。此外，假设每次移动机器人的移动幅度相同。
     *  
     * 示例 1:
     * <p>
     * 输入: "UD"
     * 输出: true
     * 解释：机器人向上移动一次，然后向下移动一次。所有动作都具有相同的幅度，因此它最终回到它开始的原点。因此，我们返回 true。
     * 示例 2:
     * <p>
     * 输入: "LL"
     * 输出: false
     * 解释：机器人向左移动两次。它最终位于原点的左侧，距原点有两次 “移动” 的距离。我们返回 false，因为它在移动结束时没有返回原点。
     *
     * @param moves
     * @return
     */
    public boolean problem657(String moves) {
        HashMap<Character, Integer> map = new HashMap<>();
        map.put('R', 1);
        map.put('L', -1);
        map.put('U', 2);
        map.put('D', -2);

        int can = 0;
        for (int i = 0; i < moves.length(); i++) {
            can += map.get(moves.charAt(i));
        }

//        执行用时 :32 ms, 在所有 Java 提交中击败了 10.48% 的用户
//        内存消耗 :43.1 MB, 在所有 Java 提交中击败了 72.14% 的用户
        return can == 0;
    }

    /**
     * 编写一个函数，其作用是将输入的字符串反转过来。输入字符串以字符数组 char[] 的形式给出。
     * 不要给另外的数组分配额外的空间，你必须原地修改输入数组、使用 O(1) 的额外空间解决这一问题。
     * 你可以假设数组中的所有字符都是 ASCII 码表中的可打印字符。
     *  
     * <p>
     * 示例 1：
     * <p>
     * 输入：["h","e","l","l","o"]
     * 输出：["o","l","l","e","h"]
     * 示例 2：
     * <p>
     * 输入：["H","a","n","n","a","h"]
     * 输出：["h","a","n","n","a","H"]
     *
     * @param s
     */
    public static char[] problem344(char[] s) {
        /**
         *实际上这种方法也是可以使用的。貌似提交不上去
         *
         StringBuilder sb = new StringBuilder();
         for (int i = 0; i < s.length; i++) {
         sb.append(s[i]);
         }
         String str = sb.reverse().toString();
         return str.toCharArray();
         */
        for (int i = 0; i < s.length / 2; i++) {
            char temp = s[i];
            int j = s.length - 1 - i;
            s[i] = s[j];
            s[j] = temp;
        }

//        执行用时 :2 ms, 在所有 Java 提交中击败了 98.43% 的用户
//        内存消耗 :59.2 MB, 在所有 Java 提交中击败了 28.24% 的用户
        return s;
    }

    /**
     * 给定一个字符串，你需要反转字符串中每个单词的字符顺序，同时仍保留空格和单词的初始顺序。
     * 示例 1:
     * 输入: "Let's take LeetCode contest"
     * 输出: "s'teL ekat edoCteeL tsetnoc" 
     * 注意：在字符串中，每个单词由单个空格分隔，并且字符串中不会有任何额外的空格。
     *
     * @param s
     * @return
     */
    public static String problem557(String s) {
        System.out.println(s);
        String[] strings = s.split(" ");
        String result = "";

        for (int i = 0; i < strings.length; i++) {
            result += new StringBuffer(strings[i]).reverse();
            if (strings.length - 1 != i) {
                result += " ";
            }
        }
        System.out.println(result);
//        执行用时 :549 ms, 在所有 Java 提交中击败了 5.03% 的用户
//        内存消耗 :368.7 MB, 在所有 Java 提交中击败了 5.02% 的用户

        return result.trim();
    }

    /**
     * 每封电子邮件都由一个本地名称和一个域名组成，以 @ 符号分隔。
     * 例如，在 alice@leetcode.com中， alice 是本地名称，而 leetcode.com 是域名。
     * 除了小写字母，这些电子邮件还可能包含 '.' 或 '+'。
     * 如果在电子邮件地址的本地名称部分中的某些字符之间添加句点（'.'），则发往那里的邮件将会转发到本地名称中没有点的同一地址。
     * 例如，"alice.z@leetcode.com” 和 “alicez@leetcode.com” 会转发到同一电子邮件地址。 （请注意，此规则不适用于域名。）
     * 如果在本地名称中添加加号（'+'），则会忽略第一个加号后面的所有内容。这允许过滤某些电子邮件，例如 m.y+name@email.com 将转发到 my@email.com。 （同样，此规则不适用于域名。）
     * 可以同时使用这两个规则。
     * 给定电子邮件列表 emails，我们会向列表中的每个地址发送一封电子邮件。实际收到邮件的不同地址有多少？
     *  
     * <p>
     * 示例：
     * <p>
     * 输入：["test.email+alex@leetcode.com","test.e.mail+bob.cathy@leetcode.com","testemail+david@lee.tcode.com"]
     * 输出：2
     * 解释：实际收到邮件的是 "testemail@leetcode.com" 和 "testemail@lee.tcode.com"。
     *  
     * <p>
     * 提示：
     * <p>
     * 1 <= emails[i].length <= 100
     * 1 <= emails.length <= 100
     * 每封 emails[i] 都包含有且仅有一个 '@' 字符。
     */
    public static int problem928(String[] emails) {
        HashSet<String> set = new HashSet<>();
        for (String email : emails) {
            int i = email.indexOf("@");
            String substring = email.substring(0, i);
            String address = email.substring(i, email.length());
            substring = substring.replaceAll("\\.", "");
            if (substring.contains("+")) {
                substring = substring.substring(0, substring.indexOf("+"));
                set.add(substring + address);
            } else {
                set.add(substring + address);
            }

        }
        System.out.println("-----------------------------");
        for (String s : set) {
            System.out.println(s);
        }

//        执行用时 :61 ms, 在所有 Java 提交中击败了 48.08% 的用户
//        内存消耗 :49.3 MB, 在所有 Java 提交中击败了 33.27% 的用户

        return set.size();
    }

    /**
     * 给定两个字符串，你需要从这两个字符串中找出最长的特殊序列。
     * 最长特殊序列定义如下：该序列为某字符串独有的最长子序列（即不能是其他字符串的子序列）。
     * 子序列可以通过删去字符串中的某些字符实现，但不能改变剩余字符的相对顺序。空序列为所有字符串的子序列，任何字符串为其自身的子序列。
     * 输入为两个字符串，输出最长特殊序列的长度。如果不存在，则返回 -1。
     * <p>
     * 示例 :
     * 输入: "aba", "cdc"
     * 输出: 3
     * 解析: 最长特殊序列可为 "aba" (或 "cdc")
     * 说明:
     * <p>
     * 两个字符串长度均小于100。
     * 字符串中的字符仅含有 'a'~'z'。
     *
     * @param a
     * @param b
     * @return
     */
    public static int problem521(String a, String b) {
        if (a.equals(b)) return -1;
        return a.length() > b.length() ? a.length() : b.length();

        /**
         * 题解：这是一道机灵题。看到题目 “最长特殊序列定义如下：该序列为某字符串独有的最长子序列（即不能是其他字符串的子序列）”，其实要注意到自己本身
         * 也是一个子序列就简单了。长度不一样的两个字符串，最长的就是独有的子序列了。
         */
//        执行用时 :0 ms, 在所有 Java 提交中击败了 100.00% 的用户
//        内存消耗 :34.4 MB, 在所有 Java 提交中击败了 66.38% 的用户
    }

    /**
     * 你将得到一个字符串数组 A。
     * 如果经过任意次数的移动，S == T，那么两个字符串 S 和 T 是特殊等价的。
     * 一次移动包括选择两个索引 i 和 j，且 i ％ 2 == j ％ 2，交换 S[j] 和 S [i]。
     * 现在规定，A 中的特殊等价字符串组是 A 的非空子集 S，这样不在 S 中的任何字符串与 S 中的任何字符串都不是特殊等价的。
     * 返回 A 中特殊等价字符串组的数量。
     *  
     * <p>
     * 示例 1：
     * 输入：["a","b","c","a","c","c"]
     * 输出：3
     * 解释：3 组 ["a","a"]，["b"]，["c","c","c"]
     * 示例 2：
     * <p>
     * 输入：["aa","bb","ab","ba"]
     * 输出：4
     * 解释：4 组 ["aa"]，["bb"]，["ab"]，["ba"]
     * 示例 3：
     * <p>
     * 输入：["abc","acb","bac","bca","cab","cba"]
     * 输出：3
     * 解释：3 组 ["abc","cba"]，["acb","bca"]，["bac","cab"]
     * 示例 4：
     * <p>
     * 输入：["abcd","cdab","adcb","cbad"]
     * 输出：1
     * 解释：1 组 ["abcd","cdab","adcb","cbad"]
     *  
     * <p>
     * 提示：
     * <p>
     * 1 <= A.length <= 1000
     * 1 <= A[i].length <= 20
     * 所有 A[i] 都具有相同的长度。
     * 所有 A[i] 都只由小写字母组成。
     *
     * @param A
     * @return
     */
    public static int problem893(String[] A) {
        /**
         * 完全理解了题目意思，再去实现就很简单了。
         * 题解：
         * 1.什么特殊等价字符串组？依据题意来就是一个字符串A。将A字符串的任意奇数位字符或偶数为字符交换得到新的字符串B，
         * 那么A跟B就是等价字符串.同样经过一定次数交换得到多个新字符串C,D,E..。，这样的[A,B,C,D....]就是等价字符串组。
         * 所以在等价字符串组内必然存在某种关系C，使得这个等价字符串组内任意2个字符串： A->C = B.
         * 2.题目的目的？给出字符串组A，找到A中共有多少对特殊等价字符串组。换个意思就是A字符串数组共存在多少种不同的关系C。
         * 所以题目的方向转变成了寻求关系C的数量。
         * 3.怎么解？我们知道等价字符串的奇数位，偶数位字符都会是相等的，不是等价字符串的话奇数部分跟偶数部分就不会相等。
         * 那么根据这个特点来当做关系C来处理就可以了。将每个字符串，都拆分以奇数部分与偶数部分组成的新字符串(这就是关系C)。
         * A中组合的新字符串数量就是题目的答案了。(每个字符串都会产生一个关系C[可能相同或不同]，到这一步骤就是去重复了。)
         *
         */
        Set<String> set = new HashSet<>();
        for (String str : A) {
            char[] chars = str.toCharArray();
            String odd = "", even = "";
            for (int i = 0; i < chars.length; i++) {
                //如果是奇数位
                if ((i & 1) == 1) {
                    odd += str.charAt(i);
                } else {
                    //如果为偶数位
                    even += str.charAt(i);
                }
            }
            char[] oddArr = odd.toCharArray();
            Arrays.sort(oddArr);
            char[] evenArr = even.toCharArray();
            Arrays.sort(evenArr);

            System.out.println(new String(oddArr) + new String(evenArr));
            set.add(new String(oddArr) + new String(evenArr));
        }

//        执行用时 :26 ms, 在所有 Java 提交中击败了 52.91% 的用户
//        内存消耗 :43 MB, 在所有 Java 提交中击败了 45.65% 的用户
        return set.size();
    }

    /**
     * 罗马数字包含以下七种字符: I， V， X， L，C，D 和 M。
     * <p>
     * 字符          数值
     * I             1
     * V             5
     * X             10
     * L             50
     * C             100
     * D             500
     * M             1000
     * 例如， 罗马数字 2 写做 II ，即为两个并列的 1。12 写做 XII ，即为 X + II 。 27 写做  XXVII, 即为 XX + V + II 。
     * 通常情况下，罗马数字中小的数字在大的数字的右边。但也存在特例，例如 4 不写做 IIII，而是 IV。数字 1 在数字 5 的左边，
     * 所表示的数等于大数 5 减小数 1 得到的数值 4 。同样地，数字 9 表示为 IX。这个特殊的规则只适用于以下六种情况：
     * I 可以放在 V (5) 和 X (10) 的左边，来表示 4 和 9。
     * X 可以放在 L (50) 和 C (100) 的左边，来表示 40 和 90。 
     * C 可以放在 D (500) 和 M (1000) 的左边，来表示 400 和 900。
     * 给定一个罗马数字，将其转换成整数。输入确保在 1 到 3999 的范围内
     *
     * @param s
     * @return
     */
    public static int problem13(String s) {
        // MCMXCIV
        HashMap<Character, Integer> map = new HashMap<>();
        map.put('I', 1);
        map.put('V', 5);
        map.put('X', 10);
        map.put('L', 50);
        map.put('C', 100);
        map.put('D', 500);
        map.put('M', 1000);
        int result = 0;
        for (int i = 0; i < s.length(); i++) {
            int cur = map.get(s.charAt(i));
            int nex;
            if (i < s.length() - 1) {
                nex = map.get(s.charAt(i + 1));
                if (nex > cur) {
                    result += (nex - cur);
                    i++;
                } else {
                    result += cur;
                }
            } else {
                result += map.get(s.charAt(i));
            }
        }
        System.out.println(result);

//        执行用时 :17 ms, 在所有 Java 提交中击败了 86.27% 的用户
//        内存消耗 :9.6 MB, 在所有 Java 提交中击败了 77.09% 的用户

        return result;
    }

    /**
     * 我们称一个数 X 为好数, 如果它的每位数字逐个地被旋转 180 度后，我们仍可以得到一个有效的，且和 X 不同的数。要求每位数字都要被旋转。
     * 如果一个数的每位数字被旋转以后仍然还是一个数字， 则这个数是有效的。0, 1, 和 8 被旋转后仍然是它们自己；2 和 5 可以互相旋转成对方；
     * 6 和 9 同理，除了这些以外其他的数字旋转以后都不再是有效的数字。
     * 现在我们有一个正整数 N, 计算从 1 到 N 中有多少个数 X 是好数？
     * <p>
     * 示例:
     * 输入: 10
     * 输出: 4
     * 解释:
     * 在[1, 10]中有四个好数： 2, 5, 6, 9。
     * 注意 1 和 10 不是好数, 因为他们在旋转之后不变。
     * 注意:
     * <p>
     * N 的取值范围是 [1, 10000]。
     *
     * @param N
     * @return
     */
    public static int problem788(int N) {
        int count = 0;
        for (int i = 1; i <= N; i++) {
            String s = String.valueOf(i);
            if (s.contains("3") || s.contains("4") || s.contains("7")) {
                continue;
            }
            s = s.replaceAll("0", "");
            s = s.replaceAll("1", "");
            s = s.replaceAll("8", "");
            if (!s.equals("")) {
                count++;
            }
        }
//        执行用时 :149 ms, 在所有 Java 提交中击败了 13.40% 的用户
//        内存消耗 :49.9 MB, 在所有 Java 提交中击败了 14.06% 的用户


        /**
         *
         * 提交记录比较优秀解法：

         public int[] digits = new int[] {0, 0, 1, -1, -1, 1, 1, -1, 0, 1};
         public int rotatedDigits(int N) {
         int total = 0;
         for (int i = 1; i <= N; i ++) {
         if (isGood(i)) {
         total ++;
         }
         }
         return total;
         }

         public boolean isGood(int n) {
         int flag = 0;
         while (n > 0) {
         int last = n % 10;
         flag = flag | digits[last];
         n /= 10;
         }
         return flag > 0;
         }
         */

        System.out.println(-1 | 0);
        return count;
    }

    /**
     * 给定一个单词，你需要判断单词的大写使用是否正确。
     * 我们定义，在以下情况时，单词的大写用法是正确的：
     * 全部字母都是大写，比如"USA"。
     * 单词中所有字母都不是大写，比如"leetcode"。
     * 如果单词不只含有一个字母，只有首字母大写， 比如 "Google"。
     * 否则，我们定义这个单词没有正确使用大写字母。
     * <p>
     * 示例 1:
     * <p>
     * 输入: "USA"
     * 输出: True
     * 示例 2:
     * <p>
     * 输入: "FlaG"
     * 输出: False
     * 注意: 输入是由大写和小写拉丁字母组成的非空单词。
     *
     * @param word
     * @return
     */
    public static boolean problem520(String word) {
        if (word.length() == 1) return true;

        char[] chars = word.toCharArray();
        int in;
        if (chars[0] >= 97 && chars[1] >= 97) {//2位全小写
            in = 1;
        } else if (chars[0] < 97 && chars[1] >= 97) { //首字母大写，第二个小写。
            in = 2;
        } else if (chars[0] < 97 && chars[1] < 97) { //2位全大写
            in = 3;
        } else {
            return false;
        }

        for (int i = 2; i < chars.length; i++) {
            if (in == 1 && chars[i] < 97) {
                return false;
            } else if (in == 2 && chars[i] < 97) {
                return false;
            } else if (in == 3 && chars[i] >= 97) {
                return false;
            }
        }
//        执行用时 :2 ms, 在所有 Java 提交中击败了 97.68% 的用户
//        内存消耗 :35 MB, 在所有 Java 提交中击败了 93.17% 的用户

        return true;
    }

    /**
     * 我们来定义一个函数 f(s)，其中传入参数 s 是一个非空字符串；该函数的功能是统计 s  中（按字典序比较）最小字母的出现频次。
     * 例如，若 s = "dcce"，那么 f(s) = 2，因为最小的字母是 "c"，它出现了 2 次。
     * 现在，给你两个字符串数组待查表 queries 和词汇表 words，请你返回一个整数数组 answer 作为答案，其中每个 answer[i] 
     * 是满足 f(queries[i]) < f(W) 的词的数目，W 是词汇表 words 中的词。
     *  
     * <p>
     * 示例 1：
     * <p>
     * 输入：queries = ["cbd"], words = ["zaaaz"]
     * 输出：[1]
     * 解释：查询 f("cbd") = 1，而 f("zaaaz") = 3 所以 f("cbd") < f("zaaaz")。
     * 示例 2：
     * <p>
     * 输入：queries = ["bbb","cc"], words = ["a","aa","aaa","aaaa"]
     * 输出：[1,2]
     * 解释：第一个查询 f("bbb") < f("aaaa")，第二个查询 f("aaa") 和 f("aaaa") 都 > f("cc")。
     *  
     * <p>
     * 提示：
     * <p>
     * 1 <= queries.length <= 2000
     * 1 <= words.length <= 2000
     * 1 <= queries[i].length, words[i].length <= 10
     * queries[i][j], words[i][j] 都是小写英文字母
     *
     * @param queries
     * @param words
     * @return
     */
    public static int[] problem1170(String[] queries, String[] words) {
        int[] counts = new int[queries.length];
        int[] world = new int[words.length];
        for (int i = 0; i < words.length; i++) {
            world[i] = minCount(words[i]);
        }
        Arrays.sort(world);

        for (int i = 0; i < queries.length; i++) {
            int count = 0;
            for (int j = world.length - 1; j >= 0; j--) {
                System.out.println(minCount(queries[i]) + "  " + world[j]);
                if (minCount(queries[i]) >= world[j]) {
                    break;
                } else {
                    count++;
                }
            }
            counts[i] = count;
            System.out.print(count + "  ");
        }

//        执行用时 :159 ms, 在所有 Java 提交中击败了 21.37% 的用户
//        内存消耗 :67.7 MB, 在所有 Java 提交中击败了 100.00% 的用户

        return counts;
    }

    private static int minCount(String s) {
        char[] chars = s.toCharArray();
        int count = 0;
        Arrays.sort(chars);
        for (int i = 0; i < chars.length - 1; i++) {
            if (chars[i] == chars[i + 1]) {
                count++;
            } else {
                return count;
            }
        }
        return count;
    }

    /**
     * 给定两个二进制字符串，返回他们的和（用二进制表示）。
     * 输入为非空字符串且只包含数字 1 和 0。
     * <p>
     * 示例 1:
     * <p>
     * 输入: a = "11", b = "1"
     * 输出: "100"
     * 示例 2:
     * <p>
     * 输入: a = "1010", b = "1011"
     * 输出: "10101"
     * <p>
     *
     * @param a
     * @param b
     * @return
     */
    public static String problem67(String a, String b) {

        StringBuilder sb = new StringBuilder();

        int i = a.length() - 1;
        int j = b.length() - 1;
        boolean add_bit = false;
        while (i >= 0 || j >= 0) {

            if (i < 0 && j >= 0) {
                if (add_bit) {
                    if (b.charAt(j) == 49) {
                        sb.append("0");
                        add_bit = true;
                    } else {
                        sb.append("1");
                        add_bit = false;
                    }
                } else {
                    sb.append(b.charAt(j));
                    add_bit = false;
                }
                j--;
                i--;
                continue;
            }
            if (i >= 0 && j < 0) {
                if (add_bit) {
                    if (a.charAt(i) == 49) {
                        sb.append("0");
                        add_bit = true;
                    } else {
                        sb.append("1");
                        add_bit = false;
                    }

                } else {
                    sb.append(a.charAt(i));
                    add_bit = false;
                }
                i--;
                j--;
                continue;
            }
            if (a.charAt(i) == b.charAt(j)) { // 0,0; 1,1
                if (a.charAt(i) == 49) {
                    sb.append(add_bit ? "1" : "0");
                    add_bit = true;
                } else {
                    sb.append(add_bit ? "1" : "0");
                    add_bit = false;
                }

            } else { // 1,0
                if (add_bit) {
                    sb.append("0");
                    add_bit = true;
                } else {
                    sb.append("1");
                    add_bit = false;
                }
            }
            i--;
            j--;
        }
        if (add_bit) {
            sb.append("1");
        }

//        执行用时 :5 ms, 在所有 Java 提交中击败了 78.80% 的用户
//        内存消耗 :36.2 MB, 在所有 Java 提交中击败了 55.19% 的用户

        System.out.println("进制求和= " + sb.reverse().toString());
        return sb.reverse().toString();
    }

    /**
     * 给定一个字符串 S，返回 “反转后的” 字符串，其中不是字母的字符都保留在原地，而所有字母的位置发生反转。
     * <p>
     * 示例 1：
     * <p>
     * 输入："ab-cd"
     * 输出："dc-ba"
     * 示例 2：
     * <p>
     * 输入："a-bC-dEf-ghIj"
     * 输出："j-Ih-gfE-dCba"
     * 示例 3：
     * <p>
     * 输入："Test1ng-Leet=code-Q!"
     * 输出："Qedo1ct-eeLg=ntse-T!"
     *  
     * <p>
     * 提示：
     * <p>
     * S.length <= 100
     * 33 <= S[i].ASCIIcode <= 122 
     * S 中不包含 \ or "
     */
    public static String problem917(String S) {
        if (S.length() == 1) return S;
        char[] cs = S.toCharArray();
        int s = 0;
        int e = cs.length - 1;
        while (s < e) {
            if (isLegal(cs[s])) {
                if (isLegal(cs[e])) {
                    char temp = cs[s];
                    cs[s] = cs[e];
                    cs[e] = temp;
                    s++;
                    e--;
                } else {
                    e--;
                }
            } else {
                s++;
            }
        }
        String str = "";
        for (char c : cs) {
            str += c;
        }
        System.out.println(str);

//        执行用时 :2 ms, 在所有 Java 提交中击败了 55.90% 的用户
//        内存消耗 :36.1 MB, 在所有 Java 提交中击败了 83.20% 的用户

        return str;
    }

    private static boolean isLegal(char c) {
        return (65 <= c && c <= 90) || (c >= 97 && c <= 122);
    }

    /**
     * 你有一个日志数组 logs。每条日志都是以空格分隔的字串。
     * 对于每条日志，其第一个字为字母数字标识符。然后，要么：
     * 标识符后面的每个字将仅由小写字母组成，或；
     * 标识符后面的每个字将仅由数字组成。
     * 我们将这两种日志分别称为字母日志和数字日志。保证每个日志在其标识符后面至少有一个字。
     * <p>
     * 将日志重新排序，使得所有字母日志都排在数字日志之前。字母日志按内容字母顺序排序，忽略标识符；在内容相同时，按标识符排序。
     * 数字日志应该按原来的顺序排列。
     * 返回日志的最终顺序。
     * <p>
     * 示例 ：
     * <p>
     * 输入：["a1 9 2 3 1","g1 act car","zo4 4 7","ab1 off key dog","a8 act zoo"]
     * 输出：["g1 act car","a8 act zoo","ab1 off key dog","a1 9 2 3 1","zo4 4 7"]
     * <p>
     * 提示：
     * <p>
     * 0 <= logs.length <= 100
     * 3 <= logs[i].length <= 100
     * logs[i] 保证有一个标识符，并且标识符后面有一个字。
     *
     * @param logs
     * @return
     */
    public static String[] problem937(String[] logs) {
        String[] str = new String[logs.length];
        String[] numStr = new String[101];
        String[] cStr = new String[101];
        int p = 0;
        int q = 0;
        for (int i = 0; i < logs.length; i++) {
            String[] split = logs[i].split(" ");
            if (split[1].charAt(0) >= 97 && split[1].charAt(0) <= 122) {
                cStr[p++] = logs[i];
            } else {
                numStr[q++] = logs[i];
            }
        }

        Arrays.sort(cStr, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                if (o1 != null && o2 != null) {
                    String s1 = o1.substring(o1.indexOf(" "), o1.length());
                    String s2 = o2.substring(o2.indexOf(" "), o2.length());
                    if (s1.equals(s2)) {
                        return o1.compareTo(o2);
                    } else {
                        return s1.compareTo(s2);
                    }
                }
                return 0;
            }
        });
        System.out.println(numStr[0] + "," + numStr[1]);

        for (int i = 0, j = 0; i < str.length; i++) {
            if (cStr[i] == null) {
                str[i] = numStr[j++];
            } else {
                str[i] = cStr[i];
            }
        }

//        执行用时 :7 ms, 在所有 java 提交中击败了 84.89% 的用户
//        内存消耗 :38.1 MB, 在所有 java 提交中击败了 95.15% 的用户
        return str;
    }

    /**
     * 编写一个函数来查找字符串数组中的最长公共前缀。
     * 如果不存在公共前缀，返回空字符串 ""。
     * <p>
     * 题解: 首先要明白题意“最长公共前缀”。如果任意两个字符串没有公共字符串的话就是返回空了。可以2个2个依次寻找公共字符串。
     * 水平扫描法
     *
     * @return
     */
    public static String problem14(String[] strs) {
        if (strs.length <= 0) return "";
        String result = strs[0];                   //选取一个字符串作为参照对象(公共前缀一定也包含在这个字符串中，除非没有)
        for (int i = 0; i < result.length(); i++) {
            char c = result.charAt(i);             //对比每一个字符，如果不是相等的，循环结束。
            for (int j = 1; j < strs.length; j++) {
                if (i > strs[j].length() - 1 || strs[j].charAt(i) != c) {//避免越界
                    return result.substring(0, i);
                }
            }
        }
        return result;

//        执行用时 :1 ms, 在所有 java 提交中击败了 95.63% 的用户
//        内存消耗 :37.4 MB, 在所有 java 提交中击败了 78.51% 的用户
    }

    /**
     * 给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串，判断字符串是否有效。
     * 有效字符串需满足：
     * 左括号必须用相同类型的右括号闭合。
     * 左括号必须以正确的顺序闭合。
     * 注意空字符串可被认为是有效字符串。
     */
    public static boolean problem20(String s) {
        if (s == null) return false;
        if (s.length() <= 0) return true;
        if (s.length() < 2) return false;
        HashMap<Character, Character> map = new HashMap<>();
        map.put('(', ')');
        map.put('{', '}');
        map.put('[', ']');

        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (map.containsKey(c)) {
                stack.push(c);
            } else if (stack.empty() || map.get(stack.pop()) != c) {
                return false;
            }
        }
        return stack.empty();

//        执行用时：2 ms, 在所有 Java 提交中击败了 79.37% 的用户
//        内存消耗：37.7 MB, 在所有 Java 提交中击败了 58.98% 的用户
    }

    /**
     * 给定一个 haystack 字符串和一个 needle 字符串，在 haystack 字符串中找出 needle 字符串出现的第一个位置 (从0开始)。
     * 如果不存在，则返回  -1。
     */
    public static int problem28(String haystack, String needle) {
        return haystack.indexOf(needle);
    }

    /**
     * 给定一个非空的字符串，判断它是否可以由它的一个子串重复多次构成。给定的字符串只含有小写英文字母，并且长度不超过10000。
     * 示例 1:
     * 输入: "abab"
     * 输出: True
     * 解释: 可由子字符串 "ab" 重复两次构成。
     *
     * @param s
     */
    public static boolean problem459(String s) {

        //如果一串字符串s完全由n个相同的子串组成，那么在2s(两个这样的字符串)中除去头尾各一个字符余下的字符串
        //必然会包含s。
        return (s + s).substring(1, 2 * s.length()-1).contains(s);

//        执行用时 :39 ms, 在所有 Java 提交中击败了 54.97% 的用户
//        内存消耗 :38 MB, 在所有 Java 提交中击败了 96.30% 的用户
    }

}
