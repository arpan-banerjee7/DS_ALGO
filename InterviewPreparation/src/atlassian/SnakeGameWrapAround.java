package atlassian;

import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Optional;


public class SnakeGameWrapAround {
    class Pair {
        int x;
        int y;

        Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Pair pair = (Pair) o;
            return x == pair.x && y == pair.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }
    }

    int row;
    int col;
    int x;
    int y;
    LinkedHashSet<Pair> snake;
    LinkedHashSet<Pair> foods;

    SnakeGameWrapAround(int row, int col, int[][] foods) {
        this.row = row;
        this.col = col;
        this.snake = new LinkedHashSet<>();
        this.foods = new LinkedHashSet<>();
        for (int i = 0; i < foods.length; i++) {
            this.foods.add(new Pair(foods[i][0], foods[i][1]));
        }
        snake.add(new Pair(0, 0));
        this.x = 0;
        this.y = 0;
    }

    private boolean validate(int x, int y) {
        x = (x % row + row) % row;
        y = (y % col + col) % col;
        if (snake.contains(new Pair(x, y))) return true;
        return false;
    }

    private void changeSnakeSize(int x, int y) {
        Pair food = foods.stream().findFirst().get();
        if (food.equals(new Pair(x, y))) {
            snake.add(new Pair(x, y));
            foods.remove(food);
        } else {
            snake.add(new Pair(x, y));
            //Remove the last element from the linkedHashSet
            Pair p = snake.stream().findFirst().get();
            snake.remove(p);

        }
    }

    private int move(Character c) {

        if (c.equals('U')) {
            if (validate(x - 1, y)) return -1;
            x--;
        } else if (c.equals('D')) {
            if (validate(x + 1, y)) return -1;
            x++;
        } else if (c.equals('L')) {
            if (validate(x, y - 1)) return -1;
            y--;
        } else if (c.equals('R')) {
            if (validate(x, y + 1)) return -1;
            y++;
        } else {
            System.out.println("Invalid input");
            return -1;
        }
        x = (x % row + row) % row;
        y = (y % col + col) % col;
        changeSnakeSize(x, y);

        // for printing(not required)
        if (false) {
            for (Pair p : snake) {
                System.out.println(p.x + "  " + p.y);
            }
        }
        return snake.size() - 1;
    }


    public static void main(String[] args) {

        SnakeGameWrapAround snake = new SnakeGameWrapAround(2, 3, new int[][]{{1, 2}, {0, 1}, {1, 0}});

        System.out.println(snake.move('R')); //-> Returns 0
        System.out.println(snake.move('D')); //-> Returns 0
        System.out.println(snake.move('R')); //-> Returns 1
        System.out.println(snake.move('U')); //-> Returns 1
        System.out.println(snake.move('L')); //-> Returns 2
        System.out.println(snake.move('U')); //-> Returns 2 (wraps around)
        System.out.println("---------------");

        SnakeGameWrapAround snake1 = new SnakeGameWrapAround(2, 3, new int[][]{{1, 2}, {0, 1}, {1, 0}});
        System.out.println(snake1.move('R')); // Should return 0
        System.out.println(snake1.move('D')); // Should return 0
        System.out.println(snake1.move('R')); // Should return 1
        System.out.println(snake1.move('U')); // Should return 1
        System.out.println(snake1.move('L')); // Should return 2
        System.out.println(snake1.move('U')); // Should return 2 (wraps around)
        System.out.println("---------------");

    }
}



/*
Leetcode 353: Design Snake Game
Design a Snake game that is played on a device with screen size = width x height. Play the game online if you are not familiar with the game.
The snake is initially positioned at the top left corner (0,0) with length = 1 unit.
You are given a list of food’s positions in row-column order. When a snake eats the food, its length and the game’s score both increase by 1.
Each food appears one by one on the screen. For example, the second food will not appear until the first food was eaten by the snake.
When a food does appear on the screen, it is guaranteed that it will not appear on a block occupied by the snake.
Example:
Given width = 3, height = 2, and food = [[1,2],[0,1]].
Snake snake = new Snake(width, height, food);
Initially the snake appears at position (0,0) and the food at (1,2).
|S| | |
| | |F|
snake.move("R"); -> Returns 0
| |S| |
| | |F|
snake.move("D"); -> Returns 0
| | | |
| |S|F|
snake.move("R"); -> Returns 1 (Snake eats the first food and right after that, the second food appears at (0,1) )
| |F| |
| |S|S|
snake.move("U"); -> Returns 1
| |F|S|
| | |S|
snake.move("L"); -> Returns 2 (Snake eats the second food)
| |S|S|
| | |S|
snake.move("U"); -> Returns -1 (Game over because snake collides with border)
* */
// Console Output

/*
0
0
1
1
2
-1
*/