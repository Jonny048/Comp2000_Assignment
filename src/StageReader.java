import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

public class StageReader {

    public Stage readStage(String path) {
        Stage stage = new Stage();

        try {
            List<String> lines = Files.readAllLines(Paths.get(path));

            for (String line : lines) {
                int idx = line.indexOf('=');
                if (idx == -1) {
                    continue; // Skip lines without a valid format
                }

                char colChar = Character.toUpperCase(line.charAt(0));
                int col = colChar - 'A';
                int row = Integer.parseInt(line.substring(1, idx));
                String actorType = line.substring(idx + 1).trim();

                boolean isBot = actorType.endsWith(" bot");
                if (isBot) {
                    actorType = actorType.substring(0, actorType.length() - 4);
                }

                Optional<Cell> cell = stage.grid.cellAtColRow(col, row);
                if (!cell.isPresent()) {
                    continue; // Skip if the cell is out of bounds
                }

                Actor newActor = null;
                switch (actorType.toLowerCase()) {
                    case "cat":
                        newActor = new Cat(cell.get(), isBot);
                        break;
                    case "dog":
                        newActor = new Dog(cell.get(), isBot);
                        break;
                    case "bird":
                        newActor = new Bird(cell.get(), isBot);
                        break;
                }

                if (newActor != null) {
                    stage.addActor(newActor);
                }
            }
        } catch (IOException | NumberFormatException e) {
            // Fallback to a default stage if file reading fails
            stage.addActor(new Cat(stage.grid.cellAtColRow(0, 0).get(), false));
            stage.addActor(new Dog(stage.grid.cellAtColRow(0, 15).get(), true));
            stage.addActor(new Bird(stage.grid.cellAtColRow(12, 9).get(), true));
            stage.spawnBone();
            stage.spawnFish();
            stage.spawnSeeds();
        }
        return stage;
    }
}