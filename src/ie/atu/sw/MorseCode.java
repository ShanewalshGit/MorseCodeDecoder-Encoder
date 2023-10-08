package ie.atu.sw;

import java.util.HashMap;
import java.util.Map;

public class MorseCode {
    
	public static String morseToEnglish(String morseChunk) // O(1)
	{
		//StringBuilder for managing encoded English
        StringBuilder decoded = new StringBuilder(); // O(1)
        
        //Hashmap used for encoding Morse to English
		Map<String, Character> english = new HashMap<String, Character>(); // O(1)
		english.put(".-", 'a');
		english.put("-...", 'b');
		english.put("-.-.", 'c');
		english.put("-..", 'd');
		english.put(".", 'e');
		english.put("..-.", 'f');
		english.put("--.", 'g');
		english.put("....", 'h');
		english.put("..", 'i');
		english.put(".---", 'j');
		english.put("-.-", 'k');
		english.put(".-..", 'l');
		english.put("--", 'm');
		english.put("-.", 'n');
		english.put("---", 'o');
		english.put(".--.", 'p');
		english.put("--.-", 'q');
		english.put(".-.", 'r');
		english.put("...", 's');
		english.put("-", 't');
		english.put("..-", 'u');
		english.put("...-", 'v');
		english.put(".--", 'w');
		english.put("-..-", 'x');
		english.put("-.--", 'y');
		english.put("--..", 'z');
        english.put("/",' ');
        english.put("--..--", ',');
        english.put(".-..-.", '"');
        english.put(".----", '1');
        english.put("..---", '2');
        english.put("...--", '3');
        english.put("....-", '4');
        english.put(".....", '5');
        english.put("-....", '6');
        english.put("--...", '7');
        english.put("---..", '8');
        english.put("----.", '9');
        english.put("-----", '0');
        english.put("..--..", '?');
        english.put("-.-.--", '!');
        english.put("-.--.", '(');
        english.put("-.--.-", ')');
        english.put(".-...", '&');
        english.put("---...", ':');
        english.put("-.-.-.", ';');
        english.put("-...-", '=');
        english.put(".-.-.", '+');
        english.put("-....-", '-');
        english.put("..--.-", '_');
        english.put("...-..-", '$');
        english.put(".--.-.", '@');

        // append corresponding English / Special Char from Morse in map
        decoded.append(english.get(morseChunk.toLowerCase())); // O(1)
		return (decoded.toString()); // O(N), return decoded String
	}
	
	public static String englishToMorse(String word) // O(N)
	{
		//StringBuilder for managing encoded English
        StringBuilder encoded = new StringBuilder();
        
        //Hashmap used for encoding English to Morse
        Map<Character, String> morse = new HashMap<Character, String>(); // O(1)
		morse.put('a', ".-");
        morse.put('b', "-...");
        morse.put('c',  "-.-.");
        morse.put('d',  "-..");
        morse.put('e',    ".");
        morse.put('f', "..-.");
        morse.put('g',  "--.");
        morse.put('h', "....");
        morse.put('i',   "..");
        morse.put('j', ".---");
        morse.put('k',   "-.-");
        morse.put('l', ".-..");
        morse.put('m',   "--");
        morse.put('n',   "-.");
        morse.put('o',  "---");
        morse.put('p', ".--.");
        morse.put('q', "--.-");
        morse.put('r', ".-.");
        morse.put('s',  "...");
        morse.put('t',   "-");
        morse.put('u',  "..-");
        morse.put('v', "...-");
        morse.put('w',  ".--");
        morse.put('x', "-..-");
        morse.put('y', "-.--");
        morse.put('z', "--..");
        morse.put(' ', " / ");
        morse.put(',', "--..--");
        morse.put('"', " .-..-.");
        morse.put('1', ".----");
        morse.put('2', "..---");
        morse.put('3', "...--");
        morse.put('4', "....-");
        morse.put('5', ".....");
        morse.put('6', "-....");
        morse.put('7', "--...");
        morse.put('8', "---..");
        morse.put('9', "----.");
        morse.put('0', "-----");
        morse.put('.', ".-.-.-");
        morse.put('?', "..--..");
        morse.put('!', "-.-.--");
        morse.put('(', "-.--.");
        morse.put(')', "-.--.-");
        morse.put('&', ".-...");
        morse.put(':', "---...");
        morse.put(';', "-.-.-.");
        morse.put('=', "-...-");
        morse.put('+', ".-.-.");
        morse.put('-', "-....-");
        morse.put('_', "..--.-");
        morse.put('$', "...-..-");
        morse.put('@', ".--.-.");

        //Parse through word, appending the corresponding Morse from chars in map
		for(int i = 0; i < word.length(); i++) { // O(n)
			encoded.append(morse.getOrDefault(word.charAt(i), "")).append(" "); // Use getOrDefault rather then RegEx to remove unknown special chars
		}
		//Separates Morse words and return encoded String
		encoded.append(" / "); // O(1), 
		return encoded.toString();
	}
}
