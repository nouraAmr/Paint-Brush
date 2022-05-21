package newpackage;
import java.awt.Color;
import java.applet.Applet;
import java.awt.Graphics2D;
import java.awt.Graphics;
import java.awt.Button;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.*;
import java.awt.BasicStroke;
import java.awt.Checkbox;
public class myApplet extends Applet 
{
    int x1, y1,x2, y2, x, y,width=0,height=0,eraserWidth=30,eraserHeight=30,rad,j=0,counter;
    boolean keepX2=false,keepY2=false,clearPressed = false,undoPressed = false,fillcheck=false,dashcheck=false;
    Vector <GeoShape> shapesVector = new Vector <GeoShape>();
    Button btnLine,btnRec,btnOval,btnRed,btnGreen,btnBlue,btnFreeHand,btnEraser,btnClear,btnDotted,btnFill,btnUndo;
    Color currentColor = Color.black; //default color
    char currentShape;   
    Color c;
    Checkbox fillCheck,dashedCheck;
public void init()
{
 
    fillCheck = new Checkbox("Fill",false);
    dashedCheck = new Checkbox("Dashed",false);
    btnLine =new Button("Line");
    btnLine.addActionListener(new ActionListener(){public void actionPerformed(ActionEvent e){currentShape = 'l';}});
    add(btnLine);
    btnRec =new Button("Rectangle");
    btnRec.addActionListener(new ActionListener(){public void actionPerformed(ActionEvent e){currentShape = 'r';}});
    add(btnRec);
    btnOval =new Button("Oval");
    btnOval.addActionListener(new ActionListener(){public void actionPerformed(ActionEvent e){currentShape= 'c';}});
    add(btnOval);
    btnRed =new Button("Red");
    btnRed.addActionListener(new ActionListener(){public void actionPerformed(ActionEvent e){currentColor= Color.red;repaint();}});
    add(btnRed);
    btnBlue =new Button("Blue");
    btnBlue.addActionListener(new ActionListener(){public void actionPerformed(ActionEvent e){currentColor= Color.blue;repaint();}});
    add(btnBlue);
    btnGreen =new Button("Green");
    btnGreen.addActionListener(new ActionListener(){public void actionPerformed(ActionEvent e){currentColor= Color.green;repaint();}});
    add(btnGreen);
    btnFreeHand =new Button("Free Hand");
    btnFreeHand.addActionListener(new ActionListener(){public void actionPerformed(ActionEvent e){currentShape= 'f';repaint();}});
    add(btnFreeHand);
    btnEraser =new Button("Eraser");
    btnEraser.addActionListener(new ActionListener(){public void actionPerformed(ActionEvent e){currentShape='e';}});
    add(btnEraser);                
    btnClear =new Button("CLEAR ALL");
    btnClear.addActionListener(new ActionListener(){public void actionPerformed(ActionEvent e){
    clearPressed=true;repaint();}});
    add(btnClear); 
    add(fillCheck);                
    add(dashedCheck);   

    btnUndo =new Button("Undo");
    btnUndo.addActionListener(new ActionListener(){
    public void actionPerformed(ActionEvent e){
        undoPressed = true;
        if(shapesVector.size()>0)
            shapesVector.removeElementAt(shapesVector.size()-1);
        repaint();
	}});
    add(btnUndo);		
    MyMouseListener myMouseLisner = new MyMouseListener();
    MyMotionListener myMotionLisner = new MyMotionListener();
    this.addMouseListener(myMouseLisner);
    this.addMouseMotionListener(myMotionLisner);
}
public void paint(Graphics g)
{
    float[] dash = {10,10,10};
    Graphics2D g2d = (Graphics2D) g.create();
    if(clearPressed)
    {
        shapesVector= new Vector <GeoShape>();
        clearPressed = false;
    }
    for (j=0; j< shapesVector.size(); j++)
    {
        g2d.setColor(shapesVector.get(j).getColor());
        if(shapesVector.get(j).getDottedState())
            g2d.setStroke(new BasicStroke(3, BasicStroke.CAP_BUTT,BasicStroke.JOIN_BEVEL,10,dash,10 ));
        else
            g2d.setStroke(new BasicStroke(0));
        switch(shapesVector.get(j).getShapeName())
        {
            case 'l':
                g2d.drawLine(shapesVector.get(j).getX1(), shapesVector.get(j).getY1(),shapesVector.get(j).getX2(), shapesVector.get(j).getY2());
                break;
            case 'r':
                g2d.drawRect(shapesVector.get(j).getX1(), shapesVector.get(j).getY1(), shapesVector.get(j).getX2(), shapesVector.get(j).getY2());	
		if(shapesVector.get(j).isFilled())
                    g2d.fillRect(shapesVector.get(j).getX1(), shapesVector.get(j).getY1(),shapesVector.get(j).getX2(), shapesVector.get(j).getY2());
		break;
            case 'c':
                g2d.drawOval(shapesVector.get(j).getX1(), shapesVector.get(j).getY1(),shapesVector.get(j).getX2(), shapesVector.get(j).getY2());	
		if(shapesVector.get(j).isFilled())
                    g2d.fillOval(shapesVector.get(j).getX1(), shapesVector.get(j).getY1(),shapesVector.get(j).getX2(), shapesVector.get(j).getY2());	
                break;
            case 'f':
                g2d.fillOval(shapesVector.get(j).getX1(), shapesVector.get(j).getY1(), 25, 25);
                break;
            case 'e':
                g2d.fillOval(shapesVector.get(j).getX1(), shapesVector.get(j).getY1(), 30, 30);
        }
    }
    if(dashcheck)
    {
        g2d.setStroke(new BasicStroke(0, BasicStroke.CAP_BUTT,BasicStroke.JOIN_BEVEL,10,dash,10 ));
    }
    g.setColor(currentColor);
    switch(currentShape)
    {
        case 'e':
            g.setColor(Color.WHITE);
            g.fillOval(x2, y2, eraserWidth, eraserHeight);
            break;
        case 'l':
            if(!undoPressed)
                g2d.drawLine( x1, y1, x2, y2);
            break;
        case 'r':
            x= (x1-x2) <0? x1: x2;
            y= (y1-y2) <0 ? y1 : y2;
            g.drawRect(x, y, width, height);
            if(fillcheck)
            {
                g.setColor(currentColor);
                g.fillRect(x, y, width, height);
            }
            width =height =0;
            break;
        case 'c':
            x= (x1-x2) <0 ? x1 : x2;
            y= (y1-y2) <0 ? y1 : y2;
            g2d.drawOval(x, y,width, height);
            if(fillcheck)
            {
                g.setColor(currentColor);
                g.fillOval(x, y, width, height);
            }
            width =height =0;
            break;
        case 'f':
            g.setColor(currentColor);
            g.fillOval(x2, y2, 1, 1);
    }
    if(undoPressed)
    {
        undoPressed = false;
    }
}
class MyMouseListener implements MouseListener
{
    public void mousePressed(MouseEvent e)
    {			
        x1= x2 =e.getX();
        y1= y2 =e.getY();
        fillcheck = fillCheck.getState();
        dashcheck = dashedCheck.getState();
    }
    public void mouseReleased(MouseEvent e) 
    {
        x2=e.getX();
        y2=e.getY();
        width= Math.abs(x2-x1);
        height = Math.abs(y2-y1);
	switch(currentShape)
        {
            case 'l':
                shapesVector.add(new Line(x1, y1, x2, y2, 'l',dashcheck,currentColor));
		break;
            case 'r':
		x= (x1-x2) <0 ? x1 : x2;
		y= (y1-y2) <0 ? y1 : y2;
		shapesVector.add(new Rect(x, y, width, height, 'r', dashcheck, fillcheck,currentColor));
		break;
            case 'c':
                x= (x1-x2) <0 ? x1 : x2;
                y= (y1-y2) <0 ? y1 : y2;
                System.out.println("circle fill check = "+fillcheck);
                shapesVector.add(new Oval(x, y, width, height, 'c', dashcheck, fillcheck,currentColor));
                break;

	}
        repaint();
}

        @Override
        public void mouseClicked(MouseEvent e) {
            //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        }

        @Override
        public void mouseEntered(MouseEvent e) {
//            throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        }

        @Override
        public void mouseExited(MouseEvent e) {
           // throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        }
    }
	
class MyMotionListener implements MouseMotionListener
{
    public void mouseMoved(MouseEvent e) 
    {}
    public void mouseDragged(MouseEvent e)
    {
        x2=e.getX();
        y2=e.getY();
        width= Math.abs(x2-x1);
        height = Math.abs(y2-y1);
        switch(currentShape)
        {
            case 'e':
                shapesVector.add(new Rect( x2, y2,eraserWidth, eraserHeight, 'e',dashcheck, fillcheck,Color.white));
		break;
            case 'f':
                shapesVector.add(new Rect( x2, y2,10, 10, 'f',  dashcheck, fillcheck,currentColor));
                break;
				
	}
    }
}
}