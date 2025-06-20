public class CountWords {
    public static void main(String[] args) {
        String sentence = "Hello this is itsCodeTide";
        int count = 0;
        boolean isWord = false;

        for (int i = 0; i < sentence.length(); i++) {
            if (sentence.charAt(i) != ' ') {
                if (!isWord) {
                    count++;
                    isWord = true;
                }
            } else {
                isWord = false;
            }
        }

        System.out.println("Word count: " + count);
    }
}