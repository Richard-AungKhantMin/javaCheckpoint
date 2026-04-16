package gp2.HTMLValidator;

import java.util.Stack;

public class anotherSolution {


    public boolean validateHTML(String html) {
        if (html == null || html.isBlank()) {
            return false;
        }
        
        html = html.trim().toLowerCase();
        
        if (html.charAt(0) != '<' || html.charAt(html.length() - 1) != '>') {
            return false;
        }
        
        Stack<String> stack = new Stack<>();
        
        for (int i = 0; i < html.length(); i++) {
            if (html.charAt(i) == '<') {
                int closeIdx = html.indexOf('>', i);
                if (closeIdx == -1) return false;
                
                String tag = html.substring(i + 1, closeIdx).trim();
                
                if (tag.endsWith("/")) {
                    // Self-closing tag, skip
                } else if (tag.startsWith("/")) {
                    // Closing tag - must match top of stack
                    if (stack.isEmpty() || !stack.pop().equals(tag.substring(1))) {
                        return false;
                    }
                } else {
                    // Opening tag - push to stack
                    stack.push(tag);
                }
                
                i = closeIdx;
            }
        }
        
        return stack.isEmpty();
    }
}

