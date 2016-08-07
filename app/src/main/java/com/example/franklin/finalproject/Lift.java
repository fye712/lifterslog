package com.example.franklin.finalproject;

import java.util.Date;

/**
 * Created by Franklin on 12/13/2015.
 * Lift class to be put in the SQLite Database
 */
public class Lift {

    int _id;
    String _name;
    int _reps;
    int _weight;
    String _date;

    public Lift() {
        //Empty Constructor
    }

    //Constructor with ID
    public Lift(int id, String name, int reps, int weight, String date) {
        this._id = id;
        this._name = name;
        this._reps = reps;
        this._weight = weight;
        this._date = date;
    }

    //Constructor without ID
    public Lift(String name, int reps, int weight, String date) {
        this._name = name;
        this._reps = reps;
        this._weight = weight;
        this._date = date;
    }

    /**
     * gets the ID of the lift
     *
     * @return
     */
    public int getID() {
        return this._id;
    }

    /**
     * Sets the ID of the lift
     *
     * @param id
     */
    public void setID(int id) {
        this._id = id;
    }

    /**
     * Gets the name of the lift
     *
     * @return
     */
    public String getName() {
        return this._name;
    }

    /**
     * Sets the name of the Lift
     *
     * @param name
     */
    public void setName(String name) {
        this._name = name;
    }

    /**
     * gets the number of reps
     *
     * @return
     */
    public int getReps() {
        return this._reps;
    }

    /**
     * sets the number of reps
     *
     * @param reps
     */
    public void setReps(int reps) {
        this._reps = reps;
    }

    /**
     * gets the weight
     *
     * @return
     */
    public int getWeight() {
        return this._weight;
    }

    /**
     * sets the weight
     *
     * @param weight
     */
    public void setWeight(int weight) {
        this._weight = weight;
    }

    /**
     * gets the date
     *
     * @return
     */
    public String getDate() {
        return this._date;
    }

    /**
     * sets the date
     *
     * @param date
     */
    public void setDate(String date) {
        this._date = date;
    }
}
