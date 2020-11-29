package com.example.notes.Controller;

import java.util.ArrayList;

public class NotesList {
    private String notesTitle = "";
    private String notesContent = "";
    public static ArrayList<NotesList> notesLists = new ArrayList<>();

    public NotesList(String notesTitle, String notesContent) {
        this.notesTitle = notesTitle;
        this.notesContent = notesContent;
    }


    public String getNotesTitle() {
        return notesTitle;
    }

    public void setNotesTitle(String notesTitle) {
        this.notesTitle = notesTitle;
    }

    public String getNotesContent() {
        return notesContent;
    }

    public void setNotesContent(String notesContent) {
        this.notesContent = notesContent;
    }

    public static void addNotes(NotesList notes) {
        notesLists.add(notes);
    }

    public static ArrayList<NotesList> getNotes() {
        return notesLists;
    }



}
