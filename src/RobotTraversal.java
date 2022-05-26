import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class RobotTraversal {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] coordinates = sc.nextLine().split(" ");
        String[] initialPosition = sc.nextLine().split(" ");
        String commands = sc.next();

        int maxRow = Integer.parseInt(coordinates[0]);
        int maxCol = Integer.parseInt(coordinates[1]);
        int posY = Integer.parseInt(initialPosition[0]);
        int posX = Integer.parseInt(initialPosition[1]);
        char initialDirection = initialPosition[2].charAt(0);


        if ((posY >= 0 && posY <= maxRow) && (posX >= 0 && posX <= maxCol)) {
            try {
                System.out.println(findFinalCoordinates(commands, posY, posX, initialDirection));
            } catch (Exception exception) {
                System.err.println("Exception while parsing input" + exception.getMessage());
            }
        } else {
            System.err.println("Invalid command input");
        }
    }

    private static String findFinalCoordinates(String commands, int y, int x, char initialDirection) throws Exception {

        for (int position = 0; position < commands.length(); position++) {
            Character[] dir = new Character[]{'E', 'N', 'W', 'S'};
            List<Character> directions = Arrays.asList(dir);

            switch (initialDirection) {
                case 'N':
                    if (commands.charAt(position) == 'M')
                        y++;
                    else if (commands.charAt(position) == 'R') {
                        initialDirection = findDir('N', directions, 'R');
                    } else if (commands.charAt(position) == 'L') {
                        initialDirection = findDir('N', directions, 'L');
                    } else
                        throw new Exception("Invalid input");
                    break;

                case 'S':
                    if (commands.charAt(position) == 'M')
                        y--;
                    else if (commands.charAt(position) == 'R')
                        initialDirection = findDir('S', directions, 'R');
                    else if (commands.charAt(position) == 'L')
                        initialDirection = findDir('S', directions, 'L');
                    else
                        throw new Exception("Invalid input");
                    break;

                case 'E':
                    if (commands.charAt(position) == 'M')
                        x++;
                    else if (commands.charAt(position) == 'R')
                        initialDirection = findDir('E', directions, 'R');
                    else if (commands.charAt(position) == 'L')
                        initialDirection = findDir('E', directions, 'L');
                    else
                        throw new Exception("Invalid input");
                    break;

                case 'W':
                    if (commands.charAt(position) == 'M')
                        x--;
                    else if (commands.charAt(position) == 'R')
                        initialDirection = findDir('W', directions, 'R');
                    else if (commands.charAt(position) == 'L')
                        initialDirection = findDir('W', directions, 'L');
                    else
                        throw new Exception("Invalid input");
                    break;

                default:
                    throw new Exception("Invalid direction input");
            }
        }
        return x + " " + y + " " + initialDirection;
    }

    private static Character findDir(Character initialDir, List<Character> dir, char leftOrRight) {
        int index = dir.indexOf(initialDir);
        int size = dir.size();
        if (leftOrRight == 'R') {
            return index > 0 && index < size - 1 ? dir.get(--index) : dir.get(size - 1);
        }
        return index >= 0 && index < size - 1 ? dir.get(++index) : dir.get(0);
    }
}