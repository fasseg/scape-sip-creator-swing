package eu.scapeproject.sip;

import java.util.ArrayList;
import java.util.List;

import eu.scapeproject.model.IntellectualEntity;

public class SIP {
    private String title;
    private final List<IntellectualEntity> entities = new ArrayList<IntellectualEntity>();

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<IntellectualEntity> getEntities() {
        return entities;
    }

    public void addEntities(List<IntellectualEntity> entities) {
        this.entities.addAll(entities);
    }

    public void addEntity(IntellectualEntity entity) {
        this.entities.add(entity);
    }

}
