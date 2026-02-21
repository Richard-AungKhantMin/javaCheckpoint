public class HTMLValidator {
//    private class TagResult {
//        private boolean found;
//        private String html;

//        public TagResult(boolean found, String html) {
//            this.found = found;
//            this.html = html;
//        }
//        public boolean getFound() {
//            return this.found;
//        }
//        public String getHtml()
//        {
//            return this.html;
//        }
//    }
    public boolean validateHTML(String html) {
        if (html == null || html.isBlank()) {
            return true;
        }
        String[] stack = new String[html.length()];
        int size = 0;
        html = html.toLowerCase();
//        html = html.stripLeading();
//        html = html.stripTrailing();
        html = html.trim();
        String currentTag;
        int first = 0;
//        boolean selfClosingTag = false;
        boolean foundEnd = true;
        if(html.charAt(0) != '<') {
            return false;
        }
        if(html.charAt(html.length()-1) != '>') {
            return false;
        }
        for (int i = 0; i < html.length(); i++) {
            if (html.charAt(i) == '<') {
                if (!foundEnd) {
                    return false;
                }
                first = i + 1;
                foundEnd = false;
            } else if (html.charAt(i) == '>') {
                currentTag = html.substring(first, i);
                if(currentTag.endsWith("/")) {
                    foundEnd = true;
                    continue;
                }
                if (!currentTag.isBlank()) {
                    stack[size] = currentTag;
                    size++;
                    foundEnd = true;
                }
            }
        }
        if(size%2 == 1) {
            return  false;
        }
        int i = 0;
        for (i = 1; i < size - 2; i++) {
            if(("/"+stack[i]).equals(stack [i + 1])){
                stack[i] = null;
                stack[i+1] = null;
            }
        }
        for(i = 0; i < size / 2; i++ ){
            if(stack[i] != null && stack [size - i - 1] != null && !(("/"+stack[i]).equals(stack [size - i - 1]))){
                return false;
            }
            stack[i] = null;
            stack[size-i-1] = null;
        }

        if(stack[i] != null){
            return false;
        }
        return true;
    }

}