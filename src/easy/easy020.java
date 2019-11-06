package easy;

import java.util.HashMap;
import java.util.Stack;

/**
 * @ClassName easy020
 * @Description TODO
 * @Author CcatGX
 * @Date 2019/11/4 11:03
 * @Version 1.0
 **/
public class easy020 {
    /**
     *  给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串，判断字符串是否有效。
     *
     * 有效字符串需满足：
     *
     * 左括号必须用相同类型的右括号闭合。
     * 左括号必须以正确的顺序闭合。
     * 注意空字符串可被认为是有效字符串。
     *
     *
     * 示例 1:
     * 输入: "()"
     * 输出: true
     *
     * 示例 2:
     * 输入: "()[]{}"
     * 输出: true
     *
     * 示例 3:
     * 输入: "(]"
     * 输出: false
     *
     * 示例 4:
     * 输入: "([)]"
     * 输出: false
     *
     * 示例 5:
     * 输入: "{[]}"
     * 输出: true
     *
    **/

    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
        if("".equals(s)){
            return true;
        }
        char strs[] = s.toCharArray();
        for(int i=0;i<strs.length;i++){
            //如果为空，直接把字符放入栈中
            if(stack.empty()){
                stack.push(strs[i]);
                continue;
            }
            //如果不为空,判断放入的括号，与要弹出的括号是否匹配，如果匹配则直接出栈，如果不匹配则放入栈中
            if(stack.peek().equals('(')&&strs[i]==')'){
                stack.pop();
            } else if(stack.peek().equals(')')&&strs[i]=='('){
                stack.pop();
            } else if(stack.peek().equals('[')&&strs[i]==']'){
                stack.pop();
            } else if(stack.peek().equals(']')&&strs[i]=='['){
                stack.pop();
            } else if(stack.peek().equals('{')&&strs[i]=='}'){
                stack.pop();
            } else if(stack.peek().equals('}')&&strs[i]=='{'){
                stack.pop();
            } else{
                stack.push(strs[i]);
            }
        }
        if(stack.empty()){
            return true;
        }
        return false;
    }


    public static void main(String[] args){
        easy020 e20 = new easy020();
       /* System.out.println(e20.isValid("()"));
        System.out.println(e20.isValid("()[]{}"));
        System.out.println(e20.isValid("(]"));
        System.out.println(e20.isValid("([)]"));
        System.out.println(e20.isValid("{[]}"));*/
        System.out.println(e20.isValid("([)"));

    }

    /**
     * 思路
     *  想象一下，你正在为你的大学课设编写一个小型编译器，编译器的任务之一（或称子任务）将检测括号是否匹配。
     *
     * 我们本文中看到的算法可用于处理编译器正在编译的程序中的所有括号，并检查是否所有括号都已配对。这将检查给定的括号字符串是否有效，是一个重要的编程问题。
     *  我们这个问题中将要处理的表达式可以包含以下三种不同类型的括号：
     *  (),
     *  [],
     *  {}
     *
     *  在查看如何检查由这些括号组成的给定表达式是否有效之前，让我们看一下该问题的简化版本，在简化后的问题中，只含一种类型的括号。这么一来，我们将会遇到的表达式是
     *  (((((()))))) -- VALID
     *  ()()()()     -- VALID
     *  (((((((()    -- INVALID
     *  ((()(())))   -- VALID
     *  上我们试着用一个简单的算法来解决这一问题。
     *
     *  1.我们从表达式的左侧开始，每次只处理一个括号。
     *  2.假设我们遇到一个开括号（即 '(' ) ，表达式是否无效取决于在该表达式的其余部分的某处是否有相匹配的闭括号（即 ')' )。此时，我们只是增加计数器的值保持跟踪现在为止开括号的数目。left += 1
     *  3.如果我们遇到一个闭括号，这可能意味着这样两种情况：
     *      1.此闭括号没有与与之对应的开括号，在这种情况下，我们的表达式无效。当 left = 0 ，也就是没有未配对的左括号可用时就是这种情况。
     *      2.我们有一些未配对的开括号可以与该闭括号配对。当 left > 0，也就是有未配对的左括号可用时就是这种情况。
     *  4.如果我们在 left=0 时遇到一个闭括号（例如')' ) ，那么当前的表达式无效。否则，我们会减少 left 的值，也就是减少了可用的未配对的左括号的数量。
     *  5.继续处理字符串，直到处理完所有括号。
     *  6.如果最后我们仍然有未配对的左括号，这意味着表达式无效。
     *
     *  在这里讨论这个特定算法是因为我们从该解决方案中获得灵感以解决原始问题。
     *
     *  如果我们只是尝试对原始问题采用相同的办法，这是根本就行不通的。基于简单计数器的方法能够在上面完美运行是因为所有括号都具有相同的类型。因此，当我们遇到一个闭括号时，
     *  我们只需要假设有一个对应匹配的开括号是可用的，即假设 left > 0
     *
     *  但是，在我们的问题中，如果我们遇到 ']' ，我们真的不知道是否有相应的 '[' 可用。你可能会问：
     *  为什么不为不同类型的括号分别维护一个单独的计数器？
     *  这可能不起作用，因为括号的相对位置在这里也很重要。例如：
     *  [{]
     *  如果我们只是在这里维持计数器，那么只要我们遇到闭合方括号，我们就会知道此处有一个可用的未配对的开口方括号。但是,
     *  最近的未配对的开括号是一个花括号，而不是一个方括号，因此计数方法在这里被打破了。
     *
     *  方法 ： 栈
     *  关于有效括号表达式的一个有趣属性是有效表达式的子表达式也应该是有效表达式。整个表达式是有效的，而它的子表达式本身也是有效的。这为问题提供了一种递归结构。
     *  如果每当我们在表达式中遇到一对匹配的括号时，我们只是从表达式中删除它，会发生什么？
     *  让我们看看下面的这个想法，从整体表达式中一次删除一个较小的表达式，因为这是一个有效的表达式，我们最后剩留下一个空字符串。
     *
     *  在表示问题的递归结构时，栈数据结构可以派上用场。我们无法真正地从内到外处理这个问题，因为我们对整体结构一无所知。但是，栈可以帮助我们递归地处理这种情况，即从外部到内部。
     *
     *  算法
     *  1.初始化栈 S。
     *  2.一次处理表达式的每个括号。
     *  3.如果遇到开括号，我们只需将其推到栈上即可。这意味着我们将稍后处理它，让我们简单地转到前面的 子表达式。
     *  4.如果我们遇到一个闭括号，那么我们检查栈顶的元素。如果栈顶的元素是一个 相同类型的 左括号，那么我们将它从栈中弹出并继续处理。否则，这意味着表达式无效。
     *  5.如果到最后我们剩下的栈中仍然有元素，那么这意味着表达式无效。
     *
     */
    private HashMap<Character, Character> mappings;

    public easy020(){
        this.mappings = new HashMap<Character, Character>();
        this.mappings.put(')', '(');
        this.mappings.put('}', '{');
        this.mappings.put(']', '[');
    }

    public boolean isValid2(String s) {

        // Initialize a stack to be used in the algorithm.
        Stack<Character> stack = new Stack<Character>();

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            // If the current character is a closing bracket.
            if (this.mappings.containsKey(c)) {

                // Get the top element of the stack. If the stack is empty, set a dummy value of '#'
                char topElement = stack.empty() ? '#' : stack.pop();

                // If the mapping for this bracket doesn't match the stack's top element, return false.
                if (topElement != this.mappings.get(c)) {
                    return false;
                }
            } else {
                // If it was an opening bracket, push to the stack.
                stack.push(c);
            }
        }

        // If the stack still contains elements, then it is an invalid expression.
        return stack.isEmpty();
    }
}
