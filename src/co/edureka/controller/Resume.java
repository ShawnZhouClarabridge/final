package co.edureka.controller;

public class Resume {
    String trainerName;
    String fileName;

    public Resume(String trainerName, String fileName) {
        this.trainerName = trainerName;
        this.fileName = fileName;
    }

    public Resume() {
    }

    public String getTrainerName() {
        return trainerName;
    }

    public void setTrainerName(String trainerName) {
        this.trainerName = trainerName;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public String toString() {
        return "Resume{" +
                "trainerName='" + trainerName + '\'' +
                ", fileName='" + fileName + '\'' +
                '}';
    }
}
