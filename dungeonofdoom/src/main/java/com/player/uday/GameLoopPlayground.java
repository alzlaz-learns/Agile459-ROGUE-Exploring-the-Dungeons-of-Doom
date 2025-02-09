package com.player.uday;

import java.util.Scanner;

public class GameLoopPlayground {
    public static void main(String[] args) {
        PlayerPlayground player = new PlayerPlayground("Hero");
        Scanner scanner = new Scanner(System.in);

        while (player.getHealth() > 0) {
            System.out.println("Enter command: ");
            String command = scanner.nextLine();

            switch (command) {
                case "eat":
                    player.eatFood();
                    break;
                case "inventory":
                    player.getPack().listItems();
                    break;
                case "drop":
                    System.out.println("Enter item to drop: ");
                    String itemName = scanner.nextLine();
                    ItemPlayground item = player.getPack().getItem(itemName);
                    if (item != null) {
                        player.getPack().dropItem(item);
                        System.out.println("Dropped " + item.getName());
                    } else {
                        System.out.println("Item not found.");
                    }
                    break;
                case "move":
                    System.out.println("Moving...");
                    break;
                case "attack":
                    MonsterPlayground monster = new MonsterPlayground("Goblin", 20);
                    player.attack(monster);
                    break;
                default:
                    System.out.println("Unknown command.");
                    break;
            }

            HungerSystemPlayground.processHunger(player);
            HealingSystemPlayground.healPlayer(player);
        }

        scanner.close();
    }
}

