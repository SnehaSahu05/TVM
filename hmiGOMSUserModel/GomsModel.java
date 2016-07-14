package hmiGOMSUserModel;

import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

public class GomsModel {

	// main method
	// *****************************************************************
	public static void main(String[] args) throws IOException {
		/*
		 * Input 5 User Parameters based on user story || Text(t/true)
		 * Symbol(t/true) Language(eng/deu) Destination(t/true)
		 * PaymentMode(cash/card) || ~ || T T Eng T cash ||
		 */

		// initialization
		int i = 1;
		Scanner scan = new Scanner(System.in);
		passParamSet(args);
		String in;
		boolean s;

		while (i != 0) {
			switch (i) {
			case 1: // Screen 1:
				s = false;
				System.out.println(
						" SI-1:HOME Screen {<Icon.train, true>, <Icon.hand, true>, <Text.’Touch here to start!’>} \n\tInput to user: visual{icons, text}");
				while (!s && i == 1) {
					if (UserParam.TEXT.equals("true") || UserParam.SYMB.equals("true")) {
						s = true;
						i = 2;
						System.in.read(); // throws IOException
					} else {
						// machine asks user to rectify knowledge of text or
						// symbol
						System.out.print(
								"\n\t\t textual or symbolic knowledge is mandate!!\n\t\t select to change user knowledge - \n\t\t 1)text=true \t 2)symbol=true \t 3)both true  ");
						in = scan.next();
						switch (Integer.parseInt(in)) {
						case 1:
							initParam(0, "true", true);
							break;
						case 2:
							initParam(1, "true", true);
							break;
						case 3:
							initParam(0, "true", false);
							initParam(1, "true", true);
							break;
						}
					}
				}
				if (i == 2) {
					System.out.println("\tOutput: touch{train} \n");
				}
				break;

			case 2: // Screen 2:
				s = false;
				System.out.println(
						"\n SI-2:LANG Screen {<Button. English, True>, <Button. Deutsch, True>,\n\t\t <Icon.Home, True>, <Text.’Select a language of your choice’>}");
				System.out.print(
						"\tInput to user: visual{icons, text, buttons}\n\t\t select to change user knowledge - \n\t\t 1)Home=true \t 2)Home=false  ");
				in = scan.next();
				while (!s && i == 2) {
					if (Integer.parseInt(in) == 1) {
						i = 1; // make screen = 1
						System.out.println("\tOutput: touch{icon.home}\n");
					} else if (UserParam.LANG.equals("eng")) {
						System.in.read(); // wait until key pressed
						System.out.println("\tOutput: touch{button.English}");
						s = true;
						i = 3;
					} else if (UserParam.LANG.equals("deu")) {
						System.in.read(); // wait until key pressed
						System.out.println("\tOutput: touch{button.Deutsch}");
						s = true;
						i = 3;
					} else {
						// machine conveys user: about no other language option.
						System.out.print(
								"\n\t\t operational only in english or deutsch\n\t\t select to change user knowledge - \n\t\t 1)lang=English \t 2)lang=Deutsch \t 3)lang=other   ");
						in = scan.next();
						switch (Integer.parseInt(in)) {
						case 1:
							initParam(2, "English", true);
							break;
						case 2:
							initParam(2, "Deutsch", true);
							break;
						default:
							initParam(2, "other", true);
							break;
						}
						in = "0";
						// System.out.println(UserParam.LANG + s);
					}
				}
				break;

			case 3: // Screen 3-->4:
				System.out.print("\n\n SI-3:SOURCE is auto selected to current station");
				System.in.read();
				i = 4;
				break;

			case 4: // Screen 4:
				s = true;
				System.out.println(
						"\n\n SI-4:DESTINATION Screen {<Icon.Home, True>, <Input.Search, True>, <Text.’Select your destination place’>,\n\t\t <button.back>, <buttons.map>, <button.destination>}");
				System.out.print(
						"\tInput to user: visual{text, icons, buttons, search field}\n\t\t select to change user knowledge - \n\t\t 1)Home=true \t 2)Back=true \t 3)Home=back=false  ");
				in = scan.next();
				while (s && i == 4) {
					if (Integer.parseInt(in) == 1) {
						i = 1; // make screen = 1
						System.out.println("\tOutput: touch{icon.home}\n");
					} else if (Integer.parseInt(in) == 2) {
						i = 2; // make screen = 2:langScreen
						System.out.println("\tOutput: touch{button.back}\n");
					} else if (UserParam.DEST.equals("false")) {
						UserParam.MAP = true;
						System.out.println("\tOutput: touch{button.map}");
						System.in.read();
						i = 5;
						s = false;
					} else {
						System.out.println("\tOutput: touch{button." + UserParam.uiDest + "}");
						System.in.read();
						i = 6;
						s = false;
					}
				}
				break;

			case 5: // Screen 5:
				/*
				 * s= false; while (!s[5] && i == 5 &&
				 * UserParam.uiDest.equals(null)) { // machine gives user the
				 * facility to restart System.out.println(
				 * "\t Type - YES: HOME Screen \t NO: continue..."); in =
				 * scan.next(); initParam(5, in, false); if
				 * (UserParam.ABORT.equals("true")) { i = 1; // make screen = 1
				 * System.out.print("\tOutput: touch{icon.home}"); initParam(5,
				 * "false", true); // change abort to // false } else {
				 * System.out.println(
				 * "\t Type - YES: to go BACK \t NO: continue..."); in =
				 * scan.next(); initParam(6, in, false); if
				 * (UserParam.BACK.equals("true")) { i = 4; // make screen = 4
				 * System.out.print("\tOutput: touch{button.back}");
				 * initParam(6, "false", true); // change back to // false }
				 * else { // dest invisible - zoom in/out // rewrite input
				 * System.out.print("\tInput: visual{text, icon, button, map}");
				 * UserParam.uiDest = "?"; while (UserParam.uiDest.equals("?"))
				 * { System.out.println("\t  Zoom in/out to see location"); in =
				 * scan.next(); System.out.println(
				 * "\t  select destination if visible (here enter name) OR enter '?':"
				 * ); UserParam.uiDest = scan.next(); } // destination known
				 * System.out.print("\tOutput: touch{button." + UserParam.uiDest
				 * + "}"); s[5] = true; i = 6; } } }
				 */
				System.out.println(
						"\n\n SI-5:MAP View {<Icon.Home, True>, <Button.Back, True>, <Map.googleMap, True>,\n\t\t <Text.’Click on the destination place’>, <Button.SelectedDestination, True>}");
				System.out.println("\tInput to user: visual{buttons,googleMap}");
				System.out.println("\t\t Since we cannot display zoomable map view, destination is auto set.");
				UserParam.uiDest = "Nibelungenplatz";
				i = 6;
				System.in.read();
				System.out.println("\tOutput: touch{button." + UserParam.uiDest + "}");
				break;

			case 6: // Screen 6:
				s = true;
				System.out.print(
						"\n\n SI-6:PAYMENT Screen {<Icon.Home, True>, <Button.back>, <button.cancel>,\n\t\t <Text,’Net Payment Amount is 5.20 Euros’>, <Button.card>, <button.cash> }");
				System.out.print(
						"\n\tInput to user: visual{text, icon, buttons}\n\t\t select to change user knowledge - \n\t\t 1)Home=true \t 2)Back=true \t 3)Cancel=true \t 4)all false   ");
				in = scan.next();
				while (s && i == 6) {
					if (Integer.parseInt(in) == 1) {
						i = 1; // make screen = 1
						System.out.println("\tOutput: touch{icon.home}\n");
					} else if (Integer.parseInt(in) == 3) {
						i = 1; // make screen = 1
						System.out.println("\tOutput: touch{button.cancel}\n");
					} else if (Integer.parseInt(in) == 2) {
						if (UserParam.MAP) {
							i = 5; // Map View
						} else {
							i = 4; // Dest screen
						}
						System.out.println("\tOutput: touch{button.back}\n");
					} else if (UserParam.PAYBY.equals("invalid")) {
						System.out.println(
								"\n\t\t select to change user knowledge - \n\t\t 1)PaymentMode=cash \t2)PaymentMode=card ");
						in = scan.next();
						switch (Integer.parseInt(in)) {
						case 1:
							initParam(4, "cash", true);
							break;
						case 2:
							initParam(4, "card", true);
							break;
						}
						in="0";
					} else /*i.e. when (UserParam.PAYBY.equals("card" || "cash"))*/ {
						System.out.println("\tOutput: touch{button." +UserParam.PAYBY+ "}");
						System.in.read();
						i = 7;
						s = false;
					}
				}
				break;

			case 7: // Screen 7-->8/9:
				System.out.println(
						"\n SI-7:PAYMENT Processing ...\n\t  Your payment through " +UserParam.PAYBY.toUpperCase()+ " is being processed!!");
				Random r = new Random();
				i = r.nextInt(9 - 8) + 8;
				if (i == 8) {
					System.out.println("\n\n SI-8:Success");
				} else if (i == 9) {
					System.out.println("\n\n SI-9:ERROR Screen");
				}

			default:
				System.out.println("\n\n Have a good day !!) ");
				// i=0 to terminate while loop [no more tickets]
				i = 0;
				break;

			}
		}

	}

	// *********************SET LIST OF ParamETERS **************************
	private static void passParamSet(String[] args) {
		for (int k = 0; k < args.length; k++) {
			if (k == args.length - 1) {
				initParam(k, args[k], true);
			} else {
				initParam(k, args[k], false);
			}
		}
	}

	// *********************SET SINGLE ParamETER **************************
	private static void initParam(int k, String input, boolean print) {
		switch (k) {
		case 0: // TEXT: args[0] - T >> T:true if user can read
			if (input.toLowerCase().equals("t") || input.toLowerCase().equals("true")) {
				UserParam.TEXT = "true";
			} else {
				UserParam.TEXT = "false";
			}
			break;
		case 1: // SYMB: args[1] - T >> T:true if there is learnability of
				// basic symbols
			if (input.toLowerCase().equals("t") || input.toLowerCase().equals("true")) {
				UserParam.SYMB = "true";
			} else {
				UserParam.SYMB = "false";
			}
			break;
		case 2: // LANG: args[2] - Eng, Deu, ...
			if (input.toLowerCase().equals("eng") || input.toLowerCase().equals("english")) {
				UserParam.LANG = "eng";
			} else if (input.toLowerCase().equals("deu") || input.toLowerCase().equals("deutsch")) {
				UserParam.LANG = "deu";
			} else {
				UserParam.LANG = input.toLowerCase();
			}
			break;
		case 3: // DEST: args[3] - T >> T:true if destination name is known
			if (input.toLowerCase().equals("t") || input.toLowerCase().equals("true")) {
				UserParam.DEST = "true";
				System.out.print("\n  define User knowledge for Destination Station Name -");
				Scanner d = new Scanner(System.in);
				UserParam.uiDest = d.next();
			} else {
				UserParam.DEST = "false";
			}
			break;
		case 4: // PAYBY: args[4] - cash, card ...
			if (input.toLowerCase().equals("cash")) {
				UserParam.PAYBY = "cash";
			} else if (input.toLowerCase().equals("card")) {
				UserParam.PAYBY = "card";
			} else {
				UserParam.PAYBY = "invalid";
			}
			break;
		default: // any other input
			break;
		}
		// rewrite user prop
		if (print) {
			System.out.println("\nUser Info:\tText\tSymbol\tLanguage\tDestination\tPaymentMode\tHOME\tGoBack\n\t\t"
					+ UserParam.TEXT + "\t" + UserParam.SYMB + "\t" + UserParam.LANG + "\t\t" + UserParam.DEST + "\t\t"
					+ UserParam.PAYBY + "\t\t" + UserParam.ABORT + "\t" + UserParam.BACK + "\n");
		}

	}

}
