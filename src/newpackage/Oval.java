/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package newpackage;

import java.awt.Color;

/**
 *
 * @author ayaah
 */
public class Oval extends GeoShape
{
    public Oval()
    {
    }
    public Oval(int x1,int y1, int x2,int y2, char shape,  boolean dashedPressed, boolean filledPressed,Color currentColor){
			super(x1, y1, x2, y2, shape, dashedPressed, filledPressed,currentColor);
		}
    
}

	