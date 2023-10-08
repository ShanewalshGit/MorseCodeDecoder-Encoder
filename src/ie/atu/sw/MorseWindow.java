package ie.atu.sw;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.ThreadLocalRandom;

public class MorseWindow extends MorseCode {
	private Colour[] colours = Colour.values(); // This might come in handy
	private ThreadLocalRandom rand = ThreadLocalRandom.current(); // This will definitely come in handy
	private JFrame win; // The GUI Window
	private JTextArea txtOutput = new JTextArea(); // The text box to output the results to
	private JTextField txtFilePath; // The file name to process

	public MorseWindow(){
		
        var dot = new JPanel();

		/*
		 * Create a window for the application. Building a GUI is an example of 
		 * "divide and conquer" in action. A GUI is really a tree. That is why
		 * we are able to create and configure GUIs in XML.
		 */
		win = new JFrame();
		win.setTitle("Data Structures & Algorithms 2023 - Morse Encoder/Decoder");
		win.setSize(650, 400);
		win.setResizable(false);
		win.setLayout(new FlowLayout());
		
        /*
         * The top panel will contain the file chooser and encode / decode buttons
         */
        var top = new JPanel(new FlowLayout(FlowLayout.LEADING));
        top.setBorder(new javax.swing.border.TitledBorder("Select File"));
        top.setPreferredSize(new Dimension(600, 80));

        txtFilePath =  new JTextField(20);
		txtFilePath.setPreferredSize(new Dimension(100, 30));

		
		var chooser = new JButton("Browse...");
		chooser.addActionListener((e) -> {
			var fc = new JFileChooser(System.getProperty("user.dir"));
			var val = fc.showOpenDialog(win);
			if (val == JFileChooser.APPROVE_OPTION) {
				var file = fc.getSelectedFile().getAbsoluteFile();
				txtFilePath.setText(file.getAbsolutePath());
			}
		});
		
		
		var btnEncodeFile = new JButton("Encode");
		btnEncodeFile.addActionListener(e -> {
			
			String path = txtFilePath.getText(); //Call getText() to get the file name // O(1)
			replaceText("Encoding.....\n" + path); // O(1)
			
			// Start your encoding here, but put the logic in another class
			
			try {
				BufferedReader br = new BufferedReader(new FileReader(path)); // O(1), Reads file line by line
				StringBuilder stringBuilder = new StringBuilder(); // O(1)
				
				br.lines().forEach(s -> { // O(N), for each line, do the following: 
			        StringBuilder wordBuilder = new StringBuilder(); // O(1), create StringBuilder for words
			        for (char c : s.toCharArray()) { // O(M), M is No. of Chars.
			            if (Character.isLetter(c)) { // If char is letter, append to wordBuilder
			                wordBuilder.append(Character.toLowerCase(c)); // O(1)
			            } else { // If not a letter, append morse to StringBuilder and reset
			                String word = wordBuilder.toString(); // O(1)
			                if (!word.isEmpty()) {
			                	//Appends morse by word (returned from Method in MorseCode)
			                    stringBuilder.append(englishToMorse(word)).append(" "); // O(1), 
			                    wordBuilder.setLength(0); // O(1), resets the wordbuilder for next use
			                }
			            }
			        }
			        // After adding Chars to wordBuilder, append MorseCode to StringBuilder
			        String word = wordBuilder.toString(); // O(1)
			        if (!word.isEmpty()) {
			        	//Appends morse by word (returned from Method in MorseCode)
			            stringBuilder.append(englishToMorse(word)).append(" "); // O(1), 
			        }

				});
				
				appendText("[ENCODED]"); // O(1)
			    appendText(stringBuilder.toString()); // O(1), appends encoded Morse to Swing UI
			    appendText("\n Translation saved to txt file in project directory\n"); // O(1)
			    //Saves encoded Morse to txt file in proj. directory using separate class method
				SaveTranslation.SaveToFile(stringBuilder.toString(), 'm'); // O(1), 
			    br.close(); // O(1)
			    //Sets dot colour according to if file is read successfully
			    dot.setBackground(getGreenColour()); // O(1)
			//Exception Handling
			} catch (FileNotFoundException e1) {
				appendText("File was not chosen/found\n"); // O(1)
				dot.setBackground(getRedColour()); // O(1)
			}
			catch (IOException e1) {
			    appendText("Error Occured"); // O(1)
			    dot.setBackground(getRedColour()); // O(1)
			}
		});
			
			
		var btnDecodeFile = new JButton("Decode");
		btnDecodeFile.addActionListener(e -> {
			/*
			 * Start your decoding here, but put the logic in another class
			 */
			String path = txtFilePath.getText(); //Call getText() to get the file name
			replaceText("Decoding.....\n" + path); // O(1)
			
			try {
				BufferedReader br = new BufferedReader(new FileReader(path)); // O(1), Reads file line by line
				StringBuilder stringBuilder = new StringBuilder(); // O(1)
				
				// For each line, split Morse chunks by spaces
				br.lines().forEach(s -> { // O(N) 
					String[] morseLetters = s.split("\\s+");
					
					for(String letters : morseLetters) {
						// for each chunk of Morse, decode it using method from MorseCode and append to builder (with space)
						stringBuilder.append(morseToEnglish(letters)); // O(NK), N is no. of words, K is word length
					}
				});
				
				appendText("[DECODED]"); // O(1)
				appendText(stringBuilder.toString()); // O(T), appends decoded Morse to Swing UI
			    //Saves decoded Morse to txt file in proj. directory using separate class method
				appendText("\n Translation saved to txt file in project directory\n");
				SaveTranslation.SaveToFile(stringBuilder.toString(), 'e'); // O(S)
				br.close(); // O(1)
			    //Sets dot colour according to if file is read successfully
				dot.setBackground(getGreenColour()); // O(1)
			//Exception Handling
			} catch (FileNotFoundException e1) {
				appendText("File not chosen/found\n"); // O(1)
				dot.setBackground(getRedColour()); // O(1)
			} catch (IOException e1) {
			    appendText("Error Occured"); // O(1)
				dot.setBackground(getRedColour()); // O(1)
			}
		});
		
		//Add all the components to the panel and the panel to the window
        top.add(txtFilePath);
        top.add(chooser);
        top.add(btnEncodeFile);
        top.add(btnDecodeFile);
        win.getContentPane().add(top); //Add the panel to the window
        
        
        /*
         * The middle panel contains the coloured square and the text
         * area for displaying the outputted text. 
         */
        var middle = new JPanel(new FlowLayout(FlowLayout.LEADING));
        middle.setPreferredSize(new Dimension(600, 200));

        dot.setBorder(new SoftBevelBorder(SoftBevelBorder.RAISED));
        dot.setBackground(getRandomColour());
        dot.setPreferredSize(new Dimension(140, 150));
        dot.addMouseListener(new MouseAdapter() { 
        	//Can't use a lambda against MouseAdapter because it is not a SAM
        	public void mousePressed( MouseEvent e ) {  
        		dot.setBackground(getRandomColour());
        	}
        });
        
        //Add the text area
		txtOutput.setLineWrap(true);
		txtOutput.setWrapStyleWord(true);
		txtOutput.setBorder(new SoftBevelBorder(SoftBevelBorder.LOWERED));
		
		var scroller = new JScrollPane(txtOutput);
		scroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scroller.setPreferredSize(new Dimension(450, 150));
		scroller.setMaximumSize(new Dimension(450, 150));
		
		//Add all the components to the panel and the panel to the window
		middle.add(dot);
		middle.add(scroller);
		win.getContentPane().add(middle);
		
		
		/*
		 * The bottom panel contains the clear and quit buttons.
		 */
		var bottom = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        bottom.setPreferredSize(new java.awt.Dimension(500, 50));

        //Create and add Clear and Quit buttons
        var clear = new JButton("Clear");
        clear.addActionListener((e) -> txtOutput.setText(""));
        
        var quit = new JButton("Quit");
        quit.addActionListener((e) -> System.exit(0));
        
        //Add all the components to the panel and the panel to the window
        bottom.add(clear);
        bottom.add(quit);
        win.getContentPane().add(bottom);       
        
        
        /*
         * All done. Now show the configured Window.
         */
		win.setVisible(true);
	}

	private Color getRandomColour() {
		Colour c = colours[rand.nextInt(0, colours.length)];
		return Color.decode(c.hex() + "");
	}
	
	private Color getGreenColour() {
		Colour c = Colour.NEON_GREEN;
		return Color.decode(c.hex() + "");
	}
	
	private Color getRedColour() {
		Colour c = Colour.RED;
		return Color.decode(c.hex() + "");
	}

	protected void replaceText(String text) {
		txtOutput.setText(text);
	}

	protected void appendText(String text) {
		txtOutput.setText(txtOutput.getText() + text);
	}
}