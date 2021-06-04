package hu.demo.vaccination.utility;

import lombok.Getter;

public enum FilePathDefinition {
    SAVE_PATH("backupfiles");

    @Getter
    private final String definition;

    FilePathDefinition(String definition) {
        this.definition = definition;
    }
}
