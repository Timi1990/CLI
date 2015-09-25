package controller;

import java.util.HashMap;

import model.Model;
import view.View;

/**
 * A Controller interface, holds a hashMap of "String,Command", mediates between
 * the View and the Model
 * @see Model
 * @see View
 */
public interface Controller
{
   HashMap<String, Command> createCommandNameToCommand();
}
