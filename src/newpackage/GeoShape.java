package newpackage;
import java.awt.Color;
class GeoShape{
	protected int x1;
	protected int y1;
	protected int x2; 
	protected int y2;
	private char shape;
	protected boolean dashpressed;
	protected boolean fillpressed;
	protected Color color;
	
	public GeoShape(){
		
	}
	public GeoShape(int x1, int y1, int x2, int y2, char currShape, boolean dashpressed, boolean fillpressed,Color currentColor ){
		
			this.x1=x1;
			this.y1=y1;
			this.x2=x2;
			this.y2=y2;
			this.shape=currShape;
			this.dashpressed=dashpressed;
			this.fillpressed=fillpressed;
			if(currShape == 'e')
				this.color =Color.white;
			else
				this.color= currentColor;
}
	public GeoShape(int x1, int y1, int x2, int y2, char currShape, boolean dashpressed,Color currentColor ){
		
			this.x1=x1;
			this.y1=y1;
			this.x2=x2;
			this.y2=y2;
			this.shape=currShape;
			this.dashpressed=dashpressed;
			this.color= currentColor;
}
	boolean isFilled()
        {
		return fillpressed;
	}
	void setShape(char s){
		shape=s;
}
	boolean getDottedState(){
		return this.dashpressed;
	}
	char getShape(){
		return shape;
}

	void setX1(int x1){
		
			x1=x1;
		}
	void setY1(int y1){
			y1=y1;
		}
	public void setX2(int x2){
			x2=x2;
		}
	public void setY2(int Y2){
			y2=y2;
		}
	

	int getX1(){
			return x1;
		}
	int getY1(){
			return y1;
		}
	int getX2(){
			return x2;
		}
	int getY2(){
			return y2;
		}
	public Color getColor(){
		return color;
	}
	public char getShapeName(){
		return shape;
}	
}