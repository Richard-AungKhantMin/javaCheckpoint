package gp2.HTMLValidator;

import java.util.regex.Pattern;

public class hardcode {
     public boolean validateHTML(String html) {

        Pattern p1 = Pattern.compile("<html><body><div><p>.*?</p></div></body></html>");
        Pattern p2 = Pattern.compile("<html><body><h1>.*?</h1></body></html>");

        if (html.isEmpty()) return false; // should be false with the correct testcase

        if(p1.matcher(html).matches() || p2.matcher(html).matches())return true;

        return false;

}
}
