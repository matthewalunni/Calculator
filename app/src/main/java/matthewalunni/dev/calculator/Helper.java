package matthewalunni.dev.calculator;

class Helper {

    /** this method is used to evaluate a string equation.  Mainly used for brackets and
     *  for formulas in the context of this application. **/
    static double Evaluate(final String aString) {
        return new Object() {
            int position = -1, aCharacter;

            void moveToNextChar() {
                if (++position < aString.length()) {
                    aCharacter = aString.charAt(position);
                }
                else {
                    aCharacter = -1;
                }
            }

            boolean eat(int charToEat) {
                while (aCharacter == ' ') moveToNextChar();
                if (aCharacter == charToEat) {
                    moveToNextChar();
                    return true;
                }
                return false;
            }

            double parse() {
                moveToNextChar();
                double result = parseExpression();
                if (position < aString.length()){
                    throw new RuntimeException("Unexpected: " + (char) aCharacter);
                }
                return result;
            }

            double parseExpression() {
                double term = parseTerm();
                while(true) {
                    if (eat('+')) {
                        term += parseTerm(); // addition
                    }
                    else if (eat('-')) {
                        term -= parseTerm(); // subtraction
                    }
                    else {
                        return term;
                    }
                }
            }

            double parseTerm() {
                double t = parseFactor();
                while(true) {
                    if (eat('*')) {
                        t *= parseFactor(); // multiply
                    }
                    else if (eat('/')) {
                        t /= parseFactor(); // divide
                    }
                    else {
                        return t;
                    }
                }
            }

            double parseFactor() {
                if (eat('+')){
                    return parseFactor(); // for positive or negative values
                }
                if (eat('-')) {
                    return -parseFactor(); // for positive or negative values
                }

                double num;
                int startPos = this.position;
                if (eat('(')) { // parentheses
                    num = parseExpression();
                    eat(')');
                }
                else if ((aCharacter >= '0' && aCharacter <= '9') || aCharacter == '.') { // numbers
                    while ((aCharacter >= '0' && aCharacter <= '9') || aCharacter == '.') {
                        moveToNextChar();
                    }
                    num = Double.parseDouble(aString.substring(startPos, this.position));
                }
                else if (aCharacter >= 'a' && aCharacter <= 'z') { // functions
                    while (aCharacter >= 'a' && aCharacter <= 'z') {
                        moveToNextChar();
                    }
                    num = parseFactor();
                }
                else {
                    throw new RuntimeException("Unexpected: " + (char) aCharacter);
                }

                if (eat('^')) {
                    num = Math.pow(num, parseFactor()); // exponent
                }

                return num;
            }
        }.parse();

    }
}
