import java.util.*;

public class RemoveInvalidParanthesis {
    public List<String> removeInvalidParentheses(String s) {
        
        HashSet<String> set = new HashSet<>();
        Queue<String> q = new LinkedList<>();
        
        List<String> list = new ArrayList<>();
        boolean found = false;
        
        // Adding the current string to set just to not explore that again amd queue to explore it next
        set.add(s);
        q.add(s);        
        
        // exploring level by level
        while(!q.isEmpty()){
            s = q.poll();
            // if valid then we add to the result list and changing flag to true
            if(isValid(s)){
                list.add(s);
                found = true;
            }
            if(!found){
                // Getting all the string to explore
                for(int i=0; i<s.length(); i++){
                    Character c = s.charAt(i);
                    if(!(c == '(' || c == '[' || c =='{' || c == '}' || c == ']' || c ==')'))
                        continue;
                    String temp = s.substring(0,i)+s.substring(i+1, s.length());
                    if(!set.contains(temp))
                    {
                        set.add(temp);
                        q.add(temp);
                    }
                }
            }
        }
        
        return list;
    }
    // Valid paranthesis check using Stacks
    public boolean isValid(String s) {
        Stack<Character> st = new Stack<>();
        for(Character c : s.toCharArray()){
            if(c == '(' || c == '[' || c =='{' || c == '}' || c == ']' || c ==')'){
                if(c == '(' || c == '[' || c =='{')
                    st.push(c);
                else if(!st.isEmpty() && c == ')')
                    if(!st.isEmpty() && st.peek() != '(')
                        return false;
                    else
                        st.pop();
                else if(!st.isEmpty() && c == '}')
                    if(!st.isEmpty() && st.peek() != '{')
                        return false;
                    else
                        st.pop();
                else if(!st.isEmpty() && c == ']')
                    if(!st.isEmpty() && st.peek() != '[')
                        return false;
                    else
                        st.pop();
                else
                    return false;
            }
        }
        if(st.size() == 0)
            return true;
        return false;
    }
    
}
