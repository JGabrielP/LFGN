package com.gp.jgabrielp10.lfgn.Models;

import com.google.firebase.Timestamp;

import java.util.List;

public class Match {
    public Team Local;
    public Team Visit;
    public Timestamp Date;
    public Field Field;
    public int GoalsLocal;
    public int GoalsVisit;
    public List<Player> GoalsPlayersLocal;
    public List<Player> GoalsPlayersVisit;
    public List<Player> YCardsLocal;
    public List<Player> YCardsVisit;
    public List<Player> RCardsLocal;
    public List<Player> RCardsVisit;
    public boolean Finished;
}
