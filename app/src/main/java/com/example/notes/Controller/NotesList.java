package com.example.notes.Controller;

import com.example.notes.ui.home.HomeFragment;

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
        HomeFragment.mrecyclerViewAdapter.notifyDataSetChanged();
    }

    public static void deleteNotes(int index) {
        if(index < notesLists.size())
            notesLists.remove(index);
        HomeFragment.mrecyclerViewAdapter.notifyDataSetChanged();
    }

    public static void updateNotes(NotesList notesList, int index) {
        notesLists.remove(index);
        notesList.addNotes(notesList);
        HomeFragment.mrecyclerViewAdapter.notifyDataSetChanged();
    }

    public static ArrayList<NotesList> getNotes() {
        return notesLists;
    }



}
