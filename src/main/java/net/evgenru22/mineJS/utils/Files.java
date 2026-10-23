package net.evgenru22.mineJS.utils;

import net.evgenru22.mineJS.MineJS;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class Files {
    public static List<String> findJsFiles() {
        Path startPath = Paths.get(MineJS.getInstance().getDataPath().toString(), "scripts");
        try (var stream = java.nio.file.Files.walk(startPath)) {
            return stream
                    .filter(java.nio.file.Files::isRegularFile)
                    .filter(path -> path.toString().toLowerCase().endsWith(".js"))
                    .map(startPath::relativize)
                    .map(Path::toString)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            MineJS.getInstance().getLogger().warning("Can't walk files for tab completion: " + e.getMessage());
            return null;
        }
    }
}
