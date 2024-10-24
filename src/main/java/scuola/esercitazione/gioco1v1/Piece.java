package scuola.esercitazione.gioco1v1;

import java.io.Serializable;
import java.util.ArrayList;

public abstract class Piece implements Serializable{

    protected Position position;
    protected Player owner;
    protected int id;
    protected ArrayList<Move> validMoves;

    public abstract void calculateMoves();

    public Piece(Position position, Player owner, int id) {
        this.position = position;
        this.owner = owner;
        this.id = id;
        this.validMoves = new ArrayList<>();
    }

    public Position getPosition() {
        return position;
    }

    public Player getOwner() {
        return owner;
    }

    public int getId() {
        return id;
    }

    public void moveTo(Position position) {
        this.position = position;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ArrayList<Move> getValidMoves() {
        return this.validMoves;
    }

    public abstract Piece clone();

    // GETTERS DELLE MOSSE SPECIFICHE PER LE PEDINE
    // PEDONI
    public abstract Move getFORWARD1();
    public abstract Move getFORWARD2();
    public abstract Move getDIAGONALLEFT();
    public abstract Move getDIAGONALRIGHT();
    // TORRI
    public abstract ArrayList<Move> getTOP();
    public abstract ArrayList<Move> getRIGHT();
    public abstract ArrayList<Move> getBOTTOM();
    public abstract ArrayList<Move> getLEFT();
    // ALFIERI
    public abstract ArrayList<Move> getTOPLEFTDIAGONAL();
    public abstract ArrayList<Move> getTOPRIGHTDIAGONAL();
    public abstract ArrayList<Move> getBOTTOMLEFTDIAGONAL();
    public abstract ArrayList<Move> getBOTTOMRIGHTDIAGONAL();

}
