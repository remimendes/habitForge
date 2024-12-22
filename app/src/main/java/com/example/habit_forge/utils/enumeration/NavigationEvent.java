package com.example.habit_forge.utils.enumeration;

public class NavigationEvent {

    private final EventType eventType;
    private final int habitId;

    public NavigationEvent(EventType eventType, int habitId) {
        this.eventType = eventType;
        this.habitId = habitId;
    }

    public EventType getEventType() {
        return eventType;
    }

    public int getId() {
        return habitId;
    }

    public enum EventType {
        Creation,Edit, Interaction, Infos
    }
}
