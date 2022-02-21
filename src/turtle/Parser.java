package turtle;

public class Parser {

	Turtle turtle;

	public Parser(Turtle t, String commands) {
		turtle = t;
		commands = commands.replaceAll("\\[", " [ ");
		commands = commands.replaceAll("\\]", " ] ");
		commands = commands.replaceAll("\\s+", " ");
		commands = commands.replaceAll("forward", "fd");
		commands = commands.replaceAll("backward", "bk");
		commands = commands.replaceAll("bd", "bk");
		commands = commands.replaceAll("right", "rt");
		commands = commands.replaceAll("left", "lt");
		commands = commands.replaceAll("penup", "pu");
		commands = commands.replaceAll("pendown", "pd");
		commands = commands.replaceAll("showturtle", "st");
		commands = commands.replaceAll("hideturtle", "ht");
		parse(commands.split(" "));
	}

	public void parse(String[] tokens) {
		for (int i = 0; i < tokens.length; i++) {
			String token = tokens[i];
			switch (token) {
			case "repeat": {
				int times = Integer.parseInt(tokens[++i]);
				int fB = -1, lB = -1;
				int fC = 0, lC = 0;
				for (int j = i + 1; j < tokens.length; j++) {
					String t = tokens[j];
					if (t.equals("[")) {
						if (fB == -1) {
							fB = j;
						}
						fC++;
					}
					if (t.equals("]")) {
						lC++;
						if (fC == lC) {
							lB = j;
							i = j;
							break;
						}
					}
				}
				String newToken = "";
				for (int x = fB + 1; x < lB; x++) {
					newToken += tokens[x] + " ";
				}
				for (int t = 0; t < times; t++) {
					parse(newToken.trim().split(" "));
				}
				break;
			}
			case "fd": {
				int arg = Integer.parseInt(tokens[++i]);
				turtle.fd(arg);
				break;
			}
			case "pu": {
				turtle.penup();
				break;
			}
			case "pd": {
				turtle.pendown();
				break;
			}
			case "bk": {
				int arg = Integer.parseInt(tokens[++i]);
				turtle.fd(-arg);
				break;
			}
			case "rt": {
				int arg = Integer.parseInt(tokens[++i]);
				turtle.rt(arg);
				break;
			}
			case "lt": {
				int arg = Integer.parseInt(tokens[++i]);
				turtle.rt(-arg);
				break;
			}
			case "cs": {
				turtle.clearScreen();
				break;
			}
			case "ht": {
				turtle.showTurtle = false;
				break;
			}
			case "st": {
				turtle.showTurtle = true;
				break;
			}
			case "home": {
				turtle.home();
				break;
			}
			case "exit": {
				System.exit(0);
				break;
			}
			default: {
				System.err.println(token + " Command Not Found");
				break;
			}
			}
		}
	}

}
