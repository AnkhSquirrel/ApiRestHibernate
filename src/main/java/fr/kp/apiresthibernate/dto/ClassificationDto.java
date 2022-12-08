package fr.kp.apiresthibernate.dto;

import fr.kp.apiresthibernate.entities.ClassificationEntity;
import fr.kp.apiresthibernate.entities.FamilleEntity;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Data
public class ClassificationDto {
    private int idClassification;
    private String classification;
    private List<Famille> familles;

    public ClassificationDto(ClassificationEntity classificationEntity) {
        idClassification = classificationEntity.getIdClassification();
        classification = classificationEntity.getClassification();
        familles = toFamilleList(classificationEntity.getFamilles());
    }

    public List<Famille> toFamilleList(List<FamilleEntity> familleEntityList) {
        List<Famille> familleList = new ArrayList<>();
        for (FamilleEntity familleEntity : familleEntityList) {
            familleList.add(new Famille(familleEntity));
        }
        return familleList;
    }

    public static List<ClassificationDto> toClassificationDtoList(List<ClassificationEntity> classificationEntityList) {
        List<ClassificationDto> classificationDtoList = new ArrayList<>();
        for (ClassificationEntity classificationEntity : classificationEntityList) {
            classificationDtoList.add(new ClassificationDto(classificationEntity));
        }
        return classificationDtoList;
    }

    public static ClassificationDto toClassificationDto(ClassificationEntity classificationEntity) {
        return new ClassificationDto(classificationEntity);
    }


    @Getter
    @Setter
    static class Famille {
        private final int idFamille;
        private final String nomFamille;

        public Famille(FamilleEntity familleEntity) {
            idFamille = familleEntity.getIdFamille();
            nomFamille = familleEntity.getFamille();
        }
    }

}
